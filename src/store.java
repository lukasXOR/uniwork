import java.util.ArrayList;
class song {
    String songName;
    String songArtist;
    String playCount;
    
    public song(String sName, String sArtist, String pCount) { //constructor
        this.songName = sName;
        this.songArtist = sArtist;
        this.playCount = pCount;
    }
    public void display() {
        System.out.print("name: "+ this.songName);
        System.out.print(" artist: "+ this.songArtist);
        System.out.print(" play count: "+ this.playCount + "\n");
    }
}
public class store {
    public static void main(String[] args) throws Exception{
        ArrayList<song> songs = new ArrayList<song>();
        songs.add(new song("test1","test2","test3"));

        //display all songs
        for (int i = 0; i < songs.size(); i++) {
            songs.get(i).display();
        }
        //add song

        
        //remove song
    }
}