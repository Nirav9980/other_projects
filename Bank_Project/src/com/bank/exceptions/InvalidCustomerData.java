package com.bank.exceptions;

public class InvalidCustomerData extends Exception 
{
	public InvalidCustomerData() 
	{
		super();
	}
	public InvalidCustomerData(String message) 
	{
		super(message);
	}
}
//The serializable class InvalidCustomerData does not declare a static final serialVersionUID field of type long

//UID = Universal IDentifier