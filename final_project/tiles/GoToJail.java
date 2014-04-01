package final_project.tiles;

import final_project.Game_Board;
import final_project.player.Player;
import final_project.tiles.Base_Tile;

public class GoToJail extends Special_Tiles{
    public GoToJail(){
        super("Go To Jail","GTJ");
    }
    
    @Override
    public String tile_action(Player in) {
        Jail.jail.into_jail(in);
        return in.get_player_name() + "has been sent to Jail, and can not collect $200 for passing Go.";
    }
}
