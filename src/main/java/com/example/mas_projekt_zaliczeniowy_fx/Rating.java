package com.example.mas_projekt_zaliczeniowy_fx;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "Rating")
public class Rating
{
   private long id;
   private int ratingValue;
   private String review;
   
   private Employee employee;
   private Client client;
   private Attraction attraction;
   
   public Rating()
   {
      
   }
   
   public Rating(Client client, int ratingValue, String review) throws Exception
   {
      this.ratingValue = ratingValue;
      this.review = review;
      this.setClient(client);
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
   public int getRatingValue()
   {
      return ratingValue;
   }
   
   @Basic
   public String getReview()
   {
      return review;
   }
   
   public void setId(long id)
   {
      this.id = id;
   }
   
   public void setRatingValue(int ratingValue)
   {
      this.ratingValue = ratingValue;
   }
   
   public void setReview(String review)
   {
      this.review = review;
   }
   
   @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
   public Employee getEmployee()
   {
      return employee;
   }
   
   public void setEmployee(Employee newEmployee)
   {
      if(this.employee != newEmployee)
      {
         this.employee = newEmployee;
         newEmployee.addRating(this);
      }
   }
   
   @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
   public Client getClient()
   {
      return client;
   }
   
   public void setClient(Client newClient) throws Exception
   {
      if(newClient == null) throw new Exception("You can't create Rating without specifying the client.");
      if(this.client != newClient)
      {
         this.client = newClient;
         newClient.addRating(this);
      }
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
         newAttraction.addRating(this);
      }
   }
   
   //TODO usuwanie, jeśli usunie się klienta
}




















