import java.util.*;
import core.*;
/**
    * Song manager that emulates basic music streaming features
    * @author lukas
    This file and all files in 'core' are written by me
 */

public class music {
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
         switch (utility.createMenu(welcomeMsg(args), "Add song", "Remove song", "View song(s)", "Quit")) {
            case "1": // Add song
                String usrSong = utility.createMenu("Enter the song with the format 'artist-name-playcount'");
                String[] usrSongInfo = usrSong.split("-");
                if (usrSongInfo.length != 3) main("Invalid format provided");
                try {
                    Long.parseLong(usrSongInfo[2], 10);
                    songs = manager.add(songs, usrSongInfo);
                    main("Song added");
                } catch (Exception NumberFormatException) {
                    main("Play count provided was not a number");
                }
                break;
            case "2": // Remove song
                songs = manager.remove(songs);
                main("Song removed");
                break;
            case "3": // Display song
                manager.display(songs);
                main();
                break;
            case "4": // Exit program
                utility.clear();
                System.out.println("Thank you for using song manager.");
                System.exit(0);
                break;
            default: // Invalid option try again
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
        String baseSongs[] = utility.retrieveBaseSongs().split("\r?\\n");
        for (int i = 0; i < baseSongs.length; i++) {
            String song[] = baseSongs[i].split("-");
            String artist = song[0];
            String name = song[1];
            Long playcount = Long.parseLong(song[2].replace(",",""));
            songs.add(new song(artist, name, playcount));
        }  
    }
}