
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.UIManager;

public class ComputerDifficulty extends JFrame implements ActionListener, MouseListener
{
    
    private JLabel difficultyMode;        
                
    private JButton easy,hard,back;
    private Border border; 
    
    private boolean computerPlayers;
    private int playerMode;
    private int humanPlayers;
    
    private String playerOne, playerTwo, playerThree, playerFour;
    
    
    
    public ComputerDifficulty(int playerMode,int humanPlayers,boolean computerPlayers,String playerOne, String playerTwo, String playerThree, String playerFour)
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
        
        this.setSize(600,600);
        this.setLayout(null);

        this.setTitle("Difficulty Level");
        border = BorderFactory.createLineBorder(Color.black);
        
        
        
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.green);
        
        getContentPane().add(setDifficultyMode());
        getContentPane().add(setEasyDifficultyMode());
        getContentPane().add(setHardDifficultyMode());
        getContentPane().add(setQuit());
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    
    }
    
    private JLabel setDifficultyMode() {
        difficultyMode = new JLabel("Difficulty Level");
        difficultyMode.setHorizontalAlignment(SwingConstants.CENTER);
        difficultyMode.setBorder(border);
        difficultyMode.setFont(new Font("TimesRoman", Font.BOLD, 57));
        difficultyMode.setBounds(67,12,460,117);
        difficultyMode.setForeground(Color.black);
        return difficultyMode;
    }
    
    
    private JButton setEasyDifficultyMode(){
        easy = new JButton("Easy");
        easy.setFont(new Font("Serif", Font.BOLD, 37));
        easy.setBackground(Color.yellow);
        easy.setForeground(Color.black);
        easy.addActionListener(this);
        easy.setFocusable(false);        
        easy.setBounds(80, 150, 400, 100);
        return easy;
    }    
    
    private JButton setHardDifficultyMode(){
        hard = new JButton("Hard");
        hard.setFont(new Font("Serif", Font.BOLD, 37));
        hard.setBackground(Color.yellow);
        hard.setForeground(Color.black);
        hard.addActionListener(this);
        hard.setFocusable(false);        
        hard.setBounds(80, 270, 400, 100);
        return hard;
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
        if (selected.equals(easy)){
            
            this.dispose();
            ColourModes displayingColourModes = new ColourModes(this.playerMode,this.humanPlayers,this.computerPlayers,this.playerOne,this.playerTwo, this.playerThree, this.playerFour);
            
            
            
        }
        
         if (selected.equals(hard)){
            
            this.dispose();
            ColourModes displayingColourModes = new ColourModes(this.playerMode,this.humanPlayers,this.computerPlayers,this.playerOne,this.playerTwo, this.playerThree, this.playerFour);
            
            
            
        }
        
        if (selected.equals(back)){
            
            PlayersInformation playerInformation = new PlayersInformation(this.playerMode,this.humanPlayers,this.computerPlayers);
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
