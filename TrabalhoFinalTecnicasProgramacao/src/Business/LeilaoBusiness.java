package Business;

import Domain.Leilao;
import Domain.Usuario;
import Enumerators.EnumTipoUsuario;
import Exception.ConnectionException;
import Exception.LeilaoException;
import Exception.PersistenceException;
import Facade.UsuarioFacade;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

public class LeilaoBusiness {

    public static void validaLeilao(Leilao leilao) throws LeilaoException {
        if (leilao.getDatainicio() == null || leilao.getDatainicio().getDay() < new Date(System.currentTimeMillis()).getDay()) {
            throw new LeilaoException("Data de início deve ser maior ou igual a hoje.");
        }
        if (leilao.getDatatermino() == null || leilao.getDatainicio().getDay() > leilao.getDatatermino().getDay()) {
            throw new LeilaoException("Data de término deve ser maior ou igual a de início.");
        }
        if (leilao.getHorainicio() == null) {
            throw new LeilaoException("Hora de início deve ser preenchida.");
        }
        if (leilao.getHoratermino() == null) {
            throw new LeilaoException("Hora de término deve ser preenchida.");
        }
    }

    public static Collection<Usuario> configuraUsuariosLance(Leilao leilao) throws ConnectionException, PersistenceException {
        Collection<Usuario> usuarios = new ArrayList<>();
        if (leilao.getUsuario().getTipoUsuario().getId() == EnumTipoUsuario.COMPRADOR) {
            usuarios = new UsuarioFacade().buscarTodosPorTipo(EnumTipoUsuario.VENDEDOR);
        } else {
            usuarios = new UsuarioFacade().buscarTodosPorTipo(EnumTipoUsuario.COMPRADOR);
        }

        return usuarios;
    }
}
