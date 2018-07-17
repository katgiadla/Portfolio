package Main;
import org.junit.*;
import org.junit.rules.ExpectedException;
import java.lang.IllegalArgumentException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.*;
import static org.junit.Assert.*;
import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class BasicCalculatorTest {
    private double m1;
    private double m2;

    public BasicCalculatorTest(double p1, double p2){
        m1 = p1;
        m2 = p2;
    }

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{{3.0,2.0},{7.0,9.0}, {121.0,10.0}};
        return Arrays.asList(data);
    }
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }







    @Test
    public void testSum1(){

        double a = 100.0, b = 10.0;
        BasicCalculator example = new BasicCalculator();
        double resultMethod = 110.0;
        double result = example.calculateSum(a,b);
        assertEquals(resultMethod,result,0.0);
    }
    @Test
    public void testSum2(){
        double a = 110.0, b = -10.0;
        BasicCalculator example = new BasicCalculator();
        double resultMethod = 100.0;
        double result = example.calculateSum(a,b);
        assertEquals(resultMethod,result,0.0);
    }
    @Test
    public void testSum3(){
        double a = 110.0, b = -10.0;
        BasicCalculator example = new BasicCalculator();
        double resultMethod = 50.0;
        double result = example.calculateSum(a,b);
        assertEquals(resultMethod,result,50.0);
    }



    @Test
    public void testDiff1(){
         double a = 10.0, b = -10.0;
        BasicCalculator example = new BasicCalculator();
        double resultMethod = 20.0;
        double result = example.calculateDiffirence(a,b);
        assertEquals(resultMethod,result,0.0);
    }
    @Test
    public void testDiff2(){
        double a = 50.0, b = -30.0;
        BasicCalculator example = new BasicCalculator();
        double resultMethod = 80.0;
        double result = example.calculateDiffirence(a,b);
        assertEquals(resultMethod,result,0.0);
    }
    @Test
    public void testDiff3(){
        double a = 10.0, b = 0;
        BasicCalculator example = new BasicCalculator();
        double resultMethod = 20.0;
        double result = example.calculateDiffirence(a,b);
        assertEquals(resultMethod,result,10.0);
    }



    @Test
    public void testMulti1(){
        //double a = 10.0, b = 1;
        BasicCalculator example = new BasicCalculator();
        double resultMethod = m1*m2;
        double result = example.calculateMultiplication(m1,m2);
        assertEquals(resultMethod,result,0.0);
    }
    @Test
    public void testMulti2(){
        double a = 5, b = 2;
        BasicCalculator example = new BasicCalculator();
        double resultMethod = 20.0;
        double result = example.calculateMultiplication(a,b);
        assertEquals(resultMethod,result,10.0);
    }
    @Test
    public void testMulti3(){
        double a = 4.3, b = 12.5;
        BasicCalculator example = new BasicCalculator();
        double resultMethod = 53.75;
        double result = example.calculateMultiplication(a,b);
        assertEquals(resultMethod,result,0.0);
    }



    @Test
    public void testDiv1(){
         double a = 5, b = 2;
        BasicCalculator example = new BasicCalculator();
        double resultMethod = 2.5;
        double result = example.calculateDivision(a,b);
        assertEquals(resultMethod,result,0.0);
    }
    @Test
    public void testDiv2(){
        double a = 3, b = 0;
        BasicCalculator example = new BasicCalculator();
        exception.expect(IllegalArgumentException.class);
        example.calculateDivision(a,b);
    }
    @Test
    public void testDiv3(){
        double a = 45, b = 5;
        BasicCalculator example = new BasicCalculator();
        double resultMethod = 9.0;
        double result = example.calculateDivision(a,b);
        assertEquals(resultMethod,result,0.0);
    }



    @Test
    public void testPow1(){
        double a = 5, b = 2;
        BasicCalculator example = new BasicCalculator();
        double resultMethod = 25.0;
        double result = example.calculatePow(a,b);
        assertEquals(resultMethod,result,0.0);
    }
    @Test
    public void testPow2(){
        double a = 0, b = 0;
        BasicCalculator example = new BasicCalculator();
        exception.expect(IllegalArgumentException.class);
        example.calculatePow(a,b);
    }
    @Test
    public void testPow3(){
        double a = -2, b = 0.5;
        BasicCalculator example = new BasicCalculator();
        exception.expect(IllegalArgumentException.class);
        example.calculatePow(a,b);
    }



    @Test
    public void testSqrt1(){
        double a = 100;
        BasicCalculator example = new BasicCalculator();
        double resultMethod = 10.0;
        double result = example.calculateSqrt(a);
        assertEquals(resultMethod,result,0.0);
    }
    @Test
    public void testSqrt2(){
        double a = 16;
        BasicCalculator example = new BasicCalculator();
        double resultMethod = 4.0;
        double result = example.calculateSqrt(a);
        assertEquals(resultMethod,result,0.0);
    }
    @Test
    public void testSqrt3(){
        double a = -5;
        BasicCalculator example = new BasicCalculator();
        exception.expect(IllegalArgumentException.class);
        example.calculateSqrt(a);
    }
}
