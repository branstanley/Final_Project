package final_project.tiles;

import final_project.Game_Board;
import final_project.player.Player;

public class Chance extends Deck{
    public Chance(){
        super("Chance", "?");
    }
    

    @Override
    public String tile_action(Player in) {
        String stringOut = "Chance!\n";
        int rand = (int)Math.random() * 16;
        
        switch(draw_card()){
            case 0:
                stringOut += "Take a ride on the Reading Railroad If you pass go collect $200\n";
                //move to tile[5]
                Game_Board.change_position(in, 5, in.get_position());
                break;
            case 1:
                Game_Board.change_position(in, 0, in.get_position());
                stringOut += "Advance to Go (Collect $200)\n";
                break;
            case 2:
                in.gain_money(50);
                stringOut += "Bank pays you dividend of $50\n";
                break;
            case 3:
                stringOut += "Go Back 3 Spaces";
                Game_Board.change_position(in, in.get_position() - 3, in.get_position());
                break;
            case 4:
                in.lose_money(15);
                stringOut += "Pay poor tax of $15\n";
                break;
        }
        
        return stringOut;
    }
    
}
