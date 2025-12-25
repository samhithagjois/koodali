package koodali.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomeworkPointsExcelService {
    //1 : imports file in Excel. reads the format, iterates through each row
    // first column is studentID, and the second one is the name
    //

    public static void readFromFile(String fileLocation) throws IOException {
        //code taken from https://www.baeldung.com/java-microsoft-excel
        FileInputStream file = new FileInputStream(fileLocation);
        Workbook workbook = new HSSFWorkbook(file);

        Sheet sheet = workbook.getSheetAt(0);

        Map<Integer, List<String>> data = new HashMap<>();
        int i = 0;
        for (Row row : sheet) {
            data.put(i, new ArrayList<String>());
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING: break;
                    case NUMERIC:  break;
                    case BOOLEAN:  break;
                    case FORMULA: break;
                    default: data.get(i).add(" ");
                }
            }
            i++;
        }

    }
}
