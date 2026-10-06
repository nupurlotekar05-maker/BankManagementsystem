package Bankmanagementsystem;
import java.util.*;
public class BankManagementSystem {
Scanner sc=new Scanner(System.in);
User1Account user[]=new User1Account[10];
int choice;
int count=0;
User1Account currentuser;
 public void start1() {
	 do {
		 System.out.println("\n--- Online Banking System ---");
         System.out.println("1. Register (Create Account)");
         System.out.println("2. Login");
         System.out.println("3. Forgot PIN");
         System.out.println("4. Admin: Show All Accounts");
         System.out.println("5. Logout");
         System.out.print("Enter choice: ");
         choice = sc.nextInt();
         switch(choice) {
         case 1:
        	
        	 register1();
        	 break;
         case 2:
        	 login1();
        	 break;
         case 3:
        	  forgotpin1();
              break;
         case 4:
        	  adminview1();
               break;
         case 5:
        	 System.out.println("-------------------------------Logout... Thank You! ------------------------------");
        	 return;
        	 default:
        		 System.out.println("Invalid Choice Enter");
        		 break;
         }
	 }while(choice!=5);
 } 
   public void register1() {
	    sc.nextLine();
	    System.out.println("------------------------REGISTER (Create Account)--------------------");
	    System.out.println("Enter your Name");
	    String name=sc.nextLine();
	    System.out.println("Enter your Email");
	    String email=sc.nextLine();
	    System.out.println("Enter your 4 Digit PIN");
	    int pin=sc.nextInt();
	    sc.nextLine();
	    String accounttype="";
	    int typechoice=0;
	    do {
	    	 System.out.println("Select Account type:");
	    	 System.out.println("1. Savings Account");
	         System.out.println("2. Current Account");
	         System.out.print("Enter choice: ");
	          int typeChoice = sc.nextInt();
	        if(typeChoice==1) {
	        	accounttype="saving";
	        } else if(typeChoice==2) {
	        	accounttype="current";
	        } else {
	        	System.out.println("Invalid choice ! Please select again.");
	        }
	    }while(typechoice!=1 && typechoice!=2);
	    
	    user[count]= new User1Account(name,email,pin,accounttype);
	    		count++;
	    		System.out.println(accounttype+" account  created successfully for"+name+"!");
   }
  public void login1() {
	   sc.nextLine();
	   System.out.println("---------------------------------LOGIN--------------------------------");
	   System.out.println("Enter your Email");
	   String mail=sc.nextLine();
	   System.out.println("Enter your 4 Digit pin");
	   int pin=sc.nextInt();
	   for(int i=0;i<count;i++) {
		   if(user[i].getEmail().equals(mail)&&user[i].equals(pin)) {
			   currentuser=user[i];
			   System.out.println("Login successful! Welcome"  + currentuser.getName());
			   menu1();
			   return;
		   }
	   }
	   System.out.println("Invalid email or PIN!"); 
   }
  public void forgotpin1() {
	   sc.nextLine();
	   System.out.println("------------------------------FORGOT PIN----------------------------------");
	   System.out.print("Enter your registered email: ");
       String resetemail = sc.nextLine();
       for(int i=0;i<count;i++){
    	   if(user[i].getEmail().equals(resetemail)) {
    		   System.out.println("Your PIN is: " + user[i].getpin());
    	   }
       }
       System.out.println("No account found with this email.");
  }
  public void adminview1() {
	  System.out.println("-------------------------------Admin: All Accounts------------------------------");
	   for(int i=0;i<count;i++) {
		   System.out.println((i+1)+"."+user[i].getName()+"|"+user[i].getEmail()+"|"+user[i].getAccounttype()+"| Balance: Rs"+user[i].getbalance());
	   }
  }
  public void menu1() {
	  do {
          System.out.println("\n--- Banking Menu ---");
          System.out.println("1. Deposit Money");
          System.out.println("2. Withdraw Money");
          System.out.println("3. Transfer Money");
          System.out.println("4. View Balance");
          System.out.println("5. Transaction History");
          System.out.println("6. Add Interest (Savings only)");
          System.out.println("7. Loan EMI Calculator");
          System.out.println("8. Logout");
          System.out.print("Enter choice: ");
          choice = sc.nextInt();
          switch(choice) {
          case 1:
        	  System.out.println("Enter amount to deposit: Rs");
        	  double dep=sc.nextDouble();
        
        	  System.out.println("Deposited successfully!");
        	  break;
          
	     case 2:
	    	 System.out.println("Enter amount to withdraw : Rs");
    	     double w=sc.nextDouble();
    
    	     System.out.println("Withdraw successfully!");
    	     break;
	     case 3:
	    	 sc.nextLine();
	    	 System.out.println("Enter recipient email:");
	    	 String email=sc.nextLine();
	    	 User1Account recipient=null;
	    	 for(int i=0;i<count;i++) {
	    		 if(user[i].getEmail().equals(email)) {
	    			 recipient=user[i];
	    			 break;
	    		 }
	    	 }
	    	 if(recipient!=null) {
	    	   System.out.println("Enter amount to transfer :Rs");
	    	   double amount=sc.nextDouble();
	    	   
	    	} 
	    	 else {
	    		 System.out.println("recipient not found !");
	    	 }
	    	 break;
	     case 4:
	    	 System.out.println("Current balance :Rs"+currentuser.getbalance());
	    	 break;
	     case 5:
	    	 ArrayList <String> History= currentuser.getTransactionHistory();
	    	 if(History.isEmpty()) {
	    		System.out.println("No transactions yet..");
	    	 }else {
	    		for(String t:History) {
	    			System.out.println(t);
	    		} 
	    	 }
	    	 break;
	     case 6:
	    	 
	    	 break;
	     case 7:
	    	  System.out.print("Enter loan principal: Rs ");
              double p = sc.nextDouble();
              System.out.print("Enter annual interest rate (%): ");
              double r = sc.nextDouble();
              System.out.print("Enter number of months: ");
              int m = sc.nextInt();
              
	    	 break;
	     case 8:
	    	 System.out.println("-----------------------------Loged Out!--------------------------");
	    	 return;
	    	 
	    	 default:
	    		 System.out.println("Invalid choice");
      }
        }while(choice!=8);
  }
public static void main(String[] args) {
	BankManagementSystem b=new BankManagementSystem();
	b.start1();
}
}
