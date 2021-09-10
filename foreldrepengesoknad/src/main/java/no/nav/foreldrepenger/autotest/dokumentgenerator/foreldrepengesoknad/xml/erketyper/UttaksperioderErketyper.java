package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.erketyper;

import java.math.BigDecimal;
import java.time.LocalDate;

import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.foreldrepenger.fordeling.MorsAktivitet;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.OppholdÅrsak;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.OverføringÅrsak;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.Stønadskonto;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.SøknadUtsettelseÅrsak;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.builders.perioder.GraderingBuilder;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.builders.perioder.UttaksperiodeBuilder;
import no.nav.vedtak.felles.xml.soeknad.kodeverk.v3.MorsAktivitetsTyper;
import no.nav.vedtak.felles.xml.soeknad.kodeverk.v3.Oppholdsaarsaker;
import no.nav.vedtak.felles.xml.soeknad.kodeverk.v3.Overfoeringsaarsaker;
import no.nav.vedtak.felles.xml.soeknad.kodeverk.v3.Utsettelsesaarsaker;
import no.nav.vedtak.felles.xml.soeknad.kodeverk.v3.Uttaksperiodetyper;
import no.nav.vedtak.felles.xml.soeknad.uttak.v3.Gradering;
import no.nav.vedtak.felles.xml.soeknad.uttak.v3.Oppholdsperiode;
import no.nav.vedtak.felles.xml.soeknad.uttak.v3.Overfoeringsperiode;
import no.nav.vedtak.felles.xml.soeknad.uttak.v3.Utsettelsesperiode;
import no.nav.vedtak.felles.xml.soeknad.uttak.v3.Uttaksperiode;

public class UttaksperioderErketyper {

    private UttaksperioderErketyper() {
        // Skal ikke instansieres
    }

    public static Uttaksperiode uttaksperiode(Stønadskonto stønadskonto, LocalDate fom, LocalDate tom) {
        return uttaksperiode(stønadskonto, fom, tom, null);
    }

    public static Uttaksperiode uttaksperiode(Stønadskonto stønadskonto, LocalDate fom, LocalDate tom, MorsAktivitet morsAktivitet) {
        var builder = new UttaksperiodeBuilder(stønadskonto.getKode(), fom, tom);
        var periode = builder.build();
        if (morsAktivitet != null) {
            var morsAktivitetsTyper = new MorsAktivitetsTyper();
            morsAktivitetsTyper.setKode(morsAktivitet.name());
            morsAktivitetsTyper.setKodeverk("MORS_AKTIVITET");
            periode.setMorsAktivitetIPerioden(morsAktivitetsTyper);
        }
        return periode;
    }

    public static Uttaksperiode uttaksperiode(Stønadskonto stønadskonto, LocalDate fom, LocalDate tom,
                                              boolean flerbarnsdager,
                                              boolean samtidigUttak) {
        return uttaksperiode(stønadskonto, fom, tom, flerbarnsdager, samtidigUttak, 100);
    }

    public static Uttaksperiode uttaksperiode(Stønadskonto stønadskonto, LocalDate fom, LocalDate tom,
                                              boolean flerbarnsdager,
                                              boolean samtidigUttak, int uttaksprosent) {
        var uttaksperiodeBuilder = new UttaksperiodeBuilder(stønadskonto.getKode(), fom, tom);
        if (flerbarnsdager) {
            uttaksperiodeBuilder.medFlerbarnsdager();
        }
        if (samtidigUttak) {
            uttaksperiodeBuilder.medSamtidigUttak(BigDecimal.valueOf(uttaksprosent));
        }
        return uttaksperiodeBuilder.build();
    }

    public static Gradering graderingsperiodeArbeidstaker(Stønadskonto stønadskonto, LocalDate fom, LocalDate tom,
                                                          String orgnummer, Integer arbeidstidsprosentIOrgnr) {
        return new GraderingBuilder(stønadskonto.getKode(), fom, tom)
                .medGraderingArbeidstaker(orgnummer, arbeidstidsprosentIOrgnr)
                .build();
    }

    public static Gradering graderingsperiodeFL(Stønadskonto stønadskonto, LocalDate fom, LocalDate tom,
                                                Integer arbeidstidsprosent) {
        return new GraderingBuilder(stønadskonto.getKode(), fom, tom)
                .medGraderingFL(arbeidstidsprosent)
                .build();
    }

    public static Gradering graderingsperiodeSN(Stønadskonto stønadskonto, LocalDate fom, LocalDate tom,
                                                Integer arbeidstidsprosent) {
        return new GraderingBuilder(stønadskonto.getKode(), fom, tom)
                .medGraderingSN(arbeidstidsprosent)
                .build();
    }

    public static Overfoeringsperiode overføringsperiode(OverføringÅrsak overføringÅrsak, Stønadskonto stønadskonto,
                                                         LocalDate fom, LocalDate tom) {
        var overfoeringsaarsaker = new Overfoeringsaarsaker();
        overfoeringsaarsaker.setKode(overføringÅrsak.name());

        var uttaksperiodetyper = new Uttaksperiodetyper();
        uttaksperiodetyper.setKode(stønadskonto.getKode());

        var overfoeringsperiode = new Overfoeringsperiode();
        overfoeringsperiode.setAarsak(overfoeringsaarsaker);
        overfoeringsperiode.setOverfoeringAv(uttaksperiodetyper);
        overfoeringsperiode.setFom(fom);
        overfoeringsperiode.setTom(tom);
        return overfoeringsperiode;
    }

    public static Oppholdsperiode oppholdsperiode(OppholdÅrsak oppholdsårsak, LocalDate fom, LocalDate tom) {
        var oppholdsperiode = new Oppholdsperiode();
        var oppholdsaarsaker = new Oppholdsaarsaker();
        oppholdsaarsaker.setKode(oppholdsårsak.getKode());
        oppholdsperiode.setAarsak(oppholdsaarsaker);
        oppholdsperiode.setFom(fom);
        oppholdsperiode.setTom(tom);
        return oppholdsperiode;
    }

    public static Utsettelsesperiode utsettelsesperiode(SøknadUtsettelseÅrsak utsettelseÅrsak, LocalDate fom, LocalDate tom) {
        return utsettelsesperiode(utsettelseÅrsak, fom, tom, null);

    }

    public static Utsettelsesperiode utsettelsesperiode(SøknadUtsettelseÅrsak utsettelseÅrsak, LocalDate fom, LocalDate tom,
                                                        MorsAktivitet morsAktivitet) {
        var utsettelsesperiode = new Utsettelsesperiode();
        utsettelsesperiode.setFom(fom);
        utsettelsesperiode.setTom(tom);
        if (morsAktivitet != null) {
            var morsAktivitetIPerioden = new MorsAktivitetsTyper();
            morsAktivitetIPerioden.setKode(morsAktivitet.name());
            utsettelsesperiode.setMorsAktivitetIPerioden(morsAktivitetIPerioden);
        }
        var årsaker = new Utsettelsesaarsaker();
        årsaker.setKode(utsettelseÅrsak.getKode());
        utsettelsesperiode.setAarsak(årsaker);

        return utsettelsesperiode;
    }

}
