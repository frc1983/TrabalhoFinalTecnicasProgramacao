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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class DAOLeilao implements IDAOLeilao {

    Connection conn = null;
    ResultSet res = null;
    Statement sta = null;
    PreparedStatement preparedsta = null;
    Collection<Leilao> leiloes;

    @Override
    public boolean insert(Leilao leilao) throws ConnectionException, PersistenceException {

        try {
            conn = DBConnection.getInstance();
            DBConnection.openTransaction();

            DAOLote daoLote = new DAOLote();
            Lote lote = leilao.getLote();
            int idLote = daoLote.insert(lote);
            lote.setId(idLote);

            String sql = "INSERT INTO Leilao (IDUSUARIO, IDLOTE, IDNATUREZA, IDFORMALANCE, DATAINICIO, DATATERMINO, HORAINICIO, HORATERMINO) VALUES (?,?,?,?,?,?,?,?)";

            preparedsta = conn.prepareStatement(sql);
            preparedsta.setInt(1, leilao.getUsuario().getId());
            preparedsta.setInt(2, leilao.getLote().getId());
            preparedsta.setInt(3, leilao.getNatureza().getId());
            preparedsta.setInt(4, leilao.getFormalance().getId());
            preparedsta.setDate(5, leilao.getDatainicio());
            preparedsta.setDate(6, leilao.getDatatermino());
            preparedsta.setTime(7, leilao.getHorainicio());
            preparedsta.setTime(8, leilao.getHoratermino());

            preparedsta.executeUpdate();

            DBConnection.closeTransaction();
            conn.commit();

        } catch (ConnectionException | SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                throw new PersistenceException("Erro ao inserir Leilão.", ex1.getCause());
            }
            throw new PersistenceException("Erro ao inserir Leilão.", ex.getCause());
        } finally {
            DBConnection.close(conn, preparedsta, null);
        }

        return true;
    }

    @Override
    public Collection<Leilao> getAllByStatus(int status) throws ConnectionException, PersistenceException {
        try {
            leiloes = new ArrayList<>();
            String sql = "";

            if (status == EnumStatusLeilao.ATIVO) {
                sql = "SELECT * "
                        + "FROM Leilao "
                        + "WHERE DATE(DATAINICIO) >= CURRENT DATE AND DATE(DATATERMINO) <= CURRENT DATE "
                        + "AND CURRENT TIME >= TIME(HORAINICIO) AND CURRENT TIME < TIME(HORATERMINO)";
            } else if (status == EnumStatusLeilao.TERMINADO) {
                sql = "SELECT * FROM Leilao "
                        + "WHERE ID NOT IN ("
                        + "    SELECT ID"
                        + "    FROM Leilao "
                        + "    WHERE DATE(DATAINICIO) >= CURRENT DATE AND TIME(HORATERMINO) > CURRENT TIME)";
            }

            conn = DBConnection.getInstance();
            preparedsta = conn.prepareStatement(sql);
            res = preparedsta.executeQuery();
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
            DBConnection.close(conn, preparedsta, res);
        }

        return leiloes;
    }

    @Override
    public Leilao getById(int id) throws PersistenceException, ConnectionException {
        try {
            String sql = "SELECT * FROM LEILAO WHERE id = ?";

            conn = DBConnection.getInstance();
            preparedsta = conn.prepareStatement(sql);
            preparedsta.setInt(1, id);
            res = preparedsta.executeQuery();
            while (res.next()) {
                return new Leilao(
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

            return null;
        } catch (ConnectionException | SQLException ex) {
            throw new PersistenceException("Erro ao consultar Leilão.", ex.getCause());
        } finally {
            DBConnection.close(conn, preparedsta, res);
        }
    }
}
