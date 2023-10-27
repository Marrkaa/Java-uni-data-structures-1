package Lab2Dmitrovskis;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import studijosKTU.*;

/**
 * Main class for declaring object variables
 * @author Core_Nvidia
 */
public class Player implements KTUable<Player> {
    final static private int minAge = 1;
    final static private int maxAge = 99;
    private String name;    // Object name
    private String surname; // Object surname
    private int age;        // Object age
    private double height;  // Object height
    private double avgAssists;  // Object average assists
    private double avgPoints;   // Object average points
    
    // Temporary variables
    String tempName;    
    String tempSurname;
    int tempAge;
    double tempHeight;
    double tempAvgAssists;
    double tempAvgPoints;
    
    /**
     * Constructor without parameters
     */
    public Player() {
    }

    /**
     * Constructor with parameters
     * @param name object age
     * @param surname object surname
     * @param age object age
     * @param height object height
     * @param avgAssists object average assists
     * @param avgPoints  object average points
     */
    public Player(String name, String surname,
                    int age, double height, double avgAssists, double avgPoints) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.height = height;
        this.avgAssists = avgAssists;
        this.avgPoints = avgPoints;
    }

    /**
     * A method which calls the parse method
     * @param dataString string of data
     */
    public Player(String dataString) {
        this.parse(dataString);
    }

    /**
     * An overridden method
     * @param dataString string of data
     * @return a new object or null
     */
    @Override
    public Player create(String dataString) {
        if(checkData(dataString))
            return new Player(dataString);
        else
            return null;
    }

    /**
     * An overridden method which parses data to objects
     * @param dataString string of data
     */
    @Override
    public final void parse(String dataString) {
        if(checkData(dataString)){
            name = tempName;
            surname = tempSurname;
            age = tempAge;
            height = tempHeight;
            avgAssists = tempAvgAssists;
            setAvgPoints(tempAvgPoints);
        }
        return;
    }
    
    /**
     * A method which checks the data if it is valid
     * @param dataString string of data
     * @return true or false
     */
    public boolean checkData(String dataString){
        Scanner ed = new Scanner(dataString);
        if (!ed.hasNext()) {
            Ks.ern("Trūksta duomenų apie zaidejus -> " + dataString);
            return false;
        }
        try {
            tempName = ed.next();
            tempSurname = ed.next();
            tempAge = ed.nextInt();
            tempHeight = ed.nextDouble();
            tempAvgAssists = ed.nextDouble();
            tempAvgPoints = ed.nextDouble();
        } catch (InputMismatchException e) {
            Ks.ern("Blogas duomenų formatas apie zaidejus -> " + dataString);
            return false;
        } catch (NoSuchElementException e) {
            Ks.ern("Trūksta duomenų apie zaidejus -> " + dataString);
            return false;
        }
        return true;
    }
    
    /**
     * An overridden method which validates the age if it is in the correct bounds
     * @return error string
     */
    @Override
    public String validate() {
        String errorType = "";
        if (age < minAge || age > maxAge) {
            errorType = "Netinkami metai, turi būti ["
                            + minAge + ":" + maxAge + "]";
        }
        return errorType;
    }

    /**
     * Overridden method which formats a line with object data
     * @return formatted string
     */
    @Override
    public String toString() {
        return String.format("%-8s %-8s %-8d %4.2f %4.2f %4.2f",
                        name, surname, age, height,
                            avgAssists, avgPoints);
    }

    /**
     * A method which returns object name
     * @return object name
     */
    public String getName() {
        return name;
    }

    /**
     * A method which returns object surname
     * @return object surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * A method which returns object age
     * @return object age
     */
    public int getAge() {
        return age;
    }

    /**
     * A method which returns object height
     * @return object height
     */
    public double getHeight() {
        return height;
    }

    /**
     * A method which returns object average points
     * @return object average points
     */
    public double getAvgPoints() {
        return avgPoints;
    }
    
    /**
     * A method which returns object average assists
     * @return object average assists
     */
    public double getAvgAssists() {
        return avgAssists;
    }

    /**
     * A method which sets the object average points
     * @param avgPoints average points
     */
    public void setAvgPoints(double avgPoints) {
        this.avgPoints = avgPoints;
    }
    
    /**
     * An overridden compareTo method by age and name
     * @param play Player class object
     * @return 0 or 1 or -1
     */
    @Override
    public int compareTo(Player play) { 
        // object class method for comparison by age and name
        int comp = this.name.compareTo(play.name);
        if(this.age > play.age || this.age == play.age && comp > 0){
            return 1;
        }
        else if(this.age == play.age && comp == 0){
            return 0;
        }
        else{
            return -1;
        }
        
    }
    
    /**
     * Comparator by age and name
     */
    public final static Comparator byAgeAndName = new Comparator() {
       @Override
        public int compare(Object o1, Object o2) {
            int k1 = ((Player) o1).getAge();
            int k2 = ((Player) o2).getAge();
            if(k1 < k2) return -1;
            if(k1 > k2) return +1;
            else if (k1 == k2)
                return (((Player) o1).getName().compareTo(((Player) o2).getName()));
            return 0;
        }
    };
    
    /**
     * Comparator by height
     */
    public final static Comparator byHeight = new Comparator() {
       @Override
        public int compare(Object o1, Object o2) {
            double k1 = ((Player) o1).getHeight();
            double k2 = ((Player) o2).getHeight();
            if(k1 < k2) return +1;
            if(k1 > k2) return -1;
            return 0;
        }
    };
    
    /**
     * Comparator by average points
     */
    public final static Comparator byAvgPoints = new Comparator() {
       @Override
        public int compare(Object o1, Object o2) {
            double k1 = ((Player) o1).getAvgPoints();
            double k2 = ((Player) o2).getAvgPoints();
            if(k1 < k2) return +1;
            if(k1 > k2) return -1;
            return 0;
        }
    };
    
    /**
     * An overridden equals method
     * @param o Object o
     * @return true or false
     */
    @Override
    public boolean equals(Object o)
    {
        if(o == this){
            return true;
        }
        
        if(!(o instanceof Player)){
            return false;
        }
        
        Player second = (Player)o;
                
        return this.name.equals(second.name) && this.surname.equals(second.surname) && this.age == second.age && this.avgAssists == second.avgAssists && this.avgPoints == second.avgPoints && this.height == second.height;
    }

    /**
     * HashCode
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.surname);
        hash = 97 * hash + this.age;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.height) ^ (Double.doubleToLongBits(this.height) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.avgAssists) ^ (Double.doubleToLongBits(this.avgAssists) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.avgPoints) ^ (Double.doubleToLongBits(this.avgPoints) >>> 32));
        return hash;
    }
}