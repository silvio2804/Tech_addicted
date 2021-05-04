package Model.utente;

import Model.storage.TableQuery;

public class UtenteQuery extends TableQuery {
    UtenteQuery(String table) {
        super(table);
    }

    String selectUtenti() {
        return String.format("SELECT * FROM %s LIMIT ?,?;", this.table);
    }

    String selectUtente() {
        return String.format("SELECT * FROM %s, WHERE email=?", table);
    }

    String insertUtente() {
        return String.format("INSERT INTO %s (email,nome,cognome,admin VALUES (?,?,?,?,?);", table);
    }

    String updateUtente() {
        return String.format("UPDATE FROM %s SET nome=?,cognome=? WHERE email=?", table);
    }

    String deleteUtente() {
        return String.format("DELETE FORM %s WHERE email=?",table);
    }
}
