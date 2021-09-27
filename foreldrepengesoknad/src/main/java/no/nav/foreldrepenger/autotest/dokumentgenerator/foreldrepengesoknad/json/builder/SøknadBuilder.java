package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.builder;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import no.nav.foreldrepenger.common.domain.BrukerRolle;
import no.nav.foreldrepenger.common.domain.Søker;
import no.nav.foreldrepenger.common.domain.Søknad;
import no.nav.foreldrepenger.common.domain.Ytelse;
import no.nav.foreldrepenger.common.domain.felles.Vedlegg;
import no.nav.foreldrepenger.common.oppslag.dkif.Målform;

public abstract class SøknadBuilder<B extends SøknadBuilder> {
    protected Søknad.SøknadBuilder søknadKladd = Søknad.builder();

    private boolean mottattDatoSatt = false;

    SøknadBuilder() {
    }

    protected abstract B self();

    protected abstract B medYtelse(Ytelse ytelse);

    public B medMottatdato(LocalDate mottatdato) {
        this.søknadKladd.mottattdato(mottatdato);
        this.mottattDatoSatt = true;
        return this.self();
    }

    public B medSøker(BrukerRolle brukerRolle, Målform språkKode) {
        this.søknadKladd.søker(new Søker(brukerRolle, språkKode));
        return this.self();
    }


    public B medBegrunnelseForSenSøknad(String begrunnelseForSenSoeknad) {
        this.søknadKladd.begrunnelseForSenSøknad(begrunnelseForSenSoeknad);
        return this.self();
    }

    public B medTilleggsopplysninger(String tilleggsopplysninger) {
        this.søknadKladd.tilleggsopplysninger(tilleggsopplysninger);
        return this.self();
    }

    public B medVedlegg(List<Vedlegg> vedleggListe) {
        this.søknadKladd.vedlegg(vedleggListe);
        return this.self();
    }

    protected Søknad build() {
        søknadKladd.tilleggsopplysninger("");
        if(!mottattDatoSatt) {
            søknadKladd.mottattdato(LocalDate.now());
        }
        søknadKladd.vedlegg(Collections.emptyList());
        return søknadKladd.build();
    }
}
