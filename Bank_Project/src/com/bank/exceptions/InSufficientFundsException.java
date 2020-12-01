package com.bank.exceptions;

public class InSufficientFundsException extends Exception 
{
	public InSufficientFundsException() 
	{
		super();
	}
	public InSufficientFundsException(String message) 
	{
		super(message);
	}
}
//The serializable class InvalidCustomerData does not declare a static final serialVersionUID field of type long

//UID = Universal IDentifier