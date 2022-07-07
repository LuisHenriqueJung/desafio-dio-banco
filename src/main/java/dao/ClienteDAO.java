package dao;

import Entidades.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private final Connection connection;

    public ClienteDAO(Connection connection) { this.connection = connection; }

    public List<Cliente> buscarClientes() throws SQLException {
        String SQL = "SELECT * FROM Cliente ";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Cliente> clientes = new ArrayList<>();

        while (resultSet.next()){
            Cliente cliente = new Cliente();
            cliente.setId(resultSet.getInt("id"));
            cliente.setNome(resultSet.getString("nome"));
            cliente.setIdade(resultSet.getInt("idade"));
            clientes.add(cliente);
        }
        return clientes;
    }

    public List<Cliente> buscarClientes(String nome) throws SQLException {
        String SQL = "SELECT * FROM Cliente WHERE nome = (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, nome);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Cliente> clientes = new ArrayList<>();

        while (resultSet.next()){
            Cliente cliente = new Cliente();
            cliente.setId(resultSet.getInt("id"));
            cliente.setNome(resultSet.getString("nome"));
            cliente.setIdade(resultSet.getInt("idade"));
            clientes.add(cliente);
        }
        return clientes;
    }

    public void inserirCliente(String nome, int idade) throws SQLException {
        String SQL = "INSERT INTO Cliente (nome,idade) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, nome);
        preparedStatement.setInt(2, idade);
        preparedStatement.executeUpdate();
    }

}
