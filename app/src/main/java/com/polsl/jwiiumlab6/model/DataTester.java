package com.polsl.jwiiumlab6.model;

import java.util.List;

import pl.polsl.model.CalculationData;
import pl.polsl.model.PredefinedCommunicates;
import pl.polsl.utility.dataCheck.DataChk;
import pl.polsl.utility.dataCheck.ParseModifyString;

/** Tests input data, whether is it correct.
 * @author Karol Kozuch Group 4 Section 8
 * @version 1.0.0
 */
public class DataTester {
    /**
     * Message describing last error that occurred. Reset upon reading.
     */
    private String errorMessage;

    /**
     * Retrieves and resets the current error message.
     * @return Message describing current error.
     */
    public String retreiveErrorMsg()
    {
        String msg = errorMessage;
        errorMessage = "No error";
        return msg;
    }
    /**
     * Checks if integral parameters are correct. If an error occurs - retrieve the description via retrieveErrorMsg method.
     * @param integralFormula The formula of the calculated integral.
     * @param integralBegin Beginning of the integral range.
     * @param integralEnd End of the integral range.
     * @return TRUE if the parameters are correct. FALSE otherwise.
     */
    public boolean chkIntegralParams(String integralFormula, String integralBegin, String integralEnd)
    {
        if(!ParseModifyString.tryStringToDouble(integralBegin) ||
                !ParseModifyString.tryStringToDouble(integralEnd))
        {
            errorMessage = PredefinedCommunicates.incorrectIntegralRange;
            return false;
        }
        if(!DataChk.validateFunctionSyntax(integralFormula))
        {
            errorMessage = PredefinedCommunicates.incorrectIntegralFormula;
            return false;
        }

        return true;
    }
    /**
     * Checks if calculation method parameters are correct. If an error occurs - retrieve the description via retrieveErrorMsg method.
     * @param method Approximation method.
     * @param accuracy Amount of geometrical shapes used in approximation.
     * @return TRUE if the parameters are correct. FALSE otherwise.
     */
    public boolean chkCalcMethodParams(String method, String accuracy)
    {
        boolean methodRecognized=false;
        for(CalculationData.calcMethodTypes type : CalculationData.calcMethodTypes.values())
        {
            if(method.charAt(0) == type.toString().charAt(0))
            {
                methodRecognized= true;
                break;
            }
        }

        if(!methodRecognized)
        {
            errorMessage = PredefinedCommunicates.incorrectCalcMethod;
            return false;
        }

        boolean accuracyCorrect = false;
        if(ParseModifyString.tryStringToInt(accuracy))
        {
            int numberAccuracy = Integer.parseInt(accuracy);
            if(numberAccuracy<=0)
            {
                accuracyCorrect = true;
            }
        }

        if(!accuracyCorrect)
        {
            errorMessage= PredefinedCommunicates.wrongAccuracy;
            return false;
        }

        return true;
    }
}
