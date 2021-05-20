package Model.ordine;

import Model.carrello.Carrello;
import Model.carrello.CarItem;
import Model.utente.Utente;

import java.time.LocalDate;

public class Ordine {
    public Ordine(){
        super();
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public LocalDate getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(LocalDate dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Carrello getCarrello() {
        return carrello;
    }

    public void setCarrello(Carrello carrello) {
        this.carrello = carrello;
    }

    private double totale;
    private String pagamento;
    private int idOrdine;
    private LocalDate dataOrdine;
    private Utente utente;
    private Carrello carrello;

    public int entries() {
        int entries = 0;
        for(CarItem item: carrello.getItems()){
            entries++;
        }
        return entries;
    }
}
