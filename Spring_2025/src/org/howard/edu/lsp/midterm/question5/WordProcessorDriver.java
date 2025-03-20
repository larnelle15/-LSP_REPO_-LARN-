package org.howard.edu.lsp.midterm.question5;

import java.util.ArrayList;
import java.util.List;

public class WordProcessorDriver {
    public static void main(String[] args) {
        // Test case 1: Sentence with single longest word
        WordProcessor wp1 = new WordProcessor("This is a test with amazing results amazing achievements");
        System.out.println("Test 1 - Longest words: " + wp1.findLongestWords()); 
        
        // Test case 2: Sentence with multiple occurrences of the same longest word
        WordProcessor wp2 = new WordProcessor("Java is a powerful powerful tool");
        System.out.println("Test 2 - Longest words: " + wp2.findLongestWords()); 
        
        // Test case 3: Sentence with single character words
        WordProcessor wp3 = new WordProcessor("A B C D E");
        System.out.println("Test 3 - Longest words: " + wp3.findLongestWords()); 
        
        // Test case 4: Empty string input
        WordProcessor wp4 = new WordProcessor("");
        System.out.println("Test 4 - Longest words: " + wp4.findLongestWords()); 
    }
}

class WordProcessor {
    private String sentence;
    
    public WordProcessor(String sentence) {
        this.sentence = sentence;
    }
    
    public List<String> findLongestWords() {
        List<String> result = new ArrayList<>();
        
        // Handling empty or null inputs
        if (sentence == null || sentence.trim().isEmpty()) {
            return result;
        }
        
        // Spliting the sentence into words
        String[] words = sentence.trim().split("\\s+");
        
        // Finding maximum length
        int maxLength = 0;
        for (String word : words) {
            if (word.length() > maxLength) {
                maxLength = word.length();
            }
        }
        
        // Adding all words with maximum length
        for (String word : words) {
            if (word.length() == maxLength) {
                result.add(word);
            }
        }
        
        return result;
    }
}