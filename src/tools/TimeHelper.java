package tools;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

/** This helper class is responsible for the functionality of the Date and Time objects in the program. */
public class TimeHelper {
    private static final ZoneId etZoneId = ZoneId.of("America/New_York");
    private static final ZoneId localZoneId = ZoneId.systemDefault();

    /**
     * @return the current LocalDate object
     */
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    /**
     * @return the current LocalTime object
     */
    public static LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    /**
     * @return the LocalDateTime object for the previous or current Sunday
     */
    public static LocalDateTime getPreviousOrCurrentSunday() {
        return LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)), LocalTime.of(0,0));
    }

    /**
     * @return the LocalDateTime object for the next Sunday
     */
    public static LocalDateTime getNextSunday() {
        return getPreviousOrCurrentSunday().plusDays(7);
    }

    /**
     * @return the LocalDateTime object for the first day of the current month
     */
    public static LocalDateTime getCurrentMonthFirstDay() {
        return LocalDateTime.of(YearMonth.now().atDay(1), LocalTime.of(0,0));
    }

    /**
     * @return the LocalDateTime object for the last day of the current month
     */
    public static LocalDateTime getCurrentMonthLastDay() {
        return getCurrentMonthFirstDay().plusMonths(1);
    }

    /**
     * @return the ZonedDateTime object for the eastern time opening hours
     */
    public static ZonedDateTime getETZonedOpeningHours() {
        return ZonedDateTime.of(LocalDate.now().atTime(8, 00), etZoneId);
    }

    /**
     * @return the ZonedDateTime object for the eastern time closing hours
     */
    public static ZonedDateTime getETZonedClosingHours() {
        return ZonedDateTime.of(LocalDate.now().atTime(22, 00), etZoneId);
    }

    /**
     * @return the LocalDateTime object for the eastern time opening hours
     */
    public static LocalDateTime getETLocalOpeningHours() {
        return getETZonedOpeningHours().withZoneSameInstant(localZoneId).toLocalDateTime();
    }

    /**
     * @return the LocalDateTime object for the eastern time opening hours
     */
    public static LocalDateTime getETLocalClosingHours() {
        return getETZonedClosingHours().withZoneSameInstant(localZoneId).toLocalDateTime();
    }
}
