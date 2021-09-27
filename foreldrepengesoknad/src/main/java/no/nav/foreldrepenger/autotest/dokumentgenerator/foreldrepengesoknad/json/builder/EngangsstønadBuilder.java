package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.builder;

import no.nav.foreldrepenger.common.domain.BrukerRolle;
import no.nav.foreldrepenger.common.domain.Søknad;
import no.nav.foreldrepenger.common.domain.Ytelse;
import no.nav.foreldrepenger.common.domain.engangsstønad.Engangsstønad;
import no.nav.foreldrepenger.common.domain.felles.annenforelder.AnnenForelder;
import no.nav.foreldrepenger.common.domain.felles.medlemskap.Medlemsskap;
import no.nav.foreldrepenger.common.domain.felles.relasjontilbarn.RelasjonTilBarn;
import no.nav.foreldrepenger.common.oppslag.dkif.Målform;

public class EngangsstønadBuilder extends SøknadBuilder<EngangsstønadBuilder> {

    private final Engangsstønad.EngangsstønadBuilder engangsstønadBuilder = Engangsstønad.builder();

    public EngangsstønadBuilder(BrukerRolle brukerRolle) {
        this.medSøker(brukerRolle, Målform.standard());
    }

    @Override
    protected EngangsstønadBuilder self() {
        return this;
    }

    @Override
    protected EngangsstønadBuilder medYtelse(Ytelse ytelse) {
        søknadKladd.ytelse(ytelse);
        return this;
    }

    public EngangsstønadBuilder medAnnenForelder(AnnenForelder annenForelder) {
        engangsstønadBuilder.annenForelder(annenForelder);
        return this;
    }

    public EngangsstønadBuilder medRelasjonTilBarn(RelasjonTilBarn relasjonTilBarn) {
        engangsstønadBuilder.relasjonTilBarn(relasjonTilBarn);
        return this;
    }

    public EngangsstønadBuilder medMedlemsskap(Medlemsskap medlemsskap) {
        engangsstønadBuilder.medlemsskap(medlemsskap);
        return this;
    }

    @Override
    public Søknad build() {
        this.medYtelse(this.engangsstønadBuilder.build());
        return super.build();
    }
}
