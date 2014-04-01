package final_project.tiles;

import final_project.player.Player;
import final_project.tiles.Base_Tile;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class IncomeTax extends Event{
    public IncomeTax(){
        super("Income Tax", "IT");
    }
    
    @Override
    public String tile_action(Player in) {
        int percent = (int)(in.get_money()*0.1);
        Object [] options = {"$200", "$"+percent};
        
        
        JOptionPane jop = new JOptionPane("You must pay income tax:", JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null,options, options[0]);

        jop.setWantsInput(false);
        JDialog  out= jop.createDialog(null, "Purchase Property");
        out.setLocation(300, 300);
        out.setVisible(true);

        jop.setLocation(300, 300);
        jop.setVisible(true);
        
        
        if(jop.getValue() == 0){
            in.lose_money(200);
            FreeParking.add_to_pool(200);
            return in.get_player_name() + " pays $200 in income tax.";
        }
        else if(jop.getValue() == 1){
            in.lose_money(percent);
            FreeParking.add_to_pool(percent);
            return in.get_player_name() + " pays $"+percent+" in income tax.";
        }
        else
            return tile_action(in);
    }
}
