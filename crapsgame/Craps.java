package crapsgame;
//Navid Galt
//SWE 510
//Fall 2016


//Fig. 6.8: Craps.java 
//Craps class simulates the dice game craps
//Import Scanner object

import java.security.SecureRandom; 
import java.util.Scanner;
public class Craps
{
  // create secure random number generator for use in method rollDice
  private static final SecureRandom randomNumbers = new SecureRandom();

  // enum type with constants that represent the game status
  private enum Status {CONTINUE, WON, LOST};
  // constants that represent common rolls of the dice
  private static final int SNAKE_EYES = 2;
  private static final int TREY = 3;
  private static final int SEVEN = 7;
  private static final int YO_LEVEN = 11;
  private static final int BOX_CARS = 12;

  //
  public static boolean byebye = false;
  public static boolean playon = true;    






  // plays one game of craps    

  public void PlayTheGame()
  {
      // high roller
      double bankAccount = 100000;
      // leave with some dignity
      double minimumAmount = 1;
      // bank status boolean
      boolean bankStatus = true;       
      // current wager
      double currentWager = 0;
     //    While we are in good  status with bank account
     while (bankStatus)
     {        

         int streak = 0;
         Status gameStatus = Status.CONTINUE; // can contain CONTINUE, WON or LOST
         // while game is not complete
         while (gameStatus == Status.CONTINUE) // not WON or LOST
         {     	              
             System.out.println("Your Account Balance, Mr. Bond -> " + bankAccount);              	   
             currentWager = checkWager(bankAccount);           
             System.out.println("Your maximum bet limit is -> " + bankAccount + " and your wager is " + currentWager );
              int myPoint = 0; // point if no win or loss on first roll
              int sumOfDice = rollDice(); // first roll of the dice
              // determine game status and point based on first roll 
              switch (sumOfDice) 
              {
                 case SEVEN: // win with 7 on first roll
                 case YO_LEVEN: // win with 11 on first roll           
                    gameStatus = Status.WON;
                    break;
                 case SNAKE_EYES: // lose with 2 on first roll
                 case TREY: // lose with 3 on first roll
                 case BOX_CARS: // lose with 12 on first roll
                    gameStatus = Status.LOST;
                    break;
                 default: // did not win or lose, so remember point         
                    gameStatus = Status.CONTINUE; // game is not over
                    myPoint = sumOfDice; // remember the point
                    System.out.printf("Point is %d%n", myPoint);
                    break;
              } 
              streak = streak + 1;
              System.out.println("Roll dice again" + streak);
              sumOfDice = rollDice(); // roll dice again
              // determine game status
              if (sumOfDice == myPoint) // win by making point
              {
                 gameStatus = Status.WON;
              }
              else 
              {
                 if (sumOfDice == SEVEN) // lose by rolling 7 before point
                 {
                     gameStatus = Status.LOST;
                 }
              }
         } 
         // display won or lost message
         if (gameStatus == Status.WON)
         {
             System.out.println("Player wins " + currentWager + " more.");
             //Positive Chatter for if you win
             System.out.println("");
             System.out.println("Crappy Joe says ...");
             System.out.println("    ->  " + chatterTool("positive"));
             System.out.println("");
             bankAccount = bankAccount + currentWager;              
             System.out.println("Current balance  ->  " + bankAccount);
             if(playAgain())
             {    
                 // Do something                  
             }
             else
             {
                 return;
             }
         }
         else
         {
            // status
            System.out.println("Player loses " + currentWager + " less.");
            //Negative Chatter for if you lose
            System.out.println("");
            System.out.println("Crappy Joe says ...");
            System.out.println("    ->  " + chatterTool("negative"));
            System.out.println("");
            bankAccount = bankAccount - currentWager;
            System.out.println("Current balance  ->  " + bankAccount);
            if(bankAccount <= minimumAmount)
            {
                // kicks us out of the loop
                bankStatus = false;
                return;
            }
            if(playAgain())
            {    
                // Do something                 
            }
            else
            {
               return;
            }
         }
      }
   }

   //Function for playing loop
      public static boolean playAgain()
      {         
          // get user decision
          Scanner scan = new Scanner(System.in);
          System.out.println("Play again?  ");
          String response = scan.nextLine();
          if(response.compareTo("Y") == 0)
          {
              byebye=true;
          }
          else if(response.compareTo("N") == 0)
          {
              byebye=false;
          }
          else
          {
              System.out.println("Not an acceptible answer, Please type Y or N");
              playAgain();
          }
          return byebye;	
      }

      //Function for Wager validation with a bankaccount
      public static double checkWager(double bankAccount)
      {         
          System.out.print("Enter your wager - > ");
          Scanner scan = new Scanner(System.in);
          String wager = scan.nextLine();        
          Double currentWager2 = Double.parseDouble(wager);
          System.out.println("Your Wager -> " + currentWager2);          
          if((bankAccount - currentWager2) >= 0)
          {
              playon=true;
              return currentWager2;	
          }
          else
          {
              playon=false;         
              System.out.println("Insufficient funds, try again Mr. Bond");
              currentWager2 = checkWager(bankAccount);    // lil sum'n sum'n (currentWager2 =)
          }
          return currentWager2;	
      }

  //	chatterTool for randomly saying things to the player
  public static String chatterTool(String chatterType)
  {
       String[] positiveChatter = new String[6];
       String[] negativeChatter = new String[6];                
       positiveChatter[0] = "You're doing great! but you're still a loser.";
       positiveChatter[1] = "Keep it up! You can almost pay off our national debt!";
       positiveChatter[2] = ".. . ... .. .. ... . Oh sorry are you still winning?";
       positiveChatter[3] = "Please keep going and visit my 'go-fund me page'";
       positiveChatter[4] = "Can you loan me some cash man?";
       positiveChatter[5] = "Winner";        
       negativeChatter[0] = "Your wife is definitely going to divorce you after this";
       negativeChatter[1] = "There goes your home!";
       negativeChatter[2] = "Ouch... you will never find a friend.";
       negativeChatter[3] = "Your gambling intuition is shit";
       negativeChatter[4] = "Please dont come back";
       negativeChatter[5] = "How will you explain this stunning defeat to your children?";        
       String badComeback = "You are so bad, you are just so bad!";        
       switch(chatterType)
       {            
           case "negative":
               return negativeChatter[randomNumbers.nextInt(5)];                
           case "positive":
               return positiveChatter[randomNumbers.nextInt(5)];                          
           default:
               return badComeback;
       }            
   }   

  // roll dice, calculate sum and display results
  public static int rollDice()
  {
     // pick random die values
     int die1 = 1 + randomNumbers.nextInt(6); // first die roll
     int die2 = 1 + randomNumbers.nextInt(6); // second die roll
     int sum = die1 + die2; // sum of die values
     // display results of this roll
     System.out.printf("Player rolled %d + %d = %d%n", 
        die1, die2, sum);
     return sum; 
  }
}
  
 // end class Craps


