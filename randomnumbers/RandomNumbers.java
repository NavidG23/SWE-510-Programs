package randomnumbers;
import java.util.Random;

public class RandomNumbers 
{
	public static void main (String[] args)
	{
		double randomnumbers= 1000000;
		double mean = 0; //declaring mean object
		double [] randomnumber = new double [10];
		double [] randomnumber1 = new double [10000];
		double [] randomnumber2 = new double [1000000];


		Random num = new Random();

		for(int k=0; k < randomnumber.length; k++)
		{
			randomnumber[k] = num.nextDouble();
			mean += randomnumber[k];	
		}

		System.out.println("The mean is " + mean/10);
		mean = 0;

		for(int k=0; k < randomnumber1.length; k++)
		{
			randomnumber1[k] = num.nextDouble();
			mean += randomnumber1[k];
		}

		System.out.println("The mean is " + mean/10000);
		mean = 0;

		for(int k=0; k < randomnumber2.length; k++)
		{
			randomnumber2[k] = num.nextDouble();
			mean += randomnumber2[k];
		}

		System.out.println("The mean is " + mean/1000000);
		mean = 0;

	}
}