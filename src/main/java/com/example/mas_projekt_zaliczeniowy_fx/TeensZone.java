package com.example.mas_projekt_zaliczeniowy_fx;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.List;

@Entity(name = "TeensZone")
public class TeensZone extends Attraction
{
   private int minHeight;
   
   public TeensZone()
   {
      
   }
   
   public TeensZone(String name, List<String> awardsToWin, int minAgeRequirement, int maxPplLoad, Attraction.attractionType type
           , int minHeight)
   {
      super(name, awardsToWin, minAgeRequirement, maxPplLoad, type);
      this.minHeight = minHeight;
   }
   
   @Basic
   @Column(nullable=false)
   public int getMinHeight()
   {
      return minHeight;
   }
   
   public void setMinHeight(int minHeight)
   {
      this.minHeight = minHeight;
   }
}



















