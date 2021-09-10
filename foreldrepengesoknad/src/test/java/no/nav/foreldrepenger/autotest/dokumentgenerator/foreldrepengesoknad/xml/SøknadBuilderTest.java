package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDate;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.builders.EngangstønadBuilder;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.builders.ForeldrepengerBuilder;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.builders.SvangerskapspengerBuilder;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.builders.SøknadBuilder;
import no.nav.vedtak.felles.xml.soeknad.svangerskapspenger.v1.Tilrettelegging;
import no.nav.vedtak.felles.xml.soeknad.v3.Soeknad;


public class SøknadBuilderTest {

    @Test
    public void foreldrepengerBuilderByggerRiktig() {
        ForeldrepengerBuilder søknad = new ForeldrepengerBuilder("1234", SøkersRolle.MOR)
                .medDekningsgrad("80")
                .medMottattDato(LocalDate.now().minusWeeks(10));

        assertThat(søknad)
                .isInstanceOf(SøknadBuilder.class)
                .isInstanceOf(ForeldrepengerBuilder.class);
        assertThatCode(søknad::build).doesNotThrowAnyException();
        assertThat(søknad.build()).isInstanceOf(Soeknad.class);
        assertThat(søknad.build().getMottattDato()).isEqualTo(LocalDate.now().minusWeeks(10));
    }

    @Test
    public void svangerskappengerBuilderByggerRiktig() {
        SvangerskapspengerBuilder søknad = new SvangerskapspengerBuilder("1234",
                SøkersRolle.FAR, Collections.singletonList(new Tilrettelegging()))
                .medTermindato(LocalDate.now().plusWeeks(8))
                .medTilleggsopplysninger("Test Test");

        assertThat(søknad)
                .isInstanceOf(SøknadBuilder.class)
                .isInstanceOf(SvangerskapspengerBuilder.class);
        assertThatCode(søknad::build).doesNotThrowAnyException();
        assertThat(søknad.build()).isInstanceOf(Soeknad.class);
        assertThat(søknad.build().getTilleggsopplysninger()).isEqualTo("Test Test");
    }



    @Test
    public void engangsstønadBuilderByggerRiktig() {
        EngangstønadBuilder søknad = new EngangstønadBuilder("113322", SøkersRolle.MEDMOR)
                .medBegrunnelseForSenSoeknad("Viste ikke at man må søke")
                .medAnnenForelder("6622");

        assertThat(søknad)
                .isInstanceOf(SøknadBuilder.class)
                .isInstanceOf(EngangstønadBuilder.class);
        assertThatCode(søknad::build).doesNotThrowAnyException();
        assertThat(søknad.build()).isInstanceOf(Soeknad.class);
        assertThat(søknad.build().getBegrunnelseForSenSoeknad()).isEqualTo("Viste ikke at man må søke");

    }

}
