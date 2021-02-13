package br.com.thiago.escola.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.thiago.escola.modelo.Materia;
import br.com.thiago.escola.repository.Materias;
import br.com.thiago.escola.util.jpa.Transactional;

public class CadastroMateriaService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Materias materias;

	@Transactional
	public Materia salvar(Materia materia) {
		materia = this.materias.guardar(materia);
		return materia;
	}
}
