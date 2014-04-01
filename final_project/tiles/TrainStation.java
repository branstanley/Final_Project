package final_project.tiles;

import final_project.player.Player;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TrainStation extends Property{
    public static TrainStation [] stations = new TrainStation[4];
    public int station_num;
    
    public TrainStation(String tile, String initials, int station_number){
        super(tile, initials, 200, 100);
        station_num = station_number;
    }

    public String perform_tile_action(Player in){
        int i = 0, output = 0;
        String outString = " " + in.get_player_name() + " pays " + get_owner().get_player_name() + " $";
        
        for(int j = 0; j < 4; ++j){
            if(j == station_num)
                continue;
            else if(this.get_owner() == stations[j].get_owner()){
                ++i;
            }
        }
        
        switch(i){
            case 0:
                output = 25;
                break;
            case 1:
                output = 50;
                break;
            case 2:
                output = 100;
                break;
            case 3:
                output = 200;
                break;
        }
        
        in.lose_money(output);
        get_owner().gain_money(output);
        outString += (output);
        
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
            
            middle.setLayout(new GridLayout(6,1));
            //middle.setBackground(Color.red);
            Title.setOpaque(true);
            
            middle.add(new JLabel("Rent                 $25", JLabel.CENTER));
            middle.add(new JLabel("If 2 are owned       $50", JLabel.CENTER));
            middle.add(new JLabel("If 3 are owned      $100", JLabel.CENTER));
            middle.add(new JLabel("If 4 are owned      $200", JLabel.CENTER));
            middle.add(new JLabel("Mortage Value      $100", JLabel.CENTER));
            
            repaint();
        }
        
    }
}
