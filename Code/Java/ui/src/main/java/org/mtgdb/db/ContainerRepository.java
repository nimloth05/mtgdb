package org.mtgdb.db;

import org.mtgdb.model.Container;
import org.mtgdb.util.Constants;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Sandro Orlando
 */
public final class ContainerRepository extends AbstractRepository implements IRepository {

  public ContainerRepository() {
  }

  public void save(final ITransaction transaction, final Container container) {
    StringBuilder builder = new StringBuilder();
    builder.append("insert into \"Container\" ")
      .append("(NAME, DESCRIPTION) values (").append("'").append(escape(container.getName())).append("'").append(Constants.COMMA + "'").append(escape(container.getDescription())).append("'")
      .append(Constants.RIGHT_PARENTHESIS);
    final ResultSet insert = transaction.insert(builder.toString());
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
