package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keycapture implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e){
        System.out.println("Key Pressed:" + KeyEvent.getKeyText(e.getKeyChar()));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        return;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        return;
    }
}
