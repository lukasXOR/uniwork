package song.core;
import java.util.*;

/**
 * Class of methods for managing songs.
 */
public class manager {
    /**
        * Add a song
        *
        * @param songs The ArrayList of songs
        * @return ArrayList with the added song
    */
    public static ArrayList<song> add(ArrayList<song> songs, String[] usrAddSong) {
        songs.add(new song(usrAddSong[0], usrAddSong[1], Long.parseLong(usrAddSong[2])));
        return songs;
    }
    /**
        * Remove a song
        *
        * @return ArrayList with the removed song
    */
    public static ArrayList<song> remove(ArrayList<song> songs) {
        switch (utility.createMenu("Remove by: ", "ID", "Name", "Artist (remove all songs by artist name)")) {
            case "1": // Remove by ID
                try {
                    int usrRemoveID = Integer.parseInt(utility.createMenu("Song ID: "));
                    if (usrRemoveID > 0 && usrRemoveID <= songs.size()) {
                        songs.remove(usrRemoveID - 1);
                    } else {
                        utility.readLine(usrRemoveID + " Song ID doesnt exist\nPress enter to try again");
                        manager.remove(songs);
                    }
                } catch (Exception NumberFormatException) {
                    utility.readLine("Invalid option\nPress enter to try again");
                    manager.remove(songs);
                }
                break;
            case "2": // Remove by name
                String usrRemoveName = utility.createMenu("Song name: ").toLowerCase();
                boolean songFound = false;
                for (int i = 0; i < songs.size(); i++) {
                    if (songs.get(i).sName.toLowerCase().equals(usrRemoveName)) {
                        songs.remove(i);
                        songFound = true;
                    }
                }
                if (!songFound) {
                    utility.readLine(usrRemoveName + " not found\nPress enter to try again");
                    manager.remove(songs);
                }
                break;
            case "3": // Remove by artist
                String usrRemoveArtist = utility.createMenu("Song artist: ").toLowerCase();
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
                    utility.readLine("No songs found\nPress enter to try again");
                    manager.remove(songs);
                }
                break;
            default: // Invalid option so start again
                utility.clear();
                utility.readLine("Invalid option\nPress enter to try again");
                manager.remove(songs);
                
        }
        return songs;
    }
    /**
        * Display a song
        *
        * @param songs The ArrayList of songs
    */
    public static void display(ArrayList<song> songs) {
        String usrInput = utility.createMenu("Display by: ", "Time added", "Name", "Artist", "Playcount");
        utility.clear();
        switch (usrInput) {
            case "1": // normal format
                System.out.format(utility.format, "ID", "Artist", "Name", "Play count");
                for (int i = 0; i < songs.size(); i++)
                    System.out.format(utility.format, i + 1, songs.get(i).sArtist, songs.get(i).sName, songs.get(i).sPlaycount);
                utility.readLine("Press enter to return to menu.");
                break;
            case "2":
            case "3": // name/artist format
                int[] sortedSongNames = new int[songs.size()];
                for (int i = 0; i < songs.size(); i++) {
                    String sortType = usrInput.equals("2") ? songs.get(i).sName : songs.get(i).sArtist;
                    int sChar = sortType.toLowerCase().charAt(0);
                /* 
                encode the song id before the ascii Character
                so we can easily fetch the song id when it is sorted
                eg.  +/- -ID- --char--
                    0  1001 10100100...
                the main flaw about this is that i am only sorting the first
                character for each name/artist. if i was to come back to this 
                project i'd make sure it'd loop through each character
                */
                    sortedSongNames[i] = (i << 27) | sChar;
                }
                utility.sortedFormat(songs, utility.sort(sortedSongNames, 0, 7));
                break;
            case "4": // playcount format
                switch (utility.createMenu("Display play counts by: ", "Order", "Over given plays", "Under given plays")) {
                    case "1":
                        int[] sortedSongPlays = new int[songs.size()];
                        for (int i = 0; i < songs.size(); i++)
                            /*
                                encode the song id alongside the playcount
                                'int' uses 32 bits but the left most bit determines
                                the sign, therefore we must not use this bit and treat 
                                this as a 31 bit number so we dont get negative playcounts
                                eg. +/- -ID-  --playcount--
                                    0  1001  1001001100011...
                            */
                            sortedSongPlays[i] = (i << 27) | (int)(songs.get(i).sPlaycount >> 5L);
                        utility.sortedFormat(songs, utility.sort(sortedSongPlays, 0, 26));
                        break;
                    case "2":
                    case "3": // custom play counts
                        String sign = utility.createMenu("Enter sign with number of plays\neg.\nfor over 2,000,000 plays '>2000000'\nfor under 2,000,000 plays '<2000000'");
                        long customPlays = 0;
                        try {
                            customPlays = Long.parseLong(sign.substring(1).replace(",", ""));
                        } catch (Exception NumberFormatException) {
                            utility.readLine("Count provided was not a number\nPress enter to try again");
                            manager.display(songs);
                        }
                        if (sign.startsWith("<")) 
                            sign = "<";
                        else if (sign.startsWith(">")) 
                            sign = ">";
                        else {
                            utility.readLine("Sign provided was invalid (Use < or >)\nPress enter to continue");
                            manager.display(songs);
                        }
                        // Checks passed... filter songs
                        utility.clear();
                        System.out.format(utility.format, "ID", "Artist", "Name", "Play count");
                        int songIndex = 0;
                        boolean songAppended = false;
                        do {
                            if (sign.equals(">"))
                                if (songs.get(songIndex).sPlaycount > customPlays) {
                                    System.out.format(utility.format, songIndex + 1, songs.get(songIndex).sArtist, songs.get(songIndex).sName, songs.get(songIndex).sPlaycount);
                                    songAppended = true;
                                }
                            if (sign.equals("<"))
                                if (songs.get(songIndex).sPlaycount < customPlays) {
                                    System.out.format(utility.format, songIndex + 1, songs.get(songIndex).sArtist, songs.get(songIndex).sName, songs.get(songIndex).sPlaycount);
                                    songAppended = true;
                                }
                        } while (songIndex++ < songs.size() - 1);
                        if (songAppended)
                            System.out.println("Showing songs " + (sign == "<" ? "under " : "over ") + customPlays + " plays");
                        else
                            System.out.format(utility.format, 0, "NO", "SONGS", "FOUND");
                        utility.readLine("Press enter to return to menu.");
                    break;
                    default:
                        utility.clear();
                        utility.readLine("Invalid option\nPress enter to try again");
                        manager.display(songs);
                }
                break;
            default:
                utility.clear();
                utility.readLine("Invalid option\nPress enter to try again");
                manager.display(songs);
        } 
    }
    
}
