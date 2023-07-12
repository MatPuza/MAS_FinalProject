package com.example.mas_projekt_zaliczeniowy_fx;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Client")
public class Client extends Person
{
   private String email;
   private String PESEL;
   
   private List<Rating> ratings = new ArrayList<>();
   private List<Reservation> reservations = new ArrayList<>();
   
   public Client()
   {
      
   }
   
   public Client(String name, String surname, LocalDate birthDate, String address, String postalAddress,
                 String phoneNumber, String email, String PESEL)
   {
      super(name, surname, birthDate, address, postalAddress, phoneNumber);
      this.email = email;
      this.PESEL = PESEL;
   }
   
   @Basic
   @Column(nullable=false)
   public String getEmail()
   {
      return email;
   }
   
   @Basic
   @Column(nullable=false)
   public String getPESEL()
   {
      return PESEL;
   }
   
   public void setEmail(String email)
   {
      this.email = email;
   }
   
   public void setPESEL(String PESEL)
   {
      this.PESEL = PESEL;
   }
   
   @OneToMany(mappedBy = "client")
   public List<Rating> getRatings()
   {
      return ratings;
   }
   
   public void setRatings(List<Rating> ratings)
   {
      this.ratings = ratings;
   }
   
   public void addRating(Rating newRating) throws Exception
   {
      if(!ratings.contains(newRating))
      {
         ratings.add(newRating);
         newRating.setClient(this);
      }
   }
   
   @OneToMany(mappedBy = "client")
   public List<Reservation> getReservations()
   {
      return reservations;
   }
   
   public void setReservations(List<Reservation> reservations)
   {
      this.reservations = reservations;
   }
   
   public void addReservation(Reservation newReservation) throws Exception
   {
      if(!reservations.contains(newReservation))
      {
         reservations.add(newReservation);
         newReservation.setClient(this);
      }
   }
}






















