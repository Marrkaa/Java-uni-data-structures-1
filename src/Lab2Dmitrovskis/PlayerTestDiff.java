package Lab2Dmitrovskis;

import java.util.Iterator;
import studijosKTU.*;

/**
 * Class which tests the formatting of players
 * @author Core_Nvidia
 */
public class PlayerTestDiff {
    public ListKTUx<Player> players = new ListKTUx<>(new Player());
    private static final Player basePlayer = new Player();

    /**
     * A method which formats the players by age
     * @param age selected age
     * @return formatted list
     */
    public ListKTUx<Player> formatByAge(int age) {
        ListKTUx<Player> temp = new ListKTUx(basePlayer);
        for (Player p : players) {
            if (p.getAge() < age) {
                temp.add(p);
            }
        }
        return temp;
    }

    /**
     * A method which formats the players by max height
     * @return formatted list
     */
    public ListKTUx<Player> formatByMaxHeight() {
        ListKTUx<Player> tallestPlayers = new ListKTUx(basePlayer);
        double maxHeight = 0;
        for (Player a : players) {
            double height = a.getHeight();
            if (height >= maxHeight) {
                if (height > maxHeight) {
                    tallestPlayers.clear();
                    maxHeight = height;
                }
                tallestPlayers.add(a);
            }
        }
        return tallestPlayers;
    }

    /**
     * A method which implements iteration and formats the list by the selected code
     * @param code start of name
     * @return formatted list
     */
    public ListKTUx<Player> formatByName(String code) {
        ListKTUx<Player> temp = new ListKTUx(basePlayer);
        for (Iterator <Player> it = players.iterator(); it.hasNext(); ) {
            Player p = it.next() ;			
            String fullName = p.getName()+ " " + p.getSurname();
            if (fullName.startsWith(code)) {
                temp.add(p);
            }
        }
        return temp;
    }
}
