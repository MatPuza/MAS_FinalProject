package com.example.mas_projekt_zaliczeniowy_fx;

import com.example.mas_projekt_zaliczeniowy_fx.controllers.CustomSceneCreator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelloApplication extends Application
{
   @Override
   public void start(Stage stage) throws IOException
   {
      FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
      Parent root = fxmlLoader.load();
      Scene scene = new Scene(root, 320, 240);
      stage.setTitle("Witaj Użytkowniku!");
      stage.setScene(scene);
      stage.setWidth(root.prefWidth(0));
      stage.setHeight(root.prefHeight(0));
      stage.setResizable(false);
      stage.show();
   }
   
   public static String loggedUser = "Client1337";
   
   /*LocalDate date1 = LocalDate.of(1993, 12, 12);
   LocalDate date2 = LocalDate.of(2005, 10, 15);
   LocalDate date3 = LocalDate.of(1980, 3, 15);
   LocalDate date4 = LocalDate.of(1982, 7, 21);
   LocalDate date5 = LocalDate.of(1984, 11, 30);
   LocalDate date6 = LocalDate.of(1986, 1, 25);
   LocalDate date7 = LocalDate.of(1988, 6, 13);
   LocalDate date8 = LocalDate.of(1990, 9, 8);
   LocalDate date9 = LocalDate.of(1992, 12, 2);
   LocalDate date10 = LocalDate.of(1995, 5, 23);
   LocalDate date11 = LocalDate.of(1997, 8, 16);
   LocalDate date12 = LocalDate.of(2005, 10, 19);*/
   public static LocalDate date1 = LocalDate.of(1980, 3, 15);
   public static LocalDate date2 = LocalDate.of(1982, 7, 21);
   public static LocalDate date3 = LocalDate.of(1984, 11, 30);
   public static LocalDate date4 = LocalDate.of(1986, 1, 25);
   public static LocalDate date5 = LocalDate.of(1988, 6, 13);
   public static LocalDate date6 = LocalDate.of(1990, 9, 8);
   public static LocalDate date7 = LocalDate.of(1992, 12, 2);
   public static LocalDate date8 = LocalDate.of(1995, 5, 23);
   public static LocalDate date9 = LocalDate.of(1997, 8, 16);
   public static LocalDate date10 = LocalDate.of(2005, 10, 19);
   
   public static void main(String[] args) throws Exception
   {
      //populateWithClasses();
      launch();
      //getFromDB();
      //addRatings();
   }
   
   public static void populateWithClasses()
   {
      StandardServiceRegistry registry = null;
      SessionFactory sessionFactory = null;
      
      try
      {
         registry = new StandardServiceRegistryBuilder()
                 .configure()
                 .build();
         sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
         
         Session session = sessionFactory.openSession();
         session.beginTransaction();
         
         Employee[] employees = {
                 new Employee("Tomasz", "Jankowski", date1, "ul. Tęczowa 15", "01-155", "701 212 302", date1,
                              "Mechanik w zakładzie BMW"),
                 new Employee("Paweł", "Pawlak", date2, "ul. Długosza 21", "02-122", "702 213 303", date5,
                              "Inżynier w Intel"),
                 new Employee("Barbara", "Kot", date3, "ul. Złota 45", "03-100", "703 214 304", date4,
                              "Specjalista IT w Google"),
                 new Employee("Agata", "Gąska", date4, "ul. Zielona 2", "04-115", "704 215 305", date2,
                              "Programista w Microsoft"),
                 new Employee("Ewa", "Zając", date5, "ul. Srebrna 33", "05-123", "705 216 306", date9,
                              "Dietetyk w klinice zdrowia"),
                 new Employee("Jacek", "Borkowski", date6, "ul. Łąkowa 44", "06-133", "706 217 307", date10,
                              "Trener personalny w klubie fitness"),
                 new Employee("Magda", "Malinowska", date7, "ul. Miedziana 55", "07-144", "707 218 308", date3,
                              "Kierowca w Uber"),
                 new Employee("Robert", "Sokół", date8, "ul. Platynowa 66", "08-155", "708 219 309", date1,
                              "Sprzedawca w Carrefour"),
                 new Employee("Marcin", "Kruk", date9, "ul. Diamentowa 77", "09-166", "709 220 310", date3,
                              "Barista w Starbucks"),
                 new Employee("Beata", "Sikora", date10, "ul. Szafirowa 88", "10-177", "710 221 311", date5,
                              "Analityk finansowy w PKO")
         };
         
         Event[] events = {
                 new Event(Event.eventStatus.IN_PROGRESS, 100, date1, "12:30"),
                 new Event(Event.eventStatus.COMPLETED, 150, date2, "15:00"),
                 new Event(Event.eventStatus.AWAITING_AUTHORIZATION, 200, date3, "16:30"),
                 new Event(Event.eventStatus.UNAUTHORIZED, 50, date4, "10:00"),
                 new Event(Event.eventStatus.CANCELLED, 75, date5, "14:15"),
                 new Event(Event.eventStatus.IN_PROGRESS, 180, date6, "13:00"),
                 new Event(Event.eventStatus.COMPLETED, 90, date7, "17:30"),
                 new Event(Event.eventStatus.AWAITING_AUTHORIZATION, 300, date8, "18:00"),
                 new Event(Event.eventStatus.UNAUTHORIZED, 60, date9, "11:30"),
                 new Event(Event.eventStatus.CANCELLED, 120, date10, "15:45")
         };
         
         Sponsor[] sponsors = {
                 new Sponsor("Patryk", "Iksiński", date1, "ul. Koszykowa 15", "03-153", "999 302 503", "Mondelez",
                             "XXX", 5, "Urodziny Kuby"),
                 new Sponsor("Adam", "Kowalski", date2, "ul. Nowowiejska 1", "05-122", "602 202 102", "Milka", "XXX",
                             3,
                             "Wydarzenie 2"),
                 new Sponsor("Tomasz", "Nowak", date3, "ul. Polna 5", "00-100", "605 203 200", "Ferrero", "XXX", 4,
                             "Koncert Ani"),
                 new Sponsor("Katarzyna", "Wisniewska", date4, "ul. Sienkiewicza 20", "03-155", "509 302 503",
                             "Nestle",
                             "XXX", 2, "Spotkanie Kółka Literackiego"),
                 new Sponsor("Julia", "Zielińska", date5, "ul. Narutowicza 45", "05-133", "612 209 102", "Cadbury",
                             "XXX", 5, "Mecz Koszykówki"),
                 new Sponsor("Andrzej", "Duda", date6, "ul. Mickiewicza 10", "00-101", "675 200 102", "Ritter Sport",
                             "XXX", 3, "Zawody Łucznicze"),
                 new Sponsor("Aleksandra", "Kowalczyk", date7, "ul. Puławska 15", "03-159", "999 300 503", "Lindt",
                             "XXX", 4, "Bieg Charytatywny"),
                 new Sponsor("Mariusz", "Pudzianowski", date8, "ul. Złota 22", "05-120", "603 200 101", "Kinder",
                             "XXX",
                             5, "Turniej Szachowy")
         };
         
         sponsors[0].addEvent(events[0]);
         sponsors[0].addEvent(events[1]);
         sponsors[1].addEvent(events[1]);
         sponsors[2].addEvent(events[3]);
         sponsors[4].addEvent(events[7]);
         
         Client[] clients = {
                 new Client("Jan", "Kowalczyk", date1, "ul. Brzozowa 10", "11-188", "721 222 312",
                            "jan.kowalczyk@gmail.com", "85040506070"),
                 new Client("Anna", "Nowakowska", date2, "ul. Wiśniowa 20", "12-199", "722 223 313",
                            "anna.nowakowska@gmail.com", "86040607080"),
                 new Client("Michał", "Wójcik", date3, "ul. Malinowa 30", "13-200", "723 224 314",
                            "michal.wojcik@gmail.com", "87040708090"),
                 new Client("Katarzyna", "Kowal", date4, "ul. Czereśniowa 40", "14-211", "724 225 315",
                            "katarzyna.kowal@gmail.com", "88040809010"),
                 new Client("Piotr", "Kamiński", date5, "ul. Jabłoniowa 50", "15-222", "725 226 316",
                            "piotr.kaminski@gmail.com", "89040901020"),
                 new Client("Karolina", "Lewandowska", date6, "ul. Gruszkowa 60", "16-233", "726 227 317",
                            "karolina.lewandowska@gmail.com", "90041002030"),
                 new Client("Jakub", "Zieliński", date7, "ul. Śliwkowa 70", "17-244", "727 228 318",
                            "jakub.zielinski@gmail.com", "91041103040"),
                 new Client("Magdalena", "Szewczyk", date8, "ul. Wiśniowa 80", "18-255", "728 229 319",
                            "magdalena.szewczyk@gmail.com", "92041204050"),
                 new Client("Wojciech", "Dąbrowski", date9, "ul. Czereśniowa 90", "19-266", "729 230 320",
                            "wojciech.dabrowski@gmail.com", "93041305060"),
                 new Client("Elżbieta", "Włodarczyk", date10, "ul. Jabłoniowa 100", "20-277", "730 231 321",
                            "elzbieta.wlodarczyk@gmail.com", "94041406070")
         };
         
         Cashier[] cashiers = {
                 new Cashier("Krzysztof", "Pawlak", date6, "ul. Jaworowa 11", "11-278", "731 232 322", date2,
                             "Barista w Starbucks", Cashier.shiftType.MORNING_SHIFT),
                 new Cashier("Izabela", "Majewska", date3, "ul. Brzozowa 12", "12-289", "732 233 323", date2,
                             "Kasjerka w Biedronka", Cashier.shiftType.AFTERNOON_SHIFT),
                 new Cashier("Robert", "Sadowski", date5, "ul. Klonowa 13", "13-290", "733 234 324", date3,
                             "Sprzedawca w MediaMarkt", Cashier.shiftType.MORNING_SHIFT)
         };
         
         CleaningService[] cleaningServices = {
                 new CleaningService("Jan", "Szewczyk", date3, "ul. Akacjowa 14", "14-301", "734 235 325", date5,
                                     "Pracownik serwisu sprzątającego", date7),
                 new CleaningService("Agata", "Kubiak", date1, "ul. Wiśniowa 15", "15-312", "735 236 326", date2,
                                     "Pracownik serwisu sprzątającego", date8),
                 new CleaningService("Piotr", "Rutkowski", date4, "ul. Malinowa 16", "16-323", "736 237 327", date10,
                                     "Pracownik serwisu sprzątającego", date9)
         };
         
         List<String> licenses1 = Arrays.asList("ROLLERCOASTER", "HAUNTED_HOUSE");
         List<String> licenses2 = Arrays.asList("ROLLERCOASTER", "CARROUSEL");
         List<String> licenses3 = Arrays.asList("FERRIS_WHEEL", "HAUNTED_HOUSE");
         
         AttractionService[] attractionServisants = {
                 new AttractionService("Katarzyna", "Kowalczyk", date1, "ul. Śliwkowa 17", "17-334", "737 238 328",
                                       date7, "Pracownik parku rozrywki", licenses1),
                 new AttractionService("Marek", "Wojciechowski", date5, "ul. Morelowa 18", "18-345", "738 239 329",
                                       date8, "Pracownik parku rozrywki", licenses2),
                 new AttractionService("Anna", "Kowalska", date9, "ul. Czereśniowa 19", "19-356", "739 240 330", date3,
                                       "Pracownik parku rozrywki", licenses3)
         };
         
         Attraction[] attractions = {
                 new Attraction("London Eye", "Piękne widoki", 3, 100, Attraction.attractionType.FERRIS_WHEEL),
                 new Attraction("Magiczny Karuzela", Arrays.asList("Zabawa na całego", "Unikalne doznania"), 5, 50,
                                Attraction.attractionType.CARROUSEL),
                 new Attraction("Dom Strachów", "Niesamowite doświadczenia", 15, 30,
                                Attraction.attractionType.HAUNTED_HOUSE),
                 new Attraction("Górska Przygoda", "Niezapomniane wrażenia", 10, 70,
                                Attraction.attractionType.ROLLERCOASTER)
         };
         
         attractions[0].addServisants(attractionServisants[0]);
         attractions[1].addServisants(attractionServisants[2]);
         attractions[2].addServisants(attractionServisants[1]);
         
         Reservation[] reservations = {
                 new Reservation(date1, "15:00", 300, 5, Reservation.reservationStatus.COMPLETED),
                 new Reservation(date4, "10:30", 500, 3, Reservation.reservationStatus.CANCELLED),
                 new Reservation(date7, "18:30", 600, 2, Reservation.reservationStatus.COMPLETED)
         };
         
         attractions[0].addReservation(reservations[0]);
         attractions[1].addReservation(reservations[2]);
         attractions[2].addReservation(reservations[1]);
         
         clients[0].addReservation(reservations[0]);
         clients[3].addReservation(reservations[1]);
         clients[4].addReservation(reservations[2]);
         
         for(Employee e : employees)
         {
            session.persist(e);
         }
         
         for(Event ev : events)
         {
            session.persist(ev);
         }
         
         for(Sponsor s : sponsors)
         {
            session.persist(s);
         }
         
         for(Client cl : clients)
         {
            session.persist(cl);
         }
         
         for(Cashier cashier : cashiers)
         {
            session.persist(cashier);
         }
         
         for(CleaningService cleaningService : cleaningServices)
         {
            session.persist(cleaningService);
         }
         
         for(AttractionService attractionService : attractionServisants)
         {
            session.persist(attractionService);
         }
         
         for(Attraction attraction : attractions)
         {
            session.persist(attraction);
         }
         
         for(Reservation reservation : reservations)
         {
            session.persist(reservation);
         }
         
         session.getTransaction().commit();
         session.close();
      }
      catch(Exception e)
      {
         e.printStackTrace();
         StandardServiceRegistryBuilder.destroy(registry);
      }
      finally
      {
         if(sessionFactory != null) sessionFactory.close();
      }
   }
   
   public static void addRatings() throws Exception
   {
      List<Client> clients = new ArrayList<>();
      List<Employee> employees = new ArrayList<>();
      
      StandardServiceRegistry registry = null;
      SessionFactory sessionFactory = null;
      
      try
      {
         registry = new StandardServiceRegistryBuilder()
                 .configure()
                 .build();
         sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
         
         Session session = sessionFactory.openSession();
         session.beginTransaction();
         
         clients = session.createQuery("FROM Client ").list();
         employees = session.createQuery("FROM Employee ").list();
         
         /*Client c = new Client("Patryk", "Iksiński", date1, "ul. Koszykowa 15", "03-153", "999 302 503", "X", "X");
         
         for(Employee e : employees)
         {
            System.out.println(e.getAge());
            e.rateEmployee(c, 3, "SSS");
            session.persist(e);
         }*/
         
         session.getTransaction().commit();
         session.close();
      }
      catch(Exception e)
      {
         e.printStackTrace();
         StandardServiceRegistryBuilder.destroy(registry);
      }
      finally
      {
         if(sessionFactory != null) sessionFactory.close();
      }
      
      for(Client c : clients)
      {
         System.out.println(c.getName());
      }
      
      for(Employee e : employees)
      {
         e.rateEmployee(clients.get(0), 4, "");
      }
   }
   
   public static void getFromDB()
   {
      StandardServiceRegistry registry = null;
      SessionFactory sessionFactory = null;
      
      try
      {
         registry = new StandardServiceRegistryBuilder()
                 .configure()
                 .build();
         sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
         
         Session session = sessionFactory.openSession();
         session.beginTransaction();
         
         List<Employee> employees = session.createQuery("FROM Employee ").list();
         
         Client c = new Client("Patryk", "Iksiński", date1, "ul. Koszykowa 15", "03-153", "999 302 503", "X", "X");
         
         for(Employee e : employees)
         {
            System.out.println(e.getAge());
            e.rateEmployee(c, 3, "SSS");
            session.persist(e);
         }
         
         session.getTransaction().commit();
         session.close();
      }
      catch(Exception e)
      {
         e.printStackTrace();
         StandardServiceRegistryBuilder.destroy(registry);
      }
      finally
      {
         if(sessionFactory != null) sessionFactory.close();
      }
   }
}