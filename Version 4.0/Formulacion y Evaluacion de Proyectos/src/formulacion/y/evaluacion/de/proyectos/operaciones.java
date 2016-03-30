/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulacion.y.evaluacion.de.proyectos;

import java.text.DecimalFormat;
import java.util.Locale;

/**
 *
 * @author demg
 */
public class operaciones {

    // Convierte numeros en notacion cientifica a notacion decimal
    public String conversion(double valor) {
        Locale.setDefault(Locale.US);
        DecimalFormat num = new DecimalFormat("########.##");
        return num.format(valor);
    }

    // Calcula las Anualidades si existe prestamo
    public double anualidades(double AK, double TIP, int AP) {
        double anualidades = 0;
        double up = TIP * Math.pow(1 + TIP, AP);
        double down = Math.pow(1 + TIP, AP) - 1;
        anualidades = (AK) * (up / down);
        return anualidades;
    }

    public double vp(double fne, double trema, int i) {
        double vp = 0;

        vp = fne / Math.pow((1 + trema), i);

        return vp;
    }

    public double isr(double bai, double ti) {
        double isr;
        isr = bai * ti;
        return isr;
    }

    public double ptu(double ptu, double bai) {
        double ptuR;

        ptuR = bai * ptu;

        return ptuR;
    }

    public double vs(double vasa, double trema, int años) {
        double vp = 0;

        vp = vasa / Math.pow((1 + trema), años);

        return vp;
    }

}
