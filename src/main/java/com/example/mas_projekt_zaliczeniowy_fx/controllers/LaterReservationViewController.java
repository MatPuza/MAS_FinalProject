package com.example.mas_projekt_zaliczeniowy_fx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LaterReservationViewController
{
   @FXML
   public Button buttonBackToMenu;
   
   @FXML
   public void initialize()
   {
      buttonBackToMenu.setOnMouseClicked(event ->
                                     {
                                        CustomSceneCreator.createScene(event, "hello-view", "Witaj ponownie!", this);
                                     });
   }
}
