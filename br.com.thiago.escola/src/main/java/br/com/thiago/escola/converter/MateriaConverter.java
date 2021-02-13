package br.com.thiago.escola.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.thiago.escola.modelo.Materia;
import br.com.thiago.escola.repository.Materias;
import br.com.thiago.escola.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Materia.class)
public class MateriaConverter implements Converter {

	private Materias materias;

	public MateriaConverter() {
		materias = CDIServiceLocator.getBean(Materias.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Materia retorno = null;
		if (value != null) {
			Long meteriaId = new Long(value);
			retorno = materias.porId(meteriaId);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Materia entity = (Materia) value;
			if (entity.getCodigo() == null) {
				return null;
			} else {
				return entity.getCodigo().toString();
			}
		}
		return null;
	}
}