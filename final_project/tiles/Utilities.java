package final_project.tiles;

import final_project.Game_Board;
import final_project.player.Player;
import final_project.tiles.Base_Tile;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Utilities extends Property{
    public static Utilities [] ut = new Utilities[2];
    
    public Utilities(String name, String initials){
        super(name, initials, 150, 75);
    }
    
    public String perform_tile_action(Player in) {
        int cost = 0;
        
        if(ut[0].get_owner() == ut[1].get_owner()){
            cost = Game_Board.get_recent_move_roll() * 10;
        }
        else{
            cost = Game_Board.get_recent_move_roll() * 4;
        }
        String outString = " " + in.get_player_name() + " pays " + get_owner().get_player_name() + " $" + cost;
        
        in.lose_money(cost);
        get_owner().gain_money(cost);
        
        Game_Board.get_recent_move_roll();
        return outString;
    }
    @Override
    public JPanel tile_details(){
        return new Deed();
    }
    
    class Deed extends Tile_Details{
        JTextArea rent_details;
        public Deed(){
            super(get_name());
            
            middle.setLayout(new GridLayout(2,1));
            //middle.setBackground(Color.red);
            Title.setOpaque(true);
            
            middle.add(new JTextArea("If one \"Utility\" is owned, rent is 4 times the amount \nshown on the dice."){
                public JTextArea construct(){
                    setLineWrap(true);
                    setBorder(new EmptyBorder(10,20,10,20));
                    return this;
                }
            }.construct());
            middle.add(new JTextArea("If both \"Utilities\" are owned, rent is 10 times the \namount shown on the dice."){
                public JTextArea construct(){
                    setLineWrap(true);
                    setBorder(new EmptyBorder(10,20,10,20));
                    return this;
                }
            }.construct());
            
            repaint();
        }
        
    }
}
