/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estado_de_resultados_proforma;

import java.util.Vector;

/**
 *
 * @author Oda Nobunaga
 */
public class Calculos {

    private float DyA;
    private int años;

    public Calculos(int year) {
        años = year;
    }

    // Depreciacion Lineal
    public Object[] dyaLineal(float II, float VS) {
        Object depreciacionL[] = new Object[años];
        float res = (II - VS);
        DyA = res / años;
        for (int i = 0; i < años; i++) {
            depreciacionL[i] = DyA;
            System.out.println(DyA);
        }
        return depreciacionL;
    }

    // Depreciacion SDA
    public float[] dyaSDA() {

        return null;
    }

    // Calculo de Bienes Antes de Impuestos
    public float[] BAI(float[] IT, float CO[], float[] CF, float[] DA, float[] GA) {
        float bai[] = null;
        for (int i = 0; i < años; i++) {
            bai[i] = IT[i] - CO[i] - CF[i] - DA[i] - GA[i];
        }
        return bai;
    }

    // Calculo de ISR
    public float[] ISR(float[] BAI, float TI) {
        float isr[] = null;
        for (int i = 0; i < años; i++) {
            isr[i] = BAI[i] * TI;
        }
        return isr;
    }

    // Calculo de Bienes Despues de Impuestos
    public float[] BDI(float BAI[], float ISR[], float PTU[]) {
        float bdi[] = null;
        for (int i = 0; i < años; i++) {
            bdi[i] = BAI[i] - ISR[i] - PTU[i];
        }
        return bdi;
    }

    // Calculo de Flujo Neto de Efectivo
    public float[] FNE(float BDI[], float DA[], float AK[]) {
        float fne[] = null;
        for (int i = 0; i < años; i++) {
            fne[i] = BDI[i] + DA[i] - AK[i];
        }
        return fne;
    }

    // Calculo Valor Presente
    public float[] VP(float FNE[], float TREMA) {
        float vp[] = null;
        for (int i = 0; i < años; i++) {
            vp[i] = (float) (FNE[i] / (Math.pow((1 + TREMA), i + 1)));
        }
        return vp;
    }

    // Suma de Valor Presente por Año
    public float sVP(float VP[]) {
        float svp = 0.0f;
        for (int i = 0; i < años; i++) {
            svp = svp + VP[i];
        }
        return svp;
    }

    // Calculo Valor Presente Neto
    public float VPN(float SVP, float TREMA, float VS, float II) {
        float vpn = 0.0f;
        for (int i = 0; i < años; i++) {
            vpn = (float) (SVP + (VS / (Math.pow(1 + TREMA, años))) - II);
        }
        return vpn;
    }

}
