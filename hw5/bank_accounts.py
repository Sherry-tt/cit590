
# sample behavior5

def open_txt_file(name):
    """
    open and read a .txt file
    """
    stream = open(name)
    lines = stream.readlines()

    for i in range(len(lines)):
        lines[i] = lines[i].strip()
        lines[i] = lines[i].split('|')
        line = lines[i];
        for j in range(0,3):
            line[j] = line[j].strip()
        lines[i] = line

    stream.close()
    return lines


def open_csv_file(name):
    """
    open and read a .csv file
    """
    stream = open(name)
    lines = stream.readlines()

    for i in range(len(lines)):
        lines[i] = lines[i].rstrip()

    stream.close()
    return lines


def open_files(name1, name2, name3):
    """
    open 3 files
    """
    file1 = open_txt_file(name1)
    file2 = open_csv_file(name2)
    file3 = open_csv_file(name3)

    return file1, file2, file3


def calculate_sum(records):
    """
    calculate the sum in the records
    """
    sum_money = []
    for line in records:
        line_temp = line.split(',')
        del (line_temp[0])
        sum = 0
        while line_temp:
            line_temp[0] = float(line_temp[0])
            sum += line_temp[0]
            del (line_temp[0])
        sum_money.append(round(sum, 2))
    return sum_money


def calculate_balance(deposits, withdrawals):
    balance = []
    length = len(deposits)
    for i in range(length):
        temp = deposits[i] - withdrawals[i]
        balance.append(round(temp, 2))

    return balance


def account_info(accounts, balance):
    """
    Creat a dictionary to store the information of an account
    eg. {'first_name': 'Brandon', 'last_name': 'Krakowsky', 'balance': 6557.59}
    """
    information = []
    length = len(accounts)
    for i in range(length):
        temp_list = {}
        temp_list['first_name'] = accounts[i][1]
        temp_list['last_name'] = accounts[i][2]
        temp_list['balance'] = balance[i]
        information.append(temp_list)

    return information


def store_information(informarion, length):
    """
    store the information in dictionary
    """
    info = {}
    for i in range(length):
        info[str(i + 1)] = informarion[i]

    return info


def init_bank_accounts(accounts, deposits, withdrawals):
    """
    Loads the given 3 files
    1) accounts .txt
    2) deposits .csv
    3) withdrawals .csv
    stores the information for individual bank accounts in a dictionary,
    and calculates the account balance
    Example:
        {'1': {'first_name': 'Brandon', 'last_name': 'Krakowsky', 'balance': 6557.59}}
    """
    accounts, deposits, withdrawals = open_files(accounts, deposits, withdrawals)

    # sum of deposit
    deposits_sum = calculate_sum(deposits)
    # print
    length = len(deposits_sum)
    for i in range(length):
        print('New balance: ', deposits_sum[i])
    # sum with withdrawals
    withdrawals_sum = calculate_sum(withdrawals)
    # print
    for j in range(length):
        print('New balance: ', round(deposits_sum[j] - withdrawals_sum[j], 2))
    # calculate balance
    balance = calculate_balance(deposits_sum, withdrawals_sum)
    # account information
    dict_inner = account_info(accounts, balance)
    # store the information
    store_info = store_information(dict_inner, len(accounts))

    return store_info


def get_account_info(bank_accounts, account_number):
    """
    Returns the account information for the given account_number as a dictionary
    Example:
        {'first_name': 'Brandon', 'last_name': 'Krakowsky', 'balance': 6557.59}
    If the account doesn't exist, returns None
    """
    if account_number in bank_accounts.keys():
        return bank_accounts[account_number]
    else:
        return None


def round_balance(balance):
    """
    Rounds the number to 2 decimal places)
    """
    return round(balance, 2)


def check_account_exist(bank_accounts, account_number):
    """
    Check whether the account exits
    """
    account_number = str(account_number)
    if account_number not in bank_accounts.keys():
        print("Sorry, that account doesn't exist.")
        return False
    else:
        return True


def withdraw(bank_accounts, account_number, amount):
    """
    Withdraws the given amount from the account with the given account_number
    Rounds the new balance to 2 decimal places (Uses round_balance() function)
    If the account doesn't exist, prints a friendly message
    Raises a Runtime Error if the given amount is greater than the available balance
    Prints the new balance
    """

    try:
        amount = float(amount)
    except:
        print("Invalid withdrawal amount.")
        return
    # if type(amount) == str:
    # if not amount.isnumeric():
    #     #判断是小数的情况
    #     temp = amount.split('.')
    #     if len(temp) > 2 or not temp[0].isnumeric() or not temp[1].isnumeric():
    #         print("Invalid withdrawal amount.")
    #         return
    #
    # amount = float(amount)
    account_number = str(account_number)
    if check_account_exist(bank_accounts, account_number):
        balance_available = bank_accounts[account_number]['balance']
        if balance_available < amount:
            raise RuntimeError('The given amount is greater than the available balance')
        else:
            new_balance = bank_accounts[account_number]['balance'] - amount
            new_balance = round_balance(new_balance)
            bank_accounts[account_number]['balance'] = new_balance
            print('New balance:', new_balance)
        return
    return


def deposit(bank_accounts, account_number, amount):
    """
    Deposits the given amount into the account with the given account_number
    Rounds the new balance to 2 decimal places (Uses round_balance() function)
    If the account doesn't exist, prints a friendly message
    Prints the new balance
    """

    try:
        amount = float(amount)
    except:
        print("Invalid deposit amount.")
        return

    # if not amount.isnumeric():
    #     print("Invalid deposit amount.")
    #     return

    if check_account_exist(bank_accounts, account_number):
        new_balance = bank_accounts[account_number]['balance'] + amount
        new_balance = round_balance(new_balance)
        bank_accounts[account_number]['balance'] = new_balance
        print('New balance:', new_balance)
        return
    return


def calculate_sales_tax(amount):
    """
    Calculates the (6%) sales tax
    """
    return amount * 0.06


def check_purchase_amount(amounts):
    """
    Check is purchase amount is valid
    """
    if type(amounts) == str:
        amounts = amounts.split(',')
    for i in range(len(amounts)):
        try:
            amounts[i] = float(amounts[i])
            # amounts[i] = str(amounts[i])
        except:
            print('Invalid purchase amounts.')
            return amounts, False
        # if type(amounts[i]) != str:
        #     amounts[i] = str(amounts[i])
        # amounts[i] = amounts[i].strip()
        # if not amounts[i].isnumeric():
        #     return False

    return amounts, True


def purchase(bank_accounts, account_number, amounts):
    """
    Makes a purchase with the total of the given amounts from the account with the given account_number
    amounts is a list of floats
    If the account doesn't exist, prints a friendly message
    Calculates the total purchase amount based on the sum of the given amounts, plus (6%) sales tax (Uses calculate_sales_tax() function)
    Raises a Runtime Error if the total purchase amount is greater than the available balance
    Prints the new balance
    """
    # return True if valid
    amounts, if_valid = check_purchase_amount(amounts)
    if not if_valid:
        return

    if check_account_exist(bank_accounts, account_number):
        total = 0

        # if type(amounts) == str:
        #     amounts = amounts.split(',')
        for amount in amounts:
            # total += float(amount)
            total += amount
        total_plus_tax = calculate_sales_tax(total) + total
        # withdraw(bank_accounts, account_number, str(total_plus_tax))
        withdraw(bank_accounts, account_number, total_plus_tax)
        return
    return


def convert_to_tuple(bank_accounts):
    """
    Converts the key:value pairs in the given bank_accounts dictionary to a list of tuples
    """
    list_of_tuple = []
    for key in bank_accounts.keys():
        list_of_tuple.append((key, bank_accounts[key]))

    return list_of_tuple


def sort_accounts_detail(accounts_tuple, sort_type, sort_direction):
    """
    Sorts based on the given sort_type and sort_direction
    """
    # If given sort_type is not an acceptable value, this function does nothing except print a friendly message and return None
    if sort_type not in [ 'account_number', 'first_name', 'last_name', 'balance']:
        print("Unexpected inputs, please input 'account_number', 'first_name', 'last_name', or 'balance'")
        return None

    # If given sort_direction is not an acceptable value (e.g. 'asc’, 'desc'), assume the sort_direction is ‘desc’
    sort_direction = sort_direction.strip()
    if sort_direction not in ['asc', 'desc']:
        sort_direction = 'desc'

    if sort_type == 'account_number':
        accounts_tuple = sorted(accounts_tuple, key=lambda x: int(x[0]), reverse = sort_direction == 'desc')
    # based on 'balance'
    elif sort_type == 'balance':
        accounts_tuple = sorted(accounts_tuple, key=lambda x: x[1]['balance'], reverse=sort_direction == 'desc')
    elif sort_type == 'first_name':
        accounts_tuple = sorted(accounts_tuple, key=lambda x: x[1]['first_name'], reverse=sort_direction == 'desc')
    elif sort_type == 'last_name':
        accounts_tuple = sorted(accounts_tuple, key=lambda x: x[1]['last_name'], reverse=sort_direction == 'desc')

    return accounts_tuple


def sort_accounts(bank_accounts, sort_type, sort_direction):
    """
    1. Converts the key:value pairs in the given bank_accounts dictionary to a list of tuples and sorts based on the given sort_type and sort_direction
    2. Returns the sorted list of tuples
    3. If the sort_type argument is the string ‘account_number’, sorts the list of tuples based on the account number (e.g. ‘3’, '5') in the given sort_direction (e.g. 'asc', 'desc')
    """
    accounts_tuple = convert_to_tuple(bank_accounts)
    sorted_account = sort_accounts_detail(accounts_tuple, sort_type, sort_direction)
    if sorted_account is not None:
        print(sorted_account)
    else:
        print('None')
    return sorted_account


def export_statement(bank_accounts, account_number, output_file):
    """
    Exports the given account information to the given output file in the following format:
    First Name: Huize
    Last Name: Huang
    Balance: 34.57

    If the account doesn’t exist, print a friendly message and do nothing
    """
    if check_account_exist(bank_accounts, account_number):

    # ls_export = []
    # ls_export.append('First Name' + bank_accounts[account_number]['First Name'])
    # ls_export.append('Last Name' + bank_accounts[account_number]['Last Name'])
    # ls_export.append('Balance' + bank_accounts[account_number]['Balance'])

        new_ls = "\n".join(f'{k.capitalize()}: {v}' for k, v in bank_accounts[account_number].items())

        file_name = account_number + '.txt'
        fout = open(file_name, "w")
        fout.writelines(new_ls)
        fout.close()

    return


def welcome_message():
    """
    Prints welcome message and input options for the user
    """
    print("\nWelcome to the bank!  What would you like to do?")
    print("1: Get account info\n",
          "2: Make a deposit\n",
          "3: Make a withdrawal\n",
          "4: Make a purchase\n",
          "5: Sort accounts\n",
          "6: Export a statement\n",
          "0: Leave the bank\n", sep='')


def user_input():
    """
    Prompts for user input and tries to cast to an int
    If the input is invalid, prints a friendly message and re-prints the options above for the user
    """
    user_ = input()
    while not user_.isnumeric() or int(user_) < 0 or int(user_) > 6:
        print('Sorry, the input is invalid.')
        welcome_message()
        user_ = input()

    user_ = int(user_)

    return user_


def ask_account_number():
    """
    Prompts for account number
    """
    return input('Account number? ')


def ask_purchase_amounts():
    """
    Ask for purchase amount
    """
    return input('Amounts (as comma separated list)? ')


def parse_user_input(bank_accounts, num):
    """
    Parse user's input
    """
    # get account info
    if num == 1:
        account_inf = get_account_info(bank_accounts, ask_account_number())
        if account_inf is None:
            print('None')
        else:
            print(account_inf)

    # make a deposit
    elif num == 2:
        deposit(bank_accounts, ask_account_number(), input('Amount? '))

    # make a withdrawal
    elif num == 3:
        withdraw(bank_accounts, ask_account_number(), input('Amount? '))

    # make a purchase
    elif num == 4:
        purchase(bank_accounts, ask_account_number(), ask_purchase_amounts())

    # sort
    elif num == 5:
        sorted_accounts = sort_accounts(bank_accounts, input("Sort type ('account_number', 'first_name', 'last_name', or 'balance')? ")
                                       , input("Sort type ('asc' or 'desc')? "))

    # export a .txt file
    elif num == 6:
        export_statement(bank_accounts, ask_account_number(), 'output.txt')

    elif num == 0:
        # Prints a friendly message and leaves the banking program
        print("Goodbye!")
        return False

    return True


def main():
    # accounts, deposits, withdrawals = open_files('accounts.txt', 'deposits.csv', 'withdrawals.csv')

    # bank_accounts = init_bank_accounts(accounts, deposits, withdrawals)
    bank_accounts = init_bank_accounts('accounts.txt', 'deposits.csv', 'withdrawals.csv')

    while True:
        welcome_message()
        user_choice = user_input()
        stop_or_not = parse_user_input(bank_accounts, user_choice)

        if not stop_or_not:
            break


if __name__ == "__main__":
    main()
