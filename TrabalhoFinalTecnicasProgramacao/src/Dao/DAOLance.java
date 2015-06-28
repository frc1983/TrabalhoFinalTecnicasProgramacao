package Dao;

import Connection.DBConnection;
import Domain.Bem;
import Domain.CategoriaBem;
import Domain.Lance;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class DAOLance implements IDAOLance {

    DBConnection dbConnection = new DBConnection();

    @Override
    public int insert(Lance lance) throws ConnectionException {
        PreparedStatement sta = null;

        try {
            dbConnection.open();
            String sql = "INSERT INTO Lance (IDUSUARIO, IDLOTE, DATA, HORA, VALOR) "
                    + "VALUES (?,?,(SELECT CURRENT_DATE FROM SYSIBM.SYSDUMMY1),(SELECT CURRENT_TIME FROM SYSIBM.SYSDUMMY1),?)";

            sta = dbConnection.getInstance().prepareStatement(sql);
            sta.setInt(1, lance.getUsuario().getId());
            sta.setInt(2, lance.getLote().getId());
            sta.setBigDecimal(3, lance.getValor());

            return sta.executeUpdate();

        } catch (SQLException ex) {
            throw new ConnectionException(ex.getCause());
        } finally {
            try {
                if (sta != null && !sta.isClosed()) {
                    sta.close();
                }
                if (dbConnection != null && dbConnection.isOpen()) {
                    dbConnection.close();
                }
            } catch (SQLException ex) {
                throw new ConnectionException(ex.getCause());
            }
        }
    }

    @Override
    public Collection<Lance> getAllByLot(int idLote) throws PersistenceException, ConnectionException {
        Collection<Lance> lances = new ArrayList<>();
        PreparedStatement sta = null;
        ResultSet res = null;

        try {
            dbConnection.open();

            String sql = "SELECT * FROM LANCE WHERE IDLOTE = ?";

            sta = dbConnection.getInstance().prepareStatement(sql);
            sta.setInt(1, idLote);
            res = sta.executeQuery();
            while (res.next()) {
                lances.add(new Lance(
                        res.getInt("ID"),
                        res.getDate("DATA"),
                        res.getTime("HORA"),
                        res.getBigDecimal("VALOR"),
                        new DAOLote().getById(res.getInt("IDLOTE")),
                        new DAOUsuario().getById(res.getInt("IDUSUARIO"))
                ));
            }
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao consultar Bens.", ex.getCause());
        } finally {
            try {
                if (res != null && !res.isClosed()) {
                    res.close();
                }
                if (sta != null && !sta.isClosed()) {
                    sta.close();
                }
                if (dbConnection != null && dbConnection.isOpen()) {
                    dbConnection.close();
                }
            } catch (SQLException ex) {
                throw new ConnectionException(ex.getCause());
            }
        }

        return lances;
    }

    @Override
    public Lance getHighestByLot(int idLote) throws PersistenceException, ConnectionException {
        PreparedStatement sta = null;
        ResultSet res = null;

        try {
            dbConnection.open();

            String sql = "SELECT * FROM LANCE WHERE IDLOTE = ? ORDER BY VALOR DESC";

            sta = dbConnection.getInstance().prepareStatement(sql);
            sta.setInt(1, idLote);
            res = sta.executeQuery();
            while (res.next()) {
                return new Lance(
                        res.getInt("ID"),
                        res.getDate("DATA"),
                        res.getTime("HORA"),
                        res.getBigDecimal("VALOR"),
                        new DAOLote().getById(res.getInt("IDLOTE")),
                        new DAOUsuario().getById(res.getInt("IDUSUARIO"))
                );
            }
            
            return null;
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao consultar Bens.", ex.getCause());
        } finally {
            try {
                if (res != null && !res.isClosed()) {
                    res.close();
                }
                if (sta != null && !sta.isClosed()) {
                    sta.close();
                }
                if (dbConnection != null && dbConnection.isOpen()) {
                    dbConnection.close();
                }
            } catch (SQLException ex) {
                throw new ConnectionException(ex.getCause());
            }
        }
    }
}
