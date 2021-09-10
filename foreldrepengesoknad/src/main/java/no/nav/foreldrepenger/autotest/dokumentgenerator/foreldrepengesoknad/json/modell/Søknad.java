package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell;

import static java.util.Collections.emptyList;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.Vedlegg;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "mottattdato")
@JsonPropertyOrder({ "mottattdato", "søker", "ytelse", "begrunnelseForSenSøknad", "tilleggsopplysninger", "vedlegg" })
public class Søknad {

    private LocalDate mottattdato;
    private Søker søker;
    private Ytelse ytelse;
    private String begrunnelseForSenSøknad;
    private String tilleggsopplysninger;
    private List<Vedlegg> vedlegg;

    @JsonCreator
    public Søknad(@JsonProperty("mottattdato") LocalDate mottattdato,
                  @JsonProperty("søker") Søker søker,
                  @JsonProperty("ytelse") Ytelse ytelse,
                  @JsonProperty("vedlegg") List<Vedlegg> vedlegg) {
        this.mottattdato = mottattdato;
        this.søker = søker;
        this.ytelse = ytelse;
        this.vedlegg = Optional.ofNullable(vedlegg).orElse(emptyList());
    }
}
