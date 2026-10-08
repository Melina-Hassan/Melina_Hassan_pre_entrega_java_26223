package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class ValidadorFecha {

    private static final DateTimeFormatter FORMATO_ESTRICTO = DateTimeFormatter
            .ofPattern("uuuu-MM-dd")
            .withResolverStyle(ResolverStyle.STRICT);

    public static boolean esFechaValida(String input) 
    {
        if (input == null || input.trim().isEmpty()) 
        {
            return false;
        }

        try 
        {
            LocalDate.parse(input.trim(), FORMATO_ESTRICTO);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}