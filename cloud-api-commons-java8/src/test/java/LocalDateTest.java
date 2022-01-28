import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.TemporalAmount;

/**
 * @description:
 * @author: peng tao
 * @create: 2022-01-27 15:22
 * {@link LocalDateAndLocalTimeAndLocalDateTime}
 */
public class LocalDateTest {
    public static final LocalDate LOCAL_DATE = LocalDate.now();
    public static final LocalTime LOCAL_TIME = LocalTime.now();
    public static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();
    /** LocalDate/LocalTime/LocalDateTime 调用 minus/plus 方法分别表示 该时间之前多久，之后多久*/
    @Test
    public void testPlus() {
        TemporalAmount duration = Duration.ofDays(11);
        TemporalAmount period = Period.of(20, 5, 3);
        System.out.println(LOCAL_DATE_TIME.plus(duration));
        System.out.println(LOCAL_DATE_TIME.minus(period));
    }
}
