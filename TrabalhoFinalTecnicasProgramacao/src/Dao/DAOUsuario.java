package Dao;

import Connection.DBConnection;
import Exception.ConnectionException;
import Domain.TipoUsuario;
import Domain.Usuario;
import Exception.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class DAOUsuario implements IDAOUsuario{

    Connection conn = null;
    ResultSet res = null;
    Statement sta = null;
    PreparedStatement preparedsta = null;
    Collection<Usuario> usuarios;
    
    @Override
    public Collection<Usuario> getAll() throws ConnectionException, PersistenceException {
        try {
            usuarios = new ArrayList<>();
            String sql = "SELECT u.*, tu.ID as IdTIPO, tu.TIPO FROM Usuario u INNER JOIN TipoUsuario tu ON u.IdTipoUsuario = tu.Id";

            conn = DBConnection.getInstance();
            sta = conn.createStatement();
            res = sta.executeQuery(sql);
            while (res.next()) {
                usuarios.add(new Usuario(
                        res.getInt("ID"),
                        res.getString("NOME"),
                        res.getString("CPFCNPJ"),
                        res.getString("EMAIL"),
                        new TipoUsuario(res.getInt("IdTIPO"), res.getString("TIPO"))
                ));
            }
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao consultar Usuários.", ex.getCause());
        } finally {
            DBConnection.close(conn, sta, res);
        }

        return usuarios;
    }

    @Override
    public boolean insert(Usuario usuario) throws ConnectionException, PersistenceException {
        try {
            String sql = "INSERT INTO Usuario (IDTIPOUSUARIO, NOME, CPFCNPJ, EMAIL) VALUES (?,?,?,?)";

            if (usuario.getId() != 0) {
                sql = "UPDATE Usuario SET IDTIPOUSUARIO = ?, NOME = ?, CPFCNPJ = ?, EMAIL = ? WHERE ID = ?";
            }

            conn = DBConnection.getInstance();
            preparedsta = conn.prepareStatement(sql);
            preparedsta.setInt(1, usuario.getTipoUsuario().getId());
            preparedsta.setString(2, usuario.getNome());
            preparedsta.setLong(3, Long.parseLong(usuario.getCpfCnpj()));
            preparedsta.setString(4, usuario.getEmail());

            if (usuario.getId() != 0) {
                preparedsta.setInt(5, usuario.getId());
            }

            preparedsta.executeUpdate();
            
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao inserir Usuário.", ex.getCause());
        } finally {
            DBConnection.close(conn, preparedsta, null);
        }
        
        return true;
    }
    
    @Override
    public Collection<Usuario> getAllByType(int typeId) throws ConnectionException, PersistenceException {
        try {
            usuarios = new ArrayList<>();
            String sql = "SELECT u.*, tu.ID as IdTIPO, tu.TIPO FROM Usuario u INNER JOIN TipoUsuario tu ON u.IdTipoUsuario = tu.Id WHERE u.idtipoUsuario = ?";

            conn = DBConnection.getInstance();
            preparedsta = conn.prepareStatement(sql);
            preparedsta.setInt(1, typeId);
            res = preparedsta.executeQuery();
            while (res.next()) {
                usuarios.add(new Usuario(
                        res.getInt("ID"),
                        res.getString("NOME"),
                        res.getString("CPFCNPJ"),
                        res.getString("EMAIL"),
                        new TipoUsuario(res.getInt("IdTIPO"), res.getString("TIPO"))
                ));
            }
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao consultar Usuários.", ex.getCause());
        } finally {
            DBConnection.close(conn, preparedsta, res);
        }

        return usuarios;
    }
    
    @Override
    public Usuario getById(int id) throws ConnectionException, PersistenceException {
        try {
            String sql = "SELECT u.*, tu.ID as IdTIPO, tu.TIPO FROM Usuario u INNER JOIN TipoUsuario tu ON u.IdTipoUsuario = tu.Id WHERE u.id = ?";

            conn = DBConnection.getInstance();
            preparedsta = conn.prepareStatement(sql);
            preparedsta.setInt(1, id);
            res = preparedsta.executeQuery();
            while (res.next()) {
                return new Usuario(
                        res.getInt("ID"),
                        res.getString("NOME"),
                        res.getString("CPFCNPJ"),
                        res.getString("EMAIL"),
                        new TipoUsuario(res.getInt("IdTIPO"), res.getString("TIPO"))
                );
            }
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao consultar Usuários.", ex.getCause());
        } finally {
            DBConnection.close(conn, preparedsta, res);
        }

        return null;
    }
}
