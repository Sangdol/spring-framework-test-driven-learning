import org.junit.Test;
import org.springframework.scheduling.support.CronSequenceGenerator;

import java.time.*;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * `@Scheduled(cron = "* * * * * *")`
 * - second, minute, hour, day, month, weekday. Month and weekday names
 *
 * @author hugh
 */
public class CronTest {

    /**
     * http://stackoverflow.com/questions/11499740/significance-of-question-mark-in-java-cron
     * - Question mark is usually used when a day is set and don't know which day it is.
     */
    @Test
    public void questionMarkTest() throws Exception {
        CronSequenceGenerator generator = new CronSequenceGenerator("0 0 * * * *");
        Date next = generator.next(dateOf(2016, 3, 18, 0, 0, 1));
        assertThat(date(next), is(LocalDateTime.of(2016, 3, 18, 1, 0, 0)));

        generator = new CronSequenceGenerator("0 0 * * * ?");
        next = generator.next(dateOf(2016, 3, 18, 0, 0, 1));
        assertThat(date(next), is(LocalDateTime.of(2016, 3, 18, 1, 0, 0)));
    }

    private LocalDateTime date(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.of("Z"));
    }

    private Date dateOf(int year, int month, int day, int hour, int minute, int second) {
        return Date.from(ZonedDateTime.of(year, month, day, hour, minute, second, 0, ZoneId.of("Z")).toInstant());
    }

}
