import java.util.*;
class song {
    String sName;
    String sArtist;
    long sPlaycount; // top songs have playcount over 'int' range
    public song(String artist, String name, long playCount) { // constructor
        this.sArtist = artist;
        this.sName = name;                                                        
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
        switch (manager.readLine("")) {
            case "1":
                int usrRemoveID = Integer.parseInt(manager.readLine("Song ID: "));
                System.out.println(usrRemoveID);
                if (usrRemoveID > 0 && usrRemoveID <= songs.size()) {
                    songs.remove(usrRemoveID - 1);
                    System.out.println("Song ID: " + usrRemoveID + " removed");
                } else {
                    System.out.println("Input out of range");
                    manager.remove(songs);
                }
                break;
            case "2":
                String usrRemoveName = manager.readLine("Song name: ").toLowerCase();
                boolean songFound = false;
                for (int i = 0; i < songs.size(); i++) {
                    if (songs.get(i).sName.toLowerCase().equals(usrRemoveName)) {
                        songs.remove(i);
                        songFound = true;
                    }
                }
                if (!songFound) {
                    System.out.println(usrRemoveName + " not found");
                    manager.remove(songs);
                } else {
                    System.out.println(usrRemoveName + " removed");
                }
            case "3":
                String usrRemoveArtist = manager.readLine("Song artist: ").toLowerCase();
                int songsRemoved = 0;
                for (int i = 0; i < songs.size(); i++) {
                    if (songs.get(i).sArtist.toLowerCase().equals(usrRemoveArtist)) {
                        songs.remove(i);
                        i--; 
                        /*
                        'songs.remove() will change the size of the array when an
                        element in removed, therefore if there are more than 1 song
                        with the same artist, push the index back by one incase if the following
                        song has the same artist as 'songs.get()' will fetch the new array
                        */
                        songsRemoved++;
                    }
                }
                if (songsRemoved == 0) {
                    System.out.println("No songs found from artist: " + usrRemoveArtist);
                    manager.remove(songs);
                } else {
                    System.out.println(songsRemoved + " songs removed from " + usrRemoveArtist);
                }
                break;
            default:
                manager.clear();
                System.out.println("Invalid option");
                manager.remove(songs);
                
        }
        return songs;
    }
    /*
    My attempt of implementing Radix LSD sort
    */
    public static int[] sort(int[] chars, int bitIndex) {
        int j = 0, z = 0, h = 0;
        int[][] buckets = new int[2][];
        /* 
        Initiliase buckets
        */
        for (int i = 0; i < buckets.length; i++) 
            buckets[i] = new int[chars.length];
        
        /*
        Get bit status and add it to respective bucket
        */
        for (int i = 0; i < chars.length; i++) 
            buckets[(chars[i] >> bitIndex) & 1][(((chars[i] >> bitIndex) & 1) > 0) ? z++ : j++] = chars[i];
        
        /*
        Instead of creating a new sorted array, use the existing
        0 bit array and append the rest of the empty values from 
        the 1 bit array. This will use less memory.
        */
        for (int i = chars.length - z, k = 0; i < chars.length; i++)
            buckets[0][i] = buckets[1][k++];
            
        /*
        Return sorted array recursively
        I will only use ASCII codes for this function therefore we will
        only need to go to 8 bits which then the whole array is sorted.
        */
        return bitIndex == 7 ? buckets[0] : sort(buckets[0], ++bitIndex);
    }
    public static void display(ArrayList<song> songs) {
        manger.display
        int test[] = {69, 84, 69, 76, 84, 76, 80, 80, 76, 66};
        int[] sortedSongs = new int[songs.size()];
        for (int i = 0; i < songs.size(); i++) {
            int sChar = songs.get(i).sArtist.charAt(0);
            sortedSongs[i] = (i << 8) | sChar;
        }
        sortedSongs = manager.sort(sortedSongs, 0);
        for (int i = 0; i < sortedSongs.length; i++) {
            //System.out.print((sortedSongs[i] >> 8) + " ");
            //System.out.println((sortedSongs[i] & 0xFF) + " ");
            System.out.println(songs.get(sortedSongs[i] >> 8).sArtist);
        }
        /*
        System.out.format(format, "ID", "Artist", "Name", "Play count");
        for (int i = 0; i < songs.size(); i++) {
            System.out.format(format, i + 1, songs.get(i).sArtist, songs.get(i).sName, songs.get(i).sPlaycount);
        }
        */
    }
    public static void clear() { // not sure if this works with all terminals
        System.out.print("\033[H\033[2J");  
        System.out.flush();   
    }
    public static String readLine(String msg) {
        System.out.print(msg.length() == 0 ? "Input: " : msg);
        return System.console().readLine();
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
         switch (manager.readLine("")) {
            case "1":
                System.out.println("Enter the song with format being 'artist-name-playcount'");
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
            String artist = song[0];
            String name = song[1];
            Long playcount = Long.parseLong(song[2].replace(",",""));
            songs.add(new song(artist, name, playcount));
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
