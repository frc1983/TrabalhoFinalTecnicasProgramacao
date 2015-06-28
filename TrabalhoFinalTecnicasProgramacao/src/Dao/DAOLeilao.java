package Dao;

import Connection.DBConnection;
import Domain.Leilao;
import Domain.Lote;
import Exception.ConnectionException;
import Enumerators.EnumStatusLeilao;
import Exception.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class DAOLeilao implements IDAOLeilao {

    DBConnection dbConnection = new DBConnection();

    @Override
    public boolean insert(Leilao leilao) throws ConnectionException, PersistenceException {

        PreparedStatement staLeilao = null;

        try {
            dbConnection.open();
            dbConnection.openTransaction();
            
            Connection instance = dbConnection.getInstance();

            DAOLote daoLote = new DAOLote();
            Lote lote = leilao.getLote();
            int idLote = daoLote.insert(instance, lote);
            lote.setId(idLote);

            String sql = "INSERT INTO Leilao (IDUSUARIO, IDLOTE, IDNATUREZA, IDFORMALANCE, DATAINICIO, DATATERMINO, HORAINICIO, HORATERMINO) VALUES (?,?,?,?,?,?,?,?)";

            staLeilao = instance.prepareStatement(sql);
            staLeilao.setInt(1, leilao.getUsuario().getId());
            staLeilao.setInt(2, leilao.getLote().getId());
            staLeilao.setInt(3, leilao.getNatureza().getId());
            staLeilao.setInt(4, leilao.getFormalance().getId());
            staLeilao.setDate(5, leilao.getDatainicio());
            staLeilao.setDate(6, leilao.getDatatermino());
            staLeilao.setTime(7, leilao.getHorainicio());
            staLeilao.setTime(8, leilao.getHoratermino());

            int idLeilao = staLeilao.executeUpdate();

            dbConnection.closeTransaction();
            dbConnection.commit();

        } catch (ConnectionException | SQLException ex) {
            dbConnection.rollback();
            throw new ConnectionException(ex.getCause());
        } finally {
            try {
                if (staLeilao != null && !staLeilao.isClosed()) {
                    staLeilao.close();
                }
                if (dbConnection != null && dbConnection.isOpen()) {
                    dbConnection.close();
                }
            } catch (SQLException ex) {
                throw new ConnectionException(ex.getCause());
            }
        }

        return true;
    }

    @Override
    public Collection<Leilao> getAllByStatus(int status) throws ConnectionException, PersistenceException {
        Collection<Leilao> leiloes = new ArrayList<>();
        PreparedStatement sta = null;
        ResultSet res = null;

        try {
            String sql = "";

            if (status == EnumStatusLeilao.ATIVO) {
                sql = "SELECT * FROM Leilao WHERE DATE(DATAINICIO) >= CURRENT DATE AND DATE(DATATERMINO) <= CURRENT DATE";
            } else if (status == EnumStatusLeilao.TERMINADO) {
                sql = "SELECT * FROM Leilao WHERE DATE(DATATERMINO) < CURRENT DATE";
            }
            
            dbConnection.open();
            sta = dbConnection.getInstance().prepareStatement(sql);
            res = sta.executeQuery();
            while (res.next()) {
                leiloes.add(new Leilao(
                        res.getInt("ID"),
                        new DAOUsuario().getById(res.getInt("IDUSUARIO")),
                        new DAOFormaLance().getById(res.getInt("IDFORMALANCE")),
                        new DAOLote().getById(res.getInt("IDLOTE")),
                        new DAONatureza().getById(res.getInt("IDNATUREZA")),
                        res.getDate("DATAINICIO"),
                        res.getDate("DATATERMINO"),
                        res.getTime("HORAINICIO"),
                        res.getTime("HORATERMINO")
                ));
            }
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao consultar Leilões.", ex.getCause());
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

        return leiloes;
    }

    @Override
    public Leilao getById(int id) throws PersistenceException, ConnectionException {
        Leilao leilao = null;
        PreparedStatement sta = null;
        ResultSet res = null;

        try {
            dbConnection.open();

            String sql = "SELECT * FROM LEILAO WHERE id = ?";

            sta = dbConnection.getInstance().prepareStatement(sql);
            sta.setInt(1, id);
            res = sta.executeQuery();
            while (res.next()) {
                leilao = new Leilao(
                        res.getInt("ID"),
                        new DAOUsuario().getById(res.getInt("IDUSUARIO")),
                        new DAOFormaLance().getById(res.getInt("IDFORMALANCE")),
                        new DAOLote().getById(res.getInt("IDLOTE")),
                        new DAONatureza().getById(res.getInt("IDNATUREZA")),
                        res.getDate("DATAINICIO"),
                        res.getDate("DATATERMINO"),
                        res.getTime("HORAINICIO"),
                        res.getTime("HORATERMINO")
                );
            }
            
            return leilao;
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
    }
}
