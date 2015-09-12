/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estado_de_resultados_proforma;

import java.awt.Color;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Oda Nobunaga
 */
public class ERP_cT extends javax.swing.JFrame {

    Calculos c;

    float iT[];
    float BAI[];
    float BDI[];
    float FNE[];
    float VP[];
    float CO;
    float II;
    float VS;
    float TI;
    float TREMA;
    int años;
    float CF;
    float GA;
    float PTU;
    float AK;
    float DyA;
    int metodoDA;

    /**
     * Creates new form NewJFrame
     */
    public ERP_cT(float it[], float co, float ii, float vs, float ti, float trema, int year, float cf, float ga, float ptu, float ak, int metodoDyA) {
        c = new Calculos(años);
        metodoDA = metodoDyA;
        iT = it;
        CO = co;
        II = ii;
        VS = vs;
        TI = ti;
        TREMA = trema;
        años = year;
        CF = cf;
        GA = ga;
        PTU = ptu;
        AK = ak;
        BAI = new float[años];
        BDI = new float[años];
        FNE = new float[años];
        VP = new float[años];
        initComponents();
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
    }

    public void llenarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        Object inT[] = new Object[años];
        Object co[] = new Object[años];
        Object cf[] = new Object[años];
        Object da[] = new Object[años];
        Object ga[] = new Object[años];
        Object isr[] = new Object[años];
        Object ptu[] = new Object[años];
        Object ak[] = new Object[años];
        float cO[] = new float[años];
        float cF[] = new float[años];
        float dA[] = new float[años];
        float gA[] = new float[años];
        float iSR[] = new float[años];
        float pTU[] = new float[años];
        float aK[] = new float[años];

        // Llena Ingresos Totales
        for (int i = 0; i < años; i++) {
            inT[i] = iT[i];
        }
        modelo.addRow(inT);

        // Llena Costo de Operacion
        for (int i = 0; i < años; i++) {
            co[i] = CO;
            cO[i] = CO;
        }
        modelo.addRow(co);

        // Llena Costo Financiero
        for (int i = 0; i < años; i++) {
            cf[i] = CF;
            cF[i] = CF;
        }
        modelo.addRow(cf);

        // Calcula y Llena Depreciacion y Amortizacion dependiendo del metodo
        if (metodoDA == 0) {
            float tot = II - VS;
            DyA = (tot / años);
            for (int i = 0; i < años; i++) {
                dA[i] = DyA;
                da[i] = DyA;
            }
        } else if (metodoDA == 1) {
            for (int i = 1; i <= años; i++) {
                float up = años - (i - 1);
                float down = (años * (años + 1)) / 2;
                float res = (up / down) * (II - VS);
                dA[i - 1] = res;
                da[i - 1] = res;
            }
        }
        modelo.addRow(da);

        // Llena Gastos Administrativos
        for (int i = 0; i < años; i++) {
            ga[i] = GA;
            gA[i] = GA;
        }
        modelo.addRow(ga);

        // Calcula y Llena Bienes Antes de Impuestos
        Object bai[] = new Object[años];
        for (int i = 0; i < años; i++) {
            BAI[i] = iT[i] - cO[i] - cF[i] - dA[i] - gA[i];
            bai[i] = BAI[i];
        }
        modelo.addRow(bai);

        // Calcula y Llena ISR
        for (int i = 0; i < años; i++) {
            isr[i] = BAI[i] * TI;
            iSR[i] = (float) isr[i];
        }
        modelo.addRow(isr);

        // Llena PTU
        for (int i = 0; i < años; i++) {
            ptu[i] = PTU;
            pTU[i] = (float) ptu[i];
        }
        modelo.addRow(ptu);

        // Calcula y Llena Bienes Despues de Impuestos
        Object bdi[] = new Object[años];
        for (int i = 0; i < años; i++) {
            BDI[i] = BAI[i] - iSR[i] - pTU[i];
            bdi[i] = BDI[i];
        }
        modelo.addRow(bdi);

        // Llena Depreciacion y Amotizacion
        for (int i = 0; i < años; i++) {
            da[i] = dA[i];
        }
        modelo.addRow(da);

        // Llena Aumento de Capital
        for (int i = 0; i < años; i++) {
            ak[i] = AK;
            aK[i] = (float) ak[i];
        }
        modelo.addRow(ak);

        // Calcula y Llena FNE
        Object fne[] = new Object[años];
        for (int i = 0; i < años; i++) {
            FNE[i] = BDI[i] + dA[i] - aK[i];
            fne[i] = FNE[i];
        }
        modelo.addRow(fne);

        // Calcula y Llena VP
        Object vp[] = new Object[años];
        double sumVP = 0;
        double dow = 0;
        for (int i = 0; i < años; i++) {
            dow = Math.pow(1 + TREMA, i + 1);
            VP[i] = (float) (FNE[i] / dow);
            vp[i] = VP[i];
            sumVP = sumVP + VP[i];
        }
        modelo.addRow(vp);
        jTable1.setModel(modelo);

        double pow = Math.pow(1 + TREMA, años);
        double vx = VS / pow;

        float vpn = (float) (sumVP + vx - II);

        jTextField1.setText(String.valueOf(vpn));

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
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

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

        jLabel1.setText("Valor Presente Neto =");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("El Proyecto es:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("jLabel3");

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
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(107, 107, 107))))
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
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
