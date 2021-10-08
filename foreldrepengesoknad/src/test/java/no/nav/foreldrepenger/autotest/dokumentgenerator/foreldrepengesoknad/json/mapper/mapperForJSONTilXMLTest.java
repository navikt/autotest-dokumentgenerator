package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.mapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper.SøknadForeldrepengerErketyper;
import no.nav.foreldrepenger.common.domain.AktørId;
import no.nav.foreldrepenger.common.domain.BrukerRolle;
import no.nav.foreldrepenger.common.domain.Fødselsnummer;
import no.nav.foreldrepenger.common.domain.felles.annenforelder.NorskForelder;
import no.nav.foreldrepenger.common.innsending.mappers.V3ForeldrepengerDomainMapper;
import no.nav.foreldrepenger.common.innsyn.SøknadEgenskap;
import no.nav.foreldrepenger.common.oppslag.Oppslag;

class mapperForJSONTilXMLTest {

    Oppslag oppslag = Mockito.mock(Oppslag.class);

    @Test
    public void sjekkerMapping() {
        var fødselsdato = LocalDate.now().minusWeeks(3);
        var søknadJson = SøknadForeldrepengerErketyper.lagSøknadForeldrepengerTermin(fødselsdato, BrukerRolle.MOR)
                .medOpptjening(no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper.OpptjeningErketyper.medFrilansOpptjening())
                .medAnnenForelder(new NorskForelder(new Fødselsnummer("12345678910")));

        when(oppslag.aktørId(any())).thenReturn(new AktørId("111111111111"));
        var mapper = new V3ForeldrepengerDomainMapper(oppslag);
        var søknadXML = mapper.tilXML(søknadJson.build(), new AktørId("22222222222"), SøknadEgenskap.INITIELL_FORELDREPENGER);
        assertThat(søknadXML).isNotNull();
    }
}
