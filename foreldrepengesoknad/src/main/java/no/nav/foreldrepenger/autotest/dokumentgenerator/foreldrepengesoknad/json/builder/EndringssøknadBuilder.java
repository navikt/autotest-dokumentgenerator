package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.builder;

import no.nav.foreldrepenger.common.domain.BrukerRolle;
import no.nav.foreldrepenger.common.domain.Søknad;
import no.nav.foreldrepenger.common.domain.Ytelse;
import no.nav.foreldrepenger.common.domain.foreldrepenger.Endringssøknad;
import no.nav.foreldrepenger.common.oppslag.dkif.Målform;

// TODO: Finn ut om søknadden skal bare inneholde endringer eller om det er avvik fra forrige som er endringene.
public class EndringssøknadBuilder extends SøknadBuilder<EndringssøknadBuilder> {

    private final Endringssøknad.EndringssøknadBuilder endringssøknadBuilder = Endringssøknad.EndringssøkandsBuilder();

    public EndringssøknadBuilder(String saksnummer, BrukerRolle brukerRolle) {
        this.medSaksnummer(saksnummer);
        this.medSøker(brukerRolle, Målform.standard());
    }

    @Override
    protected EndringssøknadBuilder self() {
        return this;
    }

    @Override
    protected EndringssøknadBuilder medYtelse(Ytelse ytelse) {
        søknadKladd.ytelse(ytelse);
        return this;
    }

    public EndringssøknadBuilder medSaksnummer(String saksnummer) {
        endringssøknadBuilder.saksnr(saksnummer);
        return this;
    }

    @Override
    public Søknad build() {
        return super.build();
    }
}
