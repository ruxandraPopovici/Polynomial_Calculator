package data_models;

import business_logic.Operations;

import java.util.ArrayList;

public class CalcModel {

    private ArrayList<Monomial> firstPolynomial;
    private ArrayList<Monomial> secondPolynomial;
    Operations operations;

    public CalcModel(){
        this.operations = new Operations();
    }

    public ArrayList<Monomial> getFirstPolynomial() {
        return this.firstPolynomial;
    }
    public void setFirstPolynomial(Polynomial firstPolynomial) {
        this.firstPolynomial = firstPolynomial.getMonomials();
    }

    public ArrayList<Monomial> getSecondPolynomial() {
        return this.secondPolynomial;
    }
    public void setSecondPolynomial(Polynomial secondPolynomial) {
        this.secondPolynomial = secondPolynomial.getMonomials();
    }

    public Polynomial doOperation(char operation){
        switch(operation) {
            case '+' :
                return operations.add(firstPolynomial, secondPolynomial);

            case '-' :
                return operations.subtract(firstPolynomial, secondPolynomial);

            case '*' :
                return operations.multiply(firstPolynomial, secondPolynomial);

            case '/' :
                return operations.divide(firstPolynomial, secondPolynomial);

            case 'D' :
                return operations.derive(firstPolynomial, secondPolynomial);

            case 'I' :
                return operations.integrate(firstPolynomial, secondPolynomial);

            default:
        }

        return new Polynomial(0);
    }

}

