package br.com.thiago.escola.service;

import java.io.Serializable;

import javax.inject.Inject;
import br.com.thiago.escola.modelo.Lancamento;
import br.com.thiago.escola.repository.Lancamentos;
import br.com.thiago.escola.util.jpa.Transactional;

public class CadastroLancamentoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Lancamentos lancamentos;

	@Transactional
	public Lancamento salvar(Lancamento lancamento) {
		lancamento = lancamentos.guardar(lancamento);
		return lancamento;
	}
}
