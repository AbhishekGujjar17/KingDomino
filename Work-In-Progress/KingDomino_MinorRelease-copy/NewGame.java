
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.UIManager;
import java.util.ArrayList;


public class NewGame extends JFrame implements ActionListener, MouseListener
{
    
    private JLabel gameTitle; 
    private Border border;
    private JButton newGame,saveGame,quit; 
    
    
    public NewGame()
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
        this.setTitle("King Domino");
        border = BorderFactory.createLineBorder(Color.black);
        
        
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.green);
        
        getContentPane().add(setGameTitle());
        getContentPane().add(setnewGame());
        getContentPane().add(setSavedGame());
        getContentPane().add(setQuitGame());
        
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    
    }
    
    public JLabel setGameTitle() {
        gameTitle = new JLabel("King Domino");
        gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gameTitle.setBorder(border);
        gameTitle.setFont(new Font("Serif", Font.BOLD, 57));
        gameTitle.setBounds(90,10,380,117);
        gameTitle.setForeground(Color.black);
        return gameTitle;
    }
    
    
    public JButton setnewGame(){
        newGame = new JButton("New Game");
        newGame.setBackground(Color.yellow);
        newGame.setForeground(Color.black);
        newGame.setFont(new Font("Serif", Font.BOLD, 37));
        newGame.addActionListener(this);
        newGame.setFocusable(false);        
        newGame.setBounds(80, 150, 400, 100);
        return newGame;
    }    
    
    public JButton setSavedGame(){
        saveGame = new JButton("Saved Games");
        saveGame.setBackground(Color.yellow);
        saveGame.setForeground(Color.black);
        saveGame.setFont(new Font("Serif", Font.BOLD, 37));
        saveGame.addActionListener(this);
        saveGame.setFocusable(false);        
        saveGame.setBounds(80, 270, 400, 100);
        return saveGame;
    }
    public JButton setQuitGame(){
        quit = new JButton("Quit");        
        quit.setBackground(Color.yellow);
        quit.setForeground(Color.black);
        quit.setFont(new Font("Serif", Font.BOLD, 37));
        quit.addActionListener(this);
        quit.setFocusable(false);        
        quit.setBounds(80, 390, 400, 100);
        return quit;
        
        
        
    
    }
    
    
    public void actionPerformed(ActionEvent aevt)
    {
        // get the object that was selected in the gui
        Object selected = aevt.getSource();
        if (selected.equals(newGame)){
            
            this.dispose();
            NumberOfPlayers playerSelectionDisplay = new NumberOfPlayers();
            
            
        }
        
         if (selected.equals(saveGame)){
            
            this.dispose();
            SavedGame savedGameDisplay = new SavedGame();
            
            
        }
        
        if (selected.equals(quit)){
            
            System.exit(0);
            
            
        }
        
        
    }


    // Mouse Listener events
    public void mouseClicked(MouseEvent mevt)
    {
        
        Object selected = mevt.getSource();
        
        
    
    }
    
    // not used but must be present to fulfil MouseListener contract
    public void mouseEntered(MouseEvent arg0){}
    public void mouseExited(MouseEvent arg0) {}
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}
}
