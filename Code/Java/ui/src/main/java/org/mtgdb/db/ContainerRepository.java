package org.mtgdb.db;

import org.mtgdb.db.sql.Column;
import org.mtgdb.db.sql.SQLGenerator;
import org.mtgdb.db.sql.Value;
import org.mtgdb.model.Container;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Sandro Orlando
 */
public final class ContainerRepository extends AbstractRepository implements IContainerRepository {

  private static final Column[] columns = new Column[] {
    new Column("NAME"),
    new Column("DESCRIPTION")
  };

  public ContainerRepository() {
  }

  @Override
  public void save(final ITransaction transaction, final Container container) {
    Value[][] row = new Value[][] {{new Value(container.getName()), new Value(container.getDescription())}};
    final String sql = SQLGenerator.insertInto("Container", columns, row);
    final ResultSet insert = transaction.insert(sql);
    try {
      while(insert.next()) {
        int id = insert.getInt(1);
        container.setId(id);
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not read id", e);
    }
  }
}
