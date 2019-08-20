package br.edu.unisep.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection obterConexao() throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        var con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/horarios",
                "postgres", "admin");

        return con;
    }
}
