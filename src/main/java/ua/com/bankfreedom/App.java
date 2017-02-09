package ua.com.bankfreedom; /**
 * Created by Администратор on 11.10.16.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import ua.com.bankfreedom.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

//It`s Needed to parse currency rates and to make equivalent in UAH
public class App {

    public static void main(String[] args) throws InterruptedException {
      EntityManagerFactory emf= Persistence.createEntityManagerFactory("BankFreedom");
        EntityManager em=emf.createEntityManager();

        Scanner sc=new Scanner(System.in);

        Thread.sleep(2000);

        while (true) {
            System.out.println("Make your choice:\n`1`.Sign up.\n`2`. Log in");

            switch (sc.nextLine()) {
                case "1":
                    makeClient(sc, em);
                    break;
                case "2":
                    ClientsEntity cl = access(sc, em);
                    if (cl != null) accActivity(sc, em, cl);
                    else System.out.println("Wrong login or password");
                    break;
                default:
                    System.out.println("Wrong number");
                    break;
            }
        }
    }

    //1.Sign in
    private static void makeClient(Scanner sc, EntityManager em) {

        System.out.println("Tape name");
        String name = sc.nextLine();

        System.out.println("Tape surname");
        String surname = sc.nextLine();

        System.out.println("Tape inn-10 figures");
        String inn = sc.nextLine();

        System.out.println("Tape login");
        String login = sc.nextLine();

        System.out.println("Tape password");
        String pass = sc.nextLine();


        ClientsEntity client = new ClientsEntity(name, surname, inn);
        LoginEntity log = new LoginEntity(login, client);
        PasswordEntity password = new PasswordEntity(pass, log);

        em.getTransaction().begin();
        try {
            em.persist(client);
            em.persist(log);
            em.persist(password);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            System.out.println("Wrong data. Ty again");
        }
    }
    // 2.Log in
    private static ClientsEntity access(Scanner sc, EntityManager em) {

        System.out.println("Tape login");
        String logi = sc.nextLine();

        System.out.println("Tape password");
        String password = sc.nextLine();

        try {
            LoginEntity log = null;
            PasswordEntity pass = null;
            Query q = em.createQuery("Select log From LoginEntity log where log.login=:logi", LoginEntity.class);
            q.setParameter("logi", logi);
            log = (LoginEntity) q.getSingleResult();


            q = em.createQuery("Select pass from PasswordEntity pass where pass.login=:log", PasswordEntity.class);
            q.setParameter("log", log);
            pass = (PasswordEntity) q.getSingleResult();
            boolean result = pass.checkPass(password);
            if (result) return log.getClient();
            else return null;
        } catch (Exception ex) {
            ;
        }
        return null;
    }

    //For logged in clients
    private static void accActivity(Scanner sc, EntityManager em, ClientsEntity cl) {
        while (true) {
            System.out.println("Make your choice: \n`1` View accounts.\n`2` Make statemant.\n" +
                    "`3` Payment\n`4` Create account\n`5` Exit to previous menu");
            switch (sc.nextLine()) {
                case "1":
                    viewAll(cl);
                    break;
                case "2":
                    statement(sc, cl);
                    break;
                case "3":
                    transfer(sc, em, cl);
                    break;
                case "4":
                    createAcc(sc, em, cl);
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Wrong number. Try again");
            }
        }
    }
    //1.View all accounts
    private static void viewAll(ClientsEntity cl) {
        List<AccountsEntity> list = cl.getAccounts();   //q.getResultList();
        for (AccountsEntity a : list) {
            System.out.println(a);
        }
    }
    //2.Statement
    private static void statement(Scanner sc, ClientsEntity cl) {
        System.out.println("Tape the account number");
        String acc1 = sc.nextLine();
        System.out.println("Tape the beginning date in format YYYY-MM-DD");
        String beg = sc.nextLine();
        System.out.println("Tape the date of end in format YYYY-MM-DD");
        String end = sc.nextLine() + " 3";

        AccountsEntity account = null;
        for (AccountsEntity acc : cl.getAccounts()) {
            if (acc.getNumber().equals(acc1)) {
                account = acc;
                System.out.println(acc);
            }
        }
        if (account == null) {
            System.out.println("No such account");
            return;
        }
        int i = 0;
        for (TransactionsEntity tr : account.getTrans()) {
            if (tr.getDate().toString().compareTo(beg) >= 0 & tr.getDate().toString().compareTo(end) <= 0) {
                i++;
                System.out.println(tr);
            }
        }
        if (i == 0) System.out.println("There were no transactions during this period");
    }
    //3.Make payment
    private static void transfer(Scanner sc, EntityManager em, ClientsEntity cl) {
        System.out.println("Write your account");
        String acc1 = sc.nextLine();
        System.out.println("Write amount");
        Double amount = Double.parseDouble(sc.nextLine());
        System.out.println("Write mfo - 6 figures(our bank - 360000");
        int mfo = Integer.parseInt(sc.nextLine());  //Can make exception if not Integer
        System.out.println("Write recipient`s account");
        String acc2 = sc.nextLine();

        AccountsEntity account = null;
        for (AccountsEntity acc : cl.getAccounts()) {
            if (acc.getNumber().equals(acc1)) {
                account = acc;
                System.out.println(acc);
            }
        }
        if (account == null) {
            System.out.println("No such account");
            return;
        }

        em.getTransaction().begin();
        try {
            if (mfo == 360000) {
                AccountsEntity acc3 = null;
                Query qu = em.createQuery("Select acc from AccountsEntity acc where acc.number=:acc2");
                qu.setParameter("acc2", acc2);
                acc3 = (AccountsEntity) qu.getSingleResult(); // Can make exception if there is no such account
                TransactionsEntity tr = new TransactionsEntity(amount, mfo, account, acc2);
                em.persist(tr);
                account.setAmount(amount);
                acc3.setAmount(-amount);
            } else {
                TransactionsEntity tr = new TransactionsEntity(amount, mfo, account, acc2);
                em.persist(tr);
                account.setAmount(amount);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Try again");
            em.getTransaction().rollback();
            return;
        }
        System.out.println("Money has been send");
    }
    //4.Create account
    private static void createAcc(Scanner sc, EntityManager em, ClientsEntity cl) {
        Random ran = new Random();
        String number = "2620" + ran.nextInt(100500);
        System.out.println("Choose currency:UAH,USD,EUR");
        String curr = sc.nextLine();
        System.out.println("First add summ");
        Double amount = Double.parseDouble(sc.nextLine()); //Can make exception if not Double
        AccountsEntity acc = new AccountsEntity(number, curr, amount, cl); //Can make exception if number is not unique

        em.getTransaction().begin();
        try {
            em.persist(acc);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }
}





