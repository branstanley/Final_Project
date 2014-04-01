package final_project.tiles;

import final_project.Game_Board;
import final_project.player.Player;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Jail extends Special_Tiles{
    private int [] escape_attempts = new int[Game_Board.number_of_players];
    
    static Jail jail = null;
    
    public Jail(){
        super("Jail","J");
        jail = this;
        for(int i = 0; i < Game_Board.number_of_players; ++i)
            escape_attempts[i] = 0;
    }
    
    @Override
    void construct_right(JPanel in){
        right_labels = new JLabel[Game_Board.number_of_players];
        in.setLayout(new GridLayout(Game_Board.number_of_players, 1));
        for(int i = 0; i < Game_Board.number_of_players; ++i){
            right_labels[i] = new JLabel(" ");
            right_labels[i].setOpaque(true);
            right_labels[i].setBackground(Color.white);
            right_labels[i].setBorder(BorderFactory.createLineBorder(Color.black));
            in.add(right_labels[i]);
        }
    }
    public void into_jail(Player in){
        Game_Board.change_position(in, 10, in.get_position());
        player_tokens[in.get_player_number()].setBackground(Color.white);
        right_labels[in.get_player_number()].setBackground(in.get_player_color());
        in.set_jail_status(true);
    }
    public void leave_jail(Player in){
        player_tokens[in.get_player_number()].setBackground(in.get_player_color());
        right_labels[in.get_player_number()].setBackground(Color.white);
        in.set_jail_status(false);
    }
    public Boolean attempt_escape(Player in, int roll1, int roll2){
        int result = 0;
        
        if(++escape_attempts[in.get_player_number()] <= 3){
            //can still roll
            result = JOptionPane.showOptionDialog(
                               null                       // Center in window.
                             , "What action will you take?"        // Message
                             , "In Jail"               // Title in titlebar
                             , JOptionPane.YES_NO_OPTION  // Option type
                             , JOptionPane.PLAIN_MESSAGE  // messageType
                             , null                       // Icon (none)
                             , new String[]{"Roll", "Use Card", "Pay $50"}                   // Button text as above.
                             , "Roll"    // Default button's label
                           );
        }
        else{
            //out of rolls, must pay $50 or use get out of jail free card
            
        }
        switch(result){
            case 0:
                if(roll1 == roll2){
                    leave_jail(in);
                    return true;
                }
                break;
            case 1:
                throw new UnsupportedOperationException("Not supported yet.");
                //break;
            case 2:
                in.lose_money(50);
                FreeParking.add_to_pool(50);
                return true;
        }
        return false;
    }
    
    @Override
    public String tile_action(Player in) {
        
        return "";
    }
}
