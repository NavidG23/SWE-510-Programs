package savingsaccount;

/**
* <h1>Savings account driver</h1>
* The savings account driver <b>currently</b> tests 4 different initial conditions to 
* generate interest adjusted savings account balances over 12 months.
*
* @author  Navid Galt
* @version 1.0
* @since   2016-11-26
*/
public class SavingsAccountDriver {
    
    /**
  * This  method will test a savings account with initial balance and interest rate arguments.
  * 
  * @param initial (required) Initial savings balance.
  * @param i_rate (required) Initial interest rate.
  */
	public static void TestSavingsAccount(double initial, double i_rate)
    {
        
        
        int number_of_months = 12;
        
        //    instantiate constructor with 2 arguments- initial balance, interest rate
        //    int x = new int(7);
        SavingsAccount saver = new SavingsAccount(initial, i_rate);
        
        // calculate monthly interest (12 mo)
		System.out.println("Monthly interest for " + i_rate + "%   -> " + saver.calculateMonthlyInterest(number_of_months));           //calling function calculateMonthlyInterest with number_of_months as paramater

        // Saver 1 with different interest rates
        System.out.println("Saver has starting balance of : " + saver.getSavingsBalance()); 
        //    Apply interest to balance
		saver.applyInterestToBalance();     
        //    The verdict is?
        System.out.println("After one year Saver has : " + saver.getSavingsBalance());  
	
    }	
    
    /**
     * The main method for the SavingsAccountDriver class.
     *
     * @param args Not used
     */
		public static void main(String[] args)
		{
           /////////
		   //  Defining initial values
		   // Saver1 = 2000, Saver2= 3000
		  double saver1_initial_savings = 2000.0;
		  double saver2_initial_savings = 3000.0;
		  // Interest1 = 0.04f, Interest = .05f;
		  //Set annualInterestRate to 4% and 5%
		  double interest_rate_1 = 0.04;
		  double interest_rate_2 = 0.05;
		  //////////////////////  
            
            
            
            //    Test cases
            //    1. saver 1 with interest rate 1
            System.out.println("Testing saver 1 with interest rate 1.");
            TestSavingsAccount(saver1_initial_savings, interest_rate_1);
            System.out.println("");
            //    2. saver 2 with interest rate 1
            System.out.println("Testing saver 2 with interest rate 1.");
            TestSavingsAccount(saver2_initial_savings, interest_rate_1);
            System.out.println("");
            //    3. saver 1 with interest rate 2
            System.out.println("Testing saver 1 with interest rate 2.");
            TestSavingsAccount(saver1_initial_savings, interest_rate_2);
            System.out.println("");
            //    4. saver 2 with interest rate 2
            System.out.println("Testing saver 2 with interest rate 2.");
            TestSavingsAccount(saver2_initial_savings, interest_rate_2);

		}		
	}	
	



