package commands;
import java.io.*;
import java.util.*;
public class Delete{
	public String keywords[]={"as","table","into","create","select","insert","load","print","store","exit","quit","from","drop"};
	List<String> keyword = Arrays.asList(keywords);

	public boolean validate(String input[]){
		String syntax[]={"drop","table"};
		if(input.length<=1){
			System.out.println("Missing keyword");
			return false;
		}
		else if(!input[1].equals(syntax[1])){
			System.out.println("Missing keyword");
			return false;
		}
		else if(input.length!=3)
		{
			System.out.println("Missing keyword");
			return false;
		}
		else
			return deletetable(input);
	}
	
	public boolean deletetable(String input[]){
		String path="tables/"+input[2]+".txt";
		File file=new File(path);
		if(file.delete()){
			return true;
		}
		else{
			System.out.println("Table not found");
			return false;
		}
	}
	
}