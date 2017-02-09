package ua.com.bankfreedom.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Администратор on 11.10.16.
 */
@Entity
@Table(name = "clients", schema = "bankfreedom")
public class ClientsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idClient")
    private int idClient;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "surname")
    private String surname;
    @Basic
    @Column(name = "inn")
    private String inn;
    @OneToMany( mappedBy = "client", cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    public List<AccountsEntity> accounts= new ArrayList<>();
    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private LoginEntity login;
//Activity
        public void addAccount(AccountsEntity account){
        account.setClient(this);
        accounts.add(account);
    }
//Constructors
    public ClientsEntity() {
    }
    public ClientsEntity(String name, String surname, String inn) {
        this.name = name;
        this.surname = surname;
        this.inn = inn;
        this.login = login;
    }

//Getters & Setters
    public List<AccountsEntity> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }
    public void setAccounts(ArrayList<AccountsEntity> accounts) {
        this.accounts = accounts;
    }

    public LoginEntity getLogin() {
        return login;
    }

    public int getIdClient() {
        return idClient;
    }
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getInn() {
        return inn;
    }
    public void setInn(String inn) {
        this.inn = inn;
    }
}
