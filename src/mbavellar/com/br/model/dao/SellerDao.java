package mbavellar.com.br.model.dao;

import db.DB;
import db.DBException;
import mbavellar.com.br.model.enums.DEPARTMENT_COLUMNS;
import mbavellar.com.br.model.enums.SELLER_COLUMNS;
import mbavellar.com.br.model.entities.Department;
import mbavellar.com.br.model.entities.Seller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDao extends BaseDao<Seller> {
  
  Map<Integer, Department> departmentIds = new HashMap<>();
  
  @Override
  public void insert(Seller obj) {
    executeUpdate(obj, SQLQueryHelper.INSERT);
  }
  
  @Override
  public void update(Seller obj) {
    executeUpdate(obj, SQLQueryHelper.UPDATE);
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
    return getSellers(null, SQLQueryHelper.FIND_ALL_SELLERS);
  }
  
  @Override
  public List<Seller> findByDepartmentId(final Integer departmentId) {
    return getSellers(departmentId, SQLQueryHelper.FIND_SELLERS_BY_DEPARTMENT_ID);
  }
  
  private Seller instantiateSeller(ResultSet rs, Department department) throws SQLException{
    return new Seller(
      rs.getInt(SELLER_COLUMNS.Id.name()),
      rs.getString(SELLER_COLUMNS.Name.name()),
      rs.getString(SELLER_COLUMNS.Email.name()),
      rs.getDate(SELLER_COLUMNS.BirthDate.name()),
      rs.getDouble(SELLER_COLUMNS.BaseSalary.name()),
      department);
  }
  
  private Department instantiateDepartment(final ResultSet rs) throws SQLException {
    return new Department(
      rs.getInt(DEPARTMENT_COLUMNS.DepartmentId.name()),
      rs.getString(DEPARTMENT_COLUMNS.Department.name()));
  }
  
  private Department getDepartment(final ResultSet rs) throws SQLException {
    int departmentId = rs.getInt(DEPARTMENT_COLUMNS.DepartmentId.name());
    Department dep = departmentIds.get(departmentId);
    if (dep == null) {
      dep = instantiateDepartment(rs);
      departmentIds.put(departmentId, dep);
    }
    return dep;
  }
  
  private void executeUpdate(Seller obj, final String query) {
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(ParameterIndex.ONE, obj.getName());
      preparedStatement.setString(ParameterIndex.TWO, obj.getEmail());
      preparedStatement.setDate(ParameterIndex.THREE, new java.sql.Date(obj.getBirthDate().getTime()));
      preparedStatement.setDouble(ParameterIndex.FOUR, obj.getBaseSalary());
      preparedStatement.setInt(ParameterIndex.FIVE, obj.getDepartment().getId());
      if (obj.getId() != null) {
        preparedStatement.setInt(ParameterIndex.SIX, obj.getId());
        preparedStatement.executeUpdate();
        return;
      }
      if (preparedStatement.executeUpdate() > 0) {
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
          obj.setId(resultSet.getInt(1));
        }
        DB.closeResultSet(resultSet);
      }
      else {
          throw new DBException("Unexpected Error! No Rows Affected!");
      }
    }
    catch (SQLException sqle) {
      throw new DBException(sqle.getMessage());
    }
    finally {
      DB.closeStatement(preparedStatement);
    }
  }
  
  private final List<Seller> getSellers (final Integer departmentId, final String query) {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      preparedStatement = conn.prepareStatement(query);
      
      if (departmentId != null)
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
}