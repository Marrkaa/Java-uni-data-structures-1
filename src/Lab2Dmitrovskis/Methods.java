package Lab2Dmitrovskis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import studijosKTU.*;

/**
 * Class of methods which we use by interacting with the GUI
 * @author Core_Nvidia
 */
public class Methods {
    
    public ListKTUx<Player> allPlayers = new ListKTUx<>(new Player());
    public ListKTUx<Player> tallestPlayers = new ListKTUx<>(new Player());
    
    /**
     * Method that prints the base players from a text file
     * @param fileName data file name
     * @param textArea GUI text area element
     * @param table GUI table element
     * @return 
     */
    public String printBasePlayers(File fileName, JTextArea textArea, JTable table) {
        String errorCode = null;
        try {
            allPlayers.clear();
            BufferedReader fReader =  new BufferedReader(new FileReader(fileName));
            String line;

            textArea.append("Data from file <" + fileName.getName() + ">\n\n");
            
            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
            
            while ((line = fReader.readLine()) != null) {
                allPlayers.add(new Player(line));
            }
            baseTable(table);
            fReader.close();
        } catch (IOException e) {
                errorCode = "Failo " + fileName.getName() + " skaitymo klaida";
        }
        return errorCode;
    }
    
    /**
     * Calculates the average age of players
     * @return average age
     */
    private double averageAge(){
        double sum = 0;
        for(Player p : allPlayers){
            sum = sum + p.getAge();
        }
        double averageAge = sum / allPlayers.size();
        return averageAge;
    }
    
    /**
     * Calculates the average height of players
     * @return average height
     */
    private double averageHeight(){
        double sum = 0;
        for(Player p : allPlayers){
            sum = sum + p.getHeight();
        }
        double averageHeight = sum / allPlayers.size();
        return averageHeight;
    }
    
    /**
     * A method which prints the averages
     * @param textArea GUI text area element
     */
    public void printAverages(JTextArea textArea){
        double averageAge = averageAge();
        double averageHeight = averageHeight();
        textArea.append("\n");
        textArea.append("Average player age is " + averageAge + "\n");
        textArea.append("Average player height is " + String.format("%.2f", averageHeight) + "\n\n");
    }
    
    /**
     * A method which formats the list of tallest players
     */
    public void formattingTallest(){
        int index = allPlayers.size();
        double averageHeight = averageHeight();
        for(int i = 0; i < allPlayers.size(); i++){
            if(allPlayers.get(i).getHeight() > averageHeight){
                tallestPlayers.add(allPlayers.get(i));
            }
        }
    }
    
    /**
     * Sets the base table
     * @param table GUI table element
     */
    public void baseTable(JTable table){
        DefaultTableModel tableModel = (DefaultTableModel)table.getModel(); 
        for(Player player: allPlayers){
            tableModel.addRow(new Object[]{player.getName(), player.getSurname(), player.getAge(), player.getHeight(), player.getAvgAssists(), player.getAvgPoints()});
        }
    }
    
    /**
     * Updates the GUI table
     * @param table GUI table element
     */
    public void updateTable(JTable table){
        DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
        tableModel.getDataVector().removeAllElements();
        tableModel.fireTableDataChanged();
        
        for(Player player: tallestPlayers){
            tableModel.addRow(new Object[]{player.getName(), player.getSurname(), player.getAge(), player.getHeight(), player.getAvgAssists(), player.getAvgPoints()});
        }
    }
    
    /**
     * Sorts the players with bubbleSort and comparator
     */
    public void sortPlayersByAgeAndName(){
        tallestPlayers.sortBuble(Player.byAgeAndName);
    }
    
    /**
     * A method which removes certain elements from the list
     * @param age selected age
     */
    public void removePlayers(int age){
        int index = -1;
        for(Player player: tallestPlayers){
            index++;
            if(player.getAge() > age){
                tallestPlayers.remove(index);
                index--;
            }
        }
    }
}
