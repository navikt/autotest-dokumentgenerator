package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.builder;

import no.nav.foreldrepenger.common.domain.BrukerRolle;
import no.nav.foreldrepenger.common.domain.Saksnummer;
import no.nav.foreldrepenger.common.domain.felles.annenforelder.AnnenForelder;
import no.nav.foreldrepenger.common.domain.felles.medlemskap.Medlemsskap;
import no.nav.foreldrepenger.common.domain.felles.opptjening.Opptjening;
import no.nav.foreldrepenger.common.domain.felles.relasjontilbarn.RelasjonTilBarn;
import no.nav.foreldrepenger.common.domain.foreldrepenger.Dekningsgrad;
import no.nav.foreldrepenger.common.domain.foreldrepenger.Endringssøknad;
import no.nav.foreldrepenger.common.domain.foreldrepenger.Rettigheter;
import no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.Fordeling;

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
        super.medAnnenForelder(annenForelder);
        return this;
    }

    @Override
    public EndringssøknadBuilder medRelasjonTilBarn(RelasjonTilBarn relasjonTilBarn) {
        super.medRelasjonTilBarn(relasjonTilBarn);
        return this;
    }

    @Override
    public EndringssøknadBuilder medRettigheter(Rettigheter rettigheter) {
        super.medRettigheter(rettigheter);
        return this;
    }

    @Override
    public EndringssøknadBuilder medDekningsgrad(Dekningsgrad dekningsgrad) {
        super.medDekningsgrad(dekningsgrad);
        return this;
    }

    @Override
    public EndringssøknadBuilder medOpptjening(Opptjening opptjening) {
        super.medOpptjening(opptjening);
        return this;
    }

    @Override
    public EndringssøknadBuilder medFordeling(Fordeling fordeling) {
        super.medFordeling(fordeling);
        return this;
    }

    @Override
    public EndringssøknadBuilder medMedlemsskap(Medlemsskap medlemsskap) {
        super.medMedlemsskap(medlemsskap);
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
