package business_logic;

import data_models.Monomial;
import data_models.Polynomial;

import java.util.*;

public class Operations {

    public Polynomial add(ArrayList<Monomial> firstPolynomial, ArrayList<Monomial> secondPolynomial){
        Polynomial result = new Polynomial();

        int i = 0;
        int j = 0;

        while(i < firstPolynomial.size() && j < secondPolynomial.size()){
            if(firstPolynomial.get(i).getPower() > secondPolynomial.get(j).getPower()){
                result.addMember(firstPolynomial.get(i));
                i++;
            }
            else if(firstPolynomial.get(i).getPower() < secondPolynomial.get(j).getPower()){
                result.addMember(secondPolynomial.get(j));
                j++;
            }
            else{
                Monomial newMonomial = new Monomial(firstPolynomial.get(i).getPower(),
                        firstPolynomial.get(i).getCoefficient() + secondPolynomial.get(j).getCoefficient());
                result.addMember(newMonomial);
                i++;
                j++;
            }
        }
        while(i < firstPolynomial.size()){
            result.addMember(firstPolynomial.get(i));
            i++;
        }
        while(j < secondPolynomial.size()){
            result.addMember(secondPolynomial.get(j));
            j++;
        }
        return result;
    }

    public Polynomial subtract(ArrayList<Monomial> firstPolynomial, ArrayList<Monomial> secondPolynomial){
        Polynomial result = new Polynomial();

        int i = 0;
        int j = 0;

        while(i < firstPolynomial.size() && j < secondPolynomial.size()){
            if(firstPolynomial.get(i).getPower() > secondPolynomial.get(j).getPower()){
                result.addMember(firstPolynomial.get(i));
                i++;
            }
            else if(firstPolynomial.get(i).getPower() < secondPolynomial.get(j).getPower()){
                Monomial newMonomial = new Monomial(secondPolynomial.get(j).getPower(),
                        (-1)*(secondPolynomial.get(j).getCoefficient()));
                result.addMember(newMonomial);
                j++;
            }
            else{
                Monomial newMonomial = new Monomial(firstPolynomial.get(i).getPower(),
                        firstPolynomial.get(i).getCoefficient() - secondPolynomial.get(j).getCoefficient());
                result.addMember(newMonomial);
                i++;
                j++;
            }
        }
        while(i < firstPolynomial.size()){
            result.addMember(firstPolynomial.get(i));
            i++;
        }
        while(j < secondPolynomial.size()){
            Monomial m = secondPolynomial.get(j);
            m.setCoefficient(m.getCoefficient() * (-1));
            result.addMember(m);
            j++;
        }
        return result;
    }

    public Polynomial multiply(ArrayList<Monomial> firstPolynomial, ArrayList<Monomial> secondPolynomial){
        Polynomial result = new Polynomial();

        ArrayList<Monomial> aux = result.getMonomials();
        for(Monomial monomial1 : firstPolynomial){
            for(Monomial monomial2 : secondPolynomial){
                int newPower = monomial1.getPower() + monomial2.getPower();
                double newCoefficient = monomial1.getCoefficient() * monomial2.getCoefficient();
                Monomial newMonomial = new Monomial(newPower, newCoefficient);
                aux.add(newMonomial);
            }
        }
        int i = 0;
        int j;
        while(i < aux.size()){
            j = i + 1;
            while(j < aux.size()){
                if(aux.get(i).getPower() == aux.get(j).getPower()){
                    aux.get(i).setCoefficient(aux.get(i).getCoefficient() + aux.get(j).getCoefficient());
                    aux.remove(j);
                }
                j++;
            }
            i++;
        }
        result.setMonomials(aux);
        return result;
    }

    public Polynomial divide(ArrayList<Monomial> firstPolynomial, ArrayList<Monomial> secondPolynomial){
        Polynomial result = new Polynomial();

        ArrayList<Monomial> dividend = firstPolynomial;
        ArrayList<Monomial> remainder;
        ArrayList<Monomial> multiplyFactor = new ArrayList<>();
        double degreeOfRemainder = secondPolynomial.get(0).getCoefficient() + 1;

        while(degreeOfRemainder >= secondPolynomial.get(0).getPower()){
            int newPower = dividend.get(0).getPower() - secondPolynomial.get(0).getPower();
            double newCoefficient = dividend.get(0).getCoefficient() / secondPolynomial.get(0).getCoefficient();
            Monomial res = new Monomial(newPower, newCoefficient);
            result.addMember(res);
            multiplyFactor.add(new Monomial(res.getPower(), -res.getCoefficient()));
            remainder = multiply(multiplyFactor, secondPolynomial).getMonomials();
            multiplyFactor.remove(0);
            dividend = add(dividend, remainder).getMonomials();
            int i = 0;
            while(i < dividend.size()){
                if(dividend.get(i).getCoefficient() == 0) {
                    dividend.remove(i);
                }
                i++;
            }
            degreeOfRemainder = (dividend.isEmpty() ?  0 : dividend.get(0).getPower());
        }
        result.addMember(new Monomial(0, 0));
        int i = 0;
        while(i < dividend.size()){
            result.addMember(dividend.get(i));
            i++;
        }
        return result;
    }

    public Polynomial derive(ArrayList<Monomial> firstPolynomial, ArrayList<Monomial> secondPolynomial){
        Polynomial result = new Polynomial();
        ArrayList<Monomial> aux;

        if(firstPolynomial.isEmpty() || firstPolynomial.get(0).getCoefficient() == 0){
            aux = secondPolynomial;
        }
        else{
            aux = firstPolynomial;
        }

        for(Monomial m : aux){
            double newCoefficient = m.getPower() * m.getCoefficient();
            int newPower = m.getPower() - 1;
            Monomial newMonomial = new Monomial(newPower, newCoefficient);
            result.addMember(newMonomial);
        }
        return result;
    }

    public Polynomial integrate(ArrayList<Monomial> firstPolynomial, ArrayList<Monomial> secondPolynomial){
        Polynomial result = new Polynomial();
        ArrayList<Monomial> aux;

        if(firstPolynomial.isEmpty() || firstPolynomial.get(0).getCoefficient() == 0){
            aux = secondPolynomial;
        }
        else{
            aux = firstPolynomial;
        }

        for(Monomial m : aux){
            double newCoefficient = m.getCoefficient()/((double)m.getPower() + 1);
            int newPower = m.getPower() + 1;
            Monomial newMonomial = new Monomial(newPower, newCoefficient);
            result.addMember(newMonomial);
        }
        return result;
    }
}

