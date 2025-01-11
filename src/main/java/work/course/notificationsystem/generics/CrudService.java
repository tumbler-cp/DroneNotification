package work.course.notificationsystem.generics;

import java.util.List;

public interface CrudService<T> {
  T create(T entity);
  T findById(Long id);
  List<T> findAll();
  T update(T entity);
  void delete(T entity);
}
