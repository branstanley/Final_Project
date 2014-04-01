package final_project.tiles;

import final_project.player.Player;
import final_project.tiles.Base_Tile;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FreeParking extends Special_Tiles{
    static private int pool = 0;
    
    public FreeParking(){
        super("Free Parking", "FP");
    }
    
    static public void add_to_pool(int in){
        pool += in;
    }
    
    @Override
    public String tile_action(Player in) {
        String outString = in.get_player_name() + " gains $" + pool + "\n";
        in.gain_money(pool);
        pool = 0;
        return outString;
    }
    @Override
    public JPanel tile_details(){
        return new Tile();
    }
    
    class Tile extends Tile_Details{
        
        public Tile(){
            super(get_name());
            
            middle.setLayout(new GridLayout(6,1));
            Title.setOpaque(true);
            
            middle.add(new JLabel("Stored amount                $"+pool, JLabel.CENTER));
            
            repaint();
        }
        
    }
}
