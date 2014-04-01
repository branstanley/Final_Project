package final_project.tiles;
import final_project.player.Player;
import final_project.tiles.Base_Tile;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

abstract public class Property extends Base_Tile{
    private Player owner;
    private int purchase_price;
    private int mortage;
    
    
    public Property(String name, String initials, int cost, int mortage){
        super(name, initials);
        purchase_price = cost;
        this.mortage = mortage;
    }
    
    public Player get_owner(){
        return owner;
    }
    public int get_purchase_price(){
        return purchase_price;
    }
    public int get_mortage(){
        return mortage;
    }
    
    public String toString(){
        return get_name();
    }
    
    abstract public String perform_tile_action(Player in);
    
    @Override
    public String tile_action(Player in) {
        if(owner == null && purchase_price <= in.get_money()){
            int a = 1;
            JOptionPane jop = new JOptionPane("This property costs $" + purchase_price + " and you have $" + in.get_money() + "\nWould you like to purchase?", JOptionPane.PLAIN_MESSAGE, JOptionPane.YES_NO_OPTION);
     
            jop.setWantsInput(false);
            JDialog  out= jop.createDialog(null, "Purchase Property");
            out.setLocation(300, 300);
            out.setVisible(true);
            
            jop.setLocation(300, 300);
            jop.setVisible(true);
            
            if(jop.getValue() == 0){
                owner = in;
                in.make_purchase(purchase_price, this);
                details[1].setText(get_owner().get_player_name());
                validate();
                repaint();
                return in.get_player_name() + " has purchased " + get_name() + " for $" + purchase_price + ".\n";
            }
            else{
                return get_name() + " not purchased\n";
            }
        }
        else if(get_owner() == null){
            return "Property not owned.";
        }
        else{
            return perform_tile_action(in);
        }
    }
}
