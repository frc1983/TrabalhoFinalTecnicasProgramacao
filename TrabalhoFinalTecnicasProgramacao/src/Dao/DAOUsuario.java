package Dao;

import Connection.DBConnection;
import Exception.ConnectionException;
import Domain.TipoUsuario;
import Domain.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class DAOUsuario {
    
    public Collection<Usuario> getAll() throws ConnectionException{
        try {
            Connection conn = DBConnection.getConnection();
            Collection<Usuario> usuarios = new ArrayList<>();
            String sql = "SELECT u.*, tu.ID as IdTIPO, tu.TIPO FROM Usuario u INNER JOIN TipoUsuario tu ON u.IdTipoUsuario = tu.Id";

            PreparedStatement sta = conn.prepareStatement(sql);            
            ResultSet res = sta.executeQuery();
            while (res.next()) {
                usuarios.add(new Usuario(
                        res.getInt("ID"),
                        res.getString("NOME"),
                        res.getString("CPFCNPJ"),
                        res.getString("EMAIL"),
                        new TipoUsuario(res.getInt("IdTIPO"), res.getString("TIPO"))
                ));
            }
            res.close();
            sta.close();
            return usuarios;            
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getCause());
        }
    }

    public boolean novoUsuario(Usuario usuario) throws ConnectionException {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO Usuario (IDTIPOUSUARIO, NOME, CPFCNPJ, EMAIL) VALUES (?,?,?,?)";
            
            if(usuario.getId() != 0){
                sql = "UPDATE Usuario SET IDTIPOUSUARIO = ?, NOME = ?, CPFCNPJ = ?, EMAIL = ? WHERE ID = ?";
            }

            PreparedStatement sta = conn.prepareStatement(sql);
            sta.setInt(1, usuario.getTipoUsuario().getId());
            sta.setString(2, usuario.getNome());
            sta.setLong(3, Long.parseLong(usuario.getCpfCnpj()));
            sta.setString(4, usuario.getEmail());
            
            if(usuario.getId() != 0){
                sta.setInt(5, usuario.getId());
            }
            
            sta.executeUpdate();            
            sta.close();
            return true;
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getCause());
        }
    }
}
