package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.erketyper;

import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.SøkersRolle;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.builders.EndringssøknadBuilder;
import no.nav.vedtak.felles.xml.soeknad.uttak.v3.Fordeling;

public class SøknadEndringErketyper {

    private SøknadEndringErketyper() {
        // Skal ikke instansieres
    }

    public static EndringssøknadBuilder lagEndringssøknad(String aktoerId, SøkersRolle søkersRolle, Fordeling fordeling,
            long saksnummer) {
        return new EndringssøknadBuilder(aktoerId, søkersRolle)
                .medFordeling(fordeling)
                .medSaksnummer(String.valueOf(saksnummer));
    }
}
