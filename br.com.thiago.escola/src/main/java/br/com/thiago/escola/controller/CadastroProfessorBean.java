package br.com.thiago.escola.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.thiago.escola.modelo.Professor;
import br.com.thiago.escola.service.CadastroProfessorService;
import br.com.thiago.util.constantes.Constante;
import br.com.thiago.util.jsf.Navegacao;
import br.com.thiago.util.jsf.UtilMensagens;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class CadastroProfessorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Professor professor;

	@Inject
	private CadastroProfessorService cadastroProfessorService;

	@PostConstruct
	public void init() {
		limpar();
	}

	protected void limpar() {
		this.professor = new Professor();
	}

	public void inicializar() {
		if (this.professor == null) {
			limpar();
		}
	}

	public String salvar() {
		try {
			cadastroProfessorService.salvar(this.professor);
			UtilMensagens.informacao(Constante.SALVO_COM_SUCESSO);
			init();
			return Navegacao.redirecionar(Constante.CADASTRO+"Professor");
		} catch (RuntimeException ne) {
			UtilMensagens.advertencia(Constante.OCORREU_UM_ERRO_NA_APLICACAO);
		}
		return Navegacao.redirecionar(Constante.CADASTRO+"Professor");
	}

}
