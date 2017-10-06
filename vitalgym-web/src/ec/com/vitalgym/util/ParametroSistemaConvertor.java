package ec.com.vitalgym.util;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ec.com.vitalgym.local.RhParametroSistemaLocal;
import ec.com.vitalgym.modelo.RhParametroSistema;


/**
 * 
 *
 */
@ManagedBean
public class ParametroSistemaConvertor implements Converter {

	@EJB
	private RhParametroSistemaLocal parametroSistemaLocal;
	

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		int id = new Integer(value);
		return parametroSistemaLocal.buscarId(id);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}
		Integer id = ((RhParametroSistema) value).getPrId();
		return (id == null) ? null : id.toString();
	}

}