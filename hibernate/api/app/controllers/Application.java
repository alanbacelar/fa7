package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.Cliente;
import models.Fornecedor;
import models.Vendedor;
import models.Visita;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import dao.VisitaDao;

public class Application extends Controller {


    public static Result index() {
        VisitaDao visitaDao = new VisitaDao();
        List<Visita> visitas = new ArrayList<>();
        HashMap<String, Object> registros = new HashMap<>();

        try {
            visitaDao.begin();
            visitas = visitaDao.todos();

            registros.put("visitas", visitas.size());
            registros.put("clientes", visitaDao.count(Cliente.class));
            registros.put("vendedores", visitaDao.count(Vendedor.class));
            registros.put("fornecedores", visitaDao.count(Fornecedor.class));

            visitaDao.commit();

        } catch (Exception e) {
            if (visitaDao.isConnected()) {
                visitaDao.rollback();
            }

            flash("error", "Ocorreu um erro ao recurepar os dados: " + e.getMessage());
        }

        return ok(index.render(visitas, registros));
    }

}