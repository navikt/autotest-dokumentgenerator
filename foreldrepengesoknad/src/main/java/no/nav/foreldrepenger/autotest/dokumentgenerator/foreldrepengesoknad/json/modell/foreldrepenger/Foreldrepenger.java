package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.foreldrepenger;

import javax.validation.Valid;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.Ytelse;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.annenforelder.AnnenForelder;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.medlemskap.Medlemsskap;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.opptjening.Opptjening;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.relasjontilbarn.RelasjonTilBarn;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.foreldrepenger.fordeling.Fordeling;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Foreldrepenger extends Ytelse {

    private AnnenForelder annenForelder;
    @Valid
    private RelasjonTilBarn relasjonTilBarn;
    private Rettigheter rettigheter;
    private Dekningsgrad dekningsgrad;
    @Valid
    private Opptjening opptjening;
    @Valid
    private Fordeling fordeling;
    @Valid
    private Medlemsskap medlemsskap;



}
