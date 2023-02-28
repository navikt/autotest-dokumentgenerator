package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.builder;

import no.nav.foreldrepenger.common.domain.BrukerRolle;
import no.nav.foreldrepenger.common.domain.Søknad;
import no.nav.foreldrepenger.common.domain.Ytelse;
import no.nav.foreldrepenger.common.domain.felles.annenforelder.AnnenForelder;
import no.nav.foreldrepenger.common.domain.felles.medlemskap.Medlemsskap;
import no.nav.foreldrepenger.common.domain.felles.opptjening.Opptjening;
import no.nav.foreldrepenger.common.domain.felles.relasjontilbarn.RelasjonTilBarn;
import no.nav.foreldrepenger.common.domain.foreldrepenger.Dekningsgrad;
import no.nav.foreldrepenger.common.domain.foreldrepenger.Foreldrepenger;
import no.nav.foreldrepenger.common.domain.foreldrepenger.Rettigheter;
import no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.Fordeling;
import no.nav.foreldrepenger.common.oppslag.dkif.Målform;

public class ForeldrepengerBuilder extends SøknadBuilder<ForeldrepengerBuilder> {
    protected AnnenForelder annenForelder;
    protected RelasjonTilBarn relasjonTilBarn;
    protected Rettigheter rettigheter;
    protected Dekningsgrad dekningsgrad;
    protected Opptjening opptjening;
    protected Fordeling fordeling;
    protected Medlemsskap medlemsskap;

    public ForeldrepengerBuilder(BrukerRolle brukerRolle) {
        this.medSøker(brukerRolle, Målform.standard());
    }

    @Override
    protected ForeldrepengerBuilder self() {
        return this;
    }

    @Override
    protected ForeldrepengerBuilder medYtelse(Ytelse ytelse) {
        this.ytelse = ytelse;
        return this;
    }

    public ForeldrepengerBuilder medAnnenForelder(AnnenForelder annenForelder) {
        this.annenForelder = annenForelder;
        return this;
    }

    public ForeldrepengerBuilder medRelasjonTilBarn(RelasjonTilBarn relasjonTilBarn) {
        this.relasjonTilBarn = relasjonTilBarn;
        return this;
    }

    public ForeldrepengerBuilder medRettigheter(Rettigheter rettigheter) {
        this.rettigheter = rettigheter;
        return this;
    }

    public ForeldrepengerBuilder medDekningsgrad(Dekningsgrad dekningsgrad) {
        this.dekningsgrad = dekningsgrad;
        return this;
    }

    public ForeldrepengerBuilder medOpptjening(Opptjening opptjening) {
        this.opptjening = opptjening;
        return this;
    }

    public ForeldrepengerBuilder medFordeling(Fordeling fordeling) {
        this.fordeling = fordeling;
        return this;
    }

    public ForeldrepengerBuilder medMedlemsskap(Medlemsskap medlemsskap) {
        this.medlemsskap = medlemsskap;
        return this;
    }

    @Override
    public Søknad build() {
        this.medYtelse(new Foreldrepenger(annenForelder, relasjonTilBarn, rettigheter, dekningsgrad, opptjening, fordeling, medlemsskap));
        return super.build();
    }
}
