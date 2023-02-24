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

abstract class SøknadBuilder<B extends SøknadBuilder> {
    protected LocalDate mottattdato;
    protected Søker søker;
    protected String tilleggsopplysninger;
    protected List<Vedlegg> vedlegg;

    protected Ytelse ytelse;

    SøknadBuilder() {
    }

    protected abstract B self();

    protected abstract B medYtelse(Ytelse ytelse);

    public B medMottattDato(LocalDate mottattdato) {
        this.mottattdato = mottattdato;
        return this.self();
    }

    public B medSøker(BrukerRolle brukerRolle, Målform språkKode) {
        this.søker = new Søker(brukerRolle, språkKode);
        return this.self();
    }

    public B medTilleggsopplysninger(String tilleggsopplysninger) {
        this.tilleggsopplysninger = tilleggsopplysninger;
        return this.self();
    }

    public B medVedlegg(List<Vedlegg> vedlegg) {
        this.vedlegg = vedlegg;
        return this.self();
    }

    protected Søknad build() {
        if (tilleggsopplysninger == null) this.tilleggsopplysninger = "";
        if (mottattdato == null) this.mottattdato = LocalDate.now();
        if(vedlegg == null) this.vedlegg = Collections.emptyList();
        return new Søknad(this.mottattdato, this.søker, this.ytelse, this.tilleggsopplysninger, this.vedlegg);
    }
}
