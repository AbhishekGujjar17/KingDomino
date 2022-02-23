
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.UIManager;


public class TwoHumanPlayer extends JFrame implements ActionListener, MouseListener
{
    
    private JLabel numberOfHumanPlayers;       
                
    private JButton onePlayer, twoPlayer,quit; 
    private Border border; 
                  
    
    
    public TwoHumanPlayer()
    {
        // For cross platform performance.
        try{
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );}
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        this.setSize(600,600);
        
        this.setLayout(null);
        this.setTitle("Number of Players");
        border = BorderFactory.createLineBorder(Color.cyan);
       
        
        
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.black);
        
        getContentPane().add(setNumberOfHumanPlayers());
        getContentPane().add(setOnePlayer());
        getContentPane().add(setTwoPlayer());
        getContentPane().add(setQuit());
        
        
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    
    }
    
    private JLabel setNumberOfHumanPlayers() {
        numberOfHumanPlayers = new JLabel("Human Players");
        numberOfHumanPlayers.setHorizontalAlignment(SwingConstants.CENTER);
        numberOfHumanPlayers.setBorder(border);
        
        numberOfHumanPlayers.setFont(new Font("Serif", Font.BOLD, 57));
        numberOfHumanPlayers.setBounds(60,12,460,117);
        numberOfHumanPlayers.setForeground(Color.cyan);
        return numberOfHumanPlayers;
    }
    
    
    private JButton setOnePlayer(){
        onePlayer = new JButton("1");
        onePlayer.setFont(new Font("Serif", Font.BOLD, 37));
        onePlayer.setBackground(Color.cyan);
        onePlayer.setForeground(Color.black);
        onePlayer.addActionListener(this);
        onePlayer.setFocusable(false);        
        onePlayer.setBounds(80, 170, 400, 100);
        return onePlayer;
    }    
    
    private JButton setTwoPlayer(){
        twoPlayer = new JButton("2");
        twoPlayer.setFont(new Font("Serif", Font.BOLD, 37));
        twoPlayer.setBackground(Color.cyan);
        twoPlayer.setForeground(Color.black);
        twoPlayer.addActionListener(this);
        twoPlayer.setFocusable(false);        
        twoPlayer.setBounds(80, 290, 400, 100);
        return twoPlayer;
    }
    private JButton setQuit(){
        quit = new JButton("Quit");        
        quit.setFont(new Font("Serif", Font.BOLD, 37));
        quit.setBackground(Color.cyan);
        quit.setForeground(Color.black);
        quit.addActionListener(this);
        quit.setFocusable(false);        
        quit.setBounds(80, 410, 400, 100);
        return quit;
        
        
        
    
    }
    
   
    public void actionPerformed(ActionEvent aevt)
    {
        // get the object that was selected in the gui
        Object selected = aevt.getSource();
        if (selected.equals(onePlayer)){
            
            ComputerDifficulty displayingComputerDifficultyMode = new ComputerDifficulty();
            this.dispose();
            
            
        }
        
         if (selected.equals(twoPlayer)){
            
            ColourModes displayingColourModes = new ColourModes();
            this.dispose();
                        
        }
        
        if (selected.equals(quit)){
            
            System.exit(0);
            
            
        }
        
        
    }


    // Mouse Listener events
    public void mouseClicked(MouseEvent mevt)
    {
        // get the object that was selected in the gui
        Object selected = mevt.getSource();
        
        
    
    }
    
    // not used but must be present to fulfil MouseListener contract
    public void mouseEntered(MouseEvent arg0){}
    public void mouseExited(MouseEvent arg0) {}
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}
}
