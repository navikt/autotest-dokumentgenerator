package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.builder;

import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.BrukerRolle;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.Søknad;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.Ytelse;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.SpråkKode;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.foreldrepenger.Endringssøknad;

// TODO: Finn ut om søknadden skal bare inneholde endringer eller om det er avvik fra forrige som er endringene.
public class EndringssøknadBuilder extends SøknadBuilder<EndringssøknadBuilder> {

    private final Endringssøknad endringssøknadKladd = new Endringssøknad();

    public EndringssøknadBuilder(String saksnummer, BrukerRolle brukerRolle) {
        this.medSaksnummer(saksnummer);
        this.medSøker(brukerRolle, SpråkKode.NB);
    }

    @Override
    protected EndringssøknadBuilder self() {
        return this;
    }

    @Override
    protected EndringssøknadBuilder medYtelse(Ytelse ytelse) {
        søknadKladd.setYtelse(ytelse);
        return this;
    }

    public EndringssøknadBuilder medSaksnummer(String saksnummer) {
        endringssøknadKladd.setSaksnr(saksnummer);
        return this;
    }

    @Override
    public Søknad build() {
        return super.build();
    }
}
