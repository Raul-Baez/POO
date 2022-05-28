package practicas;

import java.io.*;
import java.util.Arrays;

public class PracticaOnceBaezGR_1272536 {
    public static void main(String[] args) {
        Alumno[] alumnos = new Alumno[3];
        alumnos[0] = new Alumno("Kurt Ambroose","S-051");
        alumnos[1] = new Alumno("Frederick Ellsworth","S-104");
        alumnos[2] = new Alumno("Jerome Cable","S-092");

        int i;
        String[] data = new String[21]; for (i=0; i < data.length; i++){ data[i] = ""; } //Setup output array
        StringBuilder aux = new StringBuilder();
        FileInputStream fis;

        i=0; //Resetear i, ya que fue usada para formatear el arreglo data
        try {
            fis = new FileInputStream("practica11.csv");
            int bytee = fis.read();

            while(bytee != -1){
                if( bytee == 44 ){              //Si el byte leido es una coma,
                    data[i] = aux.toString();   //entonces se guarda la cadena aux en data,
                    aux = new StringBuilder();  //se resetea esta misma cadena aux
                    i++;                        //y se aumenta en uno el indice i
                }
                else {
                    if (bytee >= 48 && bytee <= 57) { //Si el byte leido corresponde a un caracter [0 - 9]
                        aux.append((char) bytee);     //entonces este se agrega como caracter a la cadena aux
                    }
                }
                bytee = fis.read();
            }
            data[i] = aux.toString(); //Se agrega el ultimo byte leido a data, ya que este no entra en el ciclo
            fis.close();
        }
        catch (IOException e){ System.out.println(" !> Error >>> " + e); }

        int[] calificaciones = new int[21];
        transformarArray(data,calificaciones);
        agregarCalificaciones(alumnos,calificaciones);
        mostrarArreglo(alumnos);
        serializarDatosAlumnado(alumnos);
    }

    public static void serializarDatosAlumnado(Alumno[] alumnos){
        FileOutputStream fos;

        try {
            fos = new FileOutputStream("datos.txt");
            ObjectOutputStream stream = new ObjectOutputStream(fos);
            for(Alumno alumno : alumnos){
                stream.writeObject(alumno);
            }
            stream.close();
            System.out.println("\n $> Archivo generado exitosamente... ");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void mostrarArreglo(Alumno[] alumnos){
        for(Alumno alumno : alumnos){
            alumno.mostrarDatos();
        }
    }

    public static void agregarCalificaciones(Alumno[] alumnos, int[] calificaciones){
        int i, j = 0;
        for(Alumno alumno : alumnos){
            for(i = 0 ; i < Alumno.getMaterias().length ; i++){
                alumno.addCalificacion(calificaciones[j]);
                j++;
            }
        }
    }

    public static void transformarArray(String[] arrStr, int[] arrInt){
        int i;
        for(i = 0 ; i < arrStr.length; i++){
            arrInt[i] = convertirStr_a_Int(arrStr[i]);
        }
    }

    public static int convertirStr_a_Int(String str){
        int val = 0;
        try {
            val = Integer.parseInt(str);
        }
        catch (NumeroNoValidoException e) { System.out.println(" !> Error >>> " + e); }
        return val;
    }
}

class NumeroNoValidoException extends IllegalArgumentException{
    public NumeroNoValidoException(){ super(); }
}

abstract class Persona{
    private final String nombre;
    private final String matricula;

    public Persona(String nombre, String matricula){
        this.nombre = nombre;
        this.matricula = matricula;
    }

    public abstract void mostrarDatos();

    public String getMatricula() {
        return matricula;
    }

    public String getNombre() {
        return nombre;
    }
}

class Alumno extends Persona implements Serializable{
    private static final String[] MATERIAS = {" CE "," CD "," MD "," POO"," AD "," MKT"," CST"};
    private final double[] calificaciones = new double[7];
    private double promedio;
    private int index = 0;

    Alumno(String nombre, String matricula){
        super(nombre,matricula);
    }

    public void addCalificacion(double x){
        calificaciones[index] = x;
        index++;
        calcularPromedio();
    }

    private void calcularPromedio(){
        promedio = 0;
        for(double c : calificaciones){
            promedio+=c;
        }
        promedio/=MATERIAS.length;
    }

    @Override
    public void mostrarDatos(){
        System.out.println("\n > Nombre >>> " + this.getNombre());
        System.out.println(" > Matricula >>> " + this.getMatricula());
        System.out.println(" > Materias >>>  " + Arrays.toString(MATERIAS));
        System.out.println(" > Historial >>> " + Arrays.toString(calificaciones));
        System.out.println(" > Promedio >>> " + promedio);
    }

    public static String[] getMaterias(){
        return MATERIAS;
    }
}
