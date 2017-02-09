package ua.com.bankfreedom.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Администратор on 11.10.16.
 */
@Entity
@Table(name = "accounts", schema = "bankfreedom")
public class AccountsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idAccount")
    private int idAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClient")
    private ClientsEntity client;
    @OneToMany (mappedBy = "account", cascade = CascadeType.ALL)
    List<TransactionsEntity> trans=new ArrayList<>();
    @Basic
    @Column(name = "number")
    private String number;
    @Basic
    @Column(name = "currency")
    private String currency;
    @Basic
    @Column(name = "amuont")
    private Double amount;
    @Basic
    @Column(name = "diskriminator")
    private String diskriminator;

//Constructors
    public AccountsEntity() {
    }
    public AccountsEntity(String number, String currency, Double amuont,ClientsEntity client) {
        this.number = number;
        this.currency = currency;
        this.amount = amuont;
        this.client=client;
    }
//Getters and setters
    public List<TransactionsEntity> getTrans() {
        return Collections.unmodifiableList(trans);
    }

    public int getIdAccount() {
        return idAccount;
    }
    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = this.amount-amount;
    }

    public String getDiskriminator() {
        return diskriminator;
    }
    public void setDiskriminator(String diskriminator) {
        this.diskriminator = diskriminator;
    }

    public void setClient(ClientsEntity client) {
        this.client = client;
    }
    public ClientsEntity getClient() {
        return client;
    }
//Activity
    void addTrans(TransactionsEntity tran){
        tran.setAccount(this);
        trans.add(tran);
    }
  public String toString(){
       return "["+number+" "+currency+" "+amount+"]";
    }









}
