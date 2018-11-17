import requests, json
from Exceptions import *

def valid_response_verify(r, expected_http_code):
	if not r.status_code == expected_http_code:
		content = json.loads(r.content.decode('utf-8'))
		if 'error' in content:
			raise HTTP_ERROR('Error in call : %s' % content)

def verify_error_type(r, error_type):
	content = json.loads(r.content.decode('utf-8'))
	if content['error'] != error_type:
		raise Exception('%s error not thrown.' % error_type)

def test_postAccountValid(investor_id, credits):
	headers = {
	    'Content-Type': 'application/json',
	    'cache-control': 'no-cache',
	    'Postman-Token': 'ed1fb611-ad99-4b2f-8b55-6deadcbe6f8d',
	    'User-Agent': 'PostmanRuntime/7.3.0',
	    'Accept': '*/*',
	    'Host': 'localhost:8181',
	    'accept-encoding': 'gzip, deflate',
	    'content-length': '101',
	}

	data = '{\n  "investorId":"%s",\n  "investorName": "tom Drouin",\n  "email":"bob@gmail.com",\n  "credits": %s\n}' % (investor_id, credits)

	r = requests.post('http://localhost:8181/accounts', headers=headers, data=data)
	valid_response_verify(r, 201)
	return r

def test_postAccountValidAlreadyOpen(investor_id):
	headers = {
	    'Content-Type': 'application/json',
	    'cache-control': 'no-cache',
	    'Postman-Token': 'ed1fb611-ad99-4b2f-8b55-6deadcbe6f8d',
	    'User-Agent': 'PostmanRuntime/7.3.0',
	    'Accept': '*/*',
	    'Host': 'localhost:8181',
	    'accept-encoding': 'gzip, deflate',
	    'content-length': '101',
	}

	data = '{\n  "investorId":"%s",\n  "investorName": "tom Drouin",\n  "email":"bob@gmail.com",\n  "credits": 50000\n}' % investor_id

	r = requests.post('http://localhost:8181/accounts', headers=headers, data=data)
	verify_error_type(r, "ACCOUNT_ALREADY_OPEN")
	return r

def test_postAccountInvalidCredits(investor_id):
	headers = {
	    'Content-Type': 'application/json',
	    'cache-control': 'no-cache',
	    'Postman-Token': 'a7f018a5-efdd-42b6-9e99-3681d4c6ee51',
	    'User-Agent': 'PostmanRuntime/7.3.0',
	    'Accept': '*/*',
	    'Host': 'localhost:8181',
	    'accept-encoding': 'gzip, deflate',
	    'content-length': '96',
	}

	data = '{\n  "investorId": %s,\n  "investorName": "Tommy Drouin",\n  "email":"tom@tom.com",\n  "credits": 0\n}' % investor_id

	r = requests.post('http://localhost:8181/accounts', headers=headers, data=data)
	verify_error_type(r, "INVALID_AMOUNT")

def test_getAccountsByInvestorId(url):
	headers = {
	    'cache-control': 'no-cache',
	    'Postman-Token': '761f1ae9-2c53-4120-bdca-20b2a64e6186',
	    'User-Agent': 'PostmanRuntime/7.3.0',
	    'Accept': '*/*',
	    'Host': 'localhost:8181',
	    'accept-encoding': 'gzip, deflate',
	}

	r = requests.get(url, headers=headers)
	valid_response_verify(r, 200)

def test_postTransactionBuyValid(url_account):
	headers = {
	    'Content-Type': 'application/json',
	    'cache-control': 'no-cache',
	    'Postman-Token': '78d96a48-86f9-4731-9c67-aa0fbc989266',
	    'User-Agent': 'PostmanRuntime/7.3.0',
	    'Accept': '*/*',
	    'Host': 'localhost:8181',
	    'accept-encoding': 'gzip, deflate',
	    'content-length': '137',
	}

	data = '{\n  "type": "BUY",\n  "date": "2015-01-01T05:00:00.142Z",\n  "stock": {\n    "market": "NASDAQ",\n    "symbol": "MSFT"\n  },\n  "quantity": 4\n}'

	r = requests.post('%s/transactions' % url_account, headers=headers, data=data)
	valid_response_verify(r, 201)

def test_postTransactionBuyInvalidType(url_account):
	headers = {
	    'Content-Type': 'application/json',
	    'cache-control': 'no-cache',
	    'Postman-Token': 'ee45b956-7ca1-4bc6-8940-f205516e7d1b',
	    'User-Agent': 'PostmanRuntime/7.3.0',
	    'Accept': '*/*',
	    'Host': 'localhost:8181',
	    'accept-encoding': 'gzip, deflate',
	    'content-length': '139',
	}
	data = '{\n  "type": "PROUT",\n  "date": "2015-01-01T05:00:00.000Z",\n  "stock": {\n    "market": "NASDAQ",\n    "symbol": "MSFT"\n  },\n  "quantity": 4\n}'
	r = requests.post('%s/transactions' % url_account, headers=headers, data=data)
	verify_error_type(r, "UNSUPPORTED_TRANSACTION_TYPE")

def test_postTransactionBuyNotEnoughCredit(url_account):
	headers = {
	    'Content-Type': 'application/json',
	    'cache-control': 'no-cache',
	    'Postman-Token': 'd171b563-7229-4964-bbe2-d61c5a773e97',
	    'User-Agent': 'PostmanRuntime/7.3.0',
	    'Accept': '*/*',
	    'Host': 'localhost:8181',
	    'accept-encoding': 'gzip, deflate',
	    'content-length': '141',
	}

	data = '{\n  "type": "BUY",\n  "date": "2015-01-01T05:00:00.000Z",\n  "stock": {\n    "market": "NASDAQ",\n    "symbol": "MSFT"\n  },\n  "quantity": 40000\n}'

	r = requests.post('%s/transactions' % url_account, headers=headers, data=data)
	verify_error_type(r, "NOT_ENOUGH_CREDITS")

def test_postTransactionBuyInvalidQuantity(url_account):
	headers = {
	    'Content-Type': 'application/json',
	    'cache-control': 'no-cache',
	    'Postman-Token': '016d5d13-4a01-4404-9690-60fee6cd2e8b',
	    'User-Agent': 'PostmanRuntime/7.3.0',
	    'Accept': '*/*',
	    'Host': 'localhost:8181',
	    'accept-encoding': 'gzip, deflate',
	    'content-length': '137',
	}

	data = '{\n  "type": "BUY",\n  "date": "2015-01-01T05:00:00.000Z",\n  "stock": {\n    "market": "NASDAQ",\n    "symbol": "MSFT"\n  },\n  "quantity": 0\n}'

	r = requests.post('%s/transactions' % url_account, headers=headers, data=data)
	verify_error_type(r, "INVALID_QUANTITY")

def test_postTransactionBuyInvalidStock(url_account):
	headers = {
	    'Content-Type': 'application/json',
	    'cache-control': 'no-cache',
	    'Postman-Token': '736f1908-c967-478b-8bc3-3e68481d183c',
	    'User-Agent': 'PostmanRuntime/7.3.0',
	    'Accept': '*/*',
	    'Host': 'localhost:8181',
	    'accept-encoding': 'gzip, deflate',
	    'content-length': '138',
	}

	data = '{\n  "type": "BUY",\n  "date": "2015-01-01T05:00:00.000Z",\n  "stock": {\n    "market": "NASDAQG",\n    "symbol": "MSFT"\n  },\n  "quantity": 2\n}'

	r = requests.post('%s/transactions' % url_account, headers=headers, data=data)
	verify_error_type(r, "STOCK_NOT_FOUND")

def test_postTransactionBuyInvalidDate(url_account):
	headers = {
	    'Content-Type': 'application/json',
	    'cache-control': 'no-cache',
	    'Postman-Token': '64c0e81f-1b3d-4b4b-a770-91e233fb3861',
	    'User-Agent': 'PostmanRuntime/7.3.0',
	    'Accept': '*/*',
	    'Host': 'localhost:8181',
	    'accept-encoding': 'gzip, deflate',
	    'content-length': '138',
	}

	data = '{\n  "type": "BUY",\n  "date": "2014-01-01T05:00:00.000Z",\n  "stock": {\n    "market": "NASDAQ",\n    "symbol": "MSFT"\n  },\n  "quantity": 2\n}'

	r = requests.post('%s/transactions' % url_account, headers=headers, data=data)
	verify_error_type(r, "INVALID_DATE")

def test_postReportValid(url_account):
	headers = {
	    'Content-Type': 'application/json',
	    'cache-control': 'no-cache',
	    'Postman-Token': '4adb7766-1945-42e5-b426-90b130e55e67',
	    'User-Agent': 'PostmanRuntime/7.3.0',
	    'Accept': '*/*',
	    'Host': 'localhost:8181',
	    'accept-encoding': 'gzip, deflate',
	    'content-length': '137',
	}

	data = '{\n  "type": "BUY",\n  "date": "2015-01-01T05:00:00.142Z",\n  "stock": {\n    "market": "NASDAQ",\n    "symbol": "MSFT"\n  },\n  "quantity": 4\n}'
	r = requests.post('%s/transactions' % url_account, headers=headers, data=data)
	valid_response_verify(r, 201)
	return r

def test_getTransactionBuy(transaction_url):
	headers = {
	    'cache-control': 'no-cache',
	    'Postman-Token': 'd728b2fb-ccbd-4b89-892e-70e018d6b30d',
	    'User-Agent': 'PostmanRuntime/7.3.0',
	    'Accept': '*/*',
	    'Host': 'localhost:8181',
	    'accept-encoding': 'gzip, deflate',
	}

	r = requests.get(transaction_url, headers=headers)
	valid_response_verify(r, 200)
	return json.loads(r.content.decode('utf-8'))

def test_postTransactionSellValid(url_account, transaction_number):
	headers = {
	    'Content-Type': 'application/json',
	    'cache-control': 'no-cache',
	    'Postman-Token': '5bfdf41e-29b6-4864-9d88-cc4a78a609ee',
	    'User-Agent': 'PostmanRuntime/7.3.0',
	    'Accept': '*/*',
	    'Host': 'localhost:8181',
	    'accept-encoding': 'gzip, deflate',
	    'content-length': '201',
	}

	data = '{\n  "type": "SELL",\n  "date": "2015-01-01T05:00:00.000Z",\n  "stock": {\n    "market": "NASDAQ",\n    "symbol": "MSFT"\n  },\n  "transactionNumber": "%s",\n  "quantity": 1\n}' % transaction_number

	r = requests.post('%s/transactions/' % url_account, headers=headers, data=data)
	valid_response_verify(r, 201)
	return r

def test_postTransactionSellInvalidTransactionNumber(url_account):
	headers = {
	    'Content-Type': 'application/json',
	    'cache-control': 'no-cache',
	    'Postman-Token': '6b34bc7f-8e6b-4085-acd1-d1250a616211',
	    'User-Agent': 'PostmanRuntime/7.3.0',
	    'Accept': '*/*',
	    'Host': 'localhost:8181',
	    'accept-encoding': 'gzip, deflate',
	    'content-length': '201',
	}

	data = '{\n  "type": "SELL",\n  "date": "2015-01-01T05:00:00.000Z",\n  "stock": {\n    "market": "NASDAQ",\n    "symbol": "MSFT"\n  },\n  "transactionNumber": "65fff940-2163-43de-b458-9d0629342570",\n  "quantity": 1\n}'

	r = requests.post('%s/transactions/' % url_account, headers=headers, data=data)
	verify_error_type(r, "INVALID_TRANSACTION_NUMBER")

def test_postTransactionSellInvalidNotEnoughBuy(url_account, transaction_number):
	headers = {
	    'Content-Type': 'application/json',
	    'cache-control': 'no-cache',
	    'Postman-Token': 'c8e6cdb6-9f54-4b7c-9fbc-44db148de25a',
	    'User-Agent': 'PostmanRuntime/7.3.0',
	    'Accept': '*/*',
	    'Host': 'localhost:8181',
	    'accept-encoding': 'gzip, deflate',
	    'content-length': '202',
	}

	data = '{\n  "type": "SELL",\n  "date": "2015-01-01T05:00:00.000Z",\n  "stock": {\n    "market": "NASDAQ",\n    "symbol": "MSFT"\n  },\n  "transactionNumber": "%s",\n  "quantity": 10\n}' % transaction_number

	r = requests.post('%s/transactions/' % url_account, headers=headers, data=data)
	verify_error_type(r, "NOT_ENOUGH_STOCK")

def test_postTransactionSellInvalidNotEnoughCredits(url_account, transaction_number):
	headers = {
	    'Content-Type': 'application/json',
	    'cache-control': 'no-cache',
	    'Postman-Token': 'e427346c-8090-4677-9dbb-a0aed88acb87',
	    'User-Agent': 'PostmanRuntime/7.3.0',
	    'Accept': '*/*',
	    'Host': 'localhost:8181',
	    'accept-encoding': 'gzip, deflate',
	    'content-length': '201',
	}

	data = '{\n  "type": "SELL",\n  "date": "2015-01-01T05:00:00.000Z",\n  "stock": {\n    "market": "NASDAQ",\n    "symbol": "MSFT"\n  },\n  "transactionNumber": "%s",\n  "quantity": 1\n}' % transaction_number

	r = requests.post('%s/transactions/' % url_account, headers=headers, data=data)

def test_getTransactionSell(url_transaction):
	headers = {
	    'cache-control': 'no-cache',
	    'Postman-Token': '79da7fef-5576-4729-bf8a-9be2c2b3bb95',
	    'User-Agent': 'PostmanRuntime/7.3.0',
	    'Accept': '*/*',
	    'Host': 'localhost:8181',
	    'accept-encoding': 'gzip, deflate',
	}

	r = requests.get(url_transaction, headers=headers)
	valid_response_verify(r, 200)
	return json.loads(r.content.decode('utf-8'))

def test_postTransactionSellStockParamNotMatching(url_account, transaction_number):
	rs = {
	    'Content-Type': 'application/json',
	    'cache-control': 'no-cache',
	    'Postman-Token': 'f1e8b606-0f00-48fd-bc0b-412d92c4d845',
	    'User-Agent': 'PostmanRuntime/7.3.0',
	    'Accept': '*/*',
	    'Host': 'localhost:8181',
	    'accept-encoding': 'gzip, deflate',
	    'content-length': '201',
	}

	data = '{\n  "type": "SELL",\n  "date": "2015-01-01T05:00:00.000Z",\n  "stock": {\n    "market": "NASDAQ",\n    "symbol": "GOOG"\n  },\n  "transactionNumber": "%s",\n  "quantity": 1\n}' % transaction_number

	r = requests.post('%s/transactions/' % url_account, headers=headers, data=data)

def test_postTransactionSellInvalidQuantity(url_account, transaction_number):

	headers = {
	    'Content-Type': 'application/json',
	    'cache-control': 'no-cache',
	    'Postman-Token': '2bdb83ba-3351-41fc-a09d-2bd7bca9cec5',
	    'User-Agent': 'PostmanRuntime/7.3.0',
	    'Accept': '*/*',
	    'Host': 'localhost:8181',
	    'accept-encoding': 'gzip, deflate',
	    'content-length': '201',
	}

	data = '{\n  "type": "SELL",\n  "date": "2015-01-01T05:00:00.000Z",\n  "stock": {\n    "market": "NASDAQ",\n    "symbol": "MSFT"\n  },\n  "transactionNumber": "%s",\n  "quantity": 0\n}' % transaction_number

	r = requests.post('%s/transactions/' % url_account, headers=headers, data=data)
	verify_error_type(r, "INVALID_QUANTITY")

def test_postTransactionSellInvalidNotEnoughCreditsForSells_makingAccount(investor_id, credits):
	headers = {
	    'Content-Type': 'application/json',
	    'cache-control': 'no-cache',
	    'Postman-Token': 'ed1fb611-ad99-4b2f-8b55-6deadcbe6f8d',
	    'User-Agent': 'PostmanRuntime/7.3.0',
	    'Accept': '*/*',
	    'Host': 'localhost:8181',
	    'accept-encoding': 'gzip, deflate',
	    'content-length': '101',
	}

	data = '{\n  "investorId":"%s",\n  "investorName": "tom Drouin",\n  "email":"bob@gmail.com",\n  "credits": %s\n}' % (investor_id2, credits)

	r = requests.post('http://localhost:8181/accounts', headers=headers, data=data)
	valid_response_verify(r, 201)
	return r

def test_postTransactionSellInvalidNotEnoughCreditsForSells_makingTRANSACTIONBUY(url_account):
	headers = {
	    'Content-Type': 'application/json',
	    'cache-control': 'no-cache',
	    'Postman-Token': '78d96a48-86f9-4731-9c67-aa0fbc989266',
	    'User-Agent': 'PostmanRuntime/7.3.0',
	    'Accept': '*/*',
	    'Host': 'localhost:8181',
	    'accept-encoding': 'gzip, deflate',
	    'content-length': '137',
	}

	data = '{\n  "type": "BUY",\n  "date": "2015-01-01T05:00:00.142Z",\n  "stock": {\n    "market": "NASDAQ",\n    "symbol": "MSFT"\n  },\n  "quantity": 4\n}'

	r = requests.post('%s/transactions' % url_account, headers=headers, data=data)
	valid_response_verify(r, 201)

	def test_postTransactionSellValid(url_account, transaction_number):
	headers = {
	    'Content-Type': 'application/json',
	    'cache-control': 'no-cache',
	    'Postman-Token': '5bfdf41e-29b6-4864-9d88-cc4a78a609ee',
	    'User-Agent': 'PostmanRuntime/7.3.0',
	    'Accept': '*/*',
	    'Host': 'localhost:8181',
	    'accept-encoding': 'gzip, deflate',
	    'content-length': '201',
	}

	data = '{\n  "type": "SELL",\n  "date": "2015-01-01T05:00:00.000Z",\n  "stock": {\n    "market": "NASDAQ",\n    "symbol": "MSFT"\n  },\n  "transactionNumber": "%s",\n  "quantity": 1\n}' % transaction_number

	r = requests.post('%s/transactions/' % url_account, headers=headers, data=data)
	valid_response_verify(r, 201)
	return r


