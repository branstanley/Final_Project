package final_project.tiles;

import final_project.Game_Board;
import final_project.player.Player;
import final_project.tiles.Base_Tile;
import final_project.tiles.FreeParking;

public class CommunityChest extends Deck{
    
    public CommunityChest(){
        super("Community Chest", "CC");
    }

    @Override
    public String tile_action(Player in) {
        String stringOut = "Chance!\n";
        int rand = (int)Math.random() * 16;
        
        switch(draw_card()){
            case 0:
                in.gain_money(10);
                stringOut += "You have won second prize in a beauty contest – Collect $10\n";
                break;
            case 1:
                Game_Board.change_position(in, 0, in.get_position());
                stringOut += "Advance to Go (Collect $200)\n";
                break;
            case 2:
                in.lose_money(50);
                stringOut += "Doctor's fees – Pay $50\n";
                FreeParking.add_to_pool(50);
                break;
            case 3:
                in.lose_money(50);
                FreeParking.add_to_pool(50);
                Game_Board.change_position(in, in.get_position() - 3, in.get_position());
                break;
            case 4:
                in.gain_money(100);
                stringOut += "Holiday Fund matures - Receive $100\n";
                break;
        }
        
        return stringOut;
    }
    
    
}
