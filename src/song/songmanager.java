import java.util.*;
class song {
    String sName;
    String sArtist;
    long sPlaycount; // top songs have playcount over 'int' range
    public song(String name, String artist, long playCount) {
        this.sName = name;
        this.sArtist = artist;
        this.sPlaycount = playCount;
    }
}
class manager {
    private static final String format = "|%1$-20s|%2$-20s|%3$-15s|\n";
    public static void add() {
        
    }
    public static void remove() {
        
    }
    public static void display(ArrayList<song> songs) {
        System.out.format(format, "Name", "Artist", "Play count");
         for (song s: songs) {
            System.out.format(format, s.sName, s.sArtist, s.sPlaycount);
         }
    }
}
public class HelloWorld{
    static ArrayList<song> songs = new ArrayList<song>();

    static { //Initiliase base songs 
        String baseSongs[] = retrieveBaseSongs().split("\r?\\n");
        for (int i = 0; i < baseSongs.length; i++) {
            String song[] = baseSongs[i].split("-");
            String name = song[0];
            String artist = song[1];
            Long playcount = Long.parseLong(song[2].replace(",",""));
            songs.add(new song(name, artist, playcount));
        }  
    }
    public static String retrieveBaseSongs() {
        return 
        "Ed Sheeran-Shape of You-3,746,122,511\n" +
        "The Weeknd-Blinding Lights-3,288,094,770\n" +
        "Ed Sheeran-Perfect-3,224,789,370\n" +
        "Luis Fonsi-Despacito-3,112,243,015\n" +
        "Tones And I-Dance Monkey-2,719,548,764\n" +
        "Lewis Capaldi-Someone You Loved-2,609,765,817\n" +
        "Post Malone-rockstar-2,569,898,717\n" +
        "Post Malone-Sunflower SpiderMan-2,500,482,535\n" +
        "Lil Nas X-Old Town Road-2,490,171,174\n" +
	    "Billie Eilish-bad guy-2,480,025,293\n";
    } 
     public static void main(String []args) throws Exception{
         System.out.println("Song manager menu");
         System.out.println("1. Add song");
         System.out.println("2. Remove song");
         System.out.println("3. View song(s)");
         System.out.println("4. Quit");
         String usrInput = System.console().readLine();
         System.out.println(usrInput.charAt(0));
     }
     public static void display() {
        manager.display(songs);
     }
}
