package Training.Utils;

import Training.Constants.ForbiddenSymbols;

/**
 * Created by Администратор on 08.06.2017.
 */
public class StringUtils {
    public static boolean isStringValidationPassed(String [] mass){
        for (String s:mass){
            for (ForbiddenSymbols f: ForbiddenSymbols.values()){
                if (s.contains(f.toString())){
                    return false;
                }
            }
        }
        return true;
    }

    public static String ValidateAddData(String[] strings, String photoName, String doubleVar){
        int symbolCap = 150;
        for (String s: strings) {
            if (s.length()<1){
                return "You must fill all fields.";
            }
            if (s.length() > symbolCap) {
                return "You cant enter more than " + symbolCap + " symbols.";
            }
        }
        if (photoName.length()!=0){
            if(!photoName.endsWith(".png") & !photoName.endsWith(".jpg")){
                return "Only images allowed to upload.";
            }
        }
        if(!isStringValidationPassed(strings)){
            return "Forbidden special symbols in input.";
        }

        try{
            double test = Double.valueOf(doubleVar);
        } catch (NumberFormatException ex){
            return "Price must be a number.";
        }
        return null;
    }
}
