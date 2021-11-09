import random
import string


def read_from_file(file_name):
    """
    Reads all words from file
    Parameter file_name is the name of the file
    This function returns a list containing all the words
    """
    f = open(file_name, "r")
    all_words = f.read().splitlines()
    f.close()
    return all_words


def ask_for_length():
    """
    Ask the user for the number of hand cards
    Prompt again if the user input is not a valid integer, or if the number is not between 3 to 10, inclusive
    Returns the number of hand cards L
    """
    num_of_card = input('Enter a number between 3 - 10 to be length of the word you are going to guess:\n')
    while (not num_of_card.isdigit()) or int(num_of_card) < 3 or int(num_of_card) > 10:
        num_of_card = input('Enter a number between 3 - 10 to be length of the word you are going to guess:\n')
    return int(num_of_card)


def filter_word_list(all_words, length):
    """
    Return a list of words with the specific length
    """
    temp = []
    for word in all_words:
        if len(word) == length:
            temp.append(word)

    return temp


def set_up(length):
    """
    Creates a main pile: 26*length cards, represented as a list of lowercase letters, with length of each letter
    Creates a discard pile of 0 cards, represented as an empty list
    """
    letter_26 = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']
    main_pile = letter_26 * length
    discard_pile = []
    return (main_pile, discard_pile)


def shuffle_cards(pile):
    """
    This function shuffles the given pile
    """
    random.shuffle(pile)


def move_to_discard_pile(discard_pile, card):
    """
    Move the given card to the top of the discard pile
    """
    discard_pile.insert(0, card)
    return


def deal_initial_cards(main_pile, discard_pile, length):
    """
    Dealing two sets of length cards each, one card to the computer, one to the user, computer gets dealt first
    Remove the card on top of the main pile and put it on the discard pile
    """
    user_hand = []
    computer_hand = []
    for i in range(length * 2):
        card = get_first_from_pile_and_remove(main_pile)
        if i % 2 == 0:
            computer_hand.append(card)
        else:
            user_hand.append(card)
    card = get_first_from_pile_and_remove(main_pile)
    move_to_discard_pile(discard_pile, card)

    return (user_hand, computer_hand)


def get_first_from_pile_and_remove(pile):
    """
    Return and remove the first item of the given list
    """
    first_element = pile[0]
    del pile[0]

    return first_element


def check_bricks(main_pile, discard_pile):
    """
    Check whether the main_pile is empty.
    If so, shuffles the discard_pile and moves all the cards to the main_pile.
    Then turn over the top card of the main_pile to be the start of the new discard_pile.
    """
    if not main_pile:
        shuffle_cards(discard_pile)

        temp_len = len(discard_pile)
        for i in range(temp_len):
            main_pile.append(get_first_from_pile_and_remove(discard_pile))

        card = get_first_from_pile_and_remove(main_pile)
        move_to_discard_pile(discard_pile, card)


def similarities_test(computer_hand, word_valid_atime):
    """
    Calculate the similarity between two words length
    """

    # len_before = len(computer_hand)
    # word_valid_atime_char = list(word_valid_atime)
    # for i in computer_hand:
    #     if i in word_valid_atime_char:
    #         word_valid_atime_char.remove(i)
    #
    # len_after = len(word_valid_atime_char)
    # similarities = (len_before - len_after)/ len_before

    num_same_position = []

    count = 0
    for i in range(len(computer_hand) ):
        if computer_hand[i] == word_valid_atime[i]:
            num_same_position.append(1)
            count += 1
        else:
            num_same_position.append(0)
    similarities = count/len(computer_hand)

    return similarities


def similarities_test2(computer_hand, word_valid_atime, card_to_get):
    """
    Replace the character of the word.
    Then calculate the similarity between two words.
    Return the max value of the similarities.
    """
    similarities = []
    for i in range(len(computer_hand)):
        if computer_hand[i] != word_valid_atime[i]:
            temp = computer_hand.copy()
            temp[i] = card_to_get
            similarities.append(similarities_test(temp, word_valid_atime))

    return max(similarities)


def computer_target_list_generate(computer_hand, words_valid):
    """
    compute how many similarities there are between each word in the computer’s target list of words and the letters on hand.
    And you can test if replacing a specific card with another card can increase the similarities.
    """
    similarities = []
    target_list = []
    for word in words_valid:
        similarities.append(similarities_test(computer_hand, word))

    for i in range(len(words_valid) - 1):
        if similarities[i] >= 0.5:
            target_list.append(words_valid[i])

    if not target_list:
        return words_valid

    return target_list


# 判断电脑是否要discard_pile
def computer_decide_for_pile(computer_hand_cards, computer_target_list, card_to_get):
    """
    Decide whether the computer need the word from discard_pile or not.
    """
    similarities_original = []
    similarities_if_change = []
    for target in computer_target_list:
        similarities_original.append(similarities_test(computer_hand_cards, target))

    max_similarities_original = max(similarities_original)
    if max_similarities_original != 1:
        # A temporary list
        temp_computer_hand_cards = computer_hand_cards.copy()

        for target in computer_target_list:
            similarities_if_change.append(similarities_test2(temp_computer_hand_cards, target, card_to_get))

        max_similarities_if_change = max(similarities_if_change)
        index_card = similarities_if_change.index(max_similarities_if_change)

        # If replace, return true; otherwise, return false
        if max_similarities_if_change > max_similarities_original:
            return [max_similarities_if_change > max_similarities_original, index_card]
        else:
            return [False]

    return [False]



def decide_which_replace(computer_hand_cards, target_word, discard_pile, word_ready):
    """
    Decide which character is going to be replaced.
    The first character which is not in target word will be replaced.
    """
    for i in range(len(computer_hand_cards) - 1):
        if computer_hand_cards[i] not in target_word:
            move_to_discard_pile(discard_pile, computer_hand_cards[i])
            computer_hand_cards[i] = word_ready
            return


def computer_play(computer_hand_cards, computer_target_list, main_pile, discard_pile):
    """
    Strategy:
    1. Calculate the similarities between the computer's word and the words in target list, get the max value of these similarities.

    2. Decide whether take card from discard_pile
    Calculate the max similarities value after replaced,
    compare the max similarities before and after the change, if it becomes larger, then take the card; otherwise, do not take the card.
    If the card was chosen, replace the character which is not helpful for the similarity with the chosen word.

    3. Decide whether take card from discard, if the computer do not take card from discard_pile
    Calculate the max similarities value after replaced,
    compare the max similarities before and after the change, if it becomes larger, then take the card; otherwise, do not take the card.
    If the card was chosen, replace the character which is not helpful for the similarity with the chosen word.
    """
    print('Computers\' turn')
    # If it returns true, replaced
    computer_decide_1 = computer_decide_for_pile(computer_hand_cards, computer_target_list, discard_pile[0])
    # Take the card from the discard_pile
    if computer_decide_1[0]:
        # Decide which character is going to be replaced
        # The the index of the word in target_list, which has the largest similarity with the card in computer's hand
        target_word = computer_target_list[computer_decide_1[1]]
        card_to_get_from_discard_pile = get_first_from_pile_and_remove(discard_pile)
        decide_which_replace(computer_hand_cards,target_word, discard_pile,card_to_get_from_discard_pile)

        print('Computer took \'{}\' from DISCARD PILE'.format(card_to_get_from_discard_pile))

    # Not to take the card from discard_pile
    # Decide whether take card from main_pile
    else:
        card_to_get_from_main_pile = get_first_from_pile_and_remove(main_pile)
        computer_decide_2 = computer_decide_for_pile(computer_hand_cards, computer_target_list, card_to_get_from_main_pile)
        # Take the card from the main_pile
        if computer_decide_1[0]:
            # Decide which character is going to be replaced
            # The index of the word in target_list, which has the largest similarity with the card in computer's hand
            target_word = computer_target_list[computer_decide_1[1]]
            decide_which_replace(computer_hand_cards, target_word, discard_pile, card_to_get_from_main_pile)
            print('Computer took \'{}\' from MAIN PILE'.format(card_to_get_from_main_pile))
        # Do not take, throw the card to discard_pile
        else:
            print('Computer discarded \'{}\' from MAIN PILE'.format(card_to_get_from_main_pile))
            discard_pile.insert(0, card_to_get_from_main_pile)

    print('computer\'s current hand is \n{}'.format(computer_hand_cards))


def ask_user_which_pile(main_pile, discard_pile,user_hand):
    """
    Ask user which pile they want to choose.
    """
    print('Your turn \nYour word list is: \n{} '.format(user_hand))
    print('Pick \'{}\' from DISCARD PILE or reveal the letter from MAIN PILE'.format(discard_pile[0]))
    respond_user_choose = input('Reply \'D\' or \'M\' to respond:\n')
    respond_user_choose = respond_user_choose.strip()

    while (respond_user_choose != 'd' and respond_user_choose != 'D' and respond_user_choose != 'm' and respond_user_choose != 'M'):
        print('Please enter a valid response')
        print('Your turn \nYour word list is: \n{} '.format(user_hand))
        print('Pick \'{}\' from DISCARD PILE or reveal the letter from MAIN PILE'.format(discard_pile[0]))
        respond_user_choose = input('Reply \'D\' or \'M\' to respond:')
        respond_user_choose = respond_user_choose.strip()

    if respond_user_choose == 'd' or respond_user_choose == 'D':
        return discard_pile

    if respond_user_choose == 'm' or respond_user_choose == 'M':
        return main_pile


def ask_for_the_letter_to_be_replaced(length):
    """
    Ask for the index of the letter that the user wants to replace
    Prompt again if the input index is out of range or invalid
    This function returns the index of the letter to be replaced
    """

    letter_index_chosen = input('Input the index of the letter to be replaced, e.g. \'1\':\n')
    while (not letter_index_chosen.isnumeric()) or int(letter_index_chosen) >= length:
        if not letter_index_chosen.isnumeric():
            print('Please enter an valid integer.')
        else:
            print('Index out of range')
        letter_index_chosen = input('Input the index of the letter to be replaced, e.g. \'1\':\n')

    return int(letter_index_chosen)


def ask_yes_or_no(msg):
    """
    Displays msg and get user's response
    This function returns True if the user answers ‘y’ or ‘yes’, and returns False if the user answers ‘n’ or ‘no’
    """
    user_answer = input(msg)
    user_answer = user_answer.strip().lower()
    while user_answer != 'y' and user_answer != 'yes' and user_answer != 'n' and user_answer != 'no':
        user_answer = input(msg)

    if user_answer == 'y' or user_answer == 'yes':
        return True
    else:
        return False


def check_game_over(human_hand_cards, computer_hand_cards, words_with_specific_length):
    """
    Check if the game ends
    If there is a tie, the game ends as well
    Returns True if the human or the computer wins the game, otherwise False
    """
    # user_similarities = []
    # computer_similarities = []
    # for word in words_with_specific_length:
    #     user_similarities.append(similarities_test(human_hand_cards, word))
    #     computer_similarities.append(similarities_test(computer_hand_cards, word))
    # user_maxsimilarity = max(user_similarities)
    # user_maxsimilarity_index = user_similarities.index(user_maxsimilarity)
    # computer_maxsimilarity = max(computer_similarities)
    # computer_maxsimilarity_index = computer_similarities.index(computer_maxsimilarity)

    # if user_maxsimilarity == 1 and computer_maxsimilarity == 1:
    #     print("Tie!")
    #     print('Your word is {}'.format(words_with_specific_length[user_maxsimilarity_index]))
    #     print('Computer\'s word is {}'.format(words_with_specific_length[computer_maxsimilarity_index]))
    #     return True
    # elif user_maxsimilarity == 1:
    #     print("you win!")
    #     print('Your word is {}'.format(words_with_specific_length[user_maxsimilarity_index]))
    #     return True
    # elif computer_maxsimilarity == 1:
    #     print("computer win!")
    #     print('Computer\'s word is {}'.format(words_with_specific_length[computer_maxsimilarity_index]))
    #     return True
    # else:
    #     return False
    human_card_str = ''.join(human_hand_cards)
    computer_card_str = ''.join(computer_hand_cards)
    human_card_in = human_card_str in words_with_specific_length
    computer_card_in = computer_card_str in words_with_specific_length

    if human_card_in and computer_card_in:
        print("Tie!")
        print('Your word is {}'.format(human_card_str))
        print('Computer\'s word is {}'.format(computer_card_str))
        return True
    elif human_card_in:
        print('Your word is {}'.format(human_card_str))
        print("you win!")
        return True
    elif computer_card_in:
        print('Computer\'s word is {}'.format(computer_card_str))
        print("computer wins!")
        return True
    else:
        return False


def main():
    # reads all words from file
    all_words = read_from_file("words.txt")

    print("Welcome to the game!")

    # ask for a number as the length of the word
    length = ask_for_length()

    # filter all_words with a length equal to the given length
    words_valid = filter_word_list(all_words, length)

    # set up main_pile and discard_pile
    pile = set_up(length)
    main_pile = pile[0]
    discard_pile = pile[1]

    # shuffle main pile
    shuffle_cards(main_pile)

    # deal cards to players, creating human_hand_cards and computer_hand_cards
    # and initialize discard pile
    both_hand = deal_initial_cards(main_pile, discard_pile, length)
    user_hand = both_hand[0]
    computer_hand = both_hand[1]

    # start the game
    while True:
        # check if main_pile is empty by calling check_bricks(main_pile, discard_pile)
        check_bricks(main_pile, discard_pile)

        # generate computer_target_list
        computer_target_list = computer_target_list_generate(computer_hand, words_valid)

        # computer play goes here
        computer_play(computer_hand, computer_target_list, main_pile, discard_pile)
        computer_target_list_generate(computer_hand, words_valid)

        # if (len(discard_pile) == 0):
        #     move_to_discard_pile(discard_pile, get_first_from_pile_and_remove(main_pile))

        # human play goes here
        print('-----------------------------------------------')

        pile_chosen = ask_user_which_pile(main_pile, discard_pile,user_hand)
        word_chosen = get_first_from_pile_and_remove(pile_chosen)

        # When user choose main_pile, show this card, then ask whether they want to take this card or not
        if pile_chosen == main_pile:
            print('The letter from main_pile is \'{}\''.format(word_chosen))
            answer_yes_or_no = ask_yes_or_no('Do you want to accept this letter? Type \'y/yes\' to accept, \'n/no\' to discard.\n')
            if answer_yes_or_no:
                index_chosen = ask_for_the_letter_to_be_replaced(length)
                user_hand[index_chosen] = word_chosen
                print('You replaced \'{}\' with \'{}\''.format(user_hand[index_chosen], word_chosen))
            else:
                print('You discarded \'{}\' from MAIL PILE'.format(word_chosen))
                discard_pile.insert(0, word_chosen)
        # When user choose the discard_pile, ask which card will be replaced
        else:
            print('The letter from discard_pile is \'{}\''.format(word_chosen))
            index_chosen = ask_for_the_letter_to_be_replaced(length)
            discard_pile.insert(0, user_hand[index_chosen])
            user_hand[index_chosen] = word_chosen
            print('You replaced \'{}\' with \'{}\''.format(user_hand[index_chosen], word_chosen))

        print('-----------------------------------------------')

        # check if game is over and print out results
        over_or_not = check_game_over(user_hand, computer_hand, words_valid)
        if over_or_not:
            break

        pass


if __name__ == "__main__":
    main()
