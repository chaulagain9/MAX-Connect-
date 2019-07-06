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
public class AiPlayer {
    public GameBoard gameBoardConstructor;
    public int depth1;

    public AiPlayer(GameBoard currentGame, int depth) {
        this.gameBoardConstructor = currentGame;
        this.depth1 = depth;
    }

    //findBestplay method which finds the best play for the computer to play.
    public int findBestPlay(GameBoard currentGame) {

        int alphaValue = Integer.MIN_VALUE;
        int betaValue = Integer.MAX_VALUE;
        int playChoice = 99;

        int v = 99999999;
        for (int i = 0; i < 7; i++) {
            if (currentGame.isValidPlay(i)) {                    //if the game is the valid play do the following things
                GameBoard newGame = new GameBoard(currentGame.getGameBoard());
                newGame.playPiece(i);
                if (maxValue(newGame, alphaValue, betaValue, depth1) < v) {
                    playChoice = i;
                    v = maxValue(newGame, alphaValue, betaValue, depth1);
                }
            }
        }
        return playChoice;
    }
//implementing the minimax algorithm to found the minValue
//algorithm implemented according to the slides.
    public int maxValue(GameBoard gameBoard, int alphaValue, int betaValue, int depth) {
        if ((gameBoard.getPieceCount() <= 42) && (depth == depth1)) {
            if (gameBoard.getCurrentTurn() == 1) {
                return gameBoard.getScore(1) - gameBoard.getScore(2);
            } else if (gameBoard.getCurrentTurn() == 2) {
                return gameBoard.getScore(2) - gameBoard.getScore(1);
            }
        }
        int v = -999999999;
        for (int i = 0; i < 7; i++) {
            while (gameBoard.isValidPlay(i)) {                   //if the game is the valid play do the following things
                GameBoard newBoard = new GameBoard(gameBoard.getGameBoard());
                newBoard.playPiece(i);
                v = Math.max(v, minValue(newBoard, alphaValue, betaValue, depth - 1));
                if (v >= betaValue) {
                    return v;
                }
                alphaValue = Math.max(alphaValue, v);
            }
        }
        return v;
    }


    //implementing the minimax algorithm to found the minValue
    //algorithm implemented according to the slides.
    public int minValue(GameBoard gameBoard, int alphaValue, int betaValue, int depth) {

        if ((gameBoard.getPieceCount() <= 42) && (depth == depth1)) {
            if (gameBoard.getCurrentTurn() == 1) {
                return gameBoard.getScore(1) - gameBoard.getScore(2);
            } else if (gameBoard.getCurrentTurn() == 2) {
                return gameBoard.getScore(2) - gameBoard.getScore(1);
            }
        }
        int v = 999999999;
        for (int i = 0; i < 7; i++) {
            if (gameBoard.isValidPlay(i)) {                 //if the game is the valid play do the following things
                GameBoard newBoard = new GameBoard(gameBoard.getGameBoard());
                newBoard.playPiece(i);
                v = Math.min(v, maxValue(newBoard, alphaValue, betaValue, depth - 1));
                if (v <= alphaValue) {
                    return v;
                }
                betaValue = Math.min(betaValue, v);
            }
        }
        return v;

    }

}