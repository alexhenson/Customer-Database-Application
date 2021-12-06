package tools;

import java.time.*;

public class TimeHelper {

    public static LocalDate currentDate = LocalDate.now();
    public static LocalTime currentTime = LocalTime.now();

    public static Month currentMonth = currentDate.getMonth();
    public static LocalDate currentDay = currentDate;
    public static LocalDate nextWeekDay = currentDate.plusDays(7);

    public static ZonedDateTime utcZonedOpening = ZonedDateTime.of(currentDate.atTime(13, 00), ZoneOffset.UTC);
    public static ZonedDateTime utcZonedClosing = ZonedDateTime.of(currentDate.plusDays(1).atTime(2, 30), ZoneOffset.UTC);

    public static ZoneId etZoneId = ZoneId.of("America/New_York");
    public static ZoneId localZoneId = ZoneId.systemDefault();
    public static ZonedDateTime localZonedOpen = utcZonedOpening.withZoneSameInstant(localZoneId);
    public static LocalDateTime etLocalOpen = localZonedOpen.toLocalDateTime();

    public static ZonedDateTime localZonedClose = utcZonedClosing.withZoneSameInstant(localZoneId);
    public static LocalDateTime etLocalClose = localZonedClose.toLocalDateTime();
}
