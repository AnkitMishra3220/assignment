package com.udacity.app;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * This class represent board game.
 * It prints board and check solutions for board game.
 * @author Ankit Mishra
 * @version 1.0
 */

public class BoardGame {

    private Logger log = Logger.getLogger(this.getClass().getName());

    private int rows;
    private int cols;
    private int outerSquareSize;
    private int innerSquareSize;
    private int[][] board;
    private Coordinate coordinate1;
    private Coordinate coordinate2;
    private Coordinate coordinate3;
    private Coordinate coordinate4;
    private Coordinate coordinate5;
    private Coordinate coordinate6;

    public BoardGame(int[][] board) {
        this.board = board;
        setup();
    }

    public BoardGame(){

    }

    private void setup() {
        if (board != null) {
            rows = board.length;
            cols = board[0].length;
            outerSquareSize = rows;
            innerSquareSize = getSquareRoot(outerSquareSize);
            //Setup constraints
            coordinate1 = new Coordinate(0,0);
            coordinate2 = new Coordinate(0,1);
            coordinate3 = new Coordinate(1,cols-2);
            coordinate4 = new Coordinate(rows-2,2);
            coordinate5 = new Coordinate(rows-1,0);
            coordinate6 = new Coordinate(rows-1,1);
        }

        if (rows != cols || innerSquareSize == -1) {
            throw new RuntimeException("Invalid Board !");
        }


    }

    private final int getSquareRoot(int n) {
        if (n < 0)
            return -1;

        switch ((int) (n & 0xF)) {
            case 0:
            case 1:
            case 4:
            case 9:
                int squareRoot = (int) Math.sqrt(n);

                if (squareRoot * squareRoot == n)
                    return squareRoot;
                else
                    return -1;

            default:
                return -1;
        }
    }

    public boolean checkSolution() {

        int noOfRows = getRows();
        int noOfCols = getCols();
        int boardSize = getOuterSquareSize();

        //Check square
        if (noOfCols != noOfRows)
            return false;

        //Check board is initialized
        if (board == null) {
            log.severe("Board is empty. Please set the board!");
            return false;
        }


        int innerSquareSize = getInnerSquareSize();
        //copy each row to array and check the rule
        for (int i = 0; i < boardSize; i++) {
            if (!checkRule(board[i]))
                return false;
        }

        //copy columns to and array and check the rule
        for (int j = 0; j < boardSize; j++) {
            int[] boardCol = new int[boardSize];

            for (int i = 0; i < boardSize; i++) {
                boardCol[i] = board[i][j];
            }
            if (!checkRule(boardCol))
                return false;
        }

        //check cell (1, 1) should be greater than cell (1, 2), cell (2,3) should be greater than cell (3,3)
        if(board[coordinate1.getX()][coordinate1.getY()]<=board[coordinate2.getX()][coordinate2.getY()] ||
           board[coordinate3.getX()][coordinate3.getY()]<=board[coordinate4.getX()][coordinate4.getY()] ||
           board[coordinate5.getX()][coordinate5.getY()]<=board[coordinate6.getX()][coordinate6.getY()]){
            return false;
        }

        return true;
    }

    private boolean checkRule(int[] boardPortion) {

        int boardLength = boardPortion.length;
        int[] temp = Arrays.copyOf(boardPortion, boardPortion.length);
        Arrays.sort(temp);

        for (int i = 0; i < boardLength; i++) {
            if (temp[i] != i + 1) {
                return false;
            }
        }
        return true;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getOuterSquareSize() {
        return outerSquareSize;
    }

    public int getInnerSquareSize() {
        return innerSquareSize;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public void setOuterSquareSize(int outerSquareSize) {
        this.outerSquareSize = outerSquareSize;
    }

    public void setInnerSquareSize(int innerSquareSize) {
        this.innerSquareSize = innerSquareSize;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }
}
