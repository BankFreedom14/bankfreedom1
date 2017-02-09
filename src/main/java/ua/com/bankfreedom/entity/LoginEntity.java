package ua.com.bankfreedom.entity;

import javax.persistence.*;

/**
 * Created by Администратор on 11.10.16.
 */
@Entity
@Table(name = "login", schema = "bankfreedom")
public class LoginEntity {
//all columns
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idLogin")
    private int idLogin;

    @Basic
    @Column(name = "login")
    private String login;

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "idClient")
    private ClientsEntity client;

    @OneToOne(mappedBy = "login")
    private PasswordEntity pass;
// 2 constructors
    public LoginEntity() {
    }
    public LoginEntity(String login, ClientsEntity client) {
        this.login = login;
        this.client = client;
    }
//Setters and getters
    public ClientsEntity getClient() {
        return client;
    }
    public void setClient(ClientsEntity client) {
        this.client = client;
    }

    public int getIdLogin() {
        return idLogin;
    }
    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }


}
