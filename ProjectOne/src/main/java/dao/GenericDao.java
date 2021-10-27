package dao;

import java.util.List;

public interface GenericDao <T> {
	List<T> getAll();
	T getByName(String name);
	void update(T entity);
	void insert(T entity);
	void delete(T entity);

}
