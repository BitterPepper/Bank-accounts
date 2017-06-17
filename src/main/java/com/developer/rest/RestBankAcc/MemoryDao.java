package com.developer.rest.RestBankAcc;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public enum MemoryDao implements Dao {
	instance;

	private Map<String, Account> accounts = new TreeMap<String, Account>();

	private MemoryDao() {
	}

	public BigDecimal getFunds(String name) {
		return accounts.get(name).getBalance();
	}

	public String setFunds(String name, double ammount, Operation oper) {
		BigDecimal opAmmount = new BigDecimal(ammount);
		Account account = accounts.get(name);
		BigDecimal tmpValue = account == null? new BigDecimal(0) : account.getBalance();
		BigDecimal resValue;
		if (oper.equals(Operation.SUBSTRACT)) {
			if (tmpValue.compareTo(opAmmount) < 0) {
				return "Not enough founds on the account: " + name + ". "
						+ "Required: " + ammount + ", account balance: "+ tmpValue.doubleValue();
			}
			resValue = tmpValue.subtract(opAmmount);
		} else {
			resValue = tmpValue.add(opAmmount);
		}
		if (account == null){
			account = new Account(name, resValue);
		} else {
			account.setBalance(resValue);
		}
		accounts.put(name, account);
		return "";
	}

	public Map<String, Account> getAccounts() {
		return accounts;
	}
}
