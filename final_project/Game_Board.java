package final_project;

import final_project.tiles.TrainStation;
import final_project.tiles.LuxuryTax;
import final_project.tiles.Utilities;
import final_project.tiles.CommunityChest;
import final_project.tiles.Residence;
import final_project.tiles.Chance;
import final_project.tiles.IncomeTax;
import final_project.tiles.Go;
import final_project.tiles.FreeParking;
import final_project.tiles.Jail;
import final_project.tiles.GoToJail;
import final_project.player.Player;
import final_project.tiles.Base_Tile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Game_Board extends JFrame implements MouseListener{
    public static int number_of_players = 4;
    static private Base_Tile [] tiles;
    static private Player [] players;
    static private int current_player = 0;
    static private int most_recent_move_roll = 0;
    static private int rollCounter = 0;
    
    private JPanel [] parts; //{ { 0, top}, {1, left}, {2, right}, {3,bottom}, {4, center}}
    private Center_Panel cp;
    private Base_Tile focus_tile;
    
    public Game_Board(){
        super();
        this.setPreferredSize(new Dimension(900,900));
        
        JPanel t = new JPanel();
        t.setBackground(Color.red);
        
        add(new new_game());
        //start_game();
        //setPreferredSize(new Dimension(800,600));
        //setExtendedState(Frame.MAXIMIZED_BOTH); 
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pack();
        setVisible(true);
    }
    
    public void start_game(Player [] p, int numPlayers){
        players = p;
        number_of_players = numPlayers;
        
        getContentPane().removeAll();
        setLayout(new BorderLayout());
        tiles = new Base_Tile[40];
        
        parts = new JPanel[5];
        parts[0] = new JPanel(new GridLayout(1,11));
        parts[1] = new JPanel(new GridLayout(9,1));
        parts[2] = new JPanel(new GridLayout(9,1));
        parts[3] = new JPanel(new GridLayout(1,11));
        cp = new Center_Panel();
        parts[4] = cp;
        
        build_tiles();
        
        
        Container temp = this.getContentPane();
        
        this.setContentPane(temp);
        getContentPane().repaint();
        
        add(parts[0], BorderLayout.PAGE_START);
        add(parts[1], BorderLayout.LINE_START);
        add(parts[2], BorderLayout.LINE_END);
        add(parts[3], BorderLayout.PAGE_END);
        add(parts[4], BorderLayout.CENTER);
        
        for(int i = 0; i < number_of_players;++i)
            tiles[0].player_in(players[i].get_player_color(), players[i].get_player_number());
        
        cp.get_left_panel().add(focus_tile.tile_details());
        
        
        validate();
        repaint();
        
    }
    public void build_tiles(){
        tiles[20] = new FreeParking();
        parts[0].add(tiles[20]);
        tiles[21] = new Residence("Kentucky Avenue", "KA", Color.decode("#f9383c"), Color.black, 220, 110, 150, 4, new int[]{18, 90, 250, 700, 875, 1050 });
        parts[0].add(tiles[21]);
        tiles[22] = new Chance();
        parts[0].add(tiles[22]);
        tiles[23] = new Residence("Indiana Avenue", "IA", Color.decode("#f9383c"), Color.black, 220, 110, 150, 4, new int[]{18, 90, 250, 700, 875, 1050 });
        parts[0].add(tiles[23]);
        tiles[24] = new Residence("Illinois Avenue", "IA", Color.decode("#f9383c"), Color.black, 240, 120, 150, 4, new int[]{20, 100, 300, 750, 925, 1100 });
        parts[0].add(tiles[24]);
        tiles[25] = new TrainStation("B&O Railroad", "BOR", 2);
        TrainStation.stations[2] = (TrainStation)tiles[25];
        parts[0].add(tiles[25]);
        tiles[26] = new Residence("Atlantic Avenue", "AA", Color.decode("#f9ec87"), Color.black, 260, 130, 150, 5, new int[]{22, 110, 330, 800, 975, 1150 });
        parts[0].add(tiles[26]);
        tiles[27] = new Residence("Ventnor Avenue", "VA", Color.decode("#f9ec87"), Color.black, 260, 130, 150, 5, new int[]{22, 110, 330, 800, 975, 1150 });
        parts[0].add(tiles[27]);
        tiles[28] = new Utilities("Water Works", "WW");
        Utilities.ut[1] = (Utilities)tiles[28];
        parts[0].add(tiles[28]);
        tiles[29] = new Residence("Marvin Gardens", "MG", Color.decode("#f9ec87"), Color.black, 280, 140, 150, 5, new int[]{24, 120, 360, 850, 1025, 1200 });
        parts[0].add(tiles[29]);
        tiles[30] = new GoToJail();
        parts[0].add(tiles[30]);
        
        Residence.property_grouping[4] = new Residence[3];
        Residence.property_grouping[4][0] = (Residence)tiles[21];
        Residence.property_grouping[4][1] = (Residence)tiles[23];
        Residence.property_grouping[4][2] = (Residence)tiles[24];
        
        Residence.property_grouping[5] = new Residence[3];
        Residence.property_grouping[5][0] = (Residence)tiles[26];
        Residence.property_grouping[5][1] = (Residence)tiles[27];
        Residence.property_grouping[5][2] = (Residence)tiles[29];
        
        //
        tiles[19] = new Residence("New York Avenue", "NYA", Color.decode("#fa924c"), Color.black, 200, 100, 100, 3, new int[]{16, 80, 220, 600, 800, 1000 });
        parts[1].add(tiles[19]);
        tiles[18] = new Residence("Tennessee Avenue", "TA", Color.decode("#fa924c"), Color.black, 180, 90, 100, 3, new int[]{14, 70, 200, 550, 750, 950 });
        parts[1].add(tiles[18]);
        tiles[17] = new CommunityChest();
        parts[1].add(tiles[17]);
        tiles[16] = new Residence("St. James Place", "SJP", Color.decode("#fa924c"), Color.black, 180, 90, 100, 3, new int[]{14, 70, 200, 550, 750, 950 });
        parts[1].add(tiles[16]);
        tiles[15] = new TrainStation("Pennsylvania Railroad", "PR", 1);
        TrainStation.stations[1] = (TrainStation)tiles[15];
        parts[1].add(tiles[15]);
        tiles[14] = new Residence("Virginia Avenue", "VA", Color.decode("#a63668"), Color.white, 160, 80, 100, 2, new int[]{12, 60, 180, 500, 700, 900 });
        parts[1].add(tiles[14]);
        tiles[13] = new Residence("States Avenue", "SA", Color.decode("#a63668"), Color.white, 140, 70, 100, 2, new int[]{10, 50, 150, 450, 625, 750 });
        parts[1].add(tiles[13]);
        tiles[12] = new Utilities("Electric Company", "EC");
        Utilities.ut[0] = (Utilities)tiles[12];
        parts[1].add(tiles[12]);
        tiles[11] = new Residence("St. Charles Place", "SCP", Color.decode("#a63668"), Color.white, 140, 70, 100, 2, new int[]{10, 50, 150, 450, 625, 750 });
        parts[1].add(tiles[11]);
        
        Residence.property_grouping[2] = new Residence[3];
        Residence.property_grouping[2][0] = (Residence)tiles[11];
        Residence.property_grouping[2][1] = (Residence)tiles[13];
        Residence.property_grouping[2][2] = (Residence)tiles[14];
        
        Residence.property_grouping[3] = new Residence[3];
        Residence.property_grouping[3][0] = (Residence)tiles[16];
        Residence.property_grouping[3][1] = (Residence)tiles[18];
        Residence.property_grouping[3][2] = (Residence)tiles[19];
        
        //
        tiles[31] = new Residence("Pacific Avenue", "PA", Color.decode("#4e8c6d"), Color.white, 300, 150, 200, 6, new int[]{26, 130, 390, 900, 1100, 1275 });
        parts[2].add(tiles[31]);
        tiles[32] = new Residence("North Carolina Avenue", "NCA", Color.decode("#4e8c6d"), Color.white, 300, 150, 200, 6, new int[]{26, 130, 390, 900, 1100, 1275 });
        parts[2].add(tiles[32]);
        tiles[33] = new CommunityChest();
        parts[2].add(tiles[33]);
        tiles[34] = new Residence("North Carolina Avenue", "NCA", Color.decode("#4e8c6d"), Color.white, 320, 160, 200, 6, new int[]{28, 150, 450, 1000, 1200, 1400 });
        parts[2].add(tiles[34]);
        tiles[35] = new TrainStation("Short Line", "SL", 3);
        TrainStation.stations[3] = (TrainStation)tiles[35];
        parts[2].add(tiles[35]);
        tiles[36] = new Chance();
        parts[2].add(tiles[36]);
        tiles[37] = new Residence("Park Place", "PP", Color.decode("#384a8f"), Color.white, 350, 175, 200, 7, new int[]{35, 175, 500, 1100, 1300, 1500 });
        parts[2].add(tiles[37]);
        tiles[38] = new LuxuryTax();
        parts[2].add(tiles[38]);
        tiles[39] = new Residence("Boardwalk", "B", Color.decode("#384a8f"), Color.white, 400, 200, 200, 7, new int[]{50, 200, 600, 1400, 1700, 2000 });
        parts[2].add(tiles[39]);
        Residence.property_grouping[6] = new Residence[3];
        Residence.property_grouping[6][0] = (Residence)tiles[31];
        Residence.property_grouping[6][1] = (Residence)tiles[32];
        Residence.property_grouping[6][2] = (Residence)tiles[34];
        
        Residence.property_grouping[7] = new Residence[3];
        Residence.property_grouping[7][0] = (Residence)tiles[37];
        Residence.property_grouping[7][1] = (Residence)tiles[39];
        
        //
        tiles[10] = new Jail();
        parts[3].add(tiles[10]);
        tiles[9] = new Residence("Connecticut Avenue", "CA", Color.decode("#bacaf1"), Color.black, 120, 60, 50, 1, new int[]{6, 30, 90, 270, 400, 550 });
        parts[3].add(tiles[9]);
        tiles[8] = new Residence("Vermont Avenue", "VA", Color.decode("#bacaf1"), Color.black, 100, 50, 50, 1, new int[]{6, 30, 90, 270, 400, 550 });
        parts[3].add(tiles[8]);
        tiles[7] = new Chance();
        parts[3].add(tiles[7]);
        tiles[6] = new Residence("Oriental Avenue", "OA", Color.decode("#bacaf1"), Color.black, 100, 50, 50, 1, new int[]{6, 30, 90, 270, 400, 550 });
        parts[3].add(tiles[6]);
        tiles[5] = new TrainStation("Reading Railroad", "RR", 0);
        TrainStation.stations[0] = (TrainStation)tiles[5];
        parts[3].add(tiles[5]);
        tiles[4] = new IncomeTax();
        parts[3].add(tiles[4]);
        tiles[3] = new Residence("Baltic Avenue", "BA", Color.decode("#4f3b76"), Color.white, 60, 30, 50, 0, new int[]{4, 20, 60, 180, 320, 450 });
        parts[3].add(tiles[3]);
        tiles[2] = new CommunityChest();
        parts[3].add(tiles[2]);
        tiles[1] = new Residence("Mediterranean Avenue", "MA", Color.decode("#4f3b76"), Color.white, 60, 30, 50, 0, new int[]{2, 10, 30, 90, 160, 250 });
        parts[3].add(tiles[1]);
        tiles[0] = new Go();
        parts[3].add(tiles[0]);
        
        Residence.property_grouping[0] = new Residence[2];
        Residence.property_grouping[0][0] = (Residence)tiles[1];
        Residence.property_grouping[0][1] = (Residence)tiles[3];
        
        Residence.property_grouping[1] = new Residence[3];
        Residence.property_grouping[1][0] = (Residence)tiles[6];
        Residence.property_grouping[1][1] = (Residence)tiles[8];
        Residence.property_grouping[1][2] = (Residence)tiles[9];
        
        focus_tile = tiles[0];
        for(int i = 0; i < 40; ++i)
            tiles[i].addMouseListener(this);
        
        validate();
        repaint();
    }
    
    static public void change_position(Player in, int new_spot, int old_spot){
        tiles[old_spot].player_out(in.get_player_number()); //player leaves tile
        players[current_player].change_position(new_spot);//update player tile number
        tiles[new_spot].player_in(in.get_player_color(), in.get_player_number()); //player enters tile
        //tiles[new_spot].tile_action(in);
    }
    protected boolean roll(){
        int r1 = (int)Math.floor(Math.random()*6) +1;
        int r2 = (int)Math.floor(Math.random()*6) +1;
        
        if(r1 == r2){
            if(rollCounter == 2){
                //player goes to jail
                ((Jail)tiles[10]).into_jail(players[current_player]);
                return false;
            }
        }
        
        if(players[current_player].get_jail_status()){
            //player is in jail
            if(!((Jail)tiles[10]).attempt_escape(players[current_player], r1, r2)){
                //fail escape
                cp.get_status_area().append("Failed to escape jail.\n");
                cp.get_status_area().append("Rolled: " + r1 + " + " + r2 + " = " +(r1+r2) + "\n");
                return false;
            }
        }
        
        //normal roll
        most_recent_move_roll = r1 + r2;

        change_position(players[current_player], players[current_player].get_position() + (r1+r2), players[current_player].get_position());

        focus_tile = tiles[players[current_player].get_position()];

        cp.get_left_panel().removeAll();
        cp.get_left_panel().add(new JScrollPane(focus_tile.tile_details()));
        validate();
        repaint();

        //roll status output
        cp.get_status_area().append("Rolled: " + r1 + " + " + r2 + " = " +(r1+r2) + "\n");
        cp.get_status_area().append(players[current_player].get_player_name() + " has moved to " + tiles[players[current_player].get_position()].get_name() + "\n");
        //tile action
        cp.get_status_area().append(tiles[players[current_player].get_position()].tile_action(players[current_player]));
        
        if(r1 == r2){
            rollCounter++;
            return true;
        }
        return false;
    }
    public void next_player(){
        if(++current_player >= number_of_players){
            current_player = 0;
        }
        rollCounter = 0;
    }
    static public int get_recent_move_roll(){
        return most_recent_move_roll;
    }
    
    @Override
    public void mouseEntered(MouseEvent evt){
        if(evt.getSource() instanceof Residence){
            cp.get_left_panel().removeAll();
            cp.get_left_panel().add(new JScrollPane(((Residence)evt.getSource()).tile_details()));
            
        }
        else if(evt.getSource() instanceof Base_Tile){
            cp.get_left_panel().removeAll();
            cp.get_left_panel().add(((Base_Tile)evt.getSource()).tile_details());
        }
        validate();
        repaint();
        
    }
    @Override
    public void mouseExited(MouseEvent evt){
            cp.get_left_panel().removeAll();
            cp.get_left_panel().add(new JScrollPane(focus_tile.tile_details()));
            validate();
            repaint();
    }
    @Override
    public void mouseClicked(MouseEvent evt) {
        
    }
    @Override
    public void mousePressed(MouseEvent e) {
        
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        
    }
    
    public static void main(String[] args) {
        
        Game_Board gb = new Game_Board();
    }
   
    private class Center_Panel extends JPanel implements ActionListener{
        private JLabel banner;
        private JPanel [] panes;
        private JButton roll, end_turn;
        private JTextArea status;
        
        public Center_Panel(){
            setLayout(new GridBagLayout());
            
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 1;
            c.weighty = 1;
            c.gridheight = 1;
            c.gridwidth = GridBagConstraints.REMAINDER;
            
            banner = new JLabel("Monopoly", JLabel.CENTER);
            banner.setFont(new Font("Serif", Font.BOLD, 30));
            add(banner, c);
            
            panes = new JPanel[2];
            panes[0] = new JPanel(new GridLayout(1,1));
            panes[0].setBorder(new EmptyBorder(20,20,20,20));
            
            panes[0].setMaximumSize(new Dimension(300, 300));
            panes[0].setMinimumSize(new Dimension(300, 300));
            panes[0].setPreferredSize(new Dimension(300, 300));
            
            panes[1] = new JPanel(new GridLayout(1,1));
            panes[1].setBorder(new EmptyBorder(20,20,20,20));
            panes[1].setMaximumSize(new Dimension(300, 300));
            panes[1].setMinimumSize(new Dimension(300, 300));
            panes[1].setPreferredSize(new Dimension(300, 300));
            status = new JTextArea();
            status.setEditable(false);
            JScrollPane scroller = new JScrollPane(status);
            //scroller.setMargin(new Insets(1,1,1,1));
            panes[1].add(scroller);
            
            
            c.gridwidth = 1;
            c.gridheight = 10;
            c.gridwidth = 2;
            c.weighty = 10;
            c.gridwidth = GridBagConstraints.REMAINDER;
            add(new JPanel(){
                public JPanel construction(){
                    setLayout(new GridLayout(1,2));
                    add(panes[0]);
                    add(panes[1]);
                    return this;
                }
            }.construction(),c);
            
            c.weighty = 1;
            c.gridheight = 1;
            roll = new JButton("Roll");
            roll.addActionListener(this);
            end_turn = new JButton("End Turn");
            end_turn.setEnabled(false);
            end_turn.addActionListener(this);
            
            c.gridwidth = 1;
            add(roll, c);
            c.gridwidth = GridBagConstraints.REMAINDER;
            add(end_turn, c);
            
            c.weighty = 20;
            c.gridheight = 10;
            c.gridwidth = GridBagConstraints.REMAINDER;
            add(new player_display(), c);
            
            
        }
        public JPanel get_left_panel(){
            return panes[0];
        }
        public JTextArea get_status_area(){
            return status;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == roll){
                if(roll()){ //doubles
                    status.append("Doubles!  Roll again.");
                }
                else{
                    roll.setEnabled(false);
                    end_turn.setEnabled(true);
                }
            }
            else if(e.getSource() == end_turn){
                roll.setEnabled(true);
                next_player();
                end_turn.setEnabled(false);
            }
        }
        
        private class player_display extends JPanel implements ActionListener, ListSelectionListener{
            JList playerList = new JList(players);
            JList property;
            JLabel propLab = new JLabel("Property");
            JPanel rightSide = new JPanel(new GridBagLayout());
            JTextField [] details = new JTextField[3];
            
            public player_display(){
                super(new GridLayout(1,3));
                
                playerList.setSelectedIndex(0);
                playerList.addListSelectionListener(this);
                add(playerList);
                
                details[0] = new JTextField("Player " + ((Player)playerList.getSelectedValue()).get_player_number());
                details[0].setBackground(((Player)playerList.getSelectedValue()).get_player_color());
                details[1] = new JTextField(((Player)playerList.getSelectedValue()).get_player_name());
                details[2] = new JTextField("$"+((Player)playerList.getSelectedValue()).get_money());
                
                for(int i = 0; i < 3; ++i){
                    details[i].setBorder(new EmptyBorder(10,10,10,10));
                }
                
                add(new JPanel(){
                    public JPanel construct(){
                        setLayout(new GridLayout(3,1));
                        
                        JPanel [] temp = new JPanel[3];
                        
                        for(int i = 0; i < 3; ++i){
                            temp[i] = new JPanel(new GridLayout(1,1));
                            temp[i].add(details[i]);
                            temp[i].setBorder(BorderFactory.createLineBorder(Color.black));
                            add(temp[i]);
                        }
                        return this;
                    }
                }.construct());
                
                add(rightSide);
                
                fillProperty();
                
                validate();
                repaint();
            }
            
            public void fillProperty(){
                rightSide.removeAll();
                
                
                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.BOTH;
                c.weightx = 1;
                c.weighty = 1;
                c.gridheight = 1;
                c.gridwidth = GridBagConstraints.REMAINDER;
                
                propLab.setBorder(new EmptyBorder(1,5,1,1));
                propLab.setBackground(Color.white);
                propLab.setOpaque(true);
                
                rightSide.add(propLab,c);
                
                c.weighty = 15;
                    
                if(((Player)playerList.getSelectedValue()).get_property() != null){
                    property = new JList(((Player)playerList.getSelectedValue()).get_property());
                    JScrollPane sp = new JScrollPane(property);
                    sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    rightSide.add(property,c);
                }
                else{
                    rightSide.add(new JScrollPane(){
                        public JScrollPane construct(){
                            setBackground(Color.white);
                            setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                            return this;
                        }
                    }.construct(),c);
                }
                validate();
                repaint();
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if(lse.getSource() == playerList){
                    
                    details[0].setText("Player " + ((Player)playerList.getSelectedValue()).get_player_number());
                    details[0].setBackground(((Player)playerList.getSelectedValue()).get_player_color());
                    details[1].setText(((Player)playerList.getSelectedValue()).get_player_name());
                    details[2].setText("$"+((Player)playerList.getSelectedValue()).get_money());
                    fillProperty();
                    validate();
                    repaint();
                    
                }
            }
        }
    }
    private class new_game extends JPanel implements KeyListener, ActionListener{
        JLabel label = new JLabel("Number of Players");
        JTextField numPlayers = new JTextField("4");
        JLabel [] players;
        JTextField [] names;
        Color [] playerCols;
        int current_Num = 4;
        JComboBox [] dropDowns;
        JPanel playerPane = new JPanel(new GridLayout(9,2));
        JButton begin = new JButton("Begin");
        
        
        public new_game(){
            super(new GridLayout(10, 1));
            numPlayers.addKeyListener(this);
            
            playerPane.remove(numPlayers);
            
            add(
                new JPanel(new GridLayout(1,2)){
                    JPanel construct(){
                        add(label);
                        add(numPlayers);
                        return this;
                    }
                }.construct()
            );
            
            players = new JLabel[8];
            playerCols = new Color[8];
            dropDowns = new JComboBox[8];
            names = new JTextField[8];
            playerCols[0] = new Color(Color.decode("#ff0000").getRGB()){
                public String toString(){
                    return "Bright Red";
                }
            };
            playerCols[1] = new Color(Color.decode("#00ff00").getRGB()){
                public String toString(){
                    return "Bright Green";
                }
            };
            playerCols[2] = new Color(Color.decode("#0000ff").getRGB()){
                public String toString(){
                    return "Bright Blue";
                }
            };
            playerCols[3] = new Color(Color.decode("#880000").getRGB()){
                public String toString(){
                    return "Dark Red";
                }
            };
            playerCols[4] = new Color(Color.decode("#008800").getRGB()){
                public String toString(){
                    return "Dark Green";
                }
            };
            playerCols[5] = new Color(Color.decode("#000088").getRGB()){
                public String toString(){
                    return "Dark Blue";
                }
            };
            playerCols[6] = new Color(Color.decode("#612468").getRGB()){
                public String toString(){
                    return "Purple";
                }
            };
            playerCols[7] = new Color(Color.decode("#ffFF00").getRGB()){
                public String toString(){
                    return "Yellow";
                }
            };
            
            for(int i = 0; i < 8; ++i){
                players[i] = new JLabel("Player " + (i+1));
                
                names[i] = new JTextField("Player Name");
                
                dropDowns[i] = new JComboBox(playerCols);
                dropDowns[i].setSelectedIndex(i);
                
                if(i > 3){
                    players[i].setVisible(false);
                    names[i].setVisible(false);
                    dropDowns[i].setVisible(false);
                }
                
                add(
                    new JPanel(new GridLayout(1,3)){
                        JPanel construct(int i){
                            add(players[i]);
                            add(names[i]);
                            add(dropDowns[i]);
                            return this;
                        }
                    }.construct(i)
                );
            }
            
            begin.addActionListener(this);
            add(begin);
        }

        @Override
        public void keyTyped(KeyEvent ke) {
            
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            
        }

        @Override
        public void keyReleased(KeyEvent ke) {
            int a;
            try{
                a = Integer.parseInt(numPlayers.getText());
                if(a > 8)
                    return;
                else if(a > current_Num){
                    for(int i = current_Num; i < a; ++i){
                        players[i].setVisible(true);
                        names[i].setVisible(true);
                        dropDowns[i].setVisible(true);
                    }
                }
                else if(a < current_Num){
                    for(int i = a; i <= current_Num; ++i){
                        players[i].setVisible(false);
                        names[i].setVisible(false);
                        dropDowns[i].setVisible(false);
                    }
                }
                current_Num = a;
                //repaint();
            }catch(Exception e){
                if(!numPlayers.getText().equals(""))
                    numPlayers.setText("Not a number");
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == begin){
                Player [] temp = new Player[current_Num];
                for(int i = 0; i < current_Num;++i){
                    temp[i] = new Player(names[i].getText(),i,((Color)dropDowns[i].getSelectedItem()));
                }
                start_game(temp, current_Num);
            }
        }
        
    }
}
