package practicas;

import java.util.Objects;
import java.util.Scanner;

public class PracticaCincoBaezGR_1272536 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        char tecla;
        int index;
        while(true){
            System.out.println("\n a > Crear cuenta \n b > Acceder \n c > Salir");
            System.out.print(" >>> ");
            tecla = scanner.next().charAt(0);
            String key;

            switch (Character.toLowerCase(tecla)) {
                case 'a' -> {
                    Banco.registrarCuenta();
                }

                case 'b' -> {
                    System.out.print("\n >>> Nombre completo (Empezando por apellido paterno) > ");
                    key = scanner2.nextLine().toUpperCase().replaceAll(" +", " ");
                    if(Banco.acceder(key)){
                        Banco.mostrarOperaciones(Banco.buscarRegistro(key));
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
}

class Banco{
    static Cuenta[] cuentas = new Cuenta[100];
    static int registros = 0;

    static void registrarCuenta(){
        String[] datos = new String[3];
        Scanner scan = new Scanner(System.in);
                 //Captura de nombre
        while(true){
            System.out.print(" >>> Nombre completo (Empezando por apellido paterno) > ");
            datos[0] = scan.nextLine().toUpperCase().replaceAll(" +", " ");
            if (!Validaciones.validarNombre(datos[0])){
                System.out.printf(" !> El nombre ingresado no es v%clido, porfavor ingrese un nombre correcto\n",225);
            }
            else break;
        }
                 //Captura de PIN
        while(true){
            System.out.printf(" >>> Ingrese un c%cdigo PIN para la cuenta [____] > ",243);
            datos[1] = scan.nextLine().replaceAll(" +", " ");
            if (!Validaciones.validarPIN(datos[1])){
                System.out.printf(" !> El c%cdigo PIN debe estar conformado por n%cmeros, ingrese un PIN v%clido\n",243,250,225);
            }
            else break;
        }
        cuentas[registros] = new Cuenta(datos[0], datos[1]);
                  //Primer deposito
        System.out.printf(" >>> Para activar su cuenta bancaria, por favor ingrese el monto de su primer dep%csito (MXN) > ",243);
        cuentas[registros].setSaldo(Validaciones.obtenerDouble());
        registros++;
    }

    static boolean acceder(String nombre){
        Scanner scanner = new Scanner(System.in);
        String pass;
        int index = buscarRegistro(nombre);
        if(index >= 0){
            System.out.print(" >>> Por favor ingrese su PIN > ");
            pass = scanner.nextLine();
            if (Objects.equals(pass, cuentas[index].getPin())){
                System.out.println(" >>> Acceso concedido... ");
                return true;
            }
            else System.out.println(" !> Acceso denegado... ");
        }
        return false;
    }

    static int buscarRegistro(String nombre){
        int index = 0;
        do{
            if (nombre.equals(cuentas[index].getNombreTitular())){
                return index;
            }
            index++;
        } while (!Objects.isNull(cuentas[index]));
        System.out.println(" !> Ese registro no existe ");
        return -1;
    }

    static void mostrarOperaciones(int index){
        Scanner scanner = new Scanner(System.in);
        char tecla;
        boolean continuar = true;

        while(continuar){
            System.out.printf("\n\t - Saldo en la cuenta %s - \n a > Depositar \n b > Retirar \n c > Salir \n",cuentas[index].getSaldo());
            System.out.print(" >>> ");
            tecla = scanner.next().charAt(0);
            double aux;
            switch (Character.toLowerCase(tecla)) {
                case 'a' -> {
                    System.out.print(" >>> Monto a depositar > ");
                    aux = Validaciones.obtenerDouble();
                    if (aux > 0){
                        cuentas[index].setSaldo(cuentas[index].getSaldo() + aux);
                    }
                    else System.out.println(" !> Seleccione la opci%cn 'b' si desea retirar ");
                }
                case 'b' -> {
                    System.out.print(" >>> Monto a retirar > ");
                    aux = Validaciones.obtenerDouble();
                    if (aux <= cuentas[index].getSaldo()){
                        cuentas[index].setSaldo(cuentas[index].getSaldo() - aux);
                    }
                    else System.out.println(" !> No puede retirar una cantidad mayor a la disponible ");
                }
                case 'c' -> {
                    continuar = false;
                }
                default -> throw new IllegalStateException("!> Opci" + (char)243 + "n inv" + (char)225 + "lida: " + tecla);
            }
        }
    }

    private static class Cuenta{
        private static String nombreTitular;
        private static String pin;
        private static double saldo;

        Cuenta(String nombreTitular, String pin){
            Cuenta.nombreTitular = nombreTitular;
            Cuenta.pin = pin;
        }
                //Getters
        String getNombreTitular(){ return Cuenta.nombreTitular; }
        String getPin(){ return Cuenta.pin; }
        double getSaldo() { return Cuenta.saldo; }
                //Setters
        void setSaldo(double saldo){ Cuenta.saldo = saldo; }
    }
}

class Validaciones{
    public static double obtenerDouble(){
        var scan = new Scanner(System.in);
        while (true){
            if (scan.hasNextDouble()) {
                return scan.nextDouble();
            }
            else {
                scan.next();
                System.out.printf(" !> La entrada no se reconoce como un n%cmero, favor de ingresar un n%cmero v%clido > ",250,250,225);
            }
        }
    }

    public static boolean validarNombre(String nombreCompleto){
        int i = 0, j;
        String[] nombrePartes = nombreCompleto.split(" ");
        if(nombrePartes.length < 3 ) return false;
        while(i < nombrePartes.length){
            j = 0;
            while(j < nombrePartes[i].length()){
                if(!Character.isLetter(nombrePartes[i].charAt(j))){ return false; }
                j++;
            }
            i++;
        }
        return true;
    }

    public static boolean validarPIN(String pin){
        int i=0, len = pin.length();

        if (len > 4){ return false; }
        while(i < len){
            if(!Character.isDigit(pin.charAt(i))){ return false; }
            i++;
        }
        return true;
    }
}