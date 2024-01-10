/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group16_coe528_project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author efekete
 */
public class OwnerHomePage extends BookstoreState {
//    public static void main(String[] args) {
//        launch(args);
//    }
    
    @Override
    public void start(Stage stage) {
        
        final Label welcome = new Label("Welcome Administrator");
        welcome.setFont(new Font("Arial", 20));
        
        Button buttonBooks = new Button("Books");
        Button buttonCustomers = new Button("Customers");
        Button buttonLogout = new Button("Logout");

        buttonBooks.setMinWidth(90);
        buttonCustomers.setMinWidth(90);
        buttonLogout.setMinWidth(90);
        
        buttonBooks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                OwnerBookList nextStage = new OwnerBookList();
                nextStage.start(stage);
                
            }
        });
        
        buttonCustomers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                OwnerCustomerList nextStage = new OwnerCustomerList();
                nextStage.start(stage);
                
            }
        });
        
        buttonLogout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LogIn nextStage = new LogIn();
                nextStage.start(stage);
                
            }
        });
        
        VBox list = new VBox();
        list.setSpacing(10);
        
        list.setAlignment(Pos.CENTER);
        list.getChildren().addAll(welcome, buttonBooks, buttonCustomers, buttonLogout);
        
        Scene scene = new Scene(list, 450, 550);
        stage.setTitle("Book Repository");
        stage.setWidth(450);
        stage.setHeight(550);
        
        stage.setScene(scene);
        stage.show();
    }
}
