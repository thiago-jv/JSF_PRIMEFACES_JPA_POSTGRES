package br.com.thiago.escola.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.thiago.escola.filtro.FiltroLancamento;
import br.com.thiago.escola.modelo.Lancamento;
import br.com.thiago.escola.service.NegocioException;
import br.com.thiago.escola.util.jpa.Transactional;

public class Lancamentos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Lancamento guardar(Lancamento lancamento) {
		return manager.merge(lancamento);
	}

	public List<Lancamento> lista() {
		return manager.createQuery(
				"select distinct l from Lancamento l inner join fetch l.aluno a inner join fetch l.materia m inner join fetch l.professor p order by l.codigo desc",
				Lancamento.class).getResultList();
	}

	public List<Lancamento> porAluno(String nome) {
		return manager.createQuery(
				"select distinct c from Lancamento l inner join fetch l.aluno a where upper(a.nome) = :pNome",
				Lancamento.class).setParameter("pNome", nome.toUpperCase()).getResultList();
	}

	public Lancamento porId(Long codigo) {
		return this.manager.find(Lancamento.class, codigo);
	}

	@Transactional
	public void remover(Lancamento lancamento) {
		try {
			lancamento = porId(lancamento.getCodigo());
			manager.remove(lancamento);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Lançamento não pode ser excluído.");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Lancamento> filtrados(FiltroLancamento filtro) {
		Criteria criteria = criteriaParaFiltros(filtro);
		criteria.setFirstResult(filtro.getPrimeiroRegistro());
		criteria.setMaxResults(filtro.getQuantidadeRegistros());
		if (filtro.isAscendente() && filtro.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.asc(filtro.getPropriedadeOrdenacao()));
		} else if (filtro.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.desc(filtro.getPropriedadeOrdenacao()));
		}
		criteria.addOrder(Order.desc("codigo"));
		return criteria.list();
	}

	public int quantidadeFiltrados(FiltroLancamento filtro) {
		Criteria criteria = criteriaParaFiltros(filtro);
		criteria.setProjection(Projections.rowCount());
		return ((Number) criteria.uniqueResult()).intValue();
	}

	private Criteria criteriaParaFiltros(FiltroLancamento filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Lancamento.class);

		criteria.createAlias("aluno", "a");
		criteria.createAlias("professor", "p");
		criteria.createAlias("materia", "m");

		if (filtro.getAluno() != null) {
			criteria.add(Restrictions.like("a.nome", filtro.getAluno(), MatchMode.ANYWHERE));
		}

		if (filtro.getProfessor() != null) {
			criteria.add(Restrictions.like("p.nome", filtro.getProfessor(), MatchMode.ANYWHERE));
		}

		if (filtro.getMateria() != null) {
			criteria.add(Restrictions.like("m.descricao", filtro.getMateria(), MatchMode.ANYWHERE));
		}

		return criteria;
	}
}
