package br.pucminas.autenticacao.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class Usuario	  {

	private Long id;
	private String nome;
	private String cpf;
	private String login;
	private Token token;
	private String senha;
	
	private List<Permissao> permissaoList;
	
	private List<Grupo> grupoList;
	
}
