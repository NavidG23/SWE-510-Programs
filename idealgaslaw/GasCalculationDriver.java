//Navid Galt
//SWE 510
//Fall 2016

package idealgaslaw;

import java.util.Scanner;

public class GasCalculationDriver
{

	public static void main(String[] args) 
	{
		//Welcomes students to hell
		System.out.println("Welcome to Ideal Gas Law!");
		System.out.println("PV=nRT"); 

		//Assigns value/object to Scanner
		Scanner whatcalc = new Scanner (System.in);
		String string1;
		System.out.println("Enter Unknown Variable from the following selection: Pressure, Volume, Number of Moles or Temperature");
		
		//Prompt users to input values	
		GasCalculation gas = new GasCalculation();
		string1 = whatcalc.nextLine();
		gas.gascalc(string1);
		
	}

}