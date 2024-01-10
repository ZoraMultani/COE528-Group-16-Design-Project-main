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
import javafx.scene.control.CheckBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class User {
    private String name;
    private String password;
    private int points;
    private boolean isAdmin;
    private CheckBox select;

    public User(String name, String password, int points, boolean isAdmin) {
        this.name = name;
        this.password = password;
        this.points = points;
        this.isAdmin = isAdmin;
        this.select = new CheckBox();
    }

    public String getUserName() {
        return name;
    }

    public String getUserPassword() {
        return password;
    }

    public int getUserPoints() {
        return points;
    }
    
    public void setUserPoints(int p) {
        points = p;
    }

    public boolean getUserGoldStatus() {
        return points >= 1000;
    }

    public boolean getAdminStatus() {
        return isAdmin;
    }
    
    public CheckBox getSelect() {
        return select;
    }
    
    public void setSelect(CheckBox select) {
        this.select = select;
    }
}
