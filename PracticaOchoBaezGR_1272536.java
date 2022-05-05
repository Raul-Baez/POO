package practicas;

import java.util.Scanner;

public class PracticaOchoCorregidaBaezGR_1272536{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char key;
        boolean trigger = false;
        Torneo torneo = new Torneo(Captura.getString("Nombre del torneo"), Captura.getString("Region del torneo"));

        while(true){
            System.out.println("\n 1 > Agregar equipo \n 2 > Agregar jugador \n 3 > Ver estadisticas \n e > Exit");
            System.out.print(" >>> ");
            key = scanner.next().charAt(0);
            System.out.println();

            switch (Character.toLowerCase(key)) {
                case '1' -> {
                    torneo.agregarEquipo(Captura.getString("Nombre del equipo"), Captura.getString("Division"), Captura.getString("Entrenador"), Captura.getInt("Torneos Jugados"), Captura.getInt("Torneos ganados"), Captura.getInt("Torneos perdidos"));
                }

                case '2' -> {
                    if(torneo.getEquipos(0) == null){
                        System.out.println(" !> Ni un equipo ha sido creado aun ");
                        break;
                    }
                    String auxNombre, auxNumero;
                    int i;
                    torneo.verEquipos();
                    System.out.print(" Equipo del jugador >>> ");
                    i = scanner.nextInt();
                    auxNombre = Captura.getString("Nombre del jugador");
                    auxNumero = Captura.getString("Numero del uniforme");
                    if(ValidacionesGenerales.verificarNombreUnico(auxNombre, torneo) && ValidacionesGenerales.verificarNumeroUnico(auxNumero, torneo, i)) {
                        torneo.getEquipos(i - 1).agregarJugador(auxNombre, auxNumero, Captura.getInt("Puntos anotados"), Captura.getString("Posicion de juego"), Captura.getString("Estatura"));
                    }
                    else System.out.println(" !> Ha ocurrido un error en la captura, no repita jugadores en distintos equipos o numeros dentro de los mismos");
                }

                case '3' -> {
                    if(torneo.getEquipos(0) == null){
                        System.out.println(" !> Ni un equipo ha sido creado aun ");
                        break;
                    }
                    for(Equipo equipo : torneo.getEquipos()){
                        System.out.println(" Puntos de " + equipo.getNombreEquipo() + " > " + equipo.getTotalPoints());
                    }
                }

                case 'e' -> {
                    System.out.println("\n *> Finalizando el programa...");
                    System.exit(117);
                }
                default -> System.out.printf(" !> Entrada inv%clida, por favor ingrese una nuevamente...\n", 225);
            }
        }
    }
}

class Torneo{
    private String nombreTorneo;
    private String regionTorneo;
    private final int cantidadEquiposMaxima = 5;
    private int partidosJugados = 0;
    private int partidosPendien;
    private int contadorEquipos = 0;
    private Equipo[] equipos = new Equipo[cantidadEquiposMaxima];

    public Torneo(String nombreTorneo, String regionTorneo){
        this.nombreTorneo = nombreTorneo;
        this.regionTorneo = regionTorneo;
        partidosPendien = (cantidadEquiposMaxima * 2) - 1;
    }

    public void agregarEquipo(String nombreEquipo, String divisionEquipo, String entrenador, int torneosJugados, int torneosGanados, int torneosPerdidos){
        if (contadorEquipos < cantidadEquiposMaxima){
            equipos[contadorEquipos] = new Equipo(nombreEquipo, divisionEquipo, entrenador, torneosJugados, torneosGanados, torneosPerdidos);
            contadorEquipos++;
        }
    }

    public void verEquipos(){
        int i = 1;
        System.out.println("\t Lista de equipos ");
        for(Equipo equipo: equipos){
            if(equipo != null){
                System.out.printf(" %s > %s \n",i,equipo.getNombreEquipo());
                i++;
            }
        }
    }

    public Equipo[] getEquipos() {
        return equipos;
    }

    public Equipo getEquipos(int index) {
        return equipos[index];
    }
}

class Equipo{
    private String nombreEquipo;
    private String divisionEquipo;
    private String entrenador;
    private int torneosJugados;
    private int torneosGanados;
    private int torneosPerdidos;
    private int contadorJugadores = 0;
    private int cantidadJugadoresMaxima = 15;
    private Jugador[] jugadores = new Jugador[cantidadJugadoresMaxima];

    public Equipo(String nombreEquipo, String divisionEquipo, String entrenador, int torneosJugados, int torneosGanados, int torneosPerdidos){
        this.nombreEquipo = nombreEquipo;
        this.divisionEquipo = divisionEquipo;
        this.entrenador = entrenador;
        this.torneosJugados = torneosJugados;
        this.torneosGanados = torneosGanados;
        this.torneosPerdidos = torneosPerdidos;
    }

    public int getTotalPoints(){
        int out = 0;
        for(Jugador jugador: jugadores){
            if(jugador != null) out += jugador.getPuntosAnotados();
        }
        return out;
    }

    public void agregarJugador(String nombre, String numeroUniforme, int puntosAnotados, String posicion, String estatura){
        if(contadorJugadores < cantidadJugadoresMaxima){
            jugadores[contadorJugadores] = new Jugador(nombre,numeroUniforme,puntosAnotados,posicion,estatura);
            contadorJugadores++;
        }
    }

    public int getPlayersAmount(){
        return contadorJugadores;
    }

    public String getNombreEquipo(){
        return nombreEquipo;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }
}

class Jugador{
    private String nombre;
    private String numeroUniforme;
    private int puntosAnotados;
    private String posicion;
    private String estatura;

    Jugador(String nombre, String numeroUniforme, int puntosAnotados, String posicion, String estatura){
        this.nombre = nombre;
        this.numeroUniforme = numeroUniforme;
        this.puntosAnotados = puntosAnotados;
        this.posicion = posicion;
        this.estatura = estatura;
    }

    public String getNumeroUniforme(){
        return numeroUniforme;
    }

    public int getPuntosAnotados(){
        return puntosAnotados;
    }

    public String getNombre(){
        return nombre;
    }
}

class Captura{
    public static String getString(String msg){
        Scanner scanner = new Scanner(System.in);
        System.out.print(" >>> " + msg + " > ");
        return scanner.nextLine();
    }

    public static int getInt(String msg){
        Scanner scanner = new Scanner(System.in);
        System.out.print(" >>> " + msg + " > ");
        return scanner.nextInt();
    }
}

class ValidacionesGenerales{

    public static boolean verificarNombreUnico(String nombre, Torneo torneo){
        int i = 0;
        for(Jugador jugador: torneo.getEquipos(i).getJugadores()){
            i++;
            if(jugador == null) return true;
            if(nombre.equalsIgnoreCase(jugador.getNombre())) return false;
        }
        return true;
    }

    public static boolean verificarNumeroUnico(String numeroUniforme, Torneo torneo, int equipo){
        for(Jugador jugador: torneo.getEquipos(equipo).getJugadores()){
            if(jugador == null) return true;
            if(numeroUniforme.equals(jugador.getNumeroUniforme())) return false;
        }
        return true;
    }
}

