package com.example.mas_projekt_zaliczeniowy_fx;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Event")
public class Event
{
   private long id;
   
   public enum eventStatus
   {AWAITING_AUTHORIZATION, IN_PROGRESS, COMPLETED, UNAUTHORIZED, CANCELLED}
   
   private eventStatus status;
   private int paidAmount;
   private LocalDate reservationDate;
   private String reservationHour;
   
   private List<Sponsor> sponsors = new ArrayList<>();
   
   public Event()
   {
      
   }
   
   public Event(eventStatus status, int paidAmount, LocalDate reservationDate, String reservationHour)
   {
      this.status = status;
      this.paidAmount = paidAmount;
      this.reservationDate = reservationDate;
      this.reservationHour = reservationHour;
   }
   
   @Id
   @GeneratedValue(generator = "increment")
   @GenericGenerator(name = "increment", strategy = "increment")
   public long getId()
   {
      return id;
   }
   
   @Enumerated
   public eventStatus getStatus()
   {
      return status;
   }
   
   @Basic
   @Column(nullable = false)
   public int getPaidAmount()
   {
      return paidAmount;
   }
   
   @Basic
   @Column(nullable = false)
   public LocalDate getReservationDate()
   {
      return reservationDate;
   }
   
   @Basic
   @Column(nullable = false)
   public String getReservationHour()
   {
      return reservationHour;
   }
   
   public void setId(long id)
   {
      this.id = id;
   }
   
   public void setStatus(eventStatus status)
   {
      this.status = status;
   }
   
   public void setPaidAmount(int paidAmount)
   {
      this.paidAmount = paidAmount;
   }
   
   public void setReservationDate(LocalDate reservationDate)
   {
      this.reservationDate = reservationDate;
   }
   
   public void setReservationHour(String reservationHour)
   {
      this.reservationHour = reservationHour;
   }
   
   @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
   @JoinTable(name = "event_sponsors",
           joinColumns = @JoinColumn(name = "event_id"),
           inverseJoinColumns = @JoinColumn(name = "sponsor_id"))
   public List<Sponsor> getSponsors()
   {
      return sponsors;
   }
   
   public void setSponsors(List<Sponsor> sponsors)
   {
      this.sponsors = sponsors;
   }
   
   public void addSponsor(Sponsor newSponsor)
   {
      if(!sponsors.contains(newSponsor))
      {
         this.sponsors.add(newSponsor);
         newSponsor.getEvents().add(this);
      }
   }
}
















