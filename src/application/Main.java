package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static String URL = "jdbc:mysql://127.0.0.1:3306/crud2?ssl=false&serverTimeZone=UTC";
    private static String user = "root";
    private static String senha = new Senha().getSenha();

    public static void main(String[] args) {
        System.out.println("Estabelecendo conexão com o Banco de Dados...");
        try(Connection connection = DriverManager.getConnection(URL,user,senha)) {
            Thread.sleep(2000);
            System.out.println("Conectado ao Banco de Dados!");
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
            System.out.println("Não foi possível conectar com o Banco de Dados.");
        }
    }
}
