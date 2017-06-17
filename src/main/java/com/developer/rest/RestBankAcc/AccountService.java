package com.developer.rest.RestBankAcc;

import java.util.Map;
import java.util.Map.Entry;

public class AccountService {

	public static String performOperation(String nameFrom, String nameTo, double amount, String oper){
		String errors = AccountService.checkParameters(nameFrom, nameTo, amount, oper);
		if (errors.length() == 0){
			if (oper.equals("put")){
				AccountService.putFunds(nameFrom, amount);
			} else if (oper.equals("transfer")){
				errors = AccountService.transferFunds(nameFrom, nameTo,  amount);
			} else {
				errors = AccountService.withdrawFunds(nameFrom, amount);
			}
	    }
		 return errors;
	}

	private static String checkParameters(String nameFrom, String nameTo, double amount, String oper) {
		StringBuilder errors = new StringBuilder();
		if (nameFrom == null) {
			errors.append("Not set name of client from! ");
		} else {
			nameFrom = nameFrom.trim();
			if (nameFrom.isEmpty()) {
				errors.append("name of client from is empty! ");
			}
		}
		if (oper.equals("transfer")) {
			if (nameTo == null || nameTo.isEmpty()) {
				errors.append("Not set name of client to! ");
			} else {
				nameTo = nameTo.trim();
				if (nameTo.isEmpty()) {
					errors.append("name of client to is empty!");
				}
			}
			if (amount <= 0) {
				errors.append("Not set ammount");
			}
		}
		return errors.toString();
	}

	private static synchronized void putFunds(String nameFrom, Double ammount) {
		Dao memoryDao = MemoryDao.instance;
		memoryDao.setFunds(nameFrom, ammount, Operation.ADD);
	}

	private static synchronized String withdrawFunds(String nameFrom, Double ammount) {
		Dao memoryDao = MemoryDao.instance;
		return memoryDao.setFunds(nameFrom, ammount, Operation.SUBSTRACT);
	}

	private static synchronized String transferFunds(String nameFrom, String nameTo, Double ammount) {
		Dao memoryDao = MemoryDao.instance;
		String error = memoryDao.setFunds(nameFrom, ammount, Operation.SUBSTRACT);
		if (error == null) {
			return error;
		}
		memoryDao.setFunds(nameTo, ammount, Operation.ADD);
		return "";
	}

	public static String getAccounts() {
		Dao memoryDao = MemoryDao.instance;
		StringBuilder result = new StringBuilder();
		Map<String, Account> accounts = memoryDao.getAccounts();
		for (Entry<String, Account> account : accounts.entrySet()){
			result.append(account.getKey()).append(" : ").append(account.getValue().getBalance())
			.append('\n');
		}
		return result.toString();
	}
}
