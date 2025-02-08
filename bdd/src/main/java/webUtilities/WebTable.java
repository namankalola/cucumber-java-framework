package webUtilities;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebTable extends Elements {

    private static final Logger logger = Logger.getLogger(WebTable.class.getName());
    private By by;

    public WebTable(By by, WebDriver driver) {
        super(by, driver);
        this.by = by;
    }

    public int getRowCount() {
        List<WebElement> rows = getElement().findElements(By.tagName("tr"));
        int rowCount = rows.size();
        logger.log(Level.INFO, "Total number of rows in table {0}: {1}", new Object[]{by, rowCount});
        return rowCount;
    }

    public int getColumnCount() {
        List<WebElement> columns = getElement().findElements(By.tagName("tr")).get(0).findElements(By.tagName("td"));
        int columnCount = columns.size();
        logger.log(Level.INFO, "Total number of columns in table {0}: {1}", new Object[]{by, columnCount});
        return columnCount;
    }

    public String getCellData(int rowIndex, int colIndex) {
        WebElement cell = getElement().findElements(By.tagName("tr")).get(rowIndex - 1)
                .findElements(By.tagName("td")).get(colIndex - 1);
        String cellText = cell.getText();
        logger.log(Level.INFO, "Cell data at row {0}, column {1} in table {2}: {3}",
                new Object[]{rowIndex, colIndex, by, cellText});
        return cellText;
    }

    public List<List<String>> getAllTableData() {
        List<WebElement> rows = getElement().findElements(By.tagName("tr"));
        List<List<String>> tableData = new java.util.ArrayList<>();

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            List<String> rowData = new java.util.ArrayList<>();
            for (WebElement cell : cells) {
                rowData.add(cell.getText());
            }
            tableData.add(rowData);
        }

        logger.log(Level.INFO, "Retrieved table data from table: {0}", by);
        return tableData;
    }

    public int findRowIndexByText(String text, int colIndex) {
        List<WebElement> rows = getElement().findElements(By.tagName("tr"));
        for (int i = 0; i < rows.size(); i++) {
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            if (cells.size() > colIndex - 1 && cells.get(colIndex - 1).getText().equalsIgnoreCase(text)) {
                logger.log(Level.INFO, "Text ''{0}'' found in row {1} of table {2}", new Object[]{text, i + 1, by});
                return i + 1;
            }
        }
        logger.log(Level.INFO, "Text ''{0}'' not found in table {1}", new Object[]{text, by});
        return -1;
    }
}
