
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.UIManager;
import java.util.ArrayList;


public class WinnerScreen extends JFrame implements ActionListener, MouseListener
{
    
    private JLabel gameTitle; 
    private Border border;
    private JButton quit; 
    private JLabel bgLabel;
    private String winnerName;
    
    public WinnerScreen(String winnerName)
    {
        // For cross platform performance.
        try{
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );}
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        
        this.winnerName = winnerName;
        this.setSize(600,600);
        this.setLayout(null);
        this.setTitle("WINNER");
        border = BorderFactory.createLineBorder(Color.black);
        
        
        
        
        
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.black);
        
        bgLabel = new JLabel();
        ImageIcon bgIcon = new ImageIcon("images/winner.jpg");
        bgLabel.setIcon(bgIcon);
        bgLabel.setBounds(0,0,780,400);
        
        //getContentPane().add(setGameTitle());
        getContentPane().add(setWinnerName());
        getContentPane().add(bgLabel);
       
      
        
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    
    }
    
    public JLabel setGameTitle() {
        gameTitle = new JLabel("CONGRATS");
        gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gameTitle.setBorder(border);
        gameTitle.setFont(new Font("Serif", Font.BOLD, 57));
        gameTitle.setBounds(90,20,380,117);
        gameTitle.setForeground(Color.black);
        return gameTitle;
    }
    
    
    public JLabel setWinnerName() {
        gameTitle = new JLabel("");
        gameTitle.setText(this.winnerName);
        gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        //gameTitle.setBorder(border);
        gameTitle.setFont(new Font("Serif", Font.BOLD, 57));
        gameTitle.setBounds(80,400,420,117);
        gameTitle.setForeground(Color.gray);
        return gameTitle;
    }
    
    
    
   
    
    
    public void actionPerformed(ActionEvent aevt)
    {
        // get the object that was selected in the gui
        Object selected = aevt.getSource();
        
        
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