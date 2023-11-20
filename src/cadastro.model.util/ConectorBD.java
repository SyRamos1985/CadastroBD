package src.cadastro.model.util;

import java.io.*;
import java.util.Properties;

import javax.management.RuntimeErrorException;

import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;

public class ConectorBD {
    private static Connection conn = null;

    public static Connection gConnection() {
        if (conn == null) {
            try {
                Properties propt = loadProperties();
                String url = propt.getProperty("url");
                String login = propt.getProperty("login");
                String senha = propt.getProperty("url");
                conn = DriverManager.getConnection(url, login, senha);

            } catch (SQLException exception) {
                throw new RuntimeErrorException(
                        "Ops! erro ao obter conexão com o banco de dados: " + exception.getMessager(), exception);
            }
        }
        return conn;
    }

    public static void closeStatement(Statement sta){
        try{
            if(sta != null){ sta.close();}

            catch(SQLException exception){
                throw new RuntimeException("Ops! Erro ao fechar o Statement: " + exception.getMessage(),exception);
            }
        }
    }

    public static void closeResultSet(ResultSet rset){
         try{
            if(rset != null){rset.close();}

            catch(SQLException exception){
                throw new RuntimeException("Ops! Erro ao fechar o ResultSet: " + exception.getMessage(),exception);
            }
        }        
    }

    public static void closeConnection(){
         try{
            if(conn != null){conn.close();}

            catch(SQLException exception){
                throw new RuntimeException("Ops! Erro ao fechar a conexão com o Banco de Dados: " + exception.getMessage(),exception);
            }
        }        
    }

    public static Properties loadProperties(){
        try(InputStream is = ConectorBD.class.getClassLoader().getResourceAsStream("properties.db")){
            Properties propt = new Properties();
            if(is != null){propt.load(is);}
            else{ throw new FileNotFoundException("Ops! Arquivo properties.db não encontrado no classpath.");}

            return propt;

            catch(SQLException exception){
                throw new RuntimeException("Ops! Erro ao carregar as propriedades a conexão do Banco de Dados: " + exception.getMessage(),exception);
            }
        }        
    }
}
