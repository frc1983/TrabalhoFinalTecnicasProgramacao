package Dao;

import Connection.ConnectionFactory;
import Connection.IConnection;
import Domain.Natureza;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class DAONatureza implements IDAONatureza {

    @Override
    public Collection<Natureza> getAll() throws ConnectionException, PersistenceException {
        Collection<Natureza> naturezas = new ArrayList<>();
        IConnection conn = null;
        Statement sta = null;
        ResultSet res = null;

        try {
            conn = ConnectionFactory.getInstance();

            String sql = "SELECT * FROM Natureza";

            sta = conn.getConnection().createStatement();
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
                if (conn != null && !conn.getConnection().isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new ConnectionException(ex.getCause());
            }
        }

        return naturezas;
    }
}
