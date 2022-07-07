package Entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static servicos.DBConcetion.getConnection;

public class Cliente {

    private int id;
    private String nome;
    private int idade;

    public Cliente(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
   }
    public Cliente(String nome, int idade,int id) {
        this.nome = nome;
        this.idade = idade;
        this.id =id;
    }
   public Cliente(){   }

    public String getnome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
    public void inserirCliente(Cliente cliente) throws SQLException {
        try (Connection connection = getConnection()) {
            String SQL = "INSERT INTO Cliente (nome,idade) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, cliente.getnome());
            preparedStatement.setInt(2, cliente.getIdade());
            preparedStatement.executeUpdate();
        }
    }
    public Cliente retornarCliente(int id) throws SQLException {
        try (Connection connection = getConnection()) {
            String SQL = "SELECT * FROM Cliente WHERE id = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Cliente> clientes = new ArrayList<>();

            while (resultSet.next()) {
                Cliente cliente = new Cliente(resultSet.getString("nome"), resultSet.getInt("idade"),resultSet.getInt("id"));
                return cliente;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                '}';
    }
}