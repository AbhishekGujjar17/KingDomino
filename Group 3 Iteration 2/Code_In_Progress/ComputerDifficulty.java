
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
                
    private JButton easy,hard,quit;
    private Border border; 
    
    
    
    public ComputerDifficulty()
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

        this.setTitle("Difficulty Mode");
        border = BorderFactory.createLineBorder(Color.cyan);
        
        
        
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.black);
        
        getContentPane().add(setDifficultyMode());
        getContentPane().add(setEasyDifficultyMode());
        getContentPane().add(setHardDifficultyMode());
        getContentPane().add(setQuit());
        
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    
    }
    
    private JLabel setDifficultyMode() {
        difficultyMode = new JLabel("Difficulty Mode");
        difficultyMode.setHorizontalAlignment(SwingConstants.CENTER);
        difficultyMode.setBorder(border);
        difficultyMode.setFont(new Font("TimesRoman", Font.BOLD, 57));
        difficultyMode.setBounds(67,12,460,117);
        difficultyMode.setForeground(Color.cyan);
        return difficultyMode;
    }
    
    
    private JButton setEasyDifficultyMode(){
        easy = new JButton("Easy");
        easy.setFont(new Font("Serif", Font.BOLD, 37));
        easy.setBackground(Color.cyan);
        easy.setForeground(Color.black);
        easy.addActionListener(this);
        easy.setFocusable(false);        
        easy.setBounds(80, 150, 400, 100);
        return easy;
    }    
    
    private JButton setHardDifficultyMode(){
        hard = new JButton("Hard");
        hard.setFont(new Font("Serif", Font.BOLD, 37));
        hard.setBackground(Color.cyan);
        hard.setForeground(Color.black);
        hard.addActionListener(this);
        hard.setFocusable(false);        
        hard.setBounds(80, 270, 400, 100);
        return hard;
    }
    private JButton setQuit(){
        quit = new JButton("Quit");        
        quit.setFont(new Font("Serif", Font.BOLD, 37));
        quit.setBackground(Color.cyan);
        quit.setForeground(Color.black);
        quit.addActionListener(this);
        quit.setFocusable(false);        
        quit.setBounds(80, 390, 400, 100);
        return quit;
        
        
        
    
    }
    
    
    public void actionPerformed(ActionEvent aevt)
    {
        // get the object that was selected in the gui
        Object selected = aevt.getSource();
        if (selected.equals(easy)){
            
            this.dispose();
            ColourModes displayingColourModes = new ColourModes();
            
            
            
        }
        
         if (selected.equals(hard)){
            
            this.dispose();
            ColourModes displayingColourModes = new ColourModes();
            
            
            
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
