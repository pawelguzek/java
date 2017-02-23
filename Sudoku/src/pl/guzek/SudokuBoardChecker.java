package pl.guzek;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Pawel on 2017-01-28.
 */
public class SudokuBoardChecker {

    private int index;

    Workbook workbook;

    public SudokuBoardChecker(Workbook workbook) {
        this.workbook = workbook;
    }


    public boolean verifyBoardStructure(int sheetIndex){
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        boolean flag = false;
        outerloop:
        for(int i = 0; i<9; i++){
            Row row = sheet.getRow(i);
            for(int j = 0; j<9; j++){
                Cell cell = row.getCell(j);
                @SuppressWarnings("deprecation")
                CellType cellType = cell.getCellTypeEnum();
                switch (cellType) {
                    case BLANK: {

                        flag = true;
                        break;
                    }
                    case NUMERIC: {
                        if(cell.getNumericCellValue() >0 && cell.getNumericCellValue()<10){
                            flag = true;
                            break;
                        }
                        else{
                            flag = false;
                            break outerloop;
                        }
                    }
                    default:{
                            flag = false;
                            break outerloop;
                        }
                }
            }
        }

        return flag;
    }

    public boolean verifyBoardSemantically(int sheetIndex){
        boolean flag = true;
        Sheet sheet = workbook.getSheetAt(sheetIndex);


        for(int i=0; i<9; i++){
            ArrayList<Integer> list1 = new ArrayList<Integer>();
            Row row = sheet.getRow(i);
            for(int j=0; j<9; j++){
                Cell cell = row.getCell(j);
                list1.add((int)cell.getNumericCellValue());
            }
            if (list1.contains(0)){
                while(list1.contains(0)){
                    list1.remove(list1.indexOf(0));

                }
            }
            Set<Integer> set1 = new HashSet<Integer>(list1);
            if(set1.size() != list1.size()){
                flag = false;
            }

        }

        for(int k=0; k<9; k++){
            ArrayList<Integer> list2 = new ArrayList<Integer>();
            for(int l=0; l<9; l++){
                Row row = sheet.getRow(l);
                Cell cell = row.getCell(k);
                list2.add((int)cell.getNumericCellValue());
            }
            if (list2.contains(0)){
                while(list2.contains(0)){
                    list2.remove(list2.indexOf(0));

                }
            }

            Set<Integer> set2 = new HashSet<Integer>(list2);
            if(set2.size() != list2.size()) {
                flag = false;
            }

        }

        return flag;

    }






}
