package controllers;

import dao.ProdutoDao;
import factory.SessionHibernateFactory;
import helpers.CargaHelper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import play.mvc.Controller;
import play.mvc.Result;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;

import models.Produto;
import views.html.test.index;
import views.html.test.cache;
import views.html.test.pool;
import views.html.test.carga;


public class TestController extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static Result carga() {

        try {
            CargaHelper.carregar();

            flash("success", "Carga inicial finalizada com sucesso. ");
        } catch (Exception e) {
            flash("error", "Ocorreu um erro na carga inicial : " + e.getMessage());
        }

        return ok(carga.render());
    }

    public static Result pool() {
        try {
            for (int i = 0; i < 5; i++) {
                (new Thread(){
                    public void run() {
                        while(true){
                            try {
                                CargaHelper.carregar(false);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                ).start();
            }

            flash("success", "Teste de pool de conexões já está em andamento.");
        } catch (Exception e) {
            flash("error", "Ocorreu um erro ao testar pool de conexões: " + e.getMessage());
        }
        return ok(pool.render());
    }

    public static Result cache() {
        ProdutoDao dao = new ProdutoDao();
        Connection conn;

        String resultado1 = "";
        String resultado2 = "";

        try {
            Session session = SessionHibernateFactory.getHibernateSession();
            Transaction transaction = session.beginTransaction();
            System.out.println("CONN 1 [ABERTA]");

            System.out.println("CONN 1 [CONSULTANDO PRODUTO]");
            Produto produto = (Produto) session.get(Produto.class, new Integer(1));

            if (produto == null) {
                System.out.println("CONN 1 [NAO EXISTE PRODUTO COM ID: 1]");
                resultado1 = "Produto não encontrado.";
                resultado2 = "Não efetuado.";
                transaction.commit();
            } else {
                resultado1 = produto.getNome();
                System.out.println("CONN 1 [RESULTADO]: " + resultado1);


                System.out.println("Abrindo conexao paralela");
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/trabalho-hibernate?autoReconnect=true",
                        "root",
                        "1234"
                );

                Statement stmt = conn.createStatement();
                StringBuilder consulta = new StringBuilder();

                consulta.append("update produto set nome = '");
                consulta.append("Produto ");
                consulta.append((new Date()).toString());
                consulta.append("'");
                consulta.append(" where id_produto = ");
                consulta.append(produto.getId());

                stmt.executeUpdate(consulta.toString());
                System.out.println("Produto "+produto.getId()+" atualizado em outra conexão.");

                transaction.commit();
                System.out.println("CONN 1 [Finalizada].");


                session = SessionHibernateFactory.getHibernateSession();
                transaction = session.beginTransaction();
                System.out.println("CONN 2 [Aberta].");

                System.out.println("CONN 2 [CONSULTANDO PRODUTO]");
                produto = (Produto) session.get(Produto.class, new Integer(1));
                resultado2 = produto.getNome();
                System.out.println("CONN 2 [RESULTADO]: " + resultado1);

                transaction.commit();
            }

            flash("success", "Teste de cache finalizado com sucesso.");

        } catch (Exception e) {
            if (dao.isConnected()) {
                dao.rollback();
            }

            flash("error", "Ocorreu um erro ao testar cache: " + e.getMessage());
        }

        return ok(cache.render(resultado1, resultado2));
    }

}