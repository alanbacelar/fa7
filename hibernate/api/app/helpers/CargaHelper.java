package helpers;

import dao.ClienteDao;
import dao.FornecedorDao;
import models.*;

import java.util.Date;


public class CargaHelper {

    public static void carregar() throws Exception {
        CargaHelper.carregar(true);
    }

    public static void carregar(Boolean commit) throws Exception {
        ClienteDao dao = new ClienteDao();

        Vendedor vendedor;
        Fornecedor fornecedor;
        Cliente cliente;
        Produto produto;
        Pedido pedido;
        ItensPedido itens;
        Agenda agenda;
        Categoria categoria;

        try {
            dao.begin();

            Endereco endereco = new Endereco();
            endereco.setCep("61900340");
            endereco.setCidade("Maracanaú");
            endereco.setUf("CE");
            endereco.setLogradouro("Rua cinco");
            endereco.setNumero(503);

            dao.salvar(endereco);

            for (int i = 1; i<100; i++) {
                cliente = new Cliente();
                cliente.setCpf("12345567"+i);
                cliente.setDataNascimento(new Date());
                cliente.setEndereco(endereco);
                cliente.setNome("Cliente "+i);

                dao.salvar(cliente);

                vendedor = new Vendedor();
                vendedor.setCpf("098978776"+i);
                vendedor.setNome("Vendedor "+i);
                vendedor.setDataNascimento(new Date());
                vendedor.setEndereco(endereco);

                dao.salvar(vendedor);

                fornecedor = new Fornecedor();
                fornecedor.setEndereco(endereco);
                fornecedor.setCnpj("34798375/100"+i);
                fornecedor.setNome("Fornecedor "+i);
                fornecedor.setNomeFantasia("Fornecedor Fantasia "+i);

                dao.salvar(fornecedor);

                produto = new Produto();
                produto.setNome("Produto "+i);
                produto.setPreco(10.1*i);
                produto.setEstoque(3*i);
                produto.setFornecedor(fornecedor);

                dao.salvar(produto);

                pedido = new Pedido();
                pedido.setCliente(cliente);
                pedido.setVendedor(vendedor);

                dao.salvar(pedido);

                itens = new ItensPedido();
                itens.setPedido(pedido);
                itens.setProduto(produto);
                itens.setQuantidade(i);

                dao.salvar(itens);

                if (i % 10 == 0) {
                    categoria = new Categoria();
                    categoria.setNome("Categoria "+i);

                    dao.salvar(categoria);
                }

                if (i % 50 == 0) {
                    agenda = new Agenda();
                    agenda.setCliente(cliente);
                    agenda.setVendedor(vendedor);
                    agenda.setData(new Date());

                    dao.salvar(agenda);
                }
            }

            if(commit) {
                dao.commit();
            } else {
                dao.rollback();
            }
        } catch (Exception e) {
            if (dao.getSession().isOpen()) {
                dao.rollback();
            }

            throw new Exception(e);
        }
    }

}