package tables;

import java.util.List;


import javax.swing.table.AbstractTableModel;

import model.question;


@SuppressWarnings("serial")
public class QuestionTable  extends AbstractTableModel {
	

	protected final List<question> questionTable;
	  
    private final String[] columnNames = new String[] { "Question", "answer1" , "answer2","answer3","answer4"};
    @SuppressWarnings("rawtypes")
	private final Class[] columnClass = new Class[] {
        String.class, String.class , String.class ,String.class , String.class
    };
 
    
    public QuestionTable(List<question> questionTable)
    {
        this.questionTable = questionTable;
    }
     
    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }
 
    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }
 
    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }
 
    @Override
    public int getRowCount()
    {
		if(questionTable==null) {
		return 0;
		}
        return questionTable.size();
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
    
    	question row = questionTable.get(rowIndex);
        if(0 == columnIndex) {
           return row.getQuestion1();
        }
        else if(1 == columnIndex) {
           return row.getAnswers().get(0);
        }
        else if(2 == columnIndex) {
            return row.getAnswers().get(1);
         }  
        else if(3 == columnIndex) {
             return row.getAnswers().get(2);
         }
        else if(4 == columnIndex) {
            return row.getAnswers().get(3);
         }
        return null;
    }
	
}
