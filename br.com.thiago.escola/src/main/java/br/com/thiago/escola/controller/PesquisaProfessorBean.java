package br.com.thiago.escola.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.thiago.escola.filtro.FiltroProfessor;
import br.com.thiago.escola.modelo.Professor;
import br.com.thiago.escola.repository.Professores;
import br.com.thiago.util.jsf.UtilMensagens;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class PesquisaProfessorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Professores professores;

	@Getter
	@Setter
	private Professor professor;

	@Getter
	@Setter
	private FiltroProfessor filtro;

	@Getter
	private LazyDataModel<Professor> model;

	public PesquisaProfessorBean() {
		filtro = new FiltroProfessor();
		model = new LazyDataModel<Professor>() {
			private static final long serialVersionUID = 1L;

			public List<Professor> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				filtro.setPrimeiroRegistro(first);
				filtro.setQuantidadeRegistros(pageSize);
				filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				filtro.setPropriedadeOrdenacao(sortField);
				setRowCount(professores.quantidadeFiltrados(filtro));
				return professores.filtrados(filtro);
			}
		};
	}

	public int qtdRegistros() {
		return professores.quantidadeFiltrados(getFiltro());
	}

	public void excluir() {
		professores.remover(professor);
		UtilMensagens.informacao("Professor " + professor.getNome() + " excluído com sucesso.");
	}

}
