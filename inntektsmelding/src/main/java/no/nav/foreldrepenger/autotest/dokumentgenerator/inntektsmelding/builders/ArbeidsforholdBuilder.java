package no.nav.foreldrepenger.autotest.dokumentgenerator.inntektsmelding.builders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.JAXBElement;

import no.nav.inntektsmelding.xml.kodeliste._20180702.ÅrsakBeregnetInntektEndringKodeliste;
import no.nav.inntektsmelding.xml.kodeliste._20180702.ÅrsakUtsettelseKodeliste;
import no.seres.xsd.nav.inntektsmelding_m._20181211.Arbeidsforhold;
import no.seres.xsd.nav.inntektsmelding_m._20181211.GraderingIForeldrepenger;
import no.seres.xsd.nav.inntektsmelding_m._20181211.ObjectFactory;
import no.seres.xsd.nav.inntektsmelding_m._20181211.Periode;
import no.seres.xsd.nav.inntektsmelding_m._20181211.UtsettelseAvForeldrepenger;

public class ArbeidsforholdBuilder {
    private ObjectFactory objectFactory = new ObjectFactory();
//    private Arbeidsforhold arbeidsforholdKladd;

    private String arbeidsforholdId = null;
    private ÅrsakBeregnetInntektEndringKodeliste aarsakVedEndring;
    protected BigDecimal beregnetInntektBelop;
    private List<UtsettelseAvForeldrepenger> utsettelseAvForeldrepengerList;
    private List<GraderingIForeldrepenger> graderingIForeldrepengerList;
    private List<Periode> avtaltFerieListeList;
    private LocalDate foersteFravaersdag = null;



    public ArbeidsforholdBuilder() {
        utsettelseAvForeldrepengerList = new ArrayList<>();
        graderingIForeldrepengerList = new ArrayList<>();
        avtaltFerieListeList = new ArrayList<>();
    }
    public ArbeidsforholdBuilder medBeregnetInntekt(BigDecimal beregnetInntektBelop) {
        this.beregnetInntektBelop = beregnetInntektBelop;
        return this;
    }
    public ArbeidsforholdBuilder medÅrsakVedEndring(ÅrsakBeregnetInntektEndringKodeliste aarsakVedEndring){
        this.aarsakVedEndring = aarsakVedEndring;
        return this;
    }
    public ArbeidsforholdBuilder medUtsettelse(String årsak, LocalDate periodeFom, LocalDate periodeTom){
        utsettelseAvForeldrepengerList.add(createUtsettelseAvForeldrepenger(map(årsak), periodeFom, periodeTom));
        return this;
    }
    public ArbeidsforholdBuilder medUtsettelse(ÅrsakUtsettelseKodeliste aarsakTilUtsettelse, LocalDate periodeFom, LocalDate periodeTom){
        utsettelseAvForeldrepengerList.add(createUtsettelseAvForeldrepenger(aarsakTilUtsettelse, periodeFom, periodeTom));
        return this;
    }
    public ArbeidsforholdBuilder medUtsettelse(List<UtsettelseAvForeldrepenger> utsettelseAvForeldrepengerList){
        this.utsettelseAvForeldrepengerList = utsettelseAvForeldrepengerList;
        return this;
    }
    public ArbeidsforholdBuilder medGradering(BigDecimal arbeidsprosent, LocalDate fom, LocalDate tom) {
        graderingIForeldrepengerList.add(createGraderingIForeldrepenger(arbeidsprosent, fom, tom));
        return this;
    }
    public ArbeidsforholdBuilder medGradering(List<GraderingIForeldrepenger> graderingIForeldrepengerList){
        this.graderingIForeldrepengerList = graderingIForeldrepengerList;
        return this;
    }
    public ArbeidsforholdBuilder medAvtaltFerie(List<Periode> avtaltFerieListeList){
        this.avtaltFerieListeList = avtaltFerieListeList;
        return this;
    }
    public ArbeidsforholdBuilder medArbeidsforholdId(String arbeidsforholdId) {
        this.arbeidsforholdId = arbeidsforholdId;
        return this;
    }
    public ArbeidsforholdBuilder medFørsteFraværsdag(LocalDate førsteFraværsdag) {
        this.foersteFravaersdag = førsteFraværsdag;
        return this;
    }

    private Periode createPeriode(LocalDate periodeFom, LocalDate periodeTom) {
        var periode = objectFactory.createPeriode();

        periode.setTom(objectFactory.createPeriodeTom(periodeTom));
        periode.setFom(objectFactory.createPeriodeFom(periodeFom));

        return periode;
    }
    private ÅrsakUtsettelseKodeliste map(String årsak) {
        if ("LOVBESTEMT_FERIE".equals(årsak)) {
            return ÅrsakUtsettelseKodeliste.LOVBESTEMT_FERIE;
        }
        if ("ARBEID".equals(årsak)) {
            return ÅrsakUtsettelseKodeliste.ARBEID;
        }
        throw new IllegalStateException("Ukjent utsettelseårsak " + årsak);
    }
    private GraderingIForeldrepenger createGraderingIForeldrepenger(BigDecimal arbeidstidIProsent, LocalDate periodeFom, LocalDate periodeTom) {
        var graderingIForeldrepenger = new GraderingIForeldrepenger();
        graderingIForeldrepenger.setPeriode(objectFactory.createGraderingIForeldrepengerPeriode(
                createPeriode(periodeFom, periodeTom)));
        graderingIForeldrepenger.setArbeidstidprosent(
                objectFactory.createGraderingIForeldrepengerArbeidstidprosent(arbeidstidIProsent.toBigInteger()));
        return graderingIForeldrepenger;
    }
    private UtsettelseAvForeldrepenger createUtsettelseAvForeldrepenger(ÅrsakUtsettelseKodeliste aarsakTilUtsettelse, LocalDate periodeFom, LocalDate periodeTom) {
        var utsettelseAvForeldrepenger = objectFactory.createUtsettelseAvForeldrepenger();
        utsettelseAvForeldrepenger.setAarsakTilUtsettelse(objectFactory
                .createUtsettelseAvForeldrepengerAarsakTilUtsettelse(aarsakTilUtsettelse.value()));
        utsettelseAvForeldrepenger.setPeriode(objectFactory.createUtsettelseAvForeldrepengerPeriode(createPeriode(periodeFom, periodeTom)));
        return utsettelseAvForeldrepenger;
    }

    public Arbeidsforhold build() {
        var arbeidsforhold = objectFactory.createArbeidsforhold();

        arbeidsforhold.setArbeidsforholdId(objectFactory.createArbeidsforholdArbeidsforholdId(arbeidsforholdId));

        if (utsettelseAvForeldrepengerList != null && !utsettelseAvForeldrepengerList.isEmpty()) {
            var utsettelseAvForeldrepengerListe = objectFactory.createUtsettelseAvForeldrepengerListe();
            utsettelseAvForeldrepengerListe.getUtsettelseAvForeldrepenger().addAll(utsettelseAvForeldrepengerList);
            arbeidsforhold.setUtsettelseAvForeldrepengerListe(
                    objectFactory.createArbeidsforholdUtsettelseAvForeldrepengerListe(utsettelseAvForeldrepengerListe));
        }
        if (avtaltFerieListeList != null && !avtaltFerieListeList.isEmpty()) {
            var avtaltFerieListe = objectFactory.createAvtaltFerieListe();
            avtaltFerieListe.getAvtaltFerie().addAll(avtaltFerieListeList);
            arbeidsforhold.setAvtaltFerieListe(
                    objectFactory.createArbeidsforholdAvtaltFerieListe(avtaltFerieListe));
        }
        if (graderingIForeldrepengerList != null && !graderingIForeldrepengerList.isEmpty()) {
            var graderingIForeldrepengerListe = objectFactory.createGraderingIForeldrepengerListe();
            graderingIForeldrepengerListe.getGraderingIForeldrepenger().addAll(graderingIForeldrepengerList);
            arbeidsforhold.setGraderingIForeldrepengerListe(
                    objectFactory.createArbeidsforholdGraderingIForeldrepengerListe(graderingIForeldrepengerListe));
        }
        if (foersteFravaersdag != null) {
            JAXBElement<LocalDate> arbeidsforholdFoersteFravaersdag = objectFactory.createArbeidsforholdFoersteFravaersdag(foersteFravaersdag);
            arbeidsforhold.setFoersteFravaersdag(arbeidsforholdFoersteFravaersdag);
        }

        Objects.requireNonNull(beregnetInntektBelop, "Beregnet inntekt kan ikke være null");

        var inntekt = objectFactory.createInntekt();
        inntekt.setBeloep(objectFactory.createInntektBeloep(beregnetInntektBelop));
        if (this.aarsakVedEndring != null) {
            inntekt.setAarsakVedEndring(objectFactory.createInntektAarsakVedEndring(aarsakVedEndring.value()));
        }
        arbeidsforhold.setBeregnetInntekt(objectFactory.createArbeidsforholdBeregnetInntekt(inntekt));

        return arbeidsforhold;
    }

}
