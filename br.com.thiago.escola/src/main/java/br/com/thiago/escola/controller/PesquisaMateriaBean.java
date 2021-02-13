package br.com.thiago.escola.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.thiago.escola.filtro.FiltroMateria;
import br.com.thiago.escola.modelo.Materia;
import br.com.thiago.escola.repository.Materias;
import br.com.thiago.util.jsf.UtilMensagens;
import lombok.Getter;
import lombok.Setter;

@ViewScoped
@Named
public class PesquisaMateriaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Materias materias;
	
	@Getter
	@Setter
	private Materia materia;

	@Getter
	@Setter
	private FiltroMateria filtro;

	@Getter
	private LazyDataModel<Materia> model;

	public PesquisaMateriaBean() {
		filtro = new FiltroMateria();
		model = new LazyDataModel<Materia>() {
			private static final long serialVersionUID = 1L;

			public List<Materia> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				filtro.setPrimeiroRegistro(first);
				filtro.setQuantidadeRegistros(pageSize);
				filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				filtro.setPropriedadeOrdenacao(sortField);
				setRowCount(materias.quantidadeFiltrados(filtro));
				return materias.filtrados(filtro);
			}
		};
	}

	public int qtdRegistros() {
		return materias.quantidadeFiltrados(getFiltro());
	}

	public void excluir() {
		materias.remover(materia);
		UtilMensagens.informacao("Matéria " + materia.getDescricao() + " excluído com sucesso.");
	}

}
