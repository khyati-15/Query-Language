package commands;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Create{
	Pattern pattern = Pattern.compile("[a-zA-Z_]*");
	public String keywords[]={"as","table","into","create","select","insert","load","print","store","exit","quit","from"};
	List<String> keyword = Arrays.asList(keywords);

	public boolean validate(String input[]){
		String syntax[]={"create","table"};
		if(input.length<=1){
			System.out.println("Missing keyword");
			return false;
		}
		else if(!input[1].equals(syntax[1])){
			System.out.println("Missing keyword");
			return false;
		}
		else if(!(input[3].charAt(0)=='(' )){
			System.out.println("Missing keyword");
			return false;
		}
		else{
			if(input[input.length-1].charAt(input[input.length-1].length()-1)==')'){
				
				String columns=input[3];
				for(int i=4;i<input.length;i++)
					columns+=input[i];
				columns=columns.trim().replaceAll("\\s{1,}","");
				columns=columns.replace("(","");
				columns=columns.replace(")","");
				if(columns.length()>2)
				{
					return checktable(input[2]);
				}
				else{
					System.out.println("Specify atleast one column name");
					return false;
				}
				
			}
			else
				return false;
		}
	}
	
	public boolean checktable(String tablename){
		Matcher matcher = pattern.matcher(tablename);
		if (!matcher.matches()) {
			System.out.println("Tablename cannot contain special characters");
			return false;
		}
			
		String path="tables/"+tablename+".txt";
		try{
		FileInputStream fin=new FileInputStream(path);
		}
		catch(FileNotFoundException e){
		if(keyword.contains(tablename)){
			System.out.println("Tablename is a keyword!!!");
			return false;
		}
		else
			return true;
		}
		System.out.println("Table already exists");
		return false;
	}
	
	public boolean createtable(String input[]) throws Exception{
		String path="tables/"+input[2]+".txt";
		//checking columns
		String columns=input[3];
		for(int i=4;i<input.length;i++){
			columns+=input[i];

		}
		columns=columns.trim().replaceAll("\\s{2,}", " ");
		columns=columns.replace("(","");
		columns=columns.replace(")","");
		columns=columns.trim();
		String cols[]=columns.split(",");
		
		List<String> columnss=Arrays.asList(cols);
		Set<String> set= new HashSet<String>(columnss);
		if(set.size()<columnss.size()){
			System.out.println("Column names must be distinct");
			return false;
		}
		
		for(int i=0;i<cols.length;i++){
			Matcher matcher = pattern.matcher(cols[i]);
			if(keyword.contains(cols[i]) || cols[i].equals(input[2]) || !matcher.matches()){
					System.out.println("Column name "+cols[i]+" invalid");
						return false;
			}
		}
		//creating file
			File file = new File(path);
			if(file.createNewFile()){
				
				FileWriter writer = new FileWriter(path, true);
				for(int i=0;i<cols.length;i++){
					writer.write(cols[i]);
					if(i!=cols.length-1)
					writer.write(",");
				}
				writer.write("\r\n");
				writer.close();
			return true;
		}
		else
			return false;	
	}
	
}