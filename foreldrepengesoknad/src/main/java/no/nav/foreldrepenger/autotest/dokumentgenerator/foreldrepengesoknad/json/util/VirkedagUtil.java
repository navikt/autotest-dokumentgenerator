package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.util;

import java.time.LocalDate;

public final class VirkedagUtil {

    private VirkedagUtil() {
    }

    public static LocalDate helgejustertTilMandag(LocalDate dato) {
        return switch (dato.getDayOfWeek()) {
            case SATURDAY -> dato.plusDays(2);
            case SUNDAY -> dato.plusDays(1);
            default -> dato;
        };
    }
    

    public static LocalDate helgejustertTilFredag(LocalDate dato) {
        return switch (dato.getDayOfWeek()) {
            case SATURDAY -> dato.minusDays(1);
            case SUNDAY -> dato.minusDays(2);
            default -> dato;
        };
    }

}
