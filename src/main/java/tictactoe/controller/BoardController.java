package tictactoe.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import tictactoe.connection.*;
import tictactoe.model.Board;
import tictactoe.view.BoardFrame;

public class BoardController {
    private Connection connection;
    private final BoardFrame frame;
    private final Board board;
    
    public BoardController(Board board,BoardFrame frame) {
        this.board = board;
        this.frame = frame;
        this.frame.setVisible(true);
        initializeListener();
    }
    
    private void initializeListener() {
        frame.addClientButonListener(new ClientButtonListener());
        frame.addServerButonListener(new ServerButtonListener());
        frame.addSquareButonListener(new SquareButtonListener());
        frame.addResetButonListener(new ResetButtonlistener());
    }
    
    public void resetTheGame() {
        MessageReceiver receive;
        board.startNewGame();
        frame.updateGameBoard(board.getGameBoard());
        if(board.getPlayerMove() == 'X')
            frame.setPlayerLabel("PLAYER 1");
        else if(board.getPlayerMove() == 'O') {
            frame.setPlayerLabel("PLAYER 2");
            receive = new MessageReceiver();
            receive.setName("Listening..");
            receive.run();
        }
    }
    
    public boolean checkCurrentRunningThread() {
        Set<Thread> threads = Thread.getAllStackTraces().keySet();
        for(Thread t : threads) {
            System.out.println(t.getName());
            if(t.getName().equals("Listening : " + board.getPlayerMove())){
                System.out.println("Current running thread : " + t.getName());
                return false;
            }     
        }
        return true;
    }
    
    public void winnerLoserCheck() {
        board.checkGameCondition();
        if(board.isGameHasCompleted()){
            switch(board.getIsWin()) {
                case 1:
                    frame.setPlayerLabel("YOU ARE THE WINNER");
                    break;
                case -1:
                    frame.setPlayerLabel("YOU ARE THE LOSER");
                    break;
                default:
                    frame.setPlayerLabel("IT IS A TIE");
                    break;
            }
        }
    }
    
    private class ServerConnectionStarter extends Thread {
        @Override
        public void run() {
            try {
                frame.setConnectionLabel("Waiting for client..");
                connection.echoConnection();
                Thread.sleep(300);
                frame.setConnectionLabel("Connected to client.");
            } catch (InterruptedException ex) {
                Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private class ClientConnectionStarter extends Thread {
        @Override
        public void run() {
            try {
                frame.setConnectionLabel("Connecting to server..");
                connection.echoConnection();
                Thread.sleep(300);
                frame.setConnectionLabel("Connected to server.");
            } catch (InterruptedException ex) {
                Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private class MessageSender extends Thread {
        @Override
        public void run() {
            connection.sendMessage(board.getGameBoard());
            winnerLoserCheck();
        }
    }
    
    private class MessageReceiver extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(100);
                board.setGameBoard(connection.listenMessage());
                if(board.getGameBoard()[0][3] == 'R') {
                    resetTheGame();
                }
                frame.updateGameBoard(board.getGameBoard());
                board.setCanMove(true);
                winnerLoserCheck();
            } catch (InterruptedException ex) {
                Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private class ResetSignalSender extends Thread {
        @Override
        public void run() {
            board.addResetSignal();
            connection.sendMessage(board.getGameBoard());
            resetTheGame();
        }
    }
    
    private class ServerButtonListener implements ActionListener {
        ServerConnectionStarter t1 = new ServerConnectionStarter();
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            connection = new Server();
            t1.setName("Server Thread");
            t1.start();
            board.setFirstPlayer();
            frame.disableClientButton();
            frame.disableServerButton();
            frame.setPlayerLabel("PLAYER 1");
        }
    }
    
    private class ClientButtonListener implements ActionListener {
        ClientConnectionStarter t1 = new ClientConnectionStarter();
        MessageReceiver receive;
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            connection = new Client();
            t1.setName("Client Thread");            
            t1.start();
            board.setSecondPlayer();
            frame.disableClientButton();
            frame.disableServerButton();
            frame.setPlayerLabel("PLAYER 2");
            receive = new MessageReceiver();
            receive.setName("Listening : " + board.getPlayerMove());
            receive.start();
        }
    }
    
    private class SquareButtonListener implements ActionListener {
        MessageSender send;
        MessageReceiver receive;
        JButton[][] temp = frame.getSquareButton();
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            if(board.isCanMove()) {
                int row = 1;
                int column = 0;
                JButton square = (JButton) ae.getSource();
                for(int i = 0; i < 3; i++) {
                    for(int j = 0; j < 3; j++) {
                        if(temp[i][j] == square) {
                            row = i;
                            column = j;
                            break;
                        }
                    }
                }
                board.createMove(row, column);
                frame.updateGameBoard(board.getGameBoard());
                send = new MessageSender();
                send.setName("Sending..");
                send.start();
                if(board.isGameHasCompleted() == false) {
                    receive = new MessageReceiver();
                    receive.setName("Listening : " + board.getPlayerMove());
                    receive.start();
                }
            } else
                frame.showCustomDialogBox("It is not your turn.");
        } 
    }
    
    private class ResetButtonlistener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(checkCurrentRunningThread()) {
                ResetSignalSender t1 = new ResetSignalSender();
                t1.start();
            } else
                frame.showCustomDialogBox("You can only reset the game when it is your turn.");
        }
    }
}
