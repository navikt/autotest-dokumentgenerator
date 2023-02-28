package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.builder;

import no.nav.foreldrepenger.common.domain.BrukerRolle;
import no.nav.foreldrepenger.common.domain.Saksnummer;
import no.nav.foreldrepenger.common.domain.Søker;
import no.nav.foreldrepenger.common.domain.felles.Vedlegg;
import no.nav.foreldrepenger.common.domain.felles.annenforelder.AnnenForelder;
import no.nav.foreldrepenger.common.domain.felles.medlemskap.Medlemsskap;
import no.nav.foreldrepenger.common.domain.felles.opptjening.Opptjening;
import no.nav.foreldrepenger.common.domain.felles.relasjontilbarn.RelasjonTilBarn;
import no.nav.foreldrepenger.common.domain.foreldrepenger.Dekningsgrad;
import no.nav.foreldrepenger.common.domain.foreldrepenger.Endringssøknad;
import no.nav.foreldrepenger.common.domain.foreldrepenger.Rettigheter;
import no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.Fordeling;
import no.nav.foreldrepenger.common.oppslag.dkif.Målform;

import java.time.LocalDate;
import java.util.List;

public class EndringssøknadBuilder extends ForeldrepengerBuilder {

    private Saksnummer saksnr;

    public EndringssøknadBuilder(Saksnummer saksnummer, BrukerRolle brukerRolle) {
        super(brukerRolle);
        this.saksnr = saksnummer;
    }

    public EndringssøknadBuilder medSaksnummer(Saksnummer saksnummer) {
        this.saksnr = saksnummer;
        return this;
    }

    @Override
    public EndringssøknadBuilder medAnnenForelder(AnnenForelder annenForelder) {
        this.annenForelder = annenForelder;
        return this;
    }

    @Override
    public EndringssøknadBuilder medRelasjonTilBarn(RelasjonTilBarn relasjonTilBarn) {
        this.relasjonTilBarn = relasjonTilBarn;
        return this;
    }

    @Override
    public EndringssøknadBuilder medRettigheter(Rettigheter rettigheter) {
        this.rettigheter = rettigheter;
        return this;
    }

    @Override
    public EndringssøknadBuilder medDekningsgrad(Dekningsgrad dekningsgrad) {
        super.medDekningsgrad(dekningsgrad);
        return this;
    }

    @Override
    public EndringssøknadBuilder medOpptjening(Opptjening opptjening) {
        this.opptjening = opptjening;
        return this;
    }

    @Override
    public EndringssøknadBuilder medFordeling(Fordeling fordeling) {
        this.fordeling = fordeling;
        return this;
    }

    @Override
    public EndringssøknadBuilder medMedlemsskap(Medlemsskap medlemsskap) {
        this.medlemsskap = medlemsskap;
        return this;
    }

    // Generell søknad felter
    @Override
    public EndringssøknadBuilder medMottattDato(LocalDate mottattdato) {
        this.mottattdato = mottattdato;
        return this;
    }

    @Override
    public EndringssøknadBuilder medSøker(BrukerRolle brukerRolle, Målform språkKode) {
        this.søker = new Søker(brukerRolle, språkKode);
        return this;
    }

    @Override
    public EndringssøknadBuilder medTilleggsopplysninger(String tilleggsopplysninger) {
        this.tilleggsopplysninger = tilleggsopplysninger;
        return this;
    }

    @Override
    public EndringssøknadBuilder medVedlegg(List<Vedlegg> vedlegg) {
        this.vedlegg = vedlegg;
        return this;
    }

    @Override
    public Endringssøknad build() {
        var søknad = super.build();
        return new Endringssøknad(
                søknad.getMottattdato(),
                søknad.getSøker(),
                søknad.getYtelse(),
                søknad.getTilleggsopplysninger(),
                søknad.getVedlegg(),
                this.saksnr
        );
    }
}
