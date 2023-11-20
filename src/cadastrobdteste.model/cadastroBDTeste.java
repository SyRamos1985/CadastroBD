package src.cadastrobdteste.model;

import cadastrobd.modelbd.model.Pessoa;
import cadastrodb.modelbd.model.PessoaFisica;
import cadastrodb.modelbd.model.PessoaJuridica;
import cadastrodao.model.PessoaFisicaDAO;
import cadastrodao.model.PessoaJuridicaDAO;
import cadastro.model.util.ConectorBD;
import cadastro.model.util.SequenceManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.list;
import java.util.Scanner;

public class cadastroBDTeste {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Connection connection = ConectorBD.getConnection();
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(connection);
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO(connection);

        int opcao;
        do{
            System.out.println("=================================");
            System.out.println("Selecione a opção desejada: ");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("6 - Exibir Pessoa Física");
            System.out.println("5 - Exibir Pessoa Jurídica");
            System.out.println("0 - Finalizar Programa");
            System.out.println("=================================");
            opcao = scanner.nextInt();
            scanner.nextLine();
            try{
                switch(opcao){
                    case 1:
                        system.out.println("F - Pessoa Física | J - Pessoa Jurídica");
                        char tInclusao = scanner.next().charAt(opcao);
                        scanner.nextLine();
                        if (tInclusao == "F" | tInclusao == "fi"){cadastrarPessoaFisica(pessoaFisicaDAO, scanner);}
                        else if (tInclusao == "J" | tInclusao == "ju"){cadastrarPessoaJuridica(pessoaFisicaJuridicaDAO, scanner);}                        
                        break;                
                    case 2:
                        alterarPessoa(pessoaFisicaDAO, pessoaJuridicaDAO, scanner);
                        break;
                    case 3:
                        excluirPessoa(pessoaFisicaDAO, pessoaJuridicaDAO, scanner);
                        break;
                    case 4:
                        buscarPeloId(pessoaFisicaDAO, pessoaJuridicaDAO, scanner);
                        break; 

                    case 5:
                        exibirTodos(pessoaFisicaDAO, pessoaJuridicaDAO);
                        break;  
                    case 6:
                        exibirPessoaFisica(pessoaFisicaDAO);
                        break; 
                    case 7:
                         exibirPessoaJuridica(pessoaFisicaDAO, pessoaJuridicaDAO, scanner);
                        break; 
                    case 0:
                        System.out.println("Encerrando o programa. Volte sempre que precisar!!!");
                        break; 
                    default: System.out.println("Ops... Opção inválida. Tente novamente!");   
                }  catch(SQLException exception){
                    System.out.println("Ops... Erro no Banco de Dados: " + exception.getMessage());
                    exception.printStackTrace();}
                while (opcao != 0);
            }
        } 
    }

    private static void cadastroPessoaFisica(PessoaFisicaDAO pessoaFisicaDAO, Scanner sca) throws SQLException {
        System.out.println("Digite o nome completo do usuário: ");
        String nomeCompleto = scanner.nextLine();
        System.out.println("Digite o CPF; ");
        String cpf = scanner.nextLine();
        System.out.println("Digite o telefone; ");
        String telefone = scanner.nextLine();
        System.out.println("Digite o email; ");
        String email = scanner.nextLine();
        System.out.println("Digite o logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.println("Digite a cidade: ");
        String cidade = scanner.nextLine();
        System.out.println("Digite o estado; ");
        String estado = scanner.nextLine();

        PessoaFisica newPessoaFisica = new PessoaFisica(0, nome, cpf, telefone, email, logradouro, cidade, estado);
        pessoaFisicaDAO.inserirPessoaFisica(newPessoaFisica);
        System.out.println("Pessoa Física cadastrada com sucesso");
    }

    private static void cadastroPessoaJuridica(PessoaFisicaJuridicaDAO pessoaFisicaJuridicaDAO, Scanner sca)
            throws SQLException {
        System.out.println("Digite o nome completo da Empresa: ");
        String nomeCompleto = scanner.nextLine();
        System.out.println("Digite o CNPJ; ");
        String cpf = scanner.nextLine();
        System.out.println("Digite o telefone; ");
        String telefone = scanner.nextLine();
        System.out.println("Digite o email; ");
        String email = scanner.nextLine();
        System.out.println("Digite o logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.println("Digite a cidade: ");
        String cidade = scanner.nextLine();
        System.out.println("Digite o estado; ");
        String estado = scanner.nextLine();

        PessoaFisica newPessoaFisicaJuridica = new PessoaFisica(0, nome, cnpj, telefone, email, logradouro, cidade,
                estado);
        pessoaFisicaDAO.inserirPessoaFisicaJuridica(newPessoaFisicaJuridica);
        System.out.println("Empresa cadastrada com sucesso");
    }

    private static void alterarPessoa(PessoaFisicaDAO pessoaFisicaDAO, PessoaJuridicaDAO pessoaJuridicaDAO, Scanner sca ) throws SQLException{
        System.out.println("F - Alterar Pessoa Física | J - Alterar Pessoa Jurídica");
        char tPessoa = sca.next().charAt(0);
        sca.nextLine();

        if (tPessoa == "F" | tPessoa == "fi"){
            System.out.println(" Digite o ID do usuário que deseja alterar: ");
            int id = sca.nextInt();
            sca.nextLine();

            PessoaFisica pesExistente = pessoaFisicaDAO.getPessoa(id);

            if (pesExistente != null){
                System.out.println("Digite o novo, nome completo do usuário: ");
                    String novoNomeCompleto = scanner.nextLine();
                System.out.println("Digite o novo CPF; ");
                    String novoCpf = scanner.nextLine();
                System.out.println("Digite o novo telefone; ");
                    String novoTelefone = scanner.nextLine();        
                System.out.println("Digite o novo email; ");
                    String novoEmail = scanner.nextLine();
                System.out.println("Digite o novo logradouro: ");
                    String novoLogradouro = scanner.nextLine();
                System.out.println("Digite a nova cidade: ");
                    String novaCidade = scanner.nextLine();
                System.out.println("Digite o novo estado; ");
                    String novoEstado = scanner.nextLine();
                
                PessoaFisica novaPessoaFisica = new PessoaFisica(id, novoNome, novoCpf, novoTelefone, novoEmail, novoLogradouro, novoCidade, novoEstado);    
                    pessoaFisicaDAO.alterarPessoaFisica(novaPessoa) ;
                    System.out.println("Pessoa Física atualizada com sucesso");      
            } else {System.out.println("Pessoa Física não encontrada.");}            
        }

        else if (tPessoa == "J" | tPessoa == "ju"){
            System.out.println(" Digite o ID da empresa que deseja alterar: ");
            int id = sca.nextInt();
            sca.nextLine();

            PessoaJuridica pesExistente = pessoaJuridicaDAO.getPessoa(id);

            if (pesExistente != null){
                System.out.println("Digite o novo, nome completo da empresa: ");
                    String novoNomeCompleto = scanner.nextLine();
                System.out.println("Digite o novo CPF; ");
                    String novoCnpj = scanner.nextLine();
                System.out.println("Digite o novo telefone; ");
                    String novoTelefone = scanner.nextLine();        
                System.out.println("Digite o novo email; ");
                    String novoEmail = scanner.nextLine();
                System.out.println("Digite o novo logradouro: ");
                    String novoLogradouro = scanner.nextLine();
                System.out.println("Digite a nova cidade: ");
                    String novaCidade = scanner.nextLine();
                System.out.println("Digite o novo estado; ");
                    String novoEstado = scanner.nextLine();
                
                PessoaJuridica novaPessoa = new PessoaJuridica(id, novoNome, novoCnpj, novoTelefone, novoEmail, novoLogradouro, novoCidade, novoEstado);    
                    pessoaJuridicaDAO.alterarPessoaJuridica(novaPessoa) ;
                    System.out.println("Pessoa Jurídica atualizada com sucesso.");      
            } else {System.out.println("Pessoa Jurídica não encontrada.");} 
           else{System.out.println("Ops.. Opção inválida")}           
        }
    }

    private static void excluirPessoa(PessoaFisicaDAO pessoaFisicaDAO, PessoaJuridicaDAO pessoaJuridicaDAO, Scanner sca ) throws SQLException{
        System.out.println("F - Excluir Pessoa Física | J - Excluir Pessoa Jurídica");
        char tPessoa = sca.next().charAt(0);
        sca.nextLine();

        if (tPessoa == "F" | tPessoa == "fi"){
            System.out.println(" Digite o ID do usuário que deseja excluir: ");
            int id = sca.nextInt();
            pessoaFisicaDAO.excluir(id);
            System.out.println(" Pessoa Física excluída com sucesso:");

         else if (tPessoa == "J" | tPessoa == "ju"){
            System.out.println(" Digite o ID da empresa que deseja excluir:");
            int id = sca.nextInt();
            pessoaJuridicaDAO.excluir(id);
            System.out.println("Pessoa Jurídica excluída com sucesso.");
             sca.nextLine();
          
         else{System.out.println("Ops.. Opção inválida")} }        
        }    
    }

    private static void buscarPessoaPeloId(PessoaFisicaDAO pessoaFisicaDAO, PessoaJuridicaDAO pessoaJuridicaDAO, Scanner sca ) throws SQLException{
        System.out.println("F - buscar Pessoa Física | J - Buscar Pessoa Jurídica");
        char tPessoa = sca.next().charAt(0);
        sca.nextLine();

        if (tPessoa == "F" | tPessoa == "fi"){
            System.out.println(" Digite o ID do usuário: ");
            int id = sca.nextInt();
            sca.nextLine();

            PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoa(id);

            if(pessoaFisica != null){
                System.out.println("Detalhes do Usuário:");
                System.out.println("ID: " + pessoaFisica.getId());
                System.out.println("Nome: " + pessoaFisica.getNome());
                System.out.println("CPF: " + pessoaFisica.getCpf());
                System.out.println("Telefone: " + pessoaFisica.getTelfone());
                System.out.println("Email: " + pessoaFisica.getEmail());
                System.out.println("Logradouro: " + pessoaFisica.getLogradouro());
                System.out.println("Cidade: " + pessoaFisica.getCidade());
                System.out.println("Estado: " + pessoaFisica.getEstado());
            }
            else{System.out.println("Usuário não encontrado.");}            
        }

        else if (tPessoa == "J" | tPessoa == "ju"){
            System.out.println(" Digite o ID da empresa: ");
            int id = sca.nextInt();
            sca.nextLine();

            PessoaJuridica pessoaJuridica = pessoaJuridicaDAO.getPessoa(id);

            if(pessoaFisica != null){
                System.out.println("Detalhes do Empresa:");
                System.out.println("ID: " + pessoaJuridica.getId());
                System.out.println("Nome: " + pessoaJuridica.getNome());
                System.out.println("CNPJ: " + pessoaJuridica.getCpf());
                System.out.println("Telefone: " + pessoaJuridica.getTelfone());
                System.out.println("Email: " + pessoaJuridica.getEmail());
                System.out.println("Logradouro: " + pessoaJuridica.getLogradouro());
                System.out.println("Cidade: " + pessoaJuridica.getCidade());
                System.out.println("Estado: " + pessoaJuridica.getEstado());
            }
            else{System.out.println("Empresa não encontrada.");}  
            else{System.out.println("Opção inválida.");}  
        }
    }

    private static void exibirTodasPessoas(PessoaFisicaDAO pessoaFisicaDAO, PessoaJuridicaDAO pessoaJuridicaDAO)
            throws SQLException {
        System.out.println(" Usuário cadastrado:");
        List<PessoaFisica> pesFisicas = pessoaFisicaDAO.getPessoas();
        if (pesFisicas.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            for (PessoaFisica pf : pesFisicas) {
                System.out.println("ID: " + pf.getId());
                System.out.println("Nome: " + pf.getNome());
                System.out.println("CPF: " + pf.getCpf());
                System.out.println("Telefone: " + pf.getTelfone());
                System.out.println("Email: " + pf.getEmail());
                System.out.println("Logradouro: " + pf.getLogradouro());
                System.out.println("Cidade: " + pf.getCidade());
                System.out.println("Estado: " + pf.getEstado());
                System.out.println();
            }
        }

        System.out.println(" Empresa cadastrada:");
        List<PessoaJuridica> pesJuridicas = pessoaJuridicaDAO.getPessoasJuridicas();
        if (pesJuridicas.isEmpty()) {
            System.out.println("Nenhuma empresa cadastrada.");
        } else {
            for (PessoaJuridica pj : pesJuridicas) {
                System.out.println("ID: " + pj.getId());
                System.out.println("Nome: " + pj.getNome());
                System.out.println("CNPJ: " + pj.getCnpj());
                System.out.println("Telefone: " + pj.getTelfone());
                System.out.println("Email: " + pj.getEmail());
                System.out.println("Logradouro: " + pj.getLogradouro());
                System.out.println("Cidade: " + pj.getCidade());
                System.out.println("Estado: " + pj.getEstado());
                System.out.println();
            }
        }
    }

    private static void exibirPessoasFisicas(PessoaFisicaDAO pessoaFisicaDAO) throws SQLException {
        System.out.println(" Usuários cadastrados:");
        List<PessoaFisica> pesFisicas = pessoaFisicaDAO.getPessoas();
        if (pesFisicas.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            for (PessoaFisica pf : pesFisicas) {
                System.out.println("ID: " + pf.getId());
                System.out.println("Nome: " + pf.getNome());
                System.out.println("CPF: " + pf.getCpf());
                System.out.println("Telefone: " + pf.getTelfone());
                System.out.println("Email: " + pf.getEmail());
                System.out.println("Logradouro: " + pf.getLogradouro());
                System.out.println("Cidade: " + pf.getCidade());
                System.out.println("Estado: " + pf.getEstado());
                System.out.println();
            }
        }

        System.out.println(" Empresas cadastradas:");
        List<PessoaJuridica> pesJuridicas = pessoaJuridicaDAO.getPessoas();
        if (pesJuridicas.isEmpty()) {
            System.out.println("Nenhuma emmpresa cadastrada.");
        } else {
            for (PessoaJuridica jd : pesJuridicas) {
                System.out.println("ID: " + pj.getId());
                System.out.println("Nome: " + pj.getNome());
                System.out.println("CPF: " + pj.getCpf());
                System.out.println("Telefone: " + pj.getTelfone());
                System.out.println("Email: " + pj.getEmail());
                System.out.println("Logradouro: " + pj.getLogradouro());
                System.out.println("Cidade: " + pj.getCidade());
                System.out.println("Estado: " + pj.getEstado());
                System.out.println();
            }
        }
    }
}
