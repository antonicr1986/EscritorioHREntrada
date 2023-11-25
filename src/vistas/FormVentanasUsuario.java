package vistas;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import logs.Logout;
import CRUD.Select;
import CRUD.Insert;
import java.awt.Color;

/**
 *
 * @author Antonio Company Rodriguez
 * 
 * Clase para crear la ventana principal del programa una vez nos hemos logueado
 */
public class FormVentanasUsuario extends javax.swing.JFrame {

    private Socket socket;    

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        FormVentanasUsuario.user = user;
    }
    private BufferedReader lector;
    private BufferedWriter escriptor;
    private ObjectInputStream perEnt;
    private String codigoUserRecibido;
    private static String user;
    private String rutaImagen = "C:\\Users\\anton\\OneDrive\\Escritorio\\M13\\EscritorioHREntrada\\img\\HREntradaIcono.jpg";
    
    private String codigo;   
    private String crud;
    private String nombreTabla;
    private String columna;
    private String palabra ="";
    private String orden;    
    
    private boolean select = false;
    private boolean insert = false;
    private boolean update = false;
    private boolean delete = false;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public BufferedWriter getEscriptor() {
        return escriptor;
    }

    public void setEscriptor(BufferedWriter escriptor) {
        this.escriptor = escriptor;
    }

    public ObjectInputStream getPerEnt() {
        return perEnt;
    }

    public void setPerEnt(ObjectInputStream perEnt) {
        this.perEnt = perEnt;
    }

    public String getCodigoUserRecibido() {
        return codigoUserRecibido;
    }

    public void setCodigoUserRecibido(String codigoUserRecibido) {
        this.codigoUserRecibido = codigoUserRecibido;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public JTextArea getjTextAreaInsert() {
        return jTextAreaInsert;
    }

    public void setjTextAreaInsert(JTextArea jTextAreaInsert) {
        this.jTextAreaInsert = jTextAreaInsert;
    }

    public JTextArea getjTextAreaSelect() {
        return jTextAreaSelect;
    }

    public void setjTextAreaSelect(JTextArea jTextAreaSelect) {
        this.jTextAreaSelect = jTextAreaSelect;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }
    
    public JLabel getjLabel1() {
        return jLabelUserCode;
    }

    public void setjLabel1(String nuevoTexto) {
        jLabelUserCode.setText(nuevoTexto);
    }
    
    public JLabel getjUserCode1() {
        return jLabelUserCode1;
    }

    public void setjUserCode1(String nuevoTexto) {
        jLabelUserCode1.setText(nuevoTexto);
    }
    
    public JLabel getjUserCode2() {
        return jLabelUserCode2;
    }

    public void setjUserCode2(String nuevoTexto) {
        jLabelUserCode2.setText(nuevoTexto);
    }
    
     public JLabel getjUserCode3() {
        return jLabelUserCode3;
    }

    public void setjUserCode3(String nuevoTexto) {
        jLabelUserCode3.setText(nuevoTexto);
    }    
    
    
    /**
     * Creates new form FormVentanasUsuario
     * con caracteristicas iniciales una de las cuales mas importantes es la de
     * segun el codigo que hayamos recibido por parametro tipo String activaremos
     * o no todas las opciones referentes a la tabla users
     * 
     * @param codigo lo usaremos para determinar si el usuario tiene acceso a opciones
     * referentes a la tabla users o no segun si empieza por 'A' admin o 'U' user
     * 
     * @param user lo utilizaremos para almacenar el nombre del usuario en la variable
     * de clase llamada user
     */
    public FormVentanasUsuario(String codigo, String user) {
        this.user = user;
        setMinimumSize(new Dimension(950, 550));
        setMaximumSize(new Dimension(1100,1000));
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);   
        initComponents();
        
        //Volver no editables por el usuario los textArea
        jTextAreaSelect.setEditable(false);
        jTextAreaInsert.setEditable(false);
        jTextAreaDelete.setEditable(false);
        jTextAreaUpdate.setEditable(false);
        
        //A�adir icono
        ImageIcon icono = new ImageIcon(rutaImagen);
        setIconImage(icono.getImage());

        buttonGroup.add(jRadioButtonEmpresa);
        buttonGroup.add(jRadioButtonEmpleado);
        buttonGroup.add(jRadioButtonJornada);
        
        //JOptionPane.showMessageDialog(null,"codigo: "+codigo);
        if (codigo !=null && codigo.charAt(0) == 'A'){
             buttonGroup.add(jRadioButtonUsers);
             jRadioButtonUsers.setVisible(true);
        }else{
            jComboBoxTablas.removeItemAt(1);
            jComboBoxTablasBorrar.removeItemAt(1);
            jComboBoxTablasUpdate.removeItemAt(1);
            jRadioButtonUsers.setVisible(false);
        }           
        
        //ComboBox Busqueda y ordenar no visibles
        //Etiqueta a�adir info y panel varios filtros no visibles
        jLabelOperacion.setVisible(false);
        jComboBoxTipoOperacion.setVisible(false);
        
        jLabelOrdenar.setVisible(false);
        jComboBoxOrdenar.setVisible(false);
        
        jLabelA�adirInfo.setVisible(false);
        
        jPanelVariosFiltros.setVisible(false);
        jLabelVariosFiltrosNoPosible.setVisible(false);
        
        jButtonA�adirATabla.setEnabled(false);
        
        activarDesactivarLabelsA�adir();
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Logout.logout((FormVentanasUsuario) e.getWindow());               
            }
        }); 
        
        jComboBoxTablas.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               cambiarItemsComboBoxColumna();
            }
        });
    }
    
    /**
    * M�todo que al ejecutarse borra el contenido del comboBoxColumna
    * y lo rellena con diferentes valores segun el contenido de la 
    * variable seleccion.
    * 
    */
    
    private void cambiarItemsComboBoxColumna(){
         String seleccion = (String) jComboBoxTablas.getSelectedItem().toString();
                // Limpia el modelo actual
                jComboBoxColumna.removeAllItems();
                if ("empleados".equals(seleccion)) {
                    jComboBoxColumna.addItem("todas");
                    jComboBoxColumna.addItem("dni");
                    jComboBoxColumna.addItem("nom");
                    jComboBoxColumna.addItem("apellido");
                    jComboBoxColumna.addItem("nomempresa");
                    jComboBoxColumna.addItem("departament");
                    jComboBoxColumna.addItem("codicard");
                    jComboBoxColumna.addItem("mail");
                    jComboBoxColumna.addItem("telephon");
                } else if ("empresa".equals(seleccion)) {
                    jComboBoxColumna.addItem("todas");
                    jComboBoxColumna.addItem("nom");
                    jComboBoxColumna.addItem("address");
                    jComboBoxColumna.addItem("telephon");
                } else if ("jornada".equals(seleccion)) {
                    jComboBoxColumna.addItem("todas");
                    jComboBoxColumna.addItem("dni");
                    jComboBoxColumna.addItem("nom");
                    jComboBoxColumna.addItem("apellido");
                    jComboBoxColumna.addItem("codicard");
                    //jComboBoxColumna.addItem("horaentrada");
                    //jComboBoxColumna.addItem("horasalida");
                    //jComboBoxColumna.addItem("total");
                    jComboBoxColumna.addItem("fecha");
                }else if ("users".equals(seleccion)) {
                    jComboBoxColumna.addItem("todas");
                    jComboBoxColumna.addItem("login");
                    //jComboBoxColumna.addItem("pass");
                    jComboBoxColumna.addItem("numtipe");
                    jComboBoxColumna.addItem("dni");                 
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

        buttonGroup = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelBusqueda = new javax.swing.JPanel();
        jButtonBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaSelect = new javax.swing.JTextArea();
        jLabelCodigo = new javax.swing.JLabel();
        jLabelOperacion = new javax.swing.JLabel();
        jLabelTabla = new javax.swing.JLabel();
        jLabelPalabra = new javax.swing.JLabel();
        jLabelColumna = new javax.swing.JLabel();
        jComboBoxTipoOperacion = new javax.swing.JComboBox<>();
        jComboBoxTablas = new javax.swing.JComboBox<>();
        jComboBoxColumna = new javax.swing.JComboBox<>();
        jLabelOrdenar = new javax.swing.JLabel();
        jComboBoxOrdenar = new javax.swing.JComboBox<>();
        jTextFieldPalabra = new javax.swing.JTextField();
        jLabelUserCode = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanelVariosFiltros = new javax.swing.JPanel();
        jTextFieldApellido2 = new javax.swing.JTextField();
        jTextFieldNom2 = new javax.swing.JTextField();
        jLabelNombre2 = new javax.swing.JLabel();
        jLabelApellido2 = new javax.swing.JLabel();
        jCheckBoxBuscarVariosFiltros = new javax.swing.JCheckBox();
        jLabelVariosFiltrosNoPosible = new javax.swing.JLabel();
        jPanelA�adir = new javax.swing.JPanel();
        jPanelBusqueda3 = new javax.swing.JPanel();
        jButtonA�adirATabla = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaInsert = new javax.swing.JTextArea();
        jLabelCodigo3 = new javax.swing.JLabel();
        jLabelUserCode3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldDni = new javax.swing.JTextField();
        jTextFieldNom = new javax.swing.JTextField();
        jTextFieldApellido = new javax.swing.JTextField();
        jTextFieldNomEmpresa = new javax.swing.JTextField();
        jTextFieldDepartament = new javax.swing.JTextField();
        jTextFieldCodicard = new javax.swing.JTextField();
        jTextFieldMail = new javax.swing.JTextField();
        jTextFieldTelephon = new javax.swing.JTextField();
        jTextFieldLogin = new javax.swing.JTextField();
        jTextFieldPass = new javax.swing.JTextField();
        jTextFieldNumtipe = new javax.swing.JTextField();
        jTextFieldAddress = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabelCamposAIntroducir = new javax.swing.JLabel();
        jRadioButtonEmpresa = new javax.swing.JRadioButton();
        jRadioButtonEmpleado = new javax.swing.JRadioButton();
        jRadioButtonJornada = new javax.swing.JRadioButton();
        jRadioButtonUsers = new javax.swing.JRadioButton();
        jLabelA�adirInfo = new javax.swing.JLabel();
        jLabelNumtipe = new javax.swing.JLabel();
        jLabelCodicard = new javax.swing.JLabel();
        jLabelA�adirInfo2 = new javax.swing.JLabel();
        jPanelActualizar = new javax.swing.JPanel();
        jPanelBusqueda1 = new javax.swing.JPanel();
        jButtonActualizarTabla = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaUpdate = new javax.swing.JTextArea();
        jLabelCodigo1 = new javax.swing.JLabel();
        jLabelTabla1 = new javax.swing.JLabel();
        jLabelPalabra1 = new javax.swing.JLabel();
        jLabelColumna1 = new javax.swing.JLabel();
        jComboBoxTablasUpdate = new javax.swing.JComboBox<>();
        jComboBoxColumna1 = new javax.swing.JComboBox<>();
        jTextFieldPalabra1 = new javax.swing.JTextField();
        jLabelUserCode1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanelBorrar = new javax.swing.JPanel();
        jPanelBusqueda2 = new javax.swing.JPanel();
        jButtonBorrar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaDelete = new javax.swing.JTextArea();
        jLabelCodigo2 = new javax.swing.JLabel();
        jLabelTabla2 = new javax.swing.JLabel();
        jLabelPalabra2 = new javax.swing.JLabel();
        jLabelColumna2 = new javax.swing.JLabel();
        jComboBoxTablasBorrar = new javax.swing.JComboBox<>();
        jComboBoxColumna2 = new javax.swing.JComboBox<>();
        jTextFieldPalabra2 = new javax.swing.JTextField();
        jLabelUserCode2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanelGestionUsuario = new javax.swing.JPanel();
        jButtonLogout = new javax.swing.JButton();
        jButtonCambiarContrase�a = new javax.swing.JButton();
        jPanelOtros = new javax.swing.JPanel();
        jLabel_ImagenOtros = new javax.swing.JLabel();
        jPanelAcercaDe = new javax.swing.JPanel();
        jLabelHREntrada = new javax.swing.JLabel();
        jLabelDescripcion = new javax.swing.JLabel();
        jLabelAutores = new javax.swing.JLabel();
        jLabel_Imagen = new javax.swing.JLabel();
        jLabelVersion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setMaximumSize(new java.awt.Dimension(1200, 1200));

        jPanelBusqueda.setBackground(new java.awt.Color(153, 204, 255));

        jButtonBuscar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonBuscar.setText("BUSCAR");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jTextAreaSelect.setColumns(20);
        jTextAreaSelect.setRows(5);
        jScrollPane1.setViewportView(jTextAreaSelect);

        jLabelCodigo.setText("C�digo:");

        jLabelOperacion.setText("Tipo de operacion:");

        jLabelTabla.setText("Tabla:");

        jLabelPalabra.setText("Palabra a buscar:");

        jLabelColumna.setText("Columna:");

        jComboBoxTipoOperacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select", "insert" }));

        jComboBoxTablas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "empleados", "users", "empresa", "jornada" }));
        jComboBoxTablas.setMinimumSize(new java.awt.Dimension(110, 22));
        jComboBoxTablas.setPreferredSize(new java.awt.Dimension(110, 22));
        jComboBoxTablas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTablasActionPerformed(evt);
            }
        });

        jComboBoxColumna.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "todas", "dni", "nom", "apellido", "nomempresa", "departament", "codicard", "mail", "telephon" }));
        jComboBoxColumna.setMinimumSize(new java.awt.Dimension(110, 22));
        jComboBoxColumna.setPreferredSize(new java.awt.Dimension(110, 22));
        jComboBoxColumna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxColumnaActionPerformed(evt);
            }
        });

        jLabelOrdenar.setText("Ordenar:");

        jComboBoxOrdenar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No" }));

        jTextFieldPalabra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPalabraActionPerformed(evt);
            }
        });
        jTextFieldPalabra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPalabraKeyReleased(evt);
            }
        });

        jLabelUserCode.setText("jLabelUserCode");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("RESULTADO B�SQUEDA");

        jPanelVariosFiltros.setBackground(new java.awt.Color(153, 204, 255));

        jTextFieldApellido2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldApellido2KeyReleased(evt);
            }
        });

        jTextFieldNom2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldNom2KeyReleased(evt);
            }
        });

        jLabelNombre2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelNombre2.setText("Nombre");

        jLabelApellido2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelApellido2.setText("Apellido");

        javax.swing.GroupLayout jPanelVariosFiltrosLayout = new javax.swing.GroupLayout(jPanelVariosFiltros);
        jPanelVariosFiltros.setLayout(jPanelVariosFiltrosLayout);
        jPanelVariosFiltrosLayout.setHorizontalGroup(
            jPanelVariosFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVariosFiltrosLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanelVariosFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelApellido2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelNombre2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelVariosFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldNom2, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(jTextFieldApellido2))
                .addContainerGap())
        );
        jPanelVariosFiltrosLayout.setVerticalGroup(
            jPanelVariosFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVariosFiltrosLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanelVariosFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldNom2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNombre2))
                .addGap(18, 18, 18)
                .addGroup(jPanelVariosFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldApellido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelApellido2))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jCheckBoxBuscarVariosFiltros.setBackground(new java.awt.Color(153, 204, 255));
        jCheckBoxBuscarVariosFiltros.setText("Buscar por varios filtros");
        jCheckBoxBuscarVariosFiltros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxBuscarVariosFiltrosActionPerformed(evt);
            }
        });

        jLabelVariosFiltrosNoPosible.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelVariosFiltrosNoPosible.setText("Opci�n no disponible");

        javax.swing.GroupLayout jPanelBusquedaLayout = new javax.swing.GroupLayout(jPanelBusqueda);
        jPanelBusqueda.setLayout(jPanelBusquedaLayout);
        jPanelBusquedaLayout.setHorizontalGroup(
            jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBusquedaLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBusquedaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(502, 502, 502))
                    .addGroup(jPanelBusquedaLayout.createSequentialGroup()
                        .addGroup(jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBusquedaLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelVariosFiltrosNoPosible, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBoxBuscarVariosFiltros)))
                            .addGroup(jPanelBusquedaLayout.createSequentialGroup()
                                .addGroup(jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelOperacion)
                                        .addComponent(jLabelTabla)
                                        .addComponent(jLabelOrdenar)
                                        .addComponent(jLabelColumna)
                                        .addComponent(jLabelPalabra)
                                        .addComponent(jLabelCodigo))
                                    .addGroup(jPanelBusquedaLayout.createSequentialGroup()
                                        .addComponent(jButtonBuscar)
                                        .addGap(9, 9, 9)))
                                .addGap(9, 9, 9)
                                .addGroup(jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jComboBoxOrdenar, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxTipoOperacion, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelBusquedaLayout.createSequentialGroup()
                                        .addComponent(jLabelUserCode)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jTextFieldPalabra, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxColumna, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxTablas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBusquedaLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanelVariosFiltros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121))))
        );
        jPanelBusquedaLayout.setVerticalGroup(
            jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBusquedaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCodigo)
                    .addComponent(jLabelUserCode)
                    .addComponent(jLabel8))
                .addGroup(jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBusquedaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addGap(34, 34, 34))
                    .addGroup(jPanelBusquedaLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelOperacion)
                            .addComponent(jComboBoxTipoOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTabla)
                            .addComponent(jComboBoxTablas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxColumna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelColumna))
                        .addGap(29, 29, 29)
                        .addGroup(jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelPalabra)
                            .addComponent(jTextFieldPalabra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelOrdenar)
                            .addComponent(jComboBoxOrdenar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBoxBuscarVariosFiltros)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelVariosFiltrosNoPosible)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelVariosFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(jButtonBuscar)
                        .addGap(19, 19, 19))))
        );

        jTabbedPane1.addTab("B�squeda", jPanelBusqueda);

        jPanelBusqueda3.setBackground(new java.awt.Color(204, 255, 204));

        jButtonA�adirATabla.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonA�adirATabla.setText("A�ADIR A TABLA");
        jButtonA�adirATabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonA�adirATablaActionPerformed(evt);
            }
        });

        jTextAreaInsert.setColumns(20);
        jTextAreaInsert.setRows(5);
        jScrollPane4.setViewportView(jTextAreaInsert);

        jLabelCodigo3.setText("C�digo:");

        jLabelUserCode3.setText("jLabelUserCode");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("RESULTADO FINAL");

        jTextFieldDni.setText("dni");

        jTextFieldNom.setText("nom");

        jTextFieldApellido.setText("apellido");

        jTextFieldNomEmpresa.setText("nomempresa");

        jTextFieldDepartament.setText("departament");

        jTextFieldCodicard.setText("codicard");

        jTextFieldMail.setText("mail");

        jTextFieldTelephon.setText("telephon");

        jTextFieldLogin.setText("login");

        jTextFieldPass.setText("pass");

        jTextFieldNumtipe.setText("numtipe");

        jTextFieldAddress.setText("address");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Selecciona una tabla:");

        jLabelCamposAIntroducir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelCamposAIntroducir.setText("CAMPOS A INTRODUCIR:");

        jRadioButtonEmpresa.setBackground(new java.awt.Color(204, 255, 204));
        jRadioButtonEmpresa.setText("empresa");
        jRadioButtonEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonEmpresaActionPerformed(evt);
            }
        });

        jRadioButtonEmpleado.setBackground(new java.awt.Color(204, 255, 204));
        jRadioButtonEmpleado.setText("empleados");
        jRadioButtonEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonEmpleadoActionPerformed(evt);
            }
        });

        jRadioButtonJornada.setBackground(new java.awt.Color(204, 255, 204));
        jRadioButtonJornada.setText("jornada");
        jRadioButtonJornada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonJornadaActionPerformed(evt);
            }
        });

        jRadioButtonUsers.setBackground(new java.awt.Color(204, 255, 204));
        jRadioButtonUsers.setText("users");
        jRadioButtonUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonUsersActionPerformed(evt);
            }
        });

        jLabelA�adirInfo.setText("Comienza por a�adir una empresa");

        jLabelNumtipe.setText("0 = admin, 1 = usuario");

        jLabelCodicard.setText("codicard = solo n�meros");

        javax.swing.GroupLayout jPanelBusqueda3Layout = new javax.swing.GroupLayout(jPanelBusqueda3);
        jPanelBusqueda3.setLayout(jPanelBusqueda3Layout);
        jPanelBusqueda3Layout.setHorizontalGroup(
            jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBusqueda3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanelBusqueda3Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jLabelUserCode3))
                    .addComponent(jLabelCodigo3)
                    .addGroup(jPanelBusqueda3Layout.createSequentialGroup()
                        .addGroup(jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButtonEmpresa)
                            .addComponent(jRadioButtonJornada))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButtonEmpleado)
                            .addComponent(jRadioButtonUsers)))
                    .addGroup(jPanelBusqueda3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jButtonA�adirATabla))
                    .addComponent(jLabelA�adirInfo)
                    .addComponent(jLabelA�adirInfo2))
                .addGroup(jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBusqueda3Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addGroup(jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabelCamposAIntroducir)
                            .addGroup(jPanelBusqueda3Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextFieldDepartament, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldDni, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextFieldAddress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelBusqueda3Layout.createSequentialGroup()
                                        .addGroup(jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTextFieldCodicard, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                            .addComponent(jTextFieldNom, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                            .addComponent(jTextFieldLogin))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextFieldPass, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldMail, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabelCodicard))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelNumtipe)
                                    .addGroup(jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextFieldTelephon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldNomEmpresa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldNumtipe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(15, 15, 15))
                    .addGroup(jPanelBusqueda3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanelBusqueda3Layout.setVerticalGroup(
            jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBusqueda3Layout.createSequentialGroup()
                .addGroup(jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBusqueda3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCodigo3)
                            .addComponent(jLabelUserCode3)
                            .addComponent(jLabelCamposAIntroducir))
                        .addGap(93, 93, 93)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButtonEmpresa)
                            .addComponent(jRadioButtonEmpleado))
                        .addGap(7, 7, 7)
                        .addGroup(jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButtonJornada)
                            .addComponent(jRadioButtonUsers)))
                    .addGroup(jPanelBusqueda3Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNomEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldDepartament, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldCodicard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTelephon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addComponent(jLabelCodicard)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNumtipe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelNumtipe)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanelBusqueda3Layout.createSequentialGroup()
                        .addComponent(jLabelA�adirInfo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelA�adirInfo2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonA�adirATabla))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout jPanelA�adirLayout = new javax.swing.GroupLayout(jPanelA�adir);
        jPanelA�adir.setLayout(jPanelA�adirLayout);
        jPanelA�adirLayout.setHorizontalGroup(
            jPanelA�adirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBusqueda3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelA�adirLayout.setVerticalGroup(
            jPanelA�adirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBusqueda3, javax.swing.GroupLayout.PREFERRED_SIZE, 584, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("A�adir", jPanelA�adir);

        jPanelBusqueda1.setBackground(new java.awt.Color(255, 255, 204));

        jButtonActualizarTabla.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonActualizarTabla.setText("ACTUALIZAR TABLA");
        jButtonActualizarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarTablaActionPerformed(evt);
            }
        });

        jTextAreaUpdate.setColumns(20);
        jTextAreaUpdate.setRows(5);
        jScrollPane2.setViewportView(jTextAreaUpdate);

        jLabelCodigo1.setText("C�digo:");

        jLabelTabla1.setText("Tabla:");

        jLabelPalabra1.setText("Palabra a buscar:");

        jLabelColumna1.setText("Columna:");

        jComboBoxTablasUpdate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "empleados", "users", "empresa", "jornada" }));

        jComboBoxColumna1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "todas", "dni", "nom", "apellido", "nomempresa", "departament", "codicard", "mail", "telephon" }));

        jLabelUserCode1.setText("jLabelUserCode");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("RESULTADO ACTUALIZACI�N");

        javax.swing.GroupLayout jPanelBusqueda1Layout = new javax.swing.GroupLayout(jPanelBusqueda1);
        jPanelBusqueda1.setLayout(jPanelBusqueda1Layout);
        jPanelBusqueda1Layout.setHorizontalGroup(
            jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBusqueda1Layout.createSequentialGroup()
                .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBusqueda1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jButtonActualizarTabla)
                        .addGap(79, 79, 79))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBusqueda1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTabla1)
                            .addComponent(jLabelPalabra1)
                            .addComponent(jLabelColumna1)
                            .addComponent(jLabelCodigo1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxColumna1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanelBusqueda1Layout.createSequentialGroup()
                                    .addComponent(jLabelUserCode1)
                                    .addGap(22, 22, 22))
                                .addComponent(jTextFieldPalabra1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBoxTablasUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)))
                .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanelBusqueda1Layout.setVerticalGroup(
            jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBusqueda1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBusqueda1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE))
                    .addGroup(jPanelBusqueda1Layout.createSequentialGroup()
                        .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCodigo1)
                            .addGroup(jPanelBusqueda1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabelUserCode1)
                                .addGap(81, 81, 81)
                                .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBoxTablasUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelTabla1))
                                .addGap(28, 28, 28)
                                .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBoxColumna1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelColumna1))
                                .addGap(24, 24, 24)
                                .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelPalabra1)
                                    .addComponent(jTextFieldPalabra1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonActualizarTabla)
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout jPanelActualizarLayout = new javax.swing.GroupLayout(jPanelActualizar);
        jPanelActualizar.setLayout(jPanelActualizarLayout);
        jPanelActualizarLayout.setHorizontalGroup(
            jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelActualizarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanelBusqueda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelActualizarLayout.setVerticalGroup(
            jPanelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBusqueda1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Actualizar", jPanelActualizar);

        jPanelBorrar.setBackground(new java.awt.Color(255, 102, 102));

        jPanelBusqueda2.setBackground(new java.awt.Color(255, 204, 204));

        jButtonBorrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonBorrar.setText("BORRAR");
        jButtonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrarActionPerformed(evt);
            }
        });

        jTextAreaDelete.setColumns(20);
        jTextAreaDelete.setRows(5);
        jScrollPane3.setViewportView(jTextAreaDelete);

        jLabelCodigo2.setText("C�digo:");

        jLabelTabla2.setText("Tabla:");

        jLabelPalabra2.setText("Palabra a buscar:");

        jLabelColumna2.setText("Columna:");

        jComboBoxTablasBorrar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "empleados", "users", "empresa", "jornada" }));

        jComboBoxColumna2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "todas", "dni", "nom", "apellido", "nomempresa", "departament", "codicard", "mail", "telephon" }));

        jTextFieldPalabra2.setPreferredSize(new java.awt.Dimension(121, 22));

        jLabelUserCode2.setText("jLabelUserCode");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("RESULTADO");

        javax.swing.GroupLayout jPanelBusqueda2Layout = new javax.swing.GroupLayout(jPanelBusqueda2);
        jPanelBusqueda2.setLayout(jPanelBusqueda2Layout);
        jPanelBusqueda2Layout.setHorizontalGroup(
            jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBusqueda2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanelBusqueda2Layout.createSequentialGroup()
                            .addComponent(jButtonBorrar)
                            .addGap(168, 168, 168))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBusqueda2Layout.createSequentialGroup()
                            .addGroup(jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelTabla2)
                                .addComponent(jLabelPalabra2)
                                .addComponent(jLabelColumna2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jComboBoxTablasBorrar, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelUserCode2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBoxColumna2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldPalabra2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(43, 43, 43)))
                    .addGroup(jPanelBusqueda2Layout.createSequentialGroup()
                        .addComponent(jLabelCodigo2)
                        .addGap(206, 206, 206)))
                .addGroup(jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanelBusqueda2Layout.setVerticalGroup(
            jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBusqueda2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCodigo2)
                    .addComponent(jLabelUserCode2)
                    .addComponent(jLabel10))
                .addGroup(jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBusqueda2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanelBusqueda2Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTabla2)
                            .addComponent(jComboBoxTablasBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelBusqueda2Layout.createSequentialGroup()
                                .addGroup(jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelPalabra2)
                                    .addComponent(jTextFieldPalabra2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addComponent(jLabelColumna2))
                            .addComponent(jComboBoxColumna2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 273, Short.MAX_VALUE)))
                .addComponent(jButtonBorrar)
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout jPanelBorrarLayout = new javax.swing.GroupLayout(jPanelBorrar);
        jPanelBorrar.setLayout(jPanelBorrarLayout);
        jPanelBorrarLayout.setHorizontalGroup(
            jPanelBorrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBorrarLayout.createSequentialGroup()
                .addComponent(jPanelBusqueda2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelBorrarLayout.setVerticalGroup(
            jPanelBorrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBusqueda2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Borrar", jPanelBorrar);

        jPanelGestionUsuario.setBackground(new java.awt.Color(153, 153, 153));

        jButtonLogout.setBackground(new java.awt.Color(255, 255, 255));
        jButtonLogout.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/PowerOff.jpg"))); // NOI18N
        jButtonLogout.setText("Logout Session");
        jButtonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogoutActionPerformed(evt);
            }
        });

        jButtonCambiarContrase�a.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonCambiarContrase�a.setText("Cambiar contrase�a usuario");
        jButtonCambiarContrase�a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCambiarContrase�aActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelGestionUsuarioLayout = new javax.swing.GroupLayout(jPanelGestionUsuario);
        jPanelGestionUsuario.setLayout(jPanelGestionUsuarioLayout);
        jPanelGestionUsuarioLayout.setHorizontalGroup(
            jPanelGestionUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGestionUsuarioLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanelGestionUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCambiarContrase�a)
                    .addComponent(jButtonLogout))
                .addContainerGap(758, Short.MAX_VALUE))
        );
        jPanelGestionUsuarioLayout.setVerticalGroup(
            jPanelGestionUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGestionUsuarioLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jButtonCambiarContrase�a)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 334, Short.MAX_VALUE)
                .addComponent(jButtonLogout)
                .addGap(43, 43, 43))
        );

        jTabbedPane1.addTab("Gesti�n usuario", jPanelGestionUsuario);

        jPanelOtros.setBackground(new java.awt.Color(153, 153, 153));
        jPanelOtros.setMaximumSize(new java.awt.Dimension(1200, 1000));
        jPanelOtros.setMinimumSize(new java.awt.Dimension(950, 500));
        jPanelOtros.setPreferredSize(new java.awt.Dimension(950, 500));

        jLabel_ImagenOtros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/HREntrada.jpg"))); // NOI18N
        jLabel_ImagenOtros.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanelOtrosLayout = new javax.swing.GroupLayout(jPanelOtros);
        jPanelOtros.setLayout(jPanelOtrosLayout);
        jPanelOtrosLayout.setHorizontalGroup(
            jPanelOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOtrosLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel_ImagenOtros)
                .addContainerGap(721, Short.MAX_VALUE))
        );
        jPanelOtrosLayout.setVerticalGroup(
            jPanelOtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelOtrosLayout.createSequentialGroup()
                .addContainerGap(265, Short.MAX_VALUE)
                .addComponent(jLabel_ImagenOtros)
                .addGap(60, 60, 60))
        );

        jTabbedPane1.addTab("Otros", jPanelOtros);

        jPanelAcercaDe.setBackground(new java.awt.Color(153, 153, 153));

        jLabelHREntrada.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabelHREntrada.setText("HREntrada �");

        jLabelDescripcion.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabelDescripcion.setText("Trabajo final de CFGS DAM");

        jLabelAutores.setFont(new java.awt.Font("Dialog", 2, 24)); // NOI18N
        jLabelAutores.setText("By David Valentin, Gustavo Se�orans y Antonio Company");

        jLabel_Imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/HREntrada.jpg"))); // NOI18N
        jLabel_Imagen.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel_Imagen.setIconTextGap(0);

        jLabelVersion.setText("v1.4");

        javax.swing.GroupLayout jPanelAcercaDeLayout = new javax.swing.GroupLayout(jPanelAcercaDe);
        jPanelAcercaDe.setLayout(jPanelAcercaDeLayout);
        jPanelAcercaDeLayout.setHorizontalGroup(
            jPanelAcercaDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAcercaDeLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanelAcercaDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelVersion)
                    .addComponent(jLabel_Imagen)
                    .addComponent(jLabelAutores)
                    .addComponent(jLabelDescripcion)
                    .addComponent(jLabelHREntrada))
                .addContainerGap(395, Short.MAX_VALUE))
        );
        jPanelAcercaDeLayout.setVerticalGroup(
            jPanelAcercaDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAcercaDeLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabelHREntrada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelVersion)
                .addGap(12, 12, 12)
                .addComponent(jLabelDescripcion)
                .addGap(18, 18, 18)
                .addComponent(jLabelAutores)
                .addGap(49, 49, 49)
                .addComponent(jLabel_Imagen)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Acerca de", jPanelAcercaDe);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 976, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * M�todo que al ejecutarse abre una ventana del tipo CambiarPasswordForm
    *
    * @param evt Evento de acci�n que se dispara al clicar en el boton
    * cambiar contrase�a
    * 
    */
    
    private void jButtonCambiarContrase�aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCambiarContrase�aActionPerformed

        CambiarPasswordForm ventanaCambioPass = new CambiarPasswordForm (user);
        ventanaCambioPass.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        ventanaCambioPass.setVisible(true);
    }//GEN-LAST:event_jButtonCambiarContrase�aActionPerformed

    /**
    * M�todo que ejecuta el m�todo logout()
    *
    * @param evt Evento de acci�n que se dispara al clicar en el boton
    * logout session 
    * 
    */
    
    private void jButtonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogoutActionPerformed

        Logout.logout(this);
        
    }//GEN-LAST:event_jButtonLogoutActionPerformed

    /**
    * M�todo que al ejecutarse asigna unos valores a unas variables booleanas
    * y despu�s ejecuta el m�todo ejecutarAccion();
    *
    * @param evt Evento de acci�n que se dispara al clicar en el 
    * boton Buscar
    * 
    */
    
    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed

        select = true;

        insert = false;
        update = false;
        delete = false;
        
        ejecutarAccion();
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    /**
    * M�todo que al ejecutarse asigna unos valores a unas variables booleanas
    * y despu�s ejecuta el m�todo ejecutarAccion();
    *
    * @param evt Evento de acci�n que se dispara al clicar en el 
    * boton Borrar
    * 
    */
    
    private void jButtonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrarActionPerformed

        delete = true;
        
        select = false;
        insert = false;
        update = false;

        ejecutarAccion();
    }//GEN-LAST:event_jButtonBorrarActionPerformed

    /**
    * M�todo que al ejecutarse asigna unos valores a unas variables booleanas
    * y despu�s ejecuta el m�todo ejecutarAccion();
    *
    * @param evt Evento de acci�n que se dispara al clicar en el 
    * boton Actualizar
    * 
    */
    
    private void jButtonActualizarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarTablaActionPerformed

        update = true;

        select = false;
        insert = false;
        delete = false;

        ejecutarAccion();
    }//GEN-LAST:event_jButtonActualizarTablaActionPerformed

    /**
    * M�todo que ejecuta los m�todos activarDesactivarTextFieldsA�adir() y
    * cambiarTextoActivarDesactivarLabelInfoA�adir()
    *
    * @param evt Evento de acci�n que se dispara al clicar en el 
    * radio button users.
    * 
    */
    
    private void jRadioButtonUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonUsersActionPerformed
        activarDesactivarLabelsA�adir();
        activarDesactivarTextFieldsA�adir();
        cambiarTextoActivarDesactivarLabelInfoA�adir();
    }//GEN-LAST:event_jRadioButtonUsersActionPerformed

    /**
    * M�todo que ejecuta los m�todos activarDesactivarTextFieldsA�adir() y
    * cambiarTextoActivarDesactivarLabelInfoA�adir()
    *
    * @param evt Evento de acci�n que se dispara al clicar en el 
    * radio button jornada.
    * 
    */
    
    private void jRadioButtonJornadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonJornadaActionPerformed
        activarDesactivarLabelsA�adir();
        activarDesactivarTextFieldsA�adir();
        cambiarTextoActivarDesactivarLabelInfoA�adir();
    }//GEN-LAST:event_jRadioButtonJornadaActionPerformed

    /**
    * M�todo que ejecuta los m�todos activarDesactivarTextFieldsA�adir() y
    * cambiarTextoActivarDesactivarLabelInfoA�adir()
    *
    * @param evt Evento de acci�n que se dispara al clicar en el 
    * radio button empleados.
    * 
    */
    
    private void jRadioButtonEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonEmpleadoActionPerformed
        activarDesactivarLabelsA�adir();
        activarDesactivarTextFieldsA�adir();
        cambiarTextoActivarDesactivarLabelInfoA�adir();
    }//GEN-LAST:event_jRadioButtonEmpleadoActionPerformed

    /**
    * M�todo que ejecuta los m�todos activarDesactivarTextFieldsA�adir() y
    * cambiarTextoActivarDesactivarLabelInfoA�adir()
    *
    * @param evt Evento de acci�n que se dispara al clicar en el 
    * radio button empresa.
    * 
    */
    private void jRadioButtonEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonEmpresaActionPerformed
        activarDesactivarLabelsA�adir();
        activarDesactivarTextFieldsA�adir();
        cambiarTextoActivarDesactivarLabelInfoA�adir();
    }//GEN-LAST:event_jRadioButtonEmpresaActionPerformed

    /**
    * M�todo que asigna valores a ciertas variables tipo booleanas y al String nombreTabla
    * y despu�s ejecuta el m�todo principal ejecutarAccion();
    *
    * @param evt Evento de acci�n que se dispara al clicar en el 
    * botton A�adir.
    */
    
    private void jButtonA�adirATablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonA�adirATablaActionPerformed
      
        insert = true;

        select = false;
        update = false;
        delete = false;

        jComboBoxTipoOperacion.setSelectedItem("insert");

        if (jRadioButtonEmpresa.isSelected()){
            nombreTabla = "empresa";
        }else if (jRadioButtonEmpleado.isSelected()){
            nombreTabla = "empleados";
        }else if (jRadioButtonJornada.isSelected()){
            nombreTabla = "jornada";
        }else if (jRadioButtonUsers.isSelected()){
            nombreTabla = "users";
        }
        ejecutarAccion();
    }//GEN-LAST:event_jButtonA�adirATablaActionPerformed

    
    private void jTextFieldPalabraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPalabraActionPerformed
      
        buscarPorVariosFiltrosONo();
        activarDesactivarBotonBusqueda();
    }//GEN-LAST:event_jTextFieldPalabraActionPerformed

    private void jComboBoxTablasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTablasActionPerformed
            
        buscarPorVariosFiltrosONo();
        activarDesactivarBotonBusqueda();
    }//GEN-LAST:event_jComboBoxTablasActionPerformed

    
    private void jComboBoxColumnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxColumnaActionPerformed
        limpiarTextfieldPalabra();
        buscarPorVariosFiltrosONo();
        activarDesactivarBotonBusqueda();
    }//GEN-LAST:event_jComboBoxColumnaActionPerformed

    private void jCheckBoxBuscarVariosFiltrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxBuscarVariosFiltrosActionPerformed
        
        buscarPorVariosFiltrosONo();
        activarDesactivarBotonBusqueda();
        if (jCheckBoxBuscarVariosFiltros.isSelected()){
            jButtonBuscar.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBoxBuscarVariosFiltrosActionPerformed

    private void jTextFieldPalabraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPalabraKeyReleased
         buscarPorVariosFiltrosONo();
        activarDesactivarBotonBusqueda();
    }//GEN-LAST:event_jTextFieldPalabraKeyReleased

    private void jTextFieldNom2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNom2KeyReleased
        comprobarTextFieldsBusquedaDobleActivarDesactivarBotonBuscar();
    }//GEN-LAST:event_jTextFieldNom2KeyReleased

    private void jTextFieldApellido2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldApellido2KeyReleased
        comprobarTextFieldsBusquedaDobleActivarDesactivarBotonBuscar();
    }//GEN-LAST:event_jTextFieldApellido2KeyReleased

     /**
    * M�todo que gestiona el estado activo/inactivo del boton Buscar
    * segun el estado de los campos de texto de la busqueda con 
    * varios filtros
    * 
    */
    
    private void comprobarTextFieldsBusquedaDobleActivarDesactivarBotonBuscar(){
        if (jTextFieldNom2.getText().equals("nom")){
            jButtonBuscar.setEnabled(false);
        }if (jTextFieldApellido2.getText().equals("apellido")){
             jButtonBuscar.setEnabled(false);
        }if (!jTextFieldNom2.getText().equals("nom") && !jTextFieldApellido2.getText().equals("apellido")){
            jButtonBuscar.setEnabled(true);
        }if (jTextFieldNom2.getText().equals("") || jTextFieldApellido2.getText().equals("")){
            jButtonBuscar.setEnabled(false);
        }
    }
    
    /**
    * M�todo que maneja los componentes a mostrar o no y habilitar o no
    * para poder gestionar el uso de varios filtros en la busqueda
    * 
    */
    
    private void buscarPorVariosFiltrosONo(){
        if (jCheckBoxBuscarVariosFiltros.isSelected()){
            if ( jComboBoxTablas.getSelectedItem().toString() == "empleados"){
                 jPanelVariosFiltros.setVisible(true);
                 jLabelVariosFiltrosNoPosible.setVisible(false);
            }
            else if ( jComboBoxTablas.getSelectedItem().toString() == "jornada"){
                 jPanelVariosFiltros.setVisible(true);
                 jLabelVariosFiltrosNoPosible.setVisible(false);
            }
            else{
                jPanelVariosFiltros.setVisible(false);
                jLabelVariosFiltrosNoPosible.setVisible(true);
            }
           jTextFieldPalabra.setEnabled(false);
           jComboBoxColumna.setEnabled(false);
           jComboBoxOrdenar.setEnabled(false);
        }else{
           jPanelVariosFiltros.setVisible(false);
           jLabelVariosFiltrosNoPosible.setVisible(false);
           jTextFieldPalabra.setEnabled(true);
           jComboBoxColumna.setEnabled(true);
           jComboBoxOrdenar.setEnabled(true);
        }
    }
    
    /**
    * M�todo principal de accion de la clase, el cual contiene toda la l�gica asociada
    * a m�todos para buscar, a�adir, borrar, actualizar datos mediante conexi�n con 
    * socket con el servidor
    * 
    */
    
     private void ejecutarAccion(){
        try {
            // TODO add your handling code here:
            socket = MainForm.getSocket();
            
            lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));//flujo lectura del server
            escriptor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));//flujo envio al server
            
            
            codigo = jLabelUserCode.getText(); //el codigo recibido tiene que ser el mismo que le hemos asignado
            crud = jComboBoxTipoOperacion.getSelectedItem().toString(); //Tipo de operacion
            if (select){
                nombreTabla = jComboBoxTablas.getSelectedItem().toString(); //Ser� el numero de tabla. (ej: 0->empleados 1->users 2-empresa 3->jornada) 
            }
            columna = jComboBoxColumna.getSelectedItem().toString(); //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
            String palabraAbuscar = jTextFieldPalabra.getText();// si es el caso ser� la columna (,dni,nom,etc), si no hay ponemos 0
            if (jTextFieldPalabra.getText().isEmpty()||jTextFieldPalabra.getText().isBlank()){//Para que cambie el valor de vacio a 0 para enviar al server.
                palabraAbuscar = "0";
            }
            orden = jComboBoxOrdenar.getSelectedItem().toString();// si es el caso el orden, si no hay ponemos 0          
            
            if (select){
                jTextAreaSelect.setText("");
            }        
            
            //Canviamos los valores de los combo box a los numericos correspondientes          
            switch (crud){
                case "select":
                    crud = "0";
                    break;
                case "insert":
                    crud = "1";
                    break;
                case "update":
                    crud = "2";
                    break;
                case "delete":
                    crud = "3";
                    break;
            }
            
            switch (nombreTabla){
                case "empleados":
                    nombreTabla = "0";
                    if (insert){
                         palabraAbuscar = "dni,"+jTextFieldDni.getText()+",nom,"+jTextFieldNom.getText()+",apellido,"+jTextFieldApellido.getText()
                            +",nomempresa,"+jTextFieldNomEmpresa.getText()+",departament,"+jTextFieldDepartament.getText()
                            +",codicard,"+jTextFieldCodicard.getText()+",mail,"+jTextFieldMail.getText()+",telephon,"+jTextFieldTelephon.getText();
                    }else{
                        if (jCheckBoxBuscarVariosFiltros.isSelected()){
                        palabraAbuscar = "nom,"+jTextFieldNom2.getText()+",apellido,"+jTextFieldApellido2.getText();
                        }
                    }                
                    break;
                case "users":
                    nombreTabla = "1";
                    if (insert){
                        palabraAbuscar = "login,"+jTextFieldLogin.getText()+",pass,"+jTextFieldPass.getText()
                            +",numtipe,"+jTextFieldNumtipe.getText()+",dni,"+jTextFieldDni.getText();
                    }
                    break;
                case "empresa":
                    nombreTabla = "2";
                    if (insert){
                        palabraAbuscar = "nom,"+jTextFieldNom.getText()+",address,"+jTextFieldAddress.getText()+",telephon,"+jTextFieldTelephon.getText();                 
                    } 
                    break;
                case "jornada":
                    nombreTabla = "3";
                    if (insert){
                        palabraAbuscar = "dni,"+jTextFieldDni.getText();
                    }else{
                        if (jCheckBoxBuscarVariosFiltros.isSelected()){
                        palabraAbuscar = "nom,"+jTextFieldNom2.getText()+",apellido,"+jTextFieldApellido2.getText();
                        }
                    }                           
                    break;
            }
            
            switch (columna){
                case "todas":
                    columna = "0";
                    break;
            }
            
            switch (orden){
                case "Si":
                    orden = "0";
                    break;
                case "No":
                    orden = "1";
                    break;
            }
            
            if(select){
                palabra = codigo + "," + crud + "," + nombreTabla + "," + columna + "," + palabraAbuscar + "," + orden;
                if (jCheckBoxBuscarVariosFiltros.isSelected()){
                    palabra = codigo + "," + crud + "," + nombreTabla + "," + palabraAbuscar + "," + orden;
                }
                jTextAreaSelect.append("Enviado al server: "+palabra+"\n");
            }else if (insert){
                palabra = codigo + "," + crud + "," + nombreTabla + "," + palabraAbuscar + "," + orden;
                jTextAreaInsert.append("Enviado al server: "+palabra+"\n");
            }else{
                 palabra = codigo + "," + crud + "," + nombreTabla + "," + columna + "," + palabraAbuscar + "," + orden;
            }
                   
            //JOptionPane.showMessageDialog(null,"Frase enviada al server: "+palabra); 
             
            
            // y ahora comprobamos que la frase este correcta si no enviamos una establecida (menos el codigo que sera error, es por si fallan las otras palabras)

            String[] frase = new String[6];
            frase = palabra.split(",");

            String[] NomApellido = new String[8];
            NomApellido = palabra.split(",");

            String[] insertEmpresas = new String[10];
            insertEmpresas = palabra.split(",");

            String[] insertUsuarios = new String[12];
            insertUsuarios = palabra.split(",");

            String[] insertEmpleadoMailTelf = new String[16];
            insertEmpleadoMailTelf = palabra.split(",");

            String[] insertEmpleadoMT = new String[18];
            insertEmpleadoMT = palabra.split(",");

            String[] insertEmpleado = new String[20];
            insertEmpleado = palabra.split(",");

            if (!codigo.equals(frase[0]) || !codigo.equals(NomApellido[0])//If para controlar que el codigo de usuario utilizado al hacer la peticion al server no es erroneo
                    || !codigo.equals(insertEmpresas[0]) || !codigo.equals(insertUsuarios[0])
                    || !codigo.equals(insertEmpleadoMailTelf[0]) || !codigo.equals(insertEmpleadoMT[0])
                    || !codigo.equals(insertEmpleado[0])) {

                jTextAreaSelect.append("El codigo es erroneo");

            } else if (frase[5].equals("0") || frase[5].equals("1")) {//Tanto si ordenamos como no ordenamos se cumplira este else if
                //JOptionPane.showMessageDialog(null, "frase5equals0 or 1");
                
                codigoUserRecibido = frase[0]; //el codigo recibido tiene que ser el mismo que le hemos asignado
                crud = frase[1];
                nombreTabla = frase[2]; //Ser� el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
                columna = frase[3]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                palabraAbuscar = frase[4];// si es el caso ser� la columna (,dni,nom,etc), si no hay ponemos 0
                orden = frase[5];// si es el caso el orden, si no hay ponemos 0

                jTextAreaSelect.append("____________________________________________________________________"+ "\n"
                    +"codigoUserRecibido: " + codigoUserRecibido + "\n"
                    +"crud: " + crud+ "\n"
                    +"nombreTabla: " + nombreTabla+ "\n"
                    +"columna: " + columna+ "\n"
                    +"palabraAbuscar: " + palabraAbuscar+ "\n"
                    +"orden: " + orden + "\n"
                    +"____________________________________________________________________" + "\n");

                palabra = codigoUserRecibido + "," + crud + "," + nombreTabla + "," + columna + "," + palabraAbuscar + "," + orden;

                if (codigoUserRecibido.equals("")) {
                    codigoUserRecibido = "0";
                }

                if (crud.equals("0")) {/*SELECT*/  
                    Select.operacionesConSelect(columna, palabra, palabraAbuscar,nombreTabla,escriptor,codigoUserRecibido,socket,jTextAreaSelect);
                }else if (crud.equals("1")){
                    //JOptionPane.showMessageDialog(null, "crudEquals1");
                    Insert.operacionesConInsertJornada(nombreTabla, columna,palabra, 
                    codigoUserRecibido, escriptor, perEnt,socket,jTextAreaInsert);
            }

            } else if (NomApellido[7].equals("0") || NomApellido[7].equals("1")) { //NomApellido7
                //JOptionPane.showMessageDialog(null, "NomApellido");

                Select.operacionesConNomYApellidos7(NomApellido, palabra, escriptor, socket, jTextAreaSelect);
                
            } else if (insertEmpresas[9].equals("0") || insertEmpresas[9].equals("1")) {
                //JOptionPane.showMessageDialog(null, "insertEmpresas");
                Insert.operacionesConInsertEmpresas(insertEmpresas, palabra, escriptor, perEnt, socket, jTextAreaInsert);
                
            } else if (insertUsuarios[11].equals("0") || insertUsuarios[11].equals("1")) {
                //JOptionPane.showMessageDialog(null, "insertUsuarios");
                Insert.operacionesConInsertUsuarios(insertUsuarios, palabra, escriptor, perEnt, socket, jTextAreaInsert);
                
            } else if (insertEmpleadoMailTelf[15].equals("0") || insertEmpleadoMailTelf[15].equals("1")) {
                //JOptionPane.showMessageDialog(null, "insertEmpleadoMailTelf15");
                Insert.operacionesConInsertEmpleadoMailTelf(insertEmpleadoMailTelf, palabra, escriptor, perEnt,
                        socket, jTextAreaInsert);
                
            }else if (insertEmpleadoMT[17].equals("0") || insertEmpleadoMT[17].equals("1") ) {
                //JOptionPane.showMessageDialog(null, "insertEmpleadoMailTelf17");
                Insert.operacionsConInsertEmpleadoMT17y15(insertEmpleadoMT, palabra, escriptor, perEnt, socket, jTextAreaInsert);
                
            } else if (insertEmpleado[19].equals("0") || insertEmpleado[19].equals("1")) { 
                //JOptionPane.showMessageDialog(null, "insertEmpleado19");
                Insert.operacionesConInsertEmpleado19(insertEmpleado, palabra, escriptor, perEnt, socket, jTextAreaInsert);                
            }
        }catch (UnknownHostException ex) {
            Logger.getLogger(FormVentanasUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(FormVentanasUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormVentanasUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception ex){
            Logger.getLogger(FormVentanasUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     
    /**
     * M�todo main para poder ejecutar r�pidamente sin ejecutar la aplicaci�n
     * y comprobar de manera mas r�pida los cambios que voy haciendo.
     * 
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(FormVentanasUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormVentanasUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormVentanasUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormVentanasUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormVentanasUsuario("A", user).setVisible(true);
            }
        });
    }                                                                                                                        
    
    /**
    * M�todo que gestiona el estado activo/inactivo del boton de Buscar
    * 
    */
    
    private void activarDesactivarBotonBusqueda(){
        if (jComboBoxColumna.getSelectedItem()!=null){
            if (!jTextFieldPalabra.getText().equals("")&&(jComboBoxColumna.getSelectedItem().toString().equals("todas"))){
            jButtonBuscar.setEnabled(false);
            }else if (jTextFieldPalabra.getText().equals("")&&(!jComboBoxColumna.getSelectedItem().toString().equals("todas"))){
                jButtonBuscar.setEnabled(false);
            }else{
                jButtonBuscar.setEnabled(true);
            }
        }
    }
    
    /**
    * M�todo que limpia el contenido del textField Palabra cuando en el comboBox
    * columna seleccionamos "todas"
    * 
    */
    
    private void limpiarTextfieldPalabra(){
        if (jComboBoxColumna.getSelectedItem()!=null){
            if (jComboBoxColumna.getSelectedItem().toString().equals("todas")){
                jTextFieldPalabra.setText("");
            }
        }   
    }
    
    /**
    * M�todo que maneja el contenido de una etiqueta informativa en la pesta�a
    * de a�adir
    * 
    */
    
    private void cambiarTextoActivarDesactivarLabelInfoA�adir(){
        if (jRadioButtonEmpresa.isSelected()){
            jLabelA�adirInfo.setVisible(true);
            jLabelA�adirInfo.setText("Comienza por a�adir una empresa");
            jLabelA�adirInfo2.setText("");
            jButtonA�adirATabla.setEnabled(true);
              
        }else if (jRadioButtonEmpleado.isSelected()){
            jLabelA�adirInfo.setVisible(true);
            jLabelA�adirInfo.setText("Introduce nom empresa existente");
            jLabelA�adirInfo2.setText("");
             jButtonA�adirATabla.setEnabled(true);
            
        }else if (jRadioButtonJornada.isSelected()){
            jLabelA�adirInfo.setVisible(true);
            jLabelA�adirInfo.setText("Introduce dni empleado existente");
            jLabelA�adirInfo2.setText("");
            jButtonA�adirATabla.setEnabled(true);
            
        }else if (jRadioButtonUsers.isSelected()){
            jLabelA�adirInfo.setVisible(true);
            jLabelA�adirInfo.setText("Introduce dni empleado existente");
            jLabelA�adirInfo2.setText("que no tenga usuario asociado");
            jButtonA�adirATabla.setEnabled(true);
        
        }else{  
            jLabelA�adirInfo.setVisible (false);
        }
    }
    
     /**
    * M�todo que maneja los estados activo o inactivo de los textFields de
    * la pesta�a de A�adir, segun el radio button de tabla seleccionada
    *
    */
    
    private void activarDesactivarTextFieldsA�adir(){
        if (jRadioButtonEmpresa.isSelected()){
            jTextFieldNom.setEnabled(true);
            jTextFieldAddress.setEnabled(true);
            jTextFieldTelephon.setEnabled(true);

            jTextFieldDni.setEnabled(false);
            jTextFieldApellido.setEnabled(false);
            jTextFieldNomEmpresa.setEnabled(false);
            jTextFieldDepartament.setEnabled(false);
            jTextFieldCodicard.setEnabled(false);
            jTextFieldMail.setEnabled(false);
            jTextFieldLogin.setEnabled(false);
            jTextFieldPass.setEnabled(false);
            jTextFieldNumtipe.setEnabled(false);
        }else if (jRadioButtonEmpleado.isSelected()){
            jTextFieldDni.setEnabled(true);
            jTextFieldNom.setEnabled(true);
            jTextFieldApellido.setEnabled(true);
            jTextFieldNomEmpresa.setEnabled(true);
            jTextFieldDepartament.setEnabled(true);
            jTextFieldCodicard.setEnabled(true);
            jTextFieldMail.setEnabled(true);
            jTextFieldTelephon.setEnabled(true);

            jTextFieldAddress.setEnabled(false);   
            jTextFieldLogin.setEnabled(false);
            jTextFieldPass.setEnabled(false);
            jTextFieldNumtipe.setEnabled(false);
        }else if (jRadioButtonJornada.isSelected()){
            jTextFieldDni.setEnabled(true);
            
            jTextFieldCodicard.setEnabled(false);
            jTextFieldNom.setEnabled(false);
            jTextFieldApellido.setEnabled(false);
            jTextFieldNomEmpresa.setEnabled(false);
            jTextFieldDepartament.setEnabled(false);    
            jTextFieldMail.setEnabled(false);
            jTextFieldTelephon.setEnabled(false);        
            jTextFieldAddress.setEnabled(false);   
            jTextFieldLogin.setEnabled(false);
            jTextFieldPass.setEnabled(false);
            jTextFieldNumtipe.setEnabled(false);
        }else if (jRadioButtonUsers.isSelected()){
            jTextFieldLogin.setEnabled(true);
            jTextFieldPass.setEnabled(true);
            jTextFieldNumtipe.setEnabled(true);
            jTextFieldDni.setEnabled(true);

            jTextFieldCodicard.setEnabled(false);       
            jTextFieldNom.setEnabled(false);
            jTextFieldApellido.setEnabled(false);
            jTextFieldNomEmpresa.setEnabled(false);
            jTextFieldDepartament.setEnabled(false);    
            jTextFieldMail.setEnabled(false);
            jTextFieldTelephon.setEnabled(false);        
            jTextFieldAddress.setEnabled(false);
        }else{
            jTextFieldLogin.setEnabled(false);
            jTextFieldPass.setEnabled(false);
            jTextFieldNumtipe.setEnabled(false);
            jTextFieldDni.setEnabled(false);
            jTextFieldCodicard.setEnabled(false);       
            jTextFieldNom.setEnabled(false);
            jTextFieldApellido.setEnabled(false);
            jTextFieldNomEmpresa.setEnabled(false);
            jTextFieldDepartament.setEnabled(false);    
            jTextFieldMail.setEnabled(false);
            jTextFieldTelephon.setEnabled(false);        
            jTextFieldAddress.setEnabled(false); 
        }
    }
    
    /**
    * M�todo que maneja los estados visible o no de etiquetas de
    * la pesta�a de A�adir, segun el radio button de tabla seleccionado
    *
    */
    
    private void activarDesactivarLabelsA�adir(){
        if (jRadioButtonEmpresa.isSelected()){       
            jLabelCodicard.setEnabled(false);
            jLabelNumtipe.setEnabled(false);
            
            jTextFieldNomEmpresa.setBackground(Color.white);
            jTextFieldDni.setBackground(Color.white);
            
        }else if (jRadioButtonEmpleado.isSelected()){
            jLabelCodicard.setEnabled(true);
            jLabelNumtipe.setEnabled(false);
            
            jTextFieldNomEmpresa.setBackground(Color.red);
            jTextFieldDni.setBackground(Color.white);
            
        }else if (jRadioButtonJornada.isSelected()){
            jLabelCodicard.setEnabled(false);
            jLabelNumtipe.setEnabled(false);
            
            jTextFieldDni.setBackground(Color.red);
            jTextFieldNomEmpresa.setBackground(Color.white);
            
        }else if (jRadioButtonUsers.isSelected()){
            jLabelCodicard.setEnabled(false);
            jLabelNumtipe.setEnabled(true);
            
            jTextFieldDni.setBackground(Color.red);
            jTextFieldNomEmpresa.setBackground(Color.white);
            
        }else{
            jLabelCodicard.setEnabled(false);
            jLabelNumtipe.setEnabled(false);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JButton jButtonActualizarTabla;
    private javax.swing.JButton jButtonA�adirATabla;
    private javax.swing.JButton jButtonBorrar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCambiarContrase�a;
    private javax.swing.JButton jButtonLogout;
    private javax.swing.JCheckBox jCheckBoxBuscarVariosFiltros;
    private javax.swing.JComboBox<String> jComboBoxColumna;
    private javax.swing.JComboBox<String> jComboBoxColumna1;
    private javax.swing.JComboBox<String> jComboBoxColumna2;
    private javax.swing.JComboBox<String> jComboBoxOrdenar;
    private javax.swing.JComboBox<String> jComboBoxTablas;
    private javax.swing.JComboBox<String> jComboBoxTablasBorrar;
    private javax.swing.JComboBox<String> jComboBoxTablasUpdate;
    private javax.swing.JComboBox<String> jComboBoxTipoOperacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelApellido2;
    private javax.swing.JLabel jLabelAutores;
    private javax.swing.JLabel jLabelA�adirInfo;
    private javax.swing.JLabel jLabelA�adirInfo2;
    private javax.swing.JLabel jLabelCamposAIntroducir;
    private javax.swing.JLabel jLabelCodicard;
    private javax.swing.JLabel jLabelCodigo;
    private javax.swing.JLabel jLabelCodigo1;
    private javax.swing.JLabel jLabelCodigo2;
    private javax.swing.JLabel jLabelCodigo3;
    private javax.swing.JLabel jLabelColumna;
    private javax.swing.JLabel jLabelColumna1;
    private javax.swing.JLabel jLabelColumna2;
    private javax.swing.JLabel jLabelDescripcion;
    private javax.swing.JLabel jLabelHREntrada;
    private javax.swing.JLabel jLabelNombre2;
    private javax.swing.JLabel jLabelNumtipe;
    private javax.swing.JLabel jLabelOperacion;
    private javax.swing.JLabel jLabelOrdenar;
    private javax.swing.JLabel jLabelPalabra;
    private javax.swing.JLabel jLabelPalabra1;
    private javax.swing.JLabel jLabelPalabra2;
    private javax.swing.JLabel jLabelTabla;
    private javax.swing.JLabel jLabelTabla1;
    private javax.swing.JLabel jLabelTabla2;
    private javax.swing.JLabel jLabelUserCode;
    private javax.swing.JLabel jLabelUserCode1;
    private javax.swing.JLabel jLabelUserCode2;
    private javax.swing.JLabel jLabelUserCode3;
    private javax.swing.JLabel jLabelVariosFiltrosNoPosible;
    private javax.swing.JLabel jLabelVersion;
    private javax.swing.JLabel jLabel_Imagen;
    private javax.swing.JLabel jLabel_ImagenOtros;
    private javax.swing.JPanel jPanelAcercaDe;
    private javax.swing.JPanel jPanelActualizar;
    private javax.swing.JPanel jPanelA�adir;
    private javax.swing.JPanel jPanelBorrar;
    private javax.swing.JPanel jPanelBusqueda;
    private javax.swing.JPanel jPanelBusqueda1;
    private javax.swing.JPanel jPanelBusqueda2;
    private javax.swing.JPanel jPanelBusqueda3;
    private javax.swing.JPanel jPanelGestionUsuario;
    private javax.swing.JPanel jPanelOtros;
    private javax.swing.JPanel jPanelVariosFiltros;
    private javax.swing.JRadioButton jRadioButtonEmpleado;
    private javax.swing.JRadioButton jRadioButtonEmpresa;
    private javax.swing.JRadioButton jRadioButtonJornada;
    private javax.swing.JRadioButton jRadioButtonUsers;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextAreaDelete;
    private javax.swing.JTextArea jTextAreaInsert;
    private javax.swing.JTextArea jTextAreaSelect;
    private javax.swing.JTextArea jTextAreaUpdate;
    private javax.swing.JTextField jTextFieldAddress;
    private javax.swing.JTextField jTextFieldApellido;
    private javax.swing.JTextField jTextFieldApellido2;
    private javax.swing.JTextField jTextFieldCodicard;
    private javax.swing.JTextField jTextFieldDepartament;
    private javax.swing.JTextField jTextFieldDni;
    private javax.swing.JTextField jTextFieldLogin;
    private javax.swing.JTextField jTextFieldMail;
    private javax.swing.JTextField jTextFieldNom;
    private javax.swing.JTextField jTextFieldNom2;
    private javax.swing.JTextField jTextFieldNomEmpresa;
    private javax.swing.JTextField jTextFieldNumtipe;
    private javax.swing.JTextField jTextFieldPalabra;
    private javax.swing.JTextField jTextFieldPalabra1;
    private javax.swing.JTextField jTextFieldPalabra2;
    private javax.swing.JTextField jTextFieldPass;
    private javax.swing.JTextField jTextFieldTelephon;
    // End of variables declaration//GEN-END:variables
}
