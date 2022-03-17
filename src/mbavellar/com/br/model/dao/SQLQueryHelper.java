package mbavellar.com.br.model.dao;

public class SQLQueryHelper {
  public static final String FIND_SELLER_BY_ID =
    "SELECT seller.*, department.Name AS Department " +
    "FROM seller INNER JOIN department " +
    "ON seller.DepartmentId = Department.Id " +
    "WHERE seller.Id = ?";
}
