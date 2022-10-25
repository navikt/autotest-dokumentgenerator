package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper;

import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.util.VirkedagUtil.helgejustertTilFredag;
import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.util.VirkedagUtil.helgejustertTilMandag;
import static no.nav.foreldrepenger.common.domain.foreldrepenger.fordeling.UttaksPeriode.UttaksPeriodeBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
        return UttaksPeriodeBuilder()
                .uttaksperiodeType(stønadskontoType)
                .fom(helgejustertTilMandag(fom))
                .tom(helgejustertTilFredag(tom))
                .build();
    }

    public static UttaksPeriode uttaksperiode(StønadskontoType stønadskonto, LocalDate fom, LocalDate tom, MorsAktivitet morsAktivitet) {
        return UttaksPeriodeBuilder()
                .uttaksperiodeType(stønadskonto)
                .fom(helgejustertTilMandag(fom))
                .tom(helgejustertTilFredag(tom))
                .morsAktivitetsType(morsAktivitet)
                .build();
    }

    public static UttaksPeriode uttaksperiode(StønadskontoType stønadskontoType, LocalDate fom, LocalDate tom,
                                              UttaksperiodeType... uttaksperiodeTyper) {
        return uttaksperiode(stønadskontoType, fom, tom, 100, uttaksperiodeTyper);
    }

    public static UttaksPeriode uttaksperiode(StønadskontoType stønadskontoType, LocalDate fom, LocalDate tom,
                                              int uttaksprosent, UttaksperiodeType... uttaksperiodeTyper) {
        var periodetype = Set.of(uttaksperiodeTyper);
        return UttaksPeriodeBuilder()
                .uttaksperiodeType(stønadskontoType)
                .fom(helgejustertTilMandag(fom))
                .tom(helgejustertTilFredag(tom))
                .ønskerFlerbarnsdager(periodetype.contains(UttaksperiodeType.FLERBARNSDAGER))
                .ønskerSamtidigUttak(periodetype.contains(UttaksperiodeType.SAMTIDIGUTTAK))
                .samtidigUttakProsent(ProsentAndel.valueOf(uttaksprosent))
                .build();
    }

    public static GradertUttaksPeriode graderingsperiodeArbeidstaker(StønadskontoType stønadskontoType,
                                                                     LocalDate fom, LocalDate tom,
                                                                     ArbeidsgiverIdentifikator arbeidsgiverIdentifikator,
                                                                     Integer arbeidstidsprosentIOrgnr) {
        return GradertUttaksPeriode.GradertUttaksPeriodeBuilder()
                .uttaksperiodeType(stønadskontoType)
                .fom(helgejustertTilMandag(fom))
                .tom(helgejustertTilFredag(tom))
                .virksomhetsnummer(List.of(arbeidsgiverIdentifikator.value()))
                .arbeidsForholdSomskalGraderes(true)
                .arbeidstidProsent(ProsentAndel.valueOf(arbeidstidsprosentIOrgnr))
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
                .fom(helgejustertTilMandag(fom))
                .tom(helgejustertTilFredag(tom))
                .arbeidsForholdSomskalGraderes(true)
                .arbeidstidProsent(ProsentAndel.valueOf(arbeidstidsprosent))
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
                .fom(helgejustertTilMandag(fom))
                .tom(helgejustertTilFredag(tom))
                .arbeidsForholdSomskalGraderes(true)
                .arbeidstidProsent(ProsentAndel.valueOf(arbeidstidsprosent))
                .erArbeidstaker(false)
                .frilans(false)
                .selvstendig(true)
                .build();
    }

    public static UtsettelsesPeriode utsettelsesperiode(UtsettelsesÅrsak utsettelseÅrsak, LocalDate fom, LocalDate tom) {
        return UtsettelsesPeriode.UtsettelsesPeriodeBuilder()
                .fom(helgejustertTilMandag(fom))
                .tom(helgejustertTilFredag(tom))
                .årsak(utsettelseÅrsak)
                // TODO: Følgende eksistere ikke i Utsettelsesperiode for XML.
                //  stønadskontotype, erarbeidstaker, virksomhetsnummer og morsaktiattstype
                .build();
    }

    public static UtsettelsesPeriode utsettelsesperiode(UtsettelsesÅrsak utsettelseÅrsak, LocalDate fom, LocalDate tom, MorsAktivitet aktivitet) {
        return UtsettelsesPeriode.UtsettelsesPeriodeBuilder()
                .fom(helgejustertTilMandag(fom))
                .tom(helgejustertTilFredag(tom))
                .årsak(utsettelseÅrsak)
                .morsAktivitetsType(aktivitet)
                // TODO: Følgende eksistere ikke i Utsettelsesperiode for XML.
                //  stønadskontotype, erarbeidstaker, virksomhetsnummer og morsaktiattstype
                .build();
    }

    public static OverføringsPeriode overføringsperiode(Overføringsårsak overføringÅrsak,
                                                        StønadskontoType stønadskontoType,
                                                        LocalDate fom, LocalDate tom) {
        return OverføringsPeriode.builder()
                .fom(helgejustertTilMandag(fom))
                .tom(helgejustertTilFredag(tom))
                .årsak(overføringÅrsak)
                .uttaksperiodeType(stønadskontoType)
                .build();
    }

    public static OppholdsPeriode oppholdsperiode(Oppholdsårsak oppholdsårsak, LocalDate fom, LocalDate tom) {
        return OppholdsPeriode.builder()
                .fom(helgejustertTilMandag(fom))
                .tom(helgejustertTilFredag(tom))
                .årsak(oppholdsårsak)
                .build();
    }
}
