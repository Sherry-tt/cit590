# Name: Rui Tan
# PennId: 34283415
# Statement of work: I worked alone without help



# import the random module
# use "random_int = randint(1, 13)" to generate a random int from 1 - 13 and store in a variable "random_int"
from random import randint


def print_instructions():
    """
    Prints out instructions for the game.
    """
    print("Let's play Simple21!"+"\n"
    +"You'll play against the computer." + "\n"
    + "Try to get as close to 21 as possible, without going over.")


def ask_yes_or_no(prompt):
    """
    Displays the given prompt and asks the user for input.  If the user's input starts with 'y', returns True.
    If the user's input starts with 'n', returns False.
    For example, calling ask_yes_or_no("Do you want to play again? (y/n)")
    would display "Do you want to play again? (y/n)", wait for user input that starts with 'y' or 'n',
    and return True or False accordingly.
    """
    yes_or_no = input(prompt)
    #trim leading and trailing whitespace
    yes_or_no = yes_or_no.strip().lower()
    #check whether input is valid
    while (not yes_or_no.startswith('y') and not yes_or_no.startswith('n')):
        yes_or_no = input(prompt)
        yes_or_no = yes_or_no.strip().lower()

    if (yes_or_no[0] == 'y'):
        return True
    elif(yes_or_no[0] == 'n'):
        return False


def next_card():
    """
    Returns a random "card", represented by an int between 1 and 10, inclusive.
    The "cards" are the numbers 1 through 10 and they are randomly generated, not drawn from a deck of
    limited size.  The odds of returning a 10 are four times as likely as any other value (because in an
    actual deck of cards, 10, Jack, Queen, and King all count as 10).
    """
    # generate random point
    card_points = randint(1,13)
    if (card_points >= 10):
        return 10;
    else:
        return card_points


def take_another_card(computer_total_points, user_visible_card):
    """
    Strategy for computer to take another card or not.  According to the computer’s own given
    total points (sum of visible cards + hidden card) and the user's sum of visible cards, you
    need to design a game strategy for the computer to win the game.
    Returns True if the strategy decides to take another card, False if the computer decides not
    to take another card.
    """
    # take card if computer's points less than or equal to 11
    if (computer_total_points <= 11 ):
        return True
    # take card if computer have less point than user
    if (computer_total_points <  user_visible_card):
        return True
    # do not take card in other situations
    return False




def is_game_over(is_user_passed, is_computer_passed):
    """
    Determines if the game is over or not.
    If the given is_user_passed is set to True, the user has passed.
    If the given is_computer_passed is set to True, the computer has passed.
    This function returns True if both the user and the computer have passed,
    and False if either of them has not yet passed.
    """
    if (is_user_passed == True and is_computer_passed == True):
        return True
    else:
        return False


def print_status(is_user, name, hidden_card, visible_card, total_points):
    """
    In each turn, prints out the current status of the game.
    If is_user is set to True, the given player is the user.  In this case, print out
    the user's given name, his/her hidden card points, visible card points, and total points.
    If is_user is set to False, the given player is the computer.  In this case, print out
    the computer's given name, and his/her visible card points.

    For example, calling print_status(True, "Brandon", 4, 15, 19) would print:
    Brandon has 4 hidden point(s).
    Brandon has 15 visible point(s).
    Brandon has 19 total point(s).

    As another example, calling print_status(False, "Computer", 1, 19, 20) would print:
    Computer has 19 visible point(s).
    """
    if (is_user == True):
        print('{} has {} hidden point(s).'.format(name, hidden_card))
        print('{} has {} visible point(s).'.format(name, visible_card))
        print('{} has {} total point(s).'.format(name, total_points))
    elif (is_user == False):
        print('{} has {} visible point(s).'.format(name,visible_card))


def print_winner(username, user_total_points, computer_name, computer_total_points):
    """
    Determines who won the game and prints the game results in the following format:
    - User's given name and the given user's total points
    - Computer's given name and the given computer's total points
    - The player who won the game and the total number of points he/she won by, or if it's a tie, nobody won.
    """
    print('{} has {} in total'.format(username, user_total_points))
    print('{} has {} in total'.format(computer_name, computer_total_points))

    # when one or both of them have total points greater than 21
    if (user_total_points > 21 or computer_total_points > 21 ):
        if (user_total_points > 21 and computer_total_points > 21 ):
            print('Nobody wins.')
        elif (user_total_points > 21):
            print('{} wins by {} point(s).'.format(computer_name, user_total_points - computer_total_points))
        elif (computer_total_points > 21):
            print('{} wins by {} point(s).'.format(username, computer_total_points - user_total_points))

    # when the total points all less than or equal to 21
    else:
        if (user_total_points == computer_total_points):
            print("Nobody wins. It's a tie!")
        elif(user_total_points > computer_total_points):
            print('{} wins by {} point(s).'.format(username, user_total_points - computer_total_points))
        elif(user_total_points < computer_total_points):
            print('{} wins by {} point(s).'.format(computer_name, computer_total_points - user_total_points))

    return


def run(username, computer_name):
    """
    This function controls the overall game and logic for the given user and computer.
    """
    #initial
    is_user_passed = False
    is_computer_passed = False

    # assign the user two random cards
    user_visible_card = next_card()
    user_hidden_card = next_card()
    user_total = user_visible_card + user_hidden_card
    # assign the computer two random cards
    comp_visible_card = next_card()
    comp_hidden_card = next_card()
    comp_total = comp_visible_card + comp_hidden_card
    #print status
    print_status(True, username, user_hidden_card, user_visible_card, user_total)
    print_status(False, computer_name, comp_hidden_card, comp_visible_card, comp_total)

    # ask the user/computer if they want to take random cards
    while (not is_game_over(is_user_passed, is_computer_passed)):
        if (is_user_passed == False):
            # if do not take another card, return False,and passed
            is_user_passed = not ask_yes_or_no('Take another card? (y/n)')
            # user does not pass
            if (is_user_passed == False):
                user_gets = next_card()
                print('{} gets {}'.format(username, user_gets))
                user_visible_card += user_gets
                user_total = user_visible_card + user_hidden_card
                print_status(True, username, user_hidden_card, user_visible_card, user_total)
            # user passed
            else:
                print('{} passed!'.format(username))

        if (is_computer_passed == False):
            # if computer gets another card, return True, and not passed
            is_computer_passed = not take_another_card(comp_total, user_visible_card)
            # computer does not pass
            if (is_computer_passed == False):
                comp_gets = next_card()
                print('{} gets {}'.format(computer_name, comp_gets))
                comp_visible_card += comp_gets
                comp_total = comp_visible_card + comp_hidden_card
                print_status(False, computer_name, comp_hidden_card, comp_visible_card, comp_total)
            # computer passed
            else:
                print('{} passed!'.format(computer_name))

    # both of them passed, game over, and print the winner
    print("-- Game Over --")
    print_winner(username, user_total, computer_name, comp_total)

    return


def main():
    """
    Main Function.
    """
    # print the game instructions
    print_instructions()
    # get and set user's name
    username = input("What's your name?\r\n")
    # set computer's name
    computer_name = "Computer"
    # game starts
    run(username, computer_name)
    # Ask if the user wants to play the game again
    while (ask_yes_or_no("Do you want to play again? (y/n)")):
        username_modify = ask_yes_or_no("Modify username? (y/n)")
        if (username_modify is True):
            username = input("Update your name?\n")
        run(username, computer_name)


if __name__ == '__main__':
    main()
