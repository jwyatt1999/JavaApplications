package testFunction;

public class Practice {
    
    //instance variables
    int x;

    // class constructor
    public Practice()
    {
        // initialise instance variables
        x = 5;
    }

    // test function
    public int addX(int y)
    {
        int sum = x + y;
        System.out.println("The sum is: " + sum);
        return sum;
    }

    // main method
    public static void main(String args[])
    {
        Practice classObj = new Practice(); //call the constructor
        int y = 10;
        classObj.addX(y);
    }
}
