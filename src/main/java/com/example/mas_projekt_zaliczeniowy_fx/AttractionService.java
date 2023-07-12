package com.example.mas_projekt_zaliczeniowy_fx;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "AttractionService")
public class AttractionService extends Employee
{
   private List<String> ownedAttractionLicenses = new ArrayList<>();
   
   private Attraction attraction;
   
   public AttractionService()
   {
      
   }
   
   public AttractionService(String name, String surname, LocalDate birthDate, String address, String postalAddress,
                            String phoneNumber, LocalDate hiringDate, String previousJob,
                            List<String> ownedAttractionLicenses)
   {
      super(name, surname, birthDate, address, postalAddress, phoneNumber, hiringDate, previousJob);
      this.ownedAttractionLicenses = ownedAttractionLicenses;
   }
   
   @ElementCollection
   public List<String> getOwnedAttractionLicenses()
   {
      return ownedAttractionLicenses;
   }
   
   public void setOwnedAttractionLicenses(List<String> ownedAttractionLicenses)
   {
      this.ownedAttractionLicenses = ownedAttractionLicenses;
   }
   
   @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
   public Attraction getAttraction()
   {
      return attraction;
   }
   
   public void setAttraction(Attraction newAttraction)
   {
      if(this.attraction != newAttraction)
      {
         this.attraction = newAttraction;
         newAttraction.addServisants(this);
      }
   }
}























