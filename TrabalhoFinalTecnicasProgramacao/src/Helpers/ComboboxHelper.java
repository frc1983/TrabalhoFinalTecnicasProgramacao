package Helpers;

import javafx.util.Pair;
import javax.swing.JComboBox;

public class ComboboxHelper {

    public static void setSelected(JComboBox combo, String selectedValue) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            Pair<Integer, String> selected = (Pair<Integer, String>) combo.getModel().getElementAt(i);
            if (selected.getValue().equals(selectedValue)) {
                combo.setSelectedIndex(i);
            }
        }
    }
}
