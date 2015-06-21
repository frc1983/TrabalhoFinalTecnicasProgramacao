package Helpers;

import Domain.CategoriaBem;
import Domain.FormaLance;
import Domain.Natureza;
import Domain.TipoUsuario;
import Domain.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import javafx.util.Pair;

public class PopulateComponents {
    public static Collection<Pair<Integer, String>> populateComboTipoUsuario(Collection<TipoUsuario> itens){
        Collection<Pair<Integer, String>> lista = new ArrayList<>();
        
        for(TipoUsuario item : itens){
            Pair<Integer, String> simplePair = new Pair<>(item.getId(), item.getTipo());
            lista.add(simplePair);
        }
        
        return lista;
    }

    public static Collection<Pair<Integer, String>> populateComboCategoriaBem(Collection<CategoriaBem> categorias) {
        Collection<Pair<Integer, String>> lista = new ArrayList<>();
        
        for(CategoriaBem item : categorias){
            Pair<Integer, String> simplePair = new Pair<>(item.getId(), item.getCategoria());
            lista.add(simplePair);
        }
        
        return lista;
    }

    public static Collection<Pair<Integer, String>> populateComboNatureza(Collection<Natureza> naturezas) {
        Collection<Pair<Integer, String>> lista = new ArrayList<>();
        
        for(Natureza item : naturezas){
            Pair<Integer, String> simplePair = new Pair<>(item.getId(), item.getNome());
            lista.add(simplePair);
        }
        
        return lista;
    }

    public static Collection<Pair<Integer, String>> populateComboFormaLance(Collection<FormaLance> formasLance) {
        Collection<Pair<Integer, String>> lista = new ArrayList<>();
        
        for(FormaLance item : formasLance){
            Pair<Integer, String> simplePair = new Pair<>(item.getId(), item.getForma());
            lista.add(simplePair);
        }
        
        return lista;
    }

    public static Collection<Pair<Integer, String>> populateComboUsuarios(Collection<Usuario> usuarios) {
        Collection<Pair<Integer, String>> lista = new ArrayList<>();
        
        for(Usuario item : usuarios){
            Pair<Integer, String> simplePair = new Pair<>(item.getId(), item.getNome());
            lista.add(simplePair);
        }
        
        return lista;
    }
}
