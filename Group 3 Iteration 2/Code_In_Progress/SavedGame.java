import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.UIManager;

public class SavedGame extends JFrame implements ActionListener {
    private JLabel savedGames;
    private JButton folderOne, folderTwo, folderThree, folderFour,folderFive, folderSix;
    private Border border;
    
    public SavedGame() {
                
        try{
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );}
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        this.setSize(800, 800);
        this.setLayout(null);
        this.setTitle("SAVED GAMES");
        
        border = BorderFactory.createLineBorder(Color.cyan);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.black);
        
        getContentPane().add(setSavedGames());
        getContentPane().add(setFolderOne());
        getContentPane().add(setFolderTwo());
        getContentPane().add(setFolderThree());
        getContentPane().add(setFolderFour());
        getContentPane().add(setFolderFive());
        getContentPane().add(setFolderSix());
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    
    }
    
    private JLabel setSavedGames() {
        savedGames = new JLabel("SAVED GAMES");
        savedGames.setHorizontalAlignment(SwingConstants.CENTER);
        savedGames.setBorder(border);
        savedGames.setFont(new Font("Serif", Font.BOLD, 57));
        savedGames.setBounds(177,10,460,80);
        savedGames.setForeground(Color.cyan);
        return savedGames;
    }
    
    
    private JButton setFolderOne(){
        folderOne = new JButton("SAVE GAME - Folder 1");
        folderOne.setFont(new Font("Serif", Font.BOLD, 37));
        folderOne.setBackground(Color.cyan);
        folderOne.setForeground(Color.black);
        folderOne.addActionListener(this);
        folderOne.setFocusable(false);        
        folderOne.setBounds(80,110, 600, 100);
        return folderOne;
    }    
    
    private JButton setFolderTwo(){
        folderTwo = new JButton("SAVE GAME - Folder 2");
        folderTwo.setFont(new Font("Serif", Font.BOLD, 37));
        folderTwo.setBackground(Color.cyan);
        folderTwo.setForeground(Color.black);
        folderTwo.addActionListener(this);
        folderTwo.setFocusable(false);        
        folderTwo.setBounds(80, 217 , 600, 100);
        return folderTwo;
    }
    private JButton setFolderThree(){
        folderThree = new JButton("SAVE GAME - Folder 3");        
        folderThree.setFont(new Font("Serif", Font.BOLD, 37));
        folderThree.setBackground(Color.cyan);
        folderThree.setForeground(Color.black);
        folderThree.addActionListener(this);
        folderThree.setFocusable(false);        
        folderThree.setBounds(80, 324, 600, 100);
        return folderThree;
    }
    private JButton setFolderFour(){
        folderFour = new JButton("SAVE GAME - Folder 4");
        folderFour.setFont(new Font("Serif", Font.BOLD, 37));
        folderFour.setBackground(Color.cyan);
        folderFour.setForeground(Color.black);
        folderFour.addActionListener(this);
        folderFour.setFocusable(false);        
        folderFour.setBounds(80, 431, 600, 100);
        return folderFour;
    }
    
    private JButton setFolderFive(){
        folderFive = new JButton("SAVE GAME - Folder 5");
        folderFive.setFont(new Font("Serif", Font.BOLD, 37));
        folderFive.setBackground(Color.cyan);
        folderFive.setForeground(Color.black);
        folderFive.addActionListener(this);
        folderFive.setFocusable(false);        
        folderFive.setBounds(80, 538, 600, 100);
        return folderFive;
    }
    
    private JButton setFolderSix(){
        folderSix = new JButton("SAVE GAME - Folder 6");
        folderSix.setFont(new Font("Serif", Font.BOLD, 37));
        folderSix.setBackground(Color.cyan);
        folderSix.setForeground(Color.black);
        folderSix.addActionListener(this);
        folderSix.setFocusable(false);        
        folderSix.setBounds(80, 645, 600, 100);
        return folderSix;
    }
    

    public void actionPerformed(ActionEvent aevt) {
        
        Object selected = aevt.getSource();
        if(selected == this.folderOne) {
            this.dispose();
        }
        else if(selected == this.folderTwo) {
            this.dispose();
        }
        
        else if(selected == this.folderThree) {
            this.dispose();
        }
        else if(selected == this.folderFour) {
            this.dispose();
        }

        else if(selected == this.folderFive) {
            this.dispose();
        }
        else if(selected == this.folderSix) {
            this.dispose();
        }
        
        
    }

}
