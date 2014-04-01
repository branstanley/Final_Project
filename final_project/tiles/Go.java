package final_project.tiles;

import final_project.player.Player;
import final_project.tiles.Base_Tile;

public class Go extends Special_Tiles{
    public Go(){
        super("Go", "Go");
    }

    @Override
    public String tile_action(Player in) {
        in.gain_money(200);
        return "Landed on Go!  Collect $200.";
    }
    
}
