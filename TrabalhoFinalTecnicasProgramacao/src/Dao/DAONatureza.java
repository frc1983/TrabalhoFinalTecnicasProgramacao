package Dao;

import Connection.DBConnection;
import Domain.Natureza;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class DAONatureza implements IDAONatureza {

    DBConnection dbConnection = new DBConnection();
    
    @Override
    public Collection<Natureza> getAll() throws ConnectionException, PersistenceException {
        Collection<Natureza> naturezas = new ArrayList<>();
        Statement sta = null;
        ResultSet res = null;

        try {
            dbConnection.open();

            String sql = "SELECT * FROM Natureza";

            sta = dbConnection.getInstance().createStatement();
            res = sta.executeQuery(sql);
            while (res.next()) {
                naturezas.add(new Natureza(
                        res.getInt("ID"),
                        res.getString("Nome"))
                );
            }
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao consultar naturezas.", ex.getCause());
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

        return naturezas;
    }

    @Override
    public Natureza getById(int id) throws ConnectionException, PersistenceException {
        Natureza natureza = null;
        Statement sta = null;
        ResultSet res = null;

        try {
            dbConnection.open();

            String sql = "SELECT * FROM Natureza";

            sta = dbConnection.getInstance().createStatement();
            res = sta.executeQuery(sql);
            while (res.next()) {
                natureza = new Natureza(
                        res.getInt("ID"),
                        res.getString("Nome")
                );
            }
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao consultar natureza.", ex.getCause());
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

        return natureza;
    }
}
