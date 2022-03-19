package mbavellar.com.br.model.dao;

import db.DB;
import db.DBException;
import mbavellar.com.br.model.entities.Department;
import mbavellar.com.br.model.enums.DEPARTMENT_COLUMNS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseDao<T> implements Dao<T> {
  protected Connection conn;
  public BaseDao() {
    this.conn = DB.getConnection();
  }
  
  public void deleteById(final Integer id, final String query, PreparedStatement preSt) {
    try {
      preSt = conn.prepareStatement(query);
      preSt.setInt(ParameterIndex.ONE, id);
      preSt.executeUpdate();
    }
    catch (SQLException sqle) {
      throw new DBException(sqle.getMessage());
    }
    finally {
      DB.closeStatement(preSt);
    }
  }
}
