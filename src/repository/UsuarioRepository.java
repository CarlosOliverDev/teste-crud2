package repository;

import entities.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRepository {
    public void salvarUsuario(Connection connection, Usuario usuario) {
        String sql = "INSERT INTO usuarios(nome, email) VALUES(?,?)";

        try(PreparedStatement stat = connection.prepareStatement(sql)) {
            stat.setString(1, usuario.getNome());
            stat.setString(2, usuario.getEmail());

            int linhasAlteradas = stat.executeUpdate();

            if(linhasAlteradas > 0) {
                System.out.println("Usuário salvo no Banco de Dados!");
            } else {
                System.out.println("Não foi possível salvar esse Usuário.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Falha na persistência do Usuário.");
        }
    }

    public void lerTodosUsuarios(Connection connection) {
        String sql = """
                SELECT id, nome, email
                FROM usuarios
                """;

        try(PreparedStatement stat = connection.prepareStatement(sql)) {

            ResultSet rs = stat.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                System.out.printf("ID: %d - Nome: %s - Email: %s",id,nome,email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não foi possível listar os Usuários salvos.");
        }
    }
    
}
