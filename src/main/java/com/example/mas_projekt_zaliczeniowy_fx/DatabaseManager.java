package com.example.mas_projekt_zaliczeniowy_fx;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Collection;

public class DatabaseManager
{
   public static void addObjectToDB(Object object)
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
         
         session.persist(object);
         
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
   
   public static void addObjectToDB(Collection<Object> objects)
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
         
         for(Object o : objects)
         {
            session.persist(o);
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
   
   public static void updateObjectInDB(Object object)
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
         
         session.merge(object);
         
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
   
   public static void updateObjectInDB(Collection<Object> objects)
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
         
         for(Object o : objects)
         {
            session.merge(o);
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
   
   public static void addRatingToDB(Rating rating)
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
         
         Client client = rating.getClient();
         client = session.merge(client);
         
         session.persist(rating);
         
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















