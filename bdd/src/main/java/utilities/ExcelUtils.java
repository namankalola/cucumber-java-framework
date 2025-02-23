package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class ExcelUtils {

    private static final Logger logger = Logger.getLogger(ExcelUtils.class.getName());
    private static final String FILE_PATH = "src/test/resources/testdata/";
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
        logger.log(Level.INFO, "Initializing ExcelUtils for Sheet: {0} | File: {1}", new Object[]{sheetName, fileName});
        threadLocalInstance.set(new ExcelUtils(sheetName, fileName));
    }

    public static ExcelUtils getInstance() {
        ExcelUtils instance = threadLocalInstance.get();
        if (instance == null) {
            throw new IllegalStateException("ExcelUtils not initialized for this thread.");
        }
        return instance;
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
            if (row.getCell(0) != null && row.getCell(1) != null) {
                String key = row.getCell(0).getStringCellValue().trim();
                String value = row.getCell(1).getStringCellValue().trim();
                dataMap.put(key, value);
            }
        }
        logger.log(Level.INFO, "Loaded {0} data entries from sheet.", dataMap.size());
    }

    public String getFieldValue(String fieldName) {
        String value = dataMap.getOrDefault(fieldName, "Field Not Found");
        logger.log(Level.INFO, "Fetching field: {0} | Value: {1}", new Object[]{fieldName, value});
        return value;
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
            logger.log(Level.INFO, "Cleaned up ExcelUtils instance.");
        }
    }
}
