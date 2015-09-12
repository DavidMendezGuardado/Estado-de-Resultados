/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpgm;

/**
 *
 * @author Oda Nobunaga
 */
public class Calculos {

    private float DyA;

    public Calculos() {
    }

    // Costo de Operacion
    public double costoOperacion(double co) {
        double CO = co;
        return CO;
    }

    // Costo Financiero
    public double cF(boolean ex) {
        double val = 0;
        if (ex == true) {
        } else {
            val = 0;
        }
        return val;
    }

    // Depreciacion Lineal
    public double dyaLineal(double II, double VS, int años) {
        double depreciacionL = 0;
//        double res = (II - VS);
//        DyA = res / años;
        depreciacionL = (II - VS) / años;
        return depreciacionL;
    }

    // Depreciacion SDA
    public double dyaSDA(int años, int i, double II, double VS) {
        double sda = 0;

        double dividendo = años - (i - 1);
        double divisorUp = años * (años + 1);
        double divisor = divisorUp / 2;
        sda = dividendo / divisor * (II - VS);

        return sda;
    }

    public double gA(double ga) {
        return ga;
    }

    public double isr(double bai, double ti) {
        double isr;
        isr = bai * ti;
        return isr;
    }

    public double ptu(double ptu) {
        return ptu;
    }

    public double vp(double fne, double trema, int i) {
        double vp = 0;

        vp = fne / Math.pow((1 + trema), i);

        return vp;
    }
}
