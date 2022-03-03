
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.UIManager;

public class ColourModes extends JFrame implements ActionListener, MouseListener
{
 
    private JLabel colourMode;        
                
    private JButton normal,deficiency,back; 
    private Border border;
    
    private boolean computerPlayers;
    private int playerMode;
    private int humanPlayers;
    
    private String playerOne, playerTwo, playerThree, playerFour;
    
    
    public ColourModes(int playerMode,int humanPlayers, boolean computerPlayers, String playerOne, String playerTwo, String playerThree, String playerFour)
    {
        // For cross platform performance.
        try{
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );}
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        this.computerPlayers = computerPlayers;
        this.playerMode = playerMode;
        this.humanPlayers = humanPlayers;
        
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.playerThree = playerThree;
        this.playerFour = playerFour;
        
        
        this.computerPlayers = computerPlayers;
        this.setSize(600,600);
        this.setLayout(null);
        this.setTitle("King Domino");
        border = BorderFactory.createLineBorder(Color.black); 
        
        
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.green);
        
        getContentPane().add(setColourMode());
        getContentPane().add(setNormalMode());
        getContentPane().add(setDeficiencyMode());
        getContentPane().add(setQuit());
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    
    }
    
    private JLabel setColourMode() {
        colourMode = new JLabel("Colour Mode");
        colourMode.setHorizontalAlignment(SwingConstants.CENTER);
        colourMode.setBorder(border);
        colourMode.setFont(new Font("TimesRoman", Font.BOLD, 57));
        colourMode.setBounds(67,12,460,117);
        colourMode.setForeground(Color.black);
        return colourMode;
    }
    
    
    private JButton setNormalMode(){
        normal = new JButton("Normal");
        normal.setFont(new Font("Serif", Font.BOLD, 37));
        normal.setBackground(Color.yellow);
        normal.setForeground(Color.black);
        normal.addActionListener(this);
        normal.setFocusable(false);        
        normal.setBounds(80, 150, 400, 100);
        return normal;
    }    
    
    private JButton setDeficiencyMode(){
        deficiency = new JButton("Vision Deficiency");
        deficiency.setFont(new Font("Serif", Font.BOLD, 37));
        deficiency.setBackground(Color.yellow);
        deficiency.setForeground(Color.black);
        deficiency.addActionListener(this);
        deficiency.setFocusable(false);        
        deficiency.setBounds(80, 270, 400, 100);
        return deficiency;
    }
    private JButton setQuit(){
        back = new JButton("Back");        
        back.setFont(new Font("Serif", Font.BOLD, 37));
        back.setBackground(Color.yellow);
        back.setForeground(Color.black);
        back.addActionListener(this);
        back.setFocusable(false);        
        back.setBounds(80, 390, 400, 100);
        return back;
        
        
        
    
    }
    
    
    public void actionPerformed(ActionEvent aevt)
    {
        // get the object that was selected in the gui
        Object selected = aevt.getSource();
        if (selected.equals(normal)){
            
            this.dispose();
            Kingdom game = new Kingdom(this.playerMode,this.humanPlayers,this.computerPlayers,this.playerOne,this.playerTwo, this.playerThree, this.playerFour);
            
            
            
        }
        
         if (selected.equals(deficiency)){
            
            this.dispose();
            Kingdom game = new Kingdom(this.playerMode,this.humanPlayers,this.computerPlayers,this.playerOne,this.playerTwo, this.playerThree, this.playerFour);
            
            
            
        }
        
        if (selected.equals(back)){
            
            if (this.computerPlayers == true){
                this.dispose();
                ComputerDifficulty computerDifficultyMode = new ComputerDifficulty(this.playerMode,this.humanPlayers,this.computerPlayers,this.playerOne,this.playerTwo, this.playerThree, this.playerFour);
                
            }
            
            else{
                
                PlayersInformation playerInformation = new PlayersInformation(this.playerMode,this.humanPlayers,this.computerPlayers);
                this.dispose();
            }
            
            
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
