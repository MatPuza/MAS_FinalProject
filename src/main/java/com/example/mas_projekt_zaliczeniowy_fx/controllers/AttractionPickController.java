package com.example.mas_projekt_zaliczeniowy_fx.controllers;

import com.example.mas_projekt_zaliczeniowy_fx.Attraction;
import com.example.mas_projekt_zaliczeniowy_fx.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.mas_projekt_zaliczeniowy_fx.controllers.ReservationViewController.pickedDate;

public class AttractionPickController
{
   private final String optionsTitle = "Wybierz z listy dostępnych atrakcji";
   public static String attractionName = "";
   public static int amountOfPpl;
   
   @FXML
   public ListView<String> attractionsList;
   
   @FXML
   public Button buttonNext;
   
   @FXML
   public Button buttonCancel;
   
   @FXML
   public TextField amountOfPplField;
   
   @FXML
   public Label errorAmountLabel;
   
   @FXML
   public void initialize()
   {
      List<String> tempList = new ArrayList<>();
      tempList.add(optionsTitle);
      tempList.addAll(getAvailableAttractionsNames());
      
      ObservableList<String> items = FXCollections.observableArrayList(tempList);
      attractionsList.setItems(items);
      
      amountOfPplField.textProperty().addListener((observable, oldValue, newValue) ->
                                                  {
                                                     String rawString = amountOfPplField.getText();
         
                                                     //If amount of ppl is 1 or 2
                                                     if(rawString.matches("\\d{1,2}"))
                                                     {
                                                        amountOfPpl = Integer.parseInt(rawString);
            
                                                        if(amountOfPpl > 10 || amountOfPpl == 0)
                                                           errorAmountLabel.setText(
                                                                   "Liczba rezerwujących przekroczyła dozwoloną " +
                                                                           "wartość (1-10).");
                                                        else errorAmountLabel.setText("");
                                                     }
                                                     else if(!rawString.equals(""))
                                                     {
                                                        errorAmountLabel.setText("W polu podano nielegalne znaki");
                                                     }
                                                     else errorAmountLabel.setText("");
                                                  });
      
      attractionsList.setOnMouseClicked(event ->
                                        {
                                           String selectedItem = attractionsList.getSelectionModel().getSelectedItem();
         
                                           if(!Objects.equals(selectedItem, optionsTitle))
                                              attractionName = selectedItem;
                                           else attractionName = "";
                                        });
      
      buttonNext.setOnMouseClicked(event ->
                                   {
                                      if(!Objects.equals(attractionName, ""))
                                      {
                                         if(!isAmountOfPplCorrect())
                                         {
                                            CustomSceneCreator.showErrorMsg("Podałeś błędną ilość uczestników.");
                                         }
                                         else
                                            CustomSceneCreator.createScene(event, "pay-view", "Ekran płatności", this);
                                      }
                                      else
                                      {
                                         CustomSceneCreator.showErrorMsg("Nie wybrałeś atrakcji.");
                                      }
                                   });
      
      buttonCancel.setOnMouseClicked(event ->
                                     {
                                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                        alert.setTitle("Powrót");
                                        alert.setHeaderText("Czy na pewno chcesz wrócić do wyboru daty?");
                                        alert.setContentText("Wybierz swoją opcję.");
         
                                        Optional<ButtonType> result = alert.showAndWait();
                                        if(result.get() == ButtonType.OK)
                                        {
                                           CustomSceneCreator.createScene(event, "reservation-view",
                                                                          "Rezerwacja biletów", this);
                                        }
                                     });
   }
   
   private boolean isAmountOfPplCorrect()
   {
      if(amountOfPplField != null)
      {
         String rawString = amountOfPplField.getText();
         
         //If amount of ppl is 1 or 2
         if(rawString.matches("\\d{1,2}"))
         {
            amountOfPpl = Integer.parseInt(rawString);
            
            if(amountOfPpl > 10 || amountOfPpl == 0) return false;
            else return true;
         }
      }
      
      return false;
   }
   
   private List<String> getAvailableAttractionsNames()
   {
      List<String> resultList = new ArrayList<>();
      
      StandardServiceRegistry registry = null;
      SessionFactory sessionFactory = null;
      
      try
      {
         registry = new StandardServiceRegistryBuilder()
                 .configure()
                 .build();
         sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
         
         Session session = sessionFactory.openSession();
         session.beginTransaction();
         
         List<Attraction> attractions = session.createQuery("FROM Attraction ").list();
         
         List<Attraction> dupeAttractions = attractions;
         List<Attraction> attractionsToRemove = new ArrayList<>();
         
         for(Attraction a : attractions)
         {
            for(Reservation r : a.getReservations())
            {
               if(r.getReservationDate().equals(pickedDate)) attractionsToRemove.add(a);
            }
         }
         
         dupeAttractions.removeAll(attractionsToRemove);
         
         for(Attraction a : dupeAttractions)
         {
            resultList.add(a.getName());
         }
         
         session.getTransaction().commit();
         session.close();
         
         return resultList;
      }
      catch(Exception e)
      {
         e.printStackTrace();
         StandardServiceRegistryBuilder.destroy(registry);
      }
      finally
      {
         if(sessionFactory != null) sessionFactory.close();
      }
      
      return resultList;
   }
}





















