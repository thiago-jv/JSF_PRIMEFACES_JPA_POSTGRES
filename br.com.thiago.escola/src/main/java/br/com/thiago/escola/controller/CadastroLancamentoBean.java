package br.com.thiago.escola.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.thiago.escola.modelo.Aluno;
import br.com.thiago.escola.modelo.Lancamento;
import br.com.thiago.escola.modelo.Materia;
import br.com.thiago.escola.modelo.Professor;
import br.com.thiago.escola.repository.Alunos;
import br.com.thiago.escola.repository.Materias;
import br.com.thiago.escola.repository.Professores;
import br.com.thiago.escola.service.CadastroLancamentoService;
import br.com.thiago.util.constantes.Constante;
import br.com.thiago.util.jsf.Navegacao;
import br.com.thiago.util.jsf.UtilMensagens;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class CadastroLancamentoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Lancamento lancamento;

	@Inject
	private CadastroLancamentoService cadastroLancamentoService;

	@Getter
	@Setter
	private Materia materia;

	@Inject
	private Materias materias;

	@Getter
	private List<Materia> listaMaterias;

	@Getter
	@Setter
	private Aluno aluno;

	@Inject
	private Alunos alunos;

	@Getter
	@Setter
	private List<Aluno> listaALunos;

	@Getter
	@Setter
	private Professor professor;

	@Inject
	private Professores professores;

	@Getter
	@Setter
	private List<Professor> listaProfessores;

	@PostConstruct
	public void init() {
		limpar();
	}

	protected void limpar() {
		this.lancamento = new Lancamento();
		this.aluno = new Aluno();
		this.materia = new Materia();
		this.professor = new Professor();
	}

	public void inicializar() {
		if (this.lancamento == null) {
			limpar();
		}
		listaALunos = alunos.lista();
		listaMaterias = materias.lista();
		listaProfessores = professores.lista();
	}

	public String salvar(){
		try {	
			lancamento.calculaMedia();
			this.lancamento = cadastroLancamentoService.salvar(lancamento);
			UtilMensagens.informacao(Constante.SALVO_COM_SUCESSO);
			init();
			return Navegacao.redirecionar(Constante.CADASTRO + "Lancamento");
		} catch (Exception ne) {
			UtilMensagens.advertencia(Constante.OCORREU_UM_ERRO_NA_APLICACAO);
		}
		return Navegacao.redirecionar(Constante.CADASTRO + "Lancamento");
	}

}
