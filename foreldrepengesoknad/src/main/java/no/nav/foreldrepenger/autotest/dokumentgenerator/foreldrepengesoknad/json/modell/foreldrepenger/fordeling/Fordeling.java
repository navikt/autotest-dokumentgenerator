package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.foreldrepenger.fordeling;

import static java.util.Collections.emptyList;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
public class Fordeling {

    private final boolean erAnnenForelderInformert;
    private final Overføringsårsak ønskerKvoteOverført;
    @Valid
    private final List<LukketPeriodeMedVedlegg> perioder;

    @Builder
    @JsonCreator
    public Fordeling(@JsonProperty("erAnnenForelderInformert") boolean erAnnenForelderInformert,
            @JsonProperty("årsak") Overføringsårsak årsak,
            @JsonProperty("perioder") List<LukketPeriodeMedVedlegg> perioder) {
        this.erAnnenForelderInformert = erAnnenForelderInformert;
        this.ønskerKvoteOverført = årsak;
        this.perioder = Optional.ofNullable(perioder).orElse(emptyList());
    }

    @JsonIgnore
    public LocalDate getFørsteUttaksdag() {
        return perioder.stream()
                .sorted()
                .filter(aktuellPeriode())
                .findFirst()
                .map(LukketPeriodeMedVedlegg::getFom)
                .orElse(null);
    }

    private static Predicate<? super LukketPeriodeMedVedlegg> aktuellPeriode() {
        return f -> f instanceof UttaksPeriode || f instanceof UtsettelsesPeriode || f instanceof OverføringsPeriode;
    }
}
