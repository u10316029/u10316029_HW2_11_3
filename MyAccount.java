/*U10316029
 * 郭政慶
 */
import java.util.Date;
import java.util.Scanner;

public class MyAccount {
	/**main method*/
	public static void main(String[] args){
		Scanner input = new Scanner(System.in); //Create a Scanner 
		System.out.println("Please enter your id : "); //Prompt the user enter the id
		int a = input.nextInt();
		double b = 20000; //Initialize the b
		double c = 4.5; //Initialize the c
		boolean start = true; //Initialize the start
		Account myaccount = new Account(a,b,c); //Create a account with id = 1122,balance = 20000,c = 4.5%
		
		if(a==1122){ //judge whether the id is 1122
			System.out.println(myaccount.toString());//Display the balance and annual interest rate
			System.out.println("Do you want to deposit or withdraw or check(d/w/c)?"); //Prompt the user take action that what you want to do
			String ans = input.next();
			while(start){ //Decide the action that the user want to do 
				if(ans.equals("d")){
					System.out.println("deposit : "); //Prompt the user enter how much want to deposit
					double in= input.nextDouble();
					b = myaccount.deposit(in);
					SavingsAccount account1 = new SavingsAccount(a,b,c);//Create a new account with id = 1122,balance = 20000+in,c = 4.5%
					System.out.println(account1.toString());// Display the balance and monthly interest
					break;
				}
				else if(ans.equals("w")){
					System.out.println("withdraw : ");//Prompt the user enter how much want to withdraw
					double out = input.nextDouble();
					b = myaccount.withdraw(out);
					SavingsAccount account2 = new SavingsAccount(a,b,c);//Create a new account with id = 1122,balance = 20000-out,c = 4.5%
					account2.withdraw(out); //Determine whether the withdraw is more than balance; 
					System.out.println(account2.toString());// Display the balance and monthly interest
					break;
				}
				else if(ans.equals("c")){
					System.out.println("You can only check $20000 at most\ncheck :");//Prompt the user enter how much want to check
					double che = input.nextDouble();
					b = myaccount.withdraw(che);
					CheckingAccount account3 = new CheckingAccount(a,b,c);//Create a new account with id = 1122,balance = 20000-che,c = 4.5%
					account3.withdraw(che); //Determine whether the check is more than balance; 
					System.out.println(account3.toString() + che);// Display the balance and how much the user check
					break;
				}
				else{
					System.out.println("Error,please enter again ");
					ans = input.next();
				}
			}
		}
	}

}
class Account{
	private int id = 0; //Initialize the id
	private double balance = 0;//Initialize the balance
	private double annualInterestRate ;
	private Date dateCreated = new Date(); //Create a Date
	Account(){} //Create a no-arg constructor
	Account(int id,double balance,double annualInterestRate){
		this.id = id;
		this.annualInterestRate = annualInterestRate;
		this.balance = balance;
	}
	public int getid(){
		return id;
	}
	public double getbalance(){
		return balance;
	}
	public double getannualInterestRate(){
		return annualInterestRate;
	}
	public String getdateCreated(){ //Return the current time
		return dateCreated.toString();
	}
	public double getMonthlyInterestRate(){ //Return the monthly interest rate
		return annualInterestRate/1200;
	}
	public double getMonthlyInterest(){ //Return the monthly interest 
		return balance * getMonthlyInterestRate();
	}
	public double withdraw(double money){ //Return the balance when deposit
		balance = balance- money;
		return balance;
	}
	public double deposit(double money){ //Return the balance when withdraw
		balance = balance + money;
		return balance;
	}
	public void setBalance(double balance){ //set the new balance
		this.balance = balance;
	}
	public void setAnnualInterestRate(double annualInterestRate ){ //set the new annual interest rate
		this.annualInterestRate = annualInterestRate;
	}
	
	public String toString(){
		return "Your balance of account is $"+getbalance()+" and your annual interest rate is "+ getannualInterestRate()+"%";
	}
}
/*Inherit superclass's properties and methods*/
class SavingsAccount extends Account{
	int i = 1;
	SavingsAccount(){ //Invoke the superclass's no-arg constructor 
		super();
	}
	SavingsAccount(int id,double balance,double annualInterestRate){
		super(id, balance, annualInterestRate);
	}
	@Override
	public double withdraw(double money){ //Judge whether the withdraw is more than balance
		if(money>getbalance()){
			i = 0;
			System.out.println("Unable to withdraw due to insufficient funds");
			return getbalance();
		}
		else{
			double balance  = getbalance() - money;
			return balance;
		}
	}
	@Override
	public String toString(){
		if(i != 0)
		return "The date of transaction is " + super.getdateCreated()+"\nYour balance of account is "+getbalance()+"and your Monthly Interest is $"+Math.round(getMonthlyInterest());
		else
		return "The date of transaction is " + super.getdateCreated();
	}
	
}
/*Inherit superclass's properties and methods*/
class CheckingAccount extends Account{
	int j =1;
	CheckingAccount(){ //Invoke the superclass's no-arg constructor 
		super();
	}
	CheckingAccount(int id,double balance,double annualInterestRate){
		super(id, balance, annualInterestRate);
	}
	@Override
	public double withdraw(double money){ //Judge whether the check is more than the limit 
		if(money>getbalance()){
			j = 0;
			System.out.println("Unable to check due to insufficient funds");
			return getbalance();
		}
		else{
			return money;
		}
	}
	@Override
	public String toString(){
		if(j==0)
			return "The date of transaction is " + super.getdateCreated();
		else{
			 return "The date of transaction is " + super.getdateCreated()+"\nYour balance of account is "+getbalance()+"\nHere is your check: " ;
		}
	}
}
	
