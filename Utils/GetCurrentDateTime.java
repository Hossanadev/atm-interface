package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetCurrentDateTime {
    public static String get() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new Date());
    }
}