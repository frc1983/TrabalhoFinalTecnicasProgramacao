package Dao;

import Connection.DBConnection;
import Exception.ConnectionException;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOUsuario {

    public boolean novoUsuario(Usuario usuario) throws ConnectionException {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO Usuario (IDTIPOUSUARIO, NOME, CPFCNPJ, EMAIL) VALUES (?,?,?,?)";

            PreparedStatement sta = conn.prepareStatement(sql);
            sta.setInt(1, usuario.getTipoUsuario().getId());
            sta.setString(2, usuario.getNome());
            sta.setLong(3, Long.parseLong(usuario.getCpfCnpj()));
            sta.setString(4, usuario.getEmail());
            sta.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getCause());
        }
    }
}
