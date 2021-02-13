package br.com.thiago.escola.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.thiago.escola.filtro.FiltroAluno;
import br.com.thiago.escola.modelo.Aluno;
import br.com.thiago.escola.repository.Alunos;
import br.com.thiago.util.jsf.UtilMensagens;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class PesquisaAlunoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Inject
	private Alunos alunos;

	@Getter
	@Setter
	private Aluno aluno;

	@Getter
	@Setter
	private FiltroAluno filtro;

	@Getter
	private LazyDataModel<Aluno> model;

	public PesquisaAlunoBean() {
		filtro = new FiltroAluno();
		model = new LazyDataModel<Aluno>() {
			private static final long serialVersionUID = 1L;

			public List<Aluno> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				filtro.setPrimeiroRegistro(first);
				filtro.setQuantidadeRegistros(pageSize);
				filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				filtro.setPropriedadeOrdenacao(sortField);
				setRowCount(alunos.quantidadeFiltrados(filtro));
				return alunos.filtrados(filtro);
			}
		};
	}

	public int qtdRegistros() {
		return alunos.quantidadeFiltrados(getFiltro());
	}

	public void excluir() {
		alunos.remover(aluno);
		UtilMensagens.informacao("Aluno " + aluno.getNome() + " excluído com sucesso.");
	}

}
