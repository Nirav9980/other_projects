package com.bank.logic;

import java.util.LinkedHashMap;
import java.util.Map;

import com.bank.controller.ATMCardServer;
import com.bank.exceptions.InSufficientFundsException;
import com.bank.exceptions.InvalidAmountException;

public class ICICIBank implements ATMCardServer
{
	private double balance = 70000; //default minimum balance required.
	
	Map<String,Long> customer =new LinkedHashMap<>();
	
	public ICICIBank() 
	{
		System.out.println(" ~~~~~~~~~~~~~~~ WEL-COME to ICICI ATM Services ~~~~~~~~~~~~~~~ ");
		customer.put("Dilip Patel",7876_5432_1123_4561L);
		customer.put("Govind Makawana",7876_5432_1123_4562L);
		customer.put("Pradeep Thakur",7876_5432_1123_4563L);
		customer.put("Jay Panchal",7876_5432_1123_4564L);
		customer.put("Manav Patel",7876_5432_1123_4565L);
		customer.put("Jayesh Pandya",7876_5432_1123_4566L);
		customer.put("Darshan Chauhan",7876_5432_1123_4567L);
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
