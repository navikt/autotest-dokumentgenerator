package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell;

import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.SpråkKode.defaultSpråk;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.SpråkKode;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Søker {
    private final BrukerRolle søknadsRolle;
    private final SpråkKode språkkode;

    public Søker(@JsonProperty("søknadsRolle") BrukerRolle søknadsRolle) {
        this(søknadsRolle, defaultSpråk());
    }

    @JsonCreator
    public Søker(@JsonProperty("søknadsRolle") BrukerRolle søknadsRolle,
            @JsonProperty("språkkode") SpråkKode språkkode) {
        this.søknadsRolle = søknadsRolle;
        this.språkkode = Optional.ofNullable(språkkode).orElse(defaultSpråk());
    }

}
