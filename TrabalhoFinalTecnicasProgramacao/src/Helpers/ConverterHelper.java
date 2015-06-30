package Helpers;

import Exception.ConverterException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;

public class ConverterHelper {

    public static Date convertDate(String date, String campo) throws ConverterException {
        if ("".equals(date) || date == null) {
            return null;
        }
        try {
            return Date.valueOf(date);
        } catch (DateTimeParseException e) {
            throw new ConverterException("Erro ao converter " + campo + " em data.");
        }
    }

    public static Time convertTime(String time, String campo) throws ConverterException {
        if ("".equals(time) || time == null) {
            return null;
        }

        try {
            DateFormat formatter = new SimpleDateFormat("HH:mm");
            return new Time(formatter.parse(time).getTime());
        } catch (ParseException | DateTimeParseException e) {
            throw new ConverterException("Erro ao converter " + campo + " em hora.");
        }
    }

    public static BigDecimal convertMoney(String value) {
        BigDecimal preco;
        if ("".equals(value)) {
            preco = new BigDecimal(0);
        } else {
            preco = BigDecimal.valueOf(Double.parseDouble(value.replace(".", "").replace(',', '.')));
        }

        return preco;
    }
}
