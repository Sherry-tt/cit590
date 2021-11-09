import unittest

from bank_accounts import *

class BankAccounts_Tests(unittest.TestCase):

    # runs before each function starting with 'test_'
    def setUp(self):

        #attach test data to self
        self.bank_accounts = init_bank_accounts('accounts.txt', 'deposits.csv', 'withdrawals.csv')

    def test_init_bank_accounts(self):
        # get balance of existing accounts


        # 6685.99 - 128.4 = 6557.59
        self.assertAlmostEqual(6557.59, self.bank_accounts['1']['balance'])

        # TODO Write at least 2 additional test cases
        # Consider balances greater than 0
        self.assertAlmostEqual(4716.89, self.bank_accounts['2']['balance'])
        # Consider 0 balances
        self.assertAlmostEqual(0, self.bank_accounts['10']['balance'])


    def test_get_account_info(self):
        # get account info from existing accounts
        account_info = get_account_info(self.bank_accounts, '1')
        self.assertAlmostEqual(6557.59, account_info['balance'])
        self.assertEqual('Brandon', account_info['first_name'])
        self.assertEqual('Krakowsky', account_info['last_name'])

        account_info = get_account_info(self.bank_accounts, '2')
        self.assertAlmostEqual(4716.89, account_info['balance'])

        account_info = get_account_info(self.bank_accounts, '7')
        self.assertEqual(0.0, account_info['balance'])
        self.assertEqual('Huize', account_info['first_name'])
        self.assertEqual('Huang', account_info['last_name'])

        # TODO Write at least 2 additional test cases
        # Consider other existing accounts
        account_info = get_account_info(self.bank_accounts, '6')
        self.assertAlmostEqual(6700.19, account_info['balance'])
        self.assertEqual('Karishma', account_info['first_name'])
        self.assertEqual('Jain', account_info['last_name'])

        # Consider non-existent accounts
        self.assertEqual(None, get_account_info(self.bank_accounts, '11'))


    def test_withdraw(self):
        # withdraw from existing accounts
        self.assertAlmostEqual(6700.19, get_account_info(self.bank_accounts, '6')['balance'])
        withdraw(self.bank_accounts, '6', .19)
        # 6700.19 - .19 = 6700.00
        self.assertAlmostEqual(6700.00, get_account_info(self.bank_accounts, '6')['balance'])

        self.assertAlmostEqual(651.44, get_account_info(self.bank_accounts, '9')['balance'])
        withdraw(self.bank_accounts, '9', 651)
        # 651.44 - 651 = .44
        self.assertAlmostEqual(.44, get_account_info(self.bank_accounts, '9')['balance'])

        # TODO Write at least 2 additional test cases
        # Consider withdrawing too much
        self.assertAlmostEqual(0, get_account_info(self.bank_accounts, '10')['balance'])
        self.assertRaises(RuntimeError, withdraw, self.bank_accounts, '10', 1)

        # Consider withdrawing from non-existent accounts
        self.assertEqual(None, withdraw(self.bank_accounts, '11', 651))


    def test_deposit(self):
        # deposit to existing accounts
        self.assertAlmostEqual(6700.19, get_account_info(self.bank_accounts, '6')['balance'])
        deposit(self.bank_accounts, '6', 1)
        # 6700.19 + 1 = 6701.19
        self.assertAlmostEqual(6701.19, get_account_info(self.bank_accounts, '6')['balance'])

        self.assertAlmostEqual(651.44, get_account_info(self.bank_accounts, '9')['balance'])
        deposit(self.bank_accounts, '9', 1000.01)
        # 651.44 + 1000.01 = 1651.45
        self.assertAlmostEqual(1651.45, get_account_info(self.bank_accounts, '9')['balance'])

        # TODO Write at least 2 additional test cases
        # Consider depositing 0 amounts
        self.assertAlmostEqual(0, get_account_info(self.bank_accounts, '10')['balance'])
        deposit(self.bank_accounts, '10', 0)
        self.assertAlmostEqual(0, get_account_info(self.bank_accounts, '10')['balance'])

        # Consider depositing to non-existent account
        self.assertEqual(None, deposit(self.bank_accounts, '11', 0))


    def test_purchase(self):
        # purchase from existing accounts
        self.assertAlmostEqual(4716.89, get_account_info(self.bank_accounts, '2')['balance'])
        purchase(self.bank_accounts, '2', [3.2, 4.89, 32.9])
        # 4716.89 - (40.99 + 2.46) = 4673.44
        self.assertAlmostEqual(4673.44, get_account_info(self.bank_accounts, '2')['balance'], 2)

        self.assertAlmostEqual(6557.59, get_account_info(self.bank_accounts, '1')['balance'])
        # 6557.59 - (6557.59 + 393.46) = RuntimeError
        self.assertRaises(RuntimeError, purchase, self.bank_accounts, '1', [6557, .59])

        # TODO Write at least 2 additional test cases
        # Consider purchasing too much (more than available)
        self.assertAlmostEqual(0, get_account_info(self.bank_accounts, '10')['balance'])
        # 0 - (1 + 2) = RuntimeError
        self.assertRaises(RuntimeError, purchase, self.bank_accounts, '10', [1, 2])

        # Consider purchasing from non-existent account
        self.assertEqual(None, deposit(self.bank_accounts, '11', 0))



    def test_sort_accounts(self):

        sorted_accounts = sort_accounts(self.bank_accounts, 'balance', 'desc')
        self.assertEqual(('6', {'first_name': 'Karishma', 'last_name': 'Jain', 'balance': 6700.19}), sorted_accounts[0])
        self.assertEqual(('1', {'first_name': 'Brandon', 'last_name': 'Krakowsky', 'balance': 6557.59}), sorted_accounts[1])
        self.assertEqual(('2', {'first_name': 'Chenyun', 'last_name': 'Wei', 'balance': 4716.89}), sorted_accounts[2])

        # TODO Write at least 2 additional test cases

        # Consider sorting on last name in asc order
        sorted_accounts = sort_accounts(self.bank_accounts, 'last_name', 'asc')
        self.assertEqual(('4', {'first_name': 'Zhe', 'last_name': 'Cai', 'balance': 114.31}), sorted_accounts[0])
        self.assertEqual(('7', {'first_name': 'Huize', 'last_name': 'Huang', 'balance': 0}), sorted_accounts[2])
        self.assertEqual(('6', {'first_name': 'Karishma', 'last_name': 'Jain', 'balance': 6700.19}), sorted_accounts[3])


        # Consider sorting on account number in asc order
        sorted_accounts = sort_accounts(self.bank_accounts, 'account_number', 'asc')
        self.assertEqual(('3', {'first_name': 'Dingyi', 'last_name': 'Shen', 'balance': 4.14}), sorted_accounts[2])
        self.assertEqual(('5', {'first_name': 'Chuanrui', 'last_name': 'Zhu', 'balance': 12.49}), sorted_accounts[4])
        self.assertEqual(('6', {'first_name': 'Karishma', 'last_name': 'Jain', 'balance': 6700.19}), sorted_accounts[5])


    def test_export_statement(self):

        export_statement(self.bank_accounts, '3', '3.txt')
        export_statement(self.bank_accounts, '4', '4.txt')
        export_statement(self.bank_accounts, '5', '5.txt')

        # check file exports for correct formatting of the info



if __name__ == '__main__':
    unittest.main()