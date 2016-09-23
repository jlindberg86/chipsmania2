/*
 * The MIT License
 *
 * Copyright 2016 Johan.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.manabreak.libclicker;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Johan
 */
public class test extends JFrame {
    private final JFrame mainFrame;
    private final JPanel mainPanel;
    private JLabel currencyText = new JLabel();
    private final JButton generateGold;
    
    public test () {
        //main window
        super("Best clicker game out there");
        World world = new World();
        world.update(1.0 / 60.0);
        mainFrame = new JFrame();
        setSize(1000,700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        generateGold = new JButton();
        
        Currency gold = new Currency.Builder(world)
                .name("Gold")
                .build();   
        currencyText.setText(gold.getAmountAsString());
        
        Generator goldMine = new Generator.Builder(world)
                .generate(gold)
                .baseAmount(1)
                .multiplier(1.15)
                .price(100)
                .priceMultiplier(1.25)
                .build();
        
        goldMine.upgrade();
        
        add(mainPanel);
        mainPanel.add(currencyText, BorderLayout.CENTER);
        mainPanel.add(generateGold, BorderLayout.SOUTH);
        
        generateGold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goldMine.process();
                currencyText.setText(gold.getAmountAsString());
            }
        });
    }
}
