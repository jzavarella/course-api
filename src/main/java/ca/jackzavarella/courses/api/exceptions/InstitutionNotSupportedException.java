package ca.jackzavarella.courses.api.exceptions;

/**
 * Created by Jack Zavarella on 7/4/2018. :)
 */
public class InstitutionNotSupportedException extends Exception {
    public InstitutionNotSupportedException(String unsupportedInstitution) {
        super("The institution '" + unsupportedInstitution + "' is not supported.");
    }
}
