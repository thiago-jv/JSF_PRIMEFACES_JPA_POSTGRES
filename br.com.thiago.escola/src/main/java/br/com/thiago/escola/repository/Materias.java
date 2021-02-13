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

import br.com.thiago.escola.filtro.FiltroMateria;
import br.com.thiago.escola.modelo.Materia;
import br.com.thiago.escola.service.NegocioException;
import br.com.thiago.escola.util.jpa.Transactional;

public class Materias implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Materia guardar(Materia materia) {
		return manager.merge(materia);
	}

	public List<Materia> lista() {
		return manager.createQuery("select distinct m from Materia m order by m.codigo desc", Materia.class)
				.getResultList();
	}

	public Materia porId(Long codigo) {
		return this.manager.find(Materia.class, codigo);
	}

	@Transactional
	public void remover(Materia materia) {
		try {
			materia = porId(materia.getCodigo());
			manager.remove(materia);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Matéria não pode ser excluída.");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Materia> filtrados(FiltroMateria filtro) {
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

	public int quantidadeFiltrados(FiltroMateria filtro) {
		Criteria criteria = criteriaParaFiltros(filtro);
		criteria.setProjection(Projections.rowCount());
		return ((Number) criteria.uniqueResult()).intValue();
	}

	private Criteria criteriaParaFiltros(FiltroMateria filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Materia.class);

		if (filtro.getDescricao() != null) {
			criteria.add(Restrictions.like("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
		}
		return criteria;
	}

}
