package com.exemplo.jpa.services;

import java.util.List;

import com.exemplo.jpa.entities.Usuario;
import com.exemplo.jpa.repositories.UsuarioRepository;

public class UsuarioService{
 
	UsuarioRepository repo;
	
	public UsuarioService() {
		repo = new UsuarioRepository();
	}
	
	public Usuario criar(Usuario user) {
		return repo.criar(user);
	}
	
	public Usuario atualizar(Usuario user) {
		return repo.atualizar(user);
	}
	
	public Usuario buscarPorId(Integer userId) {
		return repo.buscarPorId(userId);
	}
	
	public List<Usuario> buscarTodos() {
		return repo.buscarTodos();
	}
	
	public void deletar(Integer userId) {
	    repo.deletar(userId);
	}

	public Usuario buscarPorCPF(String cpf) {
		return repo.buscarPorCPF(cpf);
	}
}
