package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.relasjontilbarn;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Adopsjon extends RelasjonTilBarn {

    @NotNull
    private final LocalDate omsorgsovertakelsesdato;
    private final boolean ektefellesBarn;
    private final LocalDate ankomstDato;
    private final List<LocalDate> fødselsdato;

    @Builder
    @JsonCreator
    public Adopsjon(@JsonProperty("antallBarn") int antallBarn,
                    @JsonProperty("omsorgsovertakelsesdato") LocalDate omsorgsovertakelsesdato,
                    @JsonProperty("ektefellesBarn") boolean ektefellesBarn,
                    @JsonProperty("vedlegg") List<String> vedlegg,
                    @JsonProperty("ankomstDato") LocalDate ankomstDato,
                    @JsonProperty("fødselsdato") List<LocalDate> fødselsdato) {
        super(antallBarn, vedlegg);
        this.omsorgsovertakelsesdato = omsorgsovertakelsesdato;
        this.ektefellesBarn = ektefellesBarn;
        this.ankomstDato = ankomstDato;
        this.fødselsdato = fødselsdato;
    }

    @Override
    public LocalDate relasjonsDato() {
        return fødselsdato.get(0);
    }
}
