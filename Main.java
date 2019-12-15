import java.util.*;

public class Main 
{
	    Scanner sc = new Scanner(System.in);
	    Random rand = new Random();

	    int battlegroundPaintCounter = 0;

	    String[][] x = new String[12][14];
        String space = " ";
        String boundary = "|";
        int number = 0;

        int [][] userChoice = new int[5][2];
        int [][] computerChoice = new int[5][2];

        int rowLowerLimit = 0;
        int rowUpperLimit = 10;

        int columnLowerLimit = 0;
        int columnUpperLimit = 10;

        
// ---------- Validating user ships position--------------------

        int acessRowLimit(int z)
        {
          if(z >= rowLowerLimit && z < rowUpperLimit)
          {
          	return z;
          }
          else
          {
          	System.out.println("Invalid Y coordinate Enter again");
          	return acessRowLimit(sc.nextInt());
          }
        }

        int acessColumnLimit(int z)
        {
          if(z >= columnLowerLimit && z < columnUpperLimit)
          {
          	return z;
          }
          else
          {
          	System.out.println("Invalid X coordinate Enter again");
          	return acessColumnLimit(sc.nextInt());
          }
        }
       
   //---------------- Getting Coordinates values from the user---------------------------------

        void getUserChoice()  
        {
        	for(int i = 0;i<userChoice.length;i++)
        	{
        		for (int j = 0;j<userChoice[i].length;j++) {

        		   if(j == 0)
        		   {
        		   	 System.out.println("Enter Y cordinate of your "+(i+1)+" ship");
        			 userChoice[i][j] = acessRowLimit(sc.nextInt());
        		   }
        		   else
        		   {
        		   	System.out.println("Enter X cordinate of your "+(i+1)+" ship");
        			userChoice[i][j] = acessColumnLimit(sc.nextInt()); 
        		   }

        		  // userChoice[i][j] = i+1;
        		}

        		
        	}
        }

 //----------------- Storing user ships in the battleground----------------------------

        void storeInBattleground()
        {
          int r = 0;
          int c = 0;
          for (int i = 0;i<userChoice.length ;i++ ) 
          {
        		for (int j = 0;j<userChoice[i].length ;j++ ) 
        		{
        			if(j == 0)
        			{
        				r = userChoice[i][j] + 1;
        			}
        			else
        			{
        				c = userChoice[i][j] + 2;
        			}
        		}

        		x[r][c] = "@";
          }

        } 

//---------------Storing Computer ships in the battleground-------------------------------

        void getComputerChoice()  
        {
        	for(int i = 0;i<computerChoice.length;i++)
        	{
        		for (int j = 0;j<computerChoice[i].length;j++) {

        		   if(j == 0)
        		   {
        			 computerChoice[i][j] = validateComputerRowChoice(rand.nextInt(9));
        		   }
        		   else
        		   {
        			computerChoice[i][j] = validateComputerColumnChoice(rand.nextInt(9)); 
        		   }

        		 
        		}

        		
        	}
        }

      void storeComputerInBattleground()
        {
          int r = 0;
          int c = 0;
          System.out.println("");
          System.out.println("----------Computer is deploying ships----------");
          System.out.println("");
          for (int i = 0;i<computerChoice.length ;i++ ) 
          {
        		for (int j = 0;j<computerChoice[i].length ;j++ ) 
        		{
        			if(j == 0)
        			{
        				r = computerChoice[i][j] + 1;
        			}
        			else
        			{
        				c = computerChoice[i][j] + 2;
        			}
        		}

        		if(placeHolder(r,c))
        		{
        			System.out.println("Computer deployed ship number "+(i+1));
        		}
          }

        }

    //-----------Validating Computer Ships Position ----------------------    

        boolean placeHolder(int a, int b)
        {
        	if(x[a][b] != "@" && x[a][b] != "*")
        	{
        		x[a][b] = "*";
        		return true;
        	}
        	else
        	{
        		return placeHolder(validateComputerRowChoice(rand.nextInt(9)) + 1, validateComputerColumnChoice(rand.nextInt(9)) + 2);
        	}
        } 

        int validateComputerRowChoice(int z)
        {
          if(z >= rowLowerLimit && z < rowUpperLimit)
          {
          	return z;
          }
          else
          {
          	return validateComputerRowChoice(rand.nextInt(9));
          }
        }

        int validateComputerColumnChoice(int z)
        {
          if(z >= columnLowerLimit && z < columnUpperLimit)
          {
          	return z;
          }
          else
          {
          	return validateComputerColumnChoice(rand.nextInt(9));
          }
        }

// --------------- Ist Step Creating Battleground-----------------------------------
        void createBattleGround()
        {
            for (int i = 0;i<x.length ;i++ ) 
            {
             	for (int j = 0;j<x[i].length ;j++ )
                {

            	    if(i == 0 || i == 11)
            	    {
            		  if((j == 0 || j==1) || (j == 12 || j == 13) )
            		  {
            			x[i][j] = space; // giving space
            		  }
            		  else
            		  {
            			number = j-2;
            			x[i][j] = Integer.toString(number);
            		  }

            	    }
            	    else
            	    {
            		  if(j > 1 && j<12)
            		  {
            			//System.out.print(space+" ");
            			x[i][j] = space;
            		  }
            		  else
            		  {
            			if(j==1 || j==12)
            			{
            				//System.out.print(boundary+" ");
            				x[i][j] = boundary; //giving boundary
            			}
            			else
            			{
            				//System.out.print(i-1+" ");
            				number = i-1;
            				x[i][j] = Integer.toString(number); // giving row numbers
            			}
            		  }
                 	}
        		
        	    }

        	    System.out.println("");
        	
            }

        }

   //-----------------Displaying Battleground-----------------------------     

        void displayBattleGround()
        {
        	   if(battlegroundPaintCounter == 0)
        	   {
        	   	   System.out.println("*********** Welcome to battleship games ***********");
        	       System.out.println("");
        	   	   System.out.println("Right now the sea is empty");
        	   }
               
               System.out.println();

               for(int i = 0;i<x.length;i++)
    	        {
                  for(int j = 0;j<x[i].length;j++)
                  {
    
                   /* if(x[i][j] == "*")
                    {
                      System.out.print(space+" ");
                    }*/
                 //   else
                  //  {
                    	System.out.print(x[i][j]+" ");
                   // }
                  	
                  }
                  System.out.println("");
    	        }

    	        battlegroundPaintCounter+=1;
    	        System.out.println("");
        }

    // ----------------GAME STARTS ----------------------------
    
        
    static int userShips = 5;
    static int computerShips = 5;
    int [][] deployUser = new int[1][2];
    int [][] deployComputer = new int[1][2];

    //------------User Deploys Ships-----------------
    void checkPosition(int a, int b)
    {
       if(x[a][b] == "@")
       {
       	System.out.println("Ohh ! You sunk your own ship");
       	userShips = userShips-1;
       	x[a][b] = "O";
       }
       else if (x[a][b] == "*") {
       	System.out.println("Boom ! you sunk the enemy ship");
       	computerShips = computerShips-1;
        x[a][b] = "!";
       }
       else if(x[a][b] == "0" || x[a][b] == "!" || x[a][b] == "X")
       {
         System.out.println(" Invalid Entry --- Enter 'Y' coordinate Again");
         deployUser[0][0] = acessRowLimit(sc.nextInt());
         System.out.println("Invalid Entry ---- Enter 'X' coordinate Again");
         deployUser[0][1] = acessColumnLimit(sc.nextInt());
         checkPosition(deployUser[0][0]+1,deployUser[0][1]+2);  
       }
       else
       {
       	System.out.println("Ohh You missed !");
       	x[a][b] = "X";
       }
       displayBattleGround();
       System.out.println("");
      	System.out.print("Your Ships = "+userShips+" Computer Ships = "+computerShips);
       System.out.println("");
    }

    //------------Computer Deploys Ships---------------

    void computerTurn(int a, int b)
    {
      if(x[a][b] == "@")
      {
      	userShips = userShips-1;
      	System.out.println("Computer hit you");
      	x[a][b] = "0";
      }
      else if(x[a][b] == "*")
      {
        computerShips = computerShips-1;
        System.out.println("Computer hit itself");
        x[a][b] = "X";
      }
      else if(x[a][b] == "0" || x[a][b] == "!" || x[a][b] == "X")
      {
      	 deployComputer[0][0] = validateComputerRowChoice(rand.nextInt(9));
         deployComputer[0][1] = validateComputerColumnChoice(rand.nextInt(9));

         computerTurn(deployComputer[0][0]+1,deployComputer[0][1]+2);
      }
      else
      {
      	System.out.println("Computer missed !");
      	x[a][b] = "X";
      }

      displayBattleGround();
      System.out.println();
      
      if(userShips == 0 || computerShips == 0)
      {
      	System.out.println("########## GAME OVER ##########");
      	 System.out.print("Your Ships = "+userShips+" Computer Ships = "+computerShips);
      }
      else
      {
      	System.out.print("Your Ships = "+userShips+" Computer Ships = "+computerShips);
      }
      System.out.println("");
    }


    void gameStart()
    {

       System.out.println("Your Turn");
       System.out.println("Enter 'Y' coordinate  to deploy your ship");
       deployUser[0][0] = acessRowLimit(sc.nextInt());
       System.out.println("Enter 'X' coordinate to deploy your ship");
       deployUser[0][1] = acessColumnLimit(sc.nextInt());
       checkPosition(deployUser[0][0]+1,deployUser[0][1]+2);

       System.out.println("Computer Turn");
       deployComputer[0][0] = validateComputerRowChoice(rand.nextInt(9));
       deployComputer[0][1] = validateComputerColumnChoice(rand.nextInt(9));

       computerTurn(deployComputer[0][0]+1,deployComputer[0][1]+2);

    }    


    public static void main(String[] args)
     {
        Main obj = new Main();

        obj.createBattleGround();   // creating battleground

        obj.displayBattleGround();  // displaying empty battleground

        obj.getUserChoice();        // Getting user Coordinates

        obj.storeInBattleground(); // Stroring user coordinates in the battleground

        obj.getComputerChoice();   // storing computer generated coordinates in the battleground

        obj.storeComputerInBattleground();

        obj.displayBattleGround();  // displaying battleground again
        
        do
        {
           if(Main.userShips == 0 || Main.computerShips == 0)
           {
           	break;
           }else
           {
           	obj.gameStart();
           }
           
        }while(Main.userShips!=0 || Main.computerShips!=0);

        

     }   
  
}   