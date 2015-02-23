package lab4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.*;

public class IASComputer extends JFrame {

    JPanel panel;
    JScrollPane scr;
    JTextArea memoryPane;
    JTextPane descPane;
    JLabel lblAc, lblMq, lblMar, lblMbr, lblPc, lblIr, lblIbr;
    JRadioButton[] acLightsLeft, acLightsRight, mqLightsLeft, mqLightsRight,
            mbrLightsLeft, mbrLightsRight, pcLights, marLights, ibrLights,
            irLights;
    JButton btnRun;
    Memory memory;

    long ac, mq, mbr, ibr;
    int pc, mar, ir;
    boolean left, ibrLoad, run;
    String description;

    public static void main(String[] args) {
        new IASComputer();

    }

    public IASComputer() {
        init();

        memory = new Memory(100);
        
        btnRun.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {

                String[] input = memoryPane.getText().split("\\n");

                for (int i = 0; i < input.length; i++) {
                    memory.setMemory(i, Long.parseLong(input[i], 16));
                }

                memoryPane.setText(memory.toString());
                memoryPane.setCaretPosition(0);
                memoryPane.setEditable(false);

                //run();
                
                memoryPane.setText(memory.toString());
                memoryPane.setCaretPosition(0);
                memoryPane.setEditable(true);
            }

        });
    }

    private void init() {
        // panel attributes
        this.setSize(600, 400);
        this.setLocation(0, 0);
        this.setResizable(false);
        this.setTitle("Application");
        panel = new JPanel(null);

        scr = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        scr.setBounds(440, 10, 150, 350);
        panel.add(scr);
        memoryPane = new JTextArea(15, 20);
        scr.setViewportView(memoryPane);
        memoryPane.setLineWrap(true);
        memoryPane.setWrapStyleWord(true);

        getContentPane().add(panel);

        descPane = new JTextPane();
        descPane.setBounds(10, 340, 425, 20);
        panel.add(descPane);

        lblAc = new JLabel("AC");
        lblAc.setBounds(10, 145, 40, 15);
        panel.add(lblAc);

        lblMq = new JLabel("MQ");
        lblMq.setBounds(10, 200, 40, 15);
        panel.add(lblMq);

        lblMbr = new JLabel("MBR");
        lblMbr.setBounds(10, 10, 40, 15);
        panel.add(lblMbr);

        lblIbr = new JLabel("IBR");
        lblIbr.setBounds(10, 65, 40, 15);
        panel.add(lblIbr);

        lblPc = new JLabel("PC");
        lblPc.setBounds(10, 255, 40, 15);
        panel.add(lblPc);

        lblIr = new JLabel("IR");
        lblIr.setBounds(10, 105, 40, 15);
        panel.add(lblIr);

        lblMar = new JLabel("MAR");
        lblMar.setBounds(180, 105, 40, 15);
        panel.add(lblMar);

        btnRun = new JButton("Run");
        btnRun.setBounds(340, 305, 90, 25);
        panel.add(btnRun);

        mbrLightsLeft = new JRadioButton[20];
        for (int i = 0; i < mbrLightsLeft.length; i++) {
            mbrLightsLeft[i] = new JRadioButton("");
            mbrLightsLeft[i].setBounds((i * 21 + 10), 25, 25, 25);
            panel.add(mbrLightsLeft[i]);
        }

        mbrLightsRight = new JRadioButton[20];
        for (int i = 0; i < mbrLightsRight.length; i++) {
            mbrLightsRight[i] = new JRadioButton("");
            mbrLightsRight[i].setBounds((i * 21 + 10), 47, 25, 25);
            panel.add(mbrLightsRight[i]);
        }

        ibrLights = new JRadioButton[20];
        for (int i = 0; i < ibrLights.length; i++) {
            ibrLights[i] = new JRadioButton("");
            ibrLights[i].setBounds((i * 21 + 10), 80, 25, 25);
            panel.add(ibrLights[i]);
        }

        irLights = new JRadioButton[8];
        for (int i = 0; i < irLights.length; i++) {
            irLights[i] = new JRadioButton("");
            irLights[i].setBounds((i * 21 + 10), 120, 25, 25);
            panel.add(irLights[i]);
        }

        marLights = new JRadioButton[12];
        for (int i = 0; i < marLights.length; i++) {
            marLights[i] = new JRadioButton("");
            marLights[i].setBounds((i * 21 + 178), 120, 25, 25);
            panel.add(marLights[i]);
        }

        acLightsLeft = new JRadioButton[20];
        for (int i = 0; i < acLightsLeft.length; i++) {
            acLightsLeft[i] = new JRadioButton("");
            acLightsLeft[i].setBounds((i * 21 + 10), 160, 25, 25);
            panel.add(acLightsLeft[i]);
        }

        acLightsRight = new JRadioButton[20];
        for (int i = 0; i < acLightsRight.length; i++) {
            acLightsRight[i] = new JRadioButton("");
            acLightsRight[i].setBounds((i * 21 + 10), 182, 25, 25);
            panel.add(acLightsRight[i]);
        }

        mqLightsLeft = new JRadioButton[20];
        for (int i = 0; i < mqLightsLeft.length; i++) {
            mqLightsLeft[i] = new JRadioButton("");
            mqLightsLeft[i].setBounds((i * 21 + 10), 215, 25, 25);
            panel.add(mqLightsLeft[i]);
        }

        mqLightsRight = new JRadioButton[20];
        for (int i = 0; i < mqLightsRight.length; i++) {
            mqLightsRight[i] = new JRadioButton("");
            mqLightsRight[i].setBounds((i * 21 + 10), 237, 25, 25);
            panel.add(mqLightsRight[i]);
        }

        pcLights = new JRadioButton[8];
        for (int i = 0; i < pcLights.length; i++) {
            pcLights[i] = new JRadioButton("");
            pcLights[i].setBounds((i * 21 + 10), 270, 25, 25);
            panel.add(pcLights[i]);
        }

        this.setVisible(true);
        
        ac = mq = mbr = ibr = ir = pc = mar = 0;

        left = true;
        ibrLoad = false;
        run = true;
        description = "";
    }
}
