package user_interface;

import data_models.CalcModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalcView extends JFrame{

    private JLabel m_title = new JLabel("Polynomial Calculator");
    private JLabel m_firstLabel = new JLabel("First Polynomial = ");
    private JLabel m_secondLabel = new JLabel("Second Polynomial = ");
    private JLabel m_resultLabel = new JLabel("Result = ");

    private JTextField m_firstText = new JTextField(20);
    private JTextField m_secondText = new JTextField(20);
    private JTextField m_resultText = new JTextField(40);

    private JButton m_multiply = new JButton("Multiply");
    private JButton m_subtract = new JButton("Subtract");
    private JButton m_divide = new JButton("Divide");
    private JButton m_derive = new JButton("Derive");
    private JButton m_integrate = new JButton("Integrate");
    private JButton m_add = new JButton("Add");
    private JButton m_clear = new JButton("Clear");

    private CalcModel m_model;

    private JPanel content = new JPanel();
    private JPanel content1 = new JPanel();
    private JPanel content2 = new JPanel();
    private JPanel content3 = new JPanel();
    private JPanel content4 = new JPanel();

    public CalcView(CalcModel model){
        m_model = model;

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        content1.setLayout(new FlowLayout(FlowLayout.CENTER));
        content1.add(m_title);

        JPanel firstPL = new JPanel();
        firstPL.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel secondPL = new JPanel();
        secondPL.setLayout(new FlowLayout(FlowLayout.CENTER));

        firstPL.add(m_firstLabel);
        secondPL.add(m_secondLabel);

        JPanel firstPT = new JPanel();
        firstPT.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel secondPT = new JPanel();
        secondPT.setLayout(new FlowLayout(FlowLayout.CENTER));

        firstPT.add(m_firstText);
        secondPT.add(m_secondText);

        JPanel firstP = new JPanel();
        JPanel secondP = new JPanel();

        firstP.add(firstPL);
        firstP.add(firstPT);

        secondP.add(secondPL);
        secondP.add(secondPT);

        content2.add(firstP);
        content2.add(secondP);

        JPanel buttonsLeft = new JPanel();
        buttonsLeft.setLayout(new BoxLayout(buttonsLeft, BoxLayout.Y_AXIS));

        buttonsLeft.add(m_add);
        buttonsLeft.add(m_multiply);
        buttonsLeft.add(m_derive);

        JPanel buttonsRight = new JPanel();
        buttonsRight.setLayout(new BoxLayout(buttonsRight, BoxLayout.Y_AXIS));
        buttonsRight.add(m_subtract);
        buttonsRight.add(m_divide);
        buttonsRight.add(m_integrate);

        content3.add(buttonsLeft);
        content3.add(buttonsRight);

        content4.setLayout(new FlowLayout(FlowLayout.CENTER));
        content4.add(m_resultLabel);
        content4.add(m_resultText);

        content.add(content1);
        content.add(content2);
        content.add(content3);
        content.add(content4);
        m_clear.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(m_clear);

        this.setContentPane(content);
        this.pack();

        this.setTitle("Polynomial Calculator");
        this.setSize(740, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String getFirstPolynomial(){
        return this.m_firstText.getText();
    }
    public String getSecondPolynomial(){
        return this.m_secondText.getText();
    }

    public void addAddListener(ActionListener add){
        this.m_add.addActionListener(add);
    }
    public void addSubtractListener(ActionListener sub){
        this.m_subtract.addActionListener(sub);
    }
    public void addMultiplyListener(ActionListener mul){
        this.m_multiply.addActionListener(mul);
    }
    public void addDivideListener(ActionListener div){
        this.m_divide.addActionListener(div);
    }
    public void addDerivationListener(ActionListener der){
        this.m_derive.addActionListener(der);
    }
    public void addIntegrationListener(ActionListener integrate){
        this.m_integrate.addActionListener(integrate);
    }
    public void addClearListener(ActionListener clr){
        this.m_clear.addActionListener(clr);
    }
    public void setResult(String result){
        m_resultText.setText(result);
    }
    public void clearText(){
        m_resultText.setText("");
        m_firstText.setText("");
        m_secondText.setText("");
    }
    public void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
    public boolean seeIfEmpty(int whichTextField){
        if(whichTextField == 1){
            return m_firstText.getText().isEmpty();
        }
        else{
            return m_secondText.getText().isEmpty();
        }
    }

}

