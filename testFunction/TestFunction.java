package testFunction;

public class TestFunction {
    
    //instance variables
    private static int x;

    // class constructor
    public TestFunction()
    {
        // initialise instance variables
        x = 0;
    }

    // test function
    public static int sampleMethod(int y)
    {
        int sum = x + y;
        System.out.println("The sum is: " + sum);
        return sum;
    }

    // main method
    public static void main(String args[])
    {
        sampleMethod(x);
    }

}
