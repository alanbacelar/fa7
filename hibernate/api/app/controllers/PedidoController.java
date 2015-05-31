package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.Cliente;
import models.ItensPedido;
import models.Pedido;
import models.Produto;
import models.Vendedor;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.pedido.form;
import views.html.pedido.index;
import views.html.pedido.listar;
import dao.ClienteDao;
import dao.PedidoDao;
import dao.ProdutoDao;
import dao.VendedorDao;

public class PedidoController extends Controller {
	
	public static Result index(Integer id) {
		PedidoDao dao = new PedidoDao();
		dao.begin();

		try {
			ClienteDao clienteDao = new ClienteDao();
			Cliente cliente = clienteDao.consultarCliente(id);
			
			List<Pedido> clientePedidos = dao.pedidosCliente(id);
			
			dao.commit();
			
			return ok(index.render(clientePedidos, cliente));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (dao.isConnected()) {
				dao.rollback();
			}

			flash("error",
					"Ocorreu um erro : " + e.getMessage());
		}

		return redirect(routes.ClienteController.index());
	}
	
	public static Result adicionar(Integer id) {
        Pedido pedido = new Pedido();
        
        Form<Pedido> pedidoForm = Form.form(Pedido.class).fill(pedido);

        List<Vendedor> vendedores = new ArrayList<>();
        VendedorDao vendedorDao = new VendedorDao();
        ClienteDao clienteDao = new ClienteDao();
        Cliente cliente = new Cliente();
        
        List<Produto> produtos = new ArrayList<>();
        ProdutoDao produtoDao = new ProdutoDao();

        Map<String, String[]> formUrlEncoded = request().body().asFormUrlEncoded();
       
        try {
            vendedorDao.begin();
            vendedores = vendedorDao.todos();
            cliente = clienteDao.consultarCliente(id);
            produtos = produtoDao.todos();
            
            vendedorDao.commit();

        } catch (Exception e) {
            if (vendedorDao.isConnected()) {
            	vendedorDao.rollback();
            }

            flash("error", "Erro ao listar vendedores.");
        }

        return ok(form.render(pedidoForm, cliente, vendedores, produtos));
    }
	
	public static Result todos(){
		PedidoDao dao = new PedidoDao();
		dao.begin();
		List<Pedido> clientePedidos = new ArrayList<>();
		try {
			clientePedidos = dao.todos();
			dao.commit();
			
			return ok(listar.render(clientePedidos));
		} catch (Exception e) {
			if (dao.isConnected()) {
				dao.rollback();
			}

			flash("error",
					"Ocorreu um erro : " + e.getMessage());
		}
		return redirect(routes.Application.index());
	}
	
	public static Result save() {
        Pedido pedido = Form.form(Pedido.class).bindFromRequest().get();
        ClienteDao clienteDao = new ClienteDao();
        VendedorDao vendedorDao = new VendedorDao();
        
        Integer idCliente = Integer.parseInt(Form.form(Pedido.class).bindFromRequest().field("id_cliente").value());
        Integer idVendedor = Integer.parseInt(Form.form(Pedido.class).bindFromRequest().field("id_vendedor").value());
        
        ProdutoDao produtoDao = new ProdutoDao();
        PedidoDao dao = new PedidoDao();
        ItensPedido i = new ItensPedido();
        
        List<ItensPedido>itensList = new ArrayList<ItensPedido>();
        dao.begin();

        try {
        	i.setProduto(produtoDao.consultarProduto(1));
        	i.setQuantidade(new Integer(2));
        	itensList.add(i);
        	
        	
        	i.setProduto(produtoDao.consultarProduto(2));
        	i.setQuantidade(new Integer(4));
        	itensList.add(i);
        	
        	pedido.setCliente(clienteDao.consultarCliente(idCliente));
        	pedido.setVendedor(vendedorDao.consultarVendedor(idVendedor));
        	pedido.setItensPedido(itensList);
        	
            dao.salvar(pedido);
            dao.commit();
           // produtoDao.commit();

            flash("success", "Pedido salvo com sucesso.");
            return redirect(routes.PedidoController.index(pedido.getCliente().getId()));
        } catch (Exception e) {
            if (dao.isConnected()) {
                dao.rollback();
            }

            flash("error", "Ocorreu um erro ao tentar salvar: " + e.getMessage());
            vendedorDao.begin();
            List<Vendedor> vendedores = vendedorDao.todos();
            List<Produto> produtos = produtoDao.todos();
            
            Form<Pedido> pedidoForm = Form.form(Pedido.class).fill(pedido);
            vendedorDao.commit();
            return ok(form.render(pedidoForm, pedido.getCliente(), vendedores, produtos));
        }
    }

}
