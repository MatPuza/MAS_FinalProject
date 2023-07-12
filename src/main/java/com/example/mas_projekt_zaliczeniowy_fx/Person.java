package com.example.mas_projekt_zaliczeniowy_fx;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Person")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person
{
   private long id;
   private String name;
   private String surname;
   private LocalDate birthDate;
   private String address;
   private String postalAddress;
   
   private List<String> phoneNumbers = new ArrayList<>();
   
   public Person()
   {
      
   }
   
   public Person(String name, String surname, LocalDate birthDate, String address, String postalAddress,
                 List<String> phoneNumbers)
   {
      this.name = name;
      this.surname = surname;
      this.birthDate = birthDate;
      this.address = address;
      this.postalAddress = postalAddress;
      this.phoneNumbers = phoneNumbers;
   }
   
   public Person(String name, String surname, LocalDate birthDate, String address, String postalAddress, String phoneNumber)
   {
      this.name = name;
      this.surname = surname;
      this.birthDate = birthDate;
      this.address = address;
      this.postalAddress = postalAddress;
      this.phoneNumbers.add(phoneNumber);
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
   
   @Basic
   @Column(nullable=false)
   public String getSurname()
   {
      return surname;
   }
   
   @Basic
   @Column(nullable=false)
   public LocalDate getBirthDate()
   {
      return birthDate;
   }
   
   @Basic
   @Column(nullable=false)
   public String getAddress()
   {
      return address;
   }
   
   @Basic
   @Column(nullable=false)
   public String getPostalAddress()
   {
      return postalAddress;
   }
   
   @ElementCollection
   public List<String> getPhoneNumbers()
   {
      return phoneNumbers;
   }
   
   @Transient
   public int getAge()
   {
      return calcAge();
   }
   
   public void setId(long id)
   {
      this.id = id;
   }
   
   public void setName(String name)
   {
      this.name = name;
   }
   
   public void setSurname(String surname)
   {
      this.surname = surname;
   }
   
   public void setBirthDate(LocalDate birthDate)
   {
      this.birthDate = birthDate;
   }
   
   public void setAddress(String address)
   {
      this.address = address;
   }
   
   public void setPostalAddress(String postalAddress)
   {
      this.postalAddress = postalAddress;
   }
   
   public void setPhoneNumbers(List<String> phoneNumbers)
   {
      this.phoneNumbers = phoneNumbers;
   }
   
   private int calcAge()
   {
      return Period.between(LocalDateTime.now().toLocalDate(), birthDate.atStartOfDay().toLocalDate()).getYears() * -1;
   }
}












