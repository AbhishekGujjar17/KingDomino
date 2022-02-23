
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.UIManager;


public class NumberOfPlayers extends JFrame implements ActionListener, MouseListener
{
    
    private JLabel numberOfPlayers;        
                
    private JButton twoPlayer, fourPlayer,quit; 
    private Border border; 
    
    
    public NumberOfPlayers()
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
        
        getContentPane().add(setNumberOfPlayers());
        getContentPane().add(setTwoPlayers());
        getContentPane().add(setFourPlayers());
        getContentPane().add(setQuit());
        
        
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    
    }
    
    private JLabel setNumberOfPlayers() {
        numberOfPlayers = new JLabel("Number of Players");
        numberOfPlayers.setHorizontalAlignment(SwingConstants.CENTER);
        numberOfPlayers.setBorder(border);
        numberOfPlayers.setFont(new Font("Serif", Font.BOLD, 57));
        numberOfPlayers.setBounds(12,20,560,117);
        numberOfPlayers.setForeground(Color.cyan);
        return numberOfPlayers;
    }
    
  
    
    private JButton setTwoPlayers(){
        twoPlayer = new JButton("2 Players");
        twoPlayer.setFont(new Font("Serif", Font.BOLD, 37));
        twoPlayer.setBackground(Color.cyan);
        twoPlayer.setForeground(Color.black);
        twoPlayer.addActionListener(this);
        twoPlayer.setFocusable(false);        
        twoPlayer.setBounds(80, 170, 400, 100);
        return twoPlayer;
    }    
    
    private JButton setFourPlayers(){
        fourPlayer = new JButton("4 Players");
        fourPlayer.setFont(new Font("Serif", Font.BOLD, 37));
        fourPlayer.setBackground(Color.cyan);
        fourPlayer.setForeground(Color.black);
        fourPlayer.addActionListener(this);
        fourPlayer.setFocusable(false);        
        fourPlayer.setBounds(80, 290, 400, 100);
        return fourPlayer;
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
        if (selected.equals(twoPlayer)){
            
            TwoHumanPlayer twoPlayerSelectionDisplay = new TwoHumanPlayer();
            this.dispose();
            
            
        }
        
         if (selected.equals(fourPlayer)){
            
            FourHumanPlayer fourPlayerSelectionDisplay = new FourHumanPlayer();
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
