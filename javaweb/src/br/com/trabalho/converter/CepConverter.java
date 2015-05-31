package br.com.trabalho.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.trabalho.model.Cep;

@FacesConverter("converters.CepConverter")
public class CepConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.equals("")) {
			String cepTxt = value.replaceAll("\\-", "");
			String regiao = cepTxt.substring(0, 5);
			String sufixo = cepTxt.substring(5, cepTxt.length());
			try {
				// Testa se somente existem numeros.
				Long.valueOf(regiao);
				Long.valueOf(sufixo);
				Cep cep = new Cep(regiao, sufixo);
				return cep;
			} catch (NumberFormatException e) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Erro de conversão",
						"O valor informado não é um número de CEP válido!");
				throw new ConverterException(message);
			}
		}
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Cep cep = (Cep) value;
			String cepTxt = cep.getRegiao() + "-" + cep.getSufixo();
			return cepTxt;
		} else
			return null;
	}
}
