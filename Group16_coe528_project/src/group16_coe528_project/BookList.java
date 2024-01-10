/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group16_coe528_project;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Integer;
import java.lang.Double;

public class BookList {
    private ArrayList<Book> bookList;
    private static BookList instance;
    private FileWriter file;
    private FileReader reader;

    // If Instance of BookList does not already exist it will create an array list and fill the arraylist with the values from the books.txt file
    public BookList() {
        bookList = new ArrayList<>();
        
        try {
            reader = new FileReader("books.txt");
            Scanner scan = new Scanner(reader);
            while(scan.hasNextLine()) {
                String name = scan.next();
                String price = scan.next();
                
                Book newBook = new Book(name, Double.parseDouble(price));
                
                bookList.add(newBook);
            }
        } catch (IOException e) {
            System.out.println("A read error occurred.");
        }
    }
    
    // Returns instance of BookList if it does not already exist
    public static BookList getInstance() {
        if (instance == null) {
            instance = new BookList();
        }
        return instance;
    }

    // Adds book to ArrayList and then adds the book to the text file
    public void addBook(Book b) {
        bookList.add(b);
        
        try {    
            file=new FileWriter("books.txt");
            for (Book book : bookList) {
                file.write("\n" + book.getBookName() + " " + book.getBookPrice());
            }  
            file.close();    
        }
        catch(Exception e){
            System.out.println(e);
        }
    }  

    // Remove book based on its index and then removes it from the text file
    public void removeBook(int i) {
        bookList.remove(i);
        
        try {    
            file=new FileWriter("books.txt");    
            for (Book book : bookList) {
                    file.write("\n" + book.getBookName() + " " + book.getBookPrice());
                }
            file.close();    
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    // Returns bookList ArrayList
    public ArrayList<Book> getList() {
        return bookList;
    }
    
    /* (Testing main method)
    public static void main(String args[]) {
        BookList list = new BookList();
        
        Book book = new Book("test4", 20.0);
        Book book1 = new Book("test1", 5.0);
        
        list = list.getInstance();
        
        list.addBook(book);
        list.removeBook(1);
        
        System.out.println(list.getList().get(0).getBookName());
    }*/
}
