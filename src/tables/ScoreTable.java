package tables;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controler.Board;
import controler.Controller;


@SuppressWarnings("serial")
public class ScoreTable extends AbstractTableModel {

	protected final ArrayList<String[]> scoreTable;
	  
    private final String[] columnNames = new String[] { "FirstPlayer", "Score","KnowledgePercentPlayer1", "SecondPlayer" , "Score" , "KnowledgePercentPlayer2" , "Winner"};
    @SuppressWarnings("rawtypes")
	private final Class[] columnClass = new Class[] {
        String.class , String.class , String.class , String.class , String.class , String.class , String.class 
    };
 
    
    public ScoreTable(ArrayList<String[]> arrayList)
    {
        this.scoreTable = arrayList;
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
		if(scoreTable==null) {
		return 0;
		}
       return scoreTable.size();
   }


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
    //    Board row	=	scoreTable.get(rowIndex);
		
		     String[] array = Controller.historyGameArray().get(rowIndex);
		
		

			if(0 == columnIndex) {
	          // System.out.println(Controller.getPlayers().get(0));
//				System.out.println(array[0]);
	           return array[0];
	        }
	        else if(1 == columnIndex) {
//	        	System.out.println(array[1]);
	        	return array[1];
	        }
	        else if(2 == columnIndex) {
//	        	System.out.println(array[2]);
		           return array[2];
	         }  
	        else if(3 == columnIndex) {
//	        	System.out.println(array[3]);
		           return array[3];
	         }
	        else if(4 == columnIndex) {
//	        	System.out.println(array[4]);
		           return array[4];
	         }
	        else if(5 == columnIndex) {
//	        	System.out.println(array[5]);
		           return array[5];
		         }
	        else if(6 == columnIndex) {
//	        	System.out.println(array[6]);
		           return array[6];

	         }
//		
			return null;
	}
	

}
