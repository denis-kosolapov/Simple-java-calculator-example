package com.LPT.name;

import javax.swing.*;
import javax.xml.bind.Unmarshaller;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class Panel extends JPanel {

    private JButton numbers[] = new JButton[10];
    private Font font = new Font("SanSerif", Font.BOLD, 20);
    private JTextField output = new JTextField();
    private JButton backspace = new JButton("C"), equ = new JButton("=");
    private JButton plus = new JButton("+"), minus = new JButton("-"), multi = new JButton("*"), div = new JButton("/");
    private Double firstValue;
    private String operation;


    public Panel() {
        setLayout(null);
        setFocusable(true);
        grabFocus();

        backspace.setBounds(10, 250, 50, 50);
        backspace.setFont(font);
        add(backspace);

        plus.setBounds(190, 70, 50, 50);
        plus.setFont(font);
        add(plus);

        minus.setBounds(190, 130, 50, 50);
        minus.setFont(font);
        add(minus);

        multi.setBounds(190, 190, 50, 50);
        multi.setFont(font);
        add(multi);

        div.setBounds(190, 250, 50, 50);
        div.setFont(font);
        add(div);

        equ.setBounds(130, 250, 50, 50);
        equ.setFont(font);
        add(equ);

        numbers[0] = new JButton("0");
        numbers[0].setBounds(70, 250, 50, 50);
        numbers[0].setFont(font);
        add(numbers[0]);

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                numbers[x * 3 + y + 1] = new JButton((x * 3 + y + 1) + "");
                numbers[x * 3 + y + 1].setBounds(x * (50 + 10) + 10, y * (50 + 10) + 70, 50, 50);
                numbers[x * 3 + y + 1].setFont(font);
                add(numbers[x * 3 + y + 1]);
            }
        }

        output.setBounds(10, 10, 230, 50);
        output.setFont(font);
        output.setEditable(false);
        add(output);

        ActionListener l = (ActionEvent e) -> {
            JButton b = (JButton) e.getSource();
            output.setText(output.getText() + b.getText());
        };

        for (JButton b : numbers) {
            b.addActionListener(l);
        }

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char symbol = e.getKeyChar();

                if (!Character.isDigit(symbol))
                    return;
                else output.setText(output.getText() + symbol);
            }
        });


        backspace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try { //если не в курсе что это, посмотрите блок try/catch, он обрабатывает ошибки
                    String temp = output.getText(); //temp получает доступ к полю
                    output.setText(temp.substring(0, temp.length() - 1)); //substring (1 значение, это начальное,
                    // второе это конечное), length определяет длинну, -1 убирает последний символ
                } catch (Exception b) {
                    System.out.println("Значение отсутстует"); //хотелось бы вывести в окно, но это позже.
                }
            }
        });



        //Далее добавляем действия с операциями, но перед этим нужно создать две переменные, одно значение double
        // это первое введенное число "firstValue" другое строковое, будет операцией.
        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstValue = Double.valueOf(output.getText()); /* в начале было Integer.valueOf возвращает int значение,
                   которое мы получаем из output, но int значение ни о чем, нам же нужно, чтобы было десятичное значение,
                    так что поменял все после Double*/
                output.setText(""); //таким образом мы затираем текст, введенный ранее, тоесть устанавливаем пустую строку.
                operation = "+"; // собственно, сама операция +
            }
        });
        minus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstValue = Double.valueOf(output.getText());
                output.setText("");
                operation = "-";
            }
        });
        multi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstValue = Double.valueOf(output.getText());
                output.setText("");
                operation = "*";
            }
        });
        div.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstValue = Double.valueOf(output.getText());
                output.setText("");
                operation = "/";
            }
        });



        equ.addActionListener(new ActionListener() { // операция равно
            @Override
            public void actionPerformed(ActionEvent e) {
                double secondValue = Double.valueOf(output.getText()); /* также используем double, valueOf возвращает
                    десятичное значение, передаем в параметр полечение текста в поле */
                if ("+".equals(operation)) {
                    output.setText((firstValue + secondValue) + "");
                }
                if ("-".equals(operation)) {
                    output.setText((firstValue - secondValue) + "");
                }
                if ("*".equals(operation)) {
                    output.setText((firstValue * secondValue) + "");
                }
                if ("/".equals(operation)) {
                    output.setText((firstValue / secondValue) + "");
                }
                firstValue = Double.valueOf(0);
                operation = "+";
            }
        });
    }
}
