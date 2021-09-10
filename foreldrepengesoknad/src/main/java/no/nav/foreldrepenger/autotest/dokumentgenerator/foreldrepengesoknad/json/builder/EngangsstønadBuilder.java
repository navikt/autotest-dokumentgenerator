package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.builder;

import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.BrukerRolle;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.Søknad;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.Ytelse;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.engangsstønad.Engangsstønad;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.SpråkKode;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.annenforelder.AnnenForelder;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.medlemskap.Medlemsskap;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.relasjontilbarn.RelasjonTilBarn;

public class EngangsstønadBuilder extends SøknadBuilder<EngangsstønadBuilder> {

    private final Engangsstønad engangsstønadKladd = new Engangsstønad();

    public EngangsstønadBuilder(BrukerRolle brukerRolle) {
        this.medSøker(brukerRolle, SpråkKode.NB);
    }

    @Override
    protected EngangsstønadBuilder self() {
        return this;
    }

    @Override
    protected EngangsstønadBuilder medYtelse(Ytelse ytelse) {
        søknadKladd.setYtelse(ytelse);
        return this;
    }

    public EngangsstønadBuilder medAnnenForelder(AnnenForelder annenForelder) {
        engangsstønadKladd.setAnnenForelder(annenForelder);
        return this;
    }

    public EngangsstønadBuilder medRelasjonTilBarn(RelasjonTilBarn relasjonTilBarn) {
        engangsstønadKladd.setRelasjonTilBarn(relasjonTilBarn);
        return this;
    }

    public EngangsstønadBuilder medMedlemsskap(Medlemsskap medlemsskap) {
        engangsstønadKladd.setMedlemsskap(medlemsskap);
        return this;
    }

    @Override
    public Søknad build() {
        this.medYtelse(this.engangsstønadKladd);
        return super.build();
    }
}
