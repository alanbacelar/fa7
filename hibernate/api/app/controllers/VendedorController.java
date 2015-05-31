package controllers;

import java.util.List;

import models.Endereco;
import models.Vendedor;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.vendedor.form;
import views.html.vendedor.index;
import dao.VendedorDao;

public class VendedorController extends Controller {

    public static Result index() {
        VendedorDao dao = new VendedorDao();
        dao.begin();

        try {
            List<Vendedor> vendedores = dao.todos();
            dao.commit();
            return ok(index.render(vendedores));
        } catch (Exception e) {
            if (dao.isConnected()) {
                dao.rollback();
            }

            return internalServerError(e.getMessage());
        }
    }

    public static Result adicionar() {
        Vendedor vendedor = new Vendedor();
        vendedor.setEndereco(new Endereco());

        Form<Vendedor> vendedorForm = Form.form(Vendedor.class).fill(vendedor);

        return ok(form.render(vendedorForm));
    }

    public static Result editar(Integer id) {
        VendedorDao dao = new VendedorDao();
        dao.begin();

        try {

            Form<Vendedor> vendedorForm = Form.form(Vendedor.class).fill(
                    dao.consultarVendedor(id)
            );

            dao.commit();
            return ok(form.render(vendedorForm));
        } catch (Exception e) {

            if (dao.isConnected()) {
                dao.rollback();
            }

            return internalServerError(e.getMessage());
        }
    }

    public static Result save() {
        Vendedor vendedor = Form.form(Vendedor.class).bindFromRequest().get();
        VendedorDao dao = new VendedorDao();
        dao.begin();

        try {
            dao.salvar(vendedor.getEndereco());
            dao.salvar(vendedor);
            dao.commit();

            flash("success", "Vendedor salvo com sucesso.");
            return redirect(routes.VendedorController.index());
        } catch (Exception e) {
            if (dao.isConnected()) {
                dao.rollback();
            }

            flash("error", "Ocorreu um erro ao tentar salvar: " + e.getMessage());

            Form<Vendedor> vendedorForm = Form.form(Vendedor.class).fill(vendedor);
            return ok(form.render(vendedorForm));
        }
    }

    public static Result deletar(Integer id) {
        VendedorDao dao = new VendedorDao();
        dao.begin();

        try {

            Vendedor vendedor = dao.consultarVendedor(id);
            dao.deletar(vendedor);
            dao.commit();

            flash("success", "Vendedor removido com sucesso.");

        } catch (Exception e) {
            if (dao.isConnected()) {
                dao.rollback();
            }

            flash("error", "Ocorreu um erro ao tentar remover: " + e.getMessage());
        }

        return redirect(routes.VendedorController.index());
    }

}