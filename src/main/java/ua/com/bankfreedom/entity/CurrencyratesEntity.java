package ua.com.bankfreedom.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Администратор on 11.10.16.
 */
@Entity
@Table(name = "currencyrates", schema = "bankfreedom")
public class CurrencyratesEntity {
    private int idCr;
    private String currency;
    private double rate;
    private Date date;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCR")
    public int getIdCr() {
        return idCr;
    }

    public void setIdCr(int idCr) {
        this.idCr = idCr;
    }

    @Basic
    @Column(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Basic
    @Column(name = "rate")
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
