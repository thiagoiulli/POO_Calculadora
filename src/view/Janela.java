package view;

import model.Calculadora;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Janela extends JFrame implements ActionListener{
    private JPanel panel;
    private JButton[] buttons;
    private JTextField tfield;
    Calculadora calc;

    public Janela(){
        instanciarComponentes();
        definirLayout();
        adicionarComponentes();
        registrarHandlers();
        setVisible(true);
        calc = new Calculadora();
    }

    private void instanciarComponentes(){
        panel = new JPanel(new BorderLayout(10, 10));
        buttons = new JButton[20];
        String[] textoButoes = {"/","7","8","9","⌫",
                "*","4","5","6","C",
                "-","1","2","3","%",
                "+",".","0","=","^"};

        for (int i = 0; i < buttons.length; i++){
            buttons[i] = new JButton(textoButoes[i]);
        }
        tfield = new JTextField();
    }

    private void definirLayout(){
        setTitle("Calculadora");
        setSize(300,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tfield.setSize(100, 10);
        tfield.setEditable(false); //desabilitar digitar no texfield para o keylistener lidar com o teclado e nao dar problema
    }

    private void adicionarComponentes(){
        panel.add(tfield, BorderLayout.NORTH);
        JPanel grid = new JPanel(new GridLayout(4,5,5,5));
        for (int i = 0; i < buttons.length; i++){
            grid.add(buttons[i]);
        }
        panel.add(grid, BorderLayout.CENTER);
        add(panel);
    }

    private void registrarHandlers(){
        HashMap<Character, JButton> buttonMap = new HashMap<>(); //mapear texto das teclas para botoes (talvez nao funcione com teclas nao visiveis
        for (int i = 0; i < buttons.length; i++){
            buttonMap.put(buttons[i].getText().charAt(0), buttons[i]);
            buttons[i].addActionListener(this);
        }

        tfield.addKeyListener(new Keycapture(buttonMap)); //registrar teclas
    }

    @Override
    public void actionPerformed(ActionEvent e){
        char btext = ((JButton)e.getSource()).getText().charAt(0);
        try{
            Double.parseDouble(String.valueOf(btext));
            calc.atribuirOperadores(btext);
        } catch (NumberFormatException ex) {
            calc.decidirOperacao(btext);
        }
        finally {
            tfield.setText(calc.getVisor());
            tfield.requestFocusInWindow();
        }
    }
}
