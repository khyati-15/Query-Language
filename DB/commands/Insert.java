package commands;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.Paths;
public class Insert{
	public String keywords[]={"as","table","into","create","select","insert","load","print","store","exit","quit","from"};
	List<String> keyword = Arrays.asList(keywords);

	public boolean validate(String input[]) throws Exception{
		String syntax[]={"insert","into"};
		if(input.length<=1){
			System.out.println("Missing keyword");
			return false;
		}
		else if(!input[1].equals(syntax[1])){
			System.out.println("Missing keyword");
			return false;
		}
		else{
			if(checktable(input[2])){
				if(input[3].equals("values")){
					if(input[4].charAt(0)=='(' && input[input.length-1].charAt(input[input.length-1].length()-1)==')')
					{
						return insertvalues(input);
					}
					else{
						System.out.println("Missing Keyboard");
						return false;
					}
				}
				else{
					System.out.println("Missing keyword");
					return false;
				}
			}
			else
			{
				return false;
			}
		}
	}
	
	public boolean checktable(String tablename){
		String path="tables/"+tablename+".txt";
		try{
		FileInputStream fin=new FileInputStream(path);
		}
		catch(FileNotFoundException e){
		System.out.println("Invalid Tablename");
			return false;
		}
		return true;
	}
	
	public boolean insertvalues(String input[]) throws Exception{
		String columns="";
		for(int i=4;i<input.length;i++){
			columns+=input[i];
			if(input[i].charAt(input[i].length()-1)!=',')
				columns+=" ";
		}
		columns=columns.trim().replaceAll("\\s{2,}", " ");
		columns=columns.replace("(","");
		columns=columns.replace(")","");
		columns=columns.trim();
		String cols[]=columns.split(",");
		
		int equal=0;
		
		String path="tables/"+input[2]+".txt";
		List<String> allLines = Files.readAllLines(Paths.get(path));
		for (String line : allLines) {
				String tokens[]=line.split(",");
				if(cols.length==tokens.length)
					equal=1;
				else if(cols.length>tokens.length)
					System.out.println("Too Many Values");
				else
					System.out.println("Less Values");
				break;
			}
		if(equal==1){
			FileWriter writer = new FileWriter(path, true);
				for(int i=0;i<cols.length;i++){
					writer.write(cols[i]);
					if(i!=cols.length-1)
					writer.write(",");
				}
				writer.write("\r\n");
				writer.close();
			System.out.println("Inserted");
			return true;
		}
		else
		{
			return false;
		}
		
	}
		
	
}