import java.util.*;
/**
 * Class for storing song information.
 *
 * @param artist Name of artist.
 * @param name Name of song.
 * @param long Play count of song.
 */
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
/**
 * Class for core functions.
 */
class manager {
    private static final String format = "|%1$-2s|%2$-20s|%3$-20s|%4$-10s|\n"; // Formatting expression to pretty print tables
/**
    * Add a song
    *
    * @param songs The ArrayList of songs
    * @return ArrayList with the added song
*/
    public static ArrayList<song> add(ArrayList<song> songs, String[] usrAddSong) {
        songs.add(new song(usrAddSong[0], usrAddSong[1], Long.parseLong(usrAddSong[2])));
        System.out.println("Song added");
        return songs;
    }
/**
    * Remove a song
    *
    * @return ArrayList with the removed song
*/
    public static ArrayList<song> remove(ArrayList<song> songs) {
        System.out.println("Remove by :");
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.println("3. Artist (removes all songs by artist name)");
        switch (manager.readLine("")) {
            case "1": // Remove by ID
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
            case "2": // Remove by name
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
            case "3": // Remove by artist
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
            default: // Invalid option so start again
                manager.clear();
                System.out.println("Invalid option");
                manager.remove(songs);
                
        }
        return songs;
    }
/**
    * My attempt of implementng Radix Sort LSD (least significant digit)
    * 
    * @param chars unsorted song
    * @param bitStart position of starting bit sorted
    * @param bitEnd position of ending bit to be sorted
    * @return sorted song array
*/
    public static int[] sort(int[] chars, int bitStart, int bitEnd) {
        int zeroBits = 0, oneBits = 0; // Keep track of bit states
        /* 
        Initiliase bases
        */
        int[][] base = new int[2][]; // Base 2
        for (int i = 0; i < base.length; i++) 
            base[i] = new int[chars.length];
        
        /*
        Get bit status and add it to respective bucket
        */
        for (int i = 0; i < chars.length; i++) 
            base[(chars[i] >> bitStart) & 1][(((chars[i] >> bitStart) & 1) > 0) ? oneBits++ : zeroBits++] = chars[i];
        
        /*
        Instead of creating a new sorted array, use the existing
        0 bit array and append the rest of the empty values from 
        the 1 bit array. This will use less memory.
        */
        for (int i = chars.length - oneBits, k = 0; i < chars.length; i++)
            base[0][i] = base[1][k++];
            
        /*
        Return sorted array recursively
        */
        return bitStart == bitEnd ? base[0] : sort(base[0], ++bitStart, bitEnd);
    }
    /**
    * Display a song
    *
    * @param songs The ArrayList of songs
*/
    public static void display(ArrayList<song> songs) {
        System.out.println("Display by: ");
        System.out.println("1. Time added");
        System.out.println("2. Name");
        System.out.println("3. Artist");
        System.out.println("4. Play count");
        String usrInput = manager.readLine("");
        switch (usrInput) {
            case "1": // normal format
                System.out.format(format, "ID", "Artist", "Name", "Play count");
                for (int i = 0; i < songs.size(); i++)
                    System.out.format(format, i + 1, songs.get(i).sArtist, songs.get(i).sName, songs.get(i).sPlaycount);
                break;
            case "2":
            case "3": // name/artist format
                int[] sortedSongs = new int[songs.size()];
                for (int i = 0; i < songs.size(); i++) {
                    String sortType = usrInput.equals("2") ? songs.get(i).sName : songs.get(i).sArtist;
                    int sChar = sortType.toLowerCase().charAt(0);
                    /* encode the song id before the ascii Character
                    so we can easily fetch the song id when it is sorted
                    eg.   --ID-- --char--
                       ...101001 10100100
                    */
                    sortedSongs[i] = (i << 8) | sChar;
                }
                sortedFormat(songs, manager.sort(sortedSongs, 0, 7));
                break;
            case "4": // playcount format
                /*
                long playcounts into into
                */
                break;
        } 
    }
    /*
    Format songs into a table
    @param 
    */
    public static void sortedFormat(ArrayList<song> songs, int[] sortedIndex) {
        System.out.format(format, "ID", "Artist", "Name", "Play count");
        for (int i = 0; i < songs.size(); i++) {
            int ID = sortedIndex[i] >> 8; // Remove the ASCII char (8 bits) which will leave us the ID
            System.out.format(format, ID + 1, songs.get(ID).sArtist, songs.get(ID).sName, songs.get(ID).sPlaycount);
        }
    }
    /**
    * Base songs to be added to the ArrayList
    *
    * @return list of base songs
*/
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
/**
    * Clear console/terminal
*/
    public static void clear() { // not sure if this works with all terminals
        System.out.print("\033[H\033[2J");  
        System.out.flush();   
    }
    /**
    * Read line as a user input
    *
    * @param message to outputted
    * @return line inputted as String
*/
    public static String readLine(String msg) {
        System.out.print(msg.length() == 0 ? "Input: " : msg);
        return System.console().readLine();
    }
}
public class Main{
    static ArrayList<song> songs = new ArrayList<song>(); // Main songs store
    /**
    * Custom welcome message
    *
    * @param message to be outputted if theres no message provided then output default message
    * @return welcome message
*/
    public static String welcomeMsg(String[] msg) {
        return msg.length == 0 ? "Welcome to Song manager" : msg[0];
    }
    /**
    * main method
    *
    * @param String array
*/
     public static void main(String ...args) {
         System.out.println(welcomeMsg(args));
         System.out.println("Song manager menu\n");
         System.out.println("1. Add song");
         System.out.println("2. Remove song");
         System.out.println("3. View song(s)");
         System.out.println("4. Quit");
         switch (manager.readLine("")) {
            case "1": // Add song
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
            case "2": // Remove song
                songs = manager.remove(songs);
                main();
                break;
            case "3": // Display song
                manager.display(songs);
                main();
                break;
            case "4": // Exit program
                System.exit(0);
                break;
            default: // Invalid option try again
                manager.clear();
                main("Invalid option provided\nPlease try again");
                break;
         }
     }
/**
    * Initialiser function
    *
    * This function will be ran first even before main() so here
    * we can set the base songs that are needed.
*/
    static { //Initiliase base songs 
        String baseSongs[] = manager.retrieveBaseSongs().split("\r?\\n");
        for (int i = 0; i < baseSongs.length; i++) {
            String song[] = baseSongs[i].split("-");
            String artist = song[0];
            String name = song[1];
            Long playcount = Long.parseLong(song[2].replace(",",""));
            songs.add(new song(artist, name, playcount));
        }  
    }
}
