package koodali.service;

import koodali.model.Student;
import koodali.repository.LeaderboardRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class HomeworkPointsExcelService {
    //1 : imports file in Excel. reads the format, iterates through each row
    // first column is studentID, and the second one is the name


  private final StudentService studentService;
    private final TeacherService teacherService;

    private final LeaderboardRepository leaderboardRepository;

    //this is a temporary map to store the newly read values
    private final Map<String,Integer> studentToPointsMap = new HashMap<>();
    @Autowired
    public HomeworkPointsExcelService(StudentService studentService, TeacherService teacherService, LeaderboardRepository leaderboardRepository) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.leaderboardRepository = leaderboardRepository;
    }


    public  void collectDatafromStudentService() {
        for (Student s : studentService.getAll()) {

            studentToPointsMap.put(s.getID(), s.getHomeworkLeaderBoardScore());
        }
    }

    public  void giveDataToStudentService() {
        for (Map.Entry<String, Integer> entry : studentToPointsMap.entrySet()) {
            String id = entry.getKey();
            int points = entry.getValue();
            studentService.findByID(id).setHomeworkLeaderBoardScore(points);
        }

    }

    public  void giveDataToLeaderboardRepo() {
        for (Map.Entry<String, Integer> entry : studentToPointsMap.entrySet()) {
            String id = entry.getKey();
            int points = entry.getValue();
           Student s = studentService.findByID(id);
           s.setHomeworkLeaderBoardScore(points);
           leaderboardRepository.save(s.getID());
        }

    }



    public  void clearPointsPerStudent() {
        studentToPointsMap.clear();
    }


    private  void printCellValue(Cell cell) {
        CellType cellType = cell.getCellType().equals(CellType.FORMULA)
                ? cell.getCachedFormulaResultType() : cell.getCellType();
        if (cellType.equals(CellType.STRING)) {
            System.out.print(cell.getStringCellValue() + " | ");
        }
        if (cellType.equals(CellType.NUMERIC)) {
            if (DateUtil.isCellDateFormatted(cell)) {
                System.out.print(cell.getDateCellValue() + " | ");
            } else {
                System.out.print(cell.getNumericCellValue() + " | ");
            }
        }
        if (cellType.equals(CellType.BOOLEAN)) {
            System.out.print(cell.getBooleanCellValue() + " | ");
        }
    }

    /**
     * basic for now. Just prints all the damn info onto sysout.
     * I mean for a placeholder and to get a general feel for it its okay
     */
    public void readFromFile(String fileLocation) throws IOException {
        //code taken from https://www.baeldung.com/java-read-dates-excel
        File file = new File(fileLocation);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Workbook sheets = new XSSFWorkbook(inputStream);
            for (Sheet sheet : sheets) {
                int firstRow = sheet.getFirstRowNum();
                int lastRow = sheet.getLastRowNum();
                for (int index = firstRow + 1; index <= lastRow; index++) {
                    Row row = sheet.getRow(index);
                    for (int cellIndex = row.getFirstCellNum(); cellIndex < row.getLastCellNum(); cellIndex++) {
                        Cell cell = row.getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        printCellValue(cell);
                    }
                }
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    /**
     * Creates a Empty template for every Teacher specific to their section
     * */

    public  void createEmptyTemplateforTeacher(TeacherService teacherService, String teacherID){
        Row header;
        CellStyle headerStyle;
        XSSFFont font;
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Homework Points for each Student per Week");
            sheet.setColumnWidth(0, 6000);
            sheet.setColumnWidth(1, 4000);

            header = sheet.createRow(0);

            headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            font = workbook.createFont();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Student ID");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Total Points");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Week 1");
        headerCell.setCellStyle(headerStyle);

    }
}
