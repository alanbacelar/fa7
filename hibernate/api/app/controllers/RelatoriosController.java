package controllers;

import dao.ClienteDao;
import dao.VendedorDao;
import models.Cliente;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.relatorios.index;

import java.util.List;

public class RelatoriosController extends Controller {

    public static Result index() {
        VendedorDao dao = new VendedorDao();
        ClienteDao clienteDao = new ClienteDao();

        dao.begin();

        try {
            List<Cliente> c1 = clienteDao.consultarClientesPorNome("1");

            dao.commit();
            return ok(index.render(c1));
        } catch (Exception e) {
            if (dao.isConnected()) {
                dao.rollback();
            }

            return internalServerError(e.getMessage());
        }
    }

}