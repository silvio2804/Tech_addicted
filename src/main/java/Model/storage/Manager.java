package Model.storage;

import javax.sql.DataSource;
import java.sql.SQLException;

public abstract class Manager{ //SQL DAO

    protected Manager(DataSource source) throws SQLException {
        this.source = ConPool.getDatasource();
    }
    protected final DataSource source;
}
