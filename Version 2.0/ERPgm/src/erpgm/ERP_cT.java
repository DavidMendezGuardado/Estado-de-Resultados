/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpgm;

import java.awt.Color;
import java.util.Stack;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Oda Nobunaga
 */
public class ERP_cT extends javax.swing.JFrame {

    Calculos c = new Calculos();
    double iT[];
    double BAI[];
    double BDI[];
    double FNE[];
    double VP[];
    double CO;
    double II;
    double VS;
    double TI;
    double TREMA;
    int años;
    double GA;
    double PTU;
    double AK;
    int metodoDA;
    boolean EAK;
    double IP;
    double AP;

    /**
     * Creates new form NewJFrame
     */
    public ERP_cT(double it[], float co, float ii, float vs, float ti, float trema, int year, float ga, float ptu, float ak, int metodoDyA, boolean eAK, float ip, int ap) {
        metodoDA = metodoDyA;
        EAK = eAK;
        IP = ip;
        iT = it;
        CO = co;
        II = ii;
        VS = vs;
        TI = ti;
        TREMA = trema;
        años = year;
        GA = ga;
        PTU = ptu;
        AK = ak;
        AP = ap;
        BAI = new double[años];
        BDI = new double[años];
        FNE = new double[años];
        VP = new double[años];

        initComponents();
        jTable3.setVisible(false);
        jLabel3.setVisible(false);
        setTamañoTabla();
        llenarTabla();
    }

    public void setTamañoTabla() {
        String cn[] = new String[años];
        for (int i = 0; i < años; i++) {
            cn[i] = "" + (i + 1 + " Año");
        }
        Class[] types = new Class[años];
        for (int i = 0; i < años; i++) {
            types[i] = java.lang.Double.class;
        }
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                cn
        ) {
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                cn
        ) {
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
    }

    public void llenarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        Object inT[] = new Object[años];
        Object co[] = new Object[años];
        Object cf[] = new Object[años];
        Object dya[] = new Object[años];
        Object ga[] = new Object[años];
        Object bai[] = new Object[años];

        Object isr[] = new Object[años];
        Object bdi[] = new Object[años];

        Object ptu[] = new Object[años];
        Object ak[] = new Object[años];

        Object vp[] = new Object[años];

        // Llena Ingresos Totales
        for (int i = 0; i < años; i++) {
            inT[i] = iT[i];
        }
        modelo.addRow(inT);

        // Llena Costo de Operacion
        for (int i = 0; i < años; i++) {
            co[i] = c.costoOperacion(CO);
        }
        modelo.addRow(co);

        // Llena Costo Financiero
        if (EAK == true) {

        } else {
            for (int i = 0; i < años; i++) {
                cf[i] = c.cF(EAK);
            }
        }
        modelo.addRow(cf);

        // Calcula y Llena Depreciacion y Amortizacion dependiendo del metodo        
        if (metodoDA == 0) {
            for (int i = 0; i < años; i++) {
                dya[i] = c.dyaLineal(II, VS, años);
            }
        } else if (metodoDA == 1) {
            for (int i = 0; i < años; i++) {
                dya[i] = c.dyaSDA(años, i + 1, II, VS);
            }
        }
        modelo.addRow(dya);

        // Llena Gastos Administrativos
        for (int i = 0; i < años; i++) {
            ga[i] = c.gA(GA);
        }
        modelo.addRow(ga);

        // Calcula y Llena Bienes Antes de Impuestos        
        for (int i = 0; i < años; i++) {
            double val = iT[i] - c.costoOperacion(CO) - c.cF(EAK) - Double.parseDouble(String.valueOf(dya[i])) - c.gA(GA);
            bai[i] = val;
        }
        modelo.addRow(bai);

        // Calcula y Llena ISR
        for (int i = 0; i < años; i++) {
            isr[i] = c.isr(Double.valueOf(String.valueOf(bai[i])), TI);
        }
        modelo.addRow(isr);

        // Llena PTU
        for (int i = 0; i < años; i++) {
            ptu[i] = c.ptu(PTU);
        }
        modelo.addRow(ptu);

        // Calcula y Llena Bienes Despues de Impuestos        
        for (int i = 0; i < años; i++) {
            bdi[i] = Double.valueOf(String.valueOf(bai[i])) - Double.valueOf(String.valueOf(isr[i])) - Double.valueOf(String.valueOf(ptu[i]));
        }
        modelo.addRow(bdi);

        // Llena Depreciacion y Amotizacion
        modelo.addRow(dya);

        // Llena Aumento de Capital
        // Pendiente a Modificacion
        for (int i = 0; i < años; i++) {
            ak[i] = 0;
        }
        modelo.addRow(ak);

        // Calcula y Llena FNE
        Object fne[] = new Object[años];
        for (int i = 0; i < años; i++) {
            fne[i] = Double.valueOf(String.valueOf(bdi[i])) + Double.valueOf(String.valueOf(dya[i])) - Double.valueOf(String.valueOf(ak[i]));
        }
        modelo.addRow(fne);

        // Calcula y Llena VP        
        double sumVP = 0;
        for (int i = 0; i < años; i++) {
            vp[i] = c.vp(Double.valueOf(String.valueOf(fne[i])), TREMA, i + 1);
            sumVP = sumVP + Double.valueOf(String.valueOf(vp[i]));
        }
        modelo.addRow(vp);
        jTable1.setModel(modelo);

        double pow = Math.pow(1 + TREMA, años);
        double vx = VS / pow;

        float vpn = (float) (sumVP + vx - II);

        jLabel5.setText(String.valueOf(II - AK));
        jLabel7.setText(String.valueOf(sumVP));
        jLabel9.setText(String.valueOf(vx));
        jLabel11.setText(String.valueOf(vpn));

        if (vpn >= 0) {
            jLabel3.setText("Viable");
            jLabel3.setForeground(Color.GREEN);
            jLabel3.setVisible(true);
        } else {
            jLabel3.setText("No Viable");
            jLabel3.setForeground(Color.RED);
            jLabel3.setVisible(true);
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
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Estado de Resultados Proforma");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "1", "2", "3", "4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"+ Ingreso Total"},
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
                {"= Flujo Neto E."},
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
        jScrollPane2.setViewportView(jTable2);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Valor Presente Neto =");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("El Proyecto es:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("jLabel3");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "1", "2", "3", "4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Costo Financiero"},
                {"Pagos"},
                {"Abonos al Capital"},
                {"Saldo"}
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
        jTable4.setEnabled(false);
        jScrollPane4.setViewportView(jTable4);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("-");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("jLabel5");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("+");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("jLabel7");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("+");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("jLabel9");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("=");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("jLabel11");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(107, 107, 107))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)
                        .addComponent(jLabel9)
                        .addComponent(jLabel10)
                        .addComponent(jLabel11))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    // End of variables declaration//GEN-END:variables
}
