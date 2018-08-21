package spaceCombat.gameplay;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Stopwatch extends Thread {

    private int seconds = 0;
    private Text mainText = new Text("");
    private Boolean play = false;
    
    public Boolean getPlay() {
        return play;
    }

    public Text getMainText() {
        return mainText;
    }

    public void setMainText(Text text) {
        this.mainText = text;
    }

    @Override
    public void run() {
        
        mainText.setFont(Font.font("Monospaced",45));
        mainText.setText("Prepare for the first wave!");
        mainText.setX(300);
       
        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        };
        
        for(int i = 0;i<6;i++) {
            try {
                Thread.currentThread();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
            
            seconds++;
            mainText.setText(""+(6-seconds));
            mainText.setFont(Font.font("Monospaced",85));
            mainText.setX(620);
        }
        mainText.setText("Start!");
        mainText.setX(520);
        play = true;
        try {
            Thread.currentThread();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println(e);
        }
        mainText.setText("");
        
        
    }

}
