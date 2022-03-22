package operations_test;

import business_logic.Operations;
import data_models.*;
import user_interface.CalcController;

import org.junit.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PolTest {
    private static CalcController controller = new CalcController();
    private static Operations operations = new Operations();

    @Test
    public void testAdd(){

        Polynomial p1 = new Polynomial();
        Polynomial p2 = new Polynomial();

        p1.addMember(new Monomial(3, 2));
        p1.addMember(new Monomial(2, -1));

        p2.addMember(new Monomial(2, 3));
        p2.addMember(new Monomial(0, 4));

        Polynomial assertResult = new Polynomial();
        assertResult.addMember(new Monomial(3, 2));
        assertResult.addMember(new Monomial(2, 2));
        assertResult.addMember(new Monomial(0, 4));

        Polynomial result = operations.add(p1.getMonomials(), p2.getMonomials());

        assertEquals(result.toString('+'), assertResult.toString('+'));
    }

    @Test
    public void testSubtract(){

        Polynomial p3 = controller.readPolynomial("x+1");
        Polynomial p4 = controller.readPolynomial("x^4-4x^3+2x-2");

        Polynomial assertResult = new Polynomial();
        assertResult.addMember(new Monomial(4, -1));
        assertResult.addMember(new Monomial(3, 4));
        assertResult.addMember(new Monomial(1, -1));
        assertResult.addMember(new Monomial(0, 3));

        Polynomial result = operations.subtract(p3.getMonomials(), p4.getMonomials());

        assertEquals(result.toString('-'), assertResult.toString('-'));
    }

    @Test
    public void testMultiply(){

        Polynomial p5 = controller.readPolynomial("x-2");
        Polynomial p6 = controller.readPolynomial("3x^2-x+1");

        Polynomial assertResult = new Polynomial();
        assertResult.addMember(new Monomial(3, 3));
        assertResult.addMember(new Monomial(2, -7));
        assertResult.addMember(new Monomial(1, 3));
        assertResult.addMember(new Monomial(0, -2));

        Polynomial result = operations.multiply(p5.getMonomials(), p6.getMonomials());

        assertEquals(result.toString('*'), assertResult.toString('*'));
    }

    @Test
    public void testDivide(){
        Polynomial p7 = new Polynomial();

        p7.addMember(new Monomial(3, 1));
        p7.addMember(new Monomial(2, -2));
        p7.addMember(new Monomial(1, 6));
        p7.addMember(new Monomial(0, -5));

        Polynomial p8 = controller.readPolynomial("x^2-1");

        Polynomial assertResult = new Polynomial();
        assertResult.addMember(new Monomial(1, 1));
        assertResult.addMember(new Monomial(0, -2));
        assertResult.addMember(new Monomial(0, 0));
        assertResult.addMember(new Monomial(1, 7));
        assertResult.addMember(new Monomial(0, -7));

        Polynomial result = operations.divide(p7.getMonomials(), p8.getMonomials());

        assertEquals(result.toString('/'), assertResult.toString('/'));
    }

    @Test
    public void testDerive(){
        Polynomial p9 = new Polynomial();
        p9.addMember(new Monomial(3, 1));
        p9.addMember(new Monomial(2, -2));
        p9.addMember(new Monomial(1, 6));
        p9.addMember(new Monomial(0, -5));

        Polynomial assertResult = controller.readPolynomial("3x^2-4x+6");

        Polynomial result = operations.derive(p9.getMonomials(), new ArrayList<>());

        assertEquals(result.toString('D'), assertResult.toString('D'));
    }

    @Test
    public void testIntegrate(){
        Polynomial p10 = new Polynomial();
        p10.addMember(new Monomial(4, 6));
        p10.addMember(new Monomial(3, -2));
        p10.addMember(new Monomial(2, 5));
        p10.addMember(new Monomial(1, -1));

        Polynomial assertResult = new Polynomial();
        assertResult.addMember(new Monomial(5, (double)6/5));
        assertResult.addMember(new Monomial(4, (double)(-2)/4));
        assertResult.addMember(new Monomial(3, (double)5/3));
        assertResult.addMember(new Monomial(2, (double)(-1)/2));

        Polynomial result = operations.integrate(new ArrayList<>(), p10.getMonomials());

        assertEquals(result.toString('I'), assertResult.toString('I'));
    }

}
