package repository;

import entities.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRepository {
    public void salvarUsuario(Connection connection, Usuario usuario) throws SQLException {
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
            throw new SQLException("Falha na persistência do Usuário.");
        }
    }

    public void lerTodosUsuarios(Connection connection) throws SQLException {
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
                System.out.printf("ID: %d - Nome: %s - Email: %s\n",id,nome,email);
            }

        } catch (SQLException e) {
            throw new SQLException("Não foi possível listar os Usuários salvos.");
        }
    }

    public void atualizarEmailUsuario(Connection connection,  String email, String nome) throws SQLException {
        String sql = """
                UPDATE usuarios
                SET email = ?
                WHERE nome = ?
                """;
        try(PreparedStatement stat = connection.prepareStatement(sql)) {
            stat.setString(1,email);
            stat.setString(2,nome);

            int linhasAlteradas = stat.executeUpdate();

            if(linhasAlteradas > 0) {
                System.out.println("Email atualizado para esse Usuário!");
            } else {
                System.out.println("Não foi possível modificar o email desse Usuário.");
            }

        } catch (SQLException e) {
            throw new SQLException("Falha na atualização do Usuário.");
        }
    }

    public void excluirUsuarioPeloNome(Connection connection, String nome) throws SQLException {
        String sql = """
                DELETE 
                FROM usuarios 
                WHERE nome = ?
                """;

        try(PreparedStatement stat = connection.prepareStatement(sql)) {
            stat.setString(1,nome);

            int linhasAlteradas = stat.executeUpdate();

            if(linhasAlteradas > 0) {
                System.out.println("Usuário excluído do Banco de Dados!");
            } else {
                System.out.println("Não foi possível excluir esse Usuário.");
            }
        } catch (SQLException e) {
            throw new SQLException("Não foi possível excluir um Usuário com esse nome.");
        }
    }
}
