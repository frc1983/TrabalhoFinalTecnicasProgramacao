package Dao;

import Connection.DBConnection;
import Domain.Bem;
import Domain.CategoriaBem;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class DAOBem implements IDAOBem {

    Connection conn = null;
    ResultSet res = null;
    Statement sta = null;
    PreparedStatement preparedsta = null;
    Collection<Bem> bens;

    @Override
    public Collection<Bem> getAll() throws ConnectionException, PersistenceException {
        try {
            bens = new ArrayList<>();
            String sql = "SELECT b.*, cb.ID as IdCategoria, cb.CATEGORIA FROM Bem b INNER JOIN CategoriaBem cb ON b.IdCategoriaBem = cb.Id";

            conn = DBConnection.getInstance();
            sta = conn.createStatement();
            res = sta.executeQuery(sql);
            while (res.next()) {
                bens.add(new Bem(
                        res.getInt("ID"),
                        res.getString("DESCRICAO"),
                        res.getString("DESCRICAOCOMPLETA"),
                        new CategoriaBem(res.getInt("IdCategoria"), res.getString("CATEGORIA"))
                ));
            }
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao consultar Bens.", ex.getCause());
        } finally {
            DBConnection.close(conn, sta, res);
        }

        return bens;
    }

    @Override
    public int insert(Bem bem) throws ConnectionException {
        try {
            String sql = "INSERT INTO Bem (IDCATEGORIABEM, DESCRICAO, DESCRICAOCOMPLETA) VALUES (?,?,?)";

            if (bem.getId() != 0) {
                sql = "UPDATE Bem SET IDCATEGORIABEM = ?, DESCRICAO = ?, DESCRICAOCOMPLETA = ? WHERE ID = ?";
            }

            conn = DBConnection.getInstance();
            preparedsta = conn.prepareStatement(sql);
            preparedsta.setInt(1, bem.getCategoriaBem().getId());
            preparedsta.setString(2, bem.getDescricao());
            preparedsta.setString(3, bem.getDescricaocompleta());

            if (bem.getId() != 0) {
                preparedsta.setInt(4, bem.getId());
            }

            return preparedsta.executeUpdate();

        } catch (SQLException ex) {
            throw new ConnectionException(ex.getCause());
        } finally {
            DBConnection.close(conn, preparedsta, null);
        }
    }

    @Override
    public Collection<Bem> getAllFree() throws PersistenceException, ConnectionException {
        try {
            bens = new ArrayList<>();
            String sql = "select b.ID as IdBem, b.IDCATEGORIABEM, b.DESCRICAO, b.DESCRICAOCOMPLETA, cb.CATEGORIA from bem b\n"
                    + "INNER JOIN CategoriaBem cb ON b.IdCategoriaBem = cb.Id\n"
                    + "where b.ID NOT IN (select idbem from lote_bem)";

            conn = DBConnection.getInstance();
            sta = conn.createStatement();
            res = sta.executeQuery(sql);
            while (res.next()) {
                bens.add(new Bem(
                        res.getInt("IdBem"),
                        res.getString("DESCRICAO"),
                        res.getString("DESCRICAOCOMPLETA"),
                        new CategoriaBem(res.getInt("IDCATEGORIABEM"), res.getString("CATEGORIA"))
                ));
            }
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao consultar Bens.", ex.getCause());
        } finally {
            DBConnection.close(conn, sta, null);
        }

        return bens;
    }

    @Override
    public Collection<Bem> getAllById(int idBem) throws PersistenceException, ConnectionException {
        try {
            bens = new ArrayList<>();
            String sql = "SELECT b.*, cb.ID as IdCategoria, cb.CATEGORIA FROM Bem b INNER JOIN CategoriaBem cb ON b.IdCategoriaBem = cb.Id WHERE id = ?";
            conn = DBConnection.getInstance();
            sta = conn.prepareStatement(sql);
            preparedsta.setInt(1, idBem);
            res = preparedsta.executeQuery();
            while (res.next()) {
                bens.add(new Bem(
                        res.getInt("ID"),
                        res.getString("DESCRICAO"),
                        res.getString("DESCRICAOCOMPLETA"),
                        new CategoriaBem(res.getInt("IdCategoria"), res.getString("CATEGORIA"))
                ));
            }
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao consultar Bens.", ex.getCause());
        } finally {
            DBConnection.close(conn, preparedsta, null);
        }

        return bens;
    }

    @Override
    public Collection<Bem> getAllByLot(int id) throws PersistenceException, ConnectionException {
        try {
            bens = new ArrayList<>();
            String sql = "select b.ID as IdBem, b.DESCRICAO, b.DESCRICAOCOMPLETA, cb.ID as IDCATEGORIA, cb.CATEGORIA from Lote l "
                    + "inner join lote_bem lb on l.ID = lb.IDLOTE "
                    + "inner join bem b on lb.IDBEM = b.ID "
                    + "inner join categoriabem cb on b.IDCATEGORIABEM = cb.ID "
                    + "where l.ID = ?";

            conn = DBConnection.getInstance();
            preparedsta = conn.prepareStatement(sql);
            preparedsta.setInt(1, id);
            res = preparedsta.executeQuery();
            while (res.next()) {
                bens.add(new Bem(
                        res.getInt("IdBem"),
                        res.getString("DESCRICAO"),
                        res.getString("DESCRICAOCOMPLETA"),
                        new CategoriaBem(res.getInt("IDCATEGORIA"), res.getString("CATEGORIA"))
                ));
            }
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao consultar Bens.", ex.getCause());
        } finally {
            DBConnection.close(conn, preparedsta, null);
        }

        return bens;
    }
}
