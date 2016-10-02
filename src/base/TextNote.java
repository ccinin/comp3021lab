package base;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter; 
import java.io.FileWriter;
import java.util.Scanner;
import java.io.PrintWriter;

public class TextNote extends Note{
	private static final long serialVersionUID = 1L;
	protected String content;
	
	public TextNote (String title) {
		super(title);
		content="";
	}
	
	public TextNote (String title, String content){
		super(title);
		this.content=content;
	}
	
	public TextNote(File f){
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	public String getContent(){
		return content;
	}
	
	private String getTextFromFile(String absolutePath){
		String result = "";
		Scanner input=null;
		try{
		     input = new Scanner(new File(absolutePath + super.getTitle()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (input!=null){
		   while (input.hasNext()){
			   System.out.print(input.next() + " ");
		   }		
		   input.close(); 
		}
		return result;
	}

	public void exportTextToFile(String pathFolder){
		String thisTitle=super.getTitle().replaceAll(" ", "_");
		File file =null;
        if(pathFolder==""){
		    file = new File(pathFolder+ thisTitle +".txt");
        }else{
        	file = new File(pathFolder + File.separator+ thisTitle +".txt");
        }
//		if (file.exists()) {
//		   System.out.println("File exists");
//		   System.exit(0); 
//		}
		PrintWriter output=null;
		try{
		   output = new PrintWriter(file); 
		}catch (Exception e){
			
			e.printStackTrace();
		}
		if(output!=null){
		   output.print(content);
		   output.close(); 
		}
	}
}
