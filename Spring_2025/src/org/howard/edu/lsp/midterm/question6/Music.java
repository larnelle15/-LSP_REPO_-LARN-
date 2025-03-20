package org.howard.edu.lsp.midterm.question6;

// Music class - represents songs/tracks that can be streamed
class Music implements Streamable {
    private String title;  // Storing the title of the song
    
    // Assigning a Music object with a title
    public Music(String title) {
        this.title = title;
    }
    
    // Streamable interface methods
    public void play() { System.out.println("Playing music: " + title); }
    public void pause() { System.out.println("Paused music: " + title); }
    public void stop() { System.out.println("Stopped music: " + title); }
    
    // Adding songs to playlists
    public void addToPlaylist(String playlist) {
        System.out.println("Added " + title + " to " + playlist + " playlist");
    }
}