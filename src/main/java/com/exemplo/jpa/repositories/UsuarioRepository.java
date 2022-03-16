package com.exemplo.jpa.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.exemplo.jpa.entities.Usuario;

public class UsuarioRepository implements IRepository<Usuario> {

	EntityManagerFactory factory;
	EntityManager manager;

	public UsuarioRepository() {
		factory = Persistence.createEntityManagerFactory("exemplo-jpa");
		manager = factory.createEntityManager();
	}

	public UsuarioRepository(EntityManagerFactory factory, EntityManager manager) {
		this.factory = factory;
		this.manager = manager;
	}
	
	private String SelectUsuario = "SELECT id, nome, endereco, cpf, sexo from usuario";

	public Usuario criar(Usuario user) {

		user.setId(null);

		manager.getTransaction().begin();
		manager.persist(user);
		manager.getTransaction().commit();

		return user;
	}

	public Usuario atualizar(Usuario user) {

		if (user.getId() > 0) {
			manager.getTransaction().begin();
			manager.persist(user);
			manager.getTransaction().commit();
		}

		return user;
	}

	public Usuario buscarPorId(Integer userId) {

		Usuario user = manager.find(Usuario.class, userId);

		return user;
	}

	public List<Usuario> buscarTodos() {

		List<Usuario> users = new ArrayList<Usuario>();
		
		try {
		    users = (List<Usuario>) manager
           		    .createNativeQuery(SelectUsuario, Usuario.class)
				    .getResultList();
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
		}
		return users;
	}

	public void deletar(Integer userId) {
		Usuario usuario = buscarPorId(userId);

		if (usuario != null) {
			manager.getTransaction().begin();
			manager.remove(usuario);
			manager.getTransaction().commit();
		}
	}
	
	
	public Usuario buscarPorCPF(String cpf) {
		
		Usuario user = new Usuario();
		
		try {
		    user = (Usuario) manager
           		    .createNativeQuery(SelectUsuario + " where cpf = :cpf order by id limit 1", Usuario.class)
           		    .setParameter("cpf", cpf)
				    .getSingleResult();		    
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
		}
		return user;
	}

	public void close() {
		manager.close();
		factory.close();
	}

}
