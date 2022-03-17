package mbavellar.com.br.model.dao;

public class SQLQueryHelper {
  public static final String FIND_SELLER_BY_ID =
    "SELECT seller.*, department.Name AS Department " +
    "FROM seller INNER JOIN department " +
    "ON seller.DepartmentId = Department.Id " +
    "WHERE seller.Id = ?";
  
  public static final String FIND_SELLERS_BY_DEPARTMENT_ID =
    "SELECT seller.* , department.Name AS Department " +
    "FROM seller INNER JOIN department " +
    "ON seller.DepartmentId = department.Id " +
    "WHERE DepartmentId = ? ORDER BY Name";
  
  public static final String FIND_ALL_SELLERS =
    "SELECT seller.* , department.Name AS Department " +
    "FROM seller INNER JOIN department " +
    "ON seller.DepartmentId = department.Id " +
    "ORDER BY Name";
  
  public static final String INSERT =
    "INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) " +
    "VALUES (?, ?, ?, ?, ?);";
  
  public static final String UPDATE =
     "UPDATE seller SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? " +
     "WHERE Id = ?";
  
  private SQLQueryHelper() {}
}
