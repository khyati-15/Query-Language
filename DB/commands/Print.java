package commands;
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
public class Print{
	public String keywords[]={"as","table","into","create","select","insert","load","print","store","exit","quit","from"};
	List<String> keyword = Arrays.asList(keywords);

	public boolean validate(String input[]) throws Exception{
		if(input.length==2){
			if(checktable(input[1])){
				printtable(input[1]);
				return true;
			}
			else
				return false;
		}
		else{
			System.out.println("Incorrect Syntax");
			return false;
		}
	}
	
	public boolean checktable(String tablename){
		String path="tables/"+tablename+".txt";
		try{
		FileInputStream fin=new FileInputStream(path);
		}
		catch(FileNotFoundException e){
		System.out.println("Table not found");
			return false;
		}
		return true;
	}
	
	public void printtable(String tablename) throws Exception{
		String path="tables/"+tablename+".txt";
		List<String> allLines = Files.readAllLines(Paths.get(path));
			for (String line : allLines) {
				System.out.println(line.replaceAll(",","\t\t"));
			}
		
	}
	
}