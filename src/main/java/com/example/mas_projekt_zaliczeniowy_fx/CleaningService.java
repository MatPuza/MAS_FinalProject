package com.example.mas_projekt_zaliczeniowy_fx;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity(name = "CleaningService")
public class CleaningService extends Employee
{
   private LocalDate lastCleaning;
   
   public CleaningService()
   {
      
   }
   
   public CleaningService(String name, String surname, LocalDate birthDate, String address, String postalAddress,
                          String phoneNumber, LocalDate hiringDate, String previousJob, LocalDate lastCleaning)
   {
      super(name, surname, birthDate, address, postalAddress, phoneNumber, hiringDate, previousJob);
      this.lastCleaning = lastCleaning;
   }
   
   @Basic
   @Column(nullable=false)
   public LocalDate getLastCleaning()
   {
      return lastCleaning;
   }
   
   public void setLastCleaning(LocalDate lastCleaning)
   {
      this.lastCleaning = lastCleaning;
   }
}



















