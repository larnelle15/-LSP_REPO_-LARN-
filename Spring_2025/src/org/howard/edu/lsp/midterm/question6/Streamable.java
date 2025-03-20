package org.howard.edu.lsp.midterm.question6;

// Defining common behaviors for all media types
interface Streamable {
    void play();   // Start playing the media
    void pause();  // Pause the currently playing media
    void stop();   // Stop playback completely
}