package com.example.mas_projekt_zaliczeniowy_fx.controllers;

import com.example.mas_projekt_zaliczeniowy_fx.Attraction;
import com.example.mas_projekt_zaliczeniowy_fx.Client;
import com.example.mas_projekt_zaliczeniowy_fx.Reservation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;
import java.util.List;

public class ConfirmReservationViewController
{
   @FXML
   public TextField nameField;
   
   @FXML
   public TextField emailField;
   
   @FXML
   public TextField dateField;
   
   @FXML
   public TextField attractionField;
   
   @FXML
   public TextField amountField;
   
   @FXML
   public TextField costField;
   
   @FXML
   public Button buttonBackToMenu;
   
   @FXML
   public void initialize()
   {
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
         String hour = ReservationViewController.hour + ":" + ReservationViewController.minutes;
         int amtPpl = AttractionPickController.amountOfPpl;
         int resPrice = Reservation.countPayment(amtPpl);
         String atrName = AttractionPickController.attractionName;
         Reservation reservation = new Reservation(resDate, hour, resPrice, amtPpl,
                                                   Reservation.reservationStatus.ORDERED);
         
         List<Attraction> attractions = session.createQuery("FROM Attraction ").list();
         List<Client> clients = session.createQuery("FROM Client ").list();
         
         Attraction myAttraction = attractions.get(0);
         Client me = clients.get(0);
         
         for(Attraction a : attractions)
         {
            if(a.getName().equals(atrName)) myAttraction = a;
         }
         
         reservation.setAttraction(myAttraction);
         reservation.setClient(me);
         
         nameField.setText(me.getName() + " " + me.getSurname());
         emailField.setText(me.getEmail());
         dateField.setText(resDate.toString());
         attractionField.setText(myAttraction.getName());
         amountField.setText(String.valueOf(amtPpl));
         costField.setText(resPrice + " zł");
         
         session.persist(reservation);
         
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
      
      buttonBackToMenu.setOnMouseClicked(event ->
                                         {
                                            CustomSceneCreator.createScene(event, "hello-view", "Witaj ponownie!", this);
                                         });
   }
}




















