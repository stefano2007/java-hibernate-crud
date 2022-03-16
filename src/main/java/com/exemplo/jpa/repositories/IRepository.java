package com.exemplo.jpa.repositories;

import java.util.List;


public interface IRepository<T> {

	T criar(T obj);

	T atualizar(T obj);

	T buscarPorId(Integer id);
	
	List<T> buscarTodos();
	
	void deletar(Integer id); 
}
