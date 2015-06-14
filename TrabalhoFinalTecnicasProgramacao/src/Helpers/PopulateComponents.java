package Helpers;

import Domain.CategoriaBem;
import Domain.TipoUsuario;
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
}
