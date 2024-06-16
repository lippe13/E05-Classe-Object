import java.util.Date;
import java.util.Scanner;
import java.util.Objects;

class Operacao {
    private Date data;
    private char tipo;
    private float valor;

    public Date getData() {
        return this.data;
    }

    public char getTipo() {
        return this.tipo;
    }

    public void setTipo(char tipo) {
        if (this.tipo == 'd' || this.tipo == 's') {
            this.tipo = tipo;
        }
    }

    public float getValor() {
        return this.valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Operacao(char tipo, float valor) {
        this.tipo = tipo;
        this.valor = valor;
        data = new Date();
    }

    void extrato() {
        System.out.println(getData() + " " + getTipo() + " " + getValor());
    }

    @Override
    public String toString() {
        return "Operacao{" +
                "data=" + data +
                ", tipo=" + tipo +
                ", valor=" + valor +
                '}';
    }
}

class Saca extends Operacao {
    public Saca(float valor) {
        super('S', valor);
    }
}

class Deposita extends Operacao {
    public Deposita(float valor) {
        super('D', valor);
    }
}

class Cliente {
    private String nome;
    String endereco;
    Date dataCliente;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

class ClientePessoaFisica extends Cliente {
    String CPF;
    int idade;
    char sexo;

    @Override
    public String toString() {
        return "ClientePessoaFisica{" +
                "nome='" + getNome() + '\'' +
                ", CPF='" + CPF + '\'' +
                ", endereco='" + endereco + '\'' +
                ", idade=" + idade +
                ", sexo=" + sexo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientePessoaFisica that = (ClientePessoaFisica) o;
        return Objects.equals(CPF, that.CPF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CPF);
    }
}

class ClientePessoaJuridica extends Cliente {
    String CNPJ;
    int numFuncionarios;
    String setor;

    @Override
    public String toString() {
        return "ClientePessoaJuridica{" +
                "nome='" + getNome() + '\'' +
                ", CNPJ='" + CNPJ + '\'' +
                ", endereco='" + endereco + '\'' +
                ", numFuncionarios=" + numFuncionarios +
                ", setor='" + setor + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientePessoaJuridica that = (ClientePessoaJuridica) o;
        return Objects.equals(CNPJ, that.CNPJ);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CNPJ);
    }
}

class Conta {
    Operacao[] operacoes = new Operacao[999];
    Cliente cliente;
    private int numero;
    private float saldo_atual = 0;
    private float limite;
    int x = 0;

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public float getSaldo() {
        return this.saldo_atual;
    }

    public float getLimite() {
        return this.limite;
    }

    public void setLimite(float limite) {
        if (this.limite >= 0) {
            this.limite = limite;
        } else {
            limite = 0;
        }
    }

    void saca(float quantidade) {
        if (saldo_atual >= quantidade) {
            float nv_saldo = this.saldo_atual - quantidade;
            saldo_atual = nv_saldo;
            operacoes[x] = new Saca(quantidade);
            x++;
            System.out.println("Saque realizado com sucesso, no valor de BRL " + quantidade);
        } else {
            System.out.println("Saldo Insuficiente");
        }
    }

    void depositar(float quantidade) {
        float nv_saldo = this.saldo_atual + quantidade;
        if (nv_saldo > this.limite) {
            System.out.println("Limite estourado!!!");
        } else {
            saldo_atual = nv_saldo;
            operacoes[x] = new Deposita(quantidade);
            x++;
            System.out.println("Deposito realizado com sucesso, no valor de BRL " + quantidade);
        }
    }

    void imprimirExt() {
        System.out.println(" ");
        for (int i = 0; i < x; i++) {
            operacoes[i].extrato();
        }
        System.out.println(" ");
    }

    @Override
    public String toString() {
        return "Conta{" +
                "numero=" + numero +
                ", saldo_atual=" + saldo_atual +
                ", limite=" + limite +
                ", cliente=" + cliente +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return numero == conta.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }
}

class BMG {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Conta conta = new Conta();
        Cliente cliente = new Cliente();
        ClientePessoaFisica pf = new ClientePessoaFisica();
        ClientePessoaJuridica pj = new ClientePessoaJuridica();

        int x;
        int y;
        int z = 0;
        float quantidade;

        System.out.println("-----BANCO BMG-----");
        System.out.println("Vamos criar sua conta!");
        System.out.println("Identifique-se:");
        System.out.println("[1] - Pessoa Fisica");
        System.out.println("[2] - Pessoa Juridica");
        System.out.print("Insira um valor: ");
        y = sc.nextInt();

        switch (y) {
            case 1:
                z = 1;
                System.out.print("Insira o numero da conta: ");
                conta.setNumero(sc.nextInt());
                System.out.print("Insira seu limite: ");
                conta.setLimite(sc.nextFloat());
                sc.nextLine();
                System.out.print("Insira seu nome: ");
                cliente.setNome(sc.nextLine());
                System.out.print("Insira seu CPF: ");
                pf.CPF = sc.nextLine();
                System.out.print("Insira seu endereco: ");
                cliente.endereco = sc.nextLine();
                System.out.print("Insira sua idade: ");
                pf.idade = sc.nextInt();
                System.out.print("Insira seu sexo: ");
                pf.sexo = sc.next().charAt(0);
                System.out.println(" ");
                sc.nextLine();
                conta.cliente = pf;
                break;

            case 2:
                z = 2;
                System.out.print("Insira o numero da conta: ");
                conta.setNumero(sc.nextInt());
                System.out.print("Insira seu limite: ");
                conta.setLimite(sc.nextFloat());
                sc.nextLine();
                System.out.print("Insira seu nome: ");
                cliente.setNome(sc.nextLine());
                System.out.print("Insira seu CNPJ: ");
                pj.CNPJ = sc.nextLine();
                System.out.print("Insira seu endereco: ");
                cliente.endereco = sc.nextLine();
                System.out.print("Insira seu setor: ");
                pj.setor = sc.nextLine();
                System.out.print("Insira sua quantidade de funcionarios: ");
                pj.numFuncionarios = sc.nextInt();
                System.out.println(" ");
                sc.nextLine();
                conta.cliente = pj;
                break;

            default:
                System.out.println("Valor Invalido, tente novamente.");
                System.exit(0);
                break;
        }

        do {
            System.out.println("-----BANCO BMG-----");
            System.out.println(" ");
            System.out.println("Saldo Atual = " + conta.getSaldo());
            System.out.println(" ");
            System.out.println("[1] - Imprimir dados da sua conta");
            System.out.println("[2] - Sacar");
            System.out.println("[3] - Depositar");
            System.out.println("[4] - Imprimir extrato");
            System.out.println("[0] - Sair");

            System.out.print("Insira um valor: ");
            x = sc.nextInt();
            sc.nextLine();
            System.out.println("");

            switch (x) {
                case 1:
                    System.out.println(conta);
                    if (z == 1) {
                        System.out.println((ClientePessoaFisica) conta.cliente);
                    } else if (z == 2) {
                        System.out.println((ClientePessoaJuridica) conta.cliente);
                    }
                    break;

                case 2:
                    quantidade = 0;
                    System.out.print("Insira a quantidade que deseja sacar: ");
                    quantidade = sc.nextFloat();
                    conta.saca(quantidade);
                    break;

                case 3:
                    quantidade = 0;
                    System.out.print("Insira a quantidade que deseja depositar: ");
                    quantidade = sc.nextFloat();
                    conta.depositar(quantidade);
                    break;

                case 4:
                    conta.imprimirExt();
                    break;

                case 0:
                    System.out.println("Obrigado por usar o Banco BMG!");
                    break;

                default:
                    System.out.println("Valor Invalido, tente novamente.");
                    break;
            }

            System.out.println("");

        } while (x != 0);
        sc.close();
    }
}
