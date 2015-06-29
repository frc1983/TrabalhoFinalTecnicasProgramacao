package View;

import Domain.Lance;
import Domain.Leilao;
import Enumerators.EnumStatusLeilao;
import Exception.ConnectionException;
import Exception.PersistenceException;
import Facade.LanceFacade;
import Facade.LeilaoFacade;
import Facade.UsuarioFacade;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Principal extends javax.swing.JFrame {

    LeilaoFacade leilaoFacade;
    LanceFacade lanceFacade;
    UsuarioFacade usuarioFacade;

    private final MouseAdapter tableFinalizadosClick;
    private final MouseAdapter tableAndamentoClick;

    private final Timer timerTask;

    public Principal() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.tableFinalizadosClick = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    int rowIndex = tableFinalizados.rowAtPoint(e.getPoint());
                    TableModel model = tableFinalizados.getModel();
                    DialogLance dialogLance = new DialogLance(Integer.parseInt(model.getValueAt(rowIndex, 0).toString()), EnumStatusLeilao.TERMINADO);
                    dialogLance.setVisible(true);
                } catch (ConnectionException | PersistenceException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        this.tableAndamentoClick = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    int rowIndex = tableAndamento.rowAtPoint(e.getPoint());
                    TableModel model = tableAndamento.getModel();
                    DialogLance dialogLance = new DialogLance(Integer.parseInt(model.getValueAt(rowIndex, 0).toString()), EnumStatusLeilao.ATIVO);
                    dialogLance.setVisible(true);
                } catch (ConnectionException | PersistenceException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        leilaoFacade = new LeilaoFacade();
        lanceFacade = new LanceFacade();
        usuarioFacade = new UsuarioFacade();

        timerTask = new Timer(0, (ActionEvent e) -> {
            refreshTableAndamento();
            refreshTableFinalizados();
        });
        timerTask.setDelay(10000);
        timerTask.start();

        tableAndamento.addMouseListener(tableAndamentoClick);
        tableFinalizados.addMouseListener(tableFinalizadosClick);
    }

    private void refreshTableFinalizados() {
        DefaultTableModel model = (DefaultTableModel) tableFinalizados.getModel();
        model.setRowCount(0);
        try {
            for (Leilao leilao : leilaoFacade.buscarTodosPorTipo(EnumStatusLeilao.TERMINADO)) {
                Lance melhor = lanceFacade.buscarMelhorLancePorLote(leilao.getLote().getId(), leilao.getNatureza().getId());
                if (melhor != null) {
                    model.addRow(new Object[]{
                        leilao.getId(),
                        leilao.getLote().getPreco(),
                        usuarioFacade.buscarPorId(melhor.getUsuario().getId()).getNome(),
                        melhor.getValor(),
                        melhor.getData() + " " + melhor.getHora()
                    });
                } else {
                    model.addRow(new Object[]{
                        leilao.getId(),
                        leilao.getLote().getPreco(),
                        " - ",
                        " - ",
                        leilao.getDatatermino() + " " + leilao.getHoratermino()
                    });
                }
            }
        } catch (ConnectionException | PersistenceException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshTableAndamento() {
        DefaultTableModel model = (DefaultTableModel) tableAndamento.getModel();
        model.setRowCount(0);
        try {
            for (Leilao leilao : leilaoFacade.buscarTodosPorTipo(EnumStatusLeilao.ATIVO)) {
                Lance melhor = lanceFacade.buscarMelhorLancePorLote(leilao.getLote().getId(), leilao.getNatureza().getId());
                if (melhor != null) {
                    model.addRow(new Object[]{
                        leilao.getId(),
                        leilao.getLote().getPreco(),
                        usuarioFacade.buscarPorId(melhor.getUsuario().getId()).getNome(),
                        melhor.getValor(),
                        melhor.getData() + " " + melhor.getHora()
                    });
                } else {
                    model.addRow(new Object[]{
                        leilao.getId(),
                        leilao.getLote().getPreco(),
                        " - ",
                        " - ",
                        " - "
                    });
                }
            }
        } catch (ConnectionException | PersistenceException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAndamento = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableFinalizados = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuCadastro = new javax.swing.JMenu();
        menuItemCadastroUsuario = new javax.swing.JMenuItem();
        menuItemCadastroBens = new javax.swing.JMenuItem();
        menuItemCadastroLeilao = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Leilões S.A.");
        setPreferredSize(new java.awt.Dimension(800, 600));

        jLabel1.setText("Leilões em Andamento");

        tableAndamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Valor do Lote", "Usuário do melhor lance", "Melhor Lance", "Data/Hora Lance"
            }
        ));
        jScrollPane1.setViewportView(tableAndamento);

        tableFinalizados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Valor Lote", "Usuário vencedor", "Valor lance vencedor", "Data/Hora do termino"
            }
        ));
        jScrollPane2.setViewportView(tableFinalizados);

        jLabel2.setText("Leilões Finalizados");

        menuCadastro.setText("Cadastros");

        menuItemCadastroUsuario.setText("Usuário");
        menuItemCadastroUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastroUsuarioActionPerformed(evt);
            }
        });
        menuCadastro.add(menuItemCadastroUsuario);

        menuItemCadastroBens.setText("Bens");
        menuItemCadastroBens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastroBensActionPerformed(evt);
            }
        });
        menuCadastro.add(menuItemCadastroBens);

        menuItemCadastroLeilao.setText("Leilão");
        menuItemCadastroLeilao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastroLeilaoActionPerformed(evt);
            }
        });
        menuCadastro.add(menuItemCadastroLeilao);

        menuBar.add(menuCadastro);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1097, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemCadastroUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastroUsuarioActionPerformed
        try {
            DialogCadastroUsuario dialogCadastro = new DialogCadastroUsuario();
            dialogCadastro.setVisible(true);
        } catch (ConnectionException | PersistenceException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_menuItemCadastroUsuarioActionPerformed

    private void menuItemCadastroBensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastroBensActionPerformed
        try {
            DialogCadastroBem dialogCadastro = new DialogCadastroBem();
            dialogCadastro.setVisible(true);
        } catch (ConnectionException | PersistenceException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_menuItemCadastroBensActionPerformed

    private void menuItemCadastroLeilaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastroLeilaoActionPerformed
        try {
            DialogCadastroLeilao dialogCadastro = new DialogCadastroLeilao();
            dialogCadastro.setVisible(true);
        } catch (ConnectionException | PersistenceException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_menuItemCadastroLeilaoActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCadastro;
    private javax.swing.JMenuItem menuItemCadastroBens;
    private javax.swing.JMenuItem menuItemCadastroLeilao;
    private javax.swing.JMenuItem menuItemCadastroUsuario;
    private javax.swing.JTable tableAndamento;
    private javax.swing.JTable tableFinalizados;
    // End of variables declaration//GEN-END:variables
}
