package final_project.tiles;
import final_project.Game_Board;
import java.awt.*;
import javax.swing.*;
import final_project.player.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public abstract class Base_Tile extends JPanel implements TileInterface{
    String tile_name;
    JLabel display;
    String initials;
    
    JPanel [] panels;
    JLabel [] player_tokens;
    JLabel [] right_labels;
    JLabel [] details;
    
    public Base_Tile(String name, String initials){
        super(new GridBagLayout());
        tile_name = name;
        this.initials = initials;
        
        init_tile();
        
        construct_left();
        construct_middle(panels[1]);
        construct_right(panels[2]);
        
        add_tiles();
    }
    
    private void init_tile(){
        setBackground(Color.white);
        //setBorder(new EmptyBorder(-1,-1,-1,-1));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setPreferredSize(new Dimension(75,75));
        
        panels = new JPanel[3];
        panels[0] = new JPanel(new GridLayout(Game_Board.number_of_players, 1));
        panels[1] = new JPanel(new GridLayout(4, 1));
        panels[2] = new JPanel(new GridLayout(4, 1));
        
    }
    private void add_tiles(){
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        
        add(panels[0], c);
        c.weightx = 3;
        add(panels[1], c);
        c.weightx = 1;
        add(panels[2], c);
    }
    private void construct_left(){
        player_tokens = new JLabel[Game_Board.number_of_players];
        for(int i = 0; i < Game_Board.number_of_players; ++i){
            player_tokens[i] = new JLabel(" ");
            player_tokens[i].setOpaque(true);
            player_tokens[i].setBackground(Color.white);
            player_tokens[i].setBorder(BorderFactory.createLineBorder(Color.black));
            panels[0].add(player_tokens[i]);
        }
    }
    private void construct_middle(JPanel in){
        
        details = new JLabel[4];
        details[0] = new JLabel(initials, JLabel.CENTER);
        details[0].setOpaque(true);
        details[0].setBackground(Color.white);
        details[0].setBorder(BorderFactory.createLineBorder(Color.black));
        
        details[1] = new JLabel("        ", JLabel.CENTER); //owner
        details[1].setOpaque(true);
        details[1].setPreferredSize(new Dimension(10, 10));
        details[1].setMaximumSize(new Dimension(10, 10));
        details[1].setBackground(Color.white);
        details[1].setBorder(BorderFactory.createLineBorder(Color.black));
        
        details[2] = new JLabel(" ", JLabel.CENTER);
        details[2].setOpaque(true);
        details[2].setBackground(Color.white);
        details[2].setBorder(BorderFactory.createLineBorder(Color.black));
        
        details[3] = new JLabel(" ", JLabel.CENTER);
        details[3].setOpaque(true);
        details[3].setBackground(Color.white);
        details[3].setBorder(BorderFactory.createLineBorder(Color.black));
        
        panels[1].add(details[0]);
        panels[1].add(details[1]);
        panels[1].add(details[2]);
        panels[1].add(details[3]);
        
        validate();
        repaint();
        //c.weightx = 3;
    }
    void construct_right(JPanel in){
        right_labels = new JLabel[4];
        for(int i = 0; i < 4; ++i){
            right_labels[i] = new JLabel(" ");
            right_labels[i].setOpaque(true);
            right_labels[i].setBackground(Color.white);
            right_labels[i].setBorder(BorderFactory.createLineBorder(Color.black));
            in.add(right_labels[i]);
        }
    }

    @Override
    public JPanel tile_details(){
        return new Tile_Details(tile_name);
    }
    public String get_name(){
        return tile_name;
    }
    
    public void player_in(Color c, int playNum){
        player_tokens[playNum].setBackground(c);
    }
    public void player_out(int playNum){
        player_tokens[playNum].setBackground(Color.white);
    }
    
    class Tile_Details extends JPanel{
        public JLabel Title;
        public JPanel middle, bottom;

        public Tile_Details(String title){
            super(new GridBagLayout());
            
            setBackground(Color.white);
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 1;
            c.weighty = 1;
            c.gridheight = 1;
            c.gridwidth = GridBagConstraints.REMAINDER;

            Title = new JLabel(title, JLabel.CENTER);
            Title.setBackground(Color.white);
            add(Title,c);

            middle = new JPanel(new GridLayout(1,1));
            middle.setBackground(Color.white);
            c.weighty = 10;
            add(middle, c);

            bottom = new JPanel(new GridLayout(1,1));
            bottom.setBackground(Color.white);
            c.weighty = 3;
            add(bottom, c);
        }
    }
}
