package View;

import Business.BemBusiness;
import Dao.DAOCategoriaBem;
import Dao.IDAOCategoriaBem;
import Domain.Bem;
import Domain.CategoriaBem;
import Exception.ConnectionException;
import Helpers.PopulateComponents;
import Exception.BemException;
import Exception.PersistenceException;
import Facade.BemFacade;
import Helpers.ComboboxHelper;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import javafx.util.Pair;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class DialogCadastroBem extends javax.swing.JFrame {

    private final MouseAdapter tableItemClick;

    public DialogCadastroBem() throws ConnectionException, PersistenceException {
        IDAOCategoriaBem daoCategoriaBem = new DAOCategoriaBem();
        Collection<CategoriaBem> categorias = daoCategoriaBem.getAll();
        
        this.tableItemClick = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                editData(tableBens.rowAtPoint(e.getPoint()), tableBens);
            }
        };
        initComponents();
        txtId.setVisible(false);
        configureComboCategoriaBem(categorias);
        configureTableBens();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSalvar = new javax.swing.JButton();
        cmbCategoriaBem = new javax.swing.JComboBox();
        txtDescricao = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBens = new javax.swing.JTable();
        txtId = new javax.swing.JTextField();
        btnLimpar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescricaoCompleta = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Bens");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        jLabel1.setText("Categoria:");

        jLabel2.setText("Descrição breve:");

        jLabel3.setText("Descrição completa:");

        tableBens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Descrição", "Descrição completa", "Categoria"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableBens);

        txtId.setEditable(false);
        txtId.setText("0");

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        txtDescricaoCompleta.setColumns(10);
        txtDescricaoCompleta.setLineWrap(true);
        txtDescricaoCompleta.setRows(5);
        jScrollPane2.setViewportView(txtDescricaoCompleta);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1106, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnLimpar)
                                .addGap(160, 160, 160)
                                .addComponent(btnSalvar))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDescricao, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmbCategoriaBem, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbCategoriaBem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpar)
                    .addComponent(btnSalvar)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        cmbCategoriaBem.getAccessibleContext().setAccessibleName("cmbTipoUsuario");
        txtDescricao.getAccessibleContext().setAccessibleName("txtNome");

        setBounds(0, 55, 1148, 654);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            BemFacade facade = new BemFacade();

            Pair<Integer, String> selectedCategoriaBem = (Pair<Integer, String>) cmbCategoriaBem.getSelectedItem();
            int id = Integer.parseInt(txtId.getText());
            String descricao = txtDescricao.getText();
            String descricaoCompleta = txtDescricaoCompleta.getText();

            CategoriaBem categoria = new CategoriaBem(selectedCategoriaBem.getKey(), selectedCategoriaBem.getValue());
            Bem bem = new Bem(id, descricao, descricaoCompleta, categoria);

            BemBusiness.validaBem(bem);

            if (facade.cadastrarBem(bem)) {
                refreshTable();
                clearFields();
                JOptionPane.showMessageDialog(rootPane, "O Bem foi cadastrado", null, JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ConnectionException | BemException | PersistenceException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        clearFields();
    }//GEN-LAST:event_btnLimparActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox cmbCategoriaBem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableBens;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextArea txtDescricaoCompleta;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables

    private void refreshTable() throws ConnectionException, PersistenceException {
        DefaultTableModel model = (DefaultTableModel) tableBens.getModel();
        model.setRowCount(0);
        for (Bem bem : new BemFacade().buscarTodos()) {
            model.addRow(new Object[]{
                bem.getId(), bem.getDescricao(), bem.getDescricaocompleta(), bem.getCategoriaBem().getCategoria()});
        }

    }

    private void clearFields() {
        txtId.setText("0");
        txtDescricao.setText("");
        txtDescricaoCompleta.setText("");
        cmbCategoriaBem.setSelectedIndex(0);
    }

    private void editData(int index, JTable table) {
        int numCols = table.getColumnCount();
        TableModel model = table.getModel();

        for (int j = 0; j < numCols; j++) {
            setFormValues(index, model);
            ComboboxHelper.setSelected(cmbCategoriaBem, model.getValueAt(index, 3).toString());
        }
    }
    
    private void setFormValues(int index, TableModel model) {
        txtId.setText(model.getValueAt(index, 0).toString());
        txtDescricao.setText(model.getValueAt(index, 1).toString());
        txtDescricaoCompleta.setText(model.getValueAt(index, 2).toString());
    }

    private void configureComboCategoriaBem(Collection<CategoriaBem> categorias) {
        DefaultComboBoxModel mod = new DefaultComboBoxModel(PopulateComponents.populateComboCategoriaBem(categorias).toArray());
        cmbCategoriaBem.setModel(mod);
    }
    
    private void configureTableBens() throws PersistenceException {
        tableBens.setRowSelectionAllowed(true);
        tableBens.setColumnSelectionAllowed(false);
        try {
            refreshTable();
            tableBens.addMouseListener(this.tableItemClick);
        } catch (ConnectionException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
}
