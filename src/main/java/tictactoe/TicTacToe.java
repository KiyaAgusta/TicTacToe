package tictactoe;

import tictactoe.controller.BoardController;
import tictactoe.model.Board;
import tictactoe.view.BoardFrame;

public class TicTacToe {
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new BoardController(new Board(), new BoardFrame());
        }
    }
}
