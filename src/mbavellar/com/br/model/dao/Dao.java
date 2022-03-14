package mbavellar.com.br.model.dao;

import mbavellar.com.br.model.entities.Department;

import java.util.List;

public interface Dao<T> {
  void insert(T obj);
  void update(T obj);
  void deleteById(Integer id);
  T findById(Integer id);
  List<T> findAll();
}
