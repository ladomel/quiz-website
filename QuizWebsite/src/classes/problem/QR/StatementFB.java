package classes.problem.QR;

import java.util.ArrayList;

import classes.problem.Abstract.Statement;

public class StatementFB extends Statement{

private String description;
ArrayList<String> statement;
	
	public StatementFB(String description, ArrayList<String> statement)
	{
		this.description = description;
		this.statement = statement;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public ArrayList<String> getStatement()
	{
		return statement;
	}
}
