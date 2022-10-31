package song.core;

/**
 * Class for storing song information.
 *
 * @param artist Name of artist.
 * @param name Name of song.
 * @param long Play count of song.
 */
public class song {
    String sName;
    String sArtist;
    long sPlaycount; // top songs have playcount over 'int' range
    public song(String artist, String name, long playCount) { // constructor
        this.sArtist = artist;
        this.sName = name;                                                        
        this.sPlaycount = playCount;
    }
}
