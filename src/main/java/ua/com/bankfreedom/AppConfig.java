//// Can be used without main class
//package ua.com.bankfreedom;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import java.util.Scanner;
//
///**
// * Created by Администратор on 18.10.16.
// */
//@Configuration
//@ComponentScan("ua.com.bankfreedom")
//public class AppConfig {
//    @Bean
//    public EntityManager entityManager(){
//        System.out.println("EM");
//      EntityManagerFactory emf= Persistence.createEntityManagerFactory("BankFreedom");
//      return   emf.createEntityManager();
//    }
//    @Bean
//    public Scanner scanner(){
//        System.out.println("Scanner");
//        return new Scanner(System.in);
//    }
//}
