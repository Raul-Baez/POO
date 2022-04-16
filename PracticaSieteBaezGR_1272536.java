package practicas;

import java.util.Scanner;

public class PracticaSieteBaezGR_1272536 {
    public static void main(String[] args) {

//        System.out.println(" getName(): " + red.getName());
//        System.out.println(" getSize(): " + red.getSize());
//        System.out.println(" getSizeOnMeasure(): " + red.getSizeOnMeasure());
//        System.out.println(" getRange(): " + red.getRange());
//        System.out.println(" getRangeOnMeasure(): " + red.getRangeOnMeasure());
//        System.out.println(" getDamage(): " + red.getDamage());
        Scanner scanner = new Scanner(System.in);
        char key;
        while(true){
            System.out.println("\n a > Play \n b > Show \n c > Exit");
            System.out.print(" >>> ");
            key = scanner.next().charAt(0);
            switch (Character.toLowerCase(key)) {
                case 'a' -> {
                    Gameplay.play();
                }
                case 'b' -> {
                    BirdsArray.generateArray();
                    BirdsArray.viewArray();
                }
                case 'c' -> {
                    System.out.println("\n *> Finalizando el programa...");
                    System.exit(117);
                }
                default -> throw new IllegalStateException("!> Invalid option " + key);
            }
        }

    }
}

class Bird{
    byte size;      // 1 = Small, 2 = Medium-small, 3= Medium, 4 = Medium-large, 5 = Large, 6 = XL
    byte range;     // 1 = Low, 2 = Medium, 3 = High
    byte damage;   // [0,100]
    String name;

    Bird(byte size, byte range, byte damage){
        this.size = size;
        this.range = range;
        this.damage = damage;
    }

    public String getName(){
        return name;
    }

    public byte getSize(){
        return size;
    }

    public byte getRange(){
        return range;
    }

    public byte getDamage() {
        return damage;
    }

    public String getSizeOnMeasure(){
        switch (getSize()) {
            case 1 -> { return "Small"; }
            case 2 -> { return "Medium-small"; }
            case 3 -> { return "Medium"; }
            case 4 -> { return "Medium-large"; }
            case 5 -> { return "Large"; }
            case 6 -> { return "XL"; }
        }
        return null;
    }

    public String getRangeOnMeasure(){
        switch (getSize()) {
            case 1 -> { return "Low"; }
            case 2 -> { return "Medium"; }
            case 3 -> { return "High"; }
        }
        return null;
    }

    void spawn() { System.out.println(" > " + getName() + " just appeared and it's loaded to fire! "); }

    void aim(){ System.out.println(" > Aiming... "); }

    void fire(){ System.out.println(" > " + getName() + " is now on its way... "); }

    void impact() { System.out.println(" > " + getName() + " has made impact with a structure, calculating destruction based on its " + getDamage() + " damage points... "); }

    void despawn(){ System.out.println(" > Despawning... "); }
}

class Red extends Bird{
    Red(byte size, byte range, byte damage){
        super(size, range, damage);
        this.name = "Red";
    }

    void useSpecial(){
        System.out.println(getName() + " used its power, producing a battle cry!");
    }
}

class Blue extends Bird{
    Blue(byte size, byte range, byte damage){
        super(size, range, damage);
        this.name = "Blue";
    }

    void useSpecial(){
        System.out.println(getName() + " used its power, it multiplied itself by 3!");
    }
}

class Chuck extends Bird{
    Chuck(byte size, byte range, byte damage){
        super(size, range, damage);
        this.name = "Chuck";
    }

    void useSpecial(){
        System.out.println(getName() + " used its power, now it goes way faster!");
    }
}

class Matilda extends Bird{
    Matilda(byte size, byte range, byte damage){
        super(size, range, damage);
        this.name = "Matilda";
    }

    void useSpecial(){
        System.out.println(getName() + " used its power, leaving some bombs on the way!");
    }
}

class Bomb extends Bird{
    Bomb(byte size, byte range, byte damage){
        super(size, range, damage);
        this.name = "Bomb";
    }

    void useSpecial(){
        System.out.println(getName() + " used its power, it just exploded!");
    }

}

class Terrence extends Bird{
    Terrence(byte size, byte range, byte damage){
        super(size, range, damage);
        this.name = "Terrence";
    }

    void useSpecial(){
        System.out.println(getName() + " used its power, producing a really low pitch battle cry!");
    }
}

class BirdsArray{
    static Bird[] birdsArray = new Bird[6];

    public static void generateArray() {
        birdsArray[0] = new Bird((byte) 1, (byte) 3, (byte) 50);
        birdsArray[1] = new Bird((byte) 3, (byte) 2, (byte) 70);
        birdsArray[2] = new Bird((byte) 5, (byte) 1, (byte) 85);
    }

    static void viewArray(){
        System.out.println();
        byte i = 0;
        for (Bird bird : birdsArray) {
            i++;
            System.out.println("\tBird #" + i);
            System.out.println(" > Name >>> " + bird.getName());
            System.out.println(" > Size >>> " + bird.getSize());
            System.out.println(" > Range >>> " + bird.getRange());
            System.out.println(" > Damage >>> " + bird.getDamage());
        }
    }
}

class Gameplay{
    static Red red = new Red((byte)2,(byte)2,(byte)50);
    static Blue blue = new Blue((byte)1,(byte)3,(byte)40);
    static Chuck chuck = new Chuck((byte)3,(byte)2,(byte)60);
    static Matilda matilda = new Matilda((byte)5,(byte)2,(byte)80);
    static Bomb bomb = new Bomb((byte)4,(byte)2,(byte)70);
    static Terrence terrence = new Terrence((byte)6,(byte)1,(byte)100);

    static void play(){
        Scanner scanner = new Scanner(System.in);
        char key;
        byte i = 0;
        while(i < 7) {
            i++;
            System.out.println("\n a > Red \n b > Blue \n c > Chuck \n d > Matilda \n e > Bomb \n f > Terrence ");
            System.out.print(" >>> Next Bird > ");
            key = scanner.next().charAt(0);
            System.out.println();
            switch (Character.toLowerCase(key)) {
                case 'a' -> {
                    System.out.print(" spawn(): "); red.spawn();
                    System.out.print(" aim(): "); red.aim();
                    System.out.print(" fire(): "); red.fire();
                    System.out.print(" useSpecial(): "); red.useSpecial();
                    System.out.print(" impact(): "); red.impact();
                    System.out.print(" despawn(): "); red.despawn();
                }
                case 'b' -> {
                    System.out.print(" spawn(): "); blue.spawn();
                    System.out.print(" aim(): "); blue.aim();
                    System.out.print(" fire(): "); blue.fire();
                    System.out.print(" useSpecial(): "); blue.useSpecial();
                    System.out.print(" impact(): "); blue.impact();
                    System.out.print(" despawn(): "); blue.despawn();
                }
                case 'c' -> {
                    System.out.print(" spawn(): "); chuck.spawn();
                    System.out.print(" aim(): "); chuck.aim();
                    System.out.print(" fire(): "); chuck.fire();
                    System.out.print(" useSpecial(): "); chuck.useSpecial();
                    System.out.print(" impact(): "); chuck.impact();
                    System.out.print(" despawn(): "); chuck.despawn();
                }
                case 'd' -> {
                    System.out.print(" spawn(): "); matilda.spawn();
                    System.out.print(" aim(): "); matilda.aim();
                    System.out.print(" fire(): "); matilda.fire();
                    System.out.print(" useSpecial(): "); matilda.useSpecial();
                    System.out.print(" impact(): "); matilda.impact();
                    System.out.print(" despawn(): "); matilda.despawn();
                }
                case 'e' -> {
                    System.out.print(" spawn(): "); bomb.spawn();
                    System.out.print(" aim(): "); bomb.aim();
                    System.out.print(" fire(): "); bomb.fire();
                    System.out.print(" useSpecial(): "); bomb.useSpecial();
                    System.out.print(" impact(): "); bomb.impact();
                    System.out.print(" despawn(): "); bomb.despawn();
                }
                case 'f' -> {
                    System.out.print(" spawn(): "); terrence.spawn();
                    System.out.print(" aim(): "); terrence.aim();
                    System.out.print(" fire(): "); terrence.fire();
                    System.out.print(" useSpecial(): "); terrence.useSpecial();
                    System.out.print(" impact(): "); terrence.impact();
                    System.out.print(" despawn(): "); terrence.despawn();
                }
                default -> throw new IllegalStateException("!> Invalid option " + key);
            }
        }
        System.out.println("\n\t !> There's no more birds!");
    }
}