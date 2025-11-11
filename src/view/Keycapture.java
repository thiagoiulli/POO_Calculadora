package view;

import javax.swing.JButton;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Keycapture implements KeyListener {
    HashMap<Character, JButton> buttonMap;

    public Keycapture(HashMap<Character, JButton> buttonMap) {
        this.buttonMap = new HashMap<>();
        this.buttonMap.putAll(buttonMap);
    }
    @Override
    public void keyTyped(KeyEvent e){
        try {
            buttonMap.get(e.getKeyChar()).doClick();
        }
        catch (NullPointerException ex) { //mapeando enter, esc e backspace
            if (e.getKeyChar() == '\n') {
                buttonMap.get('=').doClick();
            }
            else if (e.getKeyChar() == '\b') {
                buttonMap.get('⌫').doClick();
            }
            else if (e.getKeyChar() == '') {
                buttonMap.get('C').doClick();
            }
            else{
                //seria bacana colocar uns efeitos quando digitar uma tecla nao permitida
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
