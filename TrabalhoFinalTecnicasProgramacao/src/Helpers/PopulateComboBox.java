/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import Model.TipoUsuario;
import java.util.ArrayList;
import java.util.Collection;
import javafx.util.Pair;

/**
 *
 * @author Fabio
 */
public class PopulateComboBox {
    public static Collection<Pair<Integer, String>> populateTipoUsuario(Collection<TipoUsuario> itens){
        Collection<Pair<Integer, String>> lista = new ArrayList<>();
        
        for(TipoUsuario item : itens){
            Pair<Integer, String> simplePair = new Pair<>(item.getId(), item.getTipo());
            lista.add(simplePair);
        }
        
        return lista;
    }
}
