public abstract class Conta implements IConta{
    private int agencia;
    private int numero;
    private double saldo;
    private Cliente cliente;

    private static int SEQUENCIAL = 1;
    private static final int AGENCIA_PADRAO = 1;

    public Conta(Cliente cliente) {
        this.cliente = cliente;
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void sacar(double valor){
        if(valor <= saldo){
            saldo -= valor;
        } else {
            System.err.println("Saldo insuficiente para saque!");
        }

    }
    public void depositar(double valor){
        this.saldo += valor;
    }
    public void transferir(Conta conta, double valor){
        if(valor <= saldo){
            sacar(valor);
            conta.depositar(valor);
        } else {
            System.err.println("Saldo insuficiente para transferência!");
        }
    }

    protected void imprimirAtributosComuns() {
        System.out.println("Titular: " + cliente.getNome());
        System.out.println("Agencia: " + getAgencia());
        System.out.println("Número: " + getNumero());
        System.out.println("Saldo: " + getSaldo());
    }
}
