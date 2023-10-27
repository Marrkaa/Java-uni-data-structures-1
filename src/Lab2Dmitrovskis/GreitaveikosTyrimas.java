package Lab2Dmitrovskis;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;
import studijosKTU.*;

/**
 * Class for time complexion analysis
 * @author Core_Nvidia
 */
public class GreitaveikosTyrimas {
    Player[] basePlayer1;
    ListKTU<Player> pSeries = new ListKTU<>();
    Random rand = new Random();  // atsitiktinių generatorius
    int[] examCounts = {10_000, 16_000, 22_000, 28_000};
    //pabandykite, gal Jūsų kompiuteris įveiks šiuos eksperimentus
    //paieškokite ekstremalaus apkrovimo be burbuliuko metodo
    //static int[] examCounts = {10_000, 20_000, 40_000, 80_000};

    void generatePlayers(int count) {
        String[][] pa = {
            {"Dumbe", "Domantas", "Gomantas", "Momantas"},
            {"Arnas", "Armantas", "Karmantas", "Tarmantas"},
            {"Paulius", "Paulauskas", "Raulauskas", "Laulauskas"},
            {"Gedas", "Gedaits", "Kedaitis", "Redaitis"},
            {"Justas", "Justaitis", "Kustaitis", "Sedaitis"},
            {"Sidas", "Sidaitis", "Ridaitis", "Kidaitis"}
        };
        basePlayer1 = new Player[count];
        rand.setSeed(2016);
        for (int i = 0; i < count; i++) {
            int name = rand.nextInt(pa.length);        // markės indeksas  0..
            int surname = rand.nextInt(pa[name].length - 1) + 1;// modelio indeksas 1..
            basePlayer1[i] = new Player(pa[name][0], pa[name][surname],
                        19 + rand.nextInt(20), // metai tarp 1994 ir 2013
                        180.0 + rand.nextDouble() * 20.0, // rida tarp 6000 ir 228000
                        1.0 + rand.nextDouble() * 30, // kaina tarp 1000 ir 351_000
                        1.0 + rand.nextDouble() * 30);
        }
        Collections.shuffle(Arrays.asList(basePlayer1));
        pSeries.clear();
        for (Player p : basePlayer1) {
            pSeries.add(p);
        }
    }

    void basicAnalysis(int elementCount) {
        // Paruošiamoji tyrimo dalis
        long t0 = System.nanoTime();
        generatePlayers(elementCount);
        ListKTU<Player> pSeries2 = pSeries.clone();
        ListKTU<Player> pSeries3 = pSeries.clone();
        ListKTU<Player> pSeries4 = pSeries.clone();
        ListKTU<Player> pSeries5 = pSeries.clone();
        long t1 = System.nanoTime();
        System.gc();
        System.gc();
        System.gc();
        long t2 = System.nanoTime();

        //  Greitaveikos bandymai ir laiko matavimai
        pSeries.sortJava();
        long t3 = System.nanoTime();
        pSeries2.sortJava(Player.byHeight);
        long t4 = System.nanoTime();
        pSeries3.sortBuble();
        long t5 = System.nanoTime();
        pSeries4.sortBuble(Player.byHeight);
        long t6 = System.nanoTime();
        pSeries5.mergeSort();
        long t7 = System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f %7.4f %7.4f %7.4f\n", elementCount,
                    (t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9,
                    (t4 - t3) / 1e9, (t5 - t4) / 1e9, (t6 - t5) / 1e9, (t7 - t6) / 1e9);
    }
    
    void removeAnalysis(int elementCount) {
        // Paruošiamoji tyrimo dalis
        long t0 = System.nanoTime();
        generatePlayers(elementCount);
        int index = -1;
        long t1 = System.nanoTime();
        System.gc();
        System.gc();
        System.gc();
        long t2 = System.nanoTime();

        //  Greitaveikos bandymai ir laiko matavimai
        for(Player player: pSeries){
            index++;
            if(player.getAge() > 21){
                pSeries.remove(index);
                index--;
            }
        }
        long t3 = System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f \n", elementCount,
                    (t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9);
    }
    
    void addAnalysis(int elementCount) {
        // Paruošiamoji tyrimo dalis
        long t0 = System.nanoTime();
        generatePlayers(elementCount);
        ListKTU<Player> temp = new ListKTU<>();
        long t1 = System.nanoTime();
        System.gc();
        System.gc();
        System.gc();
        long t2 = System.nanoTime();

        //  Greitaveikos bandymai ir laiko matavimai
        for(Player player: pSeries){
            if(player.getAge() < 21){
                temp.add(player);
            }
        }
        long t3 = System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f \n", elementCount,
                    (t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9);
    }

    void analysisSelection() {
        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("memTotal= " + memTotal);
        //Pasižiūrime kaip generuoja automobilius (20) vienetų)
        generatePlayers(20);
        for (Player p : pSeries) {
            Ks.oun(p);
        }
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - Rūšiavimas sisteminiu greitu būdu be Comparator");
        Ks.oun("4 - Rūšiavimas sisteminiu greitu būdu su Comparator");
        Ks.oun("5 - Rūšiavimas List burbuliuku be Comparator");
        Ks.oun("6 - Rūšiavimas List burbuliuku su Comparator");
        Ks.oun("7 - Rūšiavimas List mergeSort be Comparator");
        Ks.ouf("%6d %7d %7d %7d %7d %7d %7d %7d \n", 0, 1, 2, 3, 4, 5, 6, 7);
        for (int n : examCounts) {
            basicAnalysis(n);
        }
        Ks.oun("Rikiavimo metodų greitaveikos tyrimas baigtas.");
//        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
//        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
//        Ks.oun("3 - Pasririnkto indekso elemento salinimas");
//        Ks.ouf("%6d %7d %7d %7d \n", 0, 1, 2, 3);
//        for (int n : examCounts) {
//            removeAnalysis(n);
//        }
//        Ks.oun("Naikinimo metodo greitaveikos tyrimas baigtas.");
//        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
//        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
//        Ks.oun("3 - Tam tikru elemetu dejimas i sarasa");
//        Ks.ouf("%6d %7d %7d %7d \n", 0, 1, 2, 3);
//        for (int n : examCounts) {
//            addAnalysis(n);
//        }
//        Ks.oun("Idejimo metodo greitaveikos tyrimas baigtas.");
        // Čia gali būti ir kitokio tyrimo metodo (jūsų sugalvoto) iškvietimas.
    }

    public static void main(String[] args) {
        // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT"));
        new GreitaveikosTyrimas().analysisSelection();
    }
}
