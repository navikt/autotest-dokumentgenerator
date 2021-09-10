package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.engangsstønad;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.Ytelse;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.annenforelder.AnnenForelder;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.medlemskap.Medlemsskap;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.relasjontilbarn.RelasjonTilBarn;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonPropertyOrder({ "medlemsskap", "relasjonTilBarn", "annenForelder" })
public class Engangsstønad extends Ytelse {

    @Valid
    private Medlemsskap medlemsskap;
    private AnnenForelder annenForelder;
    @Valid
    private RelasjonTilBarn relasjonTilBarn;

}
