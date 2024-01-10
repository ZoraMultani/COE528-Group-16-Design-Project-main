/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group16_coe528_project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author efekete
 */
public abstract class BookstoreState extends Application {
    protected static User currentUser;
    
    public static User getCurrentUser() {
        return currentUser;
    }
    
    public static void setCurrentUser(User newUser) {
        if (newUser == null || !(newUser instanceof User)) {
            return;
        }
        currentUser = newUser;
    }
}
