package org.howard.edu.lsp.midterm.question6;

// Movie class - represents films that can be streamed
class Movie implements Streamable {
    private String title;  // Storing the title of the movie
    
    // Assigning movie objects with titles
    public Movie(String title) {
        this.title = title;
    }
    
    // Streamable interface methods
    public void play() { System.out.println("Playing movie: " + title); }
    public void pause() { System.out.println("Paused movie: " + title); }
    public void stop() { System.out.println("Stopped movie: " + title); }
    
    // Rewinding the playback
    public void rewind(int seconds) {
        System.out.println("Rewinding movie: " + title + " by " + seconds + " seconds");
    }
}
