package final_project.player;
import final_project.tiles.Property;
import java.util.ArrayList;
import final_project.tiles.*;
import java.awt.Color;

public class Player {
    private int money;
    private ArrayList <Property> property;
    private int player_number;
    private Color player_color;
    private String player_name;
    private int position;
    private Boolean jailed = false;
    
    public Player(String player, int play_num, Color c){
        player_name = player;
        player_number = play_num;
        player_color = c;
        property = new ArrayList();
        money = 200;
        position = 0;
    }
    
    //output of data
    public int get_player_number(){
        return player_number;
    }
    public Color get_player_color(){
        return player_color;
    }
    public String get_player_name(){
        return player_name;
    }
    public int get_money(){
        return money;
    }
    public Object [] get_property(){
        if(property.isEmpty())
            return null;
        return property.toArray();
    } 
    public int get_position(){
        return position;
    }
    public void change_position(int position){
        this.position = position;
    }
    public Boolean get_jail_status(){
        return jailed;
    }
    @Override
    public String toString(){
        return "Player " + player_number +": " + player_name;
    }
    
    //input of data
    public boolean make_purchase(int cost, Property in){
        if(money - cost >= 0){
            money -= cost;
            property.add(in);
            return true;
        }
        return false;
    }
    public int gain_money(int in){
        money += in;
        return money;
    }
    public int lose_money(int in){
        money -= in;
        return in;
    }
    public void set_jail_status(Boolean in){
        
    }
    
    //action
    public void mortage_property(){
        
    }
    public boolean move(int move){
        position += move;
        if(position >= 40){
            position -= 40;
            money += 200;
            return true;
        }
        return false;
    }
}
