package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.builder;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.BrukerRolle;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.Søker;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.Søknad;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.Ytelse;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.SpråkKode;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.Vedlegg;

public abstract class SøknadBuilder<B extends SøknadBuilder> {
    protected Søknad søknadKladd = new Søknad();

    SøknadBuilder() {
    }

    protected abstract B self();

    protected abstract SøknadBuilder medYtelse(Ytelse ytelse);

    public B medMottatdato(LocalDate mottatdato) {
        this.søknadKladd.setMottattdato(mottatdato);
        return this.self();
    }

    public B medSøker(BrukerRolle brukerRolle, SpråkKode språkKode) {
        this.søknadKladd.setSøker(new Søker(brukerRolle, språkKode));
        return this.self();
    }


    public B medBegrunnelseForSenSøknad(String begrunnelseForSenSoeknad) {
        this.søknadKladd.setBegrunnelseForSenSøknad(begrunnelseForSenSoeknad);
        return this.self();
    }

    public B medTilleggsopplysninger(String tilleggsopplysninger) {
        this.søknadKladd.setTilleggsopplysninger(tilleggsopplysninger);
        return this.self();
    }

    public B medVedlegg(List<Vedlegg> vedleggListe) {
        if (vedleggListe != null) {
            vedleggListe.forEach(vedlegg -> this.søknadKladd.getVedlegg().add(vedlegg));
        }

        return this.self();
    }

    protected Søknad build() {
        if (søknadKladd.getBegrunnelseForSenSøknad() == null) {
            søknadKladd.setBegrunnelseForSenSøknad(null);
        }
        if (søknadKladd.getTilleggsopplysninger() == null) {
            søknadKladd.setTilleggsopplysninger("");
        }
        if (søknadKladd.getMottattdato() == null) {
            søknadKladd.setMottattdato(LocalDate.now());
        }
        if (søknadKladd.getVedlegg() == null) {
            søknadKladd.setVedlegg(Collections.emptyList());
        }
        return søknadKladd;
    }

}
