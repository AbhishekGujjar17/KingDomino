
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
                
    private JButton onePlayer, twoPlayer,back; 
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
        border = BorderFactory.createLineBorder(Color.black);
       
        
        
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.green);
        
        getContentPane().add(setNumberOfHumanPlayers());
        getContentPane().add(setOnePlayer());
        getContentPane().add(setTwoPlayer());
        getContentPane().add(setQuit());
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        
        
        
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
        numberOfHumanPlayers.setForeground(Color.black);
        return numberOfHumanPlayers;
    }
    
    
    private JButton setOnePlayer(){
        onePlayer = new JButton("1");
        onePlayer.setFont(new Font("Serif", Font.BOLD, 37));
        onePlayer.setBackground(Color.yellow);
        onePlayer.setForeground(Color.black);
        onePlayer.addActionListener(this);
        onePlayer.setFocusable(false);        
        onePlayer.setBounds(80, 170, 400, 100);
        return onePlayer;
    }    
    
    private JButton setTwoPlayer(){
        twoPlayer = new JButton("2");
        twoPlayer.setFont(new Font("Serif", Font.BOLD, 37));
        twoPlayer.setBackground(Color.yellow);
        twoPlayer.setForeground(Color.black);
        twoPlayer.addActionListener(this);
        twoPlayer.setFocusable(false);        
        twoPlayer.setBounds(80, 290, 400, 100);
        return twoPlayer;
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
        if (selected.equals(onePlayer)){
            
            PlayersInformation playerInformation = new PlayersInformation(2,1,true);
            this.dispose();
            
            
        }
        
         if (selected.equals(twoPlayer)){
            
            PlayersInformation playerInformation = new PlayersInformation(2,2,false);
            this.dispose();
                        
        }
        
        if (selected.equals(back)){
            
            this.dispose();
            NumberOfPlayers playerSelectionDisplay = new NumberOfPlayers();
            
            
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
