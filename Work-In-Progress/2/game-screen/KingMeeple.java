import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class KingMeeple{
    private String meepleDir;
    private String kingName;
    public JLabel king;
    public KingMeeple(int kingNumber, String kingName){
        this.kingName = kingName;
        meepleDir = "images/normal/meeples/" + kingNumber + ".png";
        king = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource(meepleDir))));
        king.setPreferredSize(new Dimension(25,25));
    }
}
