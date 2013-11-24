package org.mtgdb.db.repository;

import org.mtgdb.db.DBConstants;
import org.mtgdb.db.DatabaseConnection;
import org.mtgdb.model.Edition;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Sandro Orlando
 */
public final class EditionRepository extends AbstractRepository<Edition, String> implements IEditionRepository {

  @Inject
  public EditionRepository(final DatabaseConnection connection) {
    super(connection);
  }

  @Override
  public void save(final Edition edition) {
    try {
      dao.create(edition);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void setId(final Edition obj) {
    if (obj.getId() == null) throw new RuntimeException("id is not set for edition " + obj.getName());
  }

  @Override
  public void deleteAll() {
    try {
      dao.executeRaw("truncate table \"" + DBConstants.EDITION_TABLE + "\"");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void delete(final Edition edition) {
    try {
      dao.delete(edition);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void gatherEditionInformation(final Map<String, Integer> editionToNumberOfCards) {
    for (Edition edition : getAll()) {
      editionToNumberOfCards.put(edition.getId(), edition.getNumberOfCards());
    }
  }

  @Override
  protected Class<Edition> getClassLiteral() {
    return Edition.class;
  }
}
