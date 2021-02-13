package br.com.thiago.escola.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.thiago.escola.modelo.Professor;
import br.com.thiago.escola.repository.Professores;
import br.com.thiago.escola.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Professor.class)
public class ProfessorConverter implements Converter {

	private Professores professores;

	public ProfessorConverter() {
		professores = CDIServiceLocator.getBean(Professores.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Professor retorno = null;
		if (value != null) {
			Long professorId = new Long(value);
			retorno = professores.porId(professorId);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Professor entity = (Professor) value;
			if (entity.getCodigo() == null) {
				return null;
			} else {
				return entity.getCodigo().toString();
			}
		}
		return null;
	}
}
