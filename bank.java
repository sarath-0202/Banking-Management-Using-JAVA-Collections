package bank_management;

import java.io.*;
import java.util.*;



class bank
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static List<Integer> l_accno = new ArrayList<Integer>();
    static List<String> l_name = new ArrayList<String>();
    static List<Float> l_avail_balance = new ArrayList<Float>();
    
    
    public static void main(String args[])
    {
        login login = new login();
        login.check();
    }

    static void mainmenu()
    {
        try{
            int choice = -1;
            do{
                System.out.println("\n----------------------------------------------------------------------------");
                System.out.println("\t\t\tBank Management System");
                System.out.println("----------------------------------------------------------------------------\n");
                System.out.println("\t1. Add Account");
                System.out.println("\t2. Deposit");
                System.out.println("\t3. Withdraw");
                System.out.println("\t4. Display All Benificiary Accounts");
                System.out.println("\t5. Remove Benificiary");
                System.out.println("\t6. Search Beneficiary Account");
                System.out.println("\t7. Transfer Money");
                System.out.println("\t8. Exit");
                System.out.println("\n\n\tEnter Your Choice : ");
                choice = Integer.parseInt(br.readLine());
                System.out.println("-----------------------------------------------------------------------------");
                switch(choice)
                {
                    case 1 : addAccount();
                    break;
                    case 2 : deposit();
                    break;
                    case 3 : withdraw();
                    break;
                    case 4 : displayAllAccounts();
                    break;
                    case 5 : removeAccount();
                    break;
                    case 6 : searchAccount();
                    break;
                    case 7 : transfer();
                    break;
                    case 8 : System.out.println("Are You Sure (y/n) : ");
                             if(br.readLine().equalsIgnoreCase("y"))
                                choice = -1;
                    break;
                    default : System.out.println("INVALID CHOICE !!!");
                    break;
                }
            } while(choice != -1);
        }
        catch (Exception e)
        {System.out.println(e);}
    }

    static void addAccount()
    {
        try{
            System.out.println("Enter The Account Number : ");
            Integer accno = new Integer(br.readLine());
            if(l_accno.contains(accno))
            {
                System.out.println("Account Already Exists !!!");
            }
            else
            {
                System.out.println("Enter The Account Holder Name : ");
                String name = br.readLine();
                l_accno.add(accno);
                l_name.add(name);
                l_avail_balance.add(new Float(0));
                System.out.println("\n\n Account Added Successfully....\n\n");
                System.out.println(" Account Details Are As Follows : \n");
                display(l_accno.indexOf(accno));
            }
        }
        catch(Exception e)
        {System.out.println(e);}
    }
  
    static void displayAllAccounts()
    {
        System.out.println("      Account No.     |       Account Holder Name     |       Balance    ");
        System.out.println("-----------------------------------------------------------------------------");
        if(l_accno.size()>0)
        {
            for(int i=0;i<l_accno.size();i++)
            {
                System.out.printf("      %-15d        %-22s        %.2f",l_accno.get(i),l_name.get(i),l_avail_balance.get(i));
                System.out.println("\n-----------------------------------------------------------------------------");
                System.out.println();
            }
        }
        else
        {
            System.out.println("\n\n\t\t  No Account available !!!\n\n");
        }
    }

    static int searchAccount()
    {
        int accno;
        System.out.println("Enter The Account Number To Search : ");
        try{
            accno=Integer.parseInt(br.readLine());
            if (l_accno.contains(new Integer(accno)))
            {
                int index = l_accno.indexOf(new Integer(accno));
                display(index);
                return index;
            }
            else
                System.out.println("Account Does Not Exists !!!");
        }catch(Exception e)
        {
        	System.out.println(e);
        }
        return -1;
    }

    static void deposit()
    {
        int index=searchAccount();
        if(index>=0)
        {
            try{
                float balance = (float)(Float)l_avail_balance.get(index);
                System.out.println("\nEnter The Amount To Deposit : ");
                float amount=Float.parseFloat(br.readLine());
                balance += amount;
                l_avail_balance.set(index,new Float(balance));
                System.out.println("\n\n Amount Deposited Successfully....");
                System.out.println("\n\n Current Balance Is : Rs."+balance);
            }
            catch(Exception e)
            {
            	System.out.println(e);
            }
        }
    }

    static void withdraw()
    {
        int index=searchAccount();
        if(index>=0)
        {
            try{
                float balance = (float)(Float)l_avail_balance.get(index);
                if(balance <= 0)
                {
                    System.out.println("Account Does Not Have Sufficient Balance !!!\nPlease Deposit Some Amount First...");
                }
                else
                {
                    System.out.println("\nEnter The Amount To Withdraw : ");
                    float amount=Float.parseFloat(br.readLine());
                    if(balance <= amount)
                    {
                        System.out.println("Insufficient Balance !!!");
                    }
                    else
                    {
                        balance -= amount;
                        l_avail_balance.set(index,new Float(balance));
                        System.out.println("\n\n Amount Withdrawn Successfully....");
                        System.out.println("\n\n Current Balance Is : Rs."+balance);
                    }
                }
            }
            catch(Exception e)
            {
            	System.out.println(e);
            }
        }
    }

    static void removeAccount()
    {
        int index=searchAccount();
        if(index > 0)
        {
            l_accno.remove(index);
            l_name.remove(index);
            l_avail_balance.remove(index);
            System.out.println("\n\n Beneficiary Account Deleted Successfully....");
        }
        else if(l_accno.size() == 0)
        {
            System.out.println("Account Cannot Be Deleted Because User Account only present...");
        }
    }

    static void display(int index)
    {
        System.out.println("Account Number : "+l_accno.get(index));
        System.out.println("Account Holder Name : "+l_name.get(index));
        System.out.println("Available Balance : "+l_avail_balance.get(index));
    }

    
    static void transfer()
    {
        try{
            System.out.println("Enter The Account Number Of Sender : ");
            int sno = Integer.parseInt(br.readLine());
            if (l_accno.contains(new Integer(sno)))
            {
                float sbal = (float)(Float)l_avail_balance.get(l_accno.indexOf(new Integer(sno)));
                if(sbal > 0)
                { 
                    System.out.println("Enter The Account Number Of Reciever : ");
                    int dno = Integer.parseInt(br.readLine());
                    if (l_accno.contains(new Integer(dno)))
                    {
                        System.out.println("Enter The Amount To Be Transferred : ");
                        float amount = Float.parseFloat(br.readLine());
                        if(sbal <= amount)
                        {
                            System.out.println("Insufficient Balance !!!");
                        }
                        else
                        {
                            float dbal = (float)(Float)l_avail_balance.get(l_accno.indexOf(new Integer(dno)));
                            sbal -= amount;
                            dbal += amount;
                            l_avail_balance.set(l_accno.indexOf(new Integer(sno)),new Float(sbal));
                            l_avail_balance.set(l_accno.indexOf(new Integer(dno)),new Float(dbal));
                            System.out.println("\n\n Amount Has Been Transferred From A/c No. : "+sno+" To A/c No. : "+dno+" Successfully....");
                        }
                    }
                    else
                    {
                        System.out.println("Account Does Not Exist !!!");
                        System.out.println("Please Add Beneficiary Account First !!!");
                        addAccount();
                    }
                }
                else
                {
                    System.out.println("Insufficient Balance !!!");
                }
            }
            else
            {
                System.out.println("Account Does not Exist !!!");
                
            }
        }
        catch(Exception e)
        {	
        	System.out.println(e);
        }
    }
}