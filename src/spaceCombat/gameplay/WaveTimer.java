package spaceCombat.gameplay;

public class WaveTimer extends Thread{

    private int seconds = 0;
    private Boolean killAliens = false;
    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public void run() {
        for(int i = 0; i<30;i++) {
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            seconds++;
            
        }
        killAliens = true;
    }

    public Boolean getKillAliens() {
        return killAliens;
    }
    
}
