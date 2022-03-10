package practicas;

import java.util.Random;
import java.util.Scanner;

public class PracticaCuatroBaezGR_1272536 {
    public static void main(String[] args) {
        Mexicano[] mexicanos = new Mexicano[3]; //Arreglo de objetos
        Scanner scan = new Scanner(System.in);
        char tecla;
        byte i = 0, j;
        boolean necesitaCURP, necesitaRFC;
        while(true){
            System.out.println("\n a > Capturar \n b > Mostrar \n c > Salir");
            System.out.print(" >>> ");
            tecla = scan.next().charAt(0); //Escanear solo el primer char

            switch (Character.toLowerCase(tecla)) {
                case 'a' -> {
                    while (tecla != 'n' && tecla != 'N'){
                        necesitaCURP = i == 0 || i == 2;
                        necesitaRFC = i == 1 || i == 2;
                        if (i == 3) { //La variable "i" lleva la cuenta del número de registros, cuando esta llega a 3
                                      // no es posible hacer más de estos
                            System.out.printf("\n !> %cLa capacidad m%cxima de registros ha sido alcanzada!\n", 161, 225);
                            break;
                        }
                        //El método capturar devuelve un objeto de la clase Mexicano,
                        // el cual es almacenado en el arreglo de objetos "mexicanos"
                        mexicanos[i] = capturar(necesitaCURP,necesitaRFC);
                        i++;
                        System.out.printf("\n >>> %cContinuar? (S)i/(N)o > ",191);
                        tecla = scan.next().charAt(0);
                    }
                }

                case 'b' -> { //La variable "est" es cada elemento iterable del arreglo de objetos "estudiantes"
                    j = 0;    //El uso de un ciclo for each es equivalente a while(estudiantes[j] != null)
                    for (Mexicano mexicano : mexicanos){
                        j++;
                        if (mexicano == null) {
                            break;
                        }
                        System.out.printf("\n\t|Registro #%s|\n", j);
                        mexicano.mostrar();
                    }
                }

                case 'c' -> {
                    System.out.println("\n *> Finalizando el programa...");
                    System.exit(117);
                }

                default -> throw new IllegalStateException("!> Opci" + (char)243 + "n inv" + (char)225 + "lida: " + tecla);
            }
        }
    }

    public static Mexicano capturar(boolean necesitaCURP, boolean necesitaRFC){
        String[] entidades = {"AS","BS","CL","CS","DF","GT","HG","MC","MS",
                "NL","PL","QR","SL","TC","TL","YN","NE","BC","CC","CM","CH"
                ,"DG","GR","JC","MN","NT","OC","QT","SP","SR","TS","VZ","ZS"};
        Scanner scan = new Scanner(System.in);
        Mexicano objeto;
        String nombre, fechaNacimiento, entidadNacimiento;
        char genero, tecla;

        System.out.print("\n >>> Nombre completo [AM AP N] > ");
        nombre = scan.nextLine().toUpperCase();
        System.out.print(" >>> Fecha de nacimiento [aa mm dd] > ");
        fechaNacimiento = scan.nextLine();
        System.out.print(" >>> Entidad de nacimiento [__] > ");
        entidadNacimiento = scan.nextLine().toUpperCase();

        if (existeEnArreglo(entidades,entidadNacimiento) == -1){
            System.out.printf(" !> La entidad ingresada no coincide con ninguna entidad federativa mexicana " +
                    "%cCuenta con residencia mexicana? S/N > ",191);
            tecla = scan.next().toUpperCase().charAt(0);
            if (tecla == 'S'){
                entidadNacimiento = "BC";
            }
            else if (tecla == 'N'){
                throw new IllegalStateException("!> Imposible realizar el tramite ");
            }
        }

        System.out.printf(" >>> G%cnero H/M> ",233);
        genero = scan.next().toUpperCase().charAt(0);
        //Se crea el objeto que será devuelto al arreglo de objetos "mexicanos" en el método main
        objeto = new Mexicano(nombre);

        if (necesitaCURP && necesitaRFC){
            objeto.generarCURP(fechaNacimiento,genero,entidadNacimiento);
            objeto.generarRFC(fechaNacimiento);
        }
        else if (necesitaCURP){
            objeto.generarCURP(fechaNacimiento,genero,entidadNacimiento);
        }
        else if (necesitaRFC){
            objeto.generarRFC(fechaNacimiento);
        }
        return objeto;
    }

    public static int existeEnArreglo(String[] arreglo, String busqueda) {
        for (int x = 0; x < arreglo.length; x++) {
            if (arreglo[x].equals(busqueda)) {
                return x;
            }
        }
        return -1;
    }
}

class Mexicano{
    String nombre,curp,rfc;

    Mexicano(String nombre){
        this.nombre = nombre;
    }

    void generarCURP(String fechaNacimiento, char genero, String entidadNacimiento){
        Random random = new Random();
        char[] temp = new char[18];
        int i;
        String[] nombreSeparado, fechaNacimientoSeparada;
        nombreSeparado = nombre.split(" ");
        fechaNacimientoSeparada = fechaNacimiento.split(" ");

        temp[0] = nombreSeparado[0].charAt(0);
        temp[1] = nombreSeparado[0].charAt(encontrarLetra(nombreSeparado[0],true,false));
        temp[2] = nombreSeparado[1].charAt(0);
        temp[3] = nombreSeparado[2].charAt(0);
        temp[4] = fechaNacimientoSeparada[0].charAt(0);
        temp[5] = fechaNacimientoSeparada[0].charAt(1);
        temp[6] = fechaNacimientoSeparada[1].charAt(0);
        temp[7] = fechaNacimientoSeparada[1].charAt(1);
        temp[8] = fechaNacimientoSeparada[2].charAt(0);
        temp[9] = fechaNacimientoSeparada[2].charAt(1);
        temp[10] = genero;
        temp[11] = entidadNacimiento.charAt(0);
        temp[12] = entidadNacimiento.charAt(1);
        temp[13] = nombreSeparado[0].charAt(encontrarLetra(nombreSeparado[0],false,true));
        temp[14] = nombreSeparado[1].charAt(encontrarLetra(nombreSeparado[1],false,true));
        temp[15] = nombreSeparado[2].charAt(encontrarLetra(nombreSeparado[2],false,true));
        for (i=16; i<18; i++) temp[i] = (char)(random.nextInt(26) + 'A');

        curp = new String(temp);
    }

    void generarRFC(String fechaNacimiento){
        Random random = new Random();
        char[] temp = new char[13];
        int i;
        String[] nombreSeparado, fechaNacimientoSeparada;
        nombreSeparado = nombre.split(" ");
        fechaNacimientoSeparada = fechaNacimiento.split(" ");

        temp[0] = nombreSeparado[0].charAt(0);
        temp[1] = nombreSeparado[0].charAt(encontrarLetra(nombreSeparado[0],true,false));
        temp[2] = nombreSeparado[1].charAt(0);
        temp[3] = nombreSeparado[2].charAt(0);
        temp[4] = fechaNacimientoSeparada[0].charAt(0);
        temp[5] = fechaNacimientoSeparada[0].charAt(1);
        temp[6] = fechaNacimientoSeparada[1].charAt(0);
        temp[7] = fechaNacimientoSeparada[1].charAt(1);
        temp[8] = fechaNacimientoSeparada[2].charAt(0);
        temp[9] = fechaNacimientoSeparada[2].charAt(1);
        for (i=10; i<13; i++) temp[i] = (char)(random.nextInt(26) + 'A');

        rfc = new String(temp);
    }

    int encontrarLetra(String cadena, boolean vocal, boolean consonante){
        String vocales = "AEIOU";
        String consonantes = "BCDFGHJKLMNÑPQRSTVWXYZ";
        int posicion = 0, contador = 0;
        for(char c : cadena.toCharArray()){
            if (vocal && vocales.contains(String.valueOf(c))){
                return posicion;
            }
            else if(consonante && consonantes.contains(String.valueOf(c))){
                if (contador != 0) return posicion;
                contador++;
            }
            posicion++;
        }
        return posicion;
    }

    void mostrar(){
        System.out.println(" > Nombre >>> " + nombre);
        if (curp != null) System.out.println(" > CURP >>> " + curp);
        if (rfc != null) System.out.println(" > RFC >>> " + rfc);
    }
}

