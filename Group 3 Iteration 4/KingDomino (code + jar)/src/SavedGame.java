import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.UIManager;

public class SavedGame extends JFrame implements ActionListener {
    private JLabel savedGames;
    private JButton folderOne, folderTwo, folderThree, folderFour,folderFive, back;
    private Border border;
    
    public SavedGame() {
                
        // For cross platform performance.
        try{
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );}
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        this.setSize(800, 800);
        this.setLayout(null);
        this.setTitle("SAVED GAMES");
        
        border = BorderFactory.createLineBorder(Color.black);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.green);
        
        getContentPane().add(setSavedGames());
        getContentPane().add(setFolderOne());
        getContentPane().add(setFolderTwo());
        getContentPane().add(setFolderThree());
        getContentPane().add(setFolderFour());
        getContentPane().add(setFolderFive());
        getContentPane().add(setFolderSix());
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        
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
        savedGames.setForeground(Color.black);
        return savedGames;
    }
    
    
    private JButton setFolderOne(){
        folderOne = new JButton("SAVE GAME - Folder 1");
        folderOne.setFont(new Font("Serif", Font.BOLD, 37));
        folderOne.setBackground(Color.yellow);
        folderOne.setForeground(Color.black);
        folderOne.addActionListener(this);
        folderOne.setFocusable(false);        
        folderOne.setBounds(80,110, 600, 100);
        return folderOne;
    }    
    
    private JButton setFolderTwo(){
        folderTwo = new JButton("SAVE GAME - Folder 2");
        folderTwo.setFont(new Font("Serif", Font.BOLD, 37));
        folderTwo.setBackground(Color.yellow);
        folderTwo.setForeground(Color.black);
        folderTwo.addActionListener(this);
        folderTwo.setFocusable(false);        
        folderTwo.setBounds(80, 217 , 600, 100);
        return folderTwo;
    }
    private JButton setFolderThree(){
        folderThree = new JButton("SAVE GAME - Folder 3");        
        folderThree.setFont(new Font("Serif", Font.BOLD, 37));
        folderThree.setBackground(Color.yellow);
        folderThree.setForeground(Color.black);
        folderThree.addActionListener(this);
        folderThree.setFocusable(false);        
        folderThree.setBounds(80, 324, 600, 100);
        return folderThree;
    }
    private JButton setFolderFour(){
        folderFour = new JButton("SAVE GAME - Folder 4");
        folderFour.setFont(new Font("Serif", Font.BOLD, 37));
        folderFour.setBackground(Color.yellow);
        folderFour.setForeground(Color.black);
        folderFour.addActionListener(this);
        folderFour.setFocusable(false);        
        folderFour.setBounds(80, 431, 600, 100);
        return folderFour;
    }
    
    private JButton setFolderFive(){
        folderFive = new JButton("SAVE GAME - Folder 5");
        folderFive.setFont(new Font("Serif", Font.BOLD, 37));
        folderFive.setBackground(Color.yellow);
        folderFive.setForeground(Color.black);
        folderFive.addActionListener(this);
        folderFive.setFocusable(false);        
        folderFive.setBounds(80, 538, 600, 100);
        return folderFive;
    }
    
    private JButton setFolderSix(){
        back = new JButton("Back");
        back.setFont(new Font("Serif", Font.BOLD, 37));
        back.setBackground(Color.yellow);
        back.setForeground(Color.black);
        back.addActionListener(this);
        back.setFocusable(false);        
        back.setBounds(270, 645, 150, 70);
        return back;
    }

    public void loadGame(int n) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(""+n+"/Kingdoms.bin");
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<TerritoryState> kingdoms = (List<TerritoryState>) ois.readObject();

        fis = new FileInputStream(""+ n + "/CurrentStack.bin");
        ois = new ObjectInputStream(fis);
        List<TileStack.StackSlot> currentStackState = (List<TileStack.StackSlot>) ois.readObject();

        fis = new FileInputStream(""+ n + "/FutureStack.bin");
        ois = new ObjectInputStream(fis);
        List<TileStack.StackSlot> futureStackState = (List<TileStack.StackSlot>) ois.readObject();

        fis = new FileInputStream(""+ n + "/Game.bin");
        ois = new ObjectInputStream(fis);
        GameScreenState gameScreenState = (GameScreenState) ois.readObject();
        Kingdom game = new Kingdom(gameScreenState.playerMode, gameScreenState.humanPlayers, gameScreenState.colorMode, gameScreenState.botPresent, gameScreenState.playerOne, gameScreenState.playerTwo, gameScreenState.playerThree, gameScreenState.playerFour, true);
        game.loadGame(currentStackState, futureStackState, kingdoms, gameScreenState);
    }
    

    public void actionPerformed(ActionEvent aevt) {
        
        Object selected = aevt.getSource();
        if(selected == folderOne) {
            try {
                loadGame(1);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            this.dispose();
        }
        else if(selected == folderTwo) {
            this.dispose();
        }
        
        else if(selected == folderThree) {
            this.dispose();
        }
        else if(selected == folderFour) {
            this.dispose();
        }

        else if(selected == folderFive) {
            this.dispose();
        }
        else if(selected == back) {
            new NewGame();
            this.dispose();
        }
        
        
    }

}
