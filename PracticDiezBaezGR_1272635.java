package practicas;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class PracticDiezBaezGR_1272635 {
    public static void main(String[] args) {
        //Instancia zoo
        Zoo zoo = new Zoo("Zoologico de Tijuana");
        System.out.println("\n\t =| Bienvenido al " + zoo.getName() + " |=\n");

        //Instancia y guardado de animales iniciales
        zoo.addAnimal(new Parrot("Chimuelo","Australiano",3,true,"Verde"));
        zoo.addAnimal(new Wolf("Nasus","Iberico", 5, true,"Cafe"));
        zoo.addAnimal(new Cat("Corey", "Habana",7,true,"Naranja"));
        zoo.addAnimal(new Cat("Taylor", "Habana", 6, true,"Gris"));
        zoo.addAnimal(new Sheep("Bob","Merino",5, true,"Blanco"));

        //Instancia y guardado de veterinarios iniciales
        zoo.addVeterinarian(new Veterinarian("Kurt Ambroose",3754.76,"Matutino"));
        zoo.addVeterinarian(new Veterinarian("Jerome Cable",3873.28,"Vespertino"));

        int key, subKey;
        while (true){
            System.out.println(" 1 > Agregar animal \n 2 > Remover animal \n 3 > Ver animales \n 4 > Agregar veterinario \n 5 > Remover veterinario \n 6 > Ver veterinarios \n 7 > Comprobar funciones \n 8 > Exit");
            key = Utils.getInt("Opcion");
            switch (key) {
                case 1 -> {
                    System.out.println("\n 1 > Perico \n 2 > Lobo \n 3 > Gato \n 4 > Oveja");
                    subKey = Utils.getInt("Opcion");
                    System.out.println();

                    //Switch anidado para cada clase de animal disponible en el zoologico
                    switch (subKey){
                        case 1 -> { zoo.addAnimal(createParrot()); }
                        case 2 -> { zoo.addAnimal(createWolf()); }
                        case 3 -> { zoo.addAnimal(createCat()); }
                        case 4 -> { zoo.addAnimal(createSheep()); }
                    }
                    System.out.println();
                }
                case 2 -> {
                    System.out.println();
                    zoo.removeAnimal(Utils.getString("Identificacion"));
                    System.out.println();
                }
                case 3 -> {
                    System.out.println();
                    zoo.showAnimals();
                }
                case 4 -> {
                    System.out.println();
                    zoo.addVeterinarian(createVeterinarian());
                    System.out.println();
                }
                case 5 -> {
                    System.out.println();
                    zoo.removeVeterinarian(Utils.getString("Identificacion"));
                    System.out.println();
                }
                case 6 -> {
                    System.out.println();
                    zoo.showVeterinarians();
                }
                case 7 -> {
                    zoo.testFunctions();
                }
                case 8 -> {
                    System.out.println("\n *> Finalizando el programa...");
                    System.exit(117);
                }
                default -> System.out.printf(" !> Entrada inv%clida, por favor ingrese una nuevamente...\n", 225);
            }
        }
    }

    public static Veterinarian createVeterinarian(){
        return new Veterinarian(Utils.getString("Nombre"),Utils.getDouble("Sueldo semanal (MXN)"), Utils.getString("Turno laboral"));
    }

    public static Parrot createParrot(){
        return new Parrot(Utils.getString("Nombre"),Utils.getString("Raza"), Utils.getInt("Edad en años"), Utils.getBoolean("Estado de vacunacion (true/false)"), Utils.getString("Color del plumaje"));
    }

    public static Wolf createWolf(){
        return new Wolf(Utils.getString("Nombre"),Utils.getString("Raza"), Utils.getInt("Edad en años"), Utils.getBoolean("Estado de vacunacion (true/false)"), Utils.getString("Color del pelaje"));
    }

    public static Cat createCat(){
        return new Cat(Utils.getString("Nombre"),Utils.getString("Raza"), Utils.getInt("Edad en años"), Utils.getBoolean("Estado de vacunacion (true/false)"), Utils.getString("Color del pelaje"));
    }

    public static Sheep createSheep(){
        return new Sheep(Utils.getString("Nombre"),Utils.getString("Raza"), Utils.getInt("Edad en años"), Utils.getBoolean("Estado de vacunacion (true/false)"), Utils.getString("Color del pelaje"));
    }
}


class Utils{
    public static String generateId(){
        Random rnd = new Random();
        int number = rnd.nextInt(199);
        return String.format("%03d", number);
    }

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

    public static double getDouble(String msg){
        Scanner scanner = new Scanner(System.in);
        System.out.print(" >>> " + msg + " > ");
        return scanner.nextDouble();
    }

    public static boolean getBoolean(String msg){
        Scanner scanner = new Scanner(System.in);
        System.out.print(" >>> " + msg + " > ");
        return scanner.nextBoolean();
    }
}

class Zoo{
    private final String name;
    private Animal[] animals = new Animal[10];
    private Veterinarian[] veterinarians = new Veterinarian[4];

    public Zoo(String name){
        this.name = name;
    }

    public void addAnimal(Animal x){
        int i;
        for (i=0; i<animals.length; i++){
            if (animals[i] == null){
                animals[i] = x;
                break;
            }
        }
    }

    public void addVeterinarian(Veterinarian x){
        int i;
        for (i=0; i<veterinarians.length; i++){
            if (veterinarians[i] == null){
                veterinarians[i] = x;
                break;
            }
        }
    }

    public void removeAnimal(String id){
        Animal[] animalsUpdated = new Animal[10];
        int i = 0;
        for(Animal animal : animals){
            if (animal != null && !animal.getId().equalsIgnoreCase(id)){
                animalsUpdated[i] = animal;
                i++;
            }
        }
        animals = animalsUpdated;
    }

    public void removeVeterinarian(String id){
        Veterinarian[] veterinariansUpdated = new Veterinarian[10];
        int i = 0;
        for(Veterinarian veterinarian : veterinarians){
            if (veterinarian != null && !veterinarian.getId().equalsIgnoreCase(id)){
                veterinariansUpdated[i] = veterinarians[i];
                i++;
            }
        }
        veterinarians = veterinariansUpdated;
    }

    public void showAnimals(){
        int i = 0;
        System.out.println(Arrays.toString(animals));
        for(Animal animal : animals){
            if (animal != null){
                i++;
                System.out.println(" > Nombre >>> " + animal.getName());
                System.out.println(" > Raza >>> " + animal.getRace());
                System.out.println(" > Edad >>> " + animal.getAge());
                System.out.println(" > Vacuna >>> " + (animal.getVaccinated() ? "Aplicada" : "Pendiente"));
                System.out.println(" > Identificacion >>> " + animal.getId() + "\n");
            }
        }
        if (i == 0) System.out.println(" !> No hay animales en el zoologico ahora mismo\n");
    }

    public void showVeterinarians(){
        int i = 0;
        for(Veterinarian veterinarian : veterinarians){
            if (veterinarian != null){
                i++;
                System.out.println(" > Nombre >>> " + veterinarian.getName());
                System.out.println(" > Salario semanal (MXN) >>>" + veterinarian.getSalary());
                System.out.println(" > ID-Servicio >>> " + veterinarian.getId());
                System.out.println(" > Turno laboral >>> " + veterinarian.getWorkShift() + "\n");
            }
        }
        if (i == 0) System.out.println(" !> No hay veterinarios contratados, hay que buscar uno lo antes posible\n");
    }

    public void testFunctions(){
        System.out.println();
        for (Animal animal : animals){
            if (animal != null){
                animal.eat();
                animal.sleep();
                animal.makeSound();
            }
        }
        System.out.println();
        for (Veterinarian veterinarian : veterinarians){
            if (veterinarian != null){
                veterinarian.vaccinate();
                veterinarian.heal();
                veterinarian.examinate();
            }
        }
        System.out.println();
    }

    public Animal[] getAnimals() {
        return animals;
    }

    public Animal getAnimals(int index) {
        return animals[index];
    }

    public Veterinarian[] getVeterinarians() {
        return veterinarians;
    }

    public Veterinarian getVeterinarians(int index) {
        return veterinarians[index];
    }

    public String getName() {
        return name;
    }
}

abstract class Animal{
    private final String name;
    private final String race;
    private int age;
    private boolean vaccinated;
    private final String id;

    public Animal(String name, String race, int age, boolean vaccinated){
        this.name = name;
        this.race = race;
        this.age = age;
        this.vaccinated = vaccinated;
        id = ("A-" + Utils.generateId());
    }

    abstract public void eat();
    abstract public void sleep();
    abstract public void makeSound();

    public String getName() {
        return name;
    }

    public String getRace() {
        return race;
    }

    public int getAge() {
        return age;
    }

    public boolean getVaccinated(){
        return vaccinated;
    }

    public String getId() {
        return id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setVaccinated() {
        vaccinated = true;
    }
}

class Veterinarian{
    private final String name;
    private double salary;
    private String workShift;
    private final String id;

    public Veterinarian(String name, double salary, String workShift){
        this.name = name;
        this.salary = salary;
        this.workShift = workShift;
        id = ("S-" + Utils.generateId());
    }

    public void vaccinate(){
        System.out.println(" > Dr." + name + " proceeds on the vaccination process");
    }

    public void heal(){
        System.out.println(" > Dr." + name + " heals the animal wounds");
    }

    public void examinate(){
        System.out.println(" > Dr." + name + " proceeds on the patient examination");
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public String getId() {
        return id;
    }

    public String getWorkShift() {
        return workShift;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setWorkShift(String workShift) {
        this.workShift = workShift;
    }
}

class Parrot extends Animal{
    private final String feathersColor;

    public Parrot(String name, String race, int age, boolean vaccinated, String feathersColor){
        super(name, race, age, vaccinated);
        this.feathersColor = feathersColor;
    }

    @Override
    public void eat() {
        System.out.println(" > " + getName() + " starts to eat some seeds" );
    }

    @Override
    public void sleep() {
        System.out.println(" > " + getName() + " is now sleeping, even if it doesn't look like it does");
    }

    @Override
    public void makeSound() {
        System.out.println(" > " + getName() + " just squawked");
    }

    public String getFeathersColor() {
        return feathersColor;
    }
}

class Wolf extends Animal{
    private final String coatColor;

    public Wolf(String name, String race, int age, boolean vaccinated, String coatColor){
        super(name, race, age, vaccinated);
        this.coatColor = coatColor;
    }

    @Override
    public void eat() {
        System.out.println(" > " + getName() + " starts to eat some meat");
    }

    @Override
    public void sleep() {
        System.out.println(" > " + getName() + " is now sleeping, looks just like a dog");
    }

    @Override
    public void makeSound() {
        System.out.println(" > " + getName() + " just howled");
    }

    public String getCoatColor() {
        return coatColor;
    }
}

class Cat extends Animal{
    private final String coatColor;

    public Cat(String name, String race, int age, boolean vaccinated, String coatColor){
        super(name, race, age, vaccinated);
        this.coatColor = coatColor;
    }

    @Override
    public void eat() {
        System.out.println(" > " + getName() + " starts to eat some fish");
    }

    @Override
    public void sleep() {
        System.out.println(" > " + getName() + " is now sleeping, it looks so comfortable");
    }

    @Override
    public void makeSound() {
        System.out.println(" > " + getName() + " just meowed");
    }

    public String getCoatColor() {
        return coatColor;
    }
}

class Sheep extends Animal{
    private final String coatColor;

    public Sheep(String name, String race, int age, boolean vaccinated, String coatColor){
        super(name, race, age, vaccinated);
        this.coatColor = coatColor;
    }

    @Override
    public void eat() {
        System.out.println(" > " + getName() + " starts to eat some grass");
    }

    @Override
    public void sleep() {
        System.out.println(" > " + getName() + " is now sleeping, it looks really peacefully");
    }

    @Override
    public void makeSound() {
        System.out.println(" > " + getName() + " just bleated");
    }

    public String getCoatColor() {
        return coatColor;
    }
}