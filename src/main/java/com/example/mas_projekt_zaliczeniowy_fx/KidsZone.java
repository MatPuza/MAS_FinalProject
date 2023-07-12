package com.example.mas_projekt_zaliczeniowy_fx;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.List;

@Entity(name = "KidsZone")
public class KidsZone extends Attraction
{
   private boolean requiresGuardian;
   
   public KidsZone()
   {
      
   }
   
   public KidsZone(String name, List<String> awardsToWin, int minAgeRequirement, int maxPplLoad,
                   Attraction.attractionType type, boolean requiresGuardian)
   {
      super(name, awardsToWin, minAgeRequirement, maxPplLoad, type);
      this.requiresGuardian = requiresGuardian;
   }
   
   @Basic
   @Column(nullable=false)
   public boolean isRequiresGuardian()
   {
      return requiresGuardian;
   }
   
   public void setRequiresGuardian(boolean requiresGuardian)
   {
      this.requiresGuardian = requiresGuardian;
   }
}














