package base;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class NoteBook implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Folder> folders;
	
	public NoteBook(){
		folders=new ArrayList<Folder>();
	}
	
	public NoteBook(String file){
		FileInputStream fis = null;
		ObjectInputStream in = null;
        try {
        	fis = new FileInputStream(file); 
        	in = new ObjectInputStream(fis);
        	NoteBook n = (NoteBook) in.readObject();
        	this.folders=n.folders;
        	in.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
	
	public boolean insertNote(String folderName , Note note){
		Folder f =null;
		Folder temp = new Folder(folderName);
		for(Folder f1 :folders){
			if(f1.equals(temp)){
				f=f1;
				break;
			}
		}
		if(f==null){
			f=new Folder(folderName);
			f.addNote(note);
			folders.add(f);
			return true;
		}
		for (Note n : f.getNotes()){
			if(n.equals(note)){
			   System.out.println("Creating note "+ note.getTitle() + " under folder "+folderName+" failed");
			   return false;
			}
		}
		f.addNote(note);
		return true;
	}
	
	public boolean createTextNote(String folderName, String title){
		TextNote note = new TextNote(title);
		return insertNote(folderName,note);
	}
	
	//overloading
	public boolean createTextNote(String folderName, String title, String content){
		TextNote note = new TextNote(title,content);
		return insertNote(folderName,note);
	}
    
	public boolean createImageNote(String folderName,String title){
		ImageNote note= new ImageNote(title);
		return insertNote(folderName,note);
	}
	
	public ArrayList<Folder> getFolders(){
		return folders;
	}
	
	public void sortFolders(){
		for(Folder f: folders){
			f.sortNotes();
		}
		Collections.sort(folders);
	}
	
	public List<Note> searchNotes(String keywords){
		List<Note> combinedlist=new ArrayList<Note>();
		for(Folder f:folders){
			combinedlist.addAll(f.searchNotes(keywords));
		}
		return combinedlist;
	}
	
	public boolean save(String file){ 
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		    return false;
		}
		return true;
	}
}
