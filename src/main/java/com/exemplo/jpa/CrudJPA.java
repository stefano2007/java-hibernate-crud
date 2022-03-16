package com.exemplo.jpa;

import java.util.Arrays;
import java.util.List;

import com.exemplo.jpa.entities.Sexo;
import com.exemplo.jpa.entities.Usuario;
import com.exemplo.jpa.services.UsuarioService;

public class CrudJPA {
	
	public static void main(String[] args) {

		/*
		 * Criar database crudjpa banco de dados Mysql ou modifica o arquivo de configuração
		 * Arquivo de configuração > scr/main/resources/persistence.xml
		 * */		
		
		UsuarioService service = new UsuarioService();
		
		// Criar Usuario
		Usuario usuarioCreate = new Usuario();
		usuarioCreate.setNome("João da Silva");
		usuarioCreate.setCPF("11111111111");
		usuarioCreate.setEndereco("Rua 1 n° 2");
		usuarioCreate.setSexo(Sexo.MASCULINO);

		usuarioCreate = service.criar(usuarioCreate);
		
		System.out.println("*** Criar Usuario ***");
		System.out.println(usuarioCreate.toString());

		// buscar por Id
		Usuario usuarioFindById = service.buscarPorId(usuarioCreate.getId());

		System.out.println(String.format("*** Buscar Usuario por Id %s ***", usuarioCreate.getId()));
		System.out.println(usuarioFindById.toString());

		// buscar por CPF
		Usuario usuarioFindByCPF = service.buscarPorCPF(usuarioCreate.getCPF());

		System.out.println(String.format("*** Buscar Usuario por CPF %s ***", usuarioCreate.getCPF()));
		System.out.println(usuarioFindByCPF.toString());

		// Alterar usuario

		Usuario usuarioUpdate = service.buscarPorId(usuarioCreate.getId());

		String nomeAntigo = usuarioUpdate.getNome();
		
		usuarioUpdate.setNome("João Almeida");
		usuarioUpdate = service.atualizar(usuarioUpdate);

		System.out.println(String.format("*** Alterar nome Usuario %s para %s ***", nomeAntigo , usuarioCreate.getNome()));
		System.out.println(usuarioUpdate.toString());

		// listar todos
		System.out.println("Listar todos");
		listarTodos(service.buscarTodos());
		
		System.out.println(String.format("*** Deletar Usuario por Id %s ***",  usuarioCreate.getId()));
		service.deletar(usuarioCreate.getId());		

		System.out.println("Listar todos");
		listarTodos(service.buscarTodos());
	}

	private static void listarTodos(List<Usuario> usuarios) {
		usuarios.stream().forEach(u -> {
			System.out.println(u.toString());
		});
	}
}
