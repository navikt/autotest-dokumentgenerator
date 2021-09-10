package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.medlemskap;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.neovisionaries.i18n.CountryCode;

import lombok.Data;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.LukketPeriode;

@Data
public class Utenlandsopphold {

    @NotNull
    private final CountryCode land;
    private final LukketPeriode varighet;

    @JsonCreator
    public Utenlandsopphold(@JsonProperty("land") CountryCode land, @JsonProperty("varighet") LukketPeriode varighet) {
        this.land = land;
        this.varighet = varighet;
    }

    @JsonIgnore
    public LocalDate getFom() {
        return varighet.getFom();
    }

    @JsonIgnore
    public LocalDate getTom() {
        return varighet.getTom();
    }
}
