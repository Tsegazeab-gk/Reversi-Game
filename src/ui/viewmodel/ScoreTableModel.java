package ui.viewmodel;

import models.Score;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ScoreTableModel extends AbstractTableModel {

//column names for table Score
    String[] columnNames = {"Name", "Moves", "Score"};
    List<Score> scoreList;

    public ScoreTableModel(List<Score> scoreList) {
        super();
        this.scoreList = scoreList;
    }

    @Override
    public int getRowCount() {
        int size;
        if (scoreList == null) {
            size = 0;
        } else {
            size = scoreList.size();
        }
        return size;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;
        if (columnIndex == 0) {
            temp = scoreList.get(rowIndex).getPlayerName();
        } else if (columnIndex == 1) {
            temp = scoreList.get(rowIndex).getNumberOfMove();
        } else if (columnIndex == 2) {


            temp = scoreList.get(rowIndex).getScore();
        }
        return temp;
    }

    @Override
    public String getColumnName(int column) {
        // TODO Auto-generated method stub
        return columnNames[column];
    }
    public void setTableData(List<Score> scorelist) {
        this.scoreList = scorelist;
        fireTableDataChanged();
    }
}
