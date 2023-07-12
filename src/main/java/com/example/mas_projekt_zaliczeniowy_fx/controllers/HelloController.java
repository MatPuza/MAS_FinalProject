package com.example.mas_projekt_zaliczeniowy_fx.controllers;

import com.example.mas_projekt_zaliczeniowy_fx.Attraction;
import com.example.mas_projekt_zaliczeniowy_fx.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class HelloController
{
   @FXML
   private TitledPane titledPane;
   
   @FXML
   private ListView<String> optionsList;
   
   @FXML
   private Label welcomeText;
   
   @FXML
   public void initialize()
   {
      Attraction.setLastConservationDate(LocalDate.now());
      
      welcomeText.setText("Witaj " + HelloApplication.loggedUser + "!");
      
      titledPane.setText("Wybierz akcję");
      ObservableList<String> items = FXCollections.observableArrayList(
              "Zarezerwuj bilet", "Oceń atrakcje", "Oceń pracownika", "Przeglądaj atrakcje");
      optionsList.setItems(items);
      
      optionsList.setOnMouseClicked(event ->
                                    {
                                       String selectedItem = optionsList.getSelectionModel().getSelectedItem();
                                       
                                       if(Objects.equals(selectedItem, "Zarezerwuj bilet"))
                                       {
                                          CustomSceneCreator.createScene(event, "reservation-view", "Rezerwacja biletów", this);
                                       }
                                    });
   }
}

















