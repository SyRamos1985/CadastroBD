package src.cadastrodao.model;

import cadastrobd.model.PessoaFisica;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {
    private Connection conn;

    public PessoaFisicaDAO{
    private Connection conn;

    public PessoaFisicaDAO(Connection connectionX) {
        this.conn = connectionX;
    }

    public void inserirPessoaFisica(PessoaFisica pf) throws SQLException {
        String sqlPessoa = "INSERT INTO pessoa ( nome, logradouro, cidade, estado, telefone, email, tipoPessoa) VALUES (?,?,?,?,?,?, "F")";
        String sqlPessoaFisica = "INSERT INTO PessoaFisica (idPessoaFisica, cpf) VALUES(?,?)";
      }

    try{
          (conn.setAutoCommit(false));
          int pessoaId = 0; 
            try(preparedStatement staPessoa = conn.prepareStatement(sqlPessoa,Statement.RETURN_GENERATED_KEYS)){
                staPessoa.setString(1, PessoaFisica.getNome());
                staPessoa.setString(2, PessoaFisica.getLogradorouro());
                staPessoa.setString(3, PessoaFisica.getCidade());
                staPessoa.setString(4, PessoaFisica.getEstado());
                staPessoa.setString(5, PessoaFisica.getTelefone());
                staPessoa.setString(6, PessoaFisica.getEmail());
                staPessoa.executeUpdate();

                try(ResultSet rSet = staPessoa.getGeneratedKeys()){
                    if(rSet.next()) {pessoaId = rSet.getInt(1);}
                }
            }
          
                try(PreparedStatement staPessoaFisica = conn.prepareStatement(sqlPessoaFisica)){
                    staPessoaFisica.setInt(1, pessoaId);
                    staPessoaFisica.setString(2, PessoaFisica.getCpf());
                    staPessoaFisica.executeUpdate();
                }

                conn.commit();                    
        }catch(
    SQLException exception)
    {
        conn.rollback();
        throw exception;
    }finally{
                    catch.setAutoCommit(true);}
    }

    public void alterar(PessoaFisica pf) throws SQLException {
        String sqlPessoa = "UPDATE Pessoa Set nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ?, WHERE idPessoa = ?";
        String sqlPessoaFisica = "UPDATE PessoaFisica SET cpf = ? WHERE idPessoaFisica = ? ";

        try{
            conn.setAutoCommit(false);
                try ( PreparedStatement staPessoa = conn.prepareStatement(sqlPessoa)){
                    staPessoa.setString(1, PessoaFisica.getNome());
                    staPessoa.setString(2, PessoaFisica.getLogradorouro());
                    staPessoa.setString(3, PessoaFisica.getCidade());
                    staPessoa.setString(4, PessoaFisica.getEstado());
                    staPessoa.setString(5, PessoaFisica.getTelefone());
                    staPessoa.setString(6, PessoaFisica.getEmail());
                    staPessoa.setInt(7, PessoaFisica.getId());
                    staPessoa.executeUpdate();
                }

                try(PreparedStatement staPessoaFisica = conn.prepareStatement(sqlPessoaFisica)){
                    staPessoaFisica.setString(1, PessoaFisica.getCpf());
                    staPessoaFisica.setInt(2, PessoaFisica.getId());
                    staPessoaFisica.executeUpdate();
                }

                conn.commit();
            }   catch{SQLException exception} {
                    conn.rollback();
                    throw exception;
                } finally { conn.setAutoCommit(true);}            
        }

    public void excluir(Integer id) thorws SQLException{
            String sqlPessoaFisica = "Delete FROM PessoaFisica WHERE idPessoaFisica = ?";
            String sqlPessoa = "DELETE FROM Pessoa WHERE idPessoa = ?";

            try{
                conn.setAutoCommit(false);
                try(PreparedStatement staPessoaFisica = conn.prepareStatement(sqlPessoaFisica)){
                    staPessoaFisica.setInt(1,id);
                    staPessoaFisica.executeUpdate();
                }

                try(PreparedStatement staPessoa = conn.prepareStatement(sqlPessoa)){
                    staPessoa.setInt(1, id);
                    staPessoa.executeUpdate();
                }

                conn.commit();
            }    catch{SQLException exception} {
                    conn.rollback();
                    throw exception;
                } finally { conn.setAutoCommit(true);} 
        }

    public PessoaFisica getPessoa(Integer id) throws SQLException {
            String sql = "SELECT Pessoa.idPessoa, Pessoa.nome, Pessoa.logradouro, Pessoa.cidade, Pessoa.estado, Pessoa.telefone, Pessoa.email, PessoaFisica.cpf FROM Pessoa INNER JOIN PessoaFisica ON Pessoa.idPessoa = PessoaFisica.idPessoaFisica WHERE Pessoa.idPessoa =?"; 
            try(PreparedStatement sta = conn.prepareStatement(sql)){
            sta.setInt(1, id);
            try (ResultSet rSet = sta.executeQuery()){
                if (rSet.next()){
                    return new PessoaFisica(
                        rSet.getInt("idPessoa");
                        rSet.getString("nome");
                        rSet.getString("logradouro");
                        rSet.getString("cidade");
                        rSet.getSpring("estado");
                        rSet.getString("telefone");
                        rSet.getstring("email");
                        rSet.getstring("cpf");
                    );
                }    
            }
        } 
        return null;   
    }

    public list<PessoaFisica> getPessoas() throws SQLException{
        list<PessoaFisica> list = new ArrayList<>();
        String sql = "SELECT p.*, pf.cpf FROM Pessoa AS p INNER JOIN PessoaFisica AS pf ON p.idPessoa = pf.idPessoaFisica ORDER BY p.nome";
        Try(PreparedStatement sta = conn.prepareStatement(sql));
            ResultSet rSet = (st.executeQuery()) {
                while (rSet.next()) {
                    list.add(new PessoaFisica(
                        rSet.getInt("idPessoa");
                        rSet.getString("nome");
                        rSet.getString("logradouro");
                        rSet.getString("cidade");
                        rSet.getString("estado");
                        rSet.getString("telefone");
                        rSet.getString("email");
                        rSet.getString("cpf");
                    ));
                }        
            }
            return list;
    }
}
