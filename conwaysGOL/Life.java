package conwaysGOL;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Random;
/*
 * Navid Galt
 * SWE 510
 * Fall 2016
 * Final Project- Game of Life
 */

/*
	This program simulates iterations of the Game of Life, first introduced by John Horton Conway in 1970. The "game" is a 
	zero-player game, meaning that its evolution is determined by its initial state, requiring no further input.
	One interacts with the Game of Life by creating an initial configuration and observing how it evolves, or,
	for advanced "players", by creating patterns with particular properties. In this rendition, when prompted
	the user will enter 1 to enter the path for a txt file he created that houses the points and end point (-1,-1) that he 
	would like to begin with or enter 2 for a set of random points. The user will then be prompted to enter a number of iterations, in where they will be able to
	input as many iterations as they'd like. After that, the user may sit back and watch the magic happen as the board/panel
	will go through a series of rules and configure the cell population based on that. After all is said and done the user
	will input the filename he'd like to create that will display all end points through each iteration. Enjoy!

 */


public class Life
{
    /**
     * Main program opens a window whose content pane is a JPanel belonging to class Life.
     */
    //Declaring private and public member variables used throughout program
	
    public static final Random randomNumbers = new Random();
    public static int GRID_SIZE=30;  // Number of squares along each side of the board
                                         // (Should probably not be less than 10 or more than 200,)
    public static JFrame f = new JFrame("Life");    
    public static JFrame window = new JFrame();
    public static JPanel panel= new JPanel( new GridLayout(GRID_SIZE,GRID_SIZE) );
    private static Timer timer;  // Drives the game when the user presses the "Start" button.    
    public static JLabel label_grid[][] = new JLabel[GRID_SIZE][GRID_SIZE];      
    
    public static Life the_life;
    
    public Life()
    {        
    	init();
    }    
    //Creates window that will house the alive and dead cells
    public static void init()
    {
    	window.setSize(800,800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            
        window.add(panel, BorderLayout.CENTER);
        JLabel txt = new JLabel("GAME OF THRONES", JLabel.CENTER);
        txt.setHorizontalTextPosition(JLabel.CENTER);
        txt.setFont(new Font("Times New Roman", Font.PLAIN, 21));
        window.add(txt, BorderLayout.NORTH);
        for(int i = 0; i < GRID_SIZE; i++)
        {
            for(int j = 0; j < GRID_SIZE; j++)
            {      
                label_grid[i][j] = new JLabel("( " + i + ", " + j + " )");
                panel.add(label_grid[i][j]);
            }                                
        }
        window.setVisible(true);
        boolean main_loop = true;
        while( main_loop == true )
        {
            if(EnterQuestionLoop())
            {
                main_loop = false;
            }                    
        }        
    }
    //Method that creates interface for main menu
    public static String MainMenuPrompt()
    {                       
        System.out.println("Select your pref: ");
        System.out.println("1) Load a file ");     
        System.out.println("2) Run with random configuration ");
        System.out.println("3) Exit");
        System.out.print(">  ");
        Scanner scan = new Scanner(System.in);
        String x = scan.nextLine();        
        System.out.println("");
        
        return x;
    }
    //Method that prompts user for number of iterations
    public static int AskNumberOfIterations()
    {
        System.out.println("Enter number of iterations: ");
        System.out.print(">  ");
        Scanner scan = new Scanner(System.in);
        String x = scan.nextLine();        
        System.out.println("");
        return Integer.parseInt(x);
    }
    //Method to restart the game
    public static boolean AskToGoAgain()
    {
        System.out.println("Do you want to start over?");
        System.out.println("Enter 1 or 2");
        System.out.println("1) Yes");
        System.out.println("2) No");
        System.out.print(">  ");
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();        
        System.out.println("");
        if(x == 1)
        {
            return true;
        }
        else if (x == 2)
        {
            System.out.println("Good Day Sir.");
            System.exit(0);
        }
        return false;
    }    
    //method to set live cells to white and dead cells to black
    public static void ShowCurrentBoard(boolean[][][] grid_array)
    {        
        panel.removeAll();
         for(int i = 0; i < GRID_SIZE; i++)
         {
             for(int j = 0; j < GRID_SIZE; j++)
             {
                 if(grid_array[i][j][0])
                 {
                     label_grid[i][j] = new JLabel("( " + i + ", " + j + " )");
                     label_grid[i][j].setOpaque(true);
                     label_grid[i][j].setBackground(Color.WHITE);
                     label_grid[i][j].setForeground(Color.BLACK);
                 }
                 else
                 {
                     label_grid[i][j] = new JLabel("( " + i + ", " + j + " )");
                     label_grid[i][j].setOpaque(true);
                     label_grid[i][j].setBackground(Color.BLACK);
                     label_grid[i][j].setForeground(Color.WHITE);
                 }                 
                 panel.add(label_grid[i][j]);
             }                                
         } 
         window.setVisible(true);
    }     
    /*User must enter a path and file name for the program to run correctly, this function calls ReadFromFile
    method to do just that*/
    public static boolean EvaluateMainMenuChoice(String choice)
    {
        switch(choice)
        {            
            case "1":
                System.out.println("Please enter entire path for input filename");
                System.out.println("(i.e. C:\\Users\\Navid Galt\\workspace\\SWE 510 Projects\\src\\conwaysGOL\\input.txt)\n");
                System.out.println("Enter -> ");
                Scanner scan = new Scanner(System.in);
                String x = scan.nextLine();
                String[] coords = ReadFromFile( x );            
                boolean[][][] grid = SetAliveCoordinates(new boolean[GRID_SIZE][GRID_SIZE][2], coords);
                ShowCurrentBoard(grid);            
                int num_iter = AskNumberOfIterations();            
                String all_iter_coordinates = "";            
                for(int idx = 0; idx < num_iter; idx++)
                {
                    if( GetAliveCoordinates(grid).compareTo("-1,-1") == 1 )
                    {
                        System.out.println("You have no points left.");
                        break;
                    }
                    else
                    {                           
                        //updates the grid given user defined initial points
                        grid = DoLifeIteration(grid);               
                        ShowCurrentBoard(grid);
                        all_iter_coordinates = all_iter_coordinates + GetAliveCoordinates(  grid  );
                    }
                }  
                //this function calls WritetoFile method and prompts users for output file name and saves it in the path
                System.out.println("Please enter enitre path + output filename -> ");
                System.out.println("(i.e. C:\\Users\\Navid Galt\\workspace\\SWE 510 Projects\\src\\conwaysGOL\\output.txt)");
                System.out.println("Enter -> ");
                Scanner scan2 = new Scanner(System.in);
                String y = scan2.nextLine();
                WriteToFile(    all_iter_coordinates, y);                            
                // ask user to go again
                if(AskToGoAgain())
                {
                    return true;
                }
                else
                {
                    return false;
                }                       
            //runs game with random points
            case "2":
                System.out.println("Running with random points...");
                num_iter = AskNumberOfIterations();
                coords = GetRandomNumbers();
                grid = new boolean[GRID_SIZE][GRID_SIZE][2];
                grid = SetAliveCoordinates(grid, coords);
                all_iter_coordinates = "";
                ShowCurrentBoard(grid);
                for(int idx = 0; idx < num_iter; idx++)
                {
                    if( GetAliveCoordinates(grid) == "-1,-1" )
                    {
                        System.out.println("You have no points left.");
                        ShowCurrentBoard(grid);
                    }
                    else
                    {
                        grid = DoLifeIteration(grid);         
                        ShowCurrentBoard(grid);
                        //updates the grid given random initial points
                    }
                }
                if(AskToGoAgain())
                {
                    return true;
                }
                break;
            //case 3 exits the system
            case "3":
                System.exit(0);
            
            default:
                System.out.println("Please enter 1 - 3"); 
        }
		return false;                
    }
    //Random number generator for option 2
    public static String[] GetRandomNumbers()
    {         
         int num_of_numbers = randomNumbers.nextInt(GRID_SIZE*GRID_SIZE) / 2;    
         String random_file = "";
         for(int i = 0; i < num_of_numbers; i++)
         {
             int x = randomNumbers.nextInt(GRID_SIZE); // first die roll
             int y = randomNumbers.nextInt(GRID_SIZE);
             random_file = random_file + ( x + "," + y +"x");             
         }
        random_file = random_file + "-1,-1";
        return random_file.split("x");
    }
        
    public static boolean EnterQuestionLoop()
    {     
        boolean finished_game = false;                   
        try{
            String choice = MainMenuPrompt();
            if(EvaluateMainMenuChoice( choice ))  //Passing MMP method into MMC method as parameter => this will start MMP first     
            {
                finished_game = false;
            }
            else
            {
                finished_game = true;
            }        
            return !finished_game;      
        } catch(Exception e){
             System.out.println("Caught grid size exception -> " + e);   
             return false;
        }  
    }
    //method to write out to file   
    public static void WriteToFile(String coordinate, String file)
    {
        try 
        {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(coordinate);  //Replace with the string you are trying to write                                                        
            out.close();
        }
        catch (IOException e)
        {
            System.out.println("Exception " + e);
        }   
    }
    //method to read in file
    public static String[] ReadFromFile(String file)
    {
        String coordinate_array = "";
        try 
        {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line = "";
            while((line = in.readLine()) != null)
            {
                if(line!="")
                {
                    if(line=="-1,-1")
                    {
                        System.out.println("Reached end of frame.");
                    }
                    else
                    {
                        coordinate_array = coordinate_array + line + "x";
                    }
                }
            }
            in.close();
        }
        catch (IOException e)
        {
            System.out.println("Exception " + e);
            
        }  
        String[] arr = coordinate_array.split("x");
        return arr;
    }    
    //This method sets up the algorithm each iteration goes through following the rules that the professor gave us
    public static boolean[][][] DoLifeIteration(boolean[][][] alive_grid)
    {
        for ( int r = 0; r < GRID_SIZE; r++ ) 
        {
            int above, below; // rows considered above and below row number r
            int left, right;  // columns considered left and right of column c
            above = r > 0 ? r-1 : GRID_SIZE-1;
            below = r < GRID_SIZE-1 ? r+1 : 0;
            for ( int c = 0; c < GRID_SIZE; c++ ) 
            {
                left =  c > 0 ? c-1 : GRID_SIZE-1;
                right = c < GRID_SIZE-1 ? c+1 : 0;
                int n = 0; // number of alive cells in the 8 neighboring cells
                if (alive_grid[above][left][0])
                    n++;
                if (alive_grid[above][c][0])
                    n++;
                if (alive_grid[above][right][0])
                    n++;
                if (alive_grid[r][left][0])
                    n++;
                if (alive_grid[r][right][0])
                    n++;
                if (alive_grid[below][left][0])
                    n++;
                if (alive_grid[below][c][0])
                    n++;
                if (alive_grid[below][right][0])
                    n++;                
                //    1) any live cell with fewer than two live neighbors dies                
                if ( n < 2)
                {
                    alive_grid[r][c][1] = false;
                }
                //    2) any live cells with two or three live neighbors lives
                if ( ( n <=3 ) && ( n >=2 ) )
                {
                     alive_grid[r][c][1] = true;   
                }
                //    3) any live cells with more than three live neighbors dies
                if ( n > 3 )
                {
                     alive_grid[r][c][1] = false;   
                }
                //    4) any dead cell with exactly three live neighbors lives
                if( !alive_grid[r][c][0] && (n == 3) )
                {
                     alive_grid[r][c][0] = true;  
                }                
                
                alive_grid[r][c][0] = alive_grid[r][c][1];
            }
        }
        return alive_grid;
    }
    
    public static String GetAliveCoordinates(boolean[][][] alive_grid)
    {
    	String current_state = "";
    	for(int i = 0; i < alive_grid.length; i++)
    	{
    		for(int j=0; j < alive_grid[0].length; j++)
    		{
          // print the coordinate to the file   
    			if(alive_grid[i][j][0])
    			{                 	
    				current_state = current_state + (i + "," + j) + "\n";        
    			}                        
    		}
    	}
    	// we are done with the live points, put -1,-1 to indicate we are done.
    	current_state = current_state + "-1,-1";         
    	//	we have built up our string, now we can dump it all into the file.
    	return current_state;
    }
    //pass the coordinates to set the color of cells of the grid, updating the grid
     public static boolean[][][] SetAliveCoordinates(boolean[][][] alive_grid, String[] values)
     {
         for(int i = 0; i < values.length; i++)
         {
             String[] coord = {};
             coord = values[i].split(",");
             boolean isNumeric1 = coord[0].chars().allMatch( Character::isDigit );
             boolean isNumeric2 = coord[1].chars().allMatch( Character::isDigit );
             if(isNumeric1){
                 int new_i = Integer.parseInt(coord[0]);
                 if(isNumeric2)
                 {
                     int new_j = Integer.parseInt(coord[1]);
                     alive_grid[new_i][new_j][0] = true;
                     timer = new Timer(80, new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							System.out.println("TIMER");
						}                    	 
                     });
                     String orig_coord = (coord[0] + "," + coord[1]);
                     String s = new_i + "," + new_j;
                     
                     System.out.println("Marking true the COORDINATE (orig): " + orig_coord + "  transformed into -> " + s);
                 }
             }        		
         }     
         return alive_grid;
     }
}