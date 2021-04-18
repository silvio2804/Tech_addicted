package Model;

import java.util.GregorianCalendar;

public class Customer {

    public Customer(){
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataNa() {
        return dataNa;
    }

    public void setDataNa(String dataNa) {
        this.dataNa = dataNa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public int getNumeroCivico() {
        return numeroCivico;
    }

    public void setNumeroCivico(int numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    private String nome;
    private String cognome;
    private int id;
    private String dataNa;
    private String email;
    private String via;
    private int numeroCivico;
    private String cap;
}
