package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.builder;

import no.nav.foreldrepenger.common.domain.BrukerRolle;
import no.nav.foreldrepenger.common.domain.Søknad;
import no.nav.foreldrepenger.common.domain.Ytelse;
import no.nav.foreldrepenger.common.domain.engangsstønad.Engangsstønad;
import no.nav.foreldrepenger.common.domain.felles.medlemskap.Medlemsskap;
import no.nav.foreldrepenger.common.domain.felles.relasjontilbarn.RelasjonTilBarn;
import no.nav.foreldrepenger.common.oppslag.dkif.Målform;

import javax.validation.Valid;

public class EngangsstønadBuilder extends SøknadBuilder<EngangsstønadBuilder> {

    private Medlemsskap medlemsskap;
    private RelasjonTilBarn relasjonTilBarn;


    public EngangsstønadBuilder(BrukerRolle brukerRolle) {
        this.medSøker(brukerRolle, Målform.standard());
    }

    @Override
    protected EngangsstønadBuilder self() {
        return this;
    }

    @Override
    protected EngangsstønadBuilder medYtelse(Ytelse ytelse) {
        this.ytelse = ytelse;
        return this;
    }

    public EngangsstønadBuilder medRelasjonTilBarn(RelasjonTilBarn relasjonTilBarn) {
        this.relasjonTilBarn = relasjonTilBarn;
        return this;
    }

    public EngangsstønadBuilder medMedlemsskap(Medlemsskap medlemsskap) {
        this.medlemsskap = medlemsskap;
        return this;
    }

    @Override
    public Søknad build() {
        this.medYtelse(new Engangsstønad(this.medlemsskap, this.relasjonTilBarn));
        return super.build();
    }
}
