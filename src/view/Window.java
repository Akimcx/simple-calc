package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Button extends JButton {
    Button(String text){
        super(text);
    }
}

public class Window {

    static Pattern quotingPriorOp = Pattern.compile("\\d+(\\.\\d+)?[*\\/]\\d+(\\.\\d+)?");
    static Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?[+-]\\d+(\\.\\d+)?");

    public static void main ( String[] args ) {
        JFrame window = new JFrame("Calculator");
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setSize( 300,550 );
        window.setResizable( false );
        window.setLocationRelativeTo( null );
        JPanel panel = new JPanel(new GridBagLayout());

        // Text Area
        JTextArea topTextArea = new JTextArea(1,2);
        topTextArea.setEnabled( false );
        JTextArea bottomTextArea = new JTextArea(1,2);

        // Buttons
        JButton zero = new JButton( "0" );
        JButton one = new JButton( "1" );
        JButton two = new JButton( "2" );
        JButton three = new JButton( "3" );
        JButton four = new JButton( "4" );
        JButton five = new JButton( "5" );
//        Button six = new Button( "6" );
        JButton six = new JButton( "6" );
        JButton seven = new JButton( "7" );
        JButton height = new JButton( "8" );
        JButton nine = new JButton( "9" );
        JButton point = new JButton( "." );
        JButton sign = new JButton( "+/-" );
        JButton plus = new JButton( "+" );
        JButton minus = new JButton( "-" );
        JButton multiply = new JButton( "*" );
        JButton divide = new JButton( "/" );
        JButton equal = new JButton( "=" );
        JButton eraseAll = new JButton( "CE" );
        JButton eraseLine = new JButton( "C" );
        JButton eraseChar = new JButton( "BA" );

        one.setFocusable(false);
        two.setFocusable(false);
        three.setFocusable(false);
        four.setFocusable(false);
        five.setFocusable(false);
        six.setFocusable(false);
        seven.setFocusable(false);
        height.setFocusable(false);
        nine.setFocusable(false);
        zero.setFocusable(false);

        plus.setFocusable(false);
        minus.setFocusable(false);
        divide.setFocusable(false);
        multiply.setFocusable(false);
        sign.setFocusable(false);
        point.setFocusable(false);
        equal.setFocusable(false);
        eraseAll.setFocusable(false);
        eraseLine.setFocusable(false);
        eraseChar.setFocusable(false);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add( topTextArea, constraints );

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add( bottomTextArea, constraints );

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add( eraseAll, constraints );

        constraints.gridx = 1;
        panel.add( eraseLine, constraints );

        constraints.gridx = 2;
        panel.add( eraseChar, constraints );

        constraints.gridx = 3;
        panel.add( divide, constraints );

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add( seven, constraints );

        constraints.gridx = 1;
        panel.add( height, constraints );

        constraints.gridx = 2;
        panel.add( nine, constraints );

        constraints.gridx = 3;
        panel.add( multiply, constraints );

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add( four, constraints );

        constraints.gridx = 1;
        panel.add( five, constraints );

        constraints.gridx = 2;
        panel.add( six, constraints );

        constraints.gridx = 3;
        panel.add( minus, constraints );

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add( one, constraints );

        constraints.gridx = 1;
        panel.add( two, constraints );

        constraints.gridx = 2;
        panel.add( three, constraints );

        constraints.gridx = 3;
        panel.add( plus, constraints );

        constraints.gridx = 1;
        constraints.gridy = 6;
        panel.add( zero, constraints );

        constraints.gridx = 0;
        panel.add( sign, constraints );

        constraints.gridx = 2;
        panel.add( point, constraints );

        constraints.gridx = 3;
        panel.add( equal, constraints );

        window.getContentPane().add( panel );
        window.setVisible( true );

        eraseAll.addActionListener( actionEvent -> {
                eraseLine.doClick();
                if ( topTextArea.getText().length() != 0 ) topTextArea.setText( "" );
                equal.doClick();
        });

        eraseLine.addActionListener( actionEvent -> {
                if (bottomTextArea.getText().length() != 0) bottomTextArea.setText("");
                equal.doClick();
        });

        eraseChar.addActionListener( actionEvent -> {
                if ( bottomTextArea.getText().length() == 0 ) return;
                String line = bottomTextArea.getText();
                int endIndex = line.length() - 1;
                bottomTextArea.setText(line.substring( 0, endIndex));
                equal.doClick();
        } );

        one.addActionListener( actionEvent -> {
            textAreaAppendText(bottomTextArea, one.getText());
            equal.doClick();
        });

        two.addActionListener( actionEvent -> {
            textAreaAppendText( bottomTextArea, two.getText());
            equal.doClick();
        });

        three.addActionListener( actionEvent -> {
            textAreaAppendText( bottomTextArea, three.getText());
            equal.doClick();
        });

        four.addActionListener( actionEvent -> {
            textAreaAppendText( bottomTextArea, four.getText());
            equal.doClick();
        });

        five.addActionListener( actionEvent -> {
            textAreaAppendText( bottomTextArea, five.getText());
            equal.doClick();
        });

        six.addActionListener( actionEvent -> {
            textAreaAppendText( bottomTextArea, six.getText());
            equal.doClick();
        });

        seven.addActionListener( actionEvent -> {
            textAreaAppendText( bottomTextArea, seven.getText());
            equal.doClick();
        });

        height.addActionListener( actionEvent -> {
            textAreaAppendText( bottomTextArea, height.getText());
            equal.doClick();
        });

        nine.addActionListener( actionEvent -> {
            textAreaAppendText( bottomTextArea, nine.getText());
            equal.doClick();
        });

        zero.addActionListener( actionEvent -> {
            textAreaAppendText(bottomTextArea, zero.getText());
            equal.doClick();
        });

        equal.addActionListener( actionEvent -> doOperation(bottomTextArea.getText(), topTextArea));

        point.addActionListener( actionEvent -> {
                if (bottomTextArea.getText().contains(point.getText())) return;

                if (bottomTextArea.getText().length() == 0){
                    bottomTextArea.setText( zero.getText() + point.getText() );
                } else {
                    bottomTextArea.setText( bottomTextArea.getText() + point.getText() );
                }
                equal.doClick();
        } );

        plus.addActionListener( actionEvent -> {
            handleOperation(bottomTextArea, plus);
            equal.doClick();
        });

        minus.addActionListener( actionEvent -> {
            handleOperation(bottomTextArea, minus);
            equal.doClick();
        });

        multiply.addActionListener( actionEvent -> {
            handleOperation(bottomTextArea, multiply);
            equal.doClick();
        });

        divide.addActionListener( actionEvent -> {
            handleOperation(bottomTextArea, divide);
            equal.doClick();
        });

        bottomTextArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                char keyChar = keyEvent.getKeyChar();
                if (!isCharValid(keyEvent)) keyEvent.consume();
                switch (keyChar) {
                    case '\n' , '=' -> {
                        keyEvent.consume();
                        equal.doClick();
                    }
                    case '+' -> {
                        keyEvent.consume();
                        plus.doClick();
                    }
                    case '-' -> {
                        keyEvent.consume();
                        minus.doClick();
                    }
                    case '*' -> {
                        keyEvent.consume();
                        multiply.doClick();
                    }
                    case '/' -> {
                        keyEvent.consume();
                        divide.doClick();
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
            }
        });
    }

    public static void doOperation(String string, JTextArea textArea) {
        String symH = "*/", symL = "+-", symbols = symH.concat(symL);
        if (string.isBlank() || !includeAny(string, symbols)) return;

        if (symbols.contains(getLastChar(string))) return;

        textArea.setText(parse(string));

    }

    static String res = "";
    private static String parse(String string) {
        Matcher matcher = quotingPriorOp.matcher(string);

        while (matcher.find()) {
            res = matcher.replaceFirst(doMath(matcher.group()));
            matcher.reset(res);
        }
        System.out.println(res);

        Matcher matcher1 = pattern.matcher(res);

        while (matcher1.find()) {
            System.out.println("m1"+matcher1.group());
            res = matcher1.replaceFirst(doMath(matcher1.group()));
            matcher1.reset(res);
        }
        return res;
    }

    private static boolean includeAny(String string, String symbols) {
        for (int i = 0, len = symbols.length(); i < len; i++){
            if (string.contains(Character.toString(symbols.charAt(i)))) return true;
        }
        return false;
    }

    public static String doMath(String math){
        if (math.length() == 0) return "";
        
        String p = math.replaceAll(" ", "");

        String[] parts = p.split("[+*/-]");

        if (parts.length == 1) return math;

        double num1 = Double.parseDouble(parts[0]);
        double num2 = Double.parseDouble(parts[1]);
        double res = 0;
        char operation = p.charAt(parts[0].length());

        switch (operation) {
            case '+' -> res = num1 + num2;
            case '-' -> res = num1 - num2;
            case '*' -> res = num1 * num2;
            case '/' -> {
                if (num2 == 0) return "0";
                res = num1 / num2;
            }
        }

        return Double.toString(res);
    }

    private static String join(String[] strings, int index) {
        return Arrays.stream(strings).reduce("", String::concat).substring(index);
    }

    private static boolean isCharValid(KeyEvent keyEvent) {
        int keyChar = keyEvent.getKeyChar();
        int keyCode = KeyEvent.getExtendedKeyCodeForChar(keyChar);
        return (keyCode >= 45 && keyCode <= 57) || keyCode == 61 || keyChar == '\n' || keyChar == '\b';
    }

    /**
     * Handle the change to be done when we click on an operation sign
     * @param textArea Text Area that contains the operation string
     * @param jButton The actual button that was pressed
     */
    private static void handleOperation(JTextArea textArea, JButton jButton) {
        if (textArea.getText().length() == 0) return;
        String lastChar = getLastChar(textArea.getText());

        if ("+-/*".contains(lastChar)) {
            String text = textArea.getText();
            int endIndex = text.length() - 1;
            textArea.setText(text.substring(0, endIndex) + jButton.getText());
        }

        textArea.setText(textArea.getText() + jButton.getText());
    }

    /**
     * Retrieve the last character of a string or an empty string
     * @param string The string to parse
     * @return A single character
     */
    public static String getLastChar(String string) {
        if ( string.length() == 0 ) return "";
        int endIndex = string.length() - 1;
        return string.charAt( endIndex ) + "";
    }

    public static void textAreaAppendText(JTextArea textArea, String text) {
        textArea.setText( textArea.getText() + text );
    }
}
