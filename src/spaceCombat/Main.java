package spaceCombat;

import javafx.embed.swing.JFXPanel;
import spaceCombat.gameplay.Window;


public class Main {

    public static void main(String[] args) {
        JFXPanel panel = new JFXPanel();            
        
        Window window = new Window();
        window.run();
        
    }

}
