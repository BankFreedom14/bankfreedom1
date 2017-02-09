package ua.com.bankfreedom.entity;

import ua.com.bankfreedom.inerfaces.Password;

import javax.persistence.*;

/**
 * Created by Администратор on 11.10.16.
 */
@Entity
@Table(name = "password", schema = "bankfreedom")
public class PasswordEntity implements Password {
//All columns and links
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idpass")
    private int idpass;
    @Basic
    @Column(name = "pass")
    private String pass;
    @OneToOne
    @JoinColumn(name = "idLogin")
    private LoginEntity login;
//2 constructors
    public PasswordEntity(String pass, LoginEntity login) {
        this.pass = pass;
        this.login = login;
    }
    public PasswordEntity() {
    }
//Getters and setters
    public LoginEntity getLogin() {
        return login;
    }
    public void setLogin(LoginEntity login) {
        this.login = login;
    }

    public int getIdpass() {
        return idpass;
    }
    public void setIdpass(int idpass) {
        this.idpass = idpass;
    }
//Check password
    public boolean checkPass(String password) {

        return password.equals(pass);
    }
//Change password
//    public boolean changePass() {}
}
