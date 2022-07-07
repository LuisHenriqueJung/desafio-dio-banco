package Entidades;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    //@SuppressWarnings("null")
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        int entradaUser;
        do {
            System.out.println("------Bem vindo ao banco inter----------");
            System.out.println("Selecione uma operação:");
            System.out.println(
                    "1. Cadastrar cliente; \n" +
                            "2. Criar nova conta; \n" +
                            "3. Acessar uma conta; \n" +
                            "9. Sair da aplicação!");

            entradaUser = scan.nextInt();
            switch (entradaUser) {
                case 1:
                    System.out.println("Digite o nome do cliente: ");
                    String nome = scan.next();
                    System.out.println("Insira a idade: ");
                    scan.nextLine();
                    int idade = scan.nextInt();

                    Cliente cliente = new Cliente(nome, idade);
                    cliente.inserirCliente(cliente);
                    System.out.println("Cliente " + nome + " cadastrado com Sucesso!");
                    break;

                case 2:
                    System.out.println("Digite: \n -1 para conta correte \n -2 para conta Poupança");
                    int tipoConta = scan.nextInt();
                    System.out.println("Digite o id do Cliente:");
                    int idCliente = scan.nextInt();
                    Cliente cliente1 = new Cliente().retornarCliente(idCliente);
                    Conta conta = new Conta();
                    conta.criarConta(tipoConta, cliente1);
                    break;
                case 3:
                    System.out.println("Digite o numero da conta que deseja acessar: ");
                    int contaDeposito = scan.nextInt();

                    int interfaceConta;
                    do {
                        System.out.println("------Bem vindo a sua conta!------ \n" +
                                "O que deseja fazer? \n " +
                                "1. Depositar. \n" +
                                "2. Sacar.\n" +
                                "3. Transferir.\n"+
                                "4. Consultar saldo\n"+
                                "5. Imprimir informações da conta.\n"+
                                "9. Para sair da conta.");

                        interfaceConta = scan.nextInt();
                        Conta contaUser = new Conta();
                        contaUser = contaUser.retornaConta(contaDeposito);

                        switch (interfaceConta) {
                            case 1:
                                System.out.println("Insira o valor que deseja depositar: ");
                                double valorDeposito = scan.nextDouble();
                                contaUser.depositar(valorDeposito);
                                break;
                            case 2:
                                System.out.println("Insira o valor que deseja sacar: ");
                                double valorSaque = scan.nextDouble();
                                contaUser.sacar(valorSaque);
                                break;
                            case 3:
                                System.out.println("Digite a conta de Destino: ");
                                int contaDestino = scan.nextInt();
                                System.out.println("Insira o valor a transferir: ");
                                double valorTransferencia = scan.nextDouble();
                                Conta contaTranseferir = new Conta();
                                contaTranseferir = contaTranseferir.retornaConta(contaDestino);
                                contaUser.transferir(valorTransferencia,contaTranseferir);
                                break;
                            case 4:
                                System.out.println("Seu saldo é de: "+ contaUser.getSaldo() + " R$.");
                                break;
                            case 5:
                                contaUser.imprimirInfosComuns();
                                break;
                            default:
                                System.out.println("Digite uma opção válida");

                        }
                    } while (interfaceConta != 9);
                default:
                    System.out.println("Digite uma opção válida");
            }
        }while(entradaUser != 9);

        }
        }

