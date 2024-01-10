package group16_coe528_project;

/**
 *
 * @author JKataoka, ZMultani
 */
import java.util.ArrayList;
import static javafx.application.Application.launch;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class LogIn extends BookstoreState {
    // instance variable to get hold the userlist instance
    private UserList list;
    
    //current user
    private static User currentUser;
    

    
    @Override
    public void start(Stage stage){
        // initiallizes the page
        stage.setTitle("Login");
        stage.setWidth(400);
        stage.setHeight(300);  
        
        // the '/t' are tabs cause I couldn't figure out how to center text
        Label title = new Label("\t  Welcome to the Book Store!");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        
        // Setup for the username entry field
        Label UserLabel = new Label("Username");
        TextField userInput = new TextField();
        userInput.setMaxWidth(200);
        userInput.setAlignment(Pos.CENTER_LEFT);
        HBox usernameHitbox = new HBox(UserLabel, userInput);
        usernameHitbox.setAlignment(Pos.CENTER);
        usernameHitbox.setSpacing(10);
        
        
        // Setup for the password entry field
        Label PassLabel = new Label("Password");
        PasswordField passInput = new PasswordField();
        passInput.setMaxWidth(200);
        passInput.setAlignment(Pos.CENTER_LEFT);
        HBox passwordHitbox = new HBox(PassLabel, passInput);
        passwordHitbox.setAlignment(Pos.CENTER);
        passwordHitbox.setSpacing(10);
        
        // creates the login button
        Button loginButton = new Button("Log In");
        loginButton.setMaxSize(100, 10);
        
        
        //checks the information to see whether it is the owner or a customer and sends them to the respective page
        list = list.getInstance();
        loginButton.setOnAction(action -> {
            for(User u : list.getList()){
                LogIn.setCurrentUser(u);
                if(u.getUserName().equals(userInput.getText()) && u.getUserPassword().equals(passInput.getText())){
                    
                    if(u.getAdminStatus()){
                        OwnerHomePage nextPage = new OwnerHomePage();
                        nextPage.start(stage);
                    }else{
                        CustomerBookList nextPage = new CustomerBookList();
                        nextPage.start(stage);
                    }
                }
            }
        });

        VBox pageContent = new VBox();
        pageContent.getChildren().add(title);
        pageContent.getChildren().add(usernameHitbox);
        pageContent.getChildren().add(passwordHitbox);
        pageContent.getChildren().add(loginButton);
        pageContent.setMargin(loginButton, new Insets(0, 20, 10, 150));
        //pageContent.setPadding(new Insets(0, 20, 10, 20)); 
        pageContent.setSpacing(25);
        
        Scene page = new Scene(pageContent, 400,300);
        stage.setScene(page);
        stage.show();
        
    }
// Main used for testing
//    public static void main(String args[]) {
//        launch(args);
//    }
}
