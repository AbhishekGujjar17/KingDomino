
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.UIManager;


public class PlayersInformation extends JFrame implements ActionListener, MouseListener
{
    
    private JLabel playerInformation, nameOfPlayerOne, nameOfPlayerTwo, nameOfPlayerThree, nameOfPlayerFour;
    private JTextField playerOneName, playerTwoName, playerThreeName, playerFourName;
                
    private JButton twoPlayer, fourPlayer,submitTwo, submitFour, back; 
    private Border border; 
    private boolean computerPlayers;
    private int playerMode;
    private int humanPlayers;
    
    
    public PlayersInformation(int playerMode, int humanPlayers, boolean computerPlayers)
    {
        // For cross platform performance.
        try{
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );}
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        //this.playersPlayingGame = playersPlayingGame;
        
        this.computerPlayers = computerPlayers;
        this.playerMode = playerMode;
        this.humanPlayers = humanPlayers;
        this.setSize(600,600);
        this.setLayout(null);
        this.setTitle("Number of Players");
        border = BorderFactory.createLineBorder(Color.black);

        
        
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.green);
        
        getContentPane().add(setPlayersInformation());
        
        if (playerMode == 2 && humanPlayers == 1){
            
            getContentPane().add(setNameOfPlayerOne());
            getContentPane().add(setNameOfPlayerTwo());
            getContentPane().add(getNameOfPlayerOne());
            getContentPane().add(getNameOfPlayerTwoRobot());
            getContentPane().add(setSubmitForTwoPlayers());
            getContentPane().add(setQuit());
            
        }
        
        if (playerMode == 2 && humanPlayers == 2){
            
            getContentPane().add(setNameOfPlayerOne());
            getContentPane().add(setNameOfPlayerTwo());
            getContentPane().add(getNameOfPlayerOne());
            getContentPane().add(getNameOfPlayerTwo());
            getContentPane().add(setSubmitForTwoPlayers());
            getContentPane().add(setQuit());
            
        }
        
        if (playerMode == 4 && humanPlayers == 1){
            
            getContentPane().add(setNameOfPlayerOne());
            getContentPane().add(setNameOfPlayerTwo());
            getContentPane().add(setNameOfPlayerThree());
            getContentPane().add(setNameOfPlayerFour());
           getContentPane().add(getNameOfPlayerOne());
           getContentPane().add(getNameOfPlayerTwoRobot());
           getContentPane().add(getNameOfPlayerThreeRobot());
           getContentPane().add(getNameOfPlayerFourRobot());
           getContentPane().add(setSubmitForFourPlayers());
           getContentPane().add(setQuit());
            
        }
        
        if (playerMode == 4 && humanPlayers == 2){
            
            getContentPane().add(setNameOfPlayerOne());
            getContentPane().add(setNameOfPlayerTwo());
            getContentPane().add(setNameOfPlayerThree());
            getContentPane().add(setNameOfPlayerFour());
           getContentPane().add(getNameOfPlayerOne());
           getContentPane().add(getNameOfPlayerTwo());
           getContentPane().add(getNameOfPlayerThreeRobot());
           getContentPane().add(getNameOfPlayerFourRobot());
           getContentPane().add(setSubmitForFourPlayers());
           getContentPane().add(setQuit());
            
        }
        
        if (playerMode == 4 && humanPlayers == 3){
            
            getContentPane().add(setNameOfPlayerOne());
            getContentPane().add(setNameOfPlayerTwo());
            getContentPane().add(setNameOfPlayerThree());
            getContentPane().add(setNameOfPlayerFour());
           getContentPane().add(getNameOfPlayerOne());
           getContentPane().add(getNameOfPlayerTwo());
           getContentPane().add(getNameOfPlayerThree());
           getContentPane().add(getNameOfPlayerFourRobot());
           getContentPane().add(setSubmitForFourPlayers());
           getContentPane().add(setQuit());
            
            
        }
        
        if (playerMode == 4 && humanPlayers == 4){
            
            getContentPane().add(setNameOfPlayerOne());
            getContentPane().add(setNameOfPlayerTwo());
            getContentPane().add(setNameOfPlayerThree());
            getContentPane().add(setNameOfPlayerFour());
           getContentPane().add(getNameOfPlayerOne());
           getContentPane().add(getNameOfPlayerTwo());
           getContentPane().add(getNameOfPlayerThree());
           getContentPane().add(getNameOfPlayerFour());
           getContentPane().add(setSubmitForFourPlayers());
           getContentPane().add(setQuit());
            
        }
        
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    
    }
    
     private JLabel setPlayersInformation() {
        playerInformation = new JLabel("Player's Information");
        playerInformation.setHorizontalAlignment(SwingConstants.CENTER);
        playerInformation.setBorder(border);
        playerInformation.setFont(new Font("Serif", Font.BOLD, 57));
        playerInformation.setBounds(12,20,560,117);
        playerInformation.setForeground(Color.black);
        return playerInformation;
    }
    
    private JLabel setNameOfPlayerOne() {
        nameOfPlayerOne = new JLabel("Player 1 Name:");
        nameOfPlayerOne.setFont(new Font("Serif", Font.BOLD, 37));
        nameOfPlayerOne.setBounds(17,120,260,117);
        nameOfPlayerOne.setForeground(Color.black);
        return nameOfPlayerOne;
    }


    private JLabel setNameOfPlayerTwo() {
        nameOfPlayerTwo = new JLabel("Player 2 Name:");
        nameOfPlayerTwo.setFont(new Font("Serif", Font.BOLD, 37));
        nameOfPlayerTwo.setBounds(17,200,260,117);
        nameOfPlayerTwo.setForeground(Color.black);
        return nameOfPlayerTwo;
    }

    private JLabel setNameOfPlayerThree() {
        nameOfPlayerThree = new JLabel("Player 3 Name:");
        nameOfPlayerThree.setFont(new Font("Serif", Font.BOLD, 37));
        nameOfPlayerThree.setBounds(17,280,260,117);
        nameOfPlayerThree.setForeground(Color.black);
        return nameOfPlayerThree;
    }

    private JLabel setNameOfPlayerFour() {
        nameOfPlayerFour = new JLabel("Player 4 Name:");
        nameOfPlayerFour.setFont(new Font("Serif", Font.BOLD, 37));
        nameOfPlayerFour.setBounds(17,360,260,117);
        nameOfPlayerFour.setForeground(Color.black);
        return nameOfPlayerFour;
    }


    private JTextField getNameOfPlayerOne() {
        playerOneName = new JTextField();
        playerOneName.setFont(new Font("Serif", Font.BOLD, 37));
        playerOneName.setBounds(300,150,260,70);
        playerOneName.setForeground(Color.black);
        playerOneName.setBackground(Color.yellow);
        return playerOneName;
    }

    private JTextField getNameOfPlayerTwo() {
        playerTwoName = new JTextField();
        playerTwoName.setFont(new Font("Serif", Font.BOLD, 37));
        playerTwoName.setBounds(300,230,260,70);
        playerTwoName.setForeground(Color.black);
        playerTwoName.setBackground(Color.yellow);
        return playerTwoName;
    }
    
    

    private JTextField getNameOfPlayerThree() {
        playerThreeName = new JTextField();
        playerThreeName.setFont(new Font("Serif", Font.BOLD, 37));
        playerThreeName.setBounds(300,310,260,70);
        playerThreeName.setForeground(Color.black);
        playerThreeName.setBackground(Color.yellow);
        return playerThreeName;
    }
    
    private JTextField getNameOfPlayerTwoRobot() {
        playerTwoName = new JTextField("Robot-X");
        playerTwoName.setEditable(false);
        playerTwoName.setFont(new Font("Serif", Font.BOLD, 37));
        playerTwoName.setBounds(300,230,260,70);
        playerTwoName.setForeground(Color.black);
        playerTwoName.setBackground(Color.yellow);
        return playerTwoName;
    }
    
    private JTextField getNameOfPlayerThreeRobot() {
        playerThreeName = new JTextField("Robot-Y");
        playerThreeName.setEditable(false);
        playerThreeName.setFont(new Font("Serif", Font.BOLD, 37));
        playerThreeName.setBounds(300,310,260,70);
        playerThreeName.setForeground(Color.black);
        playerThreeName.setBackground(Color.yellow);
        return playerThreeName;
    }
    
     private JTextField getNameOfPlayerFourRobot() {
        playerFourName = new JTextField("Robot-Z");
        playerFourName.setEditable(false);
        playerFourName.setFont(new Font("Serif", Font.BOLD, 37));
        playerFourName.setBounds(300,390,260,70);
        playerFourName.setForeground(Color.black);
        playerFourName.setBackground(Color.yellow);
        return playerFourName;
    }

    private JTextField getNameOfPlayerFour() {
        playerFourName = new JTextField();
        playerFourName.setFont(new Font("Serif", Font.BOLD, 37));
        playerFourName.setBounds(300,390,260,70);
        playerFourName.setForeground(Color.black);
        playerFourName.setBackground(Color.yellow);
        return playerFourName;
    }
    
    private JButton setQuit(){
        back = new JButton("Back");
        back.setHorizontalAlignment(SwingConstants.CENTER);
        back.setFont(new Font("Serif", Font.BOLD, 37));
        back.setBackground(Color.yellow);
        back.setForeground(Color.black);
        back.addActionListener(this);
        back.setFocusable(false);        
        back.setBounds(100, 480, 150, 50);
        return back;
        
    }

    
    
    
    
     private JButton setSubmitForTwoPlayers(){
        submitTwo = new JButton("Submit");
        submitTwo.setHorizontalAlignment(SwingConstants.CENTER);
        submitTwo.setFont(new Font("Serif", Font.BOLD, 37));
        submitTwo.setBackground(Color.yellow);
        submitTwo.setForeground(Color.black);
        submitTwo.addActionListener(this);
        submitTwo.setFocusable(false);        
        submitTwo.setBounds(300, 480, 150, 50);
        return submitTwo;
        
    }
   
    
    
    

    private JButton setSubmitForFourPlayers(){
        submitFour = new JButton("Submit");
        submitFour.setHorizontalAlignment(SwingConstants.CENTER);
        submitFour.setFont(new Font("Serif", Font.BOLD, 37));
        submitFour.setBackground(Color.yellow);
        submitFour.setForeground(Color.black);
        submitFour.addActionListener(this);
        submitFour.setFocusable(false);        
        submitFour.setBounds(305, 480, 150, 50);
        return submitFour;
        
    }

    
    
    public void actionPerformed(ActionEvent aevt)
    {
        // get the object that was selected in the gui
        Object selected = aevt.getSource();
        
        if (selected.equals(back)){
            
            if (this.playerMode == 2){
                 
                TwoHumanPlayer twoPlayerSelectionDisplay = new TwoHumanPlayer();
                this.dispose();
            }
            
            else{
                FourHumanPlayer fourPlayerSelectionDisplay = new FourHumanPlayer();
                this.dispose();
            }
            
            
        }        
        
        else if ((selected.equals(submitTwo)) && (playerOneName.getText().isEmpty() == false) && (playerTwoName.getText().isEmpty()==false)){
            
            if (this.computerPlayers==true){
                this.dispose();
                ComputerDifficulty computerDifficultyMode = new ComputerDifficulty(this.playerMode,this.humanPlayers,this.computerPlayers);
                
            }
            
            else{
                this.dispose();
                ColourModes displayingColourModes = new ColourModes(this.playerMode,this.humanPlayers,this.computerPlayers);
            }
            
            
        }
        
        else if ((selected.equals(submitFour)) && (playerOneName.getText().isEmpty() == false) && (playerTwoName.getText().isEmpty()==false) 
        && (playerThreeName.getText().isEmpty()==false) && (playerFourName.getText().isEmpty()==false)){
            
            if (this.computerPlayers==true){
                this.dispose();
                ComputerDifficulty computerDifficultyMode = new ComputerDifficulty(this.playerMode,this.humanPlayers,this.computerPlayers);
                
            }
            
            else{
                this.dispose();
                ColourModes displayingColourModes = new ColourModes(this.playerMode,this.humanPlayers,this.computerPlayers);
            }
            
            
        }
        
        else{
        
               JOptionPane.showMessageDialog(null,"Please Enter Player's Name","WARNING ",JOptionPane.PLAIN_MESSAGE);        
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

