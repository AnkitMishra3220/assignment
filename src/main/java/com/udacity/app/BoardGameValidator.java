package com.udacity.app;

import java.util.logging.Logger;

/**
 * This class validates the given board input and validate the result as true or false.
 * @author Ankit Mishra
 * @version 1.0
 */

public class BoardGameValidator {

    private Logger log = Logger.getLogger(this.getClass().getName());

    public boolean validateSolution(int[][] board){
        BoardGame boardGame = new BoardGame(board);
        return boardGame.checkSolution();
    }

}
