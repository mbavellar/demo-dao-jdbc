package mbavellar.com.br.application;

import mbavellar.com.br.model.dao.DaoFactory;
import mbavellar.com.br.model.dao.DepartmentDao;
import mbavellar.com.br.model.dao.SqlDepartmentQuery;
import mbavellar.com.br.model.entities.Department;

import java.util.List;

public class Program2 {
  
  public static void main (String[] args) {
  
    System.out.println("Find All");
    DepartmentDao departmentDao = DaoFactory.createDao(DepartmentDao.class);
    departmentDao.findAll(null, null).forEach(System.out::println);
    System.out.println();
    System.out.println("Insert into");
    Department department = new Department(null, "Fashion");
    departmentDao.insert(department, null);
    departmentDao.findAll(null, null).forEach(System.out::println);
    System.out.println();
  
    System.out.println("Update");
    department.setName("Music");
    departmentDao.update(department, null);
    departmentDao.findAll(null, null).forEach(System.out::println);
    System.out.println();
  
    System.out.println("Find by ID");
    System.out.println(departmentDao.findById(1, null, null));
    System.out.println();
    System.out.println("Delete by ID");
    departmentDao.deleteById(8, SqlDepartmentQuery.DELETE_DEPARTMENT_BY_ID, null);
    departmentDao.findAll(null, null).forEach(System.out::println);
    System.out.println();
  }
}
