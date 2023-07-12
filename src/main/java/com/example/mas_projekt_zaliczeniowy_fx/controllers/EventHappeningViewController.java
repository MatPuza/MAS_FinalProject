package com.example.mas_projekt_zaliczeniowy_fx.controllers;

import com.example.mas_projekt_zaliczeniowy_fx.Attraction;
import com.example.mas_projekt_zaliczeniowy_fx.Client;
import com.example.mas_projekt_zaliczeniowy_fx.Event;
import com.example.mas_projekt_zaliczeniowy_fx.Reservation;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class EventHappeningViewController
{
   @FXML
   public TextField statusField;
   
   @FXML
   public Button buttonParticipate;
   
   @FXML
   public Button buttonReservation;
   
   @FXML
   public void initialize()
   {
      Event.eventStatus status = Event.eventStatus.IN_PROGRESS;
      
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
         
         LocalDate resDate = ReservationViewController.pickedDate;
         
         List<Event> events = session.createQuery("FROM Event ").list();
         
         for(Event ev : events)
         {
            if(ev.getReservationDate().equals(resDate)) statusField.setText(ev.getStatus().toString());
         }
         
         session.getTransaction().commit();
         session.close();
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
      
      buttonParticipate.setOnMouseClicked(event ->
                                          {
                                             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                             alert.setTitle("Udało się!");
                                             alert.setHeaderText("Zostałeś dodany na listę uczestników wydarzenia.");
                                             
                                             Optional<ButtonType> result = alert.showAndWait();
                                             if(result.get() == ButtonType.OK)
                                             {
                                                CustomSceneCreator.createScene(event, "confirm-reservation-view", "Rezerwacja pomyślna!", this);
                                             }
                                          });
      
      buttonReservation.setOnMouseClicked(event ->
                                          {
                                             CustomSceneCreator.createScene(event, "confirm-reservation-view", "Rezerwacja pomyślna!", this);
                                          });
   }
}






















