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

    Connection conn = null;
    ResultSet res = null;
    Statement sta = null;
    PreparedStatement preparedsta = null;

    @Override
    public int insert(Lote lote) throws ConnectionException, PersistenceException {
        PreparedStatement sta2 = null;
        ResultSet generatedKeys;
        int idLote = 0;

        try {
            String sql = "INSERT INTO Lote (PRECO) VALUES (?)";

            conn = DBConnection.getInstance();
            preparedsta = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedsta.setBigDecimal(1, lote.getPreco());

            preparedsta.executeUpdate();
            generatedKeys = preparedsta.getGeneratedKeys();

            if (generatedKeys.next()) {
                idLote = generatedKeys.getInt(1);
            }

            sql = "INSERT INTO LOTE_BEM (IDLOTE, IDBEM) VALUES (?,?)";
            sta2 = conn.prepareStatement(sql);
            for (Bem bem : lote.getBens()) {
                sta2.setInt(1, idLote);
                sta2.setInt(2, bem.getId());
                sta2.executeUpdate();
            }

            return idLote;
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao inserir Lote.", ex.getCause());
        } finally {
            try {
                if (sta2 != null && !sta2.isClosed()) {
                    sta2.close();
                }
                DBConnection.close(conn, preparedsta, null);
            } catch (SQLException ex) {
                throw new ConnectionException(ex.getCause());
            }
        }
    }

    @Override
    public Lote getById(int id) throws ConnectionException, PersistenceException {
        try {
            String sql = "SELECT * FROM LOTE WHERE id = ?";

            conn = DBConnection.getInstance();
            preparedsta = conn.prepareStatement(sql);
            preparedsta.setInt(1, id);
            res = preparedsta.executeQuery();
            while (res.next()) {
                return new Lote(
                        res.getInt("ID"),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        res.getBigDecimal("PRECO")
                );
            }
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao consultar Lote.", ex.getCause());
        } finally {
            DBConnection.close(conn, preparedsta, res);
        }

        return null;
    }
}
