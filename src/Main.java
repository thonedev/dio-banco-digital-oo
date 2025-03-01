import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Banco banco = new Banco("Meu Banco");

        while (true) {
            System.out.println("\n=== Menu Banco ===");
            System.out.println("1. Criar Conta Corrente");
            System.out.println("2. Criar Conta Poupança");
            System.out.println("3. Listar Clientes");
            System.out.println("4. Listar Contas");
            System.out.println("5. Depositar");
            System.out.println("6. Sacar");
            System.out.println("7. Transferir");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome do Cliente: ");
                    String nome = scanner.nextLine();
                    Cliente cliente = new Cliente(nome);
                    ContaCorrente conta = new ContaCorrente(cliente);
                    banco.addConta(conta);
                    System.out.println("Conta Corrente criada com sucesso!");
                }
                case 2 -> {
                    System.out.print("Nome do Cliente: ");
                    String nome = scanner.nextLine();
                    Cliente cliente = new Cliente(nome);
                    ContaPoupanca conta = new ContaPoupanca(cliente);
                    banco.addConta(conta);
                    System.out.println("Conta Poupança criada com sucesso!");
                }
                case 3 -> banco.listarClientes();
                case 4 -> banco.listarContas();
                case 5 -> {
                    System.out.print("Número da conta: ");
                    int numero = scanner.nextInt();
                    System.out.print("Valor do depósito: ");
                    double valor = scanner.nextDouble();
                    banco.getContas().stream()
                            .filter(conta -> conta.getNumero() == numero)
                            .findFirst()
                            .ifPresentOrElse(conta -> {
                                conta.depositar(valor);
                                System.out.println("Depósito realizado com sucesso!");
                            }, () -> System.out.println("Conta não encontrada!"));
                }
                case 6 -> {
                    System.out.print("Número da conta: ");
                    int numero = scanner.nextInt();
                    System.out.print("Valor do saque: ");
                    double valor = scanner.nextDouble();
                    banco.getContas().stream()
                            .filter(conta -> conta.getNumero() == numero)
                            .findFirst()
                            .ifPresentOrElse(conta -> {
                                conta.sacar(valor);
                            }, () -> System.out.println("Conta não encontrada!"));
                }
                case 7 -> {
                    System.out.print("Número da conta origem: ");
                    int numeroOrigem = scanner.nextInt();
                    System.out.print("Número da conta destino: ");
                    int numeroDestino = scanner.nextInt();
                    System.out.print("Valor da transferência: ");
                    double valor = scanner.nextDouble();

                    Conta origem = banco.getContas().stream()
                            .filter(conta -> conta.getNumero() == numeroOrigem)
                            .findFirst()
                            .orElse(null);

                    Conta destino = banco.getContas().stream()
                            .filter(conta -> conta.getNumero() == numeroDestino)
                            .findFirst()
                            .orElse(null);

                    if (origem != null && destino != null) {
                        origem.transferir(destino, valor);
                        System.out.println("Transferência realizada com sucesso!");
                    } else {
                        System.out.println("Conta origem ou destino não encontrada!");
                    }
                }
                case 8 -> {
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}
