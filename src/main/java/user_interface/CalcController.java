package user_interface;

import data_models.CalcModel;
import data_models.Monomial;
import data_models.Polynomial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.*;

public class CalcController {

    private CalcModel m_model;
    private CalcView m_view;

    public CalcController(){
        m_model = new CalcModel();
        m_view = new CalcView(m_model);
    }

    public CalcController(CalcView view, CalcModel model){
        m_view = view;
        m_model = model;

        m_view.addAddListener(new AddListener());
        m_view.addSubtractListener(new SubtractListener());
        m_view.addMultiplyListener(new MultiplyListener());
        m_view.addDivideListener(new DivideListener());
        m_view.addDerivationListener(new DerivationListener());
        m_view.addIntegrationListener(new IntegrationListener());
        m_view.addClearListener(new ClearListener());
    }

    public Polynomial readPolynomial(String polynomial) {
        Polynomial newPolynomial = new Polynomial();

        int power = 0;
        int coefficient = 0;
        int sign = 1;

        Pattern p = Pattern.compile( "((-?\\d+(?=x))?(-?[xX])(\\^(-?\\d+))?)|((-?)[xX])|(-?\\d+)");
        Matcher m = p.matcher(polynomial);

        while (m.find()) {
            //daca exista variabila
            if (m.group(3) != null) {
                //adaugam puterea daca exista, daca nu, e 1
                power = (m.group(5) != null ? Integer.parseInt(m.group(5)) : 1);
                //adaugam coeficientul daca exista, daca nu, e 1 sau -1, in functie de ce citeste la grupul 3 al Regexului
                sign = (m.group(3).compareTo("x") == 0 ? 1 : -1);
                coefficient = sign * (m.group(2) != null ? Integer.parseInt(m.group(2)) : 1);
            }
            else {
                //exista doar coeficientul
                coefficient = Integer.parseInt(m.group());
                power = 0;
            }
            Monomial newMonomial = new Monomial(0, 0);
            newMonomial.setPower(power);
            newMonomial.setCoefficient(coefficient);
            newPolynomial.addMember(newMonomial);
        }
        return newPolynomial;
    }

    public void setPolynomials(){

        String polynomial = m_view.getFirstPolynomial();
        if(polynomial.compareTo("") == 0){
            m_model.setFirstPolynomial(new Polynomial(0));
        }
        else{
            Polynomial polynomial1 = readPolynomial(polynomial);
            m_model.setFirstPolynomial(polynomial1);
        }

        polynomial = m_view.getSecondPolynomial();
        if(polynomial.compareTo("") == 0){
            m_model.setSecondPolynomial(new Polynomial(0));
        }
        else{
            Polynomial polynomial2 = readPolynomial(polynomial);
            m_model.setSecondPolynomial(polynomial2);
        }
    }
    public void checkValidData(char operation){
        if(m_view.seeIfEmpty(1) && m_view.seeIfEmpty(2)){
            m_view.showError("Enter at least 1 polynomial!");
            m_view.clearText();
        }
        else if (operation == '/' && m_view.seeIfEmpty(2)) {
            m_view.showError("Cannot divide by 0!");
            m_view.clearText();
        }
        else if (m_model.getFirstPolynomial().isEmpty() || m_model.getSecondPolynomial().isEmpty()) {
            m_view.showError("Enter valid data!");
            m_view.clearText();
        }
        else if(operation == '/' && m_model.getFirstPolynomial().get(0).getPower() < m_model.getSecondPolynomial().get(0).getPower()){
            m_view.showError("Grade of second polynomial cannot be greater than that of the first!");
        }
        else{
            Polynomial result = m_model.doOperation(operation);
            m_view.setResult(result.toString(operation));
        }
    }

    class AddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            setPolynomials();
            checkValidData('+');
        }
    }

    class SubtractListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setPolynomials();
            checkValidData('-');
        }
    }

    class MultiplyListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            setPolynomials();
            checkValidData('*');
        }
    }

    class DivideListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            setPolynomials();
            checkValidData('/');
        }
    }

    class DerivationListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            setPolynomials();
            checkValidData('D');
        }
    }

    class IntegrationListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            setPolynomials();
            checkValidData('I');
        }
    }

    class ClearListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            m_view.clearText();
        }
    }

}
