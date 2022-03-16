package mbavellar.com.br.application;

import mbavellar.com.br.model.dao.BaseDao;
import mbavellar.com.br.model.dao.DaoFactory;
import mbavellar.com.br.model.dao.SellerDao;

public class Program {
  
  public static void main (String[] args) {
   
    SellerDao sellerDao = DaoFactory.createDao(SellerDao.class);
    System.out.println(sellerDao.findById(3));
  }
}
