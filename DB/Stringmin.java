import java.util.Scanner;
import java.util.*;
import commands.*;
class StringOptimized{
	public static void main(String args[]) throws Exception{
		Scanner sc=new Scanner(System.in);
		String command[]={"create","select","insert","load","print","store","exit","quit","drop"};
		String keywords[]={"as","table","into","create","select","insert","load","print","store","exit","quit","from","drop"};
		String input=sc.nextLine();
		input=input.replace("("," (");
		input=input.replace(";","");
		input=input.trim().replaceAll("\\s{2,}", " ");
		input=input.toLowerCase();
		String tokens[]=input.split(" ");

		List<String> list = Arrays.asList(command);
        List<String> keyword = Arrays.asList(keywords);
        if(list.contains(tokens[0])){
			if(tokens[0].equals("create")){
				Create obj=new Create();
				if(obj.validate(tokens))
				{
						if(obj.createtable(tokens))
							System.out.println("Table Created");
				}
			
			}
			else if(tokens[0].equals("drop")){
				Delete obj=new Delete();
				if(obj.validate(tokens)){
					System.out.println("Table Deleted");
				}
			}
			else if(tokens[0].equals("print")){
				Print obj=new Print();
				if(obj.validate(tokens)){
					
				}
			}
			else if(tokens[0].equals("insert")){
				Insert obj=new Insert();
				if(obj.validate(tokens)){
				}
			}
			else if(tokens[0].equals("exit") || tokens[0].equals("quit")){
				if(tokens.length==1)
					System.exit(0);
				else
					System.out.println("Syntax incorrect");
			}
			else if(tokens[0].equals("select")){
				Select obj=new Select();
				if(obj.validate(tokens)){
					
				}
			}
        }
		else
			System.out.println("Invalid command!!!");
		
	}
	
}