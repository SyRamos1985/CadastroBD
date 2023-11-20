package src.cadastrobd.model;

public class PessoaFisica extends Pessoa {
    private String cpf;

    public PessoaFisica(Interger id, String nome, String logradouro, String cidade, String estado, String telefone,
            String email, String cpf) {
        super(id, nome, logradouro, cidade, estado, telefone, email, "F");
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public void toString() {
        return super.toString() + "CPF: " + cpf;
    }
}
