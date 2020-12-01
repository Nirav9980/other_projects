package com.bank.logic;

import java.util.LinkedHashMap;
import java.util.Map;

import com.bank.controller.ATMCardServer;
import com.bank.exceptions.InSufficientFundsException;
import com.bank.exceptions.InvalidAmountException;

public class SBIBank implements ATMCardServer
{
	private double balance = 20000; //default minimum balance required.
	
	Map<String,Long> customer =new LinkedHashMap<>();
	
	public SBIBank() 
	{
		System.out.println(" ~~~~~~~~~~~~~~~ WEL-COME to State Bank Of India(SBI) ATM Services ~~~~~~~~~~~~~~~ ");
		customer.put("Krishna Rathore",6876_5432_1123_4560L);
		customer.put("Anmol Patel",6876_5432_1123_4561L);
		customer.put("Raj Rajput",6876_5432_1123_4562L);
		customer.put("Hari Rathod",6876_5432_1123_4563L);
		customer.put("Govind Patel",6876_5432_1123_4564L);
		customer.put("Shivani Shah",6876_5432_1123_4565L);
		customer.put("Anandi Thakkar",6876_5432_1123_4566L);
		customer.put("Subham Panchal",6876_5432_1123_4567L);
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
		if(amount<=0) 
		{
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
