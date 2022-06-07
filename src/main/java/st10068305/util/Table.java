package st10068305.util;

import java.util.ArrayList;

public class Table {
    String header, footer;
    ArrayList<ArrayList<String>> rows;

    public Table() {
    }

    /**
     * This constructor will set the defaults directly.
     *
     * @param header The header of the table
     * @param footer The footer of the table
     * @param rows   The rows contain a list of columns
     */
    public Table(String header, String footer, ArrayList<ArrayList<String>> rows) {
    }

    public String generateTable() {
        StringBuilder table = new StringBuilder();

        for (ArrayList<String> columns : rows) {
            table.append("|\t");

            for (String column : columns) {
                table.append(column);
                table.append("\t|\t");
            }
        }

        return table.toString();
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public void setRows(ArrayList<ArrayList<String>> rows) {
        this.rows = rows;
    }

    public void addRow(ArrayList<String> columns) {
        this.rows.add(columns);
    }

    public void addColumn(int row, String column) {
        this.rows.get(row).add(column);
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    public ArrayList<ArrayList<String>> getRows() {
        return rows;
    }
}

