package controllers;

import java.util.List;

import models.Endereco;
import models.Fornecedor;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.fornecedor.form;
import views.html.fornecedor.index;
import dao.FornecedorDao;

public class FornecedorController extends Controller {

    public static Result index() {
        FornecedorDao dao = new FornecedorDao();
        dao.begin();

        try {
            List<Fornecedor> fornecedores = dao.todos();
            dao.commit();
            return ok(index.render(fornecedores));
        } catch (Exception e) {
            if (dao.isConnected()) {
                dao.rollback();
            }

            return internalServerError(e.getMessage());
        }
    }

    public static Result adicionar() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setEndereco(new Endereco());

        Form<Fornecedor> fornecedorForm = Form.form(Fornecedor.class).fill(fornecedor);

        return ok(form.render(fornecedorForm));
    }

    public static Result editar(Integer id) {
        FornecedorDao dao = new FornecedorDao();
        dao.begin();

        try {

            Form<Fornecedor> fornecedorForm = Form.form(Fornecedor.class).fill(
                    dao.consultarFornecedor(id)
            );

            dao.commit();
            return ok(form.render(fornecedorForm));
        } catch (Exception e) {

            if (dao.isConnected()) {
                dao.rollback();
            }

            return internalServerError(e.getMessage());
        }
    }

    public static Result save() {
        Fornecedor fornecedor = Form.form(Fornecedor.class).bindFromRequest().get();
        FornecedorDao dao = new FornecedorDao();
        dao.begin();

        try {
            dao.salvar(fornecedor.getEndereco());
            dao.salvar(fornecedor);
            dao.commit();

            flash("success", "Fornecedor salvo com sucesso.");
            return redirect(routes.FornecedorController.index());
        } catch (Exception e) {
            if (dao.isConnected()) {
                dao.rollback();
            }

            flash("error", "Ocorreu um erro ao tentar salvar: " + e.getMessage());

            Form<Fornecedor> fornecedorForm = Form.form(Fornecedor.class).fill(fornecedor);
            return ok(form.render(fornecedorForm));
        }
    }

    public static Result deletar(Integer id) {
        FornecedorDao dao = new FornecedorDao();
        dao.begin();

        try {

            Fornecedor fornecedor = dao.consultarFornecedor(id);
            dao.deletar(fornecedor);
            dao.commit();

            flash("success", "Fornecedor removido com sucesso.");

        } catch (Exception e) {
            if (dao.isConnected()) {
                dao.rollback();
            }

            flash("error", "Ocorreu um erro ao tentar remover: " + e.getMessage());
        }

        return redirect(routes.FornecedorController.index());
    }

}