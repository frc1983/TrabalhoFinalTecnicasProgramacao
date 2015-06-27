package Dao;

import Connection.DBConnection;
import Domain.Bem;
import Domain.Lote;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOLote implements IDAOLote {

    DBConnection dbConnection = new DBConnection();

    @Override
    public int insert(Connection dbConnection, Lote lote) throws ConnectionException, PersistenceException {
        PreparedStatement sta = null;
        PreparedStatement sta2 = null;
        ResultSet generatedKeys;
        int idLote = 0;

        try {
            String sql = "INSERT INTO Lote (PRECO) VALUES (?)";
            
            sta = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            sta.setBigDecimal(1, lote.getPreco());

            sta.executeUpdate();
            generatedKeys = sta.getGeneratedKeys();
            
            if (generatedKeys.next())
	            idLote = generatedKeys.getInt(1);

            sql = "INSERT INTO LOTE_BEM (IDLOTE, IDBEM) VALUES (?,?)";
            sta2 = dbConnection.prepareStatement(sql);
            for (Bem bem : lote.getBens()) {                
                sta2.setInt(1, idLote);
                sta2.setInt(2, bem.getId());
                sta2.executeUpdate();
            }

            return idLote;
        } catch (SQLException ex) {
            throw new ConnectionException(ex.getCause());
        } finally {
            try {
                if (sta != null && !sta.isClosed()) {
                    sta.close();
                }
                if (sta2 != null && !sta2.isClosed()) {
                    sta2.close();
                }
                if (dbConnection != null && dbConnection.isClosed()) {
                    dbConnection.close();
                }
            } catch (SQLException ex) {
                throw new ConnectionException(ex.getCause());
            }
        }
    }

    @Override
    public Lote getById(int id) throws ConnectionException, PersistenceException {
        Lote lote = null;
        PreparedStatement sta = null;
        ResultSet res = null;

        try {
            dbConnection.open();

            String sql = "SELECT * FROM LOTE WHERE id = ?";

            sta = dbConnection.getInstance().prepareStatement(sql);
            sta.setInt(1, id);
            res = sta.executeQuery();
            while (res.next()) {
                lote = new Lote(
                        res.getInt("ID"),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        res.getBigDecimal("PRECO")
                );
            }
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao consultar Lote.", ex.getCause());
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

        return lote;
    }
}
