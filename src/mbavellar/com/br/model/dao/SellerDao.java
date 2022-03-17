package mbavellar.com.br.model.dao;

import db.DB;
import db.DBException;
import mbavellar.com.br.model.entities.Department;
import mbavellar.com.br.model.entities.Seller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDao extends BaseDao<Seller> {
  
  Map<Integer, Department> departmentIds = new HashMap<>();
  
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
      preparedStatement = conn.prepareStatement(SQLQueryHelper.FIND_SELLER_BY_ID);
      
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
  
  @Override
  public List<Seller> findByDepartmentId(final Integer departmentId) {
  
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      preparedStatement = conn.prepareStatement(SQLQueryHelper.FIND_SELLERS_BY_DEPARTMENT_ID);
      preparedStatement.setInt(ParameterIndex.ONE, departmentId);
      resultSet = preparedStatement.executeQuery();
      
      List<Seller> sellers = new ArrayList<>();
      while (resultSet.next()) {
        Department department = getDepartment(resultSet);
        Seller seller = instantiateSeller(resultSet, department);
        sellers.add(seller);
      }
      return sellers;
    } catch (SQLException sqle) {
      throw new DBException(sqle.getMessage());
    } finally {
      DB.closeStatement(preparedStatement);
      DB.closeResultSet(resultSet);
    }
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
  
  private Department getDepartment(final ResultSet rs) throws SQLException {
    int departmentId = rs.getInt("DepartmentId");
    Department dep = departmentIds.get(departmentId);
    if (dep == null) {
      dep = instantiateDepartment(rs);
      departmentIds.put(departmentId, dep);
    }
    return dep;
    
  }
}
