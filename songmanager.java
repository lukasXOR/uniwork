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
public class songmanager {
    static final String format = "|%1$-15s|%2$-15s|%3$-15s|\n";
    static ArrayList<song> songs = new ArrayList<song>();
    /*
    
    Ed Sheeran	Shape of You	3,746,122,511	985,618	2017
		The Weeknd	Blinding Lights	3,288,094,770	1,715,626
		Ed Sheeran	Perfect	3,224,789,370	1,346,254	2017
		Luis Fonsi	Despacito	3,112,243,015	678,809	2017
		Tones And I	Dance Monkey	2,719,548,764	678,167	
		Lewis Capaldi	Someone You Loved	2,609,765,817	1,289,611	2018
		Post Malone	rockstar (feat. 21 Savage)	2,569,898,717	729,633	2017
		Post Malone	Sunflower - Spider-Man: Into the Spider-Verse	2,500,482,535	1,010,713	2018
9		Lil Nas X	Old Town Road	2,490,171,174	639,539	2019
10		Billie Eilish	bad guy	2,480,025,293	697,908	2019
11
*/
    static {
        songs.add(new song("Ed Sheeran", "3", 12321L));
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
