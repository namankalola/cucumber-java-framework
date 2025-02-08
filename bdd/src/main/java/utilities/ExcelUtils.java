package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class ExcelUtils {

    private static final Logger logger = Logger.getLogger(ExcelUtils.class.getName());
    private static final String FILE_PATH = "src/test/resources/testdata/"; // Excel file path
    private static final ThreadLocal<ExcelUtils> threadLocalInstance = new ThreadLocal<>();
    
    private Workbook workbook;
    private Sheet sheet;
    private Map<String, String> dataMap; 

    private ExcelUtils(String sheetName, String fileName) {
        try (FileInputStream fis = new FileInputStream(FILE_PATH + fileName)) {
            workbook = new XSSFWorkbook(fis);
            setSheet(sheetName);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading Excel file: {0}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void initialize(String sheetName, String fileName) {
        if (threadLocalInstance.get() == null) {
            threadLocalInstance.set(new ExcelUtils(sheetName, fileName));
        }
    }

    public static ExcelUtils getInstance() {
        if (threadLocalInstance.get() == null) {
            throw new IllegalStateException("ExcelUtils not initialized for this thread.");
        }
        return threadLocalInstance.get();
    }

    public void setSheet(String sheetName) {
        sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet not found: " + sheetName);
        }
        logger.log(Level.INFO, "Switched to Excel Sheet: {0}", sheetName);
        loadDataIntoMap();
    }

    private void loadDataIntoMap() {
        dataMap = new HashMap<>();
        for (Row row : sheet) {
            Cell keyCell = row.getCell(0);  // Column A (Field Name)
            Cell valueCell = row.getCell(1); // Column B (Field Value)

            if (keyCell != null && valueCell != null) {
                dataMap.put(keyCell.toString().trim(), valueCell.toString().trim());
            }
        }
    }

    public String getFieldValue(String fieldName) {
        return dataMap.getOrDefault(fieldName, null);
    }

    public void closeWorkbook() {
        try {
            if (workbook != null) {
                workbook.close();
                logger.log(Level.INFO, "Excel workbook closed.");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error closing workbook: {0}", e.getMessage());
        }
    }

    public static void cleanup() {
        if (threadLocalInstance.get() != null) {
            threadLocalInstance.get().closeWorkbook();
            threadLocalInstance.remove();
        }
    }
}
