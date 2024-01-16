package vistas;


import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import logs.ConexionSocket;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author Antonio Company Rodriguez
 * 
 * Clase para crear la ventana inicial del programa en la cual iniciaremos sesi�n
 */
public class MainForm extends javax.swing.JFrame {

    private final String DIRECTORIOACTUAL = System.getProperty("user.dir");
    private final String RUTAIMAGEN = DIRECTORIOACTUAL + "/img/HREntradaIcono.jpg";
    
    private String palabra = "";
    private static Socket socket;
    private static SSLSocket sslSocket;
    
    private static MainForm mainForm;  
    private String codigo;

    public static SSLSocket getSslSocket() {
        return sslSocket;
    }

    public static void setSslSocket(SSLSocket sslSocket) {
        MainForm.sslSocket = sslSocket;
    }      

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public static Socket getSocket() {
        return socket;
    }

    public static void setSocket(Socket socket) {
        MainForm.socket = socket;
    }  

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }
    
    /**
     * Creates new form MainForm
     * 
     * Con las caracter�sticas establecidas dentro de este m�todo
     */
    public MainForm() {
        initComponents();
        setTitle("Ventana Login usuarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Establece la ubicaci�n de la ventana en el centro de la pantalla
        setLocationRelativeTo(null);
        setResizable(false);
        jCheckBoxVerContrase�a.setEnabled(false);
        jButtonConfirmar.setEnabled(false);
        jPasswordField.setEnabled(false);  
        
        ImageIcon icono = new ImageIcon(RUTAIMAGEN);
        setIconImage(icono.getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelIPServidor = new javax.swing.JLabel();
        jLabelUsuario = new javax.swing.JLabel();
        jLabelContrase�a = new javax.swing.JLabel();
        jTextFieldIPServidor = new javax.swing.JTextField();
        jTextFieldUsuario = new javax.swing.JTextField();
        jButtonConfirmar = new javax.swing.JButton();
        jButtonLimpiar = new javax.swing.JButton();
        jCheckBoxVerContrase�a = new javax.swing.JCheckBox();
        jPasswordField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setMaximumSize(new java.awt.Dimension(1920, 1080));
        jPanel1.setMinimumSize(new java.awt.Dimension(350, 270));
        jPanel1.setPreferredSize(new java.awt.Dimension(340, 290));

        jLabelIPServidor.setText("IP Servidor");

        jLabelUsuario.setText("Usuario");

        jLabelContrase�a.setText("Contrase�a");

        jTextFieldIPServidor.setText("localhost");

        jTextFieldUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldUsuarioKeyReleased(evt);
            }
        });

        jButtonConfirmar.setText("CONFIRMAR");
        jButtonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarActionPerformed(evt);
            }
        });

        jButtonLimpiar.setText("LIMPIAR");
        jButtonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarActionPerformed(evt);
            }
        });

        jCheckBoxVerContrase�a.setBackground(new java.awt.Color(102, 102, 102));
        jCheckBoxVerContrase�a.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBoxVerContrase�a.setText("Ver contrase�a");
        jCheckBoxVerContrase�a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxVerContrase�aActionPerformed(evt);
            }
        });

        jPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPasswordFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButtonConfirmar)
                        .addGap(57, 57, 57)
                        .addComponent(jButtonLimpiar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelIPServidor)
                            .addComponent(jLabelUsuario)
                            .addComponent(jLabelContrase�a))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldIPServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jCheckBoxVerContrase�a))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIPServidor)
                    .addComponent(jTextFieldIPServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUsuario)
                    .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelContrase�a)
                    .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxVerContrase�a)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConfirmar)
                    .addComponent(jButtonLimpiar))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, 350, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
   /**
    * M�todo que comprueba si el campo de Usuario y Contrase�a estan vac�os o no para as�
    * poder habilitar el boton de confirmar y el checkBox de ver contrase�a o no.
    * 
    */
    
    public void habilitarBotones() {
        if (!jTextFieldUsuario.getText().isEmpty() && !jPasswordField.getText().isEmpty()) {
            jButtonConfirmar.setEnabled(true);
            jCheckBoxVerContrase�a.setEnabled(true);
        } else {
            jButtonConfirmar.setEnabled(false);
        }
    }
    
    /**
    * M�todo que se ejecutar al hacer click en el boton Confirmar y que comprueba si la IP
    * introducida esta vacia para mandar un mensaje de informaci�n, o en caso contrario
    * ejecuta el m�todo conexionSocket(usuarioFormPesta�as);
    *
    * @param evt Evento de acci�n generado al interactuar con el bot�n Confirmar
    */
    
    private void jButtonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarActionPerformed
       
        FormVentanasUsuario usuarioFormPesta�as = new FormVentanasUsuario(codigo, jTextFieldUsuario.getText());
        try{
            if (jTextFieldIPServidor.getText().isEmpty()||jTextFieldIPServidor.getText().isBlank()||jTextFieldIPServidor.getText() =="" ){
                   JOptionPane.showMessageDialog(null, 
                             "Rellena el campo IP.");
            }else{//Esta la ip rellena
                if (!jTextFieldUsuario.getText().equals("") && !jPasswordField.getText().equals("")) {                                                              
                        ConexionSocket.conexionSocket(this, usuarioFormPesta�as, jTextFieldIPServidor, jTextFieldUsuario, jPasswordField);
                    }
                }
        } catch (Exception ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonConfirmarActionPerformed

    /**
    * Al ejecutar el m�todo borramos todo el contenido de los campos Usuario y Contrase�a
    *
    * @param evt Evento de acci�n generado al interactuar con el bot�n Limpiar
    */
    
    private void jButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarActionPerformed
        // TODO add your handling code here:
        jTextFieldUsuario.setText("");
        jPasswordField.setText("");
    }//GEN-LAST:event_jButtonLimpiarActionPerformed

    /**
    * M�todo que se ejecuta al hacer click en el checkBox de ver contrase�a
    *
    * @param evt Evento de acci�n generado al interactuar con el checkbox
    */
    
    private void jCheckBoxVerContrase�aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxVerContrase�aActionPerformed

        if (jCheckBoxVerContrase�a.isSelected()) {
            jPasswordField.setEchoChar((char) 0);
        } else {
            jPasswordField.setEchoChar('*');
        }
    }//GEN-LAST:event_jCheckBoxVerContrase�aActionPerformed

    /**
    * Al teclear algo el m�todo se ejecutar� y si el texto del campo Usuario esta vac�o
    * el bot�n de confirmar se deshabilitar�, en cambio si no esta vac�o se habilitar�.
    * En todas las situaciones el campo de Contrase�a se habilitar�.
    *
    * @param evt Evento de acci�n generado al interactuar con el textField Usuario
    */
    
    private void jTextFieldUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldUsuarioKeyReleased

        if (jTextFieldUsuario.getText()==""||jTextFieldUsuario.getText().isEmpty()){
            jButtonConfirmar.setEnabled(false);
        }else{
            jButtonConfirmar.setEnabled(true);
        }
         jPasswordField.setEnabled(true);
    }//GEN-LAST:event_jTextFieldUsuarioKeyReleased

    /**
    * Al ejecutar el m�todo podremos activar o desactivar el checkBox ver contrase�a si
    * el campo de Contrase�a no esta vac�o.
    *
    * @param evt Evento de acci�n generado al teclear algo en el campo Contrase�a
    */
    
    private void jPasswordFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordFieldKeyReleased

        habilitarBotones();
        if(jPasswordField.getText()==""||jPasswordField.getText().isEmpty()){
            jCheckBoxVerContrase�a.setEnabled(false);
        }
    }//GEN-LAST:event_jPasswordFieldKeyReleased
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JCheckBox jCheckBoxVerContrase�a;
    private javax.swing.JLabel jLabelContrase�a;
    private javax.swing.JLabel jLabelIPServidor;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JTextField jTextFieldIPServidor;
    private javax.swing.JTextField jTextFieldUsuario;
    // End of variables declaration//GEN-END:variables
}
