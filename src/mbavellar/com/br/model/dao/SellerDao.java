package mbavellar.com.br.model.dao;

import db.DB;
import db.DBException;
import mbavellar.com.br.model.entities.Department;
import mbavellar.com.br.model.entities.Seller;

import java.sql.Connection;
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
        "SELECT seller.*, department.Name AS Department " +
            "FROM seller INNER JOIN department " +
            "ON seller.DepartmentId = Department.Id " +
            "WHERE seller.Id = ?");
      preparedStatement.setInt(1, id);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        Department department = new Department(
          resultSet.getInt("DepartmentId"),
          resultSet.getString("Department")
        );
        Seller seller = new Seller(
          resultSet.getInt("Id"),
          resultSet.getString("Name"),
          resultSet.getString("Email"),
          resultSet.getDate("BirthDate"),
          resultSet.getDouble("BaseSalary"),
          department
        );
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
}
