package View;

import Domain.Bem;
import Domain.FormaLance;
import Domain.Natureza;
import Domain.Usuario;
import Enumerators.EnumTipoUsuario;
import Exception.ConnectionException;
import Exception.PersistenceException;
import Facade.BemFacade;
import Facade.FormaLanceFacade;
import Facade.NaturezaFacade;
import Facade.UsuarioFacade;
import Helpers.PopulateComponents;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

public class DialogCadastroLeilao extends javax.swing.JFrame {
    
    NaturezaFacade naturezaFacade;
    FormaLanceFacade formaLanceFacade;
    UsuarioFacade usuarioFacade;
    BemFacade bemFacade;
    
    Collection<Bem> bensLote = new ArrayList<>();
    Collection<Bem> bensDisponiveis = new ArrayList<>();
    
    DefaultListModel modelLote;
    DefaultListModel modelDisponiveis;
    
    public DialogCadastroLeilao() throws ConnectionException, PersistenceException {
        initComponents();
        
        listBens.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listItensLote.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        
        naturezaFacade = new NaturezaFacade();
        Collection<Natureza> naturezas = naturezaFacade.buscarTodos();
        
        formaLanceFacade = new FormaLanceFacade();
        Collection<FormaLance> formasLance = formaLanceFacade.buscarTodos();
        
        usuarioFacade = new UsuarioFacade();        
        bemFacade = new BemFacade();
        
        configureComboNatureza(naturezas);
        configureComboFormaLance(formasLance);        
        
        Collection<Usuario> usuarios = usuarioFacade.buscarTodosPorTipo(EnumTipoUsuario.COMPRADOR);
        configureComboUsuarios(usuarios);
        
        bensDisponiveis = bemFacade.buscarTodosDisponiveis();
        configureListItensBens();
        configureListItensLote();
    }
    
    private void configureComboNatureza(Collection<Natureza> naturezas) {
        DefaultComboBoxModel mod = new DefaultComboBoxModel(PopulateComponents.populateComboNatureza(naturezas).toArray());
        cmbNatureza.setModel(mod);
        
        cmbNatureza.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Collection<Usuario> usuarios = new ArrayList<>();
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        Pair<Integer, String> item = (Pair<Integer, String>) e.getItem();
                        switch (item.getValue().toLowerCase()) {
                            case "oferta":
                                usuarios = usuarioFacade.buscarTodosPorTipo(EnumTipoUsuario.COMPRADOR);
                                break;
                            case "demanda":
                                usuarios = usuarioFacade.buscarTodosPorTipo(EnumTipoUsuario.VENDEDOR);
                        }
                        
                        configureComboUsuarios(usuarios);
                    } catch (ConnectionException | PersistenceException ex) {
                        Logger.getLogger(DialogCadastroLeilao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    
    private void configureComboFormaLance(Collection<FormaLance> formasLance) {
        DefaultComboBoxModel mod = new DefaultComboBoxModel(PopulateComponents.populateComboFormaLance(formasLance).toArray());
        cmbFormaLance.setModel(mod);
    }
    
    private void configureComboUsuarios(Collection<Usuario> usuarios) {
        DefaultComboBoxModel mod = new DefaultComboBoxModel(PopulateComponents.populateComboUsuarios(usuarios).toArray());
        cmbUsuario.setModel(mod);
    }
    
    private void configureListItensBens() {
        modelDisponiveis = new DefaultListModel();
        for (Bem item : bensDisponiveis) {
            modelDisponiveis.addElement(item);
        }
        listBens.setModel(modelDisponiveis);
    }
    
    private void configureListItensLote() {
        modelLote = new DefaultListModel();
        for (Bem item : bensLote) {
            modelLote.addElement(item);
        }
        listItensLote.setModel(modelLote);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbNatureza = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbFormaLance = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbUsuario = new javax.swing.JComboBox();
        txtPreco = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDataInicio = new javax.swing.JFormattedTextField();
        txtHoraInicio = new javax.swing.JFormattedTextField();
        txtDataTermino = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        txtHoraTermino = new javax.swing.JFormattedTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listItensLote = new javax.swing.JList();
        btnAdicionarLote = new javax.swing.JButton();
        btnRemoverLote = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listBens = new javax.swing.JList();
        btnSalvarLeilao = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cmbNatureza.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Natureza:");

        jLabel2.setText("Forma de Lance:");

        cmbFormaLance.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Data de Início:");

        jLabel4.setText("Data Encerramento:");

        jLabel5.setText("Usuário Responsável:");

        jLabel6.setText("Preço do Lote:");

        jLabel7.setText("R$");

        cmbUsuario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtPreco.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));

        jLabel8.setText("Hora de Início:");

        txtDataInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        txtHoraInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance())));

        txtDataTermino.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        jLabel9.setText("Hora do Término:");

        txtHoraTermino.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance())));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel10.setText("Lote:");

        jLabel13.setText("Itens do Lote:");

        jScrollPane2.setViewportView(listItensLote);

        btnAdicionarLote.setText("Adicionar");
        btnAdicionarLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarLoteActionPerformed(evt);
            }
        });

        btnRemoverLote.setText("Remover");
        btnRemoverLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverLoteActionPerformed(evt);
            }
        });

        jLabel14.setText("<--------");

        jLabel15.setText("--------->");

        jScrollPane3.setViewportView(listBens);

        btnSalvarLeilao.setText("Salvar Leilão");

        jLabel16.setText("Bens Disponíveis:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addComponent(txtDataInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                .addComponent(txtDataTermino))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel8)
                                .addComponent(txtHoraInicio)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtHoraTermino)))
                        .addComponent(cmbNatureza, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbFormaLance, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(9, 9, 9)
                        .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel13)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAdicionarLote)
                                    .addComponent(btnRemoverLote)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel14))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalvarLeilao)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbNatureza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbFormaLance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDataTermino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHoraTermino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnAdicionarLote)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)
                                .addGap(63, 63, 63)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemoverLote)
                                .addGap(36, 36, 36)))))
                .addGap(18, 18, 18)
                .addComponent(btnSalvarLeilao)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarLoteActionPerformed
        for(int i = 0; i < listBens.getModel().getSize(); i++){
            if(((Bem)listBens.getModel().getElementAt(i)).getDescricao().equals(((Bem)listBens.getSelectedValue()).getDescricao())){
                modelLote.addElement((Bem)listBens.getSelectedValue());
                modelDisponiveis.removeElement((Bem)listBens.getSelectedValue());
            }
        }
    }//GEN-LAST:event_btnAdicionarLoteActionPerformed

    private void btnRemoverLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverLoteActionPerformed
        for(int i = 0; i < listItensLote.getModel().getSize(); i++){
            if(((Bem)listItensLote.getModel().getElementAt(i)).getDescricao().equals(((Bem)listItensLote.getSelectedValue()).getDescricao())){
                modelDisponiveis.addElement((Bem)listItensLote.getSelectedValue());
                modelLote.removeElement((Bem)listItensLote.getSelectedValue());
            }
        }
    }//GEN-LAST:event_btnRemoverLoteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarLote;
    private javax.swing.JButton btnRemoverLote;
    private javax.swing.JButton btnSalvarLeilao;
    private javax.swing.JComboBox cmbFormaLance;
    private javax.swing.JComboBox cmbNatureza;
    private javax.swing.JComboBox cmbUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList listBens;
    private javax.swing.JList listItensLote;
    private javax.swing.JFormattedTextField txtDataInicio;
    private javax.swing.JFormattedTextField txtDataTermino;
    private javax.swing.JFormattedTextField txtHoraInicio;
    private javax.swing.JFormattedTextField txtHoraTermino;
    private javax.swing.JFormattedTextField txtPreco;
    // End of variables declaration//GEN-END:variables
}
