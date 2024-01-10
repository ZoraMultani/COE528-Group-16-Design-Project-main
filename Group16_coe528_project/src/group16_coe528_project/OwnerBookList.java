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
import java.lang.Integer;
import java.lang.Double;

/**
 *
 * @author efekete
 */
public class OwnerBookList extends BookstoreState {
    private final TableView<Book> table = new TableView<Book>();
    
    @Override
    public void start(Stage stage) {
        // Gets Singleton Instance of BookList
        BookList books = BookList.getInstance();
        
        // Gets ArrayList for Books from the BookList
        final ArrayList<Book> bookTable = books.getList();
        
        // Sets size of Screne
        Scene scene = new Scene(new Group());
        stage.setTitle("Book Repository");
        stage.setWidth(440);
        stage.setHeight(540);
        
        // Displays welcome text
        Label heading = new Label();
        heading.setText("Welcome Administrator.");
        table.setEditable(true);
        
        // Sets up table for the books
        TableColumn bookColumn = new TableColumn("Name");
        TableColumn priceColumn = new TableColumn("Price");
        TableColumn selectColumn = new TableColumn("Selection");
        
        bookColumn.setMinWidth(150);
        priceColumn.setMinWidth(150);
        selectColumn.setMinWidth(100);
        
        bookColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("bookPrice"));
        selectColumn.setCellValueFactory(new PropertyValueFactory<>("select"));
        
        table.getColumns().addAll(bookColumn, priceColumn, selectColumn);
        
        // Makes columns non-selectable since the table uses checkboxes
        table.setSelectionModel(null);
        
        selectColumn.setStyle("-fx-alignment: CENTER;");
        
        // Fills book table
        for (Book book : bookTable) {
            table.getItems().addAll(book);
        }
        
        Button buttonRemove = new Button("Remove");
        Button buttonLogout = new Button("Logout");
        Button buttonAdd = new Button("Add");

        buttonRemove.setMinWidth(90);
        buttonLogout.setMinWidth(90);
        buttonAdd.setMinWidth(90);
        
        final TextField addTitle = new TextField();
        addTitle.setPromptText("Title");
        addTitle.setMaxWidth(bookColumn.getPrefWidth());
        final TextField addPrice = new TextField();
        addPrice.setMaxWidth(priceColumn.getPrefWidth());
        addPrice.setPromptText("Price");
        
        buttonRemove.setOnAction(
            new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                CustomerBookList.totalCost = 0;
                // Finds what books are selected and removes them 
                for (int i = 0; i < bookTable.size(); i++) {
                    Book book = bookTable.get(i);
                            
                    if (book.getSelect().isSelected()) {
                        books.removeBook(i);
                        i = 0;
                    }
                }
                
                // Reopens OwnerBookList with new updated list
                OwnerBookList nextStage = new OwnerBookList();
                nextStage.start(stage);
            }
        }
        );
        
        // When Redeem button is pressed find total price, calculate value of points, subtract value of points by total price and go to customer pay CustomerBuyScreen
        buttonAdd.setOnAction(
            new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (addTitle.getText().isEmpty() || addPrice.getText().isEmpty()) {
                    OwnerBookList nextStage = new OwnerBookList();
                    nextStage.start(stage);
                }
                else {
                     Book newBook = new Book(addTitle.getText(), Double.parseDouble(addPrice.getText()));
                    books.addBook(newBook);
                    addTitle.clear();
                    addPrice.clear();

                    // Reopens OwnerBookList with new updated list
                    OwnerBookList nextStage = new OwnerBookList();
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
        bottom2.getChildren().addAll(buttonAdd, addTitle, addPrice);
        
        list.getChildren().addAll(heading, table, bottom1, bottom2);
        
        ((Group) scene.getRoot()).getChildren().addAll(list);
        
        stage.setScene(scene);
        stage.show();
    }
}
