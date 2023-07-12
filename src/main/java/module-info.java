module com.example.mas_projekt_zaliczeniowy_fx {
   requires javafx.controls;
   requires javafx.fxml;
   requires javafx.web;
   
   requires org.controlsfx.controls;
   requires com.dlsc.formsfx;
   requires validatorfx;
   requires org.kordamp.ikonli.javafx;
   requires org.kordamp.bootstrapfx.core;
   requires eu.hansolo.tilesfx;
   requires com.almasb.fxgl.all;
   requires org.hibernate.orm.core;
   requires jakarta.persistence;
   requires java.naming;
   
   opens com.example.mas_projekt_zaliczeniowy_fx to javafx.fxml, org.hibernate.orm.core;
   exports com.example.mas_projekt_zaliczeniowy_fx;
   exports com.example.mas_projekt_zaliczeniowy_fx.controllers;
   opens com.example.mas_projekt_zaliczeniowy_fx.controllers to javafx.fxml, org.hibernate.orm.core;
}