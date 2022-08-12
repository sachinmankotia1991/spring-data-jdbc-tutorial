package com.sachin.demo.repository;

import java.util.List;
import java.util.Optional;

import com.sachin.demo.entity.CourseChangeBO;

public interface DAO<T> {
	List<T> list();

	void create(T t);

	Optional<T> get(int id);

	void delete(int id);

	void update(CourseChangeBO t, int id);

}
