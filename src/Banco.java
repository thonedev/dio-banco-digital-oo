import java.util.ArrayList;
import java.util.List;

public class Banco {
    private String nome;
    private List<Conta> contas = new ArrayList<>();
    public Banco(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void removeConta(Conta c){
        contas.remove(c);
    }

    public void addConta(Conta c){
        contas.add(c);
    }

    public void listarClientes() {
        for (var conta : contas) {
            if (conta instanceof ContaCorrente cc) {
                System.out.println("Cliente: " + cc.getCliente().getNome());
            } else if (conta instanceof ContaPoupanca poupanca) {
                System.out.println("Cliente: " + poupanca.getCliente().getNome());
            }
        }
    }

    public void listarContas(){
        for(var conta : contas){
            switch (conta) {
                case ContaCorrente cc -> {
                    System.out.printf("\nConta Corrente: %d\n", cc.getNumero());
                    System.out.println("Cliente: " + cc.getCliente().getNome());
                }
                case ContaPoupanca poupanca -> {
                    System.out.printf("Conta Poupança: %d\n", poupanca.getNumero());
                    System.out.println("Cliente: " + poupanca.getCliente().getNome());
                }
                default -> throw new IllegalStateException("Unexpected value: " + conta);
            }
        }
    }
}
