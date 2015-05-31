package br.com.trabalho.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("converters.CpfConverter")
public class CpfConverter implements Converter {
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) throws ConverterException {
		if (value != null && !value.equals("")) {
			String cpf = value.replaceAll("\\.", "").replaceAll("\\-", "");
			try {
				// Testa se somente existem numeros.
				Long.valueOf(cpf);
				return cpf;
			} catch (NumberFormatException e) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Erro de conversão",
						"O valor informado não é um número de CPF!");
				throw new ConverterException(message);
			}
		}
		return value;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		String cpf = (value == null ? null : value.toString());
		if (cpf != null && !cpf.equals("")) {
			cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "."
					+ cpf.substring(6, 9) + "-" + cpf.substring(9);
		}
		return cpf;
	}
}