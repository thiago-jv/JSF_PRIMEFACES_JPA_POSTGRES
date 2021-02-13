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

import br.com.thiago.escola.filtro.FiltroAluno;
import br.com.thiago.escola.modelo.Aluno;
import br.com.thiago.escola.service.NegocioException;
import br.com.thiago.escola.util.jpa.Transactional;

public class Alunos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Aluno guardar(Aluno aluno) {
		return manager.merge(aluno);
	}

	public List<Aluno> lista() {
		return manager.createQuery("select distinct a from Aluno a order by a.codigo desc", Aluno.class)
				.getResultList();
	}

	public Aluno porId(Long codigo) {
		return this.manager.find(Aluno.class, codigo);
	}
	
	@Transactional
	public void remover(Aluno aluno) {
		try {
			aluno = porId(aluno.getCodigo());
			manager.remove(aluno);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Cliente não pode ser excluído.");
		}
	}


	@SuppressWarnings("unchecked")
	public List<Aluno> filtrados(FiltroAluno filtro) {
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

	public int quantidadeFiltrados(FiltroAluno filtro) {
		Criteria criteria = criteriaParaFiltros(filtro);
		criteria.setProjection(Projections.rowCount());
		return ((Number) criteria.uniqueResult()).intValue();
	}

	private Criteria criteriaParaFiltros(FiltroAluno filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Aluno.class);

		if (filtro.getNome() != null) {
			criteria.add(Restrictions.like("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		return criteria;
	}
}
