package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.relasjontilbarn;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Fødsel extends RelasjonTilBarn {

    private final List<LocalDate> fødselsdato;
    private final LocalDate termindato;


    @Builder
    @JsonCreator
    public Fødsel(@JsonProperty("antallBarn") int antallBarn,
                  @JsonProperty("fødselsdato") List<LocalDate> fødselsdato,
                  @JsonProperty("termindato") LocalDate termindato,
                  @JsonProperty("vedlegg") List<String> vedlegg) {
        super(antallBarn, vedlegg);
        this.fødselsdato = fødselsdato;
        this.termindato = termindato;
    }

    @Override
    public LocalDate relasjonsDato() {
        return fødselsdato.get(0);
    }
}
