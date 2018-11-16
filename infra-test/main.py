from network_calls import *
from random import randint
from Exceptions import *
import time

investor_ids = []
WAIT_SERVER_START_DURATION = 20

def get_investor_id():
	investor_id = None
	while investor_id == None:
		investor_id = randint(0,10000)
		if investor_id in investor_ids:
			investor_id = None
	investor_ids.append(investor_id)
	return investor_id

def main_test():
	print('Waiting %s seconds for server to start...' % WAIT_SERVER_START_DURATION )
	time.sleep(WAIT_SERVER_START_DURATION)
	print('Waiting done.')
	investor_id = get_investor_id()
	account_response = test_postAccountValid(investor_id, 5000)

	test_postAccountValidAlreadyOpen(investor_id)

	new_investor_id = get_investor_id()

	test_postAccountInvalidCredits(new_investor_id)

	url_account = account_response.headers['Location']
	test_getAccountsByInvestorId(url_account)
	test_postTransactionBuyValid(url_account)
	test_postTransactionBuyInvalidType(url_account)
	test_postTransactionBuyNotEnoughCredit(url_account)
	test_postTransactionBuyInvalidQuantity(url_account)
	test_postTransactionBuyInvalidStock(url_account)
	test_postTransactionBuyInvalidDate(url_account)
	report_response = test_postReportValid(url_account)
	transaction_url = report_response.headers['Location']
	data = test_getTransactionBuy(transaction_url)
	transaction_number = data['transactionNumber']
	response_sell = test_postTransactionSellValid(url_account, transaction_number)
	test_postTransactionSellInvalidTransactionNumber(url_account)
	test_postTransactionSellInvalidNotEnoughBuy(url_account, transaction_number)

	# FIXME : Add not enough credits on sell!
	"""no_credit_investor_id = get_investor_id()
	no_credit_account_response = test_postAccountValid(no_credit_investor_id, 1)
	url_nocredit_account = no_credit_account_response.headers['Location']
	print('url_nocredit_account', url_nocredit_account)
	test_postTransactionSellInvalidNotEnoughCredits(url_nocredit_account, transaction_number)"""
	url_transaction_sell = response_sell.headers['Location']
	data = test_getTransactionSell(url_transaction_sell)
	sell_valid_transaction_number = data['transactionNumber']
	#test_postTransactionSellStockParamNotMatching(url_account, transaction_number)
	test_postTransactionSellInvalidQuantity(url_account, transaction_number)
	#response_sell = test_postTransactionSellValid(url_account, sell_valid_transaction_number)

main_test()
