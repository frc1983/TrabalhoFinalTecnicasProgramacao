package View;

public class Principal extends javax.swing.JFrame {

    public Principal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        menuBar = new javax.swing.JMenuBar();
        menuCadastro = new javax.swing.JMenu();
        menuItemCadastroUsuario = new javax.swing.JMenuItem();
        menuItemCadastroBens = new javax.swing.JMenuItem();
        menuItemCadastroLeilao = new javax.swing.JMenuItem();
        menuLeiloes = new javax.swing.JMenu();
        menuItemLeilaoAndamento = new javax.swing.JMenuItem();
        menuItemLeilaoFinalizado = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Leilões S.A.");
        setPreferredSize(new java.awt.Dimension(800, 600));

        menuCadastro.setText("Cadastros");

        menuItemCadastroUsuario.setText("Usuário");
        menuCadastro.add(menuItemCadastroUsuario);

        menuItemCadastroBens.setText("Bens");
        menuCadastro.add(menuItemCadastroBens);

        menuItemCadastroLeilao.setText("Leilão");
        menuCadastro.add(menuItemCadastroLeilao);

        menuBar.add(menuCadastro);
        menuCadastro.getAccessibleContext().setAccessibleName("Cadastros");

        menuLeiloes.setText("Leilões");

        menuItemLeilaoAndamento.setText("Em andamento");
        menuLeiloes.add(menuItemLeilaoAndamento);

        menuItemLeilaoFinalizado.setText("Finalizado");
        menuItemLeilaoFinalizado.setToolTipText("");
        menuLeiloes.add(menuItemLeilaoFinalizado);

        menuBar.add(menuLeiloes);

        jMenu1.setText("Detalhes");

        jMenuItem2.setText("Em andamento");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Finalizado");
        jMenu1.add(jMenuItem3);

        menuBar.add(jMenu1);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCadastro;
    private javax.swing.JMenuItem menuItemCadastroBens;
    private javax.swing.JMenuItem menuItemCadastroLeilao;
    private javax.swing.JMenuItem menuItemCadastroUsuario;
    private javax.swing.JMenuItem menuItemLeilaoAndamento;
    private javax.swing.JMenuItem menuItemLeilaoFinalizado;
    private javax.swing.JMenu menuLeiloes;
    // End of variables declaration//GEN-END:variables
}
