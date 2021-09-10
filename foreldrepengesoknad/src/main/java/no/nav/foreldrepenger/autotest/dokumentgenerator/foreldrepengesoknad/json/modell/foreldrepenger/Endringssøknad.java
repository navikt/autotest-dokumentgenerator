package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.foreldrepenger;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.Søker;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.Søknad;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.Ytelse;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.Vedlegg;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Endringssøknad extends Søknad {

    @NotNull
    private String saksnr;

    @Builder
    public Endringssøknad(@JsonProperty("saksnr") String saksnr,
                          @JsonProperty("mottattdato") LocalDate mottattdato,
                          @JsonProperty("søker") Søker søker,
                          @JsonProperty("ytelse") Ytelse ytelse,
                          @JsonProperty("vedlegg") List<Vedlegg> vedlegg) {
        super(mottattdato, søker, ytelse, vedlegg);
        this.saksnr = saksnr;
    }
}
