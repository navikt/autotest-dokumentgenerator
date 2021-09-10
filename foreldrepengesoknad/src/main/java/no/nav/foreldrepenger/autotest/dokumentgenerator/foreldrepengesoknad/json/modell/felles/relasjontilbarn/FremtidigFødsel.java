package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.relasjontilbarn;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FremtidigFødsel extends RelasjonTilBarn {
    private final LocalDate terminDato;
    private final LocalDate utstedtDato;

    @Builder
    @JsonCreator
    public FremtidigFødsel(int antallBarn, LocalDate terminDato, LocalDate utstedtDato, List<String> vedlegg) {
        super(antallBarn, vedlegg);
        this.terminDato = terminDato;
        this.utstedtDato = utstedtDato;
    }

    @Override
    public LocalDate relasjonsDato() {
        return terminDato;
    }
}
