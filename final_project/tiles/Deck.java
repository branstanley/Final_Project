
package final_project.tiles;

import java.util.ArrayList;

abstract public class Deck extends Event{
    ArrayList deck = new ArrayList();
    public Deck(String name, String initials){
        super(name, initials);
        shuffle_deck();
    }
    
    void shuffle_deck(){
        for(int i = 0; i < 5; ++i)
            deck.add(i);
        
        int x = 0, y = 0, temp;
        
        for(int i = 0; i < 100; ++i){
            x = (int)Math.random() * 5;
            y = (int)Math.random() * 5;
            
            temp = (int)deck.get(x);
            deck.set(x, deck.get(y));
            deck.set(y, temp);
        }
    }
    
    int draw_card(){
        if(deck.isEmpty())
            shuffle_deck();
        return (int)deck.remove(0);
    }
}
