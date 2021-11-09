'''
Name: Rui Tan
PennID: 34283415
Statement of work: I worked alone without help.

'''

import random

# unit price of a lottery ticket
constant_lottery_unit_price = 2

# unit price of an apple
constant_apple_unit_price = .99

# unit price of a can of beans
constant_canned_beans_unit_price = 1.58

# unit price of a soda
constant_soda_unit_price = 1.23

# the user has initial $5 for shopping
money = 5

# the user has spent $0 initially
money_spent = 0

# the amounts of apples, cans of beans, and sodas the user has purchased
apple_amount, canned_beans_amount, soda_amount = 0, 0, 0
# the amounts of lottery tickets
lottery_amount = 0
# Lottery winnings
winnings = 0

# welcome message
print('Welcome to the supermarket!  Here\'s what we have in stock:')

# list of products
print(' - Lottery tickets cost $'+ str(constant_lottery_unit_price), 'each',
    '\n','- Apples cost $' + str(constant_apple_unit_price),'each',
    '\n','- Cans of beans cost $' + str(constant_canned_beans_unit_price),'each'
    '\n','- Sodas cost $' + str(constant_soda_unit_price),'each')

# money they have available
print('\nYou have $' + str(money) + ' available')
# ask if they want to purchase a lottery ticket
lottery_chance = input('First, do you want to buy a $2 lottery ticket for a chance at winning $2-$10? (y/n)')
# purchase lottery ticket
if (lottery_chance == 'y' or lottery_chance == 'Y'):
    lottery_amount = 1
    money -= 2
    win_lose = random.randint(0, 2)
    # the user wins
    if (win_lose == 1):
        winnings = random.randint(2, 10)
        money += winnings
        print("Congrats! You won $" + str(winnings))
    #the user loses
    else:
        print('Sorry! You did not win the lottery.')
# do not purchase lottery ticket
else:
    print('No lottery tickets were purchased')


# money they have available
print('\nYou have $' + str(money) + ' available')
# ask if they want to purchase apples.
apple_purchase = input('Do you want to buy apple(s)? (y/n)')
# purchase apples
if (apple_purchase == 'y' or apple_purchase == 'Y'):
    apple_amount = input('How many apple(s) do you want to buy?')


    try:
        apple_amount = int(apple_amount)
    except ValueError as e:
        print('Numerical values only! No apples selected.')
    else:
        if (apple_amount < 0):
            apple_amount = 0
            print('Something is wrong! No apples purchased.')
        # valid input
        else:
            money_spent = apple_amount * constant_apple_unit_price
            print('The user wants to buy {} apple(s). This will cost {}.'.format(apple_amount, round(money_spent,2)))
            # enough money
            if (money > money_spent):
                print('The user has enough money. {} apple(s) purchased.'.format(apple_amount))
                money -= money_spent
            # money not enough
            else:
                apple_amount = 0
                print('Not enough money! No apples purchased.')
# do not purchase apples
else:
    print('No apples purchased.')


# money they have available
print('\nYou have $' + str(round(money, 2)) + ' available')
# ask if they want to purchase can(s) of beans.
bean_purchase = input('Do you want to buy can(s) of beans? (y/n)?')
# purchase can(s) of beans
if (bean_purchase == 'y' or bean_purchase == 'Y'):
    canned_beans_amount = input('How many can(s) of beans do you want to buy?')
    try:
        canned_beans_amount = int(canned_beans_amount)
    except ValueError as e:
        print('Numerical values only! No cans of beans selected.')
    else:
        if (canned_beans_amount < 0):
            canned_beans_amount = 0
            print('Something is wrong! No cans of beans purchased.')
        # valid input
        else:
            money_spent = canned_beans_amount * constant_canned_beans_unit_price
            print('The user wants to buy {} can(s) of beans. This will cost {}.'.format(canned_beans_amount, round(money_spent, 2)))
            # enough money
            if (money > money_spent):
                print('The user has enough money. {} can(s) of beans purchased.'.format(canned_beans_amount))
                money -= money_spent
            # money not enough
            else:
                canned_beans_amount = 0
                print('Not enough money! No cans of beans purchased.')
# do not purchase can(s) of beans
else:
    print('No cans of beans purchased.')


# money they have available
print('\nYou have $' + str(round(money, 2)) + ' available')
# ask if they want to purchase sodas.
soda_purchase = input('Do you want to buy soda(s)? (y/n)?')
# purchase sodas
if (soda_purchase == 'y' or soda_purchase == 'Y'):
    soda_amount = input('How many soda(s) do you want to buy?')
    try:
        soda_amount = int(soda_amount)
    except ValueError as e:
        print('Numerical values only! No soda(s) selected.')
    else:
        if (soda_amount < 0):
            soda_amount = 0
            print('Something is wrong! No sodas purchased.')
        # valid input
        else:
            money_spent = soda_amount * constant_soda_unit_price
            print('The user wants to buy {} soda(s). This will cost {}.'.format(soda_amount, round(money_spent, 2)))
            # enough money
            if (money > money_spent):
                print('The user has enough money. {} soda(s) purchased.'.format(soda_amount))
                money -= money_spent
            # money not enough
            else:
                soda_amount = 0
                print('Not enough money! No sodas purchased.')
# do not purchase sodas
else:
    print('No soda purchased.')

# finished and print
print('\nMoney left: ${}'.format(round(money, 2)))
print('Lottery ticket(s) purchased: {}'.format(lottery_amount))
print('Lottery winnings: ${}'.format(winnings))
print('Apple(s) purchased: {}'.format(apple_amount))
print('Can(s) of beans purchased: {}'.format(canned_beans_amount))
print('Soda(s) purchased: {}'.format(soda_amount))
print('Good Bye!')
