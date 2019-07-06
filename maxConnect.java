
/*
*********************************************************************************************************** 
 ***********************************************************************************************************  
                                        
                                        Name: Sameer Chaulagain
                                        UTA ID: 1001418268
                                        CSE 4308-001
                                        Assignment 04
*********************************************************************************************************** 
*********************************************************************************************************** 
*/
import java.util.Scanner;
import java.util.*;
import java.io.*;

public class maxConnect {

    public static void main(String[] args) {
        // check for the correct number of arguments
        if (args.length != 4) {
            System.out.println("Four command-line arguments are needed:\n"
                    + "Usage: java [program name] interactive [input_file] [computer-next / human-next] [depth]\n"
                    + " or:  java [program name] one-move [input_file] [output_file] [depth]\n");

            exit_function(0);
        }
        // parse the input arguments
        String game_mode = args[0].toString(); // the game mode
        String inputFile = args[1].toString(); // the input game file
        int depthLevel = Integer.parseInt(args[3]); // the depth level of the ai search

        // create and initialize the game board
        GameBoard currentGame = new GameBoard(inputFile);

        // create the Ai Player
        AiPlayer ArtiPlayer = new AiPlayer(currentGame, depthLevel);

        if (game_mode.equalsIgnoreCase("interactive") && args[2].toString().equalsIgnoreCase("computer-next")) {
            int current_player = currentGame.getCurrentTurn();
            System.out.println("\n          #############################################");
            System.out.println("        ################    Connect-Four     ##################");
            System.out.println("        ################     COMPUTER First   #################");
            System.out.println("            ##############################################\n");
            System.out.println("Computer's Play: 1");
            System.out.println("Human's Play:  2\n");
            System.out.println("Initial State\n");

            currentGame.printGameBoard();
            while (currentGame.getPieceCount() < 42) {
                System.out.println("Score: Player 1 = " + currentGame.getScore(1) + ", Player2 = "
                        + currentGame.getScore(2) + "\n ");

                if (currentGame.getPieceCount() > 42) {
                    System.out.println("Score: Player 1 = " + currentGame.getScore(1) + ", Player2 = "
                    + currentGame.getScore(2) + "\n ");
                }
                int playColumn = 99; // the players choice of column to play
                playColumn = ArtiPlayer.findBestPlay(currentGame);
                // play the piece
                currentGame.playPiece(playColumn);
                System.out.println("********** Computer's Play. (AI) ********** \n");
                System.out.println("move: " + currentGame.getPieceCount() + " , Player: " + current_player
                        + ", Column: " + (playColumn + 1));
                currentGame.printGameBoardToFile("computer.txt");
                currentGame.printGameBoard();
                System.out.println("Score: Player 1 = " + currentGame.getScore(1) + ", Player 2 = "
                        + currentGame.getScore(2) + "\n ");

                Scanner input = new Scanner(System.in);
                System.out.print("       HUMAN'S TURN\nEnter in the COLUMN 1 to 7 :  ");
                playColumn = input.nextInt();
                if(currentGame.isValidPlay(playColumn)){
                    // play the piece
                currentGame.playPiece(playColumn - 1);
                currentGame.printGameBoardToFile("human.txt");
                currentGame.printGameBoard();
                }
                else{
                    System.out.println("The column is full. Please choose another column");
                }
    
            
                
            }
        }

        else if (game_mode.equalsIgnoreCase("interactive") && args[2].toString().equalsIgnoreCase("human-next")) {
            System.out.println("\n          #############################################");
            System.out.println("        ################    Connect-Four     ##################");
            System.out.println("        ################     HUMAN First      #################");
            System.out.println("            ##############################################\n");
            System.out.println("Human's Play: 1");
            System.out.println("Computer's Play:  2\n");
            System.out.println("Initial State\n");

            currentGame.printGameBoard();

            while (currentGame.getPieceCount() < 42) {

                if (currentGame.getPieceCount() > 42) {
                    System.out.println("Score: Player 1 = " + currentGame.getScore(1) + ", Player2 = "
                    + currentGame.getScore(2) + "\n ");
                }

                System.out.println("Score: Player 1 = " + currentGame.getScore(1) + ", Player 2 = "
                        + currentGame.getScore(2) + "\n ");

                int playColumn = 99;
                Scanner input = new Scanner(System.in);
                System.out.print("Human's Turn. Please enter number between 1-7 :");
                playColumn = input.nextInt();
                
                if(currentGame.isValidPlay(playColumn)){
                    // play the piece
                currentGame.playPiece(playColumn - 1);
                currentGame.printGameBoardToFile("human.txt");
                currentGame.printGameBoard();
                }
                else{
                    System.out.println("The column is full.");
                }

                int current_player1 = currentGame.getCurrentTurn();
                System.out.println("********** Computer's Play. (AI) ********** \n");
                System.out.println("Score: Player 1 = " + currentGame.getScore(1) + ", Player 2 = "
                        + currentGame.getScore(2) + "\n ");

                playColumn = ArtiPlayer.findBestPlay(currentGame);
                // play the piece
                currentGame.playPiece(playColumn);
                System.out.println("AI's MOVE");

                currentGame.printGameBoard();
                currentGame.printGameBoardToFile("computer.txt");
                System.out.println("move: " + currentGame.getPieceCount() + " , Player: " + current_player1
                        + ", Column: " + (playColumn + 1));
            }
        }

        else if (game_mode.equalsIgnoreCase("one-move")) {
            currentGame.printGameBoard();
            if (currentGame.getPieceCount() > 42) {
                exit_function(0);
            }

            System.out.println(
                    "Score: Player 1 = " + currentGame.getScore(1) + ", Player 2 = " + currentGame.getScore(2) + "\n ");

            System.out.print("Please make a move. Enter 1-7 in any column: ");
            Scanner input1 = new Scanner(System.in);
            int number = input1.nextInt();

            currentGame.playPiece(number - 1);
            currentGame.printGameBoard();
            System.out.println(
                    "Score: Player 1 = " + currentGame.getScore(1) + ", Player 2 = " + currentGame.getScore(2) + "\n ");

            currentGame.printGameBoardToFile(args[2]);
            if (currentGame.getPieceCount() > 42) {
                exit_function(0);
            }
        }

    } // end of main()

    /**
     * This method is used when to exit the program prematurely.
     * 
     * @param value an integer that is returned to the system when the program
     *              exits.
     */
    private static void exit_function(int value) {
        System.out.println("exiting from MaxConnectFour.java!\n\n");
        System.exit(value);
    }
} // end of class connectFour
