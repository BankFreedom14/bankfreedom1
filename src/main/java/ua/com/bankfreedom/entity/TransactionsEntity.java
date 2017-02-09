package ua.com.bankfreedom.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Администратор on 11.10.16.
 */
@Entity
@Table(name = "transactions", schema = "bankfreedom")
public class TransactionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idTrans")
    private int idTrans;
    @Basic
    @Column(name = "amount")
    private double amount;
    @Basic
    @Column(name = "recipient")
    private String recipient;
    @Basic
    @Column(name = "MFO")
    private int mfo;

    public Date getDate() {
        return date;
    }

    @Basic
    @Column(name = "Date")
    private Date date;
    @ManyToOne
    @JoinColumn(name="idAccount")
    private AccountsEntity account;
//Constructors
public TransactionsEntity(double amount, int mfo, AccountsEntity account, String recipient) {
    this.amount = amount;
    this.mfo = mfo;
    this.account = account;
    this.recipient=recipient;
    date=new Date();
}
 public TransactionsEntity() {
    }
//Getters & setters
    public AccountsEntity getAccount() {
        return account;
    }
    public void setAccount(AccountsEntity account) {
        this.account = account;
    }

    public int getIdTrans() {
        return idTrans;
    }
    public void setIdTrans(int idTrans) {
        this.idTrans = idTrans;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getMfo() {
        return mfo;
    }
    public void setMfo(int mfo) {
        this.mfo = mfo;
    }

public String toString(){
    return "["+" "+date+" "+amount+" "+recipient+"]";
}
}
