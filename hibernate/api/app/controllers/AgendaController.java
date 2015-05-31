package controllers;


import java.util.ArrayList;
import java.util.List;

import models.Agenda;
import models.Cliente;
import models.Vendedor;
import models.Visita;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.agenda.form;
import views.html.agenda.index;
import views.html.agenda.registrar;
import dao.AgendaDao;
import dao.ClienteDao;
import dao.VendedorDao;


public class AgendaController extends Controller {

    public static Result index() {
        AgendaDao dao = new AgendaDao();

        try {
            dao.begin();
            List<Agenda> agendas = dao.todos();
            dao.commit();
            return ok(index.render(agendas));
        } catch (Exception e) {
            if (dao.isConnected()) {
                dao.rollback();
            }

            return internalServerError(e.getMessage());
        }
    }

    public static Result adicionar() {
        Agenda agenda = new Agenda();

        ClienteDao clienteDao = new ClienteDao();
        VendedorDao vendedorDao = new VendedorDao();

        List<Vendedor> vendedores = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();

        Form<Agenda> agendaForm = Form.form(Agenda.class).fill(agenda);

        try {
            vendedorDao.begin();
            vendedores = vendedorDao.todos();
            clientes = clienteDao.todos();

            vendedorDao.commit();
        } catch (Exception e) {
            if (vendedorDao.isConnected()) {
                vendedorDao.rollback();
            }

            flash("error", "Ocorreu um erro ao carregar a lista de vendedores e/ou clientes: " + e.getMessage());
        }

        return ok(form.render(agendaForm, vendedores, clientes));
    }

    public static Result editar(Integer id) {
        AgendaDao dao = new AgendaDao();
        ClienteDao clienteDao = new ClienteDao();
        VendedorDao vendedorDao = new VendedorDao();

        List<Vendedor> vendedores = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();

        Form<Agenda> agendaForm = Form.form(Agenda.class);

        dao.begin();

        try {
            vendedores = vendedorDao.todos();
            clientes = clienteDao.todos();

            agendaForm = agendaForm.fill(
                    dao.consultarAgenda(id)
            );

            dao.commit();
        } catch (Exception e) {

            if (dao.isConnected()) {
                dao.rollback();
            }
        }

        return ok(form.render(agendaForm, vendedores, clientes));
    }

    public static Result save() {
        Form<Agenda> agendaForm = Form.form(Agenda.class);
        Agenda agenda = agendaForm.bindFromRequest().get();

        AgendaDao dao = new AgendaDao();
        ClienteDao clienteDao = new ClienteDao();
        VendedorDao vendedorDao = new VendedorDao();

        Integer idVendedor = Integer.parseInt(agendaForm.bindFromRequest().field("id_vendedor").value());
        Integer idCliente = Integer.parseInt(agendaForm.bindFromRequest().field("id_cliente").value());

        try {
            dao.begin();

            agenda.setCliente(clienteDao.consultarCliente(idCliente));
            agenda.setVendedor(vendedorDao.consultarVendedor(idVendedor));

            dao.salvar(agenda);
            dao.commit();

            flash("success", "Agenda salva com sucesso.");
        } catch (Exception e) {
            if (dao.isConnected()) {
                dao.rollback();
            }

            if (e.getCause().getMessage().contains("Duplicate entry")) {
                flash("warning", "Já existe uma visita agendada para esse mesmo horário.");
            } else {
                flash("error", "Ocorreu um erro ao tentar salvar: " + e.getCause().getMessage());
            }
        }

        return redirect(routes.AgendaController.index());
    }

    public static Result deletar(Integer id) {
        AgendaDao dao = new AgendaDao();
        dao.begin();

        try {

            Agenda agenda = dao.consultarAgenda(id);
            dao.deletar(agenda);
            dao.commit();

            flash("success", "Agenda removida com sucesso.");

        } catch (Exception e) {
            if (dao.isConnected()) {
                dao.rollback();
            }

            flash("error", "Ocorreu um erro ao tentar remover: " + e.getMessage());
        }

        return redirect(routes.AgendaController.index());
    }


    public static Result registrar(Integer id) {
        AgendaDao dao = new AgendaDao();
        Visita visita = new Visita();

        try {
            dao.begin();
            visita.setAgenda(dao.consultarAgenda(id));
            dao.commit();

        } catch (Exception e) {
            if (dao.isConnected()) {
                dao.rollback();
            }

            flash("error", "Ocorreu um erro ao tentar recuperar a visita: " + e.getMessage());
        }

        Form<Visita> visitaForm = Form.form(Visita.class).fill(visita);
        return ok(registrar.render(visitaForm));
    }


    public static Result salvarVisita() {
        Form<Visita> agendaForm = Form.form(Visita.class);
        Visita visita = agendaForm.bindFromRequest().get();

        AgendaDao dao = new AgendaDao();

        Integer idAgenda = Integer.parseInt(agendaForm.bindFromRequest().field("id_agenda").value());

        try {
            dao.begin();

            Agenda agenda = dao.consultarAgenda(idAgenda);
            visita.setAgenda(agenda);

            if (visita.getLatitude() == null || visita.getLongitude() == null) {
                dao.rollback();
                flash("warning", "Não foi possível recuperar sua localização. Verifique sua conexão com a internet e tente novamente.");
            } else {
                dao.salvar(visita);
                dao.commit();

                flash("success", "Visita registrada com sucesso.");
            }

        } catch (Exception e) {
            if (dao.isConnected()) {
                dao.rollback();
            }

            flash("error", "Ocorreu um erro ao tentar salvar: " + e.getCause().getMessage());
        }

        return redirect(routes.AgendaController.index());
    }

}