package tictactoe.model;

public class Board {
    private char[][] gameBoard = new char[3][4];
    private char playerMove;
    private char opponentMove;
    private int numOfMoves;
    private boolean gameHasCompleted;
    private int isWin = 0;
    private boolean canMove;

    public void startNewGame() {
        numOfMoves = 0;
        isWin = 0;
        gameHasCompleted = false;
        resetGameBoard();
        checkPlayerMovement();
    }
    
    public void checkPlayerMovement(){
        if(playerMove == 'X')
            canMove = true;
        if(playerMove == 'O')
            canMove = false;
    }
    
    public void resetGameBoard() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 4; j++)
                gameBoard[i][j] = ' ';
        }
    }
    
    public void addResetSignal() {
        gameBoard[0][3] = 'R';
    }
    
    public boolean createMove(int row, int column) {
        if(canMove || !checkSquare(row, column)) {
            gameBoard[row][column] = playerMove;
            canMove = false;
            return true;
        }
        return false;
    }
    
    public boolean checkSquare(int row, int column) {
        return gameBoard[row][column] != ' ';
    }
    
    public void checkMoveCount() {
        numOfMoves = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(gameBoard[i][j] != ' ')
                    numOfMoves++;
            }
        }
        if(numOfMoves == 9)
            gameHasCompleted = true;
    }
    
    public boolean rowWin() {
        for(int i = 0; i < 3; i++) {
            int playerCount = 0, opponentCount = 0;
            for(int j = 0; j < 3; j++) {
                if(gameBoard[i][j] == playerMove)
                    ++playerCount;
                if(gameBoard[i][j] == opponentMove)
                    ++opponentCount;
            }
            return checkPlayerCount(playerCount, opponentCount);
        }
        return false;
    }
    
    public boolean columnWin() {
        for(int i = 0; i < 3; i++) {
            int playerCount = 0, opponentCount = 0;
            for (int j = 0; j < 3; j++) {
                if(gameBoard[j][i] == playerMove)
                    ++playerCount;
                if(gameBoard[j][i] == opponentMove)
                    ++opponentCount;
            }
            return checkPlayerCount(playerCount, opponentCount);
        }
        return false;
    }
    
    public boolean checkPlayerCount(int playerCount, int opponentCount){
        if(playerCount == 3 || opponentCount == 3) {
            if(playerCount == 3)
                isWin = 1;
            if(opponentCount == 3)
                isWin = -1;
            return true;
        }
        return false;
    }
    
    public boolean diagonalWin() {
         if(gameBoard[0][0] == playerMove && gameBoard[1][1] == playerMove && gameBoard[2][2] == playerMove) {
            isWin = 1;
            return true;
        } else if(gameBoard[2][0] == playerMove && gameBoard[1][1] == playerMove && gameBoard[0][2] == playerMove) {
            isWin = 1;
            return true;
        } else if(gameBoard[0][0] == opponentMove && gameBoard[1][1] == opponentMove && gameBoard[2][2] == opponentMove) {
            isWin = -1;
            return true;
        } else if(gameBoard[2][0] == opponentMove && gameBoard[1][1] == opponentMove && gameBoard[0][2] == opponentMove) {
            isWin = -1;
            return true;
        } else {
            return false;
        }
    }
    
    public void performWinCheck() {
        if(rowWin() || columnWin() || diagonalWin())
            gameHasCompleted = true;
    }
    
    public void checkGameCondition() {
        performWinCheck();
        checkMoveCount();
        if (gameHasCompleted) {
            canMove = false;
        }
    }
    
    public char[][] getGameBoard() {
        return gameBoard;
    }

    public char getPlayerMove() {
        return playerMove;
    }

    public int getNumOfMoves() {
        return numOfMoves;
    }

    public boolean isGameHasCompleted() {
        return gameHasCompleted;
    }

    public int getIsWin() {
        return isWin;
    }

    public boolean isCanMove() {
        return canMove;
    }
    
    public void setGameBoard(char[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
    
    public void setFirstPlayer() { 
        playerMove = 'X'; 
        opponentMove = 'O';
        resetGameBoard();
        canMove = true;
    }
    
    public void setSecondPlayer() { 
        playerMove = 'O'; 
        opponentMove = 'X';
        resetGameBoard();
        canMove = false;
    }
}
