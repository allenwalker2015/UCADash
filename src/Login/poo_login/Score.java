
package Login.poo_login;

import Login.Procesos.ACME;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public final class Score extends javax.swing.JFrame {
    private login log;
    private String user;
    DefaultTableModel model;
    public login getLog() {
        return log;
    }

    public void setLog(login log) {
        this.log = log;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    public Score() {
        initComponents();
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Score(String user) {
        this.user = user;
        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Vector encabezado=new Vector();
        encabezado.add(0,"user");
        encabezado.add(1,"score");
        
        Vector datos=new Vector();
        model=new DefaultTableModel(datos,encabezado);
        jTableDatos.setModel(model);
        try{
         this.mostrar();
        }catch(ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void limpiarTabla(){
        int i=0;
        while(i<jTableDatos.getRowCount()){
            model.removeRow(0);
        }
    }
    
    public void mostrar() throws ClassNotFoundException, SQLException{
        limpiarTabla();
        Vector datosBD=ACME.getUsuarios();
        int i=0;
        while(i<datosBD.size()){
           Vector fila=(Vector)datosBD.get(i);
           model.addRow(fila);
           i++;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDatos = new javax.swing.JTable();
        btnAceptarScore = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Score");

        jTableDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableDatos);

        btnAceptarScore.setText("Aceptar");
        btnAceptarScore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarScoreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(btnAceptarScore)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAceptarScore)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarScoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarScoreActionPerformed
        // TODO add your handling code here:
        login log = new login();
        log.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btnAceptarScoreActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Score().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptarScore;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDatos;
    // End of variables declaration//GEN-END:variables
}
