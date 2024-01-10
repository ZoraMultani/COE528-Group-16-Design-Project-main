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

public class UserList {
    private ArrayList<User> userList;
    private static UserList instance;
    private FileWriter file;
    private FileReader reader;

    // If Instance of UserList does not already exist it will create an array list and populate it with the values from the file
    private UserList() {
        userList = new ArrayList<>();
        
        try {
            reader = new FileReader("customers.txt");
            Scanner scan = new Scanner(reader);
            while(scan.hasNextLine()) {
                String name = scan.next();
                String password = scan.next();
                String points = scan.next();
                String isAdminString = scan.next();
                
                int pointInt = Integer.parseInt(points);
                
                boolean isAdmin = isAdminString.equals("true");
                
                User newUser = new User(name, password, pointInt, isAdmin);
                
                userList.add(newUser);
            }
        } catch (IOException e) {
            System.out.println("A read error occurred.");
        }
    }

    // Returns instance of UserList if it does ont already exist
    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    // Adds user to ArrayList and then adds the user to the text file
    public void addUser(User u) {
        userList.add(u);
        
        try {
            file = new FileWriter("customers.txt");
            for (User user : userList) {
                file.write("\n" + user.getUserName() + " " + user.getUserPassword() + " " + user.getUserPoints() + " " + user.getAdminStatus());
            }
            file.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
    
    // Remove user based on its index and then removes it from the text file
    public void removeUser(int i) {
        if (i != 0) {
            userList.remove(i);
        }
        
        try {    
            file=new FileWriter("customers.txt");
            for (User user : userList) {
                    file.write("\n" + user.getUserName() + " " + user.getUserPassword() + " " + user.getUserPoints() + " " + user.getAdminStatus());
                }
            file.close();    
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    // Returns userList ArrayList
    public ArrayList<User> getList() {
        return userList;
    }
    
    /* (Testing main method)
    public static void main(String args[]) {
        UserList list = new UserList();
        
        User user = new User("User4", "$5", 300, false);
        
        list = list.getInstance();
        
        list.addUser(user);
        list.removeUser(1);
        
        System.out.println(list.getList().get(0).getAdminStatus());
    } */
}
