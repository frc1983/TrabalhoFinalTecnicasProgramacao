package Dao;

import Connection.DBConnection;
import Domain.Lance;
import Enumerators.EnumNatureza;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class DAOLance implements IDAOLance {
    Connection conn = null;
    ResultSet res = null;
    Statement sta = null;
    PreparedStatement preparedsta = null;
    Collection<Lance> lances;

    @Override
    public int insert(Lance lance) throws ConnectionException {
        try {
            String sql = "INSERT INTO Lance (IDUSUARIO, IDLOTE, DATA, HORA, VALOR) "
                    + "VALUES (?,?,(SELECT CURRENT_DATE FROM SYSIBM.SYSDUMMY1),(SELECT CURRENT_TIME FROM SYSIBM.SYSDUMMY1),?)";

            conn = DBConnection.getInstance();
            preparedsta = conn.prepareStatement(sql);
            preparedsta.setInt(1, lance.getUsuario().getId());
            preparedsta.setInt(2, lance.getLote().getId());
            preparedsta.setBigDecimal(3, lance.getValor());

            return preparedsta.executeUpdate();

        } catch (ConnectionException | SQLException ex) {
            throw new ConnectionException(ex.getCause());
        } finally {
            DBConnection.close(conn, preparedsta, null);
        }
    }

    @Override
    public Collection<Lance> getAllByLot(int idLote) throws PersistenceException, ConnectionException {
        try {
            lances = new ArrayList<>();
            String sql = "SELECT * FROM LANCE WHERE IDLOTE = ?";

            conn = DBConnection.getInstance();
            preparedsta = conn.prepareStatement(sql);
            preparedsta.setInt(1, idLote);
            res = preparedsta.executeQuery();
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
            throw new PersistenceException("Erro ao consultar Lances.", ex.getCause());
        } finally {
            DBConnection.close(conn, preparedsta, null);
        }

        return lances;
    }

    @Override
    public Lance getBestByLot(int idLote, int idNatureza) throws PersistenceException, ConnectionException {
        try {
            String sql = "";
            if(idNatureza == EnumNatureza.OFERTA)
                sql = "SELECT * FROM LANCE WHERE IDLOTE = ? ORDER BY VALOR ASC";
            else if(idNatureza == EnumNatureza.DEMANDA)
                sql = "SELECT * FROM LANCE WHERE IDLOTE = ? ORDER BY VALOR DESC";

            conn = DBConnection.getInstance();
            preparedsta = conn.prepareStatement(sql);
            preparedsta.setInt(1, idLote);
            res = preparedsta.executeQuery();
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
            throw new PersistenceException("Erro ao consultar Lances.", ex.getCause());
        } finally {
            DBConnection.close(conn, preparedsta, res);
        }
    }

    @Override
    public void remove(int idLance) throws PersistenceException, ConnectionException {
        try {
            String sql = "DELETE FROM LANCE WHERE ID = ?";

            conn = DBConnection.getInstance();
            preparedsta = conn.prepareStatement(sql);
            preparedsta.setInt(1, idLance);
            preparedsta.execute();
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao remove Lance.", ex.getCause());
        } finally {
            DBConnection.close(conn, preparedsta, null);
        }
    }
}
