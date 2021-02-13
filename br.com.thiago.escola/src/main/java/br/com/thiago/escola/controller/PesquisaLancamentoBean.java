package br.com.thiago.escola.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.thiago.escola.filtro.FiltroLancamento;
import br.com.thiago.escola.modelo.Lancamento;
import br.com.thiago.escola.repository.Lancamentos;
import br.com.thiago.util.jsf.UtilMensagens;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class PesquisaLancamentoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Lancamentos lancamentos;

	@Getter
	@Setter
	private Lancamento lancamento;

	@Getter
	@Setter
	private FiltroLancamento filtro;

	@Getter
	private LazyDataModel<Lancamento> model;

	public PesquisaLancamentoBean() {
		filtro = new FiltroLancamento();
		model = new LazyDataModel<Lancamento>() {
			private static final long serialVersionUID = 1L;

			public List<Lancamento> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				filtro.setPrimeiroRegistro(first);
				filtro.setQuantidadeRegistros(pageSize);
				filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				filtro.setPropriedadeOrdenacao(sortField);
				setRowCount(lancamentos.quantidadeFiltrados(filtro));
				return lancamentos.filtrados(filtro);
			}
		};
	}

	public int qtdRegistros() {
		return lancamentos.quantidadeFiltrados(getFiltro());
	}

	public void excluir() {
		lancamentos.remover(lancamento);
		UtilMensagens.informacao("Lançamento " + lancamento.getCodigo() + " excluído com sucesso.");
	}
}
