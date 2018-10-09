package com.epam.lab.utils.parsers.xlsx;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XLSXParser {

    private static final int SHEET_INDEX = 0;
    private static final int USER_STING_ARRAY_SIZE = 2;

    private static List<Object[]> usersLoginDataList;

    public static List<Object[]> parseXLSFile(File file) {
        usersLoginDataList = new ArrayList<>();
        readFile(file);
        return usersLoginDataList;
    }

    private static void readFile(File file) {
        try {
            FileInputStream excelFile = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(SHEET_INDEX);
            fillUsersLoginDataList(datatypeSheet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fillUsersLoginDataList(Sheet datatypeSheet) {
        Object[] user;
        for (Row currentRow : datatypeSheet) {
            user = new String[USER_STING_ARRAY_SIZE];
            for (Cell currentCell : currentRow)
                user[currentCell.getColumnIndex()] = currentCell.getStringCellValue();
            usersLoginDataList.add(user);
        }
    }
}