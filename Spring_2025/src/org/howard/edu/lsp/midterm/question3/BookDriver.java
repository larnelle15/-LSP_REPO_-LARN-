package org.howard.edu.lsp.midterm.question3;

public class BookDriver {
    public static void main(String[] args) {
        // Create book instances
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "123456789", 1925);
        Book book2 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "123456789", 1925);
        Book book3 = new Book("A Promised Land", "Barack Obama", "9780593239681", 2020);
        
        // Test equals method
        System.out.println(book1.equals(book2)); // true
        System.out.println(book1.equals(book3)); // false
        
        // Test toString method
        System.out.println(book1); 
        // Output: Title: The Great Gatsby, Author: F. Scott Fitzgerald, ISBN: 123456789, Year Published: 1925
    }
}

class Book {
    // Private fields for encapsulation
    private String title;
    private String author;
    private String ISBN;
    private int yearPublished;
    
    // Constructor
    public Book(String title, String author, String ISBN, int yearPublished) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.yearPublished = yearPublished;
    }
    
    // Getters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getISBN() { return ISBN; }
    public int getYearPublished() { return yearPublished; }
    
    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setISBN(String ISBN) { this.ISBN = ISBN; }
    public void setYearPublished(int yearPublished) { this.yearPublished = yearPublished; }
    
    // Override equals - books are equal if they have the same ISBN and author
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Book)) return false;
        
        Book otherBook = (Book) obj;
        return this.ISBN.equals(otherBook.ISBN) && this.author.equals(otherBook.author);
    }
    
    // Override toString for meaningful representation
    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + ISBN + ", Year Published: " + yearPublished;
    }
}