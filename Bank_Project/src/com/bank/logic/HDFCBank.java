package com.bank.logic;

import java.util.LinkedHashMap;
import java.util.Map;

import com.bank.controller.ATMCardServer;
import com.bank.exceptions.InSufficientFundsException;
import com.bank.exceptions.InvalidAmountException;

public class HDFCBank implements ATMCardServer
{
	private double balance = 50000; //default minimum balance required.
	
	Map<String,Long> customer =new LinkedHashMap<>();
	
	public HDFCBank() 
	{
		System.out.println(" ~~~~~~~~~~~~~~~ WEL-COME to HDFC ATM Services ~~~~~~~~~~~~~~~ ");

		customer.put("Jay Agrawal",9876_5432_1123_4560L);
		customer.put("Fenil Shah",9876_5432_1123_4561L);
		customer.put("Poojan Shah",9876_5432_1123_4562L);
		customer.put("Saurav Rathava",9876_5432_1123_4563L);
		customer.put("Pradeep Patel",9876_5432_1123_4564L);
		customer.put("Shivani Kaniya",9876_5432_1123_4565L);
		customer.put("Anand Patel",9876_5432_1123_4566L);
		customer.put("Subham Kushwah",9876_5432_1123_4567L);
	}
	public boolean checkCustomerData(long cardNumber) 
	{
		if (customer.containsValue(cardNumber)) 
		{
			return true;
		}
		return false;
	}
	@Override
	public void deposite(double amount) throws InvalidAmountException 
	{
		if(amount<=0) {
			throw new InvalidAmountException(amount+" is invalid Amount to Deposit.");
		}
		balance = balance+amount;
	}
	@Override
	public double withdraw(double amount) throws InvalidAmountException, InSufficientFundsException 
	{
		if(amount<=0) 
		{
			throw new InvalidAmountException(amount+" is invalid Amount to Withdraw.");
		}
		if (balance<amount) 
		{
			throw new InSufficientFundsException("InSufficient Funds to Withdraw.");
		}
		balance = balance-amount;
		return amount;
	}
	@Override
	public double balanceEnquiry() 
	{
		return balance;
	}	
}
