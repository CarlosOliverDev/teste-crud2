package application;

import entities.Usuario;
import repository.UsuarioRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static String URL = "jdbc:mysql://127.0.0.1:3306/crud2?ssl=false&serverTimeZone=UTC";
    private static String user = "root";
    private static String senha = new Senha().getSenha();

    public static void main(String[] args) {
        Usuario usuario = new Usuario(0, "Carlos Oliveira", "carlos.teste@gmail.com");
        Usuario usuario2 = new Usuario(0, "João Silva", "joao@email.com");
        Usuario usuario3 = new Usuario(0, "Jordana", "jordana@email.com");
        Usuario usuario4 = new Usuario(0, "Maria", "maria@email.com");
        Usuario usuario5 = new Usuario(0, "Miguel", "miguel@email.com");

        System.out.println("Estabelecendo conexão com o Banco de Dados...");
        try(Connection connection = DriverManager.getConnection(URL,user,senha)) {
            Thread.sleep(1000);
            System.out.println("Conectado ao Banco de Dados!");

            UsuarioRepository usuarioRepository = new UsuarioRepository();

            System.out.println("Adicionando Usuários.");

            usuarioRepository.salvarUsuario(connection, usuario);
            usuarioRepository.salvarUsuario(connection, usuario2);
            usuarioRepository.salvarUsuario(connection, usuario3);
            usuarioRepository.salvarUsuario(connection, usuario4);
            usuarioRepository.salvarUsuario(connection, usuario5);

            System.out.println("Usuários adicionados com sucesso.");

            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=");

            Thread.sleep(1000);

            System.out.println("Excluindo Usuário.");

            usuarioRepository.excluirUsuarioPeloNome(connection, usuario2.getNome());

            System.out.println("Usuário removido com sucesso.");

            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=");

            Thread.sleep(1000);

            System.out.println("Atualizando email do Usuário.");

            String novoEmail = "carlos.teste123@email.com";
            usuarioRepository.atualizarEmailUsuario(connection, novoEmail, usuario.getNome());

            System.out.println("Usuário atualizado com sucesso.");

            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=");

            Thread.sleep(1000);

            System.out.println("Consultando todos os Usuários.");

            usuarioRepository.lerTodosUsuarios(connection);

            System.out.println("Consulta feita com sucesso.");
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
