package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper;

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

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.util.VirkedagUtil.helgejustertTilFredag;
import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.util.VirkedagUtil.helgejustertTilMandag;


public final class UttaksperioderErketyper {

    private UttaksperioderErketyper() {
    }

    public static UttaksPeriode uttaksperiode(StønadskontoType stønadskontoType, LocalDate fom, LocalDate tom) {
        return new UttaksPeriode(
                helgejustertTilMandag(fom),
                helgejustertTilFredag(tom),
                null,
                stønadskontoType,
                false,
                null,
                false,
                null,
                null
        );
    }

    public static UttaksPeriode uttaksperiode(StønadskontoType stønadskonto, LocalDate fom, LocalDate tom, MorsAktivitet morsAktivitet) {
        return new UttaksPeriode(
                helgejustertTilMandag(fom),
                helgejustertTilFredag(tom),
                null,
                stønadskonto,
                false,
                morsAktivitet,
                false,
                null,
                null
        );
    }

    public static UttaksPeriode uttaksperiode(StønadskontoType stønadskontoType, LocalDate fom, LocalDate tom,
                                              UttaksperiodeType... uttaksperiodeTyper) {
        return uttaksperiode(stønadskontoType, fom, tom, 100, uttaksperiodeTyper);
    }

    public static UttaksPeriode uttaksperiode(StønadskontoType stønadskontoType, LocalDate fom, LocalDate tom,
                                              int uttaksprosent, UttaksperiodeType... uttaksperiodeTyper) {
        var periodetype = Set.of(uttaksperiodeTyper);
        return new UttaksPeriode(
                helgejustertTilMandag(fom),
                helgejustertTilFredag(tom),
                null,
                stønadskontoType,
                periodetype.contains(UttaksperiodeType.SAMTIDIGUTTAK),
                null,
                periodetype.contains(UttaksperiodeType.FLERBARNSDAGER),
                ProsentAndel.valueOf(uttaksprosent),
                null
        );
    }

    public static GradertUttaksPeriode graderingsperiodeArbeidstaker(StønadskontoType stønadskontoType,
                                                                     LocalDate fom, LocalDate tom,
                                                                     ArbeidsgiverIdentifikator arbeidsgiverIdentifikator,
                                                                     Integer arbeidstidsprosentIOrgnr) {
        return new GradertUttaksPeriode(
                helgejustertTilMandag(fom),
                helgejustertTilFredag(tom),
                null,
                stønadskontoType,
                false,
                null,
                false,
                null,
                ProsentAndel.valueOf(arbeidstidsprosentIOrgnr),
                true,
                List.of(arbeidsgiverIdentifikator.value()),
                true,
                false,
                false,
                null
        );
    }

    public static GradertUttaksPeriode graderingsperiodeFL(StønadskontoType stønadskontoType,
                                                           LocalDate fom, LocalDate tom,
                                                           Integer arbeidstidsprosent) {
        return new GradertUttaksPeriode(
                helgejustertTilMandag(fom),
                helgejustertTilFredag(tom),
                null,
                stønadskontoType,
                false,
                null,
                false,
                null,
                ProsentAndel.valueOf(arbeidstidsprosent),
                false,
                null,
                true,
                true,
                false,
                null
        );
    }

    public static GradertUttaksPeriode graderingsperiodeSN(StønadskontoType stønadskontoType,
                                                           LocalDate fom, LocalDate tom,
                                                           Integer arbeidstidsprosent) {
        return new GradertUttaksPeriode(
                helgejustertTilMandag(fom),
                helgejustertTilFredag(tom),
                null,
                stønadskontoType,
                false,
                null,
                false,
                null,
                ProsentAndel.valueOf(arbeidstidsprosent),
                false,
                null,
                true,
                false,
                true,
                null
        );
    }

    public static UtsettelsesPeriode utsettelsesperiode(UtsettelsesÅrsak utsettelseÅrsak, LocalDate fom, LocalDate tom) {
        return new UtsettelsesPeriode(
                helgejustertTilMandag(fom),
                helgejustertTilFredag(tom),
                false,
                utsettelseÅrsak,
                null,
                null
        );
    }

    public static UtsettelsesPeriode utsettelsesperiode(UtsettelsesÅrsak utsettelseÅrsak, LocalDate fom, LocalDate tom, MorsAktivitet aktivitet) {
        return new UtsettelsesPeriode(
                helgejustertTilMandag(fom),
                helgejustertTilFredag(tom),
                false,
                utsettelseÅrsak,
                aktivitet,
                null
        );
    }

    public static OverføringsPeriode overføringsperiode(Overføringsårsak overføringÅrsak,
                                                        StønadskontoType stønadskontoType,
                                                        LocalDate fom, LocalDate tom) {
        return new OverføringsPeriode(
                helgejustertTilMandag(fom),
                helgejustertTilFredag(tom),
                overføringÅrsak,
                stønadskontoType,
                null
        );
    }

    public static OppholdsPeriode oppholdsperiode(Oppholdsårsak oppholdsårsak, LocalDate fom, LocalDate tom) {
        return new OppholdsPeriode(
                helgejustertTilMandag(fom),
                helgejustertTilFredag(tom),
                oppholdsårsak,
                null
        );
    }
}
