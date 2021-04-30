package com.udacity.app;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.logging.Logger;

import java.io.*;
import java.util.ArrayList;

public class BoardGameValidatorTest {

    private Logger log = Logger.getLogger(this.getClass().getName());
    private int[][] input;
    private String inputFile;
    private DataInputStream in;

  @Before
  public void setUp(){

     // String inputFile = "src/test/resources/input/input_invalid_4X4.txt";
     //String inputFile = "src/test/resources/input/input_invalid_9X9.txt";

     String inputFile = "src/test/resources/input/input_valid_4X4.txt";

      try {
          File file = new File(inputFile);
          if (!file.exists()) {
              log.severe("The give file doesn't exists");
              System.exit(1);
          }

          FileInputStream fstream = new FileInputStream(inputFile);
          in = new DataInputStream(fstream);
          BufferedReader br = new BufferedReader(new InputStreamReader(in));
          String strLine;

          int noOfRows = 0;
          ArrayList<String> strRowList = new ArrayList<String>();

          while ((strLine = br.readLine()) != null) {
              strRowList.add(strLine);
          }

          noOfRows = strRowList.size();

          int noOfCols = 0;

          for (int i = 0; i < noOfRows; i++) {
              String row = strRowList.get(i);
              String[] strCols = row.split(",");
              noOfCols = strCols.length;

              //validate given input
              if (noOfCols != noOfRows) {
                  log.severe("File error:No of rows should match the no of columns.");
                  System.exit(1);
              }

              if (i == 0)
                  input = new int[noOfRows][noOfCols];

              for (int j = 0; j < strCols.length; j++) {
                  input[i][j] = Integer.parseInt(strCols[j]);
              }

          }

      } catch (IOException e) {
          System.out.print("Problem in the input File.");
      } catch (Exception ex) {
          log.severe("Problem in input File parsing." + ex.getMessage());
      } finally {
          try {
              in.close();
          } catch (IOException ioe) {
              log.severe("io Exception during close!");
          }
      }

  }

  @Test
  public void testInput(){
      Assert.assertEquals(1,input[0][0]);
      Assert.assertEquals(4,input[0][1]);
      Assert.assertEquals(3,input[3][0]);
      Assert.assertEquals(2,input[2][1]);
      Assert.assertEquals(1,input[1][2]);
  }


  @Test
  public void testSolution(){
      BoardGameValidator boardGameValidator = new BoardGameValidator();
      boolean isCorrect = validateSolution(input,boardGameValidator);
      log.info("************************************************");
      if(isCorrect){
          log.info("***** The given Board is a valid solution ******");
      }else {
          log.info("***** The given Board is not a valid solution ******");
      }

  }


  public boolean validateSolution(int[][] input,BoardGameValidator boardGameValidator){
      return boardGameValidator.validateSolution(input);
    }

}
