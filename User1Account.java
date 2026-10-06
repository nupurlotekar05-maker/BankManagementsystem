package Bankmanagementsystem;

import java.util.ArrayList;

public class User1Account {
  private String name;
  private String email;
  private int pin;
  private String accounttype;// Savings or Current
  private double balance;
  private ArrayList<String> transactionHistory;
  
  public String getName() {
	  return name;
  }
  public void setname(String name) {
	  this.name=name;
  }
  public String getEmail() {
	  return email;
  }
  public void setemail(String email) {
	  this.email=email;
  }
  public int getpin() {
	  return pin;
  }
  public void setpin(int pin) {
	  this.pin=pin;
  }
  public String getAccounttype() {
	  return accounttype;
  }
  public void setaccounttype(String accounttype) {
	  this.accounttype=accounttype;
  }
  public double getbalance() {
	  return balance;
  }
  public void setbalance(double balance) {
	  this.balance=balance;
  }
  public ArrayList<String> getTransactionHistory(){
	  return transactionHistory;
  }
  
 public User1Account( String name, String email, int pin,String accounttype) {
	 this.name=name;
	 this.email=email;
	 this.pin=pin;
	 this.accounttype=accounttype;
	 this.balance=0;
	 this.transactionHistory=new ArrayList<>();
	 
 }
 
 public String toString() {
	 return "UserAccount [name=" + name  + ", email=" + email + ", int pin=" 
             + pin + ", accounttype=" + accounttype +",balance"+balance+"]";
 }
}
