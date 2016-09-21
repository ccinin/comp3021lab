package base;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class NoteBook {

	private ArrayList<Folder> folders;
	
	public NoteBook(){
		folders=new ArrayList<Folder>();
	}
	
	public boolean insertNote(String folderName , Note note){
		Folder f =null;
		for(Folder f1 :folders){
			if(f1.equals(new Folder(folderName))){
				f=f1;
			}
		}
		if(f==null){
			f=new Folder(folderName);
			folders.add(f);
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
}
