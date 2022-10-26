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
    private static final String format = "|%1$-2s|%2$-20s|%3$-20s|%4$-10s|\n";
    public static ArrayList<song> add(ArrayList<song> songs, String[] usrAddSong) {
        songs.add(new song(usrAddSong[0], usrAddSong[1], Long.parseLong(usrAddSong[2])));
        System.out.println("Song added");
        return songs;
    }
    public static ArrayList<song> remove(ArrayList<song> songs) {
        manager.display(songs);
        System.out.println("Remove by :");
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.println("3. Artist (removes all songs by artist name)");
        switch (manager.readLine()) {
            case "1":
                int usrRemoveID = Character.getNumericValue(manager.readLine().charAt(0));
                System.out.println(usrRemoveID);
                if (usrRemoveID > 0 && usrRemoveID <= songs.size()) {
                    songs.remove(usrRemoveID - 1);
                    System.out.println("Song ID: " + usrRemoveID + " removed");
                } else {
                    System.out.println("Input out of range");
                    manager.remove(songs);
                }
                break;
        }
        return songs;
    }
    public static void display(ArrayList<song> songs) {
        System.out.format(format, "ID", "Name", "Artist", "Play count");
        for (int i = 0; i < songs.size(); i++) {
            System.out.format(format, i + 1, songs.get(i).sName, songs.get(i).sArtist, songs.get(i).sPlaycount);
        }
    }
    public static void clear() { // not sure if this works with all terminals
        System.out.print("\033[H\033[2J");  
        System.out.flush();   
    }
    public static String readLine() {
        return System.console().readLine();
    }
}
public class Main{
    static ArrayList<song> songs = new ArrayList<song>();
    public static String welcomeMsg(String[] msg) {
        return msg.length == 0 ? "Welcome to Song manager" : msg[0];
    }
    public static void check(Class<?> cls) {
        switch ((String)cls.getClass().getSimpleName()) {
            case "char":
                System.out.println("char");
                break;
            case "int":
                System.out.println("int");
                break;
            case "byte":
            case "long":
            case "double":
            case "boolean":
            case "short":
            case "float":
            default: //String
                System.out.println(cls);   
        }
    }
     public static void main(String ...args) {
         check(Array.class);
         System.out.println(welcomeMsg(args));
         System.out.println("Song manager menu\n");
         System.out.println("1. Add song");
         System.out.println("2. Remove song");
         System.out.println("3. View song(s)");
         System.out.println("4. Quit");
         switch (manager.readLine()) {
            case "1":
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
            case "2":
                songs = manager.remove(songs);
                main();
                break;
            case "3":
                manager.display(songs);
                main();
                break;
            case "4":
                System.exit(0);
                break;
            default:
                manager.clear();
                main("Invalid option provided\nPlease try again");
                break;
         }
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
