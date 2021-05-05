package Model.storage;

import javax.sql.DataSource;

public abstract class Manager { //SQL DAO

    protected Manager(DataSource source) {
        this.source = source;
    }

    protected final DataSource source;
}
