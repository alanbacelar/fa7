package javax.faces;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesMessagesHelper {
	public static void createMessage(String key, String form) {
		FacesMessage message = new FacesMessage();
		
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
		String msg = bundle.getString(key);

		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		message.setDetail(msg);

		FacesContext.getCurrentInstance().addMessage(form, message);
	}
}
