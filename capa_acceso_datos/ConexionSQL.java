package capa_acceso_datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {
    private static final String URL = "jdbc:postgresql://localhost:5432/proyectoFinal";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
