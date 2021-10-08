package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper;

import java.time.LocalDate;
import java.util.List;

import no.nav.foreldrepenger.common.domain.ArbeidsgiverIdentifikator;
import no.nav.foreldrepenger.common.domain.felles.ProsentAndel;
import no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.GradertUttaksPeriode;
import no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.MorsAktivitet;
import no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.OppholdsPeriode;
import no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.Oppholdsårsak;
import no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.OverføringsPeriode;
import no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.Overføringsårsak;
import no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.StønadskontoType;
import no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.UtsettelsesPeriode;
import no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.UtsettelsesÅrsak;
import no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.UttaksPeriode;


public final class UttaksperioderErketyper {

    private UttaksperioderErketyper() {
    }

    public static UttaksPeriode uttaksperiode(StønadskontoType stønadskontoType, LocalDate fom, LocalDate tom) {
        return UttaksPeriode.UttaksPeriodeBuilder()
                .uttaksperiodeType(stønadskontoType)
                .fom(fom)
                .tom(tom)
                .build();
    }

    public static UttaksPeriode uttaksperiode(StønadskontoType stønadskonto, LocalDate fom, LocalDate tom, MorsAktivitet morsAktivitet) {
        return UttaksPeriode.UttaksPeriodeBuilder()
                .uttaksperiodeType(stønadskonto)
                .fom(fom)
                .tom(tom)
                .morsAktivitetsType(morsAktivitet)
                .build();
    }

    public static UttaksPeriode uttaksperiode(StønadskontoType stønadskontoType, LocalDate fom, LocalDate tom,
                                              Boolean flerbarnsdager,
                                              Boolean samtidigUttak) {
        return uttaksperiode(stønadskontoType, fom, tom, flerbarnsdager, samtidigUttak, 100);
    }

    public static UttaksPeriode uttaksperiode(StønadskontoType stønadskontoType, LocalDate fom, LocalDate tom,
                                              Boolean flerbarnsdager,
                                              Boolean samtidigUttak, int uttaksprosent) {
        return UttaksPeriode.UttaksPeriodeBuilder()
                .uttaksperiodeType(stønadskontoType)
                .fom(fom)
                .tom(tom)
                .ønskerFlerbarnsdager(flerbarnsdager)
                .ønskerSamtidigUttak(samtidigUttak)
                .samtidigUttakProsent(new ProsentAndel((double) uttaksprosent))
                .build();
    }

    public static GradertUttaksPeriode graderingsperiodeArbeidstaker(StønadskontoType stønadskontoType, LocalDate fom, LocalDate tom,
                                                                     ArbeidsgiverIdentifikator arbeidsgiverIdentifikator, Integer arbeidstidsprosentIOrgnr) {
        return GradertUttaksPeriode.GradertUttaksPeriodeBuilder()
                .uttaksperiodeType(stønadskontoType)
                .fom(fom)
                .tom(tom)
                .virksomhetsnummer(List.of(arbeidsgiverIdentifikator.value()))
                .arbeidsForholdSomskalGraderes(true)
                .arbeidstidProsent(new ProsentAndel(Double.valueOf(arbeidstidsprosentIOrgnr)))
                .erArbeidstaker(true)
                .frilans(false)
                .selvstendig(false)
                .build();
    }

    public static GradertUttaksPeriode graderingsperiodeFL(StønadskontoType stønadskontoType,
                                                           LocalDate fom, LocalDate tom,
                                                           Integer arbeidstidsprosent) {
        return GradertUttaksPeriode.GradertUttaksPeriodeBuilder()
                .uttaksperiodeType(stønadskontoType)
                .fom(fom)
                .tom(tom)
                .arbeidsForholdSomskalGraderes(true)
                .arbeidstidProsent(new ProsentAndel(Double.valueOf(arbeidstidsprosent)))
                .erArbeidstaker(false)
                .frilans(true)
                .selvstendig(false)
                .build();
    }

    public static GradertUttaksPeriode graderingsperiodeSN(StønadskontoType stønadskontoType,
                                                           LocalDate fom, LocalDate tom,
                                                           Integer arbeidstidsprosent) {
        return GradertUttaksPeriode.GradertUttaksPeriodeBuilder()
                .uttaksperiodeType(stønadskontoType)
                .fom(fom)
                .tom(tom)
                .arbeidsForholdSomskalGraderes(true)
                .arbeidstidProsent(new ProsentAndel(Double.valueOf(arbeidstidsprosent)))
                .erArbeidstaker(false)
                .frilans(false)
                .selvstendig(true)
                .build();
    }

    public static UtsettelsesPeriode utsettelsesperiode(UtsettelsesÅrsak utsettelseÅrsak, LocalDate fom, LocalDate tom) {
        return UtsettelsesPeriode.UtsettelsesPeriodeBuilder()
                .fom(fom)
                .tom(tom)
                .årsak(utsettelseÅrsak)
                // TODO: Følgende eksistere ikke i Utsettelsesperiode for XML.
                //  stønadskontotype, erarbeidstaker, virksomhetsnummer og morsaktiattstype
                .build();
    }

    public static UtsettelsesPeriode utsettelsesperiode(UtsettelsesÅrsak utsettelseÅrsak, LocalDate fom, LocalDate tom, MorsAktivitet aktivitet) {
        return UtsettelsesPeriode.UtsettelsesPeriodeBuilder()
                .fom(fom)
                .tom(tom)
                .årsak(utsettelseÅrsak)
                .morsAktivitetsType(aktivitet)
                // TODO: Følgende eksistere ikke i Utsettelsesperiode for XML.
                //  stønadskontotype, erarbeidstaker, virksomhetsnummer og morsaktiattstype
                .build();
    }

    public static OverføringsPeriode overføringsperiode(Overføringsårsak overføringÅrsak, StønadskontoType stønadskontoType,
                                                        LocalDate fom, LocalDate tom) {
        return OverføringsPeriode.builder()
                .fom(fom)
                .tom(tom)
                .årsak(overføringÅrsak)
                .uttaksperiodeType(stønadskontoType)
                .build();
    }

    public static OppholdsPeriode oppholdsperiode(Oppholdsårsak oppholdsårsak, LocalDate fom, LocalDate tom) {

        return OppholdsPeriode.builder()
                .fom(fom)
                .tom(tom)
                .årsak(oppholdsårsak)
                .build();
    }
}
