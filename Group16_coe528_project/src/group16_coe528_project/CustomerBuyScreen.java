/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group16_coe528_project;

import java.text.DecimalFormat;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author efekete
 */
public class CustomerBuyScreen extends BookstoreState {
 
    DecimalFormat df = new DecimalFormat("0.00");
    
    @Override
    public void start(Stage stage) {
        String status;
        
        // Calculates status of user after purchase
        if (currentUser.getUserPoints() >= 1000) {
            status = "Gold";
        }
        else status = "Silver";
        
        // Displays total cost, points, and staus after transaction
        Label totalCostLabel = new Label("Total Cost: $" + df.format(CustomerBookList.totalCost));
        Label pointsLabel = new Label("Points: " + currentUser.getUserPoints());
        //Label pointsLabel = new Label("Points: " + LogIn.pointsToPasson);
        Label statusLabel = new Label("Status: "+ status);
        
        // Log out button logs out of account
        Button btLo = new Button();
        btLo.setText("Logout");
        btLo.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                LogIn nextStage = new LogIn();
                nextStage.start(stage);
            }
        });

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(totalCostLabel, pointsLabel, statusLabel, btLo);
        root.setSpacing(15);

        
        Scene scene = new Scene(root, 500, 350);
        stage.setWidth(500);
        stage.setHeight(500);
        stage.setTitle("Bookstore App");
        stage.setScene(scene);
        stage.show();
    }
}
