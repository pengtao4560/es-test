package java8.localdate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.temporal.TemporalAmount;

/**
   java8新特性 LocalDate LocalTime LocalDateTime Demo
   详细api调用移步
    {@link LocalDateTest}
    setting->inspection->javadoc:The declaration has a problem in the Javadoc reference error调成warning即可
 * @author: peng tao
 * @create: 2022-01-27 14:38
 */
public class LocalDateAndLocalTimeAndLocalDateTime {
    public static void main(String[] args) {
        testMethodNow();
        System.out.println("------");
        testMethodOf();
        System.out.println("------");
        testMethodPlus();
    }

    private static void testMethodNow() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate); //2022-01-27
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime); // 15:04:08.029
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime); // 2022-01-27T15:04:08.029
    }
    private static void testMethodOf() {
        LocalDate localDate = LocalDate.of(2022, Month.FEBRUARY, 27);
        System.out.println(localDate); // 2022-02-27
        LocalDate localDate2 = LocalDate.of(2022, 2, 25);
        System.out.println(localDate2); // 2022-02-27
        LocalTime localTime = LocalTime.of(14, 59, 59);
        System.out.println(localTime);// 14:59:59

        System.out.println(LocalTime.of(14, 59)); // 14:59
        System.out.println(LocalTime.of(14, 59, 59, 59)); // 14:59:59.000000059
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime); //2022-02-27T14:59:59
        System.out.println(localDateTime);
        LocalDateTime localDateTime1 = LocalDateTime.of(2022, 1, 24, 12, 00, 05);
        System.out.println(localDateTime1); // 2022-01-24T12:00:05
    }

    public static  void testMethodPlus() {
        LocalDate localDate = LocalDate.now();
        LocalDate nowAfter10Year = localDate.plusYears(10);
        LocalDate nowAfter11Month = localDate.plusMonths(12);
        LocalDate nowAfter3Week = localDate.plusWeeks(3);
        LocalDate nowAfter35Day = localDate.plusDays(35);
        System.out.println(nowAfter10Year);
        System.out.println(nowAfter11Month);
        System.out.println(nowAfter3Week);
        System.out.println(nowAfter35Day);
        TemporalAmount temporalAmount = Period.of(1,2,3);
        System.out.println(LocalDateTime.now().plus(temporalAmount));
    }
}
