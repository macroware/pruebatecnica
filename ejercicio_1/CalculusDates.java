import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateCalculator {

    private static final DateTimeFormatter FORMAT_DD_MM_YYYY = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        LocalDate startDate = parseCustomDate("01/04/2025");
        LocalDate endDate = parseIsoDate("2025-05-26");

        printHeader("Fechas iniciales");
        System.out.printf("Fecha inicio : %s%n", startDate);
        System.out.printf("Fecha fin    : %s%n%n", endDate);

        long totalDays = ChronoUnit.DAYS.between(startDate, endDate);
        System.out.printf("Días entre %s y %s: %d%n%n", startDate, endDate, totalDays);

        LocalDate stage1End = startDate.plusDays(13);
        printStage("Etapa 1", startDate, stage1End);

        LocalDate stage2End = stage1End.plusDays(28);
        printStage("Etapa 2", stage1End, stage2End);

        LocalDate stage3End = stage2End.plusDays(14);
        printStage("Etapa 3", stage2End, stage3End);
    }

    private static LocalDate parseCustomDate(String date) {
        return LocalDate.parse(date, FORMAT_DD_MM_YYYY);
    }

    private static LocalDate parseIsoDate(String date) {
        return LocalDate.parse(date);
    }

    private static void printStage(String stageName, LocalDate start, LocalDate end) {
        System.out.println("-- " + stageName + " --");
        System.out.printf("Inicio : %s%n", start);
        System.out.printf("Fin    : %s%n%n", end);
    }

    private static void printHeader(String title) {
        System.out.print(System.lineSeparator());
        System.out.println("==== " + title + " ====");
    }
}
