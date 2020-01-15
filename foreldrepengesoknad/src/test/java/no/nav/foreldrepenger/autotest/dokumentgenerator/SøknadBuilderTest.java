package no.nav.foreldrepenger.autotest.dokumentgenerator;

import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.SøkersRolle;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.builders.EngangstønadBuilder;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.builders.ForeldrepengerBuilder;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.builders.SvangerskapspengerBuilder;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.builders.SøknadBuilder;
import no.nav.vedtak.felles.xml.soeknad.svangerskapspenger.v1.Tilrettelegging;
import no.nav.vedtak.felles.xml.soeknad.v3.Soeknad;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SøknadBuilderTest {

    @Test
    public void foreldrepengerBuilderByggerRiktig() {
        ForeldrepengerBuilder søknad = new ForeldrepengerBuilder("1234", SøkersRolle.MOR)
                .medDekningsgrad("80")
                .medMottattDato(LocalDate.now().minusWeeks(10));

        assertThat(søknad, instanceOf(SøknadBuilder.class));
        assertThat(søknad, instanceOf(ForeldrepengerBuilder.class));
        assertThatCode(søknad::build).doesNotThrowAnyException();
        assertThat(søknad.build(), instanceOf(Soeknad.class));
        assertEquals("mottatt dato er default verdi, medMottattDato overskriver ikke default", 0,
                søknad.build().getMottattDato().compareTo(LocalDate.now().minusWeeks(10)));


    }

    @Test
    public void svangerskappengerBuilderByggerRiktig() {
        SvangerskapspengerBuilder søknad = new SvangerskapspengerBuilder("1234",
                SøkersRolle.FAR, Collections.singletonList(new Tilrettelegging()))
                .medTermindato(LocalDate.now().plusWeeks(8))
                .medTilleggsopplysninger("Test Test");


        assertThat(søknad, instanceOf(SøknadBuilder.class));
        assertThat(søknad, instanceOf(SvangerskapspengerBuilder.class));
        assertThatCode(søknad::build).doesNotThrowAnyException();
        assertThat(søknad.build(), instanceOf(Soeknad.class));
        assertEquals("Tilleggsopplysninger er default verdi, medTilleggsopplysninger overskriver ikke default",
                0, søknad.build().getTilleggsopplysninger().compareTo("Test Test"));
    }



    @Test
    public void engangsstønadBuilderByggerRiktig() {
        EngangstønadBuilder søknad = new EngangstønadBuilder("113322", SøkersRolle.MEDMOR)
                .medBegrunnelseForSenSoeknad("Viste ikke at man må søke")
                .medAnnenForelder("6622");
        assertThat(søknad, instanceOf(SøknadBuilder.class));
        assertThat(søknad, instanceOf(EngangstønadBuilder.class));
        assertThatCode(søknad::build).doesNotThrowAnyException();
        assertThat(søknad.build(), instanceOf(Soeknad.class));
        assertEquals("Begrunnelse for sen søknad er default verdi, medBegrunnelseForSenSoeknad overskriver ikke default",
                0, søknad.build().getBegrunnelseForSenSoeknad().compareTo("Viste ikke at man må søke"));

    }

}
