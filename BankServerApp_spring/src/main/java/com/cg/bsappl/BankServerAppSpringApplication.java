package com.cg.bsappl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataIntegrityViolationException;

import com.cg.bsappl.service.AccountService;
import com.cg.bsappl.service.CustomerService;
import com.cg.bsappl.service.TransactionService;

import com.cg.bsappl.entities.*;


import com.cg.bsappl.exception.AccountException;
import com.cg.bsappl.exception.CustomerException;


@SpringBootApplication(scanBasePackages = "com.cg.bsappl.*")
@ComponentScan(basePackages = "com.cg.bsappl.*")
@EntityScan("com.cg.bsappl.*")
public class BankServerAppSpringApplication implements CommandLineRunner  {
	
	
	
	    private static AccountService accountService;
	    private static CustomerService customerService;
	    private static TransactionService transactionService;

	    @Autowired
	    public BankServerAppSpringApplication(AccountService accountService,CustomerService customerService,TransactionService transactionService) {
	        this.accountService = accountService;
	        this.customerService = customerService;
	        this.transactionService = transactionService;
	    }

	public static void main(String[] args) {
		SpringApplication.run(BankServerAppSpringApplication.class, args);
	}

	
	
	
	@Override
    public void run(String... args) throws Exception {
		Scanner s=new Scanner(System.in);
		
		System.out.println("========================Welcome to The bank of ABC========================");
		while(true)
		{
			System.out.println("1.Login as Admin");
			System.out.println("2.Login as Customer");
			System.out.println("0.Exit bank application");
			try {
			int log=s.nextInt();
			
			switch(log)
			{
				case 1:
					
					adminPage();
					
					
					break;
				case 2:
					
					customerPage();
					
					
					break;
				case 0:
					System.out.println("Leaving the bank application...");
					System.exit(0);
					break;
				default:
					System.out.println("Enter valid choice");
			}
			}
			catch(InputMismatchException e)
			{
				System.out.println("Enter only valid integers");
				s.nextLine();
			}
		}
    	
    }
	
	public static void adminPage() throws CustomerException, DataIntegrityViolationException 
	{
		
		Scanner s1=new Scanner(System.in);
		while(true)
		{
			System.out.println("1.Create customerAccount");
			System.out.println("2.Delete customerAccount");
			System.out.println("3.Update customerDetails");
			System.out.println("0.Back to main menu");
			System.out.println("Enter your choice");;
			int Choice=s1.nextInt();
			switch(Choice)
			{
				case 1:
					
					System.out.println("Enter First Name: ");
					String fn=s1.next();
					System.out.println("Enter Last Name");
					String ln=s1.next();
					System.out.println("Enter EmailId");
					String ei=s1.next();
					System.out.println("Enter amount");
					int am=s1.nextInt();
					Customer c=new Customer(0,fn,ln,ei);
					customerService.addCustomer(c);
					accountService.createAccount(new Account(accountService.getNumberOfAccounts()+1,am,c));
					System.out.println("the account is added with account Number:"+accountService.getNumberOfAccounts());
					
					break;
				case 2:
					System.out.println("Enter customer Account Number to delete");
					int de=s1.nextInt();
					
					try {
						Account ac=accountService.getAccountById(de);
					if(ac!=null) {	
					Customer c1=accountService.getAccountById(de).getCustomer();
					customerService.deleteCustomer(c1.getCustomerId());	
					accountService.deleteAccount(de);
					System.out.println("customer Account of account number :"+de+" is deleted");
					}
					}
					catch(NullPointerException e)
					{
						System.out.println("The account with account number: "+de+" did not exist");
					}
					catch(DataIntegrityViolationException e)
					{
						System.out.println("Parent row can't be deleted without child row");
					}
					break;
				case 3:
					
					System.out.println("Enter the customer Account number to update");
					int up=s1.nextInt();
					try {
					
					System.out.println("Current Customer details of the account no: "+up+"are=");
					System.out.println(accountService.getAccountById(up));
					System.out.println("Enter First Name");
					String upfn=s1.next();
					System.out.println("Enter Last Name");
					String upln=s1.next();
					System.out.println("Enter EmailId");
					String upeid=s1.next();
					Customer c1=new Customer(accountService.getAccountById(up).getCustomer().getCustomerId(),upfn,upln,upeid);
					customerService.updateCustomer(c1);
					
					accountService.updateAccount(new Account(up,accountService.getAccountById(up).getBalance(),new Customer(accountService.getAccountById(up).getCustomer().getCustomerId(),upfn,upln,upeid)));
					}
					catch(NullPointerException e)
					{
						System.out.println("The account with account number: "+up+" did not exist");
					}
					break;
				
				case 0:
					return;
				default:
					System.out.println("Enter valid choice");
			}
		}
		
	}
	public static void customerPage()
	{
		try {
		Scanner s2=new Scanner(System.in);
		while(true) {
			System.out.println("================================================================");
	        System.out.println("1.Display the balance for the Customer");
			System.out.println("2.Withdraw/deposit");
			System.out.println("3.Transfer money");
			System.out.println("4.Display transactions");
			System.out.println("5.Account customerDetails");
			System.out.println("0-Back to Main Menu");
			System.out.println("================================================================");
			
			System.out.println("Enter your choice:");
			
			int choice=s2.nextInt();
			switch(choice)
			{
			case 1:
				System.out.println("================================================================");
				System.out.println("Enter your Customer Account Number");
				System.out.println("================================================================");
				int accNum=s2.nextInt();
				Account account = accountService.getAccountById(accNum);
	            if (account != null) {
	                System.out.println("Account Balance for Account Number " + accNum + ": " + account.getBalance());
	                System.out.println("================================================================");
	            } else {
	                System.out.println("Account not found for Account Number " + accNum);
	                System.out.println("================================================================");
	            }
				break;
			case 2:
				System.out.println("Enter 'deposit' or 'withdraw'  ");
				String type=s2.next();
				if(type.equals("deposit"))
				{
					System.out.println("Enter your account number");
					int accNum2=s2.nextInt();
					System.out.println("Enter the deposit amount");
					double amount=s2.nextDouble();
					accountService.deposit(accNum2, amount);
				}
				if(type.equals("withdraw"))
				{
					System.out.println("Enter your account number");
					int accNum3=s2.nextInt();
					System.out.println("Enter the withdraw amount");
					double amount=s2.nextDouble();
					accountService.withdraw(accNum3, amount);
				}
				break;
			case 3:
				System.out.println("Enter the money to transfer:");
				double amount=s2.nextDouble();
				System.out.println("Enter the from account number:");
				int accNum4=s2.nextInt();
				System.out.println("Enter the to account number:");
				int accNum5=s2.nextInt();
				accountService.transfer(accNum4, accNum5, amount);
				break;
			case 4:
				 System.out.println("Enter your Customer Account Number");
				    int accNum6 = s2.nextInt();
				    List<Transaction> transactions = transactionService.getTransactionsByAccountId(accNum6);

                    System.out.println("================================================================");
                    System.out.println("Transactions for Account Number " + accNum6 + ":");
                    for (Transaction transaction : transactions) {
                        System.out.println(transaction);
                    }
				
				    System.out.println("================================================================");
				break;
			case 5:
				System.out.println("Enter the account number to get customer details");
				int accNum7=s2.nextInt();
				System.out.println("the customer details of account number: "+accNum7+"are=");
				//System.out.println(customerService.getCustomerDetailsById(accNum7));
				System.out.println(accountService.getAccountById(accNum7).getCustomer());
			case 0:
				return;
			default:
				System.out.println("Please enter valid choice");
				break;
			}
		}
		}
		catch(AccountException e)
		{
			
		}
	}
	
	
	
}
