package tools;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

/** This helper class is responsible for the functionality of the Date and Time objects in the program. */
public class TimeHelper {
    public static LocalDate currentDate = LocalDate.now();
    public static LocalTime currentTime = LocalTime.now();

    public static Month currentMonth = currentDate.getMonth();
    public static LocalDate previousOrCurrentSunday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
    public static LocalDate nextWeekDay = previousOrCurrentSunday.plusDays(7);

    public static ZoneId etZoneId = ZoneId.of("America/New_York");
    public static ZonedDateTime etZonedOpening = ZonedDateTime.of(currentDate.atTime(8, 00), etZoneId);
    public static ZonedDateTime etZonedClosing = ZonedDateTime.of(currentDate.atTime(22, 00), etZoneId);

    public static ZoneId localZoneId = ZoneId.systemDefault();
    public static ZonedDateTime localZonedOpen = etZonedOpening.withZoneSameInstant(localZoneId);
    public static LocalDateTime etLocalOpen = localZonedOpen.toLocalDateTime();

    public static ZonedDateTime localZonedClose = etZonedClosing.withZoneSameInstant(localZoneId);
    public static LocalDateTime etLocalClose = localZonedClose.toLocalDateTime();
}
