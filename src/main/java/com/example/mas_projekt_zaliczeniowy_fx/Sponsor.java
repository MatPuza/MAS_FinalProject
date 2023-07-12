package com.example.mas_projekt_zaliczeniowy_fx;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Sponsor")
public class Sponsor extends Person
{
   private String nameOfRepresentedCompany;
   private String contactDetails;
   private int credibility;
   
   private List<String> previouslySponsoredEvents = new ArrayList();
   
   private List<Event> events = new ArrayList<>();
   
   public Sponsor()
   {
      
   }
   
   public Sponsor(String name, String surname, LocalDate birthDate, String address, String postalAddress,
                  String phoneNumber, String nameOfRepresentedCompany, String contactDetails, int credibility,
                  List<String> previouslySponsoredEvents)
   {
      super(name, surname, birthDate, address, postalAddress, phoneNumber);
      this.nameOfRepresentedCompany = nameOfRepresentedCompany;
      this.contactDetails = contactDetails;
      this.credibility = credibility;
      this.previouslySponsoredEvents = previouslySponsoredEvents;
   }
   
   public Sponsor(String name, String surname, LocalDate birthDate, String address, String postalAddress,
                  String phoneNumber, String nameOfRepresentedCompany, String contactDetails, int credibility,
                  String previouslySponsoredEvent)
   {
      super(name, surname, birthDate, address, postalAddress, phoneNumber);
      this.nameOfRepresentedCompany = nameOfRepresentedCompany;
      this.contactDetails = contactDetails;
      this.credibility = credibility;
      this.previouslySponsoredEvents.add(previouslySponsoredEvent);
   }
   
   @Basic
   @Column(nullable=false)
   public String getNameOfRepresentedCompany()
   {
      return nameOfRepresentedCompany;
   }
   
   @Basic
   @Column(nullable=false)
   public String getContactDetails()
   {
      return contactDetails;
   }
   
   @Basic
   @Column(nullable=false)
   public int getCredibility()
   {
      return credibility;
   }
   
   @ElementCollection
   public List<String> getPreviouslySponsoredEvents()
   {
      return previouslySponsoredEvents;
   }
   
   public void setNameOfRepresentedCompany(String nameOfRepresentedCompany)
   {
      this.nameOfRepresentedCompany = nameOfRepresentedCompany;
   }
   
   public void setContactDetails(String contactDetails)
   {
      this.contactDetails = contactDetails;
   }
   
   public void setCredibility(int credibility)
   {
      this.credibility = credibility;
   }
   
   public void setPreviouslySponsoredEvents(List<String> previouslySponsoredEvents)
   {
      this.previouslySponsoredEvents = previouslySponsoredEvents;
   }
   
   @ManyToMany(mappedBy = "sponsors")
   public List<Event> getEvents()
   {
      return events;
   }
   
   public void setEvents(List<Event> events)
   {
      this.events = events;
   }
   
   public void addEvent(Event newEvent)
   {
      if(!events.contains(newEvent))
      {
         events.add(newEvent);
         newEvent.getSponsors().add(this);
      }
   }
}






















