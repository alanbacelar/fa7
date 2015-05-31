package br.com.trabalho.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.trabalho.model.Cep;

@FacesValidator("validators.CepValidator")
public class CepValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value != null) {
			Cep cep = (Cep)value;
			
			if (!Validador.validarCep(cep.getRegiao(), cep.getSufixo())) {
				FacesMessage message = new FacesMessage();
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				message.setSummary("Erro de Validacao");
				message.setDetail("Cep Invalido");
				throw new ValidatorException(message);
			}
		}
	}

}
