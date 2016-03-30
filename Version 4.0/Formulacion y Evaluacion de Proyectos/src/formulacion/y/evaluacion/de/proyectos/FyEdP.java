/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulacion.y.evaluacion.de.proyectos;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author demg Formulacion y Evaluacion de Proyectos
 */
public class FyEdP extends javax.swing.JFrame {

    private int añosProyecto;
    private double tasaInteres;
    private int añosPrestamo;
    private double tasaInteresPrestamo;
    private boolean tablaPersonal;
    private double pTU;
    operaciones opera = new operaciones();
    Object fne[];
    Object flne[] = new Object[añosProyecto];
    private double sumVP;
    private double pIB;
    private double vPN1;
    private double vPN2;
    private double tIR;
    private double VS;
    private double sumVP2;

    //Modelos de las tablas
    private DefaultTableModel it;   // Ingresos
    private DefaultTableModel itt;   // Ingresos
    private DefaultTableModel p;    // Personal
    private DefaultTableModel co;   // Costo Operacion
    private DefaultTableModel ga;   // Gastos administrativos
    private DefaultTableModel af;   // Activo Fijo
    private DefaultTableModel ad;   // Activo Diferido
    private DefaultTableModel cs;   // Capital Social
    private DefaultTableModel pb;   // Prestamo Bancario
    private DefaultTableModel cp;   // Costo Ponderado
    private DefaultTableModel dya;  // Depreciacion y Amortizacion
    private DefaultTableModel dyat;  // Depreciacion y Amortizacion totales
    private DefaultTableModel dlp;  // Deuda Largo Plazo
    private DefaultTableModel dcp;  // Deuda Corto Plazo
    private DefaultTableModel anf;  // Analisis Financiero

    /* Metodos que almacenan temporalmente los datos de las tablas */
    private ArrayList<ArrayList<Double>> iT = new ArrayList<ArrayList<Double>>();
    private ArrayList<ArrayList<Double>> iTA = new ArrayList<ArrayList<Double>>();
    private ArrayList<ArrayList<String>> P = new ArrayList<ArrayList<String>>();
    private ArrayList<Double> cO = new ArrayList<Double>();
    private ArrayList<Double> gA = new ArrayList<Double>();
    private ArrayList<Double> aF = new ArrayList<Double>();
    private ArrayList<Double> aD = new ArrayList<Double>();
    private ArrayList<Double> cS = new ArrayList<Double>();
    private ArrayList<Double> pB = new ArrayList<Double>();
    private ArrayList<ArrayList<Double>> cP = new ArrayList<ArrayList<Double>>();
    private ArrayList<ArrayList<Double>> dyA = new ArrayList<ArrayList<Double>>();
    private ArrayList<ArrayList<Double>> dyAT = new ArrayList<ArrayList<Double>>();
    private ArrayList<Double> sumDyAT = new ArrayList<Double>();
    private ArrayList<ArrayList<Double>> dLP = new ArrayList<ArrayList<Double>>();
    private ArrayList<ArrayList<Double>> dCP = new ArrayList<ArrayList<Double>>();
    private ArrayList<ArrayList<Double>> anF = new ArrayList<ArrayList<Double>>();

    /**
     * Creates new form FyEdP
     */
    public FyEdP() {
        initComponents();
    }

    public FyEdP(int años, double tI, int aP, double tIP, boolean tP, double ptu, double pib) {
        initComponents();

        // Carga Datos de ventana Main.        
        tablaPersonal = tP;
        añosProyecto = años;
        tasaInteres = tI;
        añosPrestamo = aP;
        tasaInteresPrestamo = tIP;
        pTU = ptu;
        pIB = pib;
        if (tablaPersonal != true) {
            jTabbedPane1.setEnabledAt(1, tablaPersonal);
        } else {
            agregarBoxTablaPersonal();
        }

        // Carga de Tablas y Carga de Modelos para Manipulacion de Tablas.
        jTextField11.setText(String.valueOf(añosPrestamo));
        jTextField12.setText(String.valueOf(tasaInteresPrestamo));
        iniciarTablasProyecto();
        iniciarTablasPrestamo();
        cargarModelosTablas();
    }

    public void agregarBoxTablaPersonal() {
        TableColumn modelo = (TableColumn) jTable7.getColumnModel().getColumn(1);
        modelo.setCellEditor(new DefaultCellEditor(jComboBox1));//AGREGO EL COMBO AL CELLEDITOR
    }

    /* <---PRIMERA PARTE DEL CODIGO--->*/

    /* Carga de los Modelos manipuladores de las tablas */
    public void cargarModelosTablas() {
        it = (DefaultTableModel) jTable2.getModel();
        itt = (DefaultTableModel) jTable31.getModel();
        p = (DefaultTableModel) jTable7.getModel();
        co = (DefaultTableModel) jTable4.getModel();
        ga = (DefaultTableModel) jTable6.getModel();
        af = (DefaultTableModel) jTable8.getModel();
        ad = (DefaultTableModel) jTable11.getModel();
        cs = (DefaultTableModel) jTable14.getModel();
        pb = (DefaultTableModel) jTable15.getModel();
        cp = (DefaultTableModel) jTable18.getModel();
        dya = (DefaultTableModel) jTable26.getModel();
        dyat = (DefaultTableModel) jTable12.getModel();
        dlp = (DefaultTableModel) jTable20.getModel();
        dcp = (DefaultTableModel) jTable22.getModel();
        anf = (DefaultTableModel) jTable29.getModel();
    }

    /* Metodo encargado de dar formato a las tablas con los datos obtenidos en la ventana
     "main".*/
    public void iniciarTablasProyecto() {
        // Tabla Ingresos
        String cn[] = new String[añosProyecto];
        for (int i = 0; i < añosProyecto; i++) {
            cn[i] = "" + ((i + 1) + " Año");
        }
        Class[] types = new Class[añosProyecto];
        for (int i = 0; i < añosProyecto; i++) {
            types[i] = java.lang.Double.class;
        }
        Object filas[][] = new Object[3][añosProyecto];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < añosProyecto; j++) {
                filas[i][j] = null;
            }
        }
        jTable31.setModel(new javax.swing.table.DefaultTableModel(
                filas,
                cn
        ) {
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        String cnI[] = new String[añosProyecto];
        for (int i = 0; i < añosProyecto; i++) {
            cnI[i] = "" + ((i + 1) + " Año");
        }
        Class[] typesI = new Class[añosProyecto];
        for (int i = 0; i < añosProyecto; i++) {
            typesI[i] = java.lang.Double.class;
        }
        Object filasI[][] = new Object[2][añosProyecto];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < añosProyecto; j++) {
                filasI[i][j] = null;
            }
        }
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                filasI,
                cnI
        ) {
            public Class getColumnClass(int columnIndex) {
                return typesI[columnIndex];
            }
        });

        //tabla Depreciacion
        String cnD[] = new String[añosProyecto + 1];
        for (int i = 0; i < cnD.length; i++) {
            if (i < añosProyecto) {
                cnD[i] = "" + ((i + 1) + " Año");
            } else {
                cnD[i] = "Valor de Salvamento";
            }
        }
        Class[] typesD = new Class[añosProyecto + 1];
        for (int i = 0; i < typesD.length; i++) {
            typesD[i] = java.lang.Double.class;
        }
        jTable26.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                cnD
        ) {
            public Class getColumnClass(int columnIndex) {
                return typesD[columnIndex];
            }
        });
        Object filasDDD[][] = new Object[1][añosProyecto + 1];
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < typesD.length; j++) {
                filasDDD[i][j] = null;
            }
        }
        jTable25.setModel(new javax.swing.table.DefaultTableModel(
                filasDDD,
                cnD
        ) {
            public Class getColumnClass(int columnIndex) {
                return typesD[columnIndex];
            }
        });

        // Tabla Estado de Resultados Proforma
        String cnE[] = new String[añosProyecto];
        for (int i = 0; i < añosProyecto; i++) {
            cnE[i] = "" + (i + 1 + " Año");
        }
        Class[] typesE = new Class[añosProyecto];
        for (int i = 0; i < añosProyecto; i++) {
            typesE[i] = java.lang.Double.class;
        }
        jTable28.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                cnE
        ) {
            public Class getColumnClass(int columnIndex) {
                return typesE[columnIndex];
            }
        });

        // tabla Analisis Financiero
        String cnA[] = new String[añosProyecto + 2];
        for (int i = 0; i < cnA.length; i++) {
            if (i < añosProyecto + 1) {
                cnA[i] = "" + (i + " Año");
            } else {
                cnA[i] = "Valor de Salvamento";
            }
        }
        Class[] typesA = new Class[añosProyecto + 2];
        for (int i = 0; i < typesA.length; i++) {
            typesA[i] = java.lang.Double.class;
        }
        Object filasA[][] = new Object[3][añosProyecto + 2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < typesA.length; j++) {
                filasA[i][j] = null;
            }
        }
        jTable29.setModel(new javax.swing.table.DefaultTableModel(
                filasA,
                cnA
        ) {
            public Class getColumnClass(int columnIndex) {
                return typesA[columnIndex];
            }
        });
    }

    public void iniciarTablasPrestamo() {
        String cn[] = new String[añosPrestamo + 1];
        for (int i = 0; i <= añosPrestamo; i++) {
            cn[i] = "" + (i + " Año");
        }
        Class[] types = new Class[añosPrestamo + 1];
        for (int i = 0; i <= añosPrestamo; i++) {
            types[i] = java.lang.Double.class;
        }
        jTable20.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                cn
        ) {
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton13 = new javax.swing.JButton();
        jScrollPane30 = new javax.swing.JScrollPane();
        jTable30 = new javax.swing.JTable();
        jScrollPane31 = new javax.swing.JScrollPane();
        jTable31 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jTextField13 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable13 = new javax.swing.JTable();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable14 = new javax.swing.JTable();
        jTextField5 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTable15 = new javax.swing.JTable();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTable16 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jButton20 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTable17 = new javax.swing.JTable();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTable18 = new javax.swing.JTable();
        jTextField8 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable12 = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jScrollPane24 = new javax.swing.JScrollPane();
        jTable24 = new javax.swing.JTable();
        jScrollPane25 = new javax.swing.JScrollPane();
        jTable25 = new javax.swing.JTable();
        jScrollPane26 = new javax.swing.JScrollPane();
        jTable26 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTable19 = new javax.swing.JTable();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTable20 = new javax.swing.JTable();
        jButton14 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel27 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jButton21 = new javax.swing.JButton();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTable21 = new javax.swing.JTable();
        jScrollPane22 = new javax.swing.JScrollPane();
        jTable22 = new javax.swing.JTable();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel26 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane27 = new javax.swing.JScrollPane();
        jTable27 = new javax.swing.JTable();
        jScrollPane28 = new javax.swing.JScrollPane();
        jTable28 = new javax.swing.JTable();
        jButton22 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane23 = new javax.swing.JScrollPane();
        jTable23 = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jScrollPane29 = new javax.swing.JScrollPane();
        jTable29 = new javax.swing.JTable();
        jButton23 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrativo", "Operativo" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulacion y Evaluacion de Proyectos");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Cantidad"},
                {"Precio"}
            },
            new String [] {
                "Concepto"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jButton13.setText("Calcular Totales");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jTable30.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Cantidad Anual"},
                {"Precio"},
                {"Ingresos Totales"}
            },
            new String [] {
                "Concepto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane30.setViewportView(jTable30);

        jTable31.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane31.setViewportView(jTable31);

        jLabel6.setText("Anualizados");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                            .addComponent(jScrollPane31)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton13)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane31, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(350, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("1. Tabla de Ingresos", jPanel1);

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Puesto", "Tipo de Puesto", "Cantidad", "Salario", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTable7);

        jButton5.setText("Añadir Fila");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Calcular Totales");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton15.setText("Borar Fila");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15)
                        .addGap(51, 51, 51)
                        .addComponent(jButton6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tabla de Personal", jPanel3);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Sueldo"},
                {"ISR, IMSS, AFORE"},
                {"Gasolina"},
                {"Agua"},
                {null}
            },
            new String [] {
                "Costo de Operacion"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jLabel1.setText("Total:");

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jButton1.setText("Añadir Fila");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Calcular Costo de Operacion");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Sueldo"},
                {"ISR, IMSS, AFORE"},
                {"Gasolina"},
                {"Agua"},
                {null}
            },
            new String [] {
                "Gastos Admistrativos"
            }
        ));
        jScrollPane5.setViewportView(jTable5);

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTable6);

        jLabel2.setText("Total:");

        jButton3.setText("Calcular Gastos Administrativos");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Añadir Fila");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);

        jTextField2.setEditable(false);

        jButton16.setText("Borrar Fila");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText("Borrar Fila");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(42, 42, 42)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton16)))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(91, 91, 91))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton17)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(92, 92, 92))))
        );

        jTabbedPane1.addTab("2. Capital de Trabajo", jPanel2);

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Terreno"},
                {"Transporte"},
                {null},
                {null}
            },
            new String [] {
                "Activo Fijo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane9.setViewportView(jTable9);

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane8.setViewportView(jTable8);

        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Seguro"},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Activo Diferido"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane10.setViewportView(jTable10);

        jTable11.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane11.setViewportView(jTable11);

        jButton7.setText("Añadir Fila");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Añadir Fila");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel3.setText("Total:");

        jButton9.setText("Calcular Activo Fijo");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel4.setText("Total:");

        jButton10.setText("Calcular Activo Diferido");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton18.setText("Borrar Fila");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setText("Borrar Fila");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jLabel8.setText("Prestamo Corto Plazo");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                            .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField13)
                            .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addGap(20, 20, 20))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                            .addComponent(jScrollPane9))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton8)
                                    .addComponent(jButton7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton18)
                                    .addComponent(jButton19))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton10))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton9)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("3. Destino de los Recursos", jPanel4);

        jTable13.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Acciones Comunes"},
                {"Acciones Presentes"},
                {"Obligaciones"}
            },
            new String [] {
                "Capital Social"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane13.setViewportView(jTable13);

        jTable14.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null}
            },
            new String [] {
                "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane14.setViewportView(jTable14);

        jLabel5.setText("Total Capital Social:");

        jTable15.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null}
            },
            new String [] {
                "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane15.setViewportView(jTable15);

        jTable16.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"de Largo Plazo"},
                {"de Corto Plazo"}
            },
            new String [] {
                "Prestamo Bancario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane16.setViewportView(jTable16);

        jLabel7.setText("Total Prestamo Bancario:");

        jLabel9.setText("Total de Origen de los Recursos");

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(70, 1, 100, 1));
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(30, 1, 100, 1));
        jSpinner2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner2StateChanged(evt);
            }
        });

        jButton20.setText("Calcular Capital Social y Prestamo Bancario");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                .addComponent(jLabel5))
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5)
                            .addComponent(jTextField6)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton20)
                        .addGap(57, 57, 57)))
                .addContainerGap(509, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton20)
                .addContainerGap(265, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("4. Origen de los Recursos", jPanel5);

        jTable17.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Acciones Comunes"},
                {"Acciones Presentes"},
                {"Obligaciones"}
            },
            new String [] {
                "Capital Social"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane17.setViewportView(jTable17);

        jTable18.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Monto", "Interes", "Costo K %", "Costo Real %"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane18.setViewportView(jTable18);

        jLabel10.setText("Total:");

        jLabel11.setText("Trema:");

        jButton12.setText("Calcular");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(185, 185, 185)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton12)
                .addContainerGap(184, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jButton12))
                .addContainerGap(436, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("5. Costo ponderado de Capital", jPanel6);

        jTable12.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Activos", "Montos", "Tiempo años"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane12.setViewportView(jTable12);

        jButton11.setText("Calcular Totales");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jTable24.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Totales * Año"}
            },
            new String [] {
                "Concepto"
            }
        ));
        jScrollPane24.setViewportView(jTable24);

        jTable25.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "1", "2", "3", "4", "5", "VS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane25.setViewportView(jTable25);

        jTable26.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "1", "2", "3", "4", "5", "VS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane26.setViewportView(jTable26);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton11))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane25, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                            .addComponent(jScrollPane26))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                    .addComponent(jScrollPane26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane25, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11)
                .addContainerGap())
        );

        jTabbedPane1.addTab("6. Depreciacion y Amortizacion", jPanel7);

        jLabel12.setText("Monto:");

        jTextField10.setEditable(false);

        jLabel13.setText("Tasa de Interes:");

        jLabel14.setText("Periodo:");

        jLabel15.setText("Pagos:");

        jTextField11.setEditable(false);

        jTextField12.setEditable(false);

        jTable19.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Costo Financiero"},
                {"Pagos"},
                {"Abonos al Capital"},
                {"Saldo"}
            },
            new String [] {
                "n"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane19.setViewportView(jTable19);

        jTable20.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "1", "2", "3"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane20.setViewportView(jTable20);

        jButton14.setText("Calcular");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Iguales" }));

        jLabel27.setText("%");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField11)
                                    .addComponent(jTextField10)
                                    .addComponent(jTextField12, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton14)
                                    .addComponent(jLabel27))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(331, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("7. Deuda Largo Plazo", jPanel8);

        jLabel16.setText("Monto:");

        jLabel17.setText("Periodo:");

        jLabel18.setText("Tasa de Interes:");

        jLabel19.setText("Pagos:");

        jButton21.setText("Calcular");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jTextField15.setEditable(false);
        jTextField15.setText("1");

        jTable21.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Costo Financiero"},
                {"Pagos"},
                {"Abonos al Capital"},
                {"Saldo"}
            },
            new String [] {
                "n"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane21.setViewportView(jTable21);

        jTable22.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "0", "1"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane22.setViewportView(jTable22);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Iguales" }));

        jLabel26.setText("%");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField16, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jTextField14)
                            .addComponent(jTextField15)
                            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton21)
                            .addComponent(jLabel26))))
                .addContainerGap(428, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(331, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("8. Deuda Corto Plazo", jPanel9);

        jTable27.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"+ Ingresos Totales"},
                {"- Costo de Operacion"},
                {"- Costo Financiero"},
                {"- Depreciacion y Amortizacion"},
                {"- Gastos Administrativos"},
                {"= Bienes antes de Impuestos"},
                {"- ISR"},
                {"- PTU"},
                {"= Bienes despues de Impuestos"},
                {"+ Depreciacion y Amortizacion"},
                {"- Aumento de Capital"},
                {"= Flujo Neto de Efectivo"},
                {"= Valor Presente"}
            },
            new String [] {
                "Conceptos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane27.setViewportView(jTable27);

        jTable28.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane28.setViewportView(jTable28);

        jButton22.setText("Calcular");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane28, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton22)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane27, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton22)
                .addContainerGap(281, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("9. Estado de Resultados Proforma", jPanel10);

        jTable23.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"FNE"},
                {"VP FNE"},
                {"VP FNE 2"}
            },
            new String [] {
                "n"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane23.setViewportView(jTable23);

        jLabel20.setText("TREMA:");

        jLabel21.setText("n:");

        jLabel22.setText("VPN FNE 1:");

        jLabel23.setText("VPN FNE 2:");

        jLabel24.setText("TIR:");

        jTextField18.setEditable(false);

        jTextField19.setEditable(false);

        jTextField20.setEditable(false);

        jTextField21.setEditable(false);

        jTextField22.setEditable(false);

        jLabel25.setText("Periodo de Recuperacion:");

        jTextField23.setEditable(false);

        jTable29.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "1", "2", "3", "4", "5", "VS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane29.setViewportView(jTable29);

        jButton23.setText("Calcular Analisis Financiero");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane29, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField23)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField19)
                                    .addComponent(jTextField18)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel24))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField21)
                                    .addComponent(jTextField22)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField20)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane29, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane23, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton23)
                .addContainerGap(244, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("10. Analisis Financiero", jPanel11);

        jMenu1.setText("Archivo");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ayuda");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Botones que Agregan Filas
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tabla5 = (DefaultTableModel) jTable5.getModel();
        DefaultTableModel tabla6 = (DefaultTableModel) jTable6.getModel();
        tabla5.addRow(new Object[1]);
        tabla6.addRow(new Object[1]);
        jTable5.setModel(tabla5);
        jTable6.setModel(tabla6);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tabla3 = (DefaultTableModel) jTable3.getModel();
        DefaultTableModel tabla4 = (DefaultTableModel) jTable4.getModel();
        tabla3.addRow(new Object[1]);
        tabla4.addRow(new Object[1]);
        jTable3.setModel(tabla3);
        jTable4.setModel(tabla4);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tabla7 = (DefaultTableModel) jTable7.getModel();
        tabla7.addRow(new Object[1]);
        jTable7.setModel(tabla7);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tabla8 = (DefaultTableModel) jTable8.getModel();
        tabla8.addRow(new Object[1]);
        jTable8.setModel(tabla8);
        DefaultTableModel tabla9 = (DefaultTableModel) jTable9.getModel();
        tabla9.addRow(new Object[1]);
        jTable9.setModel(tabla9);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tabla10 = (DefaultTableModel) jTable10.getModel();
        tabla10.addRow(new Object[1]);
        jTable10.setModel(tabla10);
        DefaultTableModel tabla11 = (DefaultTableModel) jTable11.getModel();
        tabla11.addRow(new Object[1]);
        jTable11.setModel(tabla11);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        // TODO add your handling code here:
        int value1 = Integer.valueOf(String.valueOf(jSpinner1.getValue()));
        int value2 = Integer.valueOf(String.valueOf(jSpinner2.getValue()));
        int sum = value1 + value2;
        if (sum < 100) {
            value2 = value2 + (100 - sum);
            jSpinner2.setValue(value2);
        } else if (sum > 100) {
            value2 = value2 - (sum - 100);
            jSpinner2.setValue(value2);
        }
        operacionCapitalSocial(Double.valueOf(String.valueOf(jSpinner1.getValue())), Double.valueOf(String.valueOf(jSpinner2.getValue())));
    }//GEN-LAST:event_jSpinner1StateChanged

    // Botones que Eliminan Filas
    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tabla7 = (DefaultTableModel) jTable7.getModel();
        tabla7.removeRow(jTable7.getRowCount() - 1);
        jTable7.setModel(tabla7);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tabla4 = (DefaultTableModel) jTable4.getModel();
        tabla4.removeRow(jTable4.getRowCount() - 1);
        jTable4.setModel(tabla4);
        DefaultTableModel tabla3 = (DefaultTableModel) jTable3.getModel();
        tabla3.removeRow(jTable3.getRowCount() - 1);
        jTable3.setModel(tabla3);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tabla6 = (DefaultTableModel) jTable6.getModel();
        tabla6.removeRow(jTable6.getRowCount() - 1);
        jTable6.setModel(tabla6);
        DefaultTableModel tabla5 = (DefaultTableModel) jTable5.getModel();
        tabla5.removeRow(jTable5.getRowCount() - 1);
        jTable5.setModel(tabla5);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tabla8 = (DefaultTableModel) jTable8.getModel();
        tabla8.removeRow(jTable8.getRowCount() - 1);
        jTable8.setModel(tabla8);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tabla11 = (DefaultTableModel) jTable11.getModel();
        tabla11.removeRow(jTable11.getRowCount() - 1);
        jTable11.setModel(tabla11);
    }//GEN-LAST:event_jButton19ActionPerformed

    /* <---SEGUNDA PARTE DEL CODIGO--->*/
    /* desde este punto se empiezan a realizar las operaciones de todo el programa, para
     futuras versiones tratar de eliminar todo el codigo de este lado del
     archivo de java, ya que se utilizaron mas de 2000 lineas de codigo y se ve poco
     limpio el codigo.
     Tambien tratar de eliminar todas las redundancias de codigo en la parte anterior.
     */

    /* Tabla de ingresos */
    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:        
        for (int i = 0; i < 2; i++) {
            iT.add(new ArrayList<Double>());
            for (int j = 0; j < añosProyecto; j++) {
                if (i == 0) {
                    iT.get(i).add(Double.valueOf(String.valueOf(jTable2.getValueAt(i, j))) * 365);
                } else {
                    iT.get(i).add(Double.valueOf(String.valueOf(jTable2.getValueAt(i, j))));
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            iTA.add(new ArrayList<Double>());
            for (int j = 0; j < añosProyecto; j++) {
                if (i == 2) {
                    iTA.get(i).add(iT.get(0).get(j) * iT.get(1).get(j));
                } else {
                    iTA.get(i).add(iT.get(i).get(j));
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < añosProyecto; j++) {
                itt.setValueAt(iTA.get(i).get(j), i, j);
            }
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    /* Tabla de Personal */
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:        
        double sumO = 0, sumA = 0;
        for (int i = 0; i < p.getRowCount(); i++) {
            P.add(new ArrayList<>());
            for (int j = 0; j < p.getColumnCount() - 1; j++) {
                P.get(i).add(String.valueOf(p.getValueAt(i, j)));
                System.out.println(P.get(i).get(j));
            }
        }
        for (int i = 0; i < P.size(); i++) {
            double t = 0.0;
            for (int j = 2; j < 4; j++) {
                t = Double.valueOf(P.get(i).get(2)) * Double.valueOf(P.get(i).get(3));
            }
            P.get(i).add(String.valueOf(t));
        }
        for (int i = 0; i < P.size(); i++) {
            for (int j = 4; j < P.get(i).size(); j++) {
                p.setValueAt(Double.valueOf(String.valueOf(P.get(i).get(j))), i, j);
            }
        }
        for (int i = 0; i < P.size(); i++) {
            for (int j = 1; j < 2; j++) {
                if (String.valueOf(p.getValueAt(i, 1)) == "Administrativo") {
                    sumA = sumA + Double.parseDouble(String.valueOf(P.get(i).get(4)));
                } else if (String.valueOf(p.getValueAt(i, 1)) == "Operativo") {
                    sumO = sumO + Double.parseDouble(String.valueOf(P.get(i).get(4)));
                }
            }
        }
        co.setValueAt(sumO, 0, 0);
        ga.setValueAt(sumA, 0, 0);
    }//GEN-LAST:event_jButton6ActionPerformed

    /* Tablas Capital de Trabajo 
     Costo de Operacion
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        double tot = 0;
        if (jTextField1.getText() != null) {
            cO.removeAll(cO);
            for (int i = 0; i < co.getRowCount(); i++) {
                if (co.getValueAt(i, 0) != null) {
                    cO.add(Double.valueOf(String.valueOf(co.getValueAt(i, 0))));
                }
            }
            for (int i = 0; i < cO.size(); i++) {
                tot = tot + cO.get(i);
            }
            jTextField1.setText(String.valueOf(tot));
        } else {
            for (int i = 0; i < co.getRowCount(); i++) {
                if (co.getValueAt(i, 0) != null) {
                    cO.add(Double.valueOf(String.valueOf(co.getValueAt(i, 0))));
                }
            }
            for (int i = 0; i < cO.size(); i++) {
                tot = tot + cO.get(i);
            }
            jTextField1.setText(String.valueOf(tot));
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /* Gastos Administrativos */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        double tot = 0;
        if (jTextField2.getText() != null) {
            gA.removeAll(gA);
            for (int i = 0; i < ga.getRowCount(); i++) {
                if (ga.getValueAt(i, 0) != null) {
                    gA.add(Double.valueOf(String.valueOf(ga.getValueAt(i, 0))));
                }
            }
            for (int i = 0; i < gA.size(); i++) {
                tot = tot + gA.get(i);
            }
            jTextField2.setText(String.valueOf(tot));
        } else {
            for (int i = 0; i < ga.getRowCount(); i++) {
                if (ga.getValueAt(i, 0) != null) {
                    gA.add(Double.valueOf(String.valueOf(ga.getValueAt(i, 0))));
                }
            }
            for (int i = 0; i < gA.size(); i++) {
                tot = tot + gA.get(i);
            }
            jTextField2.setText(String.valueOf(tot));
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    /* Tabla Destino de los Recursos 
     Activo Fijo
     */
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        double tot = 0;
        if (jTextField3.getText() != null) {
            aF.removeAll(aF);
            for (int i = 0; i < af.getRowCount(); i++) {
                if (af.getValueAt(i, 0) != null) {
                    aF.add(Double.valueOf(String.valueOf(af.getValueAt(i, 0))));
                }
            }
            for (int i = 0; i < aF.size(); i++) {
                tot = tot + Double.valueOf(String.valueOf(aF.get(i)));
            }
            jTextField3.setText(opera.conversion(tot));
        } else {
            for (int i = 0; i < af.getRowCount(); i++) {
                if (af.getValueAt(i, 0) != null) {
                    aF.add(Double.valueOf(String.valueOf(af.getValueAt(i, 0))));
                }
            }
            for (int i = 0; i < aF.size(); i++) {
                tot = tot + Double.valueOf(String.valueOf(aF.get(i)));
            }
            jTextField3.setText(opera.conversion(tot));
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        double val = Double.valueOf(jTextField13.getText());
        double tot = val;
        if (jTextField4.getText() != null) {
            aD.removeAll(aD);
            for (int i = 0; i < ad.getRowCount(); i++) {
                if (ad.getValueAt(i, 0) != null) {
                    aD.add(Double.valueOf(String.valueOf(ad.getValueAt(i, 0))));
                }
            }
            for (int i = 0; i < aD.size(); i++) {
                tot = tot + Double.valueOf(String.valueOf(aD.get(i)));
            }
            jTextField4.setText(opera.conversion(tot));
        } else {
            for (int i = 0; i < ad.getRowCount(); i++) {
                if (ad.getValueAt(i, 0) != null) {
                    aD.add(Double.valueOf(String.valueOf(ad.getValueAt(i, 0))));
                }
            }
            for (int i = 0; i < aD.size(); i++) {
                tot = tot + Double.valueOf(String.valueOf(aD.get(i)));
            }
            jTextField4.setText(opera.conversion(tot));
        }
        if (jTextField3.getText() != null) {
            double t = Double.valueOf(jTextField3.getText()) + Double.valueOf(jTextField4.getText());
            jTextField7.setText(opera.conversion(t));
        }
        llenarTablaDepreciacion();
    }//GEN-LAST:event_jButton10ActionPerformed

    /* Tabla Origen de los Recursos */
    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        operacionCapitalSocial(Double.valueOf(String.valueOf(jSpinner1.getValue())), Double.valueOf(String.valueOf(jSpinner2.getValue())));
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jSpinner2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner2StateChanged
        // TODO add your handling code here:
        int value1 = Integer.valueOf(String.valueOf(jSpinner1.getValue()));
        int value2 = Integer.valueOf(String.valueOf(jSpinner2.getValue()));
        int sum = value1 + value2;
        if (sum < 100) {
            value2 = value2 + (100 - sum);
            jSpinner2.setValue(value2);
        } else if (sum > 100) {
            value2 = value2 - (sum - 100);
            jSpinner2.setValue(value2);
        }
        operacionCapitalSocial(Double.valueOf(String.valueOf(jSpinner1.getValue())) / 100, Double.valueOf(String.valueOf(jSpinner2.getValue())) / 100);

    }//GEN-LAST:event_jSpinner2StateChanged

    /* Tabla Costo Ponderado de Capital */
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        double tot = 0, trema = 0;
        double c[] = new double[3];
        if (cp.getValueAt(0, 2) != null) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    if (cp.getValueAt(i, j) != null) {
                        cP.get(i).add(Double.valueOf(String.valueOf(cp.getValueAt(i, j))));
                    }
                }
            }
            for (int i = 0; i < cP.size(); i++) {
                c[i] = Double.valueOf(String.valueOf(cp.getValueAt(i, 1))) * Double.valueOf(String.valueOf(cp.getValueAt(i, 2)));
                cp.setValueAt(c[i], i, 3);
                cP.get(i).add(c[i]);
                trema = trema + Double.valueOf(String.valueOf(cp.getValueAt(i, 3)));
                tot = tot + Double.valueOf(String.valueOf(cp.getValueAt(i, 0)));
            }
            jTextField8.setText(opera.conversion(tot));
            jTextField9.setText(opera.conversion(trema));
        } else {
            JOptionPane.showMessageDialog(null, "Por favor inserta Costo K%", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        ArrayList<Double> totales = new ArrayList<Double>();
        double valor = 0;
        double val = 0;
        if (jTable12.getValueAt(0, 2) != null) {
            for (int i = 0; i < jTable12.getRowCount(); i++) {
                if (Double.valueOf(String.valueOf(jTable12.getValueAt(i, 2))) != 0) {
                    valor = Double.valueOf(String.valueOf(jTable12.getValueAt(i, 1))) / Double.valueOf(String.valueOf(jTable12.getValueAt(i, 2)));
                    totales.add(valor);
                } else {
                    totales.add(0.0);
                }
            }
            for (int i = 0; i < totales.size(); i++) {
                System.out.println(totales.get(i));
            }
            for (int i = 0; i < totales.size(); i++) {
                dyAT.add(new ArrayList<Double>());
                for (int j = 0; j < añosProyecto; j++) {
                    dyAT.get(i).add(totales.get(i));
                }
            }
            for (int i = 0; i < totales.size(); i++) {
                dyAT.add(new ArrayList<Double>());
                for (int j = 0; j < añosProyecto; j++) {
                    dyat.setValueAt(dyAT.get(i).get(j), i, j);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Escribe los años de duracion de\nlos activos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modeloTP = (DefaultTableModel) jTable20.getModel();
        if (modeloTP.getRowCount() == 0) {
            Object cf[] = new Object[añosPrestamo + 1];
            Object pagos[] = new Object[añosPrestamo + 1];
            Object ak[] = new Object[añosPrestamo + 1];
            Object saldo[] = new Object[añosPrestamo + 1];
            if (Double.valueOf(String.valueOf(pb.getValueAt(0, 0))) != 0) {
                for (int i = 0; i < añosPrestamo + 1; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (i == 0 && j == 3) {
                            saldo[i] = Double.valueOf(String.valueOf(pb.getValueAt(0, 0)));
                        } else if (i == 0) {
                            cf[i] = 0;
                            pagos[i] = 0;
                            ak[i] = 0;
                        }
                        if (i > 0 && j == 0) {
                            double x = (double) saldo[i - 1];
                            double valor = x * tasaInteresPrestamo;
                            cf[i] = valor;
                        }
                        if (i > 0 && j == 1) {
                            pagos[i] = opera.anualidades(Double.valueOf(String.valueOf(pb.getValueAt(0, 0))), tasaInteresPrestamo, añosPrestamo);
                        }
                        if (i > 0 && j == 2) {
                            double x = (double) pagos[i];
                            double y = (double) cf[i];
                            ak[i] = x - y;
                        }
                        if (i > 0 && j == 3) {
                            double s = (double) saldo[i - 1];
                            double a = (double) ak[i];
                            double val = s - a;
                            saldo[i] = val;
                        }
                    }
                }
                modeloTP.addRow(cf);
                modeloTP.addRow(pagos);
                modeloTP.addRow(ak);
                modeloTP.addRow(saldo);
            } else {
                for (int i = 0; i < añosPrestamo; i++) {
                    cf[i] = 0;
                    pagos[i] = 0;
                    ak[i] = 0;
                    saldo[i] = 0;
                }
                modeloTP.addRow(cf);
                modeloTP.addRow(pagos);
                modeloTP.addRow(ak);
                modeloTP.addRow(saldo);
            }
            jTable20.setModel(modeloTP);
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modeloTP = (DefaultTableModel) jTable22.getModel();
        if (modeloTP.getRowCount() == 0) {
            Object cf[] = new Object[2];
            Object pagos[] = new Object[2];
            Object ak[] = new Object[2];
            Object saldo[] = new Object[2];
            double tain = Double.valueOf(jTextField16.getText()) / 100;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    if (i == 0 && j == 3) {
                        saldo[i] = Double.valueOf(jTextField14.getText());
                    } else if (i == 0) {
                        cf[i] = 0;
                        pagos[i] = 0;
                        ak[i] = 0;
                    }
                    if (i > 0 && j == 0) {
                        double x = (double) saldo[i - 1];
                        double valor = x * tain;
                        cf[i] = valor;
                    }
                    if (i > 0 && j == 1) {
                        pagos[i] = opera.anualidades(Double.valueOf(jTextField14.getText()), Double.valueOf(jTextField16.getText()), 1);
                    }
                    if (i > 0 && j == 2) {
                        double x = (double) pagos[i];
                        double y = (double) cf[i];
                        ak[i] = x - y;
                    }
                    if (i > 0 && j == 3) {
                        double s = (double) saldo[i - 1];
                        double a = (double) ak[i];
                        double val = s - a;
                        saldo[i] = val;
                    }
                }
            }
            modeloTP.addRow(cf);
            modeloTP.addRow(pagos);
            modeloTP.addRow(ak);
            modeloTP.addRow(saldo);
            jTable22.setModel(modeloTP);
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    /* Estado de Resultados Proforma */
    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel esR = (DefaultTableModel) jTable28.getModel();
        Object inT[] = new Object[añosProyecto];
        Object coo[] = new Object[añosProyecto];
        Object cf[] = new Object[añosProyecto];
        Object deya[] = new Object[añosProyecto];
        Object gaa[] = new Object[añosProyecto];
        Object bai[] = new Object[añosProyecto];

        Object isr[] = new Object[añosProyecto];
        Object ptu[] = new Object[añosProyecto];
        Object bdi[] = new Object[añosProyecto];

        Object ak[] = new Object[añosProyecto];

        Object vp[] = new Object[añosProyecto];

        // Llena Ingresos Totales
        for (int i = 0; i < añosProyecto; i++) {
            inT[i] = iTA.get(2).get(i);
        }
        esR.addRow(inT);

        // Llena Costo de Operacion
        for (int i = 0; i < añosProyecto; i++) {
            coo[i] = Double.valueOf(jTextField1.getText()) * 12;
        }
        esR.addRow(coo);

        // Llena Costo Financiero
        for (int i = 0; i < añosProyecto; i++) {
            if (i == 0) {
                cf[i] = Double.valueOf(String.valueOf(jTable20.getValueAt(0, i + 1))) + Double.valueOf(String.valueOf(jTable22.getValueAt(0, i + 1)));
            } else {
                if (i < añosPrestamo) {
                    cf[i] = Double.valueOf(String.valueOf(jTable20.getValueAt(0, i + 1)));
                } else {
                    cf[i] = 0;
                }
            }
        }
        esR.addRow(cf);

        // Llena Depreciacion y Amortizacion
        for (int i = 0; i < añosProyecto + 1; i++) {
            if (i < añosProyecto) {
                deya[i] = jTable25.getValueAt(0, i);
            } else {
                VS = Double.valueOf(String.valueOf(jTable25.getValueAt(0, i)));
            }
        }
        esR.addRow(deya);

        // Llena Gastos Administrativos
        for (int i = 0; i < añosProyecto; i++) {
            gaa[i] = Double.valueOf(jTextField2.getText()) * 12;
        }
        esR.addRow(gaa);

        // Calcula y Llena Bienes Antes de Impuestos
        for (int i = 0; i < añosProyecto; i++) {
            bai[i] = iTA.get(2).get(i) - Double.valueOf(String.valueOf(coo[i])) - Double.valueOf(String.valueOf(cf[i])) - Double.valueOf(String.valueOf(deya[i])) - Double.valueOf(String.valueOf(gaa[i]));
        }
        esR.addRow(bai);

        // Calcula y Llena ISR
        for (int i = 0; i < añosProyecto; i++) {
            isr[i] = opera.isr(Double.valueOf(String.valueOf(bai[i])), tasaInteres);
        }
        esR.addRow(isr);

        // Calcula y Llena PTU
        for (int i = 0; i < añosProyecto; i++) {
            ptu[i] = opera.ptu(Double.valueOf(String.valueOf(bai[i])), pTU);
        }
        esR.addRow(ptu);

        // Calcula y Llena Bienes Despues de Impuestos
        for (int i = 0; i < añosProyecto; i++) {
            bdi[i] = Double.valueOf(String.valueOf(bai[i])) - Double.valueOf(String.valueOf(isr[i])) - Double.valueOf(String.valueOf(ptu[i]));
        }
        esR.addRow(bdi);

        // Llena Depreciacion y Amortizacion
        esR.addRow(deya);

        // Llena Aumento de Capital
        for (int i = 0; i < añosProyecto; i++) {
            if (i == 0) {
                ak[i] = Double.valueOf(String.valueOf(jTable20.getValueAt(2, i + 1))) + Double.valueOf(String.valueOf(jTable22.getValueAt(2, i + 1)));
            } else {
                if (i < añosPrestamo) {
                    ak[i] = Double.valueOf(String.valueOf(jTable20.getValueAt(2, i + 1)));
                } else {
                    ak[i] = 0;
                }
            }
        }
        esR.addRow(ak);

        // Calcula y Llena FNE
        for (int i = 0; i < añosProyecto; i++) {
            flne[i] = Double.valueOf(String.valueOf(bdi[i])) + Double.valueOf(String.valueOf(deya[i])) - Double.valueOf(String.valueOf(ak[i]));
        }
        esR.addRow(flne);

        // Calcula y Llena VP
        for (int i = 0; i < añosProyecto; i++) {
            int val = i + 1;
            vp[i] = opera.vp(Double.valueOf(String.valueOf(flne[i])), Double.valueOf(jTextField9.getText()) / 100, val);
            sumVP = sumVP + Double.valueOf(String.valueOf(vp[i]));
        }
        esR.addRow(vp);
    }//GEN-LAST:event_jButton22ActionPerformed

    /* Analisis Financiero */
    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
        Object vp22[] = new Object[añosProyecto];
        vPN1 = sumVP + VS - (Double.valueOf(jTextField8.getText()) - Double.valueOf(jTextField6.getText()));
        jTextField20.setText(String.valueOf(vPN1));

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < añosProyecto + 1; j++) {
                if (i == 0) {
                    jTable12.setValueAt(jTable28.getValueAt(11, j), i, j);
                } else if (i == 1) {
                    jTable12.setValueAt(jTable28.getValueAt(12, j), i, j);
                }
            }
        }

        for (int i = 0; i < añosProyecto; i++) {
            int val = i + 1;
            vp22[i] = opera.vp(Double.valueOf(String.valueOf(flne[i])), Double.valueOf(jTextField9.getText()) / 100, val);
            sumVP2 = sumVP2 + Double.valueOf(String.valueOf(vp22[i]));
        }
        jTextField21.setText(String.valueOf(sumVP2));

        /* TIR */
        double i2 = Double.valueOf(jTextField22.getText()) / 100;
        double sumVP2 = 0;
        Object vp2[] = new Object[añosProyecto];
        for (int i = 0; i < añosProyecto; i++) {
            vp2[i] = opera.vp(Double.valueOf(String.valueOf(fne[i])), i2, i + 1);
            sumVP2 = sumVP2 + (double) vp2[i];
        }
        double tir = tasaInteres + (sumVP / (sumVP - sumVP2)) * (i2 - tasaInteres);
        jLabel22.setText(String.valueOf(tir));

        /* Periodo de Recuperacion */
        String periodoRecuperacion;
        double x = sumVP / añosProyecto;
        double años = Double.valueOf(jTextField9.getText()) / x;
        periodoRecuperacion = String.valueOf(años) + " Años ";
        double meses = (años - (int) años) * 12;
        periodoRecuperacion = periodoRecuperacion + String.valueOf(meses) + " Meses ";
        double dias = (meses - (int) meses) * 30;
        periodoRecuperacion = periodoRecuperacion + String.valueOf(dias) + " dias ";
        jTextField23.setText(periodoRecuperacion);
    }//GEN-LAST:event_jButton23ActionPerformed

    public void operacionCapitalSocial(double cas, double preb) {
        double t1 = 0, t2 = 0;
        double css[] = new double[3];
        double pb[] = null;
        double in[] = {.10, .75, .15};
        double plp = 0;
        if (jTextField7.getText() != null) {
            t1 = Double.valueOf(jTextField7.getText()) * (cas / 100);
            System.out.println(t1);
            t2 = Double.valueOf(jTextField7.getText()) * (preb / 100);
            System.out.println(t2);
            jTextField5.setText(opera.conversion(t1));
            jTextField6.setText(opera.conversion(t2));

            for (int i = 0; i < 2; i++) {
                if (i == 0) {
                    plp = t2 - Double.valueOf(jTextField13.getText());
                    jTable15.setValueAt(plp, i, 0);
                } else {
                    jTable15.setValueAt(jTextField13.getText(), i, 0);
                }
            }

            jTextField10.setText(String.valueOf(plp));
            jTextField4.setText(String.valueOf(Double.valueOf(jTextField13.getText())));

            // añaderse a la tabla y al arreglo
            // capital social
            css[0] = t1 * in[0];
            css[1] = t1 * in[1];
            css[2] = t1 * in[2];
            for (int i = 0; i < 3; i++) {
                cs.setValueAt(css[i], i, 0);
                cS.add(css[i]);
            }

            // Prestamo Bancario
            // Tabla Costo Ponderado de Capital
            for (int i = 0; i < 3; i++) {
                cP.add(new ArrayList<Double>());
                for (int j = 0; j < 4; j++) {
                    if (j == 0) {
                        cp.setValueAt(css[i], i, 0);
                        cP.get(i).add(css[i]);
                    }
                    if (j == 1) {
                        cp.setValueAt(in[i], i, 1);
                        cP.get(i).add(in[i]);
                    }
                }
            }
        }
    }

    public void llenarTablaDepreciacion() {
        for (int i = 0; i < af.getRowCount(); i++) {
            if (af.getValueAt(i, 0) != null) {
                Object rowData[] = {jTable9.getValueAt(i, 0), af.getValueAt(i, 0)};
                dyat.addRow(rowData);
                dya.addRow(new Object[]{});
            }
        }
        for (int i = 0; i < ad.getRowCount(); i++) {
            if (ad.getValueAt(i, 0) != null) {
                Object rowData[] = {jTable10.getValueAt(i, 0), ad.getValueAt(i, 0)};
                dyat.addRow(rowData);
                dya.addRow(new Object[]{});
            }
        }
    }

    /**
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
            java.util.logging.Logger.getLogger(FyEdP.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FyEdP.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FyEdP.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FyEdP.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FyEdP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTable12;
    private javax.swing.JTable jTable13;
    private javax.swing.JTable jTable14;
    private javax.swing.JTable jTable15;
    private javax.swing.JTable jTable16;
    private javax.swing.JTable jTable17;
    private javax.swing.JTable jTable18;
    private javax.swing.JTable jTable19;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable20;
    private javax.swing.JTable jTable21;
    private javax.swing.JTable jTable22;
    private javax.swing.JTable jTable23;
    private javax.swing.JTable jTable24;
    private javax.swing.JTable jTable25;
    private javax.swing.JTable jTable26;
    private javax.swing.JTable jTable27;
    private javax.swing.JTable jTable28;
    private javax.swing.JTable jTable29;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable30;
    private javax.swing.JTable jTable31;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
