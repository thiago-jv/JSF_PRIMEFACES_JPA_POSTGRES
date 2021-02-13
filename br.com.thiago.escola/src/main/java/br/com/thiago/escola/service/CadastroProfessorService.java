package br.com.thiago.escola.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.thiago.escola.modelo.Professor;
import br.com.thiago.escola.repository.Professores;
import br.com.thiago.escola.util.jpa.Transactional;

public class CadastroProfessorService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Professores professores;

	@Transactional
	public Professor salvar(Professor professor) {
		professor = this.professores.guardar(professor);
		return professor;
	}

}
