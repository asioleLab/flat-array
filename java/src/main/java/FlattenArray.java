import java.util.ArrayList;

public class FlattenArray {

    // Main method with test case
    public static void main(String[] args)
    {

        // Sample [[1,2,[3]],4] -> [1,2,3,4]

        // Create the main array
        Object[] array = new Object[2];

        // Add new arrays to the main array and store the integers.
        array[0] = new Object[3];
        ((Object[]) array[0])[0] = 1;
        ((Object[]) array[0])[1] = 2;
        ((Object[]) array[0])[2] = new Object[1];
        ((Object[])((Object[]) array[0])[2])[0] = 3;
        array[1] = 4;

        // Show the array of arrays composition and the expected result.
        System.out.println("With this input [[1,2,[3]],4], expected result [1, 2, 3, 4]");

        // Create an object of the class ArrayList to store the list of integers.
        ArrayList<Integer> result = new ArrayList<Integer>();

        /*
         *  Call to the method 'merge()' with the array to flatten and a list to store the result
         *  This method is static, then it can be called by the main method without need to
         *  create an object.
         */
        try
        {
            merge(array, result);

            // Show the result.
            System.out.println("Result: " + result);
        }
        catch (InvalidTypeData ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex2)
        {
            ex2.printStackTrace();
        }


    }

    /**
     * Recursive method which flatten an array of arbitrarily nested arrays of integers
     * into a flat array of integers.
     *
     * @param array The array to be flattened
     * @param list The list which will contain the result in a flat array of integers.
     * @return nothing
     * @throws InvalidTypeData if the data stored in the Array is not other Array or
     * 	   an Integer value.
     * */
    private static void merge(Object[] array, ArrayList<Integer> list) throws InvalidTypeData
    {
        // Move through the length of the array
        for(int i = 0; i < array.length; i++)
        {
            /*
             * If the value of this position is another array then the method call itself
             * recursively, sending the array stored in this position and the list
             */
            if(array[i] instanceof Object[])
            {
                merge((Object[]) array[i], list);
            }

            // Else if the value is an integer then add it to the list.
            else if(array[i] instanceof Integer)
            {
                list.add((Integer)array[i]);
            }

            /*
             *  If the value in this position is not an array or an integer, the method throws
             *  an exception
             */
            else
            {
                throw new InvalidTypeData("Invalid type of data. It only allows for Integers or Arrays");
            }
        }
    }

}

/**
 * Customized exception with custom message, it's a new class which inherits from the class Exception.
 */
class InvalidTypeData extends Exception
{
    /**
     * Constructor with a message that allows the user to create a customized message.
     *
     * @param message The customized message to be shown when the user send invalid data.
     */

    public InvalidTypeData( String message )
    {
        super(message);
    }

    // Rewrite the method toString()
    public String toString()
    {
        return "The array sent contains data from invalid types. "
                + "Remember that the array can contain only Integers or other arrays";
    }
}