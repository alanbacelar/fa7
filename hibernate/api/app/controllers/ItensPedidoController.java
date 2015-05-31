package controllers;

import java.util.List;

import models.ItensPedido;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.pedido.itens;
import dao.ItensPedidoDao;

public class ItensPedidoController extends Controller {
	
	public static Result index(Integer id) {
		ItensPedidoDao dao = new ItensPedidoDao();
		dao.begin();

		try {	
			List<ItensPedido> itensPedido = dao.listarItensPedido(id);
			
			dao.commit();
			
			return ok(itens.render(itensPedido));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (dao.isConnected()) {
				dao.rollback();
			}

			flash("error",
					"Ocorreu um erro : " + e.getMessage());
		}

		return redirect(routes.PedidoController.index(id));
	}
}
