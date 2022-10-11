/* 
* Cálculo de la polarización de un transistor en Emisor Común
* Este programa es cortesia del blog trescientosbaudios.blog
* La distribución y copia del mismo es libre. La licencia es del tipo
* Creative Commons al igual que la del blog.
* La versión de este programa es la v1.0, creado el 3 de Enero del 2020.
*/


import java.io.*;

public class Emisor_comun {
    
    static String inicializar() {

      //**************** FUNCIONES DE ENTRADA DE DATOS ****************

      String buzon = "";
      InputStreamReader flujo = new InputStreamReader(System.in);
      BufferedReader teclado = new BufferedReader(flujo);
      try {
          buzon = teclado.readLine();
      } catch (IOException e) {
          System.out.append("Entrada incorrecta");
      }
      return buzon;
    }
    static int entero() {
        int valor = Integer.parseInt(inicializar());
        return valor;
    }
    static double real() {
        double valor = Double.parseDouble(inicializar());
        return valor;
    }
     static String cadena() {
        String valor = inicializar();
        return valor;
    }
    static char caracter() {
        String valor = inicializar();
        return valor.charAt(0);
    }

    //******************* PROGRAMA *******************************

    public static void main(String[] args) {
        System.out.printf("\n**********************************");
        System.out.printf("\n** POLARIZACION EN EMISOR COMUN **");
        System.out.printf("\n**********************************");
        
        System.out.printf ("\n\nTension de alimentacion (V): ");
        double VCC = real();

        System.out.printf ("Corriente de colector (mA): ");
        double IC = real() / 1000;

        System.out.printf ("Beta del transistor: ");
        double BETA = real();

        double VE = 0.1 * VCC; // Tensión de emisor

        double RE = VE / IC;
        double RC = 4 * RE;

        double VC = VCC - (IC * RC); // Tensión de colector
        double VCE = VC - VE; // Tensión colector - emisor

      
        System.out.printf("\n****************************");
        System.out.printf("\n** TENSIONES DEL CIRCUITO **");
        System.out.printf("\n****************************");
       
        System.out.printf("\n\nLa tension VC es de %.2f voltios.", VC);
        System.out.printf("\nLa tension VCE es de %.2f voltios.", VCE);
        System.out.printf("\nLa tension VE es de %.2f voltios.", VE);

        System.out.printf("\n\n******************************************");
        System.out.printf("\n** RESISTENCIAS DE EMISOR Y DE COLECTOR **");
        System.out.printf("\n******************************************");

        System.out.printf("\n\nLa resistencia RC es de %.0f ohmios.", RC);
        System.out.printf("\nLa resistencia RE es de %.0f ohmios.", RE);

        double Idivisor = (IC / BETA) * 10;
        double V2 = VE + 0.7;
        double R2 = V2 / Idivisor;
        double V1 = VCC - V2;
        double R1 = V1 / Idivisor;

        System.out.printf("\n\n*****************************************");
        System.out.printf("\n** RESISTENCIAS DEL DIVISOR DE TENSION **");
        System.out.printf("\n*****************************************");

        System.out.printf("\n\nLa resistencia R1 es de %.0f ohmios.", R1);
        System.out.printf("\nLa resistencia R2 es de %.0f ohmios.\n", R2);

        if (BETA < 100) {          
          System.out.printf ("\n\nLa beta del transistor es reducida, podria cargar en exceso el divisor de tension.");
          System.out.printf ("\nPuedes reducir el valor de R1 y R2, o bien emplear un par de transistores en Darlington. :)");
        }
    }
}



