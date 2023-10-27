package Lab2Dmitrovskis;

import java.util.Locale;
import studijosKTU.*;

/**
 * Class in which we test the different methods
 * @author Core_Nvidia
 */
public class PlayerTest {
    ListKTUx<Player> test = new ListKTUx<>(new Player());

    ListKTU<Player> copy; // klonavimo metodo clone testui

    /**
     * method selection method
     */
    void methodSelection() {
        //checkDifferentPlayers();
        //formatPlayerList();
        //searchInList();
        //addToList();
        //checkSorting();
        //checkRemove();
        //checkSet();
        //checkIndexOf();
        //checkContains();
        //checkAddWithIndex();
        //addToList();
        //checkFormatting();
        //checkSorting();
        //findMatching();
        //mergeTwoSorted();
    }

    /**
     * A method which checks the different ways of parsing an object
     */
    void checkDifferentPlayers() {
        Player p1 = new Player("Dumbe", "Domantas", 21, 199.02, 12.5, 15.5);
        Player p2 = new Player("Gedas", "Gedaitis", 35, 198.15, 20.5, 18.4);
        Player p3 = new Player("Justas", "Justinaitis", 19, 188.5, 10.0, 5.55);

        Player p4 = new Player();
        Player p5 = new Player();
        Player p6 = new Player();
        
        p4.parse("Jokubas Jokubaitis 24 178,5 21,0 aaa");
        p5.parse("Alfonsas Alfonsaitis 31 179,4 29,5 4,0");
        p6.parse("Titas Titaitis 25 182,6 27,0 5,0");

        Ks.oun(p1);
        Ks.oun(p2);
        Ks.oun(p3);
        Ks.oun("Pirmų 3 zaideju amziaus vidurkis= "
                    + (p1.getAge()+ p2.getAge() + p3.getAge()) / 3);
        Ks.oun(p4);
        Ks.oun(p5);
        Ks.oun(p6);
        Ks.oun("Kitų 3 zaideju ugio suma= "
                    + (p4.getHeight()+ p5.getHeight() + p6.getHeight()));
    }

    /**
     * A method which checks the various ways of adding to a list and cloning a list
     */
    void formatPlayerList() {
        // Kartu patikrina ir clone metodo veikimą
        Player p1 = new Player("Dumbe", "Domantas", 21, 199.02, 12.5, 15.5);
        Player p2 = new Player("Gedas", "Gedaitis", 35, 198.15, 20.5, 18.4);
        Player p3 = new Player("Justas", "Justinaitis", 19, 188.5, 10.0, 5.55);
        test.add(p1);
        test.add(p2);
        test.add(p3);
        test.println("Pirmi 3 zaidejai");

        copy = test.clone(); // Kitas sąrašas - pirmų trijų automobilių kopija

        // Papildome sąrašą bandomisji dar trimis aotomobiliais (sąrašas kopija nesikeis):
        test.add("Jokubas Jokubaitis 24 178,5 21,0 aaa");
        test.add("Alfonsas Alfonsaitis 31 179,4 29,5 4,0");
        test.add("Titas Titaitis 25 182,6 27,0 5,0");

        test.println("Visi 5 zaidejai");

        Ks.oun("Kopijos elementai");
        for (Player p : copy) {
            Ks.oun(p);
        }
        Ks.oun("Kopijos elementai su nuoroda ::");
        copy.forEach(System.out::println);

        Ks.oun("\nPirmų 3 zaideju amziaus vidurkis= "
                + (test.get(0).getAge()+ test.get(1).getAge()
                    + test.get(2).getAge()) / 3);

    }

    /**
     * A method which checks the searchInList method
     */
    void searchInList() {
        int sk = 0;
        for (Player p : test) {
            if (p.getSurname().compareTo("Domantas") == 0) {
                sk++;
            }
        }
        Ks.oun("Zaideju su pavarde Domantas yra = " + sk);

        // Kopijos testas:
        sk = 0;
        for (Player p : test) {
            if (p.getSurname().compareTo("Domantas") == 0) {
                sk++;
            }
        }
        Ks.oun("Kopijoje zaideju su pavarde Domantas yra = " + sk);
    }

    /**
     * A method which checks the add method
     */
    void addToList() {
        for (int i = 0; i < 8; i++) {
            test.add(new Player("Aiva", "Aivaraitis", 
                    50 - i, 200.0 - i, 100.0 - i * 2, 1.0 + i * 2));
        }
        test.add("Aokubas Jokubaitis 24 178,5 21,0 aaa");
        test.add("Dumbe Domantas 19 200,0 29,5 4,0");
        test.add("Titas Titaitis 25 182,6 27,0 5,0");
        test.add("Jokubas Mokubaitis 24 194,5 22,4 20,7");
        test.println("Testuojamų zaideju sąrašas");
        test.save("ban.txt");
    }

    /**
     * A method which checks the formatting of lists
     */
    void checkFormatting() {
        PlayerTestDiff testDiff = new PlayerTestDiff();

        testDiff.players.load("ban.txt");
        testDiff.players.println("Bandomasis rinkinys");

        test = testDiff.formatByAge(31);
        test.println("Jaunesni nei 31");

        test = testDiff.formatByMaxHeight();
        test.println("Patys auksciausi");

        test = testDiff.formatByName("D");
        test.println("Turi būti tik vardai is D raides");

        test = testDiff.formatByName("Jokubas J");
        test.println("Turi būti tik Jokubas Jokubaitis");
    }

    /**
     * A method which checks all of the sorting methods
     */
    void checkSorting() {
        PlayerTestDiff testDiff = new PlayerTestDiff();
        testDiff.players.load("ban.txt");
        testDiff.players.println("Bandomasis rinkinys");
        testDiff.players.sortBuble(Player.byAgeAndName);
        testDiff.players.println("Rūšiavimas pagal amziu ir varda");
        testDiff.players.sortBuble(Player.byAvgPoints);
        testDiff.players.println("Rūšiavimas pagal vid taskus");
        testDiff.players.sortBuble();
        testDiff.players.println(" Rūšiavimas pagal compareTo - amziu ir varda ");
        testDiff.players.mergeSort();
        testDiff.players.println("Rūšiavimas");
    }
    
    /**
     * A method which checks the remove method
     */
    void checkRemove()
    {
        Player p1 = new Player("Dumbe", "Domantas", 21, 199.02, 12.5, 15.5);
        Player p2 = new Player("Gedas", "Gedaitis", 35, 198.15, 20.5, 18.4);
        Player p3 = new Player("Justas", "Justinaitis", 19, 188.5, 10.0, 5.55);
        
        test.add(p1);
        test.add(p2);
        test.add(p3);  
        test.remove(0);
        
        if(!test.isEmpty()){
            for (Player p : test) {
               Ks.oun(p);
            }
        } else
            Ks.ern("Tuscias sarasas!");
    }
    
    /**
     * A method which checks the set method
     */
    void checkSet(){
        Player p1 = new Player("Dumbe", "Domantas", 21, 199.02, 12.5, 15.5);
        Player p2 = new Player("Gedas", "Gedaitis", 35, 198.15, 20.5, 18.4);
        Player p3 = new Player("Justas", "Justinaitis", 19, 188.5, 10.0, 5.55);
        
        test.add(p1);
        test.add(p2);
        test.add(p3);
        
        Player p4 = new Player();
        p4.parse("Jokubas Jokubaitis 24 178,5 21,0 7,7");
        
        test.set(0, p4);
        
        if(!test.isEmpty()){
            for (Player p : test) {
               Ks.oun(p);
            }
        } else
            Ks.ern("Tuscias sarasas!");
    }
    
    /**
     * A method which checks the indexOf method
     */
    void checkIndexOf(){
        Player p1 = new Player("Dumbe", "Domantas", 21, 199.02, 12.5, 15.5);
        Player p2 = new Player("Gedas", "Gedaitis", 35, 198.15, 20.5, 18.4);
        Player p3 = new Player("Justas", "Justinaitis", 19, 188.5, 10.0, 5.55);
        
        test.add(p1);
        test.add(p2);
        test.add(p3);
        
        if(!test.isEmpty()){
            Ks.oun("Pasirinkto elemento indexas sarase yra: " + test.indexOf(p2));
        } else
            Ks.ern("Tokio elemento nera sarase!");
    }
    
    /**
     * A method which checks the contains method
     */
    void checkContains(){
        Player p1 = new Player("Dumbe", "Domantas", 21, 199.02, 12.5, 15.5);
        Player p2 = new Player("Gedas", "Gedaitis", 35, 198.15, 20.5, 18.4);
        Player p3 = new Player("Justas", "Justinaitis", 19, 188.5, 10.0, 5.55);
        Player p4 = new Player();
        p4.parse("Jokubas Jokubaitis 24 178,5 21,0 20,7");
        
        test.add(p1);
        test.add(p2);
        test.add(p3);
        test.add(p4);
        
        if(!test.isEmpty()){
            if(test.contains(p4)){
                Ks.oun("Pasirinktas elementas egzistuoja sarase");
            }else
                Ks.oun("Pasirinktas elementas neegzistuoja sarase");
                
        } else
            Ks.ern("Tokio elemento nera sarase!");
    }
    
    /**
     * A method which checks the insert method
     */
    public void checkAddWithIndex(){
        Player p1 = new Player("Dumbe", "Domantas", 21, 199.02, 12.5, 15.5);
        Player p2 = new Player("Gedas", "Gedaitis", 35, 198.15, 20.5, 18.4);
        Player p3 = new Player("Justas", "Justinaitis", 19, 188.5, 10.0, 5.55);
        Player p4 = new Player();
        p4.parse("Jokubas Jokubaitis 24 178,5 21,0 20,7");
        
        test.add(p1);
        test.add(p2);
        test.add(p3);
        add(1, p4, test);
        
        if(!test.isEmpty()){
            for (Player p : test) {
               Ks.oun(p);
            }
        } else
            Ks.ern("Tuscias sarasas!");
    }
    
    /**
     * A method which finds matching objects in two different lists
     */
    public void findMatching(){
        Player p1 = new Player("Dumbe", "Domantas", 21, 199.02, 12.5, 15.5);
        Player p2 = new Player("Gedas", "Gedaitis", 35, 198.15, 20.5, 18.4);
        Player p3 = new Player("Justas", "Justinaitis", 19, 188.5, 10.0, 5.55);
        ListKTUx<Player> players1 = new ListKTUx<>(new Player());
        ListKTUx<Player> players2 = new ListKTUx<>(new Player());
        players1.add(p1);
        players1.add(p2);
        players1.add(p3);
        Player p4 = new Player();
        p4.parse("Justas Justinaitis 19 188,5 10,0 5,55");
        add(p4, players2);
        
        Ks.oun("Sutape elementai: ");
        for(int i = 0; i < players1.size(); i++){
            for(int j = 0; j < players2.size(); j++){
                if(players1.get(i).equals(players2.get(j))){
                    Ks.oun(players1.get(i));
                }
            }
        }
    }
    
    /**
     * A method which merges two sorted lists
     */
    public void mergeTwoSorted(){
        Player p1 = new Player("Dumbe", "Domantas", 21, 199.02, 12.5, 15.5);
        Player p2 = new Player("Gedas", "Gedaitis", 35, 198.15, 20.5, 18.4);
        Player p3 = new Player("Justas", "Justinaitis", 19, 188.5, 10.0, 5.55);
        ListKTUx<Player> players1 = new ListKTUx<>(new Player());
        players1.add(p1);
        players1.add(p2);
        players1.add(p3);
        
        ListKTUx<Player> players2 = new ListKTUx<>(new Player());
        Player p4 = new Player();
        Player p5 = new Player();
        p4.parse("Jokubas Jokubaitis 27 178,5 21,0 aaa"); 
        p5.parse("Alfonsas Alfonsaitis 27 172,5 6,8 7,7");
        add(p4, players2);
        add(p5, players2);
        
        players1.sortBuble();
        players2.sortBuble();
        
        for (Player p : players1) {
            Ks.oun(p);
        }
        Ks.oun("\n");
        for (Player p : players2) {
            Ks.oun(p);
        }
        Ks.oun("\n");
        
        for(int i = 0; i < players2.size(); i++){
            for(int j = 0; j < players1.size(); j++){
                if(players1.get(j).compareTo(players2.get(i)) == 1){
                    players1.add(j, players2.get(i));
                    break;
                }
            }
        }
              
        for (Player p : players1) {
            Ks.oun(p);
        }
    }
    
    /**
     * A method which adds players to the list if the object is not null
     * @param player Player class object
     * @param list a list
     */
    public void add(Player player, ListKTUx<Player> list) {
        if (!isEmptyPlayer(player)) {
            list.add(player);
        }
    }
    
    /**
     * A method which inserts players to the lists certain index if the object is not null
     * @param k index
     * @param player Player class object
     * @param list a list
     */
    public void add(int k, Player player, ListKTUx<Player> list){
        if (!isEmptyPlayer(player)) {
            list.add(k, player);
        }
    }
    
    /**
     * A method which checks if an object is null
     * @param player Player class object
     * @return true or false
     */
    public boolean isEmptyPlayer(Player player) {
        if (player == null || player.getName() == null || player.getName().isEmpty()) {
            return true;
        }
        return false;
    }
    
    /**
     * Metodų testas
     * @param args 
     */
    public static void main(String... args) {
        // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT"));
        new PlayerTest().methodSelection();
    }
}
