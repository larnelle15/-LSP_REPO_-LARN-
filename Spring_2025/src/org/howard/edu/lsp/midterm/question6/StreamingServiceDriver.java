package org.howard.edu.lsp.midterm.question6;

// Driver class to test the streaming service functionality
public class StreamingServiceDriver {
    public static void main(String[] args) {
        // Creating instances of different media types
        Streamable music = new Music("Still a Friend - Incognito");
        Streamable movie = new Movie("Avengers End Game");
        Streamable audiobook = new Audiobook("A Promised Land – Barack Obama");
        
        // Testing common behaviors for Music
        System.out.println("Testing Music:");
        music.play(); music.pause(); music.stop();
        System.out.println();
        
        // Testing common behaviors for Movie
        System.out.println("Testing Movie:");
        movie.play(); movie.pause(); movie.stop();
        System.out.println();
        
        // Testing common behaviors for Audiobook
        System.out.println("Testing Audiobook:");
        audiobook.play(); audiobook.pause(); audiobook.stop();
        System.out.println();
        
        // Testing unique behaviors
        ((Movie)movie).rewind(30);                    // Movie-specific 
        ((Audiobook)audiobook).setPlaybackSpeed(1.5); // Audiobook-specific 
    }
}
