package base;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Folder implements Comparable<Folder>{

	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name){
		this.name=name;
		notes = new ArrayList<Note>();
		
	}
	
	public void addNote(Note note){
		
		notes.add(note);
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<Note> getNotes(){
		return notes;
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		return result;
//	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() { 
		int nText = 0;
	    int nImage = 0;
	    // TODO
	    for (Note n : this.notes){
	    	if (n instanceof TextNote){
	    		nText++;
	    	}else{
	    		nImage++;
	    	}
	    }
	    return name + ":" + nText + ":" + nImage; }
	
	@Override
	public int compareTo(Folder o){
		if(this.name.compareTo(o.name)==0){
			return 0;
		}else if(this.name.compareTo(o.name)>0) {
			return 1;
		}else{
			return -1;
		}		
	}
	
	public void sortNotes(){
		
		Collections.sort(notes);
		
	}
	

	
	public List<Note> searchNotes(String keywords){
		ArrayList<Note> returnList=new ArrayList<Note>();
		ArrayList<String> keyarray=new ArrayList<String>();
		ArrayList<String> wordarray=new ArrayList<String>();
		for( String word : keywords.split(" ")){
			keyarray.add(word.toLowerCase());
		}
		
		for (Note n : notes){	
			for( String word : n.getTitle().split(" ")){
				wordarray.add(word.toLowerCase());
			}
			if(n instanceof TextNote){
				TextNote textn=(TextNote)n;
				for( String word : textn.getContent().split(" ")){
					wordarray.add(word.toLowerCase());
				}
			}
			boolean pass=true;
			int count=0;
			int status=0;// 0=neutral 1=previous pass 2=previous miss 3=pass current
			for(String key: keyarray){
				count++;
				
				if(key.equals("or")){
					if(status==1){status=3;}
					if(status==2){status=0;}
					continue;
				}else{
					if(status==1){status=0;}
					if(status==2){pass=false;break;}
					if(status==3){status=0;continue;}
				}
				for (String word : wordarray){
					status=2;
					word=word.toLowerCase();
					if(key.equals(word)){				
						status=1;
						break;
					}
				}
				
				if(count==keyarray.size()){
					if(status==2){pass=false;}
				}
			}
			if(pass){
				returnList.add(n);
			}
		}
		
		return returnList;

	}
}
