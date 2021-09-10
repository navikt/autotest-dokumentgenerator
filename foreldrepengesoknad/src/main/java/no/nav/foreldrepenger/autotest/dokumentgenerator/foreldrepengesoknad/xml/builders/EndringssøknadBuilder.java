package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.builders;

import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.SøkersRolle;
import no.nav.vedtak.felles.xml.soeknad.endringssoeknad.v3.Endringssoeknad;
import no.nav.vedtak.felles.xml.soeknad.endringssoeknad.v3.ObjectFactory;
import no.nav.vedtak.felles.xml.soeknad.felles.v3.Ytelse;
import no.nav.vedtak.felles.xml.soeknad.uttak.v3.Fordeling;
import no.nav.vedtak.felles.xml.soeknad.v3.Soeknad;

public class EndringssøknadBuilder extends SøknadBuilder<EndringssøknadBuilder>{
    private Endringssoeknad endringssoeknadKladd;

    public EndringssøknadBuilder(String aktoerId, SøkersRolle søkerRolle) {
        endringssoeknadKladd = new Endringssoeknad();
        medSøker(aktoerId, søkerRolle);
    }
    @Override
    protected EndringssøknadBuilder medYtelse(Ytelse ytelse) {
        søknadKladd.setOmYtelse(setOmYtelseJAXBElement(
                (new ObjectFactory()).createEndringssoeknad((Endringssoeknad) ytelse)));
        return this;

    }
    @Override
    protected EndringssøknadBuilder self() {
        return this;
    }
    public EndringssøknadBuilder medFordeling(Fordeling fordeling) {
        endringssoeknadKladd.setFordeling(fordeling);
        return this;
    }
    public EndringssøknadBuilder medSaksnummer(String saksnummer) {
        endringssoeknadKladd.setSaksnummer(saksnummer);
        return this;
    }

    @Override
    public Soeknad build() {
        medYtelse(endringssoeknadKladd);
        return super.build();
    }


}
