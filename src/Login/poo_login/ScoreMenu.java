/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login.poo_login;

import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cesar
 */
public final class ScoreMenu extends javax.swing.JFrame {
    public static boolean open=false;
    private static Image image=null;
        static {
                    ImageIcon ii = new ImageIcon("Imagenes/icon.png");
                    image = ii.getImage();
        }
    /**
     * Creates new form prueba
     */
    public ScoreMenu() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(image);
        DefaultTableModel model1 = new DefaultTableModel();
        this.Score.setModel(model1);
        model1.addColumn("Usuario");
        model1.addColumn("Puntaje");
    }
    
   
    
    public void mostrar1(){
       try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ucadash", "root", "root");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT NomUsuario, Score1 from usuario ORDER BY Score1 DESC");        
            ResultSetMetaData rsm = rs.getMetaData();
            int numCol = rsm.getColumnCount();
            DefaultTableModel model1 = new DefaultTableModel();
            this.Score.setModel(model1);
            model1.addColumn("Usuario");
            model1.addColumn("Puntaje");
            int cont=0;
            while(rs.next() && cont<10){    
                Object [] fila = new Object [numCol];
                for(int j=0; j<numCol;j++){
                    fila [j] = rs.getObject(j+1);
                }
                model1.addRow(fila);
                cont++;
            }            
       }catch(ClassNotFoundException | SQLException ex){
           System.out.println(ex.getMessage());
       }  
    }
    
    public void mostrar2(){
       try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ucadash", "root", "root");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT NomUsuario, Score2 from usuario ORDER BY Score2 DESC");        
            ResultSetMetaData rsm = rs.getMetaData();
            int numCol = rsm.getColumnCount();
            DefaultTableModel model1 = new DefaultTableModel();
            this.Score.setModel(model1);
            model1.addColumn("Usuario");
            model1.addColumn("Puntaje");
            int cont=0;
            while(rs.next() && cont<10){    
                Object [] fila = new Object [numCol];
                for(int j=0; j<numCol;j++){
                    fila [j] = rs.getObject(j+1);
                }
                model1.addRow(fila);
                cont++;
            }            
       }catch(ClassNotFoundException | SQLException ex){
           System.out.println(ex.getMessage());
       }  
    }
    
    public void mostrar3(){
       try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ucadash", "root", "root");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT NomUsuario, Score3 from usuario ORDER BY Score3 DESC");        
            ResultSetMetaData rsm = rs.getMetaData();
            int numCol = rsm.getColumnCount();
            DefaultTableModel model1 = new DefaultTableModel();
            this.Score.setModel(model1);
            model1.addColumn("Usuario");
            model1.addColumn("Puntaje");
            int cont=0;
            while(rs.next() && cont<10){    
                Object [] fila = new Object [numCol];
                for(int j=0; j<numCol;j++){
                    fila [j] = rs.getObject(j+1);
                }
                model1.addRow(fila);
                cont++;
            }            
       }catch(ClassNotFoundException | SQLException ex){
           System.out.println(ex.getMessage());
       }  
    }
    
    public void mostrar4(){
       try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ucadash", "root", "root");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT NomUsuario, Score4 from usuario ORDER BY Score4 DESC");        
            ResultSetMetaData rsm = rs.getMetaData();
            int numCol = rsm.getColumnCount();
            DefaultTableModel model1 = new DefaultTableModel();
            this.Score.setModel(model1);
            model1.addColumn("Usuario");
            model1.addColumn("Puntaje");
            int cont=0;
            while(rs.next() && cont<10){    
                Object [] fila = new Object [numCol];
                for(int j=0; j<numCol;j++){
                    fila [j] = rs.getObject(j+1);
                }
                model1.addRow(fila);
                cont++;
            }            
       }catch(ClassNotFoundException | SQLException ex){
           System.out.println(ex.getMessage());
       }  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Score = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnN1 = new javax.swing.JButton();
        btnN2 = new javax.swing.JButton();
        btnN3 = new javax.swing.JButton();
        btnN4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Score.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        Score.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Score.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(Score);
        if (Score.getColumnModel().getColumnCount() > 0) {
            Score.getColumnModel().getColumn(0).setResizable(false);
            Score.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel1.setText("Top10 UCADash");

        jButton1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnN1.setText("Nivel1");
        btnN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnN1ActionPerformed(evt);
            }
        });

        btnN2.setText("Nivel2");
        btnN2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnN2ActionPerformed(evt);
            }
        });

        btnN3.setText("Nivel3");
        btnN3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnN3ActionPerformed(evt);
            }
        });

        btnN4.setText("Nivel4");
        btnN4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnN4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnN1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnN2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnN3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnN4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnN1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnN2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnN3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnN4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(19, 19, 19))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        open=false;
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnN1ActionPerformed
        // TODO add your handling code here:
        mostrar1();
    }//GEN-LAST:event_btnN1ActionPerformed

    private void btnN2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnN2ActionPerformed
        // TODO add your handling code here:
        mostrar2();
    }//GEN-LAST:event_btnN2ActionPerformed

    private void btnN3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnN3ActionPerformed
        // TODO add your handling code here:
        mostrar3();
    }//GEN-LAST:event_btnN3ActionPerformed

    private void btnN4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnN4ActionPerformed
        // TODO add your handling code here:
        mostrar4();
    }//GEN-LAST:event_btnN4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void start() {
        open=true;
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ScoreMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScoreMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScoreMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScoreMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ScoreMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Score;
    private javax.swing.JButton btnN1;
    private javax.swing.JButton btnN2;
    private javax.swing.JButton btnN3;
    private javax.swing.JButton btnN4;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
