package org.mtgdb.db.repository;

import com.google.inject.Inject;
import org.mtgdb.db.IDatabaseConnection;
import org.mtgdb.db.ITransactionToken;
import org.mtgdb.model.Container;

import java.sql.SQLException;

/**
 * @author Sandro Orlando
 */
public final class ContainerRepository extends AbstractRepository<Container, Integer> implements IContainerRepository {

  @Inject
  public ContainerRepository(final IDatabaseConnection connection) {
    super(connection);
  }

  @Override
  public void save(final ITransactionToken transaction, final Container container) {
    try {
      dao.create(container);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected Class getClassLiteral() {
    return Container.class;
  }
}
