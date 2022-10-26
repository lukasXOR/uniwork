import java.util.*;
class song {
    String sName;
    String sArtist;
    long sPlaycount; // top songs have playcount over 'int' range
    public song(String name, String artist, long playCount) { // constructor
        this.sName = name;
        this.sArtist = artist;                                                        
        this.sPlaycount = playCount;
    }
}
class manager {
    private static final String format = "|%1$-20s|%2$-20s|%3$-15s|\n";
    public static ArrayList<song> add(ArrayList<song> songs, String[] usrAddSong) {
        songs.add(new song(usrAddSong[0], usrAddSong[1], Long.parseLong(usrAddSong[2])));
        System.out.println("Song added");
        return songs;
    }
    public static void remove() {
        manager.clear();
        System.out.println("Remove by :")
        System.out.println("1. Index")
        System.out.println("2. Name")
        System.out.println("3. Artist (removes all songs by artist name)")
    }
    public static void display(ArrayList<song> songs) {
        System.out.format(format, "Name", "Artist", "Play count");
         for (song s: songs) {
            System.out.format(format, s.sName, s.sArtist, s.sPlaycount);
         }
    }
    public static void clear() { // not sure if this works with all terminals
        System.out.print("\033[H\033[2J");  
        System.out.flush();   
    }
}
public class Main{
    static ArrayList<song> songs = new ArrayList<song>();
    public static String welcomeMsg(String[] msg) {
        return msg.length == 0 ? "Welcome to Song manager" : msg[0];
    }
     public static void main(String ...args) {
         System.out.println(welcomeMsg(args));
         System.out.println("Song manager menu\n");
         System.out.println("1. Add song");
         System.out.println("2. Remove song");
         System.out.println("3. View song(s)");
         System.out.println("4. Quit");
         switch ((int)System.console().readLine().charAt(0)) {
            case 49: //1
                System.out.println("Enter the song with format being 'name-artist-playcount'");
                String usrSong = System.console().readLine();
                String[] usrSongInfo = usrSong.split("-");
                if (usrSongInfo.length != 3) main("Invalid format provided");
                try {
                    long playcount = Long.parseLong(usrSongInfo[2], 10);
                    songs = manager.add(songs, usrSongInfo);
                    main();
                } catch (Exception NumberFormatException) {
                    main("Play count provided was not a number");
                }
                break;
            case 50: //2
                songs = manager.remove();
                break;
            case 51: //3
                manager.display(songs);
                main();
                break;
            case 52: //4
                System.exit(0);
                break;
            default:
                manager.clear();
                main("Invalid option provided\nPlease try again");
                break;
         }
     }
     public static void display() {
        manager.display(songs);
     }
     
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
}
