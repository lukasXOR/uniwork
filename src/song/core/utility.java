package song.core;
import java.util.*;
/**
    * Class for methods that depend managing the songs 
 */
public class utility {
    /**
        * Formatting expression to pretty print tables
    */
    public static final String format = "|%1$-2s|%2$-20s|%3$-20s|%4$-10s|\n"; 

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
        * Format songs into a table
        * @param songs Song array
        * @param sortedIndex Array with sorted Song IDs
    */
    public static void sortedFormat(ArrayList<song> songs, int[] sortedIndex) {
        System.out.format(utility.format, "ID", "Artist", "Name", "Play count");
        for (int song : sortedIndex) {
            int songIndex = song >> 27;
            song sInfo = songs.get(songIndex);
            System.out.format(format, songIndex + 1, sInfo.sArtist, sInfo.sName, sInfo.sPlaycount);
        }
        readLine("Press enter to return to menu.");
    }
    /**
        * Base songs to be added to the ArrayList
        *
        * @return string of base songs
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
        * Custom welcome message
        *
        * @param message to be outputted if theres no message provided then output default message
        * @return welcome message
    */
    public static String welcomeMsg(String[] msg) {
        return msg.length == 0 ? "Welcome to Song manager" : msg[0];
    }
    /**
        * Clear console/terminal
    */
    public static void clear() { // not sure if this works with all terminals
        System.out.print("\033[H\033[2J");  
        System.out.flush();   
        /*
        * Alternative method
        * for (int i = 0; i < 50; i++) System.out.println();
        */
    }
    /**
        * Get the users input while displaying a message
        * @param msg
        * @return User input as string
     */
    public static String readLine(String msg) {
        System.out.print(msg);
        return System.console().readLine();
    }
    /**
        * Create numbered menu with users input
        * @param msg Array of options
        * @return User input from menu
    */
    public static String createMenu(String ...msg) {
        clear();
        int option = 0;
        while (option < msg.length)
            if (option == 0)
                System.out.println(msg[option++]);
            else 
                System.out.println(option + ". " + msg[option++]);
        return readLine("Input: ");
    }
}
