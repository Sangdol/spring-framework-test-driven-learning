import java.time.*;
import java.util.Date;

/**
 * @author hugh
 */
public class DateHelper {

    public final static ZoneId UTC = ZoneId.of("UTC");

    public static Date dateOf(int year, int month, int day, int hour, int minute, int second) {
        return Date.from(ZonedDateTime.of(year, month, day, hour, minute, second, 0, ZoneId.of("Z")).toInstant());
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), UTC);
    }

    public static Date toDate(LocalDateTime date) {
        return toDate(ZonedDateTime.of(date, UTC));
    }

    public static Date toDate(ZonedDateTime date) {
        return Date.from(date.toInstant());
    }

    public static ZonedDateTime now() {
        return ZonedDateTime.now(UTC).withNano(0);
    }
}
