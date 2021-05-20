package Model.ordine;

import Model.storage.QueryBuilder;

import javax.management.Query;

final class QueryOrdine {
    private static final String ORDINE_QUERY = "ordine";
    private static final String ORDINE_ALIAS = "ord";

    static String fetchOrdini() {
        QueryBuilder queryBuilder = new QueryBuilder(ORDINE_QUERY,ORDINE_ALIAS);
        queryBuilder.select().limit(true);
        return queryBuilder.generateQuery();
    }

    static String fetchOrdiniConProdotti(int id){
        QueryBuilder queryBuilder = new QueryBuilder(ORDINE_QUERY,ORDINE_ALIAS);
        queryBuilder.select().innerJoin("carrello","car").on("car.idCar = ord.idCar")
                .innerJoin("prodottiInCarrello","proInCar").on("car.idCar=proInCar.idCarrello")
                .innerJoin("prodotto","pro").on("proInCar.idProdotto=pro.idProd")
                .where("car.idUtente=?");
        return queryBuilder.generateQuery();
    }

    static String createOrdine(){
        QueryBuilder queryBuilder = new QueryBuilder(ORDINE_QUERY,ORDINE_ALIAS);
        queryBuilder.insert("totale");
        return queryBuilder.generateQuery();
    }

    static String insertCar(){
        QueryBuilder queryBuilder = new QueryBuilder("prodottiInCarrello","proInCar");
        queryBuilder.insert("idCarrello","idProdotto","quantita");
        return queryBuilder.generateQuery();
    }

    public static String deleteOrdine() {
        QueryBuilder queryBuilder = new QueryBuilder(ORDINE_QUERY,ORDINE_ALIAS);
        queryBuilder.delete().where("id=?");
        return queryBuilder.generateQuery();
    }
    public static String updateOrdine(){
        QueryBuilder queryBuilder = new QueryBuilder(ORDINE_QUERY,ORDINE_ALIAS);
        queryBuilder.update();
        return queryBuilder.generateQuery();
    }
}
