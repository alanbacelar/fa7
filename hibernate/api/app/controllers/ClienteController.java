package controllers;

import java.util.List;

import models.Cliente;
import models.Endereco;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.cliente.form;
import views.html.cliente.index;
import dao.ClienteDao;

public class ClienteController extends Controller {

    public static Result index() {
        ClienteDao dao = new ClienteDao();
        dao.begin();

        try {
            List<Cliente> clientes = dao.todos();
            dao.commit();
            return ok(index.render(clientes));
        } catch (Exception e) {
            if (dao.isConnected()) {
                dao.rollback();
            }

            return internalServerError(e.getMessage());
        }
    }

    public static Result adicionar() {
        Cliente cliente = new Cliente();
        cliente.setEndereco(new Endereco());

        Form<Cliente> clienteForm = Form.form(Cliente.class).fill(cliente);

        return ok(form.render(clienteForm));
    }

    public static Result editar(Integer id) {
        ClienteDao dao = new ClienteDao();
        dao.begin();

        try {
            Form<Cliente> clienteForm = Form.form(Cliente.class).fill(
                    dao.consultarCliente(id)
            );

            dao.commit();
            return ok(form.render(clienteForm));
        } catch (Exception e) {

            if (dao.isConnected()) {
                dao.rollback();
            }

            return internalServerError(e.getMessage());
        }
    }

    public static Result save() {
        Cliente cliente = Form.form(Cliente.class).bindFromRequest().get();

        ClienteDao dao = new ClienteDao();
        dao.begin();

        try {
            dao.salvar(cliente.getEndereco());
            dao.salvar(cliente);
            dao.commit();

            flash("success", "Cliente salvo com sucesso.");
            return redirect(routes.ClienteController.index());
        } catch (Exception e) {
            if (dao.isConnected()) {
                dao.rollback();
            }

            flash("error", "Ocorreu um erro ao tentar salvar: " + e.getMessage());

            Form<Cliente> clienteForm = Form.form(Cliente.class).fill(cliente);
            return ok(form.render(clienteForm));
        }
    }

    public static Result deletar(Integer id) {
        ClienteDao dao = new ClienteDao();
        dao.begin();

        try {

            Cliente cliente = dao.consultarCliente(id);
            dao.deletar(cliente);
            dao.commit();

            flash("success", "Cliente removido com sucesso.");

        } catch (Exception e) {
            if (dao.isConnected()) {
                dao.rollback();
            }

            flash("error", "Ocorreu um erro ao tentar remover: " + e.getMessage());
        }

        return redirect(routes.ClienteController.index());
    }

}