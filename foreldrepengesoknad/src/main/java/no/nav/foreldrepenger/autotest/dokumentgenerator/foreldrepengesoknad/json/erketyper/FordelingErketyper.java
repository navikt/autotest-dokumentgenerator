package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper;

import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper.UttaksperioderErketyper.uttaksperiode;
import static no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.StønadskontoType.FELLESPERIODE;
import static no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.StønadskontoType.FORELDREPENGER;
import static no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.StønadskontoType.FORELDREPENGER_FØR_FØDSEL;
import static no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.StønadskontoType.MØDREKVOTE;

import java.time.LocalDate;
import java.util.Arrays;

import no.nav.foreldrepenger.common.domain.ArbeidsgiverIdentifikator;
import no.nav.foreldrepenger.common.domain.BrukerRolle;
import no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.Fordeling;
import no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.LukketPeriodeMedVedlegg;
import no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.StønadskontoType;

public final class FordelingErketyper {

    private FordelingErketyper() {
    }

    public static Fordeling fordelingHappyCase(LocalDate familehendelseDato, BrukerRolle søkerRolle) {
        if (søkerRolle == BrukerRolle.MOR) {
            return fordelingMorHappyCaseLong(familehendelseDato);
        } else {
            return fordelingFarHappyCase(familehendelseDato);
        }
    }

    public static Fordeling fordelingMorHappyCase(LocalDate familehendelseDato) {
        return generiskFordeling(
                uttaksperiode(FORELDREPENGER_FØR_FØDSEL, familehendelseDato.minusWeeks(3),
                        familehendelseDato.minusDays(1)),
                uttaksperiode(MØDREKVOTE, familehendelseDato, familehendelseDato.plusWeeks(10)));
    }


    public static Fordeling fordelingMorHappyCaseLong(LocalDate familehendelseDato) {
        return generiskFordeling(
                uttaksperiode(FORELDREPENGER_FØR_FØDSEL, familehendelseDato.minusWeeks(3),
                        familehendelseDato.minusDays(1)),
                uttaksperiode(MØDREKVOTE, familehendelseDato, familehendelseDato.plusWeeks(15).minusDays(1)),
                uttaksperiode(FELLESPERIODE, familehendelseDato.plusWeeks(15),
                        familehendelseDato.plusWeeks(31).minusDays(1)));
    }

    public static Fordeling fordelingFarHappyCase(LocalDate familehendelseDato) {
        return generiskFordeling(
                uttaksperiode(FELLESPERIODE, familehendelseDato.plusWeeks(3), familehendelseDato.plusWeeks(5)));
    }

    public static Fordeling fordelingEndringssøknadGradering(StønadskontoType stønadskonto, LocalDate fom, LocalDate tom, ArbeidsgiverIdentifikator identifikator, Integer arbeidstidsprosentIOrgnr) {
        return generiskFordeling(UttaksperioderErketyper.graderingsperiodeArbeidstaker(stønadskonto, fom, tom, identifikator, arbeidstidsprosentIOrgnr));
    }

    public static Fordeling fordelingFarAleneomsorg(LocalDate familehendelseDato) {
        return generiskFordelingAnnenforeldreIkkeInformert(
                uttaksperiode(FORELDREPENGER, familehendelseDato, familehendelseDato.plusWeeks(20)));
    }

    public static Fordeling fordelingMorAleneomsorgHappyCase(LocalDate familehendelseDato) {
        return generiskFordelingAnnenforeldreIkkeInformert(
                uttaksperiode(FORELDREPENGER_FØR_FØDSEL, familehendelseDato.minusWeeks(3L), familehendelseDato.minusDays(1L)),
                uttaksperiode(FORELDREPENGER, familehendelseDato, familehendelseDato.plusWeeks(100L)));
    }

    public static Fordeling generiskFordeling(LukketPeriodeMedVedlegg... perioder) {
        return basicBuilder(perioder)
                .erAnnenForelderInformert(true)
                .build();
    }

    public static Fordeling generiskFordelingAnnenforeldreIkkeInformert(LukketPeriodeMedVedlegg... perioder) {
        return basicBuilder(perioder)
                .erAnnenForelderInformert(false)
                .build();
    }

    private static Fordeling.FordelingBuilder basicBuilder(LukketPeriodeMedVedlegg[] perioder) {
        return Fordeling.builder().ønskerJustertUttakVedFødsel(false).perioder(Arrays.asList(perioder));
    }

}
