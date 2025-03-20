package org.howard.edu.lsp.midterm.question6;

// Audiobook class 
class Audiobook implements Streamable {
    private String title;  // Storing the title of the audiobook
    
    // Assigning an Audiobook object with a title
    public Audiobook(String title) {
        this.title = title;
    }
    
    //Streamable interface methods
    public void play() { System.out.println("Playing audiobook: " + title); }
    public void pause() { System.out.println("Paused audiobook: " + title); }
    public void stop() { System.out.println("Stopped audiobook: " + title); }
    
    // Adjusting playback speed
    public void setPlaybackSpeed(double speed) {
        System.out.println("Setting playback speed of audiobook: " + title + " " + speed + "x");
    }
}