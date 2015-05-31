package br.com.trabalho.listener;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.com.trabalho.managedbean.LoginBean;

public class LoginPhaseListener implements PhaseListener {
	private static final long serialVersionUID = -6679617179413348825L;

	public void afterPhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
		String viewId = context.getViewRoot().getViewId();
		LoginBean loginBean = context.getApplication().evaluateExpressionGet(
				context, "#{loginBean}", LoginBean.class);

		if (!viewId.startsWith("/usuario-editar.")) {
			if (viewId.startsWith("/logout.") || viewId.startsWith("/login.")) {
				ExternalContext externalContext = context.getExternalContext();
				HttpSession httpSession = (HttpSession) externalContext
						.getSession(false);
				httpSession.invalidate();
			} else if (loginBean.getUsuarioAutenticado() == null) {
				navigateToView(context, "login?faces-redirect=true");
			}
		}
	}

	private void navigateToView(FacesContext context, String view) {
		Application application = context.getApplication();
		NavigationHandler navigationHandler = application
				.getNavigationHandler();

		navigationHandler.handleNavigation(context, null, view);
	}

	public void beforePhase(PhaseEvent event) {
		// ...
	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
}