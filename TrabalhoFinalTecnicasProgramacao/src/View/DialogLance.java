package View;

import Domain.Bem;
import Domain.Lance;
import Domain.Leilao;
import Domain.Lote;
import Domain.Usuario;
import Enumerators.EnumFormaLance;
import Enumerators.EnumStatusLeilao;
import Enumerators.EnumTipoUsuario;
import Exception.ConnectionException;
import Exception.PersistenceException;
import Facade.BemFacade;
import Facade.LanceFacade;
import Facade.LeilaoFacade;
import Facade.UsuarioFacade;
import Helpers.PopulateComponents;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class DialogLance extends javax.swing.JFrame {

    private LeilaoFacade leilaoFacade;
    private UsuarioFacade usuarioFacade;
    private BemFacade bemFacade;
    private LanceFacade lanceFacade;

    private DefaultListModel modelLote;
    private DefaultListModel modelLances;

    private Leilao leilao;
    private Collection<Usuario> usuarios;
    private Collection<Bem> bensLote;
    private Collection<Lance> lancesLeilao;
    private Lance maiorLance;
    private Lote lote;

    public DialogLance(int idLeilao, int statusLeilao) throws ConnectionException, PersistenceException {
        initComponents();

        leilaoFacade = new LeilaoFacade();
        usuarioFacade = new UsuarioFacade();
        bemFacade = new BemFacade();
        lanceFacade = new LanceFacade();

        leilao = leilaoFacade.buscarPorId(idLeilao);

        if (leilao.getUsuario().getTipoUsuario().getId() == EnumTipoUsuario.COMPRADOR) {
            usuarios = usuarioFacade.buscarTodosPorTipo(EnumTipoUsuario.VENDEDOR);
        } else {
            usuarios = usuarioFacade.buscarTodosPorTipo(EnumTipoUsuario.COMPRADOR);
        }

        maiorLance = lanceFacade.buscarMelhorLancePorLote(idLeilao, leilao.getNatureza().getId());
        if (maiorLance != null) {
            txtUsuarioVencedor.setText(usuarioFacade.buscarPorId(maiorLance.getUsuario().getId()).getNome());
            txtLanceVencedor.setText(maiorLance.toString());
        }

        lote = leilao.getLote();

        configureComboUsuarios(usuarios);
        configureListItensLote();

        if (statusLeilao == EnumStatusLeilao.ATIVO) {
            if (leilao.getFormalance().getId() == EnumFormaLance.ABERTO) {
                configureListLances(idLeilao);
            } else {
                txtUsuarioVencedor.setText("");
                txtLanceVencedor.setText("");
            }
        }

        txtNatureza.setText(leilao.getNatureza().getNome());
        txtFormaLance.setText(leilao.getFormalance().getForma());

        configureWindow(statusLeilao);
    }

    private void configureComboUsuarios(Collection<Usuario> usuarios) {
        DefaultComboBoxModel mod = new DefaultComboBoxModel(PopulateComponents.populateComboUsuarios(usuarios).toArray());
        cmbUsuarios.setModel(mod);
    }

    private void configureListItensLote() throws PersistenceException, ConnectionException {
        bensLote = bemFacade.buscarTodosPorLote(leilao.getLote().getId());
        modelLote = new DefaultListModel();
        for (Bem item : bensLote) {
            modelLote.addElement(item);
        }
        listBensLote.setModel(modelLote);
    }

    private void configureListLances(int idLeilao) throws ConnectionException, PersistenceException {
        lancesLeilao = lanceFacade.buscarTodosPorLote(idLeilao);
        modelLances = new DefaultListModel();
        for (Lance item : lancesLeilao) {
            modelLances.addElement(item);
        }
        listLancesUsuario.setModel(modelLances);
    }

    private void configureWindow(int statusLeilao) {
        switch (statusLeilao) {
            case EnumStatusLeilao.TERMINADO:
                configureTerminado();
                break;
            case EnumStatusLeilao.ATIVO:
                configureAtivo();
                break;
        }
    }

    private void configureTerminado() {
        panelLance.setVisible(false);
        listLancesUsuario.enable(false);
    }

    private void configureAtivo() {
        panelLance.setVisible(true);
        listLancesUsuario.enable(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listLancesUsuario = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listBensLote = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNatureza = new javax.swing.JTextField();
        txtFormaLance = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtLanceVencedor = new javax.swing.JTextField();
        panelLance = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbUsuarios = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        javax.swing.JToggleButton btnEfetuarLance = new javax.swing.JToggleButton();
        txtLance = new javax.swing.JFormattedTextField();
        javax.swing.JToggleButton btnCancelarLance = new javax.swing.JToggleButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        txtUsuarioVencedor = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setViewportView(listLancesUsuario);

        jLabel1.setText("Lances:");

        jLabel2.setText("Lote:");

        listBensLote.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listBensLote.setEnabled(false);
        jScrollPane2.setViewportView(listBensLote);

        jLabel3.setText("Leilão:");

        jLabel4.setText("Natureza:");

        jLabel5.setText("Forma de lance:");

        txtNatureza.setText("jTextField1");
        txtNatureza.setEnabled(false);

        txtFormaLance.setText("jTextField2");
        txtFormaLance.setEnabled(false);

        jLabel7.setText("Lance vencedor:");

        txtLanceVencedor.setToolTipText("");
        txtLanceVencedor.setEnabled(false);

        jLabel6.setText("Lance:");

        jLabel8.setText("Usuário:");

        cmbUsuarios.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Valor:");

        btnEfetuarLance.setText("Efetuar lance");
        btnEfetuarLance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEfetuarLanceActionPerformed(evt);
            }
        });

        txtLance.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));

        btnCancelarLance.setText("Cancelar lance");
        btnCancelarLance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarLanceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLanceLayout = new javax.swing.GroupLayout(panelLance);
        panelLance.setLayout(panelLanceLayout);
        panelLanceLayout.setHorizontalGroup(
            panelLanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLanceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(btnEfetuarLance, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbUsuarios, 0, 237, Short.MAX_VALUE)
                    .addComponent(jLabel6)
                    .addComponent(txtLance))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelarLance)
                .addGap(178, 178, 178))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelLanceLayout.setVerticalGroup(
            panelLanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLanceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCancelarLance)
                .addGap(8, 8, 8)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(25, 25, 25)
                .addComponent(txtLance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEfetuarLance)
                .addContainerGap())
        );

        btnCancelarLance.getAccessibleContext().setAccessibleName("btnCancelarLance");

        jLabel10.setText("Usuário vencedor:");

        txtUsuarioVencedor.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelLance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(txtNatureza, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(txtFormaLance))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(txtLanceVencedor, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtUsuarioVencedor))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNatureza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFormaLance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtLanceVencedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUsuarioVencedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelLance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarLanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarLanceActionPerformed
        try {
            for (int i = 0; i < listLancesUsuario.getModel().getSize(); i++) {
                if ((Lance) listLancesUsuario.getModel().getElementAt(i) != null) {
                    Lance lance = (Lance) listLancesUsuario.getSelectedValue();
                    lanceFacade.cancelaLance(lance.getId());
                }
            }
            configureListLances(leilao.getId());
        } catch (ConnectionException | PersistenceException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCancelarLanceActionPerformed

    private void btnEfetuarLanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEfetuarLanceActionPerformed
        try {
            BigDecimal preco;
            if ("".equals(txtLance.getText())) {
                preco = new BigDecimal(0);
            } else {
                preco = BigDecimal.valueOf(Double.parseDouble(txtLance.getText().replace(".", "").replace(',', '.')));
            }

            Pair<Integer, String> selectedUsuario = (Pair<Integer, String>) cmbUsuarios.getSelectedItem();
            Usuario u = usuarioFacade.buscarPorId(selectedUsuario.getKey());

            Lance l = new Lance(0, null, null, preco, lote, u);

            int idLance = lanceFacade.cadastrarLance(l);
            if (idLance > 0) {
                configureListLances(leilao.getId());
                txtLance.setText("");
                JOptionPane.showMessageDialog(rootPane, "Lance efetuado", null, JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PersistenceException | ConnectionException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEfetuarLanceActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbUsuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList listBensLote;
    private javax.swing.JList listLancesUsuario;
    private javax.swing.JPanel panelLance;
    private javax.swing.JTextField txtFormaLance;
    private javax.swing.JFormattedTextField txtLance;
    private javax.swing.JTextField txtLanceVencedor;
    private javax.swing.JTextField txtNatureza;
    private javax.swing.JTextField txtUsuarioVencedor;
    // End of variables declaration//GEN-END:variables
}
