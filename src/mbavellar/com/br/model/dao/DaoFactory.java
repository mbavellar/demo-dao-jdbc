package mbavellar.com.br.model.dao;

import db.DB;

import java.lang.reflect.InvocationTargetException;

public class DaoFactory {
  
  public static <D> D createDao(Class<D> daoClass) {
    try {
      return daoClass.getDeclaredConstructor().newInstance();
    } catch (InstantiationException | IllegalAccessException |
      NoSuchMethodException | InvocationTargetException e) {
      e.printStackTrace();
    }
    return null;
  }
}
