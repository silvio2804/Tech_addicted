package Model.utente;

import Model.carrello.Carrello;
import Model.ordine.Ordine;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Utente {

    public Utente(){
        super();
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

    public String getCitta() {
        return citta;
    }

    public void setCitta(String cap) {
        this.citta = cap;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public ArrayList<Ordine> getOrdini() {
        return ordini;
    }

    public void setOrdini(ArrayList<Ordine> ordini) {
        this.ordini = ordini;
    }

    public ArrayList<Carrello> getStorico() {
        return storico;
    }

    public void setStorico(ArrayList<Carrello> storico) {
        this.storico = storico;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String nome;
    private String cognome;
    private int id;
    private String dataNa;
    private String email;
    private String via;
    private int numeroCivico;
    private String citta;
    private boolean admin;
    private String password;

    private ArrayList<Ordine> ordini;
    private ArrayList<Carrello> storico;
}
