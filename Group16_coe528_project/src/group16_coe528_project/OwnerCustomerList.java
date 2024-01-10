/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group16_coe528_project;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author efekete
 */
public class OwnerCustomerList extends BookstoreState {
    
    private final TableView<User> table = new TableView<User>();
    
    @Override
    public void start(Stage stage) {
        // Gets Singleton Instance of UserList
        UserList users = UserList.getInstance();
        
        // Gets ArrayList for Books from the BookList
        final ArrayList<User> userTable = users.getList();
        
        // Sets size of Screne
        Scene scene = new Scene(new Group());
        stage.setTitle("Book Repository");
        stage.setWidth(590);
        stage.setHeight(540);
        
        // Displays welcome text
        Label heading = new Label();
        heading.setText("Welcome Administrator.");
        table.setEditable(true);
        
        // Sets up table for the books
        TableColumn nameColumn = new TableColumn("Name");
        TableColumn passwordColumn = new TableColumn("Password");
        TableColumn pointsColumn = new TableColumn("Points");
        TableColumn selectColumn = new TableColumn("Selection");
        
        nameColumn.setMinWidth(150);
        passwordColumn.setMinWidth(150);
        pointsColumn.setMinWidth(150);
        selectColumn.setMinWidth(100);
        
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("userPassword"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("userPoints"));
        selectColumn.setCellValueFactory(new PropertyValueFactory<>("select"));
        
        table.getColumns().addAll(nameColumn, passwordColumn, pointsColumn, selectColumn);
        
        // Makes columns non-selectable since the table uses checkboxes
        table.setSelectionModel(null);
        
        selectColumn.setStyle("-fx-alignment: CENTER;");
        
        // Fills user table
        for (User user : userTable) {
            table.getItems().addAll(user);
        }
        
        Button buttonRemove = new Button("Remove");
        Button buttonLogout = new Button("Logout");
        Button buttonAdd = new Button("Add");

        buttonRemove.setMinWidth(90);
        buttonLogout.setMinWidth(90);
        buttonAdd.setMinWidth(90);
        
        final TextField addName = new TextField();
        addName.setPromptText("Name");
        addName.setMaxWidth(nameColumn.getPrefWidth());
        final TextField addPassword = new TextField();
        addPassword.setMaxWidth(passwordColumn.getPrefWidth());
        addPassword.setPromptText("Password");
        
        buttonRemove.setOnAction(
            new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                CustomerBookList.totalCost = 0;
                // Finds what books are selected and removes them 
                for (int i = 0; i < userTable.size(); i++) {
                    User user = userTable.get(i);
                            
                    if (user.getSelect().isSelected()) {
                        users.removeUser(i);
                        i = 0;
                    }
                }
                
                // Reopens OwnerCustomerList with new updated list
                OwnerCustomerList nextStage = new OwnerCustomerList();
                nextStage.start(stage);
            }
        }
        );
        
        // When Redeem button is pressed find total price, calculate value of points, subtract value of points by total price and go to customer pay CustomerBuyScreen
        buttonAdd.setOnAction(
            new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (addName.getText().isEmpty() || addPassword.getText().isEmpty()) {
                    OwnerCustomerList nextStage = new OwnerCustomerList();
                    nextStage.start(stage);
                }
                else {
                    User newUser = new User(addName.getText(), addPassword.getText(), 0, false);
                    users.addUser(newUser);
                    addName.clear();
                    addPassword.clear();

                    // Reopens OwnerCustomerList with new updated list
                    OwnerCustomerList nextStage = new OwnerCustomerList();
                    nextStage.start(stage);
                }
            }
        }
        );
        
        // When Logout button is pressed log out of account
        buttonLogout.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                LogIn nextStage = new LogIn();
                nextStage.start(stage);
            }
        }
        );
        
        HBox bottom1 = new HBox();
        HBox bottom2 = new HBox();
        VBox list = new VBox();
        
        bottom1.setSpacing(10);
        bottom2.setSpacing(10);
        list.setSpacing(5);
        list.setPadding(new Insets(10, 0, 0, 10));
        
        bottom1.getChildren().addAll(buttonRemove,buttonLogout);
        bottom2.getChildren().addAll(buttonAdd, addName, addPassword);
        
        list.getChildren().addAll(heading, table, bottom1, bottom2);
        
        ((Group) scene.getRoot()).getChildren().addAll(list);
        
        stage.setScene(scene);
        stage.show();
    }
}
