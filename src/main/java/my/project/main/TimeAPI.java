package my.project.main;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Created by Mustafa Aslanov on 31/7/16.
 */
public class TimeAPI {

    public static void main(String... go) {
        new TimeAPI().startTestPeriodAndDuration();
    }

    public void startTestLocalDateAndTime(){
        //İndiki tarix və zaman
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println("Hal hazırda: " +localDateTime);

        LocalDate localDate = localDateTime.toLocalDate();
        LocalTime localTime = localDateTime.toLocalTime();

        System.out.println("İndiki tarıx: " + localDate +
                "\nHal hazırda saat: " + localTime);

        //Setter olmadığı üçün əvəzinə:
        LocalDateTime localDateTime2 = localDateTime.withMonth(3).with(ChronoField.DAY_OF_MONTH, 20);
        //30 Mart 2016
        System.out.println("Local Date Time 2: "+localDateTime2);

        Month month = localDateTime.getMonth();
        int day = localDateTime.getDayOfMonth();
        //İngilis azərbaycan qarışığı olacaq amma nə etmək olar. Azərbaycan dilində yoxdu aylar :(
        System.out.println("Hal hazırda " + month + " ayınını " + day);

        //Yeni ile 1 dəqiqə qalır :)
        LocalDateTime localDateTime3 = LocalDateTime.of(2016, Month.DECEMBER, 31, 23, 59);
        System.out.println("Yeni ilə 1 dəqiqə: " + localDateTime3);

        //Tarixdən ay gün saat və s. çıxa və ya əlavə edə bilərik.
        LocalDate ldKecmish = localDate.minusDays(10);
        LocalDate ldGelecek = localDate.plusDays(10);

        System.out.println("10 gün keçmiş: " + ldKecmish +
                "\n10 gün gələcək: "+ldGelecek);


        String myDate = "2012-12-21";
        LocalDate localDateParse = LocalDate.parse(myDate);

        System.out.println("String -dən parse edilmiş tarix: " + localDateParse);
    }

    public void startTestZonedDateAndTime(){
        LocalDateTime localDateTime = LocalDateTime.now();

        ZoneId zoneIdDefault = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneIdDefault);

        //və ya bunu qısaca bu cür də yaza bilərik.
        ZonedDateTime zonedDateTime1 = ZonedDateTime.now();
        System.out.println("Local Date Time-dan: " + zonedDateTime);
        System.out.println("now() funksiyası ilə: " + zonedDateTime1);



        //Bütün zonaların siyahısı və offset-ləri
        Set<String> zones = ZoneId.getAvailableZoneIds();
        List<String> listZones = new ArrayList<>(zones);
        Collections.sort(listZones);
        for (String zone : listZones){
            ZoneId zoneId = ZoneId.of(zone);
            ZonedDateTime zdt = localDateTime.atZone(zoneId);
            ZoneOffset offset = zdt.getOffset();

            String out = String.format("%35s %10s%n", zoneId, offset);
            System.out.println(out);
        }

        /*ZoneId zoneId = ZoneId.of("Asia/Hong_Kong");
        zonedDateTime1 = zonedDateTime1.withZoneSameInstant(zoneId);

        System.out.println("Hong Kongda zaman: " + zonedDateTime1);

        zonedDateTime = zonedDateTime.withZoneSameLocal(ZoneId.of("Asia/Baku"));
        System.out.println("Bakı vaxtı: " + zonedDateTime);*/

    }

    public void startTestPeriodAndDuration(){
        LocalDate localDate1 = LocalDate.now();
        System.out.println("Hal hazırda: " + localDate1);

        //İndiki zamanın üzərinə 4 gün 6 həfət 1 il əlavə edirik.
        LocalDate localDate2 = localDate1.plusDays(4).plusWeeks(6).plus(1 , ChronoUnit.YEARS);
        System.out.println("Gələcək: " + localDate2);

        //iki tarix arasındakı fərqi alırıq. ld1 başlanğıc ld2 bitiş tarixidir.
        Period period = Period.between(localDate1, localDate2);
        System.out.println("Fərq: " + period.getYears() + " - il, " + period.getMonths() +
                " - ay, " + period.getDays() + " - gün");

        LocalTime localTime1 = LocalTime.now();
        System.out.println("Hal hazırda saat: " + localTime1);

        Duration birSaat = Duration.ofHours(1);

        //1 saat əlavə edirik indiki zamana. Bunu duration-la etmək olur.
        LocalTime localTime2 = localTime1.plus(birSaat);
        System.out.println("1 saat sonra: " + localTime2);

        Duration duration = Duration.between(localTime1, localTime2);

        System.out.println(duration);
        System.out.println("Saniyələrlə fərq: " + duration.getSeconds());
    }

    public void utilDate(){
        //Yeni Date yaradırıq.
        Date date = new Date();
        System.out.println("Util Date: " + date.toString());

        // Date-dən milli saniyələrlə instant alırıq.
        Instant instant = date.toInstant();

        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        System.out.println("LocalDateTime: " + localDateTime);
    }

}
