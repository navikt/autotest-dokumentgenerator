package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.opptjening;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.ÅpenPeriode;

@Data
public class FrilansOppdrag {
    private final String oppdragsgiver;
    private final ÅpenPeriode periode;

    @JsonCreator
    public FrilansOppdrag(@JsonProperty("oppdragsgiver") String oppdragsgiver,
            @JsonProperty("periode") ÅpenPeriode periode) {
        this.oppdragsgiver = oppdragsgiver;
        this.periode = periode;
    }
}
