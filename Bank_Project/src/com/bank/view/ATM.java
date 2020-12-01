package com.bank.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.bank.controller.ATMCardServer;
import com.bank.exceptions.InSufficientFundsException;
import com.bank.exceptions.InvalidAmountException;

public class ATM 
{
	public static void main(String[] args) 
	{			
		outerloop:while (true) //as "goto" statement
		{
			System.out.println("Enter Your Bank-Name which Bank Card you have?");
			Scanner sc = new Scanner(System.in);
			
			try 
			{	
//				Class cls = Class.forName("com.bank.logic.BOIBank");
				Class cls = Class.forName("com.bank.logic."+sc.nextLine()+"Bank");
//				Class class = Class.forName("com.bank.logic."+sc.nextLine()+"Bank");
//					//can not assign the name "class"
				
				
//				System.out.println("CLASS: "+cls);
				Object object = cls.newInstance();
//				System.out.println("OBJECT: "+object);
				
				innerloop1:while(true)
				{
					System.out.println();
					
					System.out.print("Enter Your ATM Card Number : ");
					long cardNumber = sc.nextLong();
					sc.nextLine();
					
					ATMCardServer card = (ATMCardServer)object;
//					All the functionalities in the MVC are gone through the Controller.
					
					if(card.checkCustomerData(cardNumber)) 
					{
						System.out.println("Card matched with our Bank Server!");

						System.out.println();
						
						System.out.println("Start Your Transactions : ");
						
						innerloop2:while(true) 
						{
							System.out.println();
							
							System.out.println("Choose A Option,");
							System.out.println("1.Deposite");
							System.out.println("2.Withdraw");
							System.out.println("3.Balance Enquiry");
							System.out.println("4.Exit");
						
							System.out.println();
							
							System.out.print("Enter Option number which operation you want to continue : ");
							int option = sc.nextInt();
							sc.nextLine();
							
							System.out.println();
							
							switch(option) 
							{
								case 1:
								{
									while(true) 
									{
										try 
										{
											System.out.print("Enter Amount to Deposite(in Rupees) : ");
											double amount = sc.nextDouble();
											sc.nextLine();
											
											card.deposite(amount);
											System.out.println("Rupees "+amount+" deposite successfully!");
											System.out.println("Current Balance : Rupees "+card.balanceEnquiry());
											
											continue innerloop2;
										}
										catch (InputMismatchException e) 
										{
											System.out.println("Error! Enter only Numbers!");
										}
										catch (InvalidAmountException e) 
										{
											System.out.println("Error!"+e.getMessage()+"!");
										}
									}
								}
								
								case 2:
								{
									while(true) 
									{
										try 
										{
											System.out.print("Enter Amount to Withdraw(in Rupees) : ");
											double amount = sc.nextDouble();
											sc.nextLine();
											
											card.withdraw(amount);
											System.out.println("Rupees "+amount+" Withdraw Successfully!");
											System.out.println("Current Balance : Rupees "+card.balanceEnquiry());

											continue innerloop2;
										}
										catch (InSufficientFundsException e) 
										{
											System.out.println("Sorry! Insufficient Fund in Your Account!");
										}
										catch (InvalidAmountException e) 
										{
											System.out.println("Error!"+e.getMessage()+"!");
										}
									}
								}
								
								case 3: 
								{
									System.out.println("Current Balance : Rupees "+card.balanceEnquiry());

									continue innerloop2;
								}
								
								case 4:
								{
									break outerloop;
								}
								
								default:
								{
									System.out.println("Invalid Option! Choose Correct Option!");
								}
							}
						}
					}
					else
					{
							System.out.println("Please,Try with a Valid Bank Card!");
					}
				}
			}
			catch (ClassNotFoundException | IllegalAccessException |NoClassDefFoundError|InstantiationException e)
			{
				System.out.println("Please: Enter a valid Bank Name!");
			}
			catch (InputMismatchException e) 
			{
				System.out.println("Enter only Numbers!");
//				break;
			}
		}
		System.out.println("\n***************Thank-You,Visit Again**************");
	}
}