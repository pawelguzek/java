package pl.guzek;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Pawel on 2017-01-28.
 */
public class App {

    public static void main (String[] args){

        try {
            Workbook workbook = WorkbookFactory.create(new File("sudoku.xlsx"));
            SudokuBoardChecker sCheck = new SudokuBoardChecker(workbook);


            for(int i=0; i<workbook.getNumberOfSheets(); i++){

              if (sCheck.verifyBoardStructure(i)){
                System.out.print("plansza nr " + (i+1) + " jest poprawna syntaktycznie");

                  if(sCheck.verifyBoardSemantically(i)){
                      System.out.println(" i semantycznie");
                  }
                  else{
                      System.out.println(" lecz nie jest poprawna semantycznie");
                  }
            }
            else{
                System.out.println("plansza nr " + (i+1)+ " nie jest poprawna syntaktycznie i nie jest poprawna semantycznie");
            }



            }

         //   System.out.println(value);
        } catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
            e.printStackTrace();
        }




    }


}
