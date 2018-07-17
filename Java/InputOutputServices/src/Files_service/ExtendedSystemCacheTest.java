package Files_service;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;


public class ExtendedSystemCacheTest {
   // private
   // private

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
    public void allMethodsTest() {
        ScatterSystem system = new ScatterSystem();
        ExtendedSystemCache extendedSystemCache = new ExtendedSystemCache();
        Double output,output2;
        double[] input = {5.0, 4.0, 3.0,8.0}; // 5,4,3;4
        double[] input1 = {2.0,2.0,2.0,2.0,2.0};
        try {
            //System.out.println("try");
            //output = extendedSystemCache.get(input);
            //output2 = extendedSystemCache.get(input1);
            //System.out.println(output);
            //if()
            output = system.makeOperation(input);
            output2 = system.makeOperation(input1);
            extendedSystemCache.put(input, output);
            extendedSystemCache.put(input1, output2);


            try {
            extendedSystemCache.printHashMap();
            extendedSystemCache.exportCSV("exportCSV.csv");
            extendedSystemCache.clear();
            System.out.println(" After clear");
            extendedSystemCache.printHashMap();
            extendedSystemCache.importCSV("exportCSV.csv");
            System.out.println(" After import");
            extendedSystemCache.printHashMap();

        } catch (IOException e) {
           // e.printStackTrace();
        }
        System.out.println("Output: "+output);
        System.out.println("After ex: " + extendedSystemCache.get(input));
        Assert.assertEquals(output, extendedSystemCache.get(input));
        Assert.assertEquals(output2, extendedSystemCache.get(input1));

        }catch (NullPointerException e1) {
            System.out.println("catch");


        }
    }
}
