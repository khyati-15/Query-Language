package commands;
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
public class Select{
	public String keywords[]={"as","table","into","create","select","insert","load","print","store","exit","quit","from"};
	List<String> keyword = Arrays.asList(keywords);

	public boolean validate(String input[]) throws Exception{
		int from=-1;
		for(int i=0;i<input.length;i++){
			if(input[i].equals("from"))
			{
				from=i;
				break;
			}
		}
		if(from==-1 || from<2 || from==input.length-1)
		{
			System.out.println("Missing keywords");
			return false;
		}
		else{
			if(checktable(input[from+1])){
				if(input[from-1].equals("*") && from+1==input.length-1){
					printtable(input[from+1]);
					return true;
				}
				else{
					selecttable(input,from);
					return true;
				}
			}
			else
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
		System.out.println("|--------------------------------------------------------------------------|");
			for (String line : allLines) {
				System.out.print("|\t");
				System.out.println(line.replaceAll(",","\t | \t")+"\t\t");
				System.out.println("|--------------------------------------------------------------------------|");
				

			}
		
	}
	
	public void selecttable(String input[],int index) throws Exception{
		String cols=input[1];
		if(index!=2){
			for(int i=2;i<index;i++)
				cols+=input[i];
		}

		if(index+2==input.length){
			withoutcondition(cols,input[index+1]);
		}
	}
	
	public void withoutcondition(String cols,String tablename) throws Exception{
		if(cols.charAt(0)==',' || cols.charAt(cols.length()-1)==',')
			System.out.println("Incorrect Syntax");
		else{
		String tokens[]=cols.split(",");
		String path="tables/"+tablename+".txt";
		List<String> allLines = Files.readAllLines(Paths.get(path));
		String indexes[]=new String[1];
		
		for (String line : allLines) {
				indexes=line.split(",");
				break;
			}
	
		int required[]=new int[indexes.length];
		int size=0;
		for(int i=0;i<tokens.length;i++){
			for(int j=0;j<indexes.length;j++)
				if(indexes[j].equals(tokens[i]))
				{
					required[size]=j;
					size++;
				}
		}
	
		if(size!=tokens.length)
			System.out.println("Invalid columnname");
		else{
			for(String line:allLines){
				String data[]=line.split(",");
				System.out.print("|\t");
				for(int i=0;i<size;i++){
					System.out.print(data[required[i]]);
					if(i!=size-1)
					System.out.print("\t|\t");
				}
				System.out.println(" \t |");
			}
		}
		}
	}
	
	
}