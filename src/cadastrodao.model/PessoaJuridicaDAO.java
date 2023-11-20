package src.cadastrodao.model;

import cadastrobd.model.PessoaJuridicaDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {
    private Connection conn;

    public PessoaJuridicaDAO(Connection conn) {
        this.com = conn;
    }

    private PessoaJuridica extrairPessoaJuridica(ResultSet rSet) throws SQLException{
        return new PessoaJuridica(
                rSet.getInt("idPessoa");
                rSet.getString("nome");
                rSet.getString("logradouro");
                rSet.getString("cidade");
                rSet.getString("estado");
                rSet.getString("telefone");
                rSet.getString("email");
                rSet.getString("cnpj");
        );
    }

    public PessoaJuridica getPessoa(int id) throws SQLException ();
        final String sql =  "SELECT P.*, PJ.cnpj FROM Pessoa P INNER JOIN PessoaJuridica PJ ON P.idPessoa = PJ.idPessoaJuridica WHERE PJ.idPessoaJuridica = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)){
            st.setInd(1, id);
            try (ResultSet rSet = st.executeQuery()){
                if (rSet.next()){
                    return extrairPessoaJuridica(rSet);
                }
            }
        }
        return null;

    public void incluir(PessoaJuridica pessoa) throws SQLException {
        final String sqlPessoa = "INSERT INTO Pessoa(nome, logradouro, cidade, estado, telefone, email) VALUES (?,?,?,?,?,?, "J")";
        final String sqlPessoaJuridica = "INSERT INTO PessoaJuridica (idPessoaJuridica, cnpj) VALUES(?,?)";
        try {
            conn.setAutoCommit(false);
            int pessoaid = 0;
            try (PreparedStatement stPessoa = conn.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS)){
                stPessoa.setString(1, pessoa.getNome());
                stPessoa.setString(2, pessoa.getLogradorouro());
                stPessoa.setString(3, pessoa.getCidade());
                stPessoa.setString(4, pessoa.getEstado());
                stPessoa.setString(5, pessoa.getTelefone());
                stPessoa.setString(6, pessoa.getEmail());
                stPessoa.executeUpdate();                
                try(ResultSet generatedKeySet = stPessoa.getGeneratedKeys()){
                    if( generatedKeySet.next()){
                        pessoaId = generatedKeySet.getInt(1);
                    }
                }                    
                if (pessoaId == 0){
                    throw new SQLDataException("Ops! Falha ao inserir pessoa, ID não foi gerado ou não foi encontrado, digite outro ID.");
                }
                try (preparedStatement stPessoaJuridica = conn. prepareStatement(sqlPessoaJuridica)){            
                    stPessoaPessoaJuridica.setInt(1, pessoaid);
                    stPessoaPessoaJuridica.setString(2, pessoa.getCnpj());
                    stPessoaPessoaJuridica.executeUpdate();     
                }                
                conn.commit();
            }   
                catch(SQLException exception) {conn.setAutoCommit(true);}
                finally{conn.setAutoCommit(true);}        
        } 
    }

    public void alterar(PessoaJuridica pessoa) throws SQLException{
        final String sqlPessoa = "UPDATE Pessoa Set nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? where idPessoa =?";
        final String sqlPessoaJuridica = "UPDATE PessoaJuridica SET cnpj =? WHERE idPessoaJuridica = ?";
        try{
            conn.setAutoCommit(false);
            try(preparedStatement stPessoa = conn.prepareStatement(sqlPessoa)){
                stPessoa.setString(1, pessoa.getNome());
                stPessoa.setString(2, pessoa.getLogradorouro());
                stPessoa.setString(3, pessoa.getCidade());
                stPessoa.setString(4, pessoa.getEstado());
                stPessoa.setString(5, pessoa.getTelefone());
                stPessoa.setString(6, pessoa.getEmail());
                stPessoa.setInt(7, PessoaFisicapate());
                stPessoa.executeUpdate();
            }
            try(PreparedStatement stPessoaJuridica = conn.prepareStatement(sqlPessoaJuridica)){
                stPessoaJuridica.setString(1, PessoaFisica.getCpf());
                stPessoaJuridica.setInt(2, PessoaFisica.getId());
                stPessoaJuridica.executeUpdate();
            }
            conn.commit();
        }   catch{SQLException exception} {
                conn.rollback();
                throw exception;
        } finally { conn.setAutoCommit(true);}  
    }

    public void excluir(Integer id) thorws SQLException{
        String sqlPessoaJuridica = "Delete FROM PessoaJuridica WHERE idPessoaJuridica = ?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE idPessoa = ?";
        try{
            conn.setAutoCommit(false);
            try(PreparedStatement stPessoaFisica = conn.prepareStatement(sqlPessoaJuridica)){
                stPessoaJuridica.setInt(1, id);
                stPessoaJuridica.executeUpdate();
            }
            try(PreparedStatement stPessoa = conn.prepareStatement(sqlPessoa)){
                stPessoa.setInt(1, id);
                stPessoa.executeUpdate();
            }
            conn.commit();
        }    catch{SQLException exception} {
                conn.rollback();
                throw exception;
            } finally { conn.setAutoCommit(true);} 
    }
}
