package mbavellar.com.br.model.dao;

import db.DB;
import java.sql.Connection;

public abstract class BaseDao<T> implements Dao<T> {
  protected Connection conn;
  public BaseDao() {
    this.conn = DB.getConnection();
  }
}
