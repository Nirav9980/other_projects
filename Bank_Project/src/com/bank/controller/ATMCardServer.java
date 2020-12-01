package com.bank.controller;

import com.bank.exceptions.*;

public interface ATMCardServer
{
	public boolean checkCustomerData(long cardNumber);
	
	public void deposite(double amount) throws InvalidAmountException;
	
	public double withdraw(double amount) throws InvalidAmountException,InSufficientFundsException;
	
	public double balanceEnquiry();
}
