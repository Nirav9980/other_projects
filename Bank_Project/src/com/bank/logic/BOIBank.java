package com.bank.logic;

import java.util.LinkedHashMap;
import java.util.Map;

import com.bank.controller.ATMCardServer;
import com.bank.exceptions.InSufficientFundsException;
import com.bank.exceptions.InvalidAmountException;

public class BOIBank implements ATMCardServer
{
	private double balance = 10000; //default minimum balance required.
	
	Map<String,Long> customer =new LinkedHashMap<>();
	//Generic can also be assigned to the Super Class,as well.
	
	public BOIBank() 
	{
	System.out.println(" ~~~~~~~~~~~~~~~ WEL-COME to Bank Of India(BOI) ATM Services ~~~~~~~~~~~~~~~ ");
		
		customer.put("Pradeep Patel",8876_5432_1123_4561L); //887654321123456
//		where "L" represents the "long" DataType 
		customer.put("Naman Shah",8876_5432_1123_4562L);
		customer.put("Manan Shah",8876_5432_1123_4563L);
		customer.put("Krushit Modi",8876_5432_1123_4564L);
		customer.put("Paresh Panchal",8876_5432_1123_4565L);
		customer.put("Vipul Rathod",8876_5432_1123_4566L);
		customer.put("Shrey Parekh",8876_5432_1123_4567L);
		customer.put("Parth Parmar",8876_5432_1123_4568L);
	}
	public boolean checkCustomerData(long cardNumber) 
	{
		if (customer.containsValue(cardNumber))
		{
			return true;
		}
		return false; //else
	}
	@Override
	public void deposite(double amount) throws InvalidAmountException
	{
		if(amount<=0) 
		{
			throw new InvalidAmountException(amount+" is invalid Amount to Deposit.");
		}
		balance = balance + amount;
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
