package com.developer.rest.RestBankAcc;

import java.math.BigDecimal;
import java.util.Map;

public interface Dao {
	public BigDecimal getFunds(String name);

	public String setFunds(String name, BigDecimal ammount, Operation oper);

	public Map<String, Account> getAccounts();
}
