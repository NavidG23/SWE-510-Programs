package idealgaslaw;

import java.util.Scanner;

/*
 * This class allows you to calculate gas composition based on known variables
 * 
 * Navid Galt
 * SWE 510
 * Fall 2016*/

public class GasCalculation {
		
		//Declaring R as a constant
		final double R = 8.314; 

//Prompt user for known variables and find variable for Pressure
	public void gascalc(String string1){
		boolean flag = true; 
		while (flag) //create loop in case the wrong variable is entered
		{ 
			if (string1.equalsIgnoreCase("Pressure"))
			{
				flag = false;
				double p = 0;
				System.out.println("Enter known Variables");
				System.out.println("Provide Volume in Liters=");
				Scanner vx = new Scanner(System.in);
				double v = vx.nextDouble();
			
			
				System.out.println("Give Amount of Moles=");
				Scanner nm = new Scanner(System.in);
				double n = nm.nextDouble();
			
			
				System.out.println("In Kevlin give Temperature=");
				Scanner tk = new Scanner(System.in);
				double t = tk.nextDouble();
			
				p = ((n*R*t)/v);
				System.out.println("Value for Pressure = " +p + " pascals");	
			}

//Prompt user for known variables and find variable for Volume	
		
			else if (string1.equalsIgnoreCase("Volume"))
			{
				flag = false;
				double v = 0;
				System.out.println("Enter known Variables");
				System.out.println("Give Pressure in Pascals=");
				Scanner p = new Scanner(System.in);
				double Pressure = p.nextDouble();
			
			
				System.out.println("Give Amount of Moles=");
				Scanner nm = new Scanner(System.in);
				double n = nm.nextDouble();
			
			
				System.out.println("In Kevlin give Temperature=");
				Scanner tk = new Scanner(System.in);
				double t = tk.nextDouble();
			
				v = ((n*R*t)/Pressure);
				System.out.println("Value for Volume = " +v + " liters");
			 }

		

//Prompt user for known variable to help find number of Moles

			else if (string1.equalsIgnoreCase("Number of Moles"))
			{
				flag = false;
				double n = 0;
				System.out.println("Enter known Variables");
				System.out.println("Provide Volume in Liters=");
				Scanner vx = new Scanner(System.in);
				double v = vx.nextDouble();
			
			
				System.out.println("Give Pressure in Pascals=");
				Scanner p = new Scanner(System.in);
				double Pressure = p.nextDouble();
			
			
				System.out.println("In Kevlin give Temperature=");
				Scanner tk = new Scanner(System.in);
				double t = tk.nextDouble();
			
			
				n = ((Pressure*v)/(R*t));
				System.out.println("Amount of Moles = " +n + " moles");
			 }

//Prompt user for known variable to help find Temperature

			else if (string1.equalsIgnoreCase("Temperature"))
			{
				flag = false;
				double t = 0;
				System.out.println("Enter known Variables");
				System.out.println("Provide Volume in Liters=");
				Scanner vx = new Scanner(System.in);
				double v = vx.nextDouble();
			
			
				System.out.println("Give Amount of Moles=");
				Scanner nm = new Scanner(System.in);
				double n = nm.nextDouble();
			
			
				System.out.println("Give Pressure in Pascals=");
				Scanner p = new Scanner(System.in);
				double Pressure = p.nextDouble();
			
				t = ((Pressure*v)/(n*R));
				System.out.println("Value of the Temperature = " +t + " kelvin");
			}	
		
//Display line if user enters any other invalid variable
			else
				System.out.println("Invalid variable, please enter valid variable");
				Scanner asdf = new Scanner(System.in);
				string1 = asdf.nextLine();
	
		}
	}
}



