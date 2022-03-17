package mbavellar.com.br.model.dao;

import db.DB;
import db.DBException;
import mbavellar.com.br.model.entities.Department;
import mbavellar.com.br.model.entities.Seller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDao extends BaseDao<Seller> {
  
  @Override
  public void insert(Seller obj) {
  
  }
  
  @Override
  public void update(Seller obj) {
  
  }
  
  @Override
  public void deleteById(Integer id) {
  
  }
  
  @Override
  public Seller findById(Integer id) {
  
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      preparedStatement = conn.prepareStatement(
        SQLQueryHelper.FIND_SELLER_BY_ID);
      
      preparedStatement.setInt(ParameterIndex.ONE, id);
      
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        Department department = instantiateDepartment(resultSet);
        Seller seller = instantiateSeller(resultSet, department);
        return seller;
      }
      return null;
      
    } catch (SQLException sqle) {
      throw new DBException(sqle.getMessage());
    } finally {
      DB.closeStatement(preparedStatement);
      DB.closeResultSet(resultSet);
    }
  }
  
  @Override
  public List<Seller> findAll() {
    return null;
  }
  
  private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException{
    return new Seller(
      rs.getInt("Id"),
      rs.getString("Name"),
      rs.getString("Email"),
      rs.getDate("BirthDate"),
      rs.getDouble("BaseSalary"),
      dep);
  }
  
  private Department instantiateDepartment(final ResultSet rs) throws SQLException {
    return new Department(
      rs.getInt("DepartmentId"),
      rs.getString("Department"));
  }
}
