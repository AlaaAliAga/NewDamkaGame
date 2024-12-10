package model;

import java.util.ArrayList;


public class question {

	private String question1;
	private ArrayList<String> answers;
	private String CorrectAns;
	private String Level;
	private String team;
	

	public void setCorrectAns(String correctAns) {
		CorrectAns = correctAns;
	}
	
	public question(String q1,ArrayList<String> ans,String correctAns,String lv,String team)
	{
		this.question1=q1;
		this.answers=ans;
		this.CorrectAns=correctAns;
		this.Level=lv;
		this.team=team;
	}
	
	public question(String question) {
		super();
		this.question1 = question;
		this.answers = new ArrayList<>();
		this.Level=new String();
	}


	public question() {
		super();
		this.answers = new ArrayList<>();
	}
	

	public String getCorrectAns() {
		return CorrectAns;
	}

	


	public ArrayList<String> getAnswers() {
		return answers;
	}


	public void setAnswers(ArrayList<String> answers) {
		if(answers.size()==4)
		this.answers = answers;
	}

	public void setAnswers(String ans) {
		if(this.answers !=null && this.answers.size()<4) {
			addAnswer(ans);
		}
	}
	public boolean addAnswer(String ans) {
		return answers.add(ans);
	}

	
	
	@Override
	public String toString() {
		return "question [question1=" + question1 + ", answers=" + answers + ", CorrectAns=" + CorrectAns + ", Level="
				+ Level + ", team=" + team + "]";
	}

	public boolean AddAnswer(String s)
	{
		if (s !=null)
		{
			this.answers.add(s);
		}
		return false;
	}

	public String getQuestion1() {
		return question1;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	


	public String getLevel() {
		return Level;
	}

	public void setLevel(String level) {
		Level = level;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

}
