package src.cadastrobd.model;

public class PessoaJuridica extends Pessoa {
    public static void main(String[] args) {
    private String cnpj;

 
        public PessoaJuridica(Interger id, String nome, String logradouro, String cidade, String estado, String telefone,
             String email, String cpf, String cnpj) {
            super(id, nome, logradouro, cidade, estado, telefone, email);
            this.cnpj = cnpj;
        }

        public String getCnpj() {
            return cnpj;
        }

        public void setCnpj(String cnpj) {
            this.cnpj = cnpj;
        }

        @Override
            public String to

        String(){
            return super.toSpring() + "CNPJ: " + cnpj;
        }
    }
}
