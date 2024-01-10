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
import javafx.scene.control.CheckBox;
import java.lang.Integer;
import java.lang.Double;

public class Book {
    private String name;
    private double price;
    private CheckBox select;
    
    public Book(String name, double price) {
        this.name = name;
        this.price = price;
        this.select = new CheckBox();
    }
    
    public String getBookName() {
        return name;
    }
    
    public double getBookPrice() {
        return price;
    }
    
    public CheckBox getSelect() {
        return select;
    }
    
    public void setSelect(CheckBox select) {
        this.select = select;
    }
}
