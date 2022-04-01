
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
                
    private JButton twoPlayer, fourPlayer,back; 
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
        border = BorderFactory.createLineBorder(Color.black);
        
        
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.green);
        
        getContentPane().add(setNumberOfPlayers());
        getContentPane().add(setTwoPlayers());
        getContentPane().add(setFourPlayers());
        getContentPane().add(setQuit());
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
 
        
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
        numberOfPlayers.setForeground(Color.black);
        return numberOfPlayers;
    }
    
  
    
    private JButton setTwoPlayers(){
        twoPlayer = new JButton("2 Players");
        twoPlayer.setFont(new Font("Serif", Font.BOLD, 37));
        twoPlayer.setBackground(Color.yellow);
        twoPlayer.setForeground(Color.black);
        twoPlayer.addActionListener(this);
        twoPlayer.setFocusable(false);        
        twoPlayer.setBounds(80, 170, 400, 100);
        return twoPlayer;
    }    
    
    private JButton setFourPlayers(){
        fourPlayer = new JButton("4 Players");
        fourPlayer.setFont(new Font("Serif", Font.BOLD, 37));
        fourPlayer.setBackground(Color.yellow);
        fourPlayer.setForeground(Color.black);
        fourPlayer.addActionListener(this);
        fourPlayer.setFocusable(false);        
        fourPlayer.setBounds(80, 290, 400, 100);
        return fourPlayer;
    }
    private JButton setQuit(){
        back = new JButton("Back");        
        back.setFont(new Font("Serif", Font.BOLD, 37));
        back.setBackground(Color.yellow);
        back.setForeground(Color.black);
        back.addActionListener(this);
        back.setFocusable(false);        
        back.setBounds(80, 410, 400, 100);
        return back;
        
        
        
    
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
        
        if (selected.equals(back)){
            
            NewGame backNewGame = new NewGame();
            this.dispose();
            
            
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
