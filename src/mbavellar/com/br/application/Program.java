package mbavellar.com.br.application;

import mbavellar.com.br.model.dao.DaoFactory;
import mbavellar.com.br.model.dao.SellerDao;
import mbavellar.com.br.model.entities.Department;
import mbavellar.com.br.model.entities.Seller;

import java.util.Date;
import java.util.List;

public class Program {
  
  public static void main (String[] args) {
  
    SellerDao sellerDao = DaoFactory.createDao(SellerDao.class);
    
    System.out.println("==== Test 1: Seller findById ====");
    System.out.println(sellerDao.findById(3));
  
    System.out.println("\n==== Test 2 : Seller findByDepartmentId ====");
    List<Seller> sellers = sellerDao.findByDepartmentId(2);
    sellers.forEach(System.out::println);
  
    System.out.println("\n==== Test 3 : Seller findAll ====");
    sellers = sellerDao.findAll();
    sellers.forEach(System.out::println);
  
    System.out.println("\n==== Test 4 : Insert New Seller ====");
    Seller seller = new Seller(
      10, "Greg", "greg@gmail.com", new Date(), 4000.0,
      new Department(1, "Computers"));
    //sellerDao.insert(seller);
    //System.out.println(seller.getId());
  
    System.out.println("\n==== Test 5 : Update Seller ====");
      seller.setDepartment(new Department(2, "Electronics"));
    sellerDao.update(seller);
    System.out.println(seller.getId());
    
  }
}
