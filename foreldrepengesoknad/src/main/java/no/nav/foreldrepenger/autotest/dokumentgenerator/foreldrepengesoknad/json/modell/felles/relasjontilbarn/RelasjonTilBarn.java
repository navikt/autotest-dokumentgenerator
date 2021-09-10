package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.relasjontilbarn;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
import static java.util.Collections.emptyList;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonTypeInfo(use = NAME, include = PROPERTY, property = "type")
@JsonSubTypes({
        @Type(value = Fødsel.class, name = "fødsel"),
        @Type(value = Adopsjon.class, name = "adopsjon"),
        @Type(value = FremtidigFødsel.class, name = "termin"),
        @Type(value = Omsorgsovertakelse.class, name = "omsorgsovertakelse")
})
public abstract class RelasjonTilBarn {

    public abstract LocalDate relasjonsDato();

    private List<String> vedlegg;

    private int antallBarn;

    protected RelasjonTilBarn(int antallBarn, List<String> vedlegg) {
        this.antallBarn = antallBarn;
        this.vedlegg = Optional.ofNullable(vedlegg).orElse(emptyList());
    }
}
