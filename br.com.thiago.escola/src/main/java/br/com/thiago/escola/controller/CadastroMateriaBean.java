package br.com.thiago.escola.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.thiago.escola.modelo.Materia;
import br.com.thiago.escola.service.CadastroMateriaService;
import br.com.thiago.util.constantes.Constante;
import br.com.thiago.util.jsf.Navegacao;
import br.com.thiago.util.jsf.UtilMensagens;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class CadastroMateriaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Materia materia;

	@Inject
	private CadastroMateriaService cadastroMateriaService;

	@PostConstruct
	public void init() {
		limpar();
	}

	protected void limpar() {
		this.materia = new Materia();
	}

	public void inicializar() {
		if (this.materia == null) {
			limpar();
		}
	}

	public String salvar() {
		try {
			cadastroMateriaService.salvar(this.materia);
			UtilMensagens.informacao(Constante.SALVO_COM_SUCESSO);
			init();
			return Navegacao.redirecionar(Constante.CADASTRO + "Materia");
		} catch (RuntimeException ne) {
			UtilMensagens.advertencia(Constante.OCORREU_UM_ERRO_NA_APLICACAO);
		}
		return Navegacao.redirecionar(Constante.CADASTRO + "Materia");
	}

}
