package final_project.tiles;

import final_project.player.Player;
import javax.swing.JPanel;

public interface TileInterface {
    String tile_action(Player in);
    JPanel tile_details(); 
}
