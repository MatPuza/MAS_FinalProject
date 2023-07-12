package com.example.mas_projekt_zaliczeniowy_fx;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

@Entity(name = "Cashier")
public class Cashier extends Employee
{
   public enum shiftType {MORNING_SHIFT, AFTERNOON_SHIFT}
   private shiftType shift;
   
   public Cashier()
   {
      
   }
   
   public Cashier(String name, String surname, LocalDate birthDate, String address, String postalAddress,
                  String phoneNumber, LocalDate hiringDate, String previousJob, shiftType shift)
   {
      super(name, surname, birthDate, address, postalAddress, phoneNumber, hiringDate, previousJob);
      this.shift = shift;
   }
   
   @Enumerated
   public shiftType getShift()
   {
      return shift;
   }
   
   public void setShift(shiftType shift)
   {
      this.shift = shift;
   }
}























