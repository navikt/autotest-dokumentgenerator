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

    protected final Foreldrepenger.ForeldrepengerBuilder builder = Foreldrepenger.builder();

    public ForeldrepengerBuilder(BrukerRolle brukerRolle) {
        this.medSøker(brukerRolle, Målform.standard());
    }

    @Override
    protected ForeldrepengerBuilder self() {
        return this;
    }

    @Override
    protected ForeldrepengerBuilder medYtelse(Ytelse ytelse) {
        søknadKladd.ytelse(ytelse);
        return this;
    }

    public ForeldrepengerBuilder medAnnenForelder(AnnenForelder annenForelder) {
        builder.annenForelder(annenForelder);
        return this;
    }

    public ForeldrepengerBuilder medRelasjonTilBarn(RelasjonTilBarn relasjonTilBarn) {
        builder.relasjonTilBarn(relasjonTilBarn);
        return this;
    }

    public ForeldrepengerBuilder medRettigheter(Rettigheter rettigheter) {
        builder.rettigheter(rettigheter);
        return this;
    }

    public ForeldrepengerBuilder medDekningsgrad(Dekningsgrad dekningsgrad) {
        builder.dekningsgrad(dekningsgrad);
        return this;
    }

    public ForeldrepengerBuilder medOpptjening(Opptjening opptjening) {
        builder.opptjening(opptjening);
        return this;
    }

    public ForeldrepengerBuilder medFordeling(Fordeling fordeling) {
        builder.fordeling(fordeling);
        return this;
    }

    public ForeldrepengerBuilder medMedlemsskap(Medlemsskap medlemsskap) {
        builder.medlemsskap(medlemsskap);
        return this;
    }

    @Override
    public Søknad build() {
        this.medYtelse(this.builder.build());
        return super.build();
    }
}
