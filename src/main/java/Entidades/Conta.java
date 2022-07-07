package Entidades;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static servicos.DBConcetion.getConnection;

public class Conta {

    protected static final int AGENCIA_PADRAO = 10;
    private int SEQUENCIAL = 10;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }
    public Conta(){}

    public Conta (int agencia,int numero,double saldo){
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
    }
    public void sacar(double valor) throws SQLException{
        saldo -= valor;
        try (Connection connection = getConnection()) {
            String SQL = "update conta set saldo = (?) where numero=(?);";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setDouble(1,this.getSaldo());
            preparedStatement.setInt(2, this.getNumero());
            preparedStatement.executeUpdate();
            System.out.println("Deposito realizado, saldo de " + this.getSaldo());
        }
    }



    public void depositar(double valor) throws SQLException {
        saldo += valor;
        try (Connection connection = getConnection()) {
            String SQL = "update conta set saldo = (?) where numero=(?);";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setDouble(1,this.getSaldo());
            preparedStatement.setInt(2, this.getNumero());
            preparedStatement.executeUpdate();
            System.out.println("Deposito realizado, saldo de " + this.getSaldo());
        }
    }

    public void transferir(double valor, Conta contaDestino) throws SQLException {
        this.sacar(valor);
        contaDestino.depositar(valor);
        System.out.println("Transferencia de " + valor + " realizada com sucesso!\n" +
                "Seu saldo é de " + this.getSaldo());

    }
    public void criarConta(int id_tipo,Cliente cliente) throws SQLException{
            Conta conta = new Conta(cliente);
            try (Connection connection = getConnection()) {
                String SQL = "INSERT INTO Conta(agencia,numero,saldo,ID_cliente,ID_tipo) VALUES (?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.setInt(1,conta.getAgencia());
                preparedStatement.setInt(2, conta.getNumero());
                preparedStatement.setDouble(3, conta.getSaldo());
                preparedStatement.setInt(4,cliente.getId());
                preparedStatement.setInt(5, id_tipo);
                preparedStatement.executeUpdate();
                System.out.println("sucesso, pode descarnsar!");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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
        public Conta retornaConta(int numero) throws SQLException {
            try(Connection connection = getConnection()){
                String SQL = "SELECT * FROM conta WHERE id=(?)";
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.setInt(1,numero);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Conta contaUser = new Conta(resultSet.getInt("agencia"), resultSet.getInt("numero"),resultSet.getDouble("saldo"));
                    return contaUser;
                }
            }
            return null;
            }
    public int getAgencia() {
        return agencia;
    }
    public int getNumero() {
        return numero;
    }
    public double getSaldo() {
        return saldo;
    }

    protected void imprimirInfosComuns() {
        System.out.println(String.format("Titular: %s", this.cliente.getnome()));
        System.out.println(String.format("Agencia: %d", this.agencia));
        System.out.println(String.format("Numero: %d", this.numero));
        System.out.println(String.format("saldo: %.2f", this.saldo));

    }



}