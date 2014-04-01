package final_project.tiles;

import final_project.Game_Board;
import final_project.player.Player;
import javax.swing.*;
import java.awt.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Residence extends Property{
    private int [] rents;
    private int house_cost;
    private int num_houses;
    private int group_number;
    Color background, foreground;
    public static Residence [][] property_grouping = new Residence[8][];
    
    public Residence(String name, String initials, Color background_color, Color foreground_color, int price,int mortage, int house_cost, int group_number, int [] in_rents){
        super(name, initials, price, mortage);
        rents = in_rents;
        this.group_number = group_number;
        this.house_cost = house_cost;
        
        background = background_color;
        foreground = foreground_color;
        
        num_houses = 0;
        
        details[0].setForeground(foreground_color);
        details[0].setBackground(background_color);
        
        if(get_owner() != null)
            details[1].setText(get_owner().get_player_name());
        
        details[2].setText("$"+rents[num_houses]);
        details[3].setText("$"+get_purchase_price());
        
        validate();
        repaint();
    }
    @Override
    public JPanel tile_details(){
        return new Deed();
    }

    private Boolean check_group(){
        if(property_grouping[group_number].length == 3){
            if(property_grouping[group_number][0] == property_grouping[group_number][1] && property_grouping[group_number][0] == property_grouping[group_number][2])
                return true;
        }
        else{
            if(property_grouping[group_number][0] == property_grouping[group_number][1])
                return true;
        }
        return false;
    }
    
    public String perform_tile_action(Player in) {
        /*
         * What happens when this tile is owned
         */
        if(get_owner() == null || get_owner() == in)
            return "";
        String output = "" + in.get_player_name() + " pays " + get_owner().get_player_name() + " $";
        if(num_houses == 0){
            if(check_group()){
                in.lose_money((rents[0]*2));
                get_owner().gain_money(rents[0]*2);
                output += (rents[0]*2);
            }
            else{
                in.lose_money((rents[0]));
                get_owner().gain_money(rents[0]);
                output += (rents[0]*2);
            }
        }
        else{
            in.lose_money((rents[num_houses]));
            get_owner().gain_money(rents[num_houses]);
            output += (rents[num_houses]);
        }
        return output + "\n";
    }
    class Deed extends Tile_Details{
        JTextArea rent_details;
        public Deed(){
            super(get_name());
            
            middle.setLayout(new GridLayout(6,1));
            //middle.setBackground(Color.red);
            Title.setOpaque(true);
            Title.setBackground(background);
            Title.setForeground(foreground);
            
            middle.add(new JLabel("Rent                $"+rents[0], JLabel.CENTER));
            middle.add(new JLabel("With 1 House                         $"+rents[1], JLabel.CENTER));
            middle.add(new JLabel("With 2 Houses                        $"+rents[2], JLabel.CENTER));
            middle.add(new JLabel("With 3 House2                        $"+rents[3], JLabel.CENTER));
            middle.add(new JLabel("With 4 House2                        $"+rents[4], JLabel.CENTER));
            middle.add(new JLabel("With Hotel                                $"+rents[5], JLabel.CENTER));
            
            bottom.setLayout(new GridLayout(3,1));
            bottom.add(new JLabel("Mortgage Value $"+get_mortage(), JLabel.CENTER));
            bottom.add(new JLabel("House Cost $"+house_cost+"/house", JLabel.CENTER));
            bottom.add(new JLabel("Hotels $"+house_cost+" plus 4 houses", JLabel.CENTER));
            
            repaint();
        }
        
    }
}
