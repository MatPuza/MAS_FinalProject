package com.example.mas_projekt_zaliczeniowy_fx.controllers;

import com.example.mas_projekt_zaliczeniowy_fx.Attraction;
import com.example.mas_projekt_zaliczeniowy_fx.Client;
import com.example.mas_projekt_zaliczeniowy_fx.Employee;
import com.example.mas_projekt_zaliczeniowy_fx.Reservation;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationViewController
{
   public static LocalDate pickedDate;
   public static int hour;
   public static int minutes;
   
   @FXML
   public DatePicker dateField;
   
   @FXML
   public TextField hourField;
   
   @FXML
   public Button buttonNext;
   
   @FXML
   public Button buttonCancel;
   
   @FXML
   public Label errorHourLabel;
   
   @FXML
   public void initialize()
   {
      hourField.textProperty().addListener((observable, oldValue, newValue) ->
                                           {
                                              if(!checkHour())
                                              {
                                                 errorHourLabel.setText("Podano złe dane.");
                                              }
                                              else
                                              {
                                                 errorHourLabel.setText("");
                                              }
                                           });
      
      buttonCancel.setOnMouseClicked(event ->
                                     {
                                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                        alert.setTitle("Anuluj rezerwację");
                                        alert.setHeaderText("Czy na pewno chcesz anulować?");
                                        alert.setContentText("Wybierz swoją opcję.");
         
                                        Optional<ButtonType> result = alert.showAndWait();
                                        if(result.get() == ButtonType.OK)
                                        {
                                           CustomSceneCreator.createScene(event, "hello-view", "Witaj Użytkowniku!",
                                                                          this);
                                        }
                                     });
      
      buttonNext.setOnMouseClicked(event ->
                                   {
                                      boolean areValuesCorrect = checkDate();
                                      if(areValuesCorrect) areValuesCorrect = checkHour();
                                      if(areValuesCorrect) areValuesCorrect = checkDBDates();
         
                                      if(!areValuesCorrect)
                                      {
                                         CustomSceneCreator.showErrorMsg("Podałeś błędne dane.");
                                      }
                                      else
                                      {
                                         //If 7 days haven't passed, show window asking if client wants to change date
                                         if(!Attraction.getLastConservationDate().plusDays(7).isBefore(pickedDate))
                                         {
                                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert.setTitle("Brak dostępnych atrakcji");
                                            alert.setHeaderText("W podanym terminie brak dostępnych atrakcji.");
                                            alert.setContentText("Czy chcesz wybrać inną datę?");
               
                                            Optional<ButtonType> result = alert.showAndWait();
                                            if(result.get() == ButtonType.CANCEL)
                                            {
                                               CustomSceneCreator.createScene(event, "hello-view", "Witaj Użytkowniku!",
                                                                              this);
                                            }
                                         }
                                         else
                                         {
                                            CustomSceneCreator.createScene(event, "attraction-pick-view",
                                                                           "Wybierz atrakcję", this);
                                         }
                                      }
                                   });
   }
   
   private boolean checkDate()
   {
      if(dateField != null)
      {
         pickedDate = dateField.getValue();
         
         if(pickedDate.isBefore(LocalDate.now())) return false;
         else return true;
      }
      
      return false;
   }
   
   private boolean checkHour()
   {
      if(hourField != null)
      {
         String rawString = hourField.getText();
         
         if(!rawString.contains(":")) return false;
         
         String[] time = rawString.split(":");
         
         if(time.length < 2) return false;
         if(!time[0].matches("\\d{2}") || !time[1].matches("\\d{2}")) return false;
         
         hour = Integer.parseInt(time[0]);
         minutes = Integer.parseInt(time[1]);
         
         if(hour < 10 || hour > 21)
         {
            Tooltip tooltip = new Tooltip("Park nie pracuje w tych godzinach.");
            hourField.setTooltip(tooltip);
         }
         else
         {
            hourField.setTooltip(null);
         }
         
         if(hour < 0 || hour > 23 || minutes < 0 || minutes > 59) return false;
         else return true;
      }
      
      return false;
   }
   
   private boolean checkDBDates()
   {
      List<Attraction> attractions = new ArrayList<>();
      
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
         
         attractions = session.createQuery("FROM Attraction ").list();
         
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
         
         session.getTransaction().commit();
         session.close();
         
         return !dupeAttractions.isEmpty();
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
      
      return false;
   }
}
















