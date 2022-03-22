package data_models;

public class Monomial {
    private int power;
    private double coefficient;

    public Monomial(int power, double coefficient) {
        this.power = power;
        this.coefficient = coefficient;
    }

    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = power;
    }

    public double getCoefficient() {
        return coefficient;
    }
    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public String toString(){
        if(this.power == 0){
            if(this.coefficient != 0)
                return this.coefficient + "";
        }
        if(this.power == 1){
            if(this.coefficient == 1){
                return "x";
            }
            else if (this.coefficient == -1){
                return "-x";
            }
            return this.coefficient + "x";
        }
        if(this.coefficient == 1){
            return "x^" + this.power;
        }
        else if(this.coefficient == -1)
            return "-x^" + this.power;
        return this.coefficient + "x^" + this.power;
    }
}

