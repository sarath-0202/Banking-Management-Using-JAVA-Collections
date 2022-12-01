package bank_management;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class login
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String username="sarath";
    static String password="0202";
    String user;
    String pass;
    
    void accept()
    {
        try{
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("\n\t\tWelcome To Bank Management System\n");
            System.out.println("\nEnter The Username : ");
            user = br.readLine();
            System.out.println("Enter The Password : ");
            pass = br.readLine();
        }
        catch(Exception e)
        {}
    }
    void check()
    {
        for(int i = 2; i >= 0 ; i--)
        {
            accept();
            if(username.compareTo(user) == 0 && password.compareTo(pass) == 0)
            {
                System.out.println("\n\n Login Successfull....\n\n");
                bank.mainmenu();
                break;
            }
            else
            {
                System.out.println("\n\n Login Failed !!!");
                System.out.println(" The Entered Username Or Password May Be Incorrect !!!");
                if(i > 0)
                    System.out.println(" You Have "+i+" Chance(s) more....\n Please Enter Valid Credentials & Try Again....\n");
                else
                    System.out.println(" You Have Exhausted your login attempts !!!\n You can try After Some times....");
            }
        }
    }

}
