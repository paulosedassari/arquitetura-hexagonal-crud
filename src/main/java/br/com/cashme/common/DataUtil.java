package br.com.cashme.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DataUtil {

    public static String converterParaFormatoEspecificado(LocalDateTime dataHora, DateTimeFormatter formato) {
        if (Objects.isNull(dataHora)) dataHora = LocalDateTime.now();
        return dataHora.format(formato);
    }
}
