package final_project.tiles;

import final_project.player.Player;

public class LuxuryTax extends Event{
    public LuxuryTax(){
        super("Luxury Tax", "LT");
    }
    
    @Override
    public String tile_action(Player in) {
        in.lose_money(75);
        FreeParking.add_to_pool(75);
        return in.get_player_name() + " pays $75 in Luxury Tax.";
    }
}
