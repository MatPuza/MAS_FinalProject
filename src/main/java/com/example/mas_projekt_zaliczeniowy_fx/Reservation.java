package com.example.mas_projekt_zaliczeniowy_fx;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Reservation")
public class Reservation
{
   private long id;
   private LocalDate reservationDate;
   private String reservationHour;
   private int reservationPrice;
   private int howManyPpl;
   public enum reservationStatus {ORDERED, PAID, COMPLETED, CANCELLED}
   private reservationStatus status;
   
   private Client client;
   private Attraction attraction;
   
   public Reservation()
   {
      
   }
   
   public Reservation(LocalDate reservationDate, String reservationHour, int reservationPrice, int howManyPpl, reservationStatus status)
   {
      this.reservationDate = reservationDate;
      this.reservationHour = reservationHour;
      this.reservationPrice = reservationPrice;
      this.howManyPpl = howManyPpl;
      this.status = status;
   }
   
   @Id
   @GeneratedValue(generator = "increment")
   @GenericGenerator(name = "increment", strategy = "increment")
   public long getId()
   {
      return id;
   }
   
   @Basic
   @Column(nullable=false)
   public LocalDate getReservationDate()
   {
      return reservationDate;
   }
   
   @Basic
   @Column(nullable=false)
   public String getReservationHour()
   {
      return reservationHour;
   }
   
   @Basic
   @Column(nullable=false)
   public int getReservationPrice()
   {
      return reservationPrice;
   }
   
   @Basic
   @Column(nullable=false)
   public int getHowManyPpl()
   {
      return howManyPpl;
   }
   
   @Enumerated
   public reservationStatus getStatus()
   {
      return status;
   }
   
   public void setId(long id)
   {
      this.id = id;
   }
   
   public void setReservationDate(LocalDate reservationDate)
   {
      this.reservationDate = reservationDate;
   }
   
   public void setReservationHour(String reservationHour)
   {
      this.reservationHour = reservationHour;
   }
   
   public void setReservationPrice(int reservationPrice)
   {
      this.reservationPrice = reservationPrice;
   }
   
   public void setHowManyPpl(int howManyPpl)
   {
      this.howManyPpl = howManyPpl;
   }
   
   public void setStatus(reservationStatus status)
   {
      this.status = status;
   }
   
   public static int countPayment(int howManyPpl)
   {
      return howManyPpl * 35;
   }
   
   @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
   public Client getClient()
   {
      return client;
   }
   
   public void setClient(Client newClient) throws Exception
   {
      if(this.client != newClient)
      {
         this.client = newClient;
         newClient.addReservation(this);
      }
   }
   
   @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
   public Attraction getAttraction()
   {
      return attraction;
   }
   
   public void setAttraction(Attraction newAttraction) throws Exception
   {
      if(this.attraction != newAttraction)
      {
         this.attraction = newAttraction;
         newAttraction.addReservation(this);
      }
   }
}

















