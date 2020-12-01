package com.bank.exceptions;

public class InvalidAmountException extends Exception
{
	public InvalidAmountException() 
	{
		super();
	}
	public InvalidAmountException(String message) 
	{
		super(message);
	}
}
//The serializable class InvalidCustomerData does not declare a static final serialVersionUID field of type long

//UID = Universal IDentifier