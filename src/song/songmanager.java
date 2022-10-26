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
public class HelloWorld{
    static final String format = "|%1$-15s|%2$-15s|%3$-15s|\n";
    static ArrayList<song> songs = new ArrayList<song>();
    /*
    Ed Sheeran-Shape of You-3,746,122,511
	The Weeknd-Blinding Lights-3,288,094,770
	Ed Sheeran-Perfect-3,224,789,370
	Luis Fonsi-Despacito 3,112,243,015
	Tones And I-Dance Monkey-2,719,548,764
	Lewis Capaldi-Someone You Loved-2,609,765,817
	Post Malone-rockstar (feat. 21 Savage)-2,569,898,717
	Post Malone-Sunflower Spider-Man: Into the Spider Verse-2,500,482,535
	Lil Nas X-Old Town Road-2,490,171,174
	Billie Eilish-bad guy-2,480,025,293

*/
    static {
        String baseSongs[] = retrieveBaseSongs().split("\r?\\n");
        for (int i = 0; i < baseSongs.length; i++) {
            String song[] = baseSongs[i].split("-");
            String name = song[0];
            String artist = song[1];
            Long playcount = Long.parseLong(song[2].replace(",",""));
            songs.add(new song(name, artist, playcount));
        }  
        //songs.add(new song("Ed Sheeran", "Shapeof ", 12321L));
    }
    public static String retrieveBaseSongs() {
        return 
        "Ed Sheeran-Shape of You-3,746,122,511\n" +
        "The Weeknd-Blinding Lights-3,288,094,770\n" +
        "Ed Sheeran-Perfect-3,224,789,370\n" +
        "Luis Fonsi-Despacito-3,112,243,015\n" +
        "Tones And I-Dance Monkey-2,719,548,764\n" +
        "Lewis Capaldi-Someone You Loved-2,609,765,817\n" +
        "Post Malone-rockstar (feat. 21 Savage)-2,569,898,717\n" +
        "Post Malone-Sunflower SpiderMan: Into the Spider Verse-2,500,482,535\n" +
        "Lil Nas X-Old Town Road-2,490,171,174\n" +
	    "Billie Eilish-bad guy-2,480,025,293\n";
    } 
     public static void main(String []args){
        display();
     }
     public static void display() {
         System.out.format(format, "Name", "Artist", "Play count");
         for (song s: songs) {
            System.out.format(format, s.sName, s.sArtist, s.sPlaycount);
         }
     }
}
