package Model.storage;

import Model.search.Condition;
import Model.search.Operator;

import java.util.List;
import java.util.StringJoiner;

/**
 * Query builder build query by Silvio
 */


public class QueryBuilder {

    public QueryBuilder(String table, String alias) {
        this.table = table;
        this.alias = alias;
        this.query = new StringBuilder();
    }

    public String generateQuery(){
        String generatedQuery = query.toString();
        query.setLength(0);
        return generatedQuery;
    }

    public QueryBuilder select(String... fields){
        query.append("SELECT ");
        if(fields.length == 0){
            query.append("*");
        }else{
            StringJoiner commaJoiner = new StringJoiner(",");
            for(String field : fields){
                commaJoiner.add(String.format("%s.%s",alias,field));//serve per concatenare alias.attributo ruotando sull'array di fields
            }
            query.append(commaJoiner.toString()); //concateni quello che crea il commajoiner
        }
        query.append(" FROM ").append(table).append(" AS ").append(alias); //alias della tabella
        return this;
    }

    public QueryBuilder where(String condition){
        query.append(" WHERE ").append(condition);
        return this;
    }

    public QueryBuilder orCondition(String condition){
        query.append(OR).append(condition);
        return this;
    }

    public QueryBuilder andCondition(String condition){
        query.append(AND).append(condition);
        return this;
    }

    public QueryBuilder insert(String... fields){
        query.append("INSERT INTO ").append(table).append(' ');
        StringJoiner commaJoiner = new StringJoiner(",","(",")");
        for(String field : fields){
            commaJoiner.add(field);
        }
        query.append(commaJoiner.toString());
        query.append(" VALUES ");
        int numberOfFields = fields.length;
        StringJoiner qmJoiner = new StringJoiner(",","(",")");
        do{
            qmJoiner.add(QM);
            numberOfFields--;
        }while (numberOfFields != 0);
        query.append(qmJoiner.toString());
        return this;
    }

    public QueryBuilder delete(){
         query.append("DELETE FROM ").append(table);
         return this;
    }

    public QueryBuilder update(String... fields){
        query.append("UPDATE ").append(table);
        StringJoiner commaJoiner = new StringJoiner(",");
        for(String field : fields){
            commaJoiner.add(String.format("%s = %s",field,QM));
        }
        query.append(commaJoiner.toString());
        return this;
    }

    public QueryBuilder limit(boolean withOffset){
        query.append(" LIMIT ").append(QM);
        if(withOffset){
            query.append(',').append(QM);
        }
        return this;
    }

    public QueryBuilder innerJoin(String joinedTable,String joinedAlias){
        query.append(" INNER JOIN ").append(joinedTable).append(' ').append(joinedAlias);
        return this;
    }

    public QueryBuilder outerJoin(boolean isLeft, String joinedTable, String joinedAlias){
        String direction = isLeft ? " LEFT JOIN" :" RIGHT JOIN";
        query.append(direction).append(' ').append(joinedTable).append(' ').append(joinedAlias);
        return this;
    }

    public QueryBuilder on(String condition){
        query.append(" ON ").append(condition);
        return this;
    }

    public QueryBuilder search(List<Condition> conditions){
        StringJoiner searchJoiner = new StringJoiner("AND");
        for(Condition con : conditions){
                searchJoiner.add(String.format("%s.%s%s", alias,con.toString(),QM));
        }
        query.append(searchJoiner);
        return this;
    }

    public QueryBuilder count(String name){
        query.append("SELECT COUNT(*) AS ");
        query.append(name);
        query.append(" FROM ");
        query.append(this.table);
        return this;
    }

    private final String table,alias;
    private final StringBuilder query;
    private static final String QM ="?";
    private static final String AND =" AND ";
    private static final String OR =" OR ";
}
