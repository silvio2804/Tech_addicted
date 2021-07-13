package Model.storage;

import javax.sql.DataSource;
import java.sql.SQLException;

public abstract class Manager{

    public Manager(DataSource source) throws SQLException {
        this.source = ConPool.getDatasource();
    }
    public final DataSource source;
}
