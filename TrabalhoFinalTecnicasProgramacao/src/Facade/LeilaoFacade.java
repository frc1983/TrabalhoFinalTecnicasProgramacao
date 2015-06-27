package Facade;

import Dao.DAOLeilao;
import Domain.Leilao;
import Exception.ConnectionException;
import Exception.PersistenceException;
import java.util.Collection;

public class LeilaoFacade {
    private final DAOLeilao daoLeilao;
    
    public LeilaoFacade(){
        daoLeilao = new DAOLeilao();
    }
    
    public Collection<Leilao> buscarTodosPorTipo(int status) throws ConnectionException, PersistenceException{
        return daoLeilao.getAllByStatus(status);
    }
    
    public boolean cadastrarLeilao(Leilao leilao) throws ConnectionException, PersistenceException{
        return daoLeilao.insert(leilao);
    }
}
