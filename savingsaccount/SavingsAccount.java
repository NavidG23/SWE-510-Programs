package savingsaccount;

/**
* <h1>Savings account</h1>
* The savings account class <b>currently</b> allows getting and setting of savingsBalance, annualInterestRate, 
* and number of months that will be factored into the calculation of future interest applied savings balance.
*
* @author  Navid Galt
* @version 1.0
* @since   2016-11-26
*/
public class SavingsAccount 
{
  // declare member variables
  private double savingsBalance;  
  private double annualInterestRate;
  private int number_of_months;
    
  /**
  * Empty Constructor.
  */
  public SavingsAccount()
  {
  	// initialize member variables (DEFAULT VALUES)
    this.savingsBalance = 0;
    this.annualInterestRate = 0;
    this.number_of_months = 12;
  }
  ///////////////////////////////////////end of constructor 1
    
  /**
  * Constructor.
  * 
  * @param sb (required) Initial savings balance.
  */
  public SavingsAccount(double sb)
  {
    // initialize member variables
    this.savingsBalance = sb;
    this.annualInterestRate = 0;
    this.number_of_months = 12;
  }
  ///////////////////////////////////////end of constructor 2

  /**
  * 2 parameter constructor.
  * 
  * @param sb (required) Initial savings balance.
  * @param air (required) Initial interest rate.
  */
  public SavingsAccount(double sb, double air)
  {
    // initialize member variables
    this.savingsBalance = sb;
    this.annualInterestRate = air;
    this.number_of_months = 12;
  }    
 ////////////////////////////////////////end of constructor 3

  /**
  * 3 parameter constructor.
  * 
  * @param sb (required) Initial savings balance.
  * @param air (required) Initial interest rate.
  * @param nm (required) Number of months
  */  
  public SavingsAccount(double sb, double air, int nm)
  {
    // initialize member variables
    this.savingsBalance = sb;
    this.annualInterestRate = air;
    this.number_of_months = nm;
  }  
      
  /**
   * Applies the interest rate to the currently held balance.
     */
  public void applyInterestToBalance()
  {
      double tempBalance = this.savingsBalance + (this.savingsBalance * (this.annualInterestRate));
      this.savingsBalance = tempBalance;
  }
    
     /**
     * Return monthly interest calculation.
     * @param n     the number of months applied in calculation.
     * @return      the monthly interest calculation result.
     * @see         double
     */
  public double calculateMonthlyInterest(int n) //variable n is number of months passed into function
  {
    //  (savingsBalance*annualInterestRate/number_of_months)
    return ( (this.getSavingsBalance() * this.getAnnualInterestRate()) / (double) n); //type-casting-> changing double to int
  }

    /**
     * Returns the current savings balance.
     * @return      the current savings balance
     * @see         double
     */
	public double getSavingsBalance()
	{
		return this.savingsBalance;
	}    

    /**
     * Sets the savings balance to the parameter passed in.
     * @param  d  the new savings balance
     * @see         void
     */ 
    public void setSavingsBalance(double d)
    {
        this.savingsBalance = d;
    }
  
//Getter*setter for getAnnualInterestRate
// Getter methods
    /**
     * Returns the annual interest rate.
     * @return      the current annual interest rate
     * @see         double
     */
	public double getAnnualInterestRate()
	{
		return this.annualInterestRate;
	}    
	
    /**
     * Sets the annual interest rate equal to the parameter passed in.
     * @param  d  the new annual interest rate.
     * @see         void
     */
  public void setAnnualInterestRate(double d)
  {
		this.annualInterestRate = d;
  }
    
}