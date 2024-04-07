/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
//Java Project - Guess the Number (Mystery Number Challenge, Guess the Secret Number)
import java.util.*;
public class Main{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome to   Guess the Number   Game!! ~ by Sonali");
        System.out.print("\nEnter your name : ");
        String name=sc.nextLine();
        
        boolean playAgain=true;
        int round=0;
        int point=0;
        int score=0;
        
        while(playAgain){
            round++;
            System.out.println(name +" Can you guess the mystery number within the given chances??");
            
            System.out.println("It's your round number "+round);
            System.out.println("Let's play...");
        
            int range=100;
            System.out.println(); 
            System.out.println("Level Easy - Number of changes is 10\nLevel Moderate - Nuber of chances is 7\nLevel Hard - Number of chanes is 5");
            int chance=0;
            System.out.println(); 
            System.out.println("Enter the difficulty level - (Easy, Moderate, Hard)");
            String level=sc.next();
            System.out.println();
        
            if(level.equalsIgnoreCase("Easy")){
                chance=10;
                System.out.println(name + " You have only 10 chances ");
                System.out.println("Guess the number in the range of (0 - 100)");  //(0(inclusive) - 100(exclusive))
            }
            else if(level.equalsIgnoreCase("Moderate")){
                chance=7;
                System.out.println(name + " You have only 7 chances ");
                System.out.println("Guess the number in the range of (0 - 100)"); //(0(inclusive) - 100(exclusive))
            }
            else if(level.equalsIgnoreCase("Hard")){
                chance=5;
                System.out.println(name + " You have only 5 chances ");
                System.out.println("Guess the number in the range of (0 - 100)"); //(0(inclusive) - 100(exclusive))
            }
        
            //Generating a random number by creating an instance of Random class
            Random rand=new Random();
            int myNumber=rand.nextInt(range);  //generates a random integer between the range "between 0 (inclusive) and range(exclusive)"
        
            int userNumber=sc.nextInt();
            int attempt=0;
            while(userNumber != myNumber){
                attempt++;
                if(attempt == chance){
                    System.out.println("Over...Sorry!! "+ name + " You have lost the game \nThe number was : "+ myNumber);
                    //Calculating points
                    point=chance - attempt;
                    System.out.println(name + " you took total allotted attempts i.e "+attempt);
                    System.out.println("your point is "+point);
                    break;
                }
            
                if(userNumber < myNumber){
                    System.out.println(name + " Your number is too small...Plzz guess a larger number");
                }
                else if(userNumber > myNumber){
                    System.out.println(name + " Your number is too large...Plzz guess a smaller number");
                }
                if(attempt == chance-1){  
                    System.out.println("it's your last chance...");
                }
                System.out.println("Again guess a number : ");
                userNumber = sc.nextInt();
            }
            
            System.out.println();
            if(userNumber == myNumber){
                System.out.println("WOOHOOO!!...You have guessed the correct number");
                System.out.println("Congratulation!! You won the "+ level + " level");
            }
            
            //Giving points based on the number of attempts
            if((attempt+1) == 1){  //if at the first attempt we guess the correct number
                point=10;
                System.out.println(name + " you took "+ (attempt+1) +" attempt");
                System.out.println("your point is "+point);
            }
            else if(attempt != chance){
                if((attempt+1) != chance){
                    point=10 - (attempt+1);
                    System.out.println(name + " you took "+ (attempt+1) +" attempts");
                    System.out.println("your point is "+point);
                }
            }
            
            System.out.println();
            if(point<=0){
                score=0;
            }
            else{
                score=score+point;
            }
            System.out.println("\n"+name + " do you want to play again?? (Yes/No)");
            String playChoice=sc.next();
            if(playChoice.equalsIgnoreCase("No")){
                playAgain=false;
            }
            sc.nextLine();  //consume newline character left in the buffer
        }
        //Displaying the total score
        System.out.println();
        System.out.println("Hey "+ name +" you have played "+round+" round/rounds");
        System.out.println("Your total score for    Guess the Number   game is "+score);
    }
}