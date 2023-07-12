package com.example.mas_projekt_zaliczeniowy_fx;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Employee")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Employee extends Person
{
   private LocalDate hiringDate;
   private static double hourlySalary;
   
   private List<String> previousJobs = new ArrayList<>();
   
   private List<Rating> ratings = new ArrayList<>();
   
   public Employee()
   {
      
   }
   
   public Employee(String name, String surname, LocalDate birthDate, String address, String postalAddress,
                   String phoneNumber, LocalDate hiringDate, List<String> previousJobs)
   {
      super(name, surname, birthDate, address, postalAddress, phoneNumber);
      this.hiringDate = hiringDate;
      this.previousJobs = previousJobs;
   }
   
   public Employee(String name, String surname, LocalDate birthDate, String address, String postalAddress,
                   String phoneNumber, LocalDate hiringDate, String previousJob)
   {
      super(name, surname, birthDate, address, postalAddress, phoneNumber);
      this.hiringDate = hiringDate;
      this.previousJobs.add(previousJob);
   }
   
   @Basic
   @Column(nullable = false)
   public LocalDate getHiringDate()
   {
      return hiringDate;
   }
   
   @Transient
   public static double getHourlySalary()
   {
      return hourlySalary;
   }
   
   @ElementCollection
   public List<String> getPreviousJobs()
   {
      return previousJobs;
   }
   
   public void setHiringDate(LocalDate hiringDate)
   {
      this.hiringDate = hiringDate;
   }
   
   public static void setHourlySalary(double hourlySalary)
   {
      Employee.hourlySalary = hourlySalary;
   }
   
   public void setPreviousJobs(List<String> previousJobs)
   {
      this.previousJobs = previousJobs;
   }
   
   @OneToMany(mappedBy = "employee")
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
         newRating.setEmployee(this);
      }
   }
   
   @Transient
   public int calculateBonus(double rating)
   {
      if(rating > 4.8) return 8;
      else if(rating > 4.3) return 5;
      else if(rating > 3.8) return 3;
      return 1;
   }
   
   public void rateEmployee(Client client, int ratingValue, String review) throws Exception
   {
      if(ratingValue < 1) ratingValue = 1;
      if(ratingValue > 5) ratingValue = 5;
      
      Rating rating = new Rating(client, ratingValue, review);
      
      rating.setEmployee(this);
      this.addRating(rating);
      System.out.println("AAAAAAAAAAAAAAAA");
      System.out.println(rating.getClient().getName());
      DatabaseManager.addRatingToDB(rating);
   }
}



















