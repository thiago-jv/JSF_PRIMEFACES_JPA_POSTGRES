package br.com.thiago.escola.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.thiago.escola.modelo.Aluno;
import br.com.thiago.escola.repository.Alunos;
import br.com.thiago.escola.util.jpa.Transactional;

public class CadastroAlunoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Alunos alunos;

	@Transactional
	public Aluno salvar(Aluno aluno) {
		aluno = this.alunos.guardar(aluno);
		return aluno;
	}
}
