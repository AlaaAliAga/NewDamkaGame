package controler;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
//import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
//import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TreeSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.PieceType;
import model.Pieces;
import model.Player;
import model.TileType;
import model.Tiles;
import model.question;
import views.NewGame;
import views.ViewQuestionInGame;
import views.LoadGAME;
import views.MainScrean;
import views.MainScrean;

public class Controller {
	// array for saving the game ..
	private static int array[] = new int [32];

	//-------------new-------------//
    private static String[] players;
    private static ArrayList<Board>  AllGames;
	private static Controller controler;
	private static ArrayList<String> LoadGames;
	private static boolean flag = false;
	private static boolean flagQuestion = false;
	private static ArrayList<question> removedQuestion;
	private static ArrayList<question> questions;
	public static ArrayList<String []> gameHistory1 = new ArrayList<>();
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	static LocalDateTime now = LocalDateTime.now();  

	
	private static boolean winFlag = false;

	 public static ArrayList<Board> getAllGames() {
		   if(AllGames==null) {
			   AllGames= new  ArrayList<Board>();
		   }
			return AllGames;
		}

		public void setAllGames(ArrayList<Board> allGames) {
			AllGames = allGames;
		}
		public static String[] getPlayers() {
			   if(players==null) 
				   players= new String [7];
			return players;
		}

		public static ArrayList<String> getLoadGames() {
			if(LoadGames==null) {
				LoadGames= new  ArrayList<String>();
			   }
		return LoadGames;
	}

	public static void setLoadGames(ArrayList<String> loadGames) {
		LoadGames = loadGames;
	}

		public void setPlayers(String [] players) {
			Controller.players = players;
		}
		
	

	public Controller(){
		questions=new ArrayList<question>();
		removedQuestion = new ArrayList<question>();
		players=new String[7];
    	AllGames= new ArrayList<Board>(); 
    	LoadGames = new ArrayList<String>();
    	loadQuestionFiles();
 		LoadGame();
	}


	public static Controller getInstance() {
		if(controler==null) { 
			controler=new Controller();			
		}
		return controler; 
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
	                Controller c = new Controller();
	                MainScrean window = new MainScrean();
	        		MainScrean.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	


	
	public static question getSpecificQuestion(String question) {
		ArrayList<question> array = new ArrayList<>();
		array.addAll(getQuestionss());
		array.addAll(getRemovedQuestion());
		for(question q : array) {
			if(q.getQuestion1().equals(question))
				return q;
		}
		return null;
	}



	
	
	// check board if either of the players already won
	public static void checkStatus() {
		checkWin();
		if(!winFlag)
			checkStuck();
	}

	
	
	/// a Random Question 
		public static question  getRandomQuestion() 
		{ 
			question q = new question();
			Random rand = new Random(); 
			if(!questions.isEmpty()) {
				q = questions.get(rand.nextInt(questions.size())); 
				questions.remove(q);
				removedQuestion.add(q);
				return q;
			}else {
				questions.addAll(removedQuestion);
				q = questions.get(rand.nextInt(questions.size())); 
				questions.remove(q);
				removedQuestion.add(q);
				return q;	
			}
		}

	
	
	// checks if either plays has no moves to make
	public static void checkStuck() {
		Tiles[][] b = Board.getTheBoard();
		ArrayList<Pieces> redPieces = new ArrayList<>();
		ArrayList<Pieces> blackPieces = new ArrayList<>();

		for(int i = 0; i < b.length; i++) {
			for(int j = 0; j < b[0].length; j++) {
				if(b[i][j].isOccupied() && 
						(b[i][j].getPiece().getType() == PieceType.RED || b[i][j].getPiece().getType() == PieceType.RED_KING)) {
					redPieces.add(new Pieces(i, j, b[i][j].getPiece().getType()));
				} else if(b[i][j].isOccupied()) {
					blackPieces.add(new Pieces(i, j, b[i][j].getPiece().getType()));
				}
			}
		}

		boolean redLose = true;
		boolean blackLose = true;
		for(Pieces p : redPieces) {
			if(p.canEatOrMove(Board.getTheBoard())) {
				redLose = false;
			}
		}
		for(Pieces p : blackPieces) {
			if(p.canEatOrMove(Board.getTheBoard())) {
				blackLose = false;
			}
		}


		if(redLose) {
			if(Board.getScoreBlackPlayer() > Board.getScoreRedPlayer()) {
				WriteToGameHistroy();
				NewGame.displayDialog(MainScrean.getNickName1());
			}else if(Board.getScoreBlackPlayer() < Board.getScoreRedPlayer()) {
				WriteToGameHistroy();
				NewGame.displayDialog(MainScrean.getNickName2());	
			}else {
				WriteToGameHistroy();
				NewGame.displayDialog(MainScrean.getNickName1());	
			}
		}else
			if(blackLose) {
				if(Board.getScoreBlackPlayer() > Board.getScoreRedPlayer()) {
					WriteToGameHistroy();
					NewGame.displayDialog(MainScrean.getNickName1());
				}else if(Board.getScoreBlackPlayer() < Board.getScoreRedPlayer()) {
					WriteToGameHistroy();
					NewGame.displayDialog(MainScrean.getNickName2());	
				}else {
					WriteToGameHistroy();
					NewGame.displayDialog(MainScrean.getNickName2());
				}
			}
	}



	public static void resetTilesColor () {
		Tiles [][] t = Board.getTheBoard();
		for(int i = 0 ; i<8 ; i++) {
			for(int j=0;j<8;j++) {
				if((i+j)%2==0) {
					t[i][j].setType(TileType.WHITE);
				}else {
					t[i][j].setType(TileType.GRAY);
				}
			}
		}
	}

	// checks if either players has no pieces left
	public static void checkWin() { // If one side has no pieces left, the other side
		// wins. No draw game functionality
		ArrayList<Pieces> redPieces = redPiecesAva();
		ArrayList<Pieces> blackPieces = blackPiecesAva();
		if (redPieces.size()==0) {
			if(Board.getScoreBlackPlayer() > Board.getScoreRedPlayer()) {
				winFlag=true;
				WriteToGameHistroy();
				NewGame.displayDialog(MainScrean.getNickName1());
			}else if(Board.getScoreBlackPlayer() < Board.getScoreRedPlayer()) {
				winFlag=true;
				WriteToGameHistroy();
				NewGame.displayDialog(MainScrean.getNickName2());	
			} else {
				winFlag=true;
				WriteToGameHistroy();
				NewGame.displayDialog(MainScrean.getNickName2());
			}
		} else if (blackPieces.size()==0) {
			if(Board.getScoreBlackPlayer() > Board.getScoreRedPlayer()) {
				WriteToGameHistroy();
				winFlag=true;
				NewGame.displayDialog(MainScrean.getNickName1());
			}else if(Board.getScoreBlackPlayer() < Board.getScoreRedPlayer()) {
				winFlag=true;
				WriteToGameHistroy();
				NewGame.displayDialog(MainScrean.getNickName2());	
			} else {
				winFlag=true;
				WriteToGameHistroy();
				NewGame.displayDialog(MainScrean.getNickName1());
			}

		}
	}

	public static ArrayList<String> LoadGame() {
		if(flag) {
			Controller.LoadGames.clear();
		}
		flag=true;
		BufferedReader in = null;
		ArrayList<String> myList = new ArrayList<String>();
		try {   
			in = new BufferedReader(new FileReader("LoadGame.txt"));
			String str;
			while ((str = in.readLine()) != null) {
				myList.add(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    	LoadGames.addAll(myList);

		return myList;
	}

	
	
	public static ArrayList<Pieces> redPiecesAva (){
		ArrayList<Pieces> redPieces = new ArrayList<>();
		Tiles [][] b = Board.getTheBoard();
	
		for(int i=0 ; i <  8 ;i++) {
			for(int j=0 ; j<8 ; j++) {
				if((i+j)%2==1) {
					if (b[i][j].isOccupied() && (b[i][j].getPiece().getType() == PieceType.RED||b[i][j].getPiece().getType() == PieceType.RED_KING)) {
						redPieces.add(b[i][j].getPiece());
					}
				}
			}
		}
		return redPieces;

	}

	public static ArrayList<Pieces> blackPiecesAva (){
		ArrayList<Pieces> blackPieces = new ArrayList<>();
		Tiles [][] b = Board.getTheBoard();
		for(int i=0 ; i <  8 ;i++) {
			for(int j=0 ; j<8 ; j++) {
				if((i+j)%2==1) {
					if(b[i][j].isOccupied() && (b[i][j].getPiece().getType() == PieceType.BLACK ||b[i][j].getPiece().getType() == PieceType.BLACK_KING ))
					{
						blackPieces.add(b[i][j].getPiece());
					}
				}
			}
		}
		return blackPieces;
	}






	// load theBoard Game from Array Strings .
	public Board loadBoardGame (String str) {
		Board b = new Board();
		Tiles [][] board = Board.getTheBoard();
		String [] strArray = new String [33];
			strArray = str.split(","); 
		for(int i=0 ; i<8 ; i++) {
			for(int j = 0 ; j<8 ; j++) {
				if((i+j)%2==1) {
					board[i][j].setPiece(null);
				}
			}
		}
		int i1 = 0 ; 
		for(int i = 0 ; i<8 ; i++) {
			for(int j =0; j<8 ; j ++) {
				if((i+j)%2==1) {
					if(strArray[i1] == "2") {
						board[i][j].setPiece(new Pieces(i,j, PieceType.BLACK));
						i1++;
					}else if(strArray[i1] == "1") {
						board[i][j].setPiece(new Pieces(i,j, PieceType.RED));
						i1++;
					}else if(strArray[i1]=="22") {
						board[i][j].setPiece(new Pieces(i,j, PieceType.BLACK_KING));
						i1++;
					}else if(strArray[i1]=="11") {
						board[i][j].setPiece(new Pieces(i,j, PieceType.RED_KING));
						i1++;
					}else if(strArray[i1]=="0") {
						i1++;
					}else if(strArray[i1]=="R") {
						Board.setTurnCounter(1);
					}else if(strArray[i1]=="B") {
						Board.setTurnCounter(0);
					}
				}
			}
		}
		b.setTheBoard(board);
		return b;
	}
	public static String getSpecificBoard(String strings) {
		for(String q : LoadGames) {
			if(q.equals(strings))
				return q;
		}
		return null;
	}


	public static void WriteToGameHistroy() {
		try {
		    BufferedWriter writer = new BufferedWriter(new FileWriter("GameHistory.txt", true));	
		    double redPercent = ((double)ViewQuestionInGame.getCounterCurrectAnswerRed()/(double)ViewQuestionInGame.getCounterQuestionRed())*100.0;
		    double blackPercent = ((double)ViewQuestionInGame.getCounterCurectAnswerBlack()/(double)ViewQuestionInGame.getCounterQuestionBlack())*100.0;
		    writer.write(MainScrean.getNickName2() + ",");
			writer.write(Board.getScoreRedPlayer() + ",");
			writer.write((int)redPercent + ",");
			 writer.write(MainScrean.getNickName1() + ",");
			writer.write(Board.getScoreBlackPlayer() + ",");
			writer.write((int)blackPercent + ",");
			if(Board.getTurnCounter()%2==0)
				  writer.write(MainScrean.getNickName1() );
			else
				writer.write(MainScrean.getNickName2());
			writer.write("\n");
			writer.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	
	public static ArrayList<String> readFromGameHistroy() {
		BufferedReader in = null;
		ArrayList<String> myList = new ArrayList<String>();
		try {   
			in = new BufferedReader(new FileReader("GameHIstory.txt"));
			String str;
			while ((str = in.readLine()) != null) {
				myList.add(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return myList;
	}

	
	public static ArrayList<String[]> historyGameArray (){
		ArrayList<String []> returnArray = new ArrayList<>();
		ArrayList<String> fileArray = readFromGameHistroy();
		String[] str = new String [7];
		for(String s : fileArray) {
			str = s.split(",");
			returnArray.add(str);
			gameHistory1.add(str);
		}
		return returnArray; 
	}
	
	
	
	
	public static void WriteToFileLoafGame(String[] array) {
		try {
		    BufferedWriter writer = new BufferedWriter(new FileWriter("LoadGame.txt", true));
			for(String s : array) {
				if(s=="B" || s=="R")
					writer.write(s);
				else
					writer.write(s + ",");
			}
			writer.write("\n");
			writer.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}



	/// saving and returned a position Board in array integer
	public static void saveGame() {
		Tiles [][] b = Board.getTheBoard();
		String array[] = new String [33];
		int counterArray = 0 ;

		for(int i = 0; i < b.length; i++) {
			for(int j = 0; j < b[0].length; j++) {
				if(b[i][j].isOccupied() && ((i+j)%2== 1) ) {
					if(b[i][j].getPiece().getType() == PieceType.BLACK) {
						array[counterArray] = "2";
						counterArray++;
					}else if(b[i][j].getPiece().getType() == PieceType.BLACK_KING) {
						array[counterArray] = "22";
						counterArray++;
					}else if(b[i][j].getPiece().getType() == PieceType.RED) {
						array[counterArray] = "1";
						counterArray++;
					}else if(b[i][j].getPiece().getType() == PieceType.RED_KING) {
						array[counterArray] = "11";
						counterArray++;
					} 
				}else if(!b[i][j].isOccupied() && ((i+j)%2== 1) ){
						// if null
						array[counterArray] = "0";
						counterArray++;
					}
			}
		}
		if(Board.getTurnCounter()%2==1)
		{array[counterArray] = "R";
		}else { 
			array[counterArray] = "B";
		}
		//write to LoadGame.txt File 
		WriteToFileLoafGame(array);
		LoadGame();
	}
	public ArrayList<question> getQuestionsAfterRead() {
		loadQuestionFiles();		
		return questions;
	}

	
	/**
	 * This Method reads the initial state of the json file
	 * 
	 * @return if the file was loaded successfully, else false
	 */

	public static boolean loadQuestionFiles() {
		if(flagQuestion) {
			Controller.questions.clear();
		}
		//Controller.questions.clear();
		flagQuestion = true;
		JSONParser p = new JSONParser();
		try {
			Object obj = p.parse(new FileReader("Questions.json"));
			JSONObject jObject = (JSONObject) obj;
			JSONArray Qs = (JSONArray) jObject.get("questions");
			for (Object Q : Qs) {
				jObject = (JSONObject) Q;
				String Question1 = (String) jObject.get("question");
				JSONArray answers = ((JSONArray) jObject.get("answers"));
				String CorrAns = (String) jObject.get("correct_ans");
				String Level = (String) jObject.get("level");
				String team = (String) jObject.get("team");
				ArrayList<String> Ans = new ArrayList<String>();
				Object[] tempA = answers.toArray();
				for (Object tm : tempA) {
					Ans.add((String) tm);
				}
				question ques = new question(Question1, Ans, CorrAns, Level, team);
				questions.add(ques);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * Saves Questions
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void saveQuestions(Map<String, List<question>> questions) {
		JSONArray JSONquestions = new JSONArray();
		JSONObject toWrite = new JSONObject();
		for (Map.Entry<String, List<question>> list : questions.entrySet()) {
			if (list.getValue() == null)
				continue;
			for (question q : list.getValue()) {
				JSONObject jo = new JSONObject();
				JSONArray ans = new JSONArray();
				for (String a : q.getAnswers()) {
					ans.add(a);
				}
				jo.put("correct_ans", q.getCorrectAns());
				jo.put("level", q.getLevel());
				jo.put("team", q.getTeam());
				jo.put("question", q.getQuestion1());
				jo.put("answers", ans);
				JSONquestions.add(jo);

			}
			toWrite.put("questions", JSONquestions);
		}
		File f = new File("Questions.json");
		try (FileWriter file = new FileWriter(f)) {
			file.write(toWrite.toJSONString());
			// Logger.log("Question JSON was saved");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
/**
 * add question to JSON FILE
 * @param q1
 * @param ans
 * @param correctAns
 * @param lv
 * @param team
 * @return 
 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean AddToFile(String q1, ArrayList<String> ans, String correctAns, String lv, String team) {
		question Q = new question(q1, ans, correctAns, lv, team);
		boolean flag = false;
		for (question Qu : Controller.questions) {
			if (Qu.equals(Q)) {
				flag = true;
			}
		}
		if (flag == false) {
			Controller.questions.add(Q);
		}
		Map M = new HashMap<String, List<question>>();
		ArrayList<question> A = new ArrayList<question>();
		ArrayList<question> B = new ArrayList<question>();
		ArrayList<question> C = new ArrayList<question>();
		for (question q : questions) {
			if (q.getLevel().equals("1")) {
				A.add(q);
			}
			if (q.getLevel().equals("2")) {
				B.add(q);
			}
			if (q.getLevel().equals("3")) {
				C.add(q);
			}

		}
		M.put("1", A);
		M.put("2", B);
		M.put("3", C);
		saveQuestions(M);
		
		return true ;
	}

	/**
	 * This Method remove question from the file , not Ready Yet
	 */
	@SuppressWarnings("unchecked")
	public static boolean removeData(String q1, ArrayList<String> ans, String correctAns, String lv, String team) {
		question Q = new question(q1, ans, correctAns, lv, team);
		System.out.println(Q);
		questions.remove(Q);

		return true;

	}
	
	
	/////// new 
	public static boolean RemoveQuestion(question question) {
		if(question==null)
			return false;

		question temp = new question();
		boolean flag=false;
		for(question q : questions) {
			if(q!=null)
				if(question.getQuestion1().equals(q.getQuestion1())) {
					temp = q;
					flag = true;
				}
		}
		if(flag==false) {
			return false;
		}


		questions.remove(temp);
	
		reWriteQuestions();
		return true;
	}

	@SuppressWarnings("unchecked")
	public static void reWriteQuestions() {
		JSONArray jsarray=new JSONArray();
		JSONObject jobject=new JSONObject();
		for(question question: getQuestionss())   {
			JSONObject obj=new JSONObject();
			obj.put("question",question.getQuestion1()); 
			JSONArray answers = new JSONArray();
			for(String ans:question.getAnswers()) {
				answers.add(ans);	        			
			}
			obj.put("answers", answers);
			obj.put("correct_ans", question.getCorrectAns());
			obj.put("level", question.getLevel());
			obj.put("team", question.getTeam());
			
			jsarray.add(obj);
		}
		try {
			removeAndCreateNewFile("Questions.json");
		}catch(Exception ex) {

		}
		try (FileWriter f = new FileWriter("Questions.json")) {
			jobject.put("questions", jsarray);
			f.write(jobject.toJSONString());
		} 
		catch(Exception ex) {
		}
	}

	public static boolean updateQuestion(String oldQ, String newQuestion, ArrayList<String> ans, String correctAns, String lv, String team) {
		if(oldQ==null || ans==null || correctAns==null|| lv==null || team==null)
			return false;
		question input = new question(oldQ,ans,correctAns,lv,team);
		question temp = new question();
		boolean flag=false;
		for(question q : questions) {
			if(q!=null)
				if(input.getQuestion1().equals(q.getQuestion1())) {
					temp = q;
					flag = true;
				}
		}
		if(flag==false) {
			return false;
		}

		questions.remove(temp);
	
		
		AddToFile(newQuestion,ans,correctAns,lv,team);
		loadQuestionFiles();
		return true;
	}

	
	
                 // new
	public static boolean removeAndCreateNewFile(String questionFileName) {
		if(!questionFileName.equals("Questions.json") && !questionFileName.equals("Questions.json")) {
			return false;
		}
		if(questionFileName.equals("Questions.json")) {
			File f=null;
			f=new File("Questions.json");
			if(f.isFile())
				f.delete();
			try {
				f.createNewFile();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			File f=null;
			f=new File("Questions.json");
			if(f.isFile())
				f.delete();
			try {
				f.createNewFile();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}


/**
 * update Json File
 * @param q1
 * @param ans
 * @param correctAns
 * @param lv
 * @param team
 * @return
 */
	public static boolean UpdateFile(String q1, ArrayList<String> ans, String correctAns, String lv, String team) {
		for (question q : Controller.questions) {
			if (q.getQuestion1().equals(q1)) {
				removeData(q.getQuestion1(), q.getAnswers(), q.getCorrectAns(), q.getLevel(), q.getTeam());
				AddToFile(q1, ans, correctAns, lv, team);
				return true;
			}

		}
		System.out.print("Question Not Found ");
		return false;

	}
	public boolean UpdateFile(String q1, ArrayList<String> ans, int correctAns, int lv, String team) {
		return true;
	}

	public question GetFromFile(String q1) {
		return null;
	}

	public static ArrayList<question> getRemovedQuestion() {
		if(removedQuestion.isEmpty())
			removedQuestion = new ArrayList<question>();
		return removedQuestion;
	}

	public static void setRemovedQuestion(ArrayList<question> removedQuestion) {
		Controller.removedQuestion = removedQuestion;
	}
	
	public static ArrayList<question> getQuestionss() {
		
		if(questions==null)
			questions=new ArrayList<question>();
		return questions;
	}


}
