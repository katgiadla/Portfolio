package Main;
import org.junit.*;
import org.junit.rules.ExpectedException;
import java.lang.IllegalArgumentException;
import static org.junit.Assert.*;


public class FieldCalculatorTest {


    public FieldCalculatorTest(){

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
    public void testSquare1() {
        double x = 5.0;
        FieldCalculator instance = new FieldCalculator();
        double expResult = 25.0;
        double result = instance.calculateSquare(x);
        assertEquals(expResult, result, 0.0);
    }
    @Test
    public void testSquare2() {
        double x = 0.0;
        FieldCalculator instance = new FieldCalculator();
        double expResult = 0.0;
        double result = instance.calculateSquare(x);
        assertEquals(expResult, result, 0.0);
    }
    @Test
    public void testSquare3() {
        double x = -5.0;
        FieldCalculator instance = new FieldCalculator();
        exception.expect(IllegalArgumentException.class);
        instance.calculateSquare(x);
    }



    @Test
    public void testCircle1() {
        double x = 1.0;
        FieldCalculator instance = new FieldCalculator();
        double expResult = 3.14;
        double result = instance.calculateCircle(x);
        assertEquals(expResult, result, 0.01);
    }
    @Test
    public void testCircle2() {
        double x = -1.0;
        FieldCalculator instance = new FieldCalculator();
        exception.expect(IllegalArgumentException.class);
        instance.calculateSquare(x);
    }
    @Test
    public void testCircle3() {
        double x = 0.0;
        FieldCalculator instance = new FieldCalculator();
        double expResult = 0;
        double result = instance.calculateCircle(x);
        assertEquals(expResult, result, 0);
    }



    @Test
    public void testTriangle1() {
        double x = 0.0, y = 2.0;
        FieldCalculator instance = new FieldCalculator();
        double expResult = 0;
        double result = instance.calculateCircle(x);
        assertEquals(expResult, result, 0);
    }
    @Test
    public void testTriangle2() {
        double x = -10.0, y = 2.0;
        FieldCalculator instance = new FieldCalculator();
        exception.expect(IllegalArgumentException.class);
        instance.calculateSquare(x);
    }
    @Test
    public void testTriangle3() {
        double x = 5.0, y = 2.0;
        FieldCalculator instance = new FieldCalculator();
        double expResult = 5.0;
        double result = instance.calculateTriangle(x,y);
        assertEquals(expResult, result, 0);
    }



    @Test
    public void testRectangle1() {
        double x = 2.0, y = 4.0;
        FieldCalculator instance = new FieldCalculator();
        double expResult = 8.0;
        double result = instance.calculateRectangle(x, y);
        assertEquals(expResult, result, 0.0);
    }
    @Test
    public void testRectangle2() {
        double x = 2.0, y = 0.0;
        FieldCalculator instance = new FieldCalculator();
        double expResult = 0.0;
        double result = instance.calculateRectangle(x, y);
        assertEquals(expResult, result, 0.0);
    }
    @Test
    public void testRectangle13() {
        double x = 2.0, y = -4.0;
        FieldCalculator instance = new FieldCalculator();
        exception.expect(IllegalArgumentException.class);
        instance.calculateRectangle(x,y);
    }
}
