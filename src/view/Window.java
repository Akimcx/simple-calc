package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Button extends JButton{

    String text;

    Button(String text) {
        super(text);
        this.text = text;
        this.setFocusable(false);
    }
}

public class Window {

    static Pattern quotingPriorOp = Pattern.compile("\\d+(\\.\\d+)?[*/]\\d+(\\.\\d+)?");
    static Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?[+-]\\d+(\\.\\d+)?");

    public static void main(String[] args) {
        JFrame window = new JFrame("Calculator");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(300, 550);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        JPanel panel = new JPanel(new GridBagLayout());

        // Text Area
        JTextArea topTextArea = new JTextArea(1, 2);
        topTextArea.setEnabled(false);
        topTextArea.setDisabledTextColor(Color.gray);
        JTextArea bottomTextArea = new JTextArea(1, 2);

        // Buttons
        Button zero = new Button("0");
        Button one = new Button("1");
        Button two = new Button("2");
        Button three = new Button("3");
        Button four = new Button("4");
        Button five = new Button("5");
        Button six = new Button("6");
        Button seven = new Button("7");
        Button height = new Button("8");
        Button nine = new Button("9");
        Button point = new Button(".");
        Button sign = new Button("+/-");
        Button plus = new Button("+");
        Button minus = new Button("-");
        Button multiply = new Button("*");
        Button divide = new Button("/");
        Button equal = new Button("=");
        Button eraseAll = new Button("CE");
        Button eraseLine = new Button("C");
        Button eraseChar = new Button("BA");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(topTextArea, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(bottomTextArea, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(eraseAll, constraints);

        constraints.gridx = 1;
        panel.add(eraseLine, constraints);

        constraints.gridx = 2;
        panel.add(eraseChar, constraints);

        constraints.gridx = 3;
        panel.add(divide, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(seven, constraints);

        constraints.gridx = 1;
        panel.add(height, constraints);

        constraints.gridx = 2;
        panel.add(nine, constraints);

        constraints.gridx = 3;
        panel.add(multiply, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(four, constraints);

        constraints.gridx = 1;
        panel.add(five, constraints);

        constraints.gridx = 2;
        panel.add(six, constraints);

        constraints.gridx = 3;
        panel.add(minus, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(one, constraints);

        constraints.gridx = 1;
        panel.add(two, constraints);

        constraints.gridx = 2;
        panel.add(three, constraints);

        constraints.gridx = 3;
        panel.add(plus, constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        panel.add(zero, constraints);

        constraints.gridx = 0;
        panel.add(sign, constraints);

        constraints.gridx = 2;
        panel.add(point, constraints);

        constraints.gridx = 3;
        panel.add(equal, constraints);

        window.getContentPane().add(panel);
        window.setVisible(true);

        eraseAll.addActionListener(actionEvent -> {
            eraseLine.doClick();
            if (topTextArea.getText().length() != 0) topTextArea.setText("");
            equal.doClick();
        });

        eraseLine.addActionListener(actionEvent -> {
            if (bottomTextArea.getText().length() != 0) bottomTextArea.setText("");
            equal.doClick();
        });

        eraseChar.addActionListener(actionEvent -> {
            if (bottomTextArea.getText().length() == 0) return;
            String line = bottomTextArea.getText();
            int endIndex = line.length() - 1;
            bottomTextArea.setText(line.substring(0, endIndex));
            equal.doClick();
        });

        one.addActionListener(actionEvent -> {
//            controller.handleClick(one.getText());
            textAreaAppendText(bottomTextArea, one.getText());
            equal.doClick();
        });

        two.addActionListener(actionEvent -> {
            textAreaAppendText(bottomTextArea, two.getText());
            equal.doClick();
        });

        three.addActionListener(actionEvent -> {
            textAreaAppendText(bottomTextArea, three.getText());
            equal.doClick();
        });

        four.addActionListener(actionEvent -> {
            textAreaAppendText(bottomTextArea, four.getText());
            equal.doClick();
        });

        five.addActionListener(actionEvent -> {
            textAreaAppendText(bottomTextArea, five.getText());
            equal.doClick();
        });

        six.addActionListener(actionEvent -> {
            textAreaAppendText(bottomTextArea, six.getText());
            equal.doClick();
        });

        seven.addActionListener(actionEvent -> {
            textAreaAppendText(bottomTextArea, seven.getText());
            equal.doClick();
        });

        height.addActionListener(actionEvent -> {
            textAreaAppendText(bottomTextArea, height.getText());
            equal.doClick();
        });

        nine.addActionListener(actionEvent -> {
            textAreaAppendText(bottomTextArea, nine.getText());
            equal.doClick();
        });

        zero.addActionListener(actionEvent -> {
            textAreaAppendText(bottomTextArea, zero.getText());
            equal.doClick();
        });

        equal.addActionListener(actionEvent -> doOperation(bottomTextArea.getText(), topTextArea));

        point.addActionListener(actionEvent -> {
            if (bottomTextArea.getText().contains(point.getText())) return;

            if (bottomTextArea.getText().length() == 0) {
                bottomTextArea.setText(zero.getText() + point.getText());
            } else {
                bottomTextArea.setText(bottomTextArea.getText() + point.getText());
            }
            equal.doClick();
        });

        plus.addActionListener(actionEvent -> {
            handleOperation(bottomTextArea, plus);
            equal.doClick();
        });

        minus.addActionListener(actionEvent -> {
            handleOperation(bottomTextArea, minus);
            equal.doClick();
        });

        multiply.addActionListener(actionEvent -> {
            handleOperation(bottomTextArea, multiply);
            equal.doClick();
        });

        divide.addActionListener(actionEvent -> {
            handleOperation(bottomTextArea, divide);
            equal.doClick();
        });

        bottomTextArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

                char keyChar = keyEvent.getKeyChar();
                if (!isCharValid(keyEvent)) keyEvent.consume();
                switch (keyChar) {
                    case '\n', '=' -> {
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
                equal.doClick();
            }
        });

    }

    public static void doOperation(String string, JTextArea textArea) {
        String symH = "*/", symL = "+-", symbols = symH.concat(symL);
        if (string.isBlank() || !includeAny(string, symbols)) return;

        if (symbols.contains(getLastChar(string))) return;

//        textArea.setText(new DecimalFormat("0.#").format(Double.parseDouble(parse(string))));
        textArea.setText(parse(string));
    }

    private static String parse(String string) {
        Matcher matcher = quotingPriorOp.matcher(string);

        while (matcher.find()) {
            string = matcher.replaceFirst(doMath(matcher.group()));
            matcher.reset(string);
        }
        Matcher matcher1 = pattern.matcher(string);
        while (matcher1.find()) {
            string = matcher1.replaceFirst(doMath(matcher1.group()));
            matcher1.reset(string);
        }
        System.out.println("Hello " + string);
        return string;
    }

    private static boolean includeAny(String string, String symbols) {
        for (int i = 0, len = symbols.length(); i < len; i++) {
            if (string.contains(Character.toString(symbols.charAt(i)))) return true;
        }
        return false;
    }

    public static String doMath(String math) {
        if (math.length() == 0) return "";

        String p = math.replaceAll(" ", "");

        String[] parts = p.split("[+*/-]");

        if (parts.length == 1) return math;

        double num1 = Double.parseDouble(parts[0]);
        double num2 = Double.parseDouble(parts[1]);
        char operation = p.charAt(parts[0].length());

        return Double.toString(switch (operation) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> {
                if (num2 == 0)
                    throw new ArithmeticException("Division by zero");
                else
                    yield num1 / num2;
            }
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        });
    }

    private static boolean isCharValid(KeyEvent keyEvent) {
        int keyChar = keyEvent.getKeyChar();
        int keyCode = KeyEvent.getExtendedKeyCodeForChar(keyChar);
        return (keyCode >= 45 && keyCode <= 57) || keyCode == 61 || keyChar == '\n' || keyChar == '\b';
    }

    /**
     * Handle the change to be done when we click on an operation sign
     *
     * @param textArea Text Area that contains the operation string
     * @param jButton  The actual button that was pressed
     */
    private static void handleOperation(JTextArea textArea, JButton jButton) {
        String areaText = textArea.getText();
        if (areaText.length() == 0) return;

        int caretPos = textArea.getCaretPosition();
        String buttonText = jButton.getText();

        String charBeforeCaret = getCharRelativeToCaretPos(areaText, caretPos - 1);
        String charAfterCaret = getCharRelativeToCaretPos(areaText, caretPos);

        if ("+-/*".contains(charBeforeCaret)) {
            textArea.select(caretPos - 1,caretPos);
            textArea.replaceSelection(buttonText);
            return;
        }

        if("+-/*".contains(charAfterCaret)) {
            textArea.select(caretPos,caretPos + 1);
            textArea.replaceSelection(buttonText);
            return;
        }

        textArea.insert(buttonText, caretPos);

    }

    /**
     *
     * @param string The haystack
     * @param pos Where to search it
     * @return the char found or None if not
     */
    private static String getCharRelativeToCaretPos(String string, int pos) {
        try {
            return Character.toString(string.charAt(pos));
        } catch (IndexOutOfBoundsException exception) {
            return "None";
        }
    }

    /**
     * Retrieve the last character of a string or an empty string
     *
     * @param string The string to parse
     * @return A single character
     */
    public static String getLastChar(String string) {
        if (string.length() == 0) return "";
        int endIndex = string.length() - 1;
        return Character.toString(string.charAt(endIndex));
    }

    public static void textAreaAppendText(JTextArea textArea, String text) {
        textArea.setText(textArea.getText() + text);
    }
}
