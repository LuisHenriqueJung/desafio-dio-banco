package Entidades;

import dao.ClienteDAO;

import java.sql.Connection;
import java.sql.SQLException;

import static servicos.DBConcetion.getConnection;

public class Main {

    //@SuppressWarnings("null")
        public static void main(String[] args) throws SQLException {

            //inserir cliente no cadastro, nome e idade como parâmetros.
            try(Connection connection = getConnection()) {
                ClienteDAO clienteDAO = new ClienteDAO(connection);
                clienteDAO.inserirCliente("Anderson",24);
            }

            //Busca todos os clientes;
            try(Connection connection = getConnection()) {
                ClienteDAO clienteDAO = new ClienteDAO(connection);
                System.out.println(clienteDAO.buscarClientes().toString());
            }

            //Buscar cliente pelo nome.
            try(Connection connection = getConnection()) {
                ClienteDAO clienteDAO = new ClienteDAO(connection);
                System.out.println(clienteDAO.buscarClientes("Anderson").toString());
            }

    }

}