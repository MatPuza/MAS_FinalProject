package com.example.mas_projekt_zaliczeniowy_fx;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Attraction")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Attraction
{
   private long id;
   private String name;
   private List<String> awardsToWin = new ArrayList<>();
   private int minAgeRequirement;
   private int maxPplLoad;
   private static LocalDate lastConservationDate;
   public enum attractionType {ROLLERCOASTER, FERRIS_WHEEL, CARROUSEL, HAUNTED_HOUSE}
   private attractionType type;
   
   private List<Rating> ratings = new ArrayList<>();
   private List<AttractionService> attractionServisants = new ArrayList<>();
   private List<Reservation> reservations = new ArrayList<>();
   
   public Attraction()
   {
      
   }
   
   public Attraction(String name, List<String> awardsToWin, int minAgeRequirement, int maxPplLoad, attractionType type)
   {
      this.name = name;
      this.awardsToWin = awardsToWin;
      this.minAgeRequirement = minAgeRequirement;
      this.maxPplLoad = maxPplLoad;
      this.type = type;
      setLastConservationDate(LocalDate.now());
   }
   
   public Attraction(String name, String awardToWin, int minAgeRequirement, int maxPplLoad, attractionType type)
   {
      this.name = name;
      this.awardsToWin.add(awardToWin);
      this.minAgeRequirement = minAgeRequirement;
      this.maxPplLoad = maxPplLoad;
      this.type = type;
      setLastConservationDate(LocalDate.now());
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
   public String getName()
   {
      return name;
   }
   
   @ElementCollection
   public List<String> getAwardsToWin()
   {
      return awardsToWin;
   }
   
   @Basic
   @Column(nullable=false)
   public int getMinAgeRequirement()
   {
      return minAgeRequirement;
   }
   
   @Basic
   @Column(nullable=false)
   public int getMaxPplLoad()
   {
      return maxPplLoad;
   }
   
   @Transient
   public static LocalDate getLastConservationDate()
   {
      return lastConservationDate;
   }
   
   @Enumerated
   public attractionType getType()
   {
      return type;
   }
   
   public void setId(long id)
   {
      this.id = id;
   }
   
   public void setName(String name)
   {
      this.name = name;
   }
   
   public void setAwardsToWin(List<String> awardsToWin)
   {
      this.awardsToWin = awardsToWin;
   }
   
   public void setMinAgeRequirement(int minAgeRequirement)
   {
      this.minAgeRequirement = minAgeRequirement;
   }
   
   public void setMaxPplLoad(int maxPplLoad)
   {
      this.maxPplLoad = maxPplLoad;
   }
   
   public static void setLastConservationDate(LocalDate lastConservationDate)
   {
      Attraction.lastConservationDate = lastConservationDate;
   }
   
   public void setType(attractionType type)
   {
      this.type = type;
   }
   
   @OneToMany(mappedBy = "attraction")
   public List<Rating> getRatings()
   {
      return ratings;
   }
   
   public void setRatings(List<Rating> ratings)
   {
      this.ratings = ratings;
   }
   
   public void addRating(Rating newRating)
   {
      if(!ratings.contains(newRating))
      {
         ratings.add(newRating);
         newRating.setAttraction(this);
      }
   }
   
   @OneToMany(mappedBy = "attraction")
   public List<AttractionService> getServisants()
   {
      return attractionServisants;
   }
   
   public void setServisants(List<AttractionService> servisants)
   {
      this.attractionServisants = servisants;
   }
   
   public void addServisants(AttractionService newServisant)
   {
      if(!attractionServisants.contains(newServisant))
      {
         attractionServisants.add(newServisant);
         newServisant.setAttraction(this);
      }
   }
   
   @OneToMany(mappedBy = "attraction")
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
         newReservation.setAttraction(this);
      }
   }
}





















