package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper;

import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper.FordelingErketyper.fordeling;
import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper.MedlemsskapErketyper.medlemskapUtlandetForrige12mnd;
import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper.MedlemsskapErketyper.medlemsskapNorge;
import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper.OpptjeningErketyper.egenNaeringOgFrilansOpptjening;
import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper.OpptjeningErketyper.utenlandskArbeidsforhold;
import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper.RelasjonTilBarnErketyper.termin;
import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper.RettigheterErketyper.annenpartIkkeRettOgMorHarUføretrygd;
import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper.SøknadForeldrepengerErketyper.lagSøknadForeldrepengerFødsel;
import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper.UttaksperioderErketyper.utsettelsesperiode;
import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper.UttaksperioderErketyper.uttaksperiode;
import static no.nav.foreldrepenger.common.domain.Orgnummer.MAGIC_ORG;
import static no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.MorsAktivitet.ARBEID;
import static no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.MorsAktivitet.TRENGER_HJELP;
import static no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.MorsAktivitet.UFØRE;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.neovisionaries.i18n.CountryCode;

import no.nav.foreldrepenger.common.domain.BrukerRolle;
import no.nav.foreldrepenger.common.domain.Fødselsnummer;
import no.nav.foreldrepenger.common.domain.engangsstønad.Engangsstønad;
import no.nav.foreldrepenger.common.domain.felles.annenforelder.NorskForelder;
import no.nav.foreldrepenger.common.domain.felles.relasjontilbarn.FremtidigFødsel;
import no.nav.foreldrepenger.common.domain.foreldrepenger.Dekningsgrad;
import no.nav.foreldrepenger.common.domain.foreldrepenger.Foreldrepenger;
import no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.StønadskontoType;
import no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.UtsettelsesÅrsak;
import no.nav.foreldrepenger.common.domain.svangerskapspenger.Svangerskapspenger;
import no.nav.foreldrepenger.common.domain.svangerskapspenger.tilrettelegging.Tilrettelegging;

class SoeknadBuilderIntegritetTest {


    public static final LocalDate NOW = LocalDate.now();
    public static final LocalDate FØDSELSDATO = NOW.minusMonths(1);
    public static final LocalDate TERMINDATO = NOW.minusMonths(1).minusWeeks(3);

    @Test
    void verifiserAtDefaultVerdierBlirOverskrevetForForeldrepengeBuilder() {
        var fpStartdato = FØDSELSDATO;
        var fordeling = fordeling(
                uttaksperiode(StønadskontoType.FORELDREPENGER, fpStartdato, fpStartdato.plusWeeks(5).minusDays(1), ARBEID),
                utsettelsesperiode(UtsettelsesÅrsak.FRI, fpStartdato.plusWeeks(5), fpStartdato.plusWeeks(10).minusDays(1), TRENGER_HJELP),
                uttaksperiode(StønadskontoType.FORELDREPENGER, fpStartdato.plusWeeks(10), fpStartdato.plusWeeks(15).minusDays(1), ARBEID),
                utsettelsesperiode(UtsettelsesÅrsak.FRI, fpStartdato.plusWeeks(15), fpStartdato.plusWeeks(38).minusDays(1), TRENGER_HJELP),
                uttaksperiode(StønadskontoType.FORELDREPENGER, fpStartdato.plusWeeks(38), fpStartdato.plusWeeks(43).minusDays(1), UFØRE)
        ).build();
        var medlemsskap = medlemsskapNorge();
        var opptjening = egenNaeringOgFrilansOpptjening();
        var termin = termin(2, NOW.minusMonths(4));
        var rettigheter = annenpartIkkeRettOgMorHarUføretrygd();
        var norskForelder = new NorskForelder(new Fødselsnummer("111111111111"), "Frodig Salat");
        var dekningsgrad = Dekningsgrad.ÅTTI;
        var søknad = lagSøknadForeldrepengerFødsel(FØDSELSDATO, BrukerRolle.MOR)
                .medDekningsgrad(dekningsgrad)
                .medAnnenForelder(norskForelder)
                .medFordeling(fordeling)
                .medMedlemsskap(medlemsskap)
                .medOpptjening(opptjening)
                .medRelasjonTilBarn(termin)
                .medRettigheter(rettigheter)
                .build();


        var ytelse = søknad.getYtelse();
        assertThat(ytelse).isInstanceOf(Foreldrepenger.class);
        var foreldrepenger = (Foreldrepenger) ytelse;
        assertThat(foreldrepenger.fordeling()).isEqualTo(fordeling);
        assertThat(foreldrepenger.medlemsskap()).isEqualTo(medlemsskap);
        assertThat(foreldrepenger.opptjening()).isEqualTo(opptjening);
        assertThat(foreldrepenger.relasjonTilBarn())
                .isInstanceOf(FremtidigFødsel.class)
                .isEqualTo(termin);
        assertThat(foreldrepenger.rettigheter()).isEqualTo(rettigheter);
        assertThat(foreldrepenger.dekningsgrad()).isEqualTo(dekningsgrad);
        assertThat(foreldrepenger.annenForelder())
                .isInstanceOf(NorskForelder.class)
                .isEqualTo(norskForelder);

    }

    @Test
    void verifiserAtDefaultVerdierBlirOverskrevetForEngangsstønad() {
        var medlemsskap = medlemskapUtlandetForrige12mnd();
        var termin = termin(2, NOW.minusMonths(4));
        var søknad = SøknadEngangsstønadErketyper.lagEngangstønadAdopsjon(BrukerRolle.MOR, FØDSELSDATO, false)
                .medMedlemsskap(medlemsskap)
                .medRelasjonTilBarn(termin)
                .build();

        var ytelse = søknad.getYtelse();
        assertThat(ytelse).isInstanceOf(Engangsstønad.class);
        var engangsstønad = (Engangsstønad) ytelse;
        assertThat(engangsstønad.medlemsskap()).isEqualTo(medlemsskap);
        assertThat(engangsstønad.relasjonTilBarn())
                .isInstanceOf(FremtidigFødsel.class)
                .isEqualTo(termin);
    }

    @Test
    void verifiserAtDefaultVerdierBlirOverskrevetForSvangerskapspenger() {
        var medlemsskap = medlemskapUtlandetForrige12mnd();
        var opptjening = utenlandskArbeidsforhold(CountryCode.AR);
        var tilrettelegging1 = TilretteleggingsErketyper.ingenTilrettelegging(
                LocalDate.now(),
                LocalDate.now(),
                ArbeidsforholdErketyper.virksomhet(MAGIC_ORG));
        var tilrettelegging2 = TilretteleggingsErketyper.helTilrettelegging(
                LocalDate.now(),
                LocalDate.now(),
                ArbeidsforholdErketyper.virksomhet(MAGIC_ORG));
        List<Tilrettelegging> tilrettelegginger = List.of(tilrettelegging1, tilrettelegging2);
        var søknad = SøknadSvangerskapspengerErketyper.lagSvangerskapspengerSøknad(
                BrukerRolle.MOR, TERMINDATO.plusWeeks(3), tilrettelegginger)
                .medMedlemsskap(medlemsskap)
                .medOpptjening(opptjening)
                .medFødselsdato(FØDSELSDATO)
                .medTermindato(TERMINDATO)
                .build();

        var ytelse = søknad.getYtelse();
        assertThat(ytelse).isInstanceOf(Svangerskapspenger.class);
        var svangerskapspenger = (Svangerskapspenger) ytelse;
        assertThat(svangerskapspenger.medlemsskap()).isEqualTo(medlemsskap);
        assertThat(svangerskapspenger.tilrettelegging()).containsExactly(tilrettelegging1, tilrettelegging2);
    }

}
