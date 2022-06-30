package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.builder;

import java.time.LocalDate;
import java.util.Collections;

import no.nav.foreldrepenger.common.domain.BrukerRolle;
import no.nav.foreldrepenger.common.domain.Saksnummer;
import no.nav.foreldrepenger.common.domain.Søker;
import no.nav.foreldrepenger.common.domain.felles.annenforelder.AnnenForelder;
import no.nav.foreldrepenger.common.domain.felles.relasjontilbarn.Fødsel;
import no.nav.foreldrepenger.common.domain.foreldrepenger.Endringssøknad;
import no.nav.foreldrepenger.common.domain.foreldrepenger.Rettigheter;
import no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.Fordeling;
import no.nav.foreldrepenger.common.oppslag.dkif.Målform;

public class EndringssøknadBuilder {

    private final Endringssøknad.EndringssøknadBuilder builder = Endringssøknad.EndringssøkandsBuilder();

    private boolean mottattDatoSatt = false;

    public EndringssøknadBuilder(Saksnummer saksnummer, BrukerRolle brukerRolle) {
        this.medSøker(new Søker(brukerRolle, Målform.standard()));
        this.medSaksnummer(saksnummer);
    }

    public EndringssøknadBuilder medMottattDato(LocalDate mottattDato) {
        mottattDatoSatt = true;
        builder.mottattDato(mottattDato);
        return this;
    }

    public EndringssøknadBuilder medSøker(Søker søker) {
        builder.søker(søker);
        return this;
    }

    public EndringssøknadBuilder medFordeling(Fordeling fordeling) {
        builder.fordeling(fordeling);
        return this;
    }

    public EndringssøknadBuilder medAnnenForelder(AnnenForelder annenForelder) {
        builder.annenForelder(annenForelder);
        return this;
    }

    public EndringssøknadBuilder medFødsel(Fødsel fødsel) {
        builder.fødsel(fødsel);
        return this;
    }

    public EndringssøknadBuilder medRettigheter(Rettigheter rettigheter) {
        builder.rettigheter(rettigheter);
        return this;
    }

    public EndringssøknadBuilder medSaksnummer(Saksnummer saksnummer) {
        builder.saksnr(saksnummer);
        return this;
    }

    public Endringssøknad build() {
        if(!mottattDatoSatt) {
            builder.mottattDato(LocalDate.now());
        }
        builder.vedlegg(Collections.emptyList());
        var engangssøknad = builder.build();
        engangssøknad.setTilleggsopplysninger("");
        return engangssøknad;
    }
}
