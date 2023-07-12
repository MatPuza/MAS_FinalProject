package com.example.mas_projekt_zaliczeniowy_fx.controllers;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class CustomSceneCreator
{
   public static void createScene(Event event, String fxmlFile, String title, Object self)
   {
      FXMLLoader fxmlLoader = new FXMLLoader(
              self.getClass().getResource(
                      "/com/example/mas_projekt_zaliczeniowy_fx/" + fxmlFile +
                              ".fxml"));
      Parent root = null;
      try
      {
         root = fxmlLoader.load();
      }
      catch(IOException e)
      {
         throw new RuntimeException(e);
      }
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      Scene scene = new Scene(root, 320, 240);
      stage.setTitle(title);
      stage.setScene(scene);
      stage.setWidth(root.prefWidth(0));
      stage.setHeight(root.prefHeight(0));
      stage.setResizable(false);
      stage.show();
   }
   
   public static void showErrorMsg(String message)
   {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText(message);
      
      Optional<ButtonType> result = alert.showAndWait();
   }
}
