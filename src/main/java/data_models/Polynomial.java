package data_models;

import java.util.ArrayList;

public class Polynomial {
    private ArrayList<Monomial> monomials;

    public Polynomial(){
        this.monomials = new ArrayList<>();
    }
    //polinom initializat cu 0
    public Polynomial(int zero){
        Monomial monomial = new Monomial(0, 0);
        this.monomials = new ArrayList<>();
        this.monomials.add(monomial);
    }

    public void addMember(Monomial monomial){
        this.monomials.add(monomial);
    }

    public ArrayList<Monomial> getMonomials() {
        return monomials;
    }
    public void setMonomials(ArrayList<Monomial> monomials) {
        this.monomials = monomials;
    }

    public void showPolynomial(){
        System.out.println("{ ");
        for(Monomial m : monomials){
            System.out.println(m.toString());
        }
        System.out.println("}\n");
    }

    public String toString(char operation){
        String polynomial = "";
        String sign = "";

        for(Monomial m : monomials){
            if(operation == '/' && m.getCoefficient() == 0 && m.getPower() == 0){
                polynomial += ";  rest = ";
            }
            else{
                if(m.getCoefficient() > 0){
                    sign = " +";
                }
                else if (m.getCoefficient() < 0){
                    sign = " ";
                }
                else continue;
                polynomial += sign + m.toString();
            }
        }
        if(operation == 'I'){
            polynomial += " +c";
        }
        return polynomial;
    }

}

