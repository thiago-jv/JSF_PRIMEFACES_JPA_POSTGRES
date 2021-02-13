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

import br.com.thiago.escola.filtro.FiltroProfessor;
import br.com.thiago.escola.modelo.Professor;
import br.com.thiago.escola.service.NegocioException;
import br.com.thiago.escola.util.jpa.Transactional;

public class Professores implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Professor guardar(Professor professor) {
		return manager.merge(professor);
	}

	public List<Professor> lista() {
		return manager.createQuery("select distinct p from Professor p order by p.codigo desc", Professor.class)
				.getResultList();
	}

	public Professor porId(Long codigo) {
		return this.manager.find(Professor.class, codigo);
	}

	@Transactional
	public void remover(Professor professor) {
		try {
			professor = porId(professor.getCodigo());
			manager.remove(professor);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Professor não pode ser excluído.");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Professor> filtrados(FiltroProfessor filtro) {
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

	public int quantidadeFiltrados(FiltroProfessor filtro) {
		Criteria criteria = criteriaParaFiltros(filtro);
		criteria.setProjection(Projections.rowCount());
		return ((Number) criteria.uniqueResult()).intValue();
	}

	private Criteria criteriaParaFiltros(FiltroProfessor filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Professor.class);

		if (filtro.getNome() != null) {
			criteria.add(Restrictions.like("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		return criteria;
	}
}
