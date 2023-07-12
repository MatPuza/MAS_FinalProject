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

public class PayViewController
{
   public static String creditCardNumberString;
   public static String cardholderNameString;
   public static String expiryDateString;
   public static String cvvString;
   
   @FXML
   public TextField creditCardNumber;
   
   @FXML
   public TextField cardholderName;
   
   @FXML
   public TextField expiryDate;
   
   @FXML
   public TextField cvv;
   
   @FXML
   public Button buttonNext;
   
   @FXML
   public Button buttonCancel;
   
   @FXML
   public Button buttonPayLater;
   
   @FXML
   public void initialize()
   {
      buttonNext.setOnMouseClicked(event ->
                                   {
                                      if(!areTextFieldsSet()) CustomSceneCreator.showErrorMsg("Nie wypełniłeś wszystkich pól poprawnie.");
                                      else
                                      {
                                         creditCardNumberString = creditCardNumber.getText();
                                         cardholderNameString = cardholderName.getText();
                                         expiryDateString = expiryDate.getText();
                                         cvvString = cvv.getText();
                                         
                                         CustomSceneCreator.createScene(event, "confirm-reservation-view", "Potwierdź rezerwację", this);
                                      }
                                   });
      
      buttonCancel.setOnMouseClicked(event ->
                                     {
                                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                        alert.setTitle("Powrót do menu");
                                        alert.setHeaderText("Czy na pewno chcesz anulować rezerwację??");
                                        alert.setContentText("Wybierz swoją opcję.");
                                        
                                        Optional<ButtonType> result = alert.showAndWait();
                                        if(result.get() == ButtonType.OK)
                                        {
                                           CustomSceneCreator.createScene(event, "hello-view", "Witaj Użytkowniku!", this);
                                        }
                                     });
      
      buttonPayLater.setOnMouseClicked(event ->
                                       {
                                          boolean isThereAvailableEvent = false;
                                          
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
                                             Reservation reservation = new Reservation(resDate, hour, resPrice, amtPpl, Reservation.reservationStatus.ORDERED);
                                             
                                             List<Attraction> attractions = session.createQuery("FROM Attraction ").list();
                                             List<Client> clients = session.createQuery("FROM Client ").list();
                                             List<Event> events = session.createQuery("FROM Event ").list();
                                             
                                             Attraction myAttraction = attractions.get(0);
                                             
                                             for(Attraction a : attractions)
                                             {
                                                if(a.getName().equals(atrName)) myAttraction = a;
                                             }
                                             
                                             reservation.setAttraction(myAttraction);
                                             reservation.setClient(clients.get(0));
                                             
                                             session.persist(reservation);
                                             
                                             for(Event ev : events)
                                             {
                                                if(ev.getReservationDate().equals(resDate)) isThereAvailableEvent = true;
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
                                          
                                          if(!isThereAvailableEvent) CustomSceneCreator.createScene(event, "confirm-reservation-view", "Rezerwacja pomyślna!", this);
                                          else CustomSceneCreator.createScene(event, "event-happening-view", "Gorące eventy w twojej okolicy!", this);
                                       });
   }
   
   private boolean isTextFieldSet(TextField textField)
   {
      if(textField.getText().isEmpty())
      {
         textField.getStyleClass().add("error");
         return false;
      }
      else textField.getStyleClass().remove("error");
      
      return true;
   }
   
   private boolean areTextFieldsSet()
   {
      boolean result = isTextFieldSet(creditCardNumber);
      if(result) result = isTextFieldSet(cardholderName);
      else isTextFieldSet(cardholderName);
      
      if(expiryDate.getText().isEmpty() || expiryDate.getText().length() != 5)
      {
         expiryDate.getStyleClass().add("error");
         result = false;
      }
      else
      {
         expiryDate.getStyleClass().remove("error");
      }
      
      if(cvv.getText().isEmpty() || cvv.getText().length() != 3)
      {
         cvv.getStyleClass().add("error");
         result = false;
      }
      else cvv.getStyleClass().remove("error");
      
      return result;
   }
}






















