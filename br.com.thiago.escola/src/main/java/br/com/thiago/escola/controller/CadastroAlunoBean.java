package br.com.thiago.escola.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.thiago.escola.modelo.Aluno;
import br.com.thiago.escola.service.CadastroAlunoService;
import br.com.thiago.util.constantes.Constante;
import br.com.thiago.util.jsf.Navegacao;
import br.com.thiago.util.jsf.UtilMensagens;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class CadastroAlunoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Aluno aluno;

	@Inject
	private CadastroAlunoService cadastroAlunoService;

	@PostConstruct
	public void init() {
		limpar();
	}

	protected void limpar() {
		this.aluno = new Aluno();
	}

	public void inicializar() {
		if (this.aluno == null) {
			limpar();
		}
	}

	public String salvar() {
		try {
			cadastroAlunoService.salvar(this.aluno);
			UtilMensagens.informacao(Constante.SALVO_COM_SUCESSO);
			init();
			return Navegacao.redirecionar(Constante.CADASTRO+"Aluno");
		} catch (RuntimeException ne) {
			UtilMensagens.informacao(Constante.OCORREU_UM_ERRO_NA_APLICACAO);
		}
		return Navegacao.redirecionar(Constante.CADASTRO+"Aluno");
	}

}
