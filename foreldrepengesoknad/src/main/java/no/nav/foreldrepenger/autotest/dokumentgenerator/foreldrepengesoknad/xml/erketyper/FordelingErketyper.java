package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.erketyper;

import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.Stønadskonto.FEDREKVOTE;
import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.Stønadskonto.FELLESPERIODE;
import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.Stønadskonto.FORELDREPENGER;
import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.Stønadskonto.FORELDREPENGER_FØR_FØDSEL;
import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.Stønadskonto.MØDREKVOTE;
import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.erketyper.UttaksperioderErketyper.graderingsperiodeArbeidstaker;
import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.erketyper.UttaksperioderErketyper.uttaksperiode;

import java.time.LocalDate;

import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.Stønadskonto;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.SøkersRolle;
import no.nav.vedtak.felles.xml.soeknad.uttak.v3.Fordeling;
import no.nav.vedtak.felles.xml.soeknad.uttak.v3.LukketPeriodeMedVedlegg;

public class FordelingErketyper {

    private FordelingErketyper() {
        // Skal ikke instansieres
    }

    public static Fordeling fordelingHappyCase(LocalDate familehendelseDato, SøkersRolle søkerRolle) {
        if (søkerRolle == SøkersRolle.MOR) {
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

    public static Fordeling fordelingMorHappyCaseEkstraUttakFørFødsel(LocalDate familehendelseDato) {
        return generiskFordeling(
                uttaksperiode(FORELDREPENGER_FØR_FØDSEL, familehendelseDato.minusWeeks(12),
                        familehendelseDato.minusDays(1)),
                uttaksperiode(MØDREKVOTE, familehendelseDato, familehendelseDato.plusWeeks(10)));
    }

    public static Fordeling fordelingMorHappyCaseMedEkstraUttak(LocalDate familiehendelseDato) {
        var fordeling = new Fordeling();
        fordeling.setAnnenForelderErInformert(true);
        fordeling.getPerioder().add(uttaksperiode(FELLESPERIODE, familiehendelseDato.plusWeeks(10).plusDays(1),
                familiehendelseDato.plusWeeks(12)));
        return fordeling;
    }

    public static Fordeling fordelingMorMedAksjonspunkt(LocalDate familiehendelseDato) {
        var fordeling = new Fordeling();
        fordeling.setAnnenForelderErInformert(true);
        fordeling.getPerioder().add(uttaksperiode(FORELDREPENGER_FØR_FØDSEL, familiehendelseDato.minusWeeks(3),
                familiehendelseDato.minusDays(1)));
        return fordeling;
    }

    public static Fordeling fordelingMorAleneomsorgHappyCase(LocalDate familehendelseDato) {
        var fordeling = new Fordeling();
        fordeling.setAnnenForelderErInformert(false);

        fordeling.getPerioder().add(uttaksperiode(FORELDREPENGER_FØR_FØDSEL, familehendelseDato.minusWeeks(3),
                familehendelseDato.minusDays(1)));
        fordeling.getPerioder()
                .add(uttaksperiode(FORELDREPENGER, familehendelseDato, familehendelseDato.plusWeeks(100)));

        return fordeling;
    }


    public static Fordeling fordelingFarHappyCase(LocalDate familehendelseDato) {
        return generiskFordeling(
                uttaksperiode(FELLESPERIODE, familehendelseDato.plusWeeks(3), familehendelseDato.plusWeeks(5)));
    }

    public static Fordeling fordelingFarHappyCaseMedMor(LocalDate familiehendelseDato) {
        return generiskFordeling(
                uttaksperiode(FELLESPERIODE, familiehendelseDato.plusWeeks(6), familiehendelseDato.plusWeeks(9)));
    }

    public static Fordeling fordelingFarUtenOverlapp(LocalDate familehendelseDato) {
        return generiskFordeling(uttaksperiode(FELLESPERIODE, familehendelseDato.plusWeeks(6).plusDays(1),
                familehendelseDato.plusWeeks(8)));
    }

    public static Fordeling fordelingFarHappycaseKobletMedMorHappycase(LocalDate familehendelseDato) {
        return generiskFordeling(uttaksperiode(FEDREKVOTE, familehendelseDato.plusWeeks(10).plusDays(1),
                familehendelseDato.plusWeeks(16)));
    }

    public static Fordeling fordelingFarAleneomsorg(LocalDate familehendelseDato) {
        var fordeling = new Fordeling();
        fordeling.setAnnenForelderErInformert(false);

        fordeling.getPerioder()
                .add(uttaksperiode(FORELDREPENGER, familehendelseDato, familehendelseDato.plusWeeks(20)));

        return fordeling;
    }



    public static Fordeling fordelingEndringssøknadGradering(Stønadskonto stønadskonto, LocalDate fom, LocalDate tom,
                                                             String orgnummer, Integer arbeidstidsprosentIOrgnr) {
        return generiskFordeling(
                graderingsperiodeArbeidstaker(stønadskonto, fom, tom, orgnummer, arbeidstidsprosentIOrgnr));
    }

    public static Fordeling generiskFordeling(LukketPeriodeMedVedlegg... perioder) {
        var fordeling = new Fordeling();
        fordeling.setAnnenForelderErInformert(true);

        for (LukketPeriodeMedVedlegg lukketPeriodeMedVedlegg : perioder) {
            fordeling.getPerioder().add(lukketPeriodeMedVedlegg);
        }

        return fordeling;
    }
}
