package pl.polsl.model;
/**Stores predefined communicates that the server can send to the clientside.
 * @author Karol Kozuch Group 4 Section 8
 * @version 1.0*/
public class PredefinedCommunicates {
    /**
     * Description of error about wrong integral range.
     */
    public static final String incorrectIntegralRange = "Provided integral range was incorrect - both of the values have to be decimal numbers.";
    /**
     * Description of error about wrong integral formula.
     */
    public static final String incorrectIntegralFormula = "Provided integral formula was incorrect. Check syntax.";
    /**
     * When requested last made query but in fact none has been made yet.
     */
    public static final String noQueriesMadeYet = "You haven't made any queries yet, darling!";

    /**
     *   Error message indicating wrong input of approximation method that should be used.
     */
    public static final String incorrectCalcMethod = "Provided method was not recognized.";

    /**
     * Response used when returning computed values.
     */
    public static final String calcResult = "Calculations result: ";
    /**
     * String informing about wrong form of provided accuracy.
     */
    public static final String wrongAccuracy = "Accuracy must be a positive integer.";

}
