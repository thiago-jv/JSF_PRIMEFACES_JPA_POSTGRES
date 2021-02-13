package br.com.thiago.escola.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.thiago.escola.modelo.Aluno;
import br.com.thiago.escola.repository.Alunos;
import br.com.thiago.escola.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Aluno.class)
public class AlunoConverter implements Converter {

	private Alunos alunos;

	public AlunoConverter() {
		alunos = CDIServiceLocator.getBean(Alunos.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Aluno retorno = null;
		if (value != null) {
			Long alunoId = new Long(value);
			retorno = alunos.porId(alunoId);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Aluno entity = (Aluno) value;
			if (entity.getCodigo() == null) {
				return null;
			} else {
				return entity.getCodigo().toString();
			}
		}
		return null;
	}

}
