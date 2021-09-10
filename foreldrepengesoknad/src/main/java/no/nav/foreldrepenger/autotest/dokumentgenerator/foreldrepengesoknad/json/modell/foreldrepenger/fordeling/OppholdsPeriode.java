package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.foreldrepenger.fordeling;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OppholdsPeriode extends LukketPeriodeMedVedlegg {

    private final Oppholdsårsak årsak;

    @Builder
    public OppholdsPeriode(LocalDate fom, LocalDate tom, @NotNull Oppholdsårsak årsak, List<String> vedlegg) {
        super(fom, tom, vedlegg);
        this.årsak = årsak;
    }
}
