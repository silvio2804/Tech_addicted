package Model.storage;

public abstract class TableQuery {

    public final String table;

    protected TableQuery(String table) {
        this.table = table;
    }
}
