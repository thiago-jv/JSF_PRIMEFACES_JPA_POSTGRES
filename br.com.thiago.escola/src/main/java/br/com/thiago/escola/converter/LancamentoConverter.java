package br.com.thiago.escola.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.thiago.escola.modelo.Lancamento;
import br.com.thiago.escola.repository.Lancamentos;
import br.com.thiago.escola.util.cdi.CDIServiceLocator;


@FacesConverter(forClass = Lancamento.class)
public class LancamentoConverter implements Converter {

	private Lancamentos lancamentos;

	public LancamentoConverter() {
		lancamentos = CDIServiceLocator.getBean(Lancamentos.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Lancamento retorno = null;
		if (value != null) {
			Long lancamentoId = new Long(value);
			retorno = lancamentos.porId(lancamentoId);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Lancamento entity = (Lancamento) value;
			if (entity.getCodigo() == null) {
				return null;
			} else {
				return entity.getCodigo().toString();
			}
		}
		return null;
	}

}


