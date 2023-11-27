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
import CRUD.Update;
import java.awt.Color;
import javax.swing.JComboBox;

/**
 *
 * @author Antonio Company Rodriguez
 * 
 * Clase para crear la ventana principal del programa una vez nos hemos logueado
 */
public class FormVentanasUsuario extends javax.swing.JFrame {

    private Socket socket;    
    private String insertEmpresas[];
    
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

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        FormVentanasUsuario.user = user;
    }
    
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
        return jLabelUserCodeInsert;
    }

    public void setjUserCode1(String nuevoTexto) {
        jLabelUserCodeInsert.setText(nuevoTexto);
    }
    
    public JLabel getjUserCode2() {
        return jLabelUserCodeDelete;
    }

    public void setjUserCode2(String nuevoTexto) {
        jLabelUserCodeDelete.setText(nuevoTexto);
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
        
        //Añadir icono
        ImageIcon icono = new ImageIcon(rutaImagen);
        setIconImage(icono.getImage());

        buttonGroup.add(jRadioButtonEmpresa);
        buttonGroup.add(jRadioButtonEmpleado);
        buttonGroup.add(jRadioButtonJornada);
        
        buttonGroup1.add(jRadioButtonEmpresaInsert);
        buttonGroup1.add(jRadioButtonEmpleadoInsert);
        buttonGroup1.add(jRadioButtonJornadaInsert);
        
        //JOptionPane.showMessageDialog(null,"codigo: "+codigo);
        if (codigo !=null && codigo.charAt(0) == 'A'){
             buttonGroup.add(jRadioButtonUsers);
             buttonGroup1.add(jRadioButtonUsersInsert);
             jRadioButtonUsers.setVisible(true);
             jComboBoxColumnaDelete.setEnabled(false);
             jLabelReferencia2Delete.setVisible(false);
             jLabelColumnaDelete2.setVisible(false);
             jComboBoxColumnaDelete2.setVisible(false);
             jTextFieldReferenciaDelete2.setVisible(false);
             
        }else{
            jComboBoxTablas.removeItemAt(1);
            jComboBoxTablaDelete.removeItemAt(1);
            jRadioButtonUsers.setVisible(false);
            
            jComboBoxTablaDelete.setVisible(false);
            jLabelCodigoDelete.setVisible(false);
            jLabelUserCodeDelete.setVisible(false);
            jTextFieldReferencia1Delete.setVisible(false);
            jLabelReferencia1Delete.setVisible(false);
            jLabelReferencia2Delete.setVisible(false);
            jComboBoxColumnaDelete.setVisible(false);
            jLabelTablaDelete.setVisible(false);
            jLabelColumnaDelete.setVisible(false);
            jButtonBorrar.setVisible(false);
            jLabelColumnaDelete2.setVisible(false);
            jComboBoxColumnaDelete2.setVisible(false);
            jTextFieldReferenciaDelete2.setVisible(false);
            System.out.println("Antes de ocultar jTextAreaDelete");
            jTextAreaDelete.setVisible(false);
            System.out.println("Después de ocultar jTextAreaDelete");
            jLabelResultadoDelete.setText("*CONSULTA CON UN USUARIO ADMINISTRADOR\nPARA PODER BORRAR ELEMENTOS EN LA APLICACIÓN");
        }           
        
        //ComboBox Busqueda y ordenar no visibles
        //Etiqueta añadir info y panel varios filtros no visibles
        jLabelOperacion.setVisible(false);
        jComboBoxTipoOperacion.setVisible(false);
        
        jLabelOrdenar.setVisible(false);
        jComboBoxOrdenar.setVisible(false);
        
        jLabelAñadirInfo.setVisible(false);
        
        jPanelVariosFiltros.setVisible(false);
        jLabelVariosFiltrosNoPosible.setVisible(false);
        
        jButtonAñadirATabla.setEnabled(false);
        
        activarDesactivarLabelsAñadir();
        
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
    * Método que al ejecutarse borra el contenido del comboBoxColumna
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
        buttonGroup1 = new javax.swing.ButtonGroup();
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
        jPanelAñadir = new javax.swing.JPanel();
        jPanelBusqueda3 = new javax.swing.JPanel();
        jButtonAñadirATabla = new javax.swing.JButton();
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
        jLabelSeleccionaTablaAñadir = new javax.swing.JLabel();
        jLabelCamposAIntroducir = new javax.swing.JLabel();
        jRadioButtonEmpresa = new javax.swing.JRadioButton();
        jRadioButtonEmpleado = new javax.swing.JRadioButton();
        jRadioButtonJornada = new javax.swing.JRadioButton();
        jRadioButtonUsers = new javax.swing.JRadioButton();
        jLabelAñadirInfo = new javax.swing.JLabel();
        jLabelNumtipe = new javax.swing.JLabel();
        jLabelCodicard = new javax.swing.JLabel();
        jLabelAñadirInfo2 = new javax.swing.JLabel();
        jPanelActualizar = new javax.swing.JPanel();
        jPanelBusqueda1 = new javax.swing.JPanel();
        jButtonActualizarTabla = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaUpdate = new javax.swing.JTextArea();
        jLabelCodigoInsert = new javax.swing.JLabel();
        jLabelReferenciaInsert = new javax.swing.JLabel();
        jLabelColumnaInsert = new javax.swing.JLabel();
        jComboBoxColumnaInsert = new javax.swing.JComboBox<>();
        jTextFieldReferenciaInsert = new javax.swing.JTextField();
        jLabelUserCodeInsert = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabelCamposAIntroducir1 = new javax.swing.JLabel();
        jTextFieldDni1 = new javax.swing.JTextField();
        jTextFieldDepartament1 = new javax.swing.JTextField();
        jTextFieldAddress1 = new javax.swing.JTextField();
        jTextFieldNom1 = new javax.swing.JTextField();
        jTextFieldCodicard1 = new javax.swing.JTextField();
        jTextFieldLogin1 = new javax.swing.JTextField();
        jTextFieldApellido1 = new javax.swing.JTextField();
        jTextFieldMail1 = new javax.swing.JTextField();
        jTextFieldPass1 = new javax.swing.JTextField();
        jTextFieldNomEmpresa1 = new javax.swing.JTextField();
        jTextFieldTelephon1 = new javax.swing.JTextField();
        jTextFieldNumtipe1 = new javax.swing.JTextField();
        jLabelNumtipe1 = new javax.swing.JLabel();
        jLabelCodicard1 = new javax.swing.JLabel();
        jLabelSeleccionaTablaInsert = new javax.swing.JLabel();
        jRadioButtonEmpresaInsert = new javax.swing.JRadioButton();
        jRadioButtonJornadaInsert = new javax.swing.JRadioButton();
        jRadioButtonEmpleadoInsert = new javax.swing.JRadioButton();
        jRadioButtonUsersInsert = new javax.swing.JRadioButton();
        jLabelActualizarInfo1 = new javax.swing.JLabel();
        jLabelActualizarInfo2 = new javax.swing.JLabel();
        jPanelBorrar = new javax.swing.JPanel();
        jPanelBusqueda2 = new javax.swing.JPanel();
        jButtonBorrar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaDelete = new javax.swing.JTextArea();
        jLabelCodigoDelete = new javax.swing.JLabel();
        jLabelTablaDelete = new javax.swing.JLabel();
        jLabelReferencia1Delete = new javax.swing.JLabel();
        jLabelColumnaDelete = new javax.swing.JLabel();
        jComboBoxTablaDelete = new javax.swing.JComboBox<>();
        jComboBoxColumnaDelete = new javax.swing.JComboBox<>();
        jTextFieldReferencia1Delete = new javax.swing.JTextField();
        jLabelUserCodeDelete = new javax.swing.JLabel();
        jLabelResultadoDelete = new javax.swing.JLabel();
        jLabelReferencia2Delete = new javax.swing.JLabel();
        jTextFieldReferenciaDelete2 = new javax.swing.JTextField();
        jLabelColumnaDelete2 = new javax.swing.JLabel();
        jComboBoxColumnaDelete2 = new javax.swing.JComboBox<>();
        jPanelGestionUsuario = new javax.swing.JPanel();
        jButtonLogout = new javax.swing.JButton();
        jButtonCambiarContraseña = new javax.swing.JButton();
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

        jLabelCodigo.setText("Código:");

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
        jLabel8.setText("RESULTADO BÚSQUEDA");

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
        jLabelVariosFiltrosNoPosible.setText("Opción no disponible");

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

        jTabbedPane1.addTab("Búsqueda", jPanelBusqueda);

        jPanelBusqueda3.setBackground(new java.awt.Color(204, 255, 204));

        jButtonAñadirATabla.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonAñadirATabla.setText("AÑADIR A TABLA");
        jButtonAñadirATabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAñadirATablaActionPerformed(evt);
            }
        });

        jTextAreaInsert.setColumns(20);
        jTextAreaInsert.setRows(5);
        jScrollPane4.setViewportView(jTextAreaInsert);

        jLabelCodigo3.setText("Código:");

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

        jLabelSeleccionaTablaAñadir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSeleccionaTablaAñadir.setText("Selecciona una tabla:");

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

        jLabelAñadirInfo.setText("Comienza por añadir una empresa");

        jLabelNumtipe.setText("0 = admin, 1 = usuario");

        jLabelCodicard.setText("codicard = solo números");

        javax.swing.GroupLayout jPanelBusqueda3Layout = new javax.swing.GroupLayout(jPanelBusqueda3);
        jPanelBusqueda3.setLayout(jPanelBusqueda3Layout);
        jPanelBusqueda3Layout.setHorizontalGroup(
            jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBusqueda3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSeleccionaTablaAñadir)
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
                        .addComponent(jButtonAñadirATabla))
                    .addComponent(jLabelAñadirInfo)
                    .addComponent(jLabelAñadirInfo2))
                .addGroup(jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBusqueda3Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addGroup(jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabelCamposAIntroducir)
                            .addGroup(jPanelBusqueda3Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanelBusqueda3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldDepartament, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldDni, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                        .addComponent(jTextFieldNumtipe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanelBusqueda3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
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
                        .addComponent(jLabelSeleccionaTablaAñadir)
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
                        .addComponent(jLabelAñadirInfo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelAñadirInfo2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAñadirATabla))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout jPanelAñadirLayout = new javax.swing.GroupLayout(jPanelAñadir);
        jPanelAñadir.setLayout(jPanelAñadirLayout);
        jPanelAñadirLayout.setHorizontalGroup(
            jPanelAñadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBusqueda3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelAñadirLayout.setVerticalGroup(
            jPanelAñadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBusqueda3, javax.swing.GroupLayout.PREFERRED_SIZE, 584, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Añadir", jPanelAñadir);

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

        jLabelCodigoInsert.setText("Código:");

        jLabelReferenciaInsert.setText("Referencia:");

        jLabelColumnaInsert.setText("Columna:");

        jComboBoxColumnaInsert.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "----------------", "dni", "nom", "apellido", "nomempresa", "departament", "codicard", "mail", "telephon" }));
        jComboBoxColumnaInsert.setEnabled(false);

        jLabelUserCodeInsert.setText("jLabelUserCode");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("RESULTADO ACTUALIZACIÓN");

        jLabelCamposAIntroducir1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelCamposAIntroducir1.setText("CAMPOS A MODIFICAR:");

        jTextFieldDni1.setText("dniNuevo");

        jTextFieldDepartament1.setText("departamentNuevo");

        jTextFieldAddress1.setText("addressNuevo");

        jTextFieldNom1.setText("nomNuevo");

        jTextFieldCodicard1.setText("codicardNuevo");

        jTextFieldLogin1.setText("loginNuevo");

        jTextFieldApellido1.setText("apellidoNuevo");

        jTextFieldMail1.setText("mailNuevo");

        jTextFieldPass1.setText("passNuevo");

        jTextFieldNomEmpresa1.setText("nomempresaNuevo");

        jTextFieldTelephon1.setText("telephonNuevo");

        jTextFieldNumtipe1.setText("numtipeNuevo");

        jLabelNumtipe1.setText("0 = admin, 1 = usuario");

        jLabelCodicard1.setText("codicard = solo números");

        jLabelSeleccionaTablaInsert.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSeleccionaTablaInsert.setText("Selecciona una tabla:");

        jRadioButtonEmpresaInsert.setBackground(new java.awt.Color(255, 255, 204));
        jRadioButtonEmpresaInsert.setText("empresa");
        jRadioButtonEmpresaInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonEmpresaInsertActionPerformed(evt);
            }
        });

        jRadioButtonJornadaInsert.setBackground(new java.awt.Color(255, 255, 204));
        jRadioButtonJornadaInsert.setText("jornada");
        jRadioButtonJornadaInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonJornadaInsertActionPerformed(evt);
            }
        });

        jRadioButtonEmpleadoInsert.setBackground(new java.awt.Color(255, 255, 204));
        jRadioButtonEmpleadoInsert.setText("empleados");
        jRadioButtonEmpleadoInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonEmpleadoInsertActionPerformed(evt);
            }
        });

        jRadioButtonUsersInsert.setBackground(new java.awt.Color(255, 255, 204));
        jRadioButtonUsersInsert.setText("users");
        jRadioButtonUsersInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonUsersInsertActionPerformed(evt);
            }
        });

        jLabelActualizarInfo1.setText("Introduce en referencia,");

        jLabelActualizarInfo2.setText("el dato del elemento a modificar.");

        javax.swing.GroupLayout jPanelBusqueda1Layout = new javax.swing.GroupLayout(jPanelBusqueda1);
        jPanelBusqueda1.setLayout(jPanelBusqueda1Layout);
        jPanelBusqueda1Layout.setHorizontalGroup(
            jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBusqueda1Layout.createSequentialGroup()
                .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBusqueda1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBusqueda1Layout.createSequentialGroup()
                                .addComponent(jLabelCodigoInsert)
                                .addGap(70, 70, 70)
                                .addComponent(jLabelUserCodeInsert))
                            .addComponent(jLabelSeleccionaTablaInsert)
                            .addComponent(jLabelActualizarInfo1)
                            .addComponent(jLabelActualizarInfo2)
                            .addGroup(jPanelBusqueda1Layout.createSequentialGroup()
                                .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButtonEmpresaInsert)
                                    .addComponent(jRadioButtonJornadaInsert)
                                    .addComponent(jLabelColumnaInsert))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxColumnaInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jRadioButtonEmpleadoInsert)
                                    .addComponent(jRadioButtonUsersInsert)))
                            .addGroup(jPanelBusqueda1Layout.createSequentialGroup()
                                .addComponent(jLabelReferenciaInsert)
                                .addGap(37, 37, 37)
                                .addComponent(jTextFieldReferenciaInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelBusqueda1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jButtonActualizarTabla)))
                .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBusqueda1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelBusqueda1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelCamposAIntroducir1)
                                .addGroup(jPanelBusqueda1Layout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextFieldAddress1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldDepartament1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldDni1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(23, 23, 23)
                                    .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelBusqueda1Layout.createSequentialGroup()
                                            .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jTextFieldCodicard1)
                                                .addComponent(jTextFieldNom1)
                                                .addComponent(jTextFieldLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jTextFieldPass1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTextFieldMail1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTextFieldApellido1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jLabelCodicard1))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextFieldNomEmpresa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldTelephon1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTextFieldNumtipe1, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelNumtipe1, javax.swing.GroupLayout.Alignment.LEADING)))))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(200, Short.MAX_VALUE))))
        );
        jPanelBusqueda1Layout.setVerticalGroup(
            jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBusqueda1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBusqueda1Layout.createSequentialGroup()
                        .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelUserCodeInsert)
                            .addComponent(jLabelCodigoInsert))
                        .addGap(38, 38, 38)
                        .addComponent(jLabelSeleccionaTablaInsert)
                        .addGap(18, 18, 18)
                        .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButtonEmpresaInsert)
                            .addComponent(jRadioButtonEmpleadoInsert))
                        .addGap(7, 7, 7)
                        .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButtonJornadaInsert)
                            .addComponent(jRadioButtonUsersInsert))
                        .addGap(28, 28, 28)
                        .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxColumnaInsert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelColumnaInsert))
                        .addGap(24, 24, 24)
                        .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelReferenciaInsert)
                            .addComponent(jTextFieldReferenciaInsert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addComponent(jLabelActualizarInfo1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelActualizarInfo2)
                        .addGap(158, 158, 158)
                        .addComponent(jButtonActualizarTabla))
                    .addGroup(jPanelBusqueda1Layout.createSequentialGroup()
                        .addComponent(jLabelCamposAIntroducir1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldDni1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldApellido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNomEmpresa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldDepartament1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldCodicard1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldMail1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTelephon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addComponent(jLabelCodicard1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelBusqueda1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldPass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNumtipe1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldAddress1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelNumtipe1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
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
        jButtonBorrar.setEnabled(false);
        jButtonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrarActionPerformed(evt);
            }
        });

        jTextAreaDelete.setColumns(20);
        jTextAreaDelete.setRows(5);
        jScrollPane3.setViewportView(jTextAreaDelete);

        jLabelCodigoDelete.setText("Código:");

        jLabelTablaDelete.setText("Tabla:");

        jLabelReferencia1Delete.setText("Referencia 1:");

        jLabelColumnaDelete.setText("Columna:");

        jComboBoxTablaDelete.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "empleados", "users", "empresa", "jornada" }));
        jComboBoxTablaDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTablaDeleteActionPerformed(evt);
            }
        });

        jComboBoxColumnaDelete.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "dni", "nom" }));

        jTextFieldReferencia1Delete.setPreferredSize(new java.awt.Dimension(121, 22));
        jTextFieldReferencia1Delete.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldReferencia1DeleteKeyReleased(evt);
            }
        });

        jLabelUserCodeDelete.setText("jLabelUserCode");

        jLabelResultadoDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelResultadoDelete.setText("RESULTADO");

        jLabelReferencia2Delete.setText("Referencia 2:");

        jTextFieldReferenciaDelete2.setPreferredSize(new java.awt.Dimension(121, 22));
        jTextFieldReferenciaDelete2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldReferenciaDelete2KeyReleased(evt);
            }
        });

        jLabelColumnaDelete2.setText("Columna 2:");

        jComboBoxColumnaDelete2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "fecha" }));

        javax.swing.GroupLayout jPanelBusqueda2Layout = new javax.swing.GroupLayout(jPanelBusqueda2);
        jPanelBusqueda2.setLayout(jPanelBusqueda2Layout);
        jPanelBusqueda2Layout.setHorizontalGroup(
            jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBusqueda2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBusqueda2Layout.createSequentialGroup()
                        .addGroup(jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonBorrar)
                            .addComponent(jLabelColumnaDelete)
                            .addComponent(jLabelTablaDelete)
                            .addComponent(jLabelReferencia1Delete)
                            .addComponent(jLabelCodigoDelete))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxTablaDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelUserCodeDelete)
                            .addComponent(jComboBoxColumnaDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldReferencia1Delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelBusqueda2Layout.createSequentialGroup()
                        .addGroup(jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelColumnaDelete2)
                            .addComponent(jLabelReferencia2Delete))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxColumnaDelete2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldReferenciaDelete2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(43, 43, 43)
                .addGroup(jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelResultadoDelete)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(149, Short.MAX_VALUE))
        );
        jPanelBusqueda2Layout.setVerticalGroup(
            jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBusqueda2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCodigoDelete)
                    .addComponent(jLabelUserCodeDelete)
                    .addComponent(jLabelResultadoDelete))
                .addGroup(jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBusqueda2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanelBusqueda2Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTablaDelete)
                            .addComponent(jComboBoxTablaDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58)
                        .addGroup(jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelColumnaDelete)
                            .addComponent(jComboBoxColumnaDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelReferencia1Delete)
                            .addComponent(jTextFieldReferencia1Delete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelColumnaDelete2)
                            .addComponent(jComboBoxColumnaDelete2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelBusqueda2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelReferencia2Delete)
                            .addComponent(jTextFieldReferenciaDelete2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)))
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

        jButtonCambiarContraseña.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButtonCambiarContraseña.setText("Cambiar contraseña usuario");
        jButtonCambiarContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCambiarContraseñaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelGestionUsuarioLayout = new javax.swing.GroupLayout(jPanelGestionUsuario);
        jPanelGestionUsuario.setLayout(jPanelGestionUsuarioLayout);
        jPanelGestionUsuarioLayout.setHorizontalGroup(
            jPanelGestionUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGestionUsuarioLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanelGestionUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCambiarContraseña)
                    .addComponent(jButtonLogout))
                .addContainerGap(758, Short.MAX_VALUE))
        );
        jPanelGestionUsuarioLayout.setVerticalGroup(
            jPanelGestionUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGestionUsuarioLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jButtonCambiarContraseña)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 353, Short.MAX_VALUE)
                .addComponent(jButtonLogout)
                .addGap(43, 43, 43))
        );

        jTabbedPane1.addTab("Gestión usuario", jPanelGestionUsuario);

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
                .addContainerGap(284, Short.MAX_VALUE)
                .addComponent(jLabel_ImagenOtros)
                .addGap(60, 60, 60))
        );

        jTabbedPane1.addTab("Otros", jPanelOtros);

        jPanelAcercaDe.setBackground(new java.awt.Color(153, 153, 153));

        jLabelHREntrada.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabelHREntrada.setText("HREntrada ©");

        jLabelDescripcion.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabelDescripcion.setText("Trabajo final de CFGS DAM");

        jLabelAutores.setFont(new java.awt.Font("Dialog", 2, 24)); // NOI18N
        jLabelAutores.setText("By David Valentin, Gustavo Señorans y Antonio Company");

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
                .addContainerGap(85, Short.MAX_VALUE))
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
    * Método que al ejecutarse abre una ventana del tipo CambiarPasswordForm
    *
    * @param evt Evento de acción que se dispara al clicar en el boton
    * cambiar contraseña
    * 
    */
    
    private void jButtonCambiarContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCambiarContraseñaActionPerformed
        CambiarPasswordForm ventanaCambioPass = new CambiarPasswordForm (jComboBoxTipoOperacion,this,user, insertEmpresas, escriptor, perEnt, socket, jTextAreaUpdate);
        ventanaCambioPass.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        ventanaCambioPass.setVisible(true);
    }//GEN-LAST:event_jButtonCambiarContraseñaActionPerformed

    /**
    * Método que ejecuta el método logout()
    *
    * @param evt Evento de acción que se dispara al clicar en el boton
    * logout session 
    * 
    */
    
    private void jButtonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogoutActionPerformed
        Logout.logout(this);
        
    }//GEN-LAST:event_jButtonLogoutActionPerformed

    /**
    * Método que al ejecutarse asigna unos valores a unas variables booleanas
    * y después ejecuta el método ejecutarAccion();
    *
    * @param evt Evento de acción que se dispara al clicar en el 
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
    * Método que al ejecutarse asigna unos valores a unas variables booleanas
    * y después ejecuta el método ejecutarAccion();
    *
    * @param evt Evento de acción que se dispara al clicar en el 
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
    * Método que al ejecutarse asigna unos valores a unas variables booleanas
    * y después ejecuta el método ejecutarAccion();
    *
    * @param evt Evento de acción que se dispara al clicar en el 
    * boton Actualizar
    * 
    */
    
    private void jButtonActualizarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarTablaActionPerformed
        update = true;

        select = false;
        insert = false;
        delete = false;
        
        jComboBoxTipoOperacion.setSelectedItem("update");

        if (jRadioButtonEmpresaInsert.isSelected()){
            nombreTabla = "empresa";
        }else if (jRadioButtonEmpleadoInsert.isSelected()){
            nombreTabla = "empleados";
        }else if (jRadioButtonJornadaInsert.isSelected()){
            nombreTabla = "jornada";
        }else if (jRadioButtonUsersInsert.isSelected()){
            nombreTabla = "users";
        }

        ejecutarAccion();
    }//GEN-LAST:event_jButtonActualizarTablaActionPerformed

    /**
    * Método que ejecuta los métodos activarDesactivarTextFieldsAñadir() y
    * cambiarTextoActivarDesactivarLabelInfoAñadir()
    *
    * @param evt Evento de acción que se dispara al clicar en el 
    * radio button users.
    * 
    */
    
    private void jRadioButtonUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonUsersActionPerformed
        activarDesactivarLabelsAñadir();
        activarDesactivarTextFieldsAñadir();
        cambiarTextoActivarDesactivarLabelInfoAñadir();
    }//GEN-LAST:event_jRadioButtonUsersActionPerformed

    /**
    * Método que ejecuta los métodos activarDesactivarTextFieldsAñadir() y
    * cambiarTextoActivarDesactivarLabelInfoAñadir()
    *
    * @param evt Evento de acción que se dispara al clicar en el 
    * radio button jornada.
    * 
    */
    
    private void jRadioButtonJornadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonJornadaActionPerformed
        activarDesactivarLabelsAñadir();
        activarDesactivarTextFieldsAñadir();
        cambiarTextoActivarDesactivarLabelInfoAñadir();
    }//GEN-LAST:event_jRadioButtonJornadaActionPerformed

    /**
    * Método que ejecuta los métodos activarDesactivarTextFieldsAñadir() y
    * cambiarTextoActivarDesactivarLabelInfoAñadir()
    *
    * @param evt Evento de acción que se dispara al clicar en el 
    * radio button empleados.
    * 
    */
    
    private void jRadioButtonEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonEmpleadoActionPerformed
        activarDesactivarLabelsAñadir();
        activarDesactivarTextFieldsAñadir();
        cambiarTextoActivarDesactivarLabelInfoAñadir();
    }//GEN-LAST:event_jRadioButtonEmpleadoActionPerformed

    /**
    * Método que ejecuta los métodos activarDesactivarTextFieldsAñadir() y
    * cambiarTextoActivarDesactivarLabelInfoAñadir()
    *
    * @param evt Evento de acción que se dispara al clicar en el 
    * radio button empresa.
    * 
    */
    private void jRadioButtonEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonEmpresaActionPerformed
        activarDesactivarLabelsAñadir();
        activarDesactivarTextFieldsAñadir();
        cambiarTextoActivarDesactivarLabelInfoAñadir();
    }//GEN-LAST:event_jRadioButtonEmpresaActionPerformed

    /**
    * Método que asigna valores a ciertas variables tipo booleanas y al String nombreTabla
    * y después ejecuta el método principal ejecutarAccion();
    *
    * @param evt Evento de acción que se dispara al clicar en el 
    * botton Añadir.
    */
    
    private void jButtonAñadirATablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAñadirATablaActionPerformed
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
    }//GEN-LAST:event_jButtonAñadirATablaActionPerformed

    
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

    private void jRadioButtonEmpresaInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonEmpresaInsertActionPerformed
        // TODO add your handling code here:
        activarDesactivarLabelsActualizar();
        activarDesactivarTextFieldsActualizar();
        cambiarTextoActivarDesactivarLabelInfoActualizar();
    }//GEN-LAST:event_jRadioButtonEmpresaInsertActionPerformed

    private void jRadioButtonJornadaInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonJornadaInsertActionPerformed
        // TODO add your handling code here:
        activarDesactivarLabelsActualizar();
        activarDesactivarTextFieldsActualizar();
        cambiarTextoActivarDesactivarLabelInfoActualizar();
    }//GEN-LAST:event_jRadioButtonJornadaInsertActionPerformed

    private void jRadioButtonEmpleadoInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonEmpleadoInsertActionPerformed
        // TODO add your handling code here:
        activarDesactivarLabelsActualizar();
        activarDesactivarTextFieldsActualizar();
        cambiarTextoActivarDesactivarLabelInfoActualizar();
    }//GEN-LAST:event_jRadioButtonEmpleadoInsertActionPerformed

    private void jRadioButtonUsersInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonUsersInsertActionPerformed
        activarDesactivarLabelsActualizar();
        activarDesactivarTextFieldsActualizar();
        cambiarTextoActivarDesactivarLabelInfoActualizar();
    }//GEN-LAST:event_jRadioButtonUsersInsertActionPerformed

    private void jComboBoxTablaDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTablaDeleteActionPerformed
        modificarComboBoxColumnasBorrar();
    }//GEN-LAST:event_jComboBoxTablaDeleteActionPerformed

    private void jTextFieldReferencia1DeleteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldReferencia1DeleteKeyReleased
        if (jComboBoxTablaDelete.getSelectedItem().toString().equals("jornada")){
            if ((jTextFieldReferenciaDelete2.getText()==""|| jTextFieldReferenciaDelete2.getText().isEmpty())||
                (jTextFieldReferencia1Delete.equals("")|| jTextFieldReferencia1Delete.getText().isEmpty())){
            jButtonBorrar.setEnabled(false);
            }else{
                jButtonBorrar.setEnabled(true);
            }    
        }
        else //No tenmemos seleccionada la tabla jornada
        {
          if (jTextFieldReferencia1Delete.getText()==""|| jTextFieldReferencia1Delete.getText().isEmpty()){
            jButtonBorrar.setEnabled(false);
            }else{
                jButtonBorrar.setEnabled(true);
            }  
        }
    }//GEN-LAST:event_jTextFieldReferencia1DeleteKeyReleased

    private void jTextFieldReferenciaDelete2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldReferenciaDelete2KeyReleased
        if ((jTextFieldReferenciaDelete2.getText()==""|| jTextFieldReferenciaDelete2.getText().isEmpty())||
                (jTextFieldReferencia1Delete.equals("")|| jTextFieldReferencia1Delete.getText().isEmpty())){
            jButtonBorrar.setEnabled(false);
        }else{
            jButtonBorrar.setEnabled(true);
        }
    }//GEN-LAST:event_jTextFieldReferenciaDelete2KeyReleased

    private void modificarComboBoxColumnasBorrar(){
        if (jComboBoxTablaDelete.getSelectedItem().toString().equals("empleados")){         
            jLabelReferencia1Delete.setEnabled(true);
            jLabelReferencia2Delete.setVisible(false);
            jLabelColumnaDelete2.setVisible(false);
            
            jComboBoxColumnaDelete.setSelectedItem("dni");
            jComboBoxColumnaDelete2.setVisible(false);
            
            jTextFieldReferenciaDelete2.setVisible(false);
            
        }else if (jComboBoxTablaDelete.getSelectedItem().toString().equals("empresa")){
            jComboBoxColumnaDelete.setSelectedItem("nom");
            jComboBoxColumnaDelete2.setVisible(false);
            
            jLabelReferencia1Delete.setEnabled(true);
            jLabelReferencia2Delete.setVisible(false);
            jLabelColumnaDelete2.setVisible(false);
                     
            jTextFieldReferenciaDelete2.setVisible(false);
            
        }else if (jComboBoxTablaDelete.getSelectedItem().toString().equals("users")){
             jComboBoxColumnaDelete.setSelectedItem("dni");
             jLabelReferencia1Delete.setEnabled(true);
             jLabelReferencia2Delete.setVisible(false);
             jLabelColumnaDelete2.setVisible(false);
             jComboBoxColumnaDelete2.setVisible(false);
             jTextFieldReferenciaDelete2.setVisible(false);
             
        }else if (jComboBoxTablaDelete.getSelectedItem().toString().equals("jornada")){
             jComboBoxColumnaDelete.setSelectedItem("dni");
             jComboBoxColumnaDelete2.setVisible(true);
             jComboBoxColumnaDelete2.setSelectedItem("fecha");
             
             jLabelReferencia1Delete.setEnabled(true);        
             jLabelReferencia2Delete.setVisible(true);
             jLabelColumnaDelete2.setVisible(true);
             
             jTextFieldReferenciaDelete2.setVisible(true);
             
             jButtonBorrar.setEnabled(false);
        }
    }
    
     /**
    * Método que gestiona el estado activo/inactivo del boton Buscar
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
    * Método que maneja los componentes a mostrar o no y habilitar o no
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
    * Método principal de accion de la clase, el cual contiene toda la lógica asociada
    * a métodos para buscar, añadir, borrar, actualizar datos mediante conexión con 
    * socket con el servidor
    * 
    */
    
     public void ejecutarAccion(){
        try {
            // TODO add your handling code here:
            socket = MainForm.getSocket();
            
            lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));//flujo lectura del server
            escriptor = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));//flujo envio al server
            
            
            codigo = jLabelUserCode.getText(); //el codigo recibido tiene que ser el mismo que le hemos asignado
            crud = jComboBoxTipoOperacion.getSelectedItem().toString(); //Tipo de operacion
            if (select){
                nombreTabla = jComboBoxTablas.getSelectedItem().toString(); //Será el numero de tabla. (ej: 0->empleados 1->users 2-empresa 3->jornada) 
            }

            if (select){
                 columna = jComboBoxColumna.getSelectedItem().toString(); //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
            }
           
            String palabraAbuscar = jTextFieldPalabra.getText();// si es el caso será la columna (,dni,nom,etc), si no hay ponemos 0
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
                         
                    }else if (update){
                        palabraAbuscar = "dniNuevo,"+jTextFieldDni1.getText()+",nomNuevo,"+jTextFieldNom1.getText()+",apellidoNuevo,"+jTextFieldApellido1.getText()
                            +",nomempresaNuevo,"+jTextFieldNomEmpresa1.getText()+",departamentNuevo,"+jTextFieldDepartament1.getText()
                            +",codicardNuevo,"+jTextFieldCodicard1.getText()+",mailNuevo,"+jTextFieldMail1.getText()+",telephonNuevo,"+jTextFieldTelephon1.getText()
                                +",dni,"+jTextFieldReferenciaInsert.getText();
                    }
                    else{
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
                        
                    }else if (update){
                        palabraAbuscar = "passNuevo," + jTextFieldPass1.getText() + ",numtipeNuevo,"+jTextFieldNumtipe1.getText()
                                +",login,"+jTextFieldReferenciaInsert.getText()
                            ;
                    }
                    break;
                case "empresa":
                    nombreTabla = "2";
                    if (insert){
                        palabraAbuscar = "nom,"+jTextFieldNom.getText()+",address,"+jTextFieldAddress.getText()+",telephon,"+jTextFieldTelephon.getText(); 
                        
                    } else if (update){
                        palabraAbuscar = "nomNuevo,"+jTextFieldNom1.getText()+",addressNuevo,"+jTextFieldAddress1.getText()
                                +",telephonNuevo,"+jTextFieldTelephon1.getText()+",nom,"+jTextFieldReferenciaInsert.getText();
                    }
                    break;
                case "jornada":
                    nombreTabla = "3";
                    if (insert){
                        palabraAbuscar = "dni,"+jTextFieldDni.getText();
                        
                    }else if (update){
                        palabraAbuscar = "dni,"+jTextFieldDni1.getText();
                        
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
            }else if (update){
                palabra = codigo + "," + crud + "," + nombreTabla + "," + palabraAbuscar + "," + orden;
                jTextAreaUpdate.append("Enviado al server: "+palabra+"\n");
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
            this.insertEmpresas=insertEmpresas;

            String[] insertUsuarios = new String[12];
            insertUsuarios = palabra.split(",");

            String[] insertEmpleadoMailTelf = new String[16];
            insertEmpleadoMailTelf = palabra.split(",");

            String[] insertEmpleadoMT = new String[18];
            insertEmpleadoMT = palabra.split(",");

            String[] insertEmpleado = new String[20];
            insertEmpleado = palabra.split(",");
            
            String[] updateEmpleado = new String[22];
            updateEmpleado = palabra.split(",");

            if (!codigo.equals(frase[0]) || !codigo.equals(NomApellido[0])//If para controlar que el codigo de usuario utilizado al hacer la peticion al server no es erroneo
                    || !codigo.equals(insertEmpresas[0]) || !codigo.equals(insertUsuarios[0])
                    || !codigo.equals(insertEmpleadoMailTelf[0]) || !codigo.equals(insertEmpleadoMT[0])
                    || !codigo.equals(insertEmpleado[0])) {

                jTextAreaSelect.append("El codigo es erroneo");

            } else if (frase[5].equals("0") || frase[5].equals("1")) {//Tanto si ordenamos como no ordenamos se cumplira este else if
                //JOptionPane.showMessageDialog(null, "frase5equals0 or 1");
                
                codigoUserRecibido = frase[0]; //el codigo recibido tiene que ser el mismo que le hemos asignado
                crud = frase[1];
                nombreTabla = frase[2]; //Será el numero de tabla. (ej: 1->empleados 2->users 3-jornada 4-usertipe 5->empresa)
                columna = frase[3]; //sera la palabra que busquemos(ej: juan,1234567D), si ponemos 0 sera todos los de la tabla
                palabraAbuscar = frase[4];// si es el caso será la columna (,dni,nom,etc), si no hay ponemos 0
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
                
            }else if (NomApellido[1].equals("2") && NomApellido[2].equals("2") && NomApellido[3].equals("nomNuevo") && NomApellido[9].equals("nom")) {    
                JOptionPane.showMessageDialog(null, "updateEmpresa");
                Update.updateEmpresa(NomApellido, palabra, escriptor, perEnt, socket, jTextAreaUpdate);
                
            }else if (insertEmpresas[1].equals("2") && insertEmpresas[2].equals("1") && insertEmpresas[3].equals("passNuevo")) {
                JOptionPane.showMessageDialog(null, "updateUser");
                Update.updateUser(insertEmpresas, palabra, escriptor, perEnt, socket, jTextAreaUpdate);
                
            }else if (updateEmpleado[1].equals("2") && updateEmpleado[2].equals("0") && updateEmpleado[3].equals("dniNuevo")) {
                JOptionPane.showMessageDialog(null, "updateEmpleado");
                Update.updateEmpleado(updateEmpleado, palabra, escriptor, perEnt, socket, jTextAreaUpdate);
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
     * Método main para poder ejecutar rápidamente sin ejecutar la aplicación
     * y comprobar de manera mas rápida los cambios que voy haciendo.
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
    * Método que gestiona el estado activo/inactivo del boton de Buscar
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
    * Método que limpia el contenido del textField Palabra cuando en el comboBox
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
    * Método que maneja el contenido de una etiqueta informativa en la pestaña
    * de añadir
    * 
    */
    
    private void cambiarTextoActivarDesactivarLabelInfoAñadir(){
        if (jRadioButtonEmpresa.isSelected()){
            jLabelAñadirInfo.setVisible(true);
            jLabelAñadirInfo.setText("Comienza por añadir una empresa");
            jLabelAñadirInfo2.setText("");
            jButtonAñadirATabla.setEnabled(true);
              
        }else if (jRadioButtonEmpleado.isSelected()){
            jLabelAñadirInfo.setVisible(true);
            jLabelAñadirInfo.setText("Introduce nom empresa existente");
            jLabelAñadirInfo2.setText("");
             jButtonAñadirATabla.setEnabled(true);
            
        }else if (jRadioButtonJornada.isSelected()){
            jLabelAñadirInfo.setVisible(true);
            jLabelAñadirInfo.setText("Introduce dni empleado existente");
            jLabelAñadirInfo2.setText("");
            jButtonAñadirATabla.setEnabled(true);
            
        }else if (jRadioButtonUsers.isSelected()){
            jLabelAñadirInfo.setVisible(true);
            jLabelAñadirInfo.setText("Introduce dni empleado existente");
            jLabelAñadirInfo2.setText("que no tenga usuario asociado");
            jButtonAñadirATabla.setEnabled(true);
        
        }else{  
            jLabelAñadirInfo.setVisible (false);
        }
    }
    
    /**
    * Método que maneja el contenido de una etiqueta informativa en la pestaña
    * de Actualizar
    * 
    */
    
    private void cambiarTextoActivarDesactivarLabelInfoActualizar(){
        if (jRadioButtonEmpresa.isSelected()){
            jLabelAñadirInfo.setVisible(true);
            jLabelAñadirInfo.setText("Comienza por añadir una empresa");
            jLabelAñadirInfo2.setText("");
            jButtonAñadirATabla.setEnabled(true);
              
        }else if (jRadioButtonEmpleado.isSelected()){
            jLabelAñadirInfo.setVisible(true);
            jLabelAñadirInfo.setText("Introduce nom empresa existente");
            jLabelAñadirInfo2.setText("");
             jButtonAñadirATabla.setEnabled(true);
            
        }else if (jRadioButtonJornada.isSelected()){
            jLabelAñadirInfo.setVisible(true);
            jLabelAñadirInfo.setText("Introduce dni empleado existente");
            jLabelAñadirInfo2.setText("");
            jButtonAñadirATabla.setEnabled(true);
            
        }else if (jRadioButtonUsers.isSelected()){
            jLabelAñadirInfo.setVisible(true);
            jLabelAñadirInfo.setText("Introduce dni empleado existente");
            jLabelAñadirInfo2.setText("que no tenga usuario asociado");
            jButtonAñadirATabla.setEnabled(true);
        
        }else{  
            jLabelAñadirInfo.setVisible (false);
        }
    }
    
    /**
    * Método que maneja los estados activo o inactivo de los textFields de
    * la pestaña de Añadir, segun el radio button de tabla seleccionada
    *
    */
    
    private void activarDesactivarTextFieldsAñadir(){
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
    * Método que maneja los estados activo o inactivo de los textFields de
    * la pestaña de Actualizar, segun el radio button de tabla seleccionada
    *
    */
    
    private void activarDesactivarTextFieldsActualizar(){
        if (jRadioButtonEmpresaInsert.isSelected()){
            jComboBoxColumnaInsert.setSelectedItem("nom");
            
            jTextFieldNom1.setEnabled(true);
            jTextFieldAddress1.setEnabled(true);
            jTextFieldTelephon1.setEnabled(true);

            jTextFieldDni1.setEnabled(false);
            jTextFieldApellido1.setEnabled(false);
            jTextFieldNomEmpresa1.setEnabled(false);
            jTextFieldDepartament1.setEnabled(false);
            jTextFieldCodicard1.setEnabled(false);
            jTextFieldMail1.setEnabled(false);
            jTextFieldLogin1.setEnabled(false);
            jTextFieldPass1.setEnabled(false);
            jTextFieldNumtipe1.setEnabled(false);
        }else if (jRadioButtonEmpleadoInsert.isSelected()){
            jComboBoxColumnaInsert.setSelectedItem("dni");
            
            jTextFieldDni1.setEnabled(true);
            jTextFieldNom1.setEnabled(true);
            jTextFieldApellido1.setEnabled(true);
            jTextFieldNomEmpresa1.setEnabled(true);
            jTextFieldDepartament1.setEnabled(true);
            jTextFieldCodicard1.setEnabled(true);
            jTextFieldMail1.setEnabled(true);
            jTextFieldTelephon1.setEnabled(true);

            jTextFieldAddress1.setEnabled(false);   
            jTextFieldLogin1.setEnabled(false);
            jTextFieldPass1.setEnabled(false);
            jTextFieldNumtipe1.setEnabled(false);
        }else if (jRadioButtonJornadaInsert.isSelected()){
            jComboBoxColumnaInsert.setSelectedItem("todas");
            jTextFieldDni.setEnabled(true);
            
            jTextFieldCodicard1.setEnabled(false);
            jTextFieldNom1.setEnabled(false);
            jTextFieldApellido1.setEnabled(false);
            jTextFieldNomEmpresa1.setEnabled(false);
            jTextFieldDepartament1.setEnabled(false);    
            jTextFieldMail1.setEnabled(false);
            jTextFieldTelephon1.setEnabled(false);        
            jTextFieldAddress1.setEnabled(false);   
            jTextFieldLogin1.setEnabled(false);
            jTextFieldPass1.setEnabled(false);
            jTextFieldNumtipe1.setEnabled(false);
        }else if (jRadioButtonUsersInsert.isSelected()){
            jComboBoxColumnaInsert.setSelectedItem("login");
            
            jTextFieldLogin1.setEnabled(true);
            jTextFieldPass1.setEnabled(true);
            jTextFieldNumtipe1.setEnabled(true);
            
            jTextFieldDni1.setEnabled(false);
            jTextFieldCodicard1.setEnabled(false);       
            jTextFieldNom1.setEnabled(false);
            jTextFieldApellido1.setEnabled(false);
            jTextFieldNomEmpresa1.setEnabled(false);
            jTextFieldDepartament1.setEnabled(false);    
            jTextFieldMail1.setEnabled(false);
            jTextFieldTelephon1.setEnabled(false);        
            jTextFieldAddress1.setEnabled(false);
        }else{
            jTextFieldLogin1.setEnabled(false);
            jTextFieldPass1.setEnabled(false);
            jTextFieldNumtipe1.setEnabled(false);
            jTextFieldDni1.setEnabled(false);
            jTextFieldCodicard1.setEnabled(false);       
            jTextFieldNom1.setEnabled(false);
            jTextFieldApellido1.setEnabled(false);
            jTextFieldNomEmpresa1.setEnabled(false);
            jTextFieldDepartament1.setEnabled(false);    
            jTextFieldMail1.setEnabled(false);
            jTextFieldTelephon1.setEnabled(false);        
            jTextFieldAddress1.setEnabled(false); 
        }
    }
    
    /**
    * Método que maneja los estados visible o no de etiquetas de
    * la pestaña de Añadir, segun el radio button de tabla seleccionado
    *
    */
    
    private void activarDesactivarLabelsAñadir(){
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
    
    /**
    * Método que maneja los estados visible o no de etiquetas de
    * la pestaña de Actualizar, segun el radio button de tabla seleccionado
    *
    */
    
    private void activarDesactivarLabelsActualizar(){
        if (jRadioButtonEmpresaInsert.isSelected()){       
            jLabelCodicard1.setEnabled(false);
            jLabelNumtipe1.setEnabled(false);
            
        }else if (jRadioButtonEmpleadoInsert.isSelected()){
            jLabelCodicard1.setEnabled(true);
            jLabelNumtipe1.setEnabled(false);
            
        }else if (jRadioButtonJornadaInsert.isSelected()){
            jLabelCodicard1.setEnabled(false);
            jLabelNumtipe1.setEnabled(false);
            
        }else if (jRadioButtonUsersInsert.isSelected()){
            jLabelCodicard1.setEnabled(false);
            jLabelNumtipe1.setEnabled(true);
            
        }else{
            jLabelCodicard1.setEnabled(false);
            jLabelNumtipe1.setEnabled(false);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonActualizarTabla;
    private javax.swing.JButton jButtonAñadirATabla;
    private javax.swing.JButton jButtonBorrar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCambiarContraseña;
    private javax.swing.JButton jButtonLogout;
    private javax.swing.JCheckBox jCheckBoxBuscarVariosFiltros;
    private javax.swing.JComboBox<String> jComboBoxColumna;
    private javax.swing.JComboBox<String> jComboBoxColumnaDelete;
    private javax.swing.JComboBox<String> jComboBoxColumnaDelete2;
    private javax.swing.JComboBox<String> jComboBoxColumnaInsert;
    private javax.swing.JComboBox<String> jComboBoxOrdenar;
    private javax.swing.JComboBox<String> jComboBoxTablaDelete;
    private javax.swing.JComboBox<String> jComboBoxTablas;
    private javax.swing.JComboBox<String> jComboBoxTipoOperacion;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelActualizarInfo1;
    private javax.swing.JLabel jLabelActualizarInfo2;
    private javax.swing.JLabel jLabelApellido2;
    private javax.swing.JLabel jLabelAutores;
    private javax.swing.JLabel jLabelAñadirInfo;
    private javax.swing.JLabel jLabelAñadirInfo2;
    private javax.swing.JLabel jLabelCamposAIntroducir;
    private javax.swing.JLabel jLabelCamposAIntroducir1;
    private javax.swing.JLabel jLabelCodicard;
    private javax.swing.JLabel jLabelCodicard1;
    private javax.swing.JLabel jLabelCodigo;
    private javax.swing.JLabel jLabelCodigo3;
    private javax.swing.JLabel jLabelCodigoDelete;
    private javax.swing.JLabel jLabelCodigoInsert;
    private javax.swing.JLabel jLabelColumna;
    private javax.swing.JLabel jLabelColumnaDelete;
    private javax.swing.JLabel jLabelColumnaDelete2;
    private javax.swing.JLabel jLabelColumnaInsert;
    private javax.swing.JLabel jLabelDescripcion;
    private javax.swing.JLabel jLabelHREntrada;
    private javax.swing.JLabel jLabelNombre2;
    private javax.swing.JLabel jLabelNumtipe;
    private javax.swing.JLabel jLabelNumtipe1;
    private javax.swing.JLabel jLabelOperacion;
    private javax.swing.JLabel jLabelOrdenar;
    private javax.swing.JLabel jLabelPalabra;
    private javax.swing.JLabel jLabelReferencia1Delete;
    private javax.swing.JLabel jLabelReferencia2Delete;
    private javax.swing.JLabel jLabelReferenciaInsert;
    private javax.swing.JLabel jLabelResultadoDelete;
    private javax.swing.JLabel jLabelSeleccionaTablaAñadir;
    private javax.swing.JLabel jLabelSeleccionaTablaInsert;
    private javax.swing.JLabel jLabelTabla;
    private javax.swing.JLabel jLabelTablaDelete;
    private javax.swing.JLabel jLabelUserCode;
    private javax.swing.JLabel jLabelUserCode3;
    private javax.swing.JLabel jLabelUserCodeDelete;
    private javax.swing.JLabel jLabelUserCodeInsert;
    private javax.swing.JLabel jLabelVariosFiltrosNoPosible;
    private javax.swing.JLabel jLabelVersion;
    private javax.swing.JLabel jLabel_Imagen;
    private javax.swing.JLabel jLabel_ImagenOtros;
    private javax.swing.JPanel jPanelAcercaDe;
    private javax.swing.JPanel jPanelActualizar;
    private javax.swing.JPanel jPanelAñadir;
    private javax.swing.JPanel jPanelBorrar;
    private javax.swing.JPanel jPanelBusqueda;
    private javax.swing.JPanel jPanelBusqueda1;
    private javax.swing.JPanel jPanelBusqueda2;
    private javax.swing.JPanel jPanelBusqueda3;
    private javax.swing.JPanel jPanelGestionUsuario;
    private javax.swing.JPanel jPanelOtros;
    private javax.swing.JPanel jPanelVariosFiltros;
    private javax.swing.JRadioButton jRadioButtonEmpleado;
    private javax.swing.JRadioButton jRadioButtonEmpleadoInsert;
    private javax.swing.JRadioButton jRadioButtonEmpresa;
    private javax.swing.JRadioButton jRadioButtonEmpresaInsert;
    private javax.swing.JRadioButton jRadioButtonJornada;
    private javax.swing.JRadioButton jRadioButtonJornadaInsert;
    private javax.swing.JRadioButton jRadioButtonUsers;
    private javax.swing.JRadioButton jRadioButtonUsersInsert;
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
    private javax.swing.JTextField jTextFieldAddress1;
    private javax.swing.JTextField jTextFieldApellido;
    private javax.swing.JTextField jTextFieldApellido1;
    private javax.swing.JTextField jTextFieldApellido2;
    private javax.swing.JTextField jTextFieldCodicard;
    private javax.swing.JTextField jTextFieldCodicard1;
    private javax.swing.JTextField jTextFieldDepartament;
    private javax.swing.JTextField jTextFieldDepartament1;
    private javax.swing.JTextField jTextFieldDni;
    private javax.swing.JTextField jTextFieldDni1;
    private javax.swing.JTextField jTextFieldLogin;
    private javax.swing.JTextField jTextFieldLogin1;
    private javax.swing.JTextField jTextFieldMail;
    private javax.swing.JTextField jTextFieldMail1;
    private javax.swing.JTextField jTextFieldNom;
    private javax.swing.JTextField jTextFieldNom1;
    private javax.swing.JTextField jTextFieldNom2;
    private javax.swing.JTextField jTextFieldNomEmpresa;
    private javax.swing.JTextField jTextFieldNomEmpresa1;
    private javax.swing.JTextField jTextFieldNumtipe;
    private javax.swing.JTextField jTextFieldNumtipe1;
    private javax.swing.JTextField jTextFieldPalabra;
    private javax.swing.JTextField jTextFieldPass;
    private javax.swing.JTextField jTextFieldPass1;
    private javax.swing.JTextField jTextFieldReferencia1Delete;
    private javax.swing.JTextField jTextFieldReferenciaDelete2;
    private javax.swing.JTextField jTextFieldReferenciaInsert;
    private javax.swing.JTextField jTextFieldTelephon;
    private javax.swing.JTextField jTextFieldTelephon1;
    // End of variables declaration//GEN-END:variables
}
