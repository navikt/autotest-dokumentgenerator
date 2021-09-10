package no.nav.foreldrepenger.autotest.dokumentgenerator.inntektsmelding.builders;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import no.nav.inntektsmelding.xml.kodeliste._20180702.NaturalytelseKodeliste;
import no.nav.inntektsmelding.xml.kodeliste._20180702.YtelseKodeliste;
import no.nav.inntektsmelding.xml.kodeliste._20180702.ÅrsakBeregnetInntektEndringKodeliste;
import no.nav.inntektsmelding.xml.kodeliste._20180702.ÅrsakInnsendingKodeliste;
import no.nav.inntektsmelding.xml.kodeliste._20180702.ÅrsakUtsettelseKodeliste;
import no.seres.xsd.nav.inntektsmelding_m._20181211.Arbeidsforhold;
import no.seres.xsd.nav.inntektsmelding_m._20181211.Arbeidsgiver;
import no.seres.xsd.nav.inntektsmelding_m._20181211.ArbeidsgiverPrivat;
import no.seres.xsd.nav.inntektsmelding_m._20181211.Avsendersystem;
import no.seres.xsd.nav.inntektsmelding_m._20181211.EndringIRefusjon;
import no.seres.xsd.nav.inntektsmelding_m._20181211.GjenopptakelseNaturalytelseListe;
import no.seres.xsd.nav.inntektsmelding_m._20181211.GraderingIForeldrepenger;
import no.seres.xsd.nav.inntektsmelding_m._20181211.InntektsmeldingM;
import no.seres.xsd.nav.inntektsmelding_m._20181211.ObjectFactory;
import no.seres.xsd.nav.inntektsmelding_m._20181211.Omsorgspenger;
import no.seres.xsd.nav.inntektsmelding_m._20181211.OpphoerAvNaturalytelseListe;
import no.seres.xsd.nav.inntektsmelding_m._20181211.Periode;
import no.seres.xsd.nav.inntektsmelding_m._20181211.PleiepengerPeriodeListe;
import no.seres.xsd.nav.inntektsmelding_m._20181211.SykepengerIArbeidsgiverperioden;
import no.seres.xsd.nav.inntektsmelding_m._20181211.UtsettelseAvForeldrepenger;

public class InntektsmeldingBuilder {
    private InntektsmeldingM inntektsmeldingKladd;
    private ArbeidsforholdBuilder arbeidsforholdBuilderKladd;
    private SkjemainnholdBuilder skjemainnholdBuilderKladd;
    private RefusjonBuilder refusjonBuilderKladd;



    public InntektsmeldingBuilder() {
        inntektsmeldingKladd = new InntektsmeldingM();
        arbeidsforholdBuilderKladd = new ArbeidsforholdBuilder();
        skjemainnholdBuilderKladd = new SkjemainnholdBuilder();
    }
    public InntektsmeldingBuilder medPleiepengerPeriodeListe(PleiepengerPeriodeListe pleiepengerPeriodeListe) {
        this.skjemainnholdBuilderKladd.medPleiepengerPeriodeListe(pleiepengerPeriodeListe);
        return this;
    }
    public InntektsmeldingBuilder medOmsorgspenger(Omsorgspenger omsorgspenger) {
        this.skjemainnholdBuilderKladd.medOmsorgspenger(omsorgspenger);
        return this;
    }
    public InntektsmeldingBuilder medArbeidsgiver(String virksomhetsnummer, String kontaktinformasjonTLF){
        this.skjemainnholdBuilderKladd.medArbeidsgiver(virksomhetsnummer, kontaktinformasjonTLF);
        return this;
    }
    public InntektsmeldingBuilder medArbeidsgiver(Arbeidsgiver arbeidsgiver){
        this.skjemainnholdBuilderKladd.medArbeidsgiver(arbeidsgiver);
        return this;
    }
    public InntektsmeldingBuilder medArbeidsgiverPrivat(String arbeidsgiverFnr, String kontaktinformasjonTLF) {
        skjemainnholdBuilderKladd.medArbeidsgiverPrivat(arbeidsgiverFnr, kontaktinformasjonTLF);
        return this;
    }
    public InntektsmeldingBuilder medArbeidsgiverPrivat(ArbeidsgiverPrivat arbeidsgiverPrivat) {
        this.skjemainnholdBuilderKladd.medArbeidsgiverPrivat(arbeidsgiverPrivat);
        return this;
    }
    public InntektsmeldingBuilder medOpphoerAvNaturalytelseListe(BigDecimal belopPrMnd, LocalDate fom, NaturalytelseKodeliste kodelisteNaturalytelse) {
        this.skjemainnholdBuilderKladd.medOpphoerAvNaturalytelseListe(belopPrMnd, fom, kodelisteNaturalytelse);
        return this;
    }
    public InntektsmeldingBuilder medOpphoerAvNaturalytelseListe(OpphoerAvNaturalytelseListe opphoerAvNaturalytelseListe) {
        this.skjemainnholdBuilderKladd.medOpphoerAvNaturalytelseListe(opphoerAvNaturalytelseListe);
        return this;
    }
    public InntektsmeldingBuilder medGjenopptakelseNaturalytelseListe(GjenopptakelseNaturalytelseListe gjenopptakelseNaturalytelseListe) {
        this.skjemainnholdBuilderKladd.medGjenopptakelseNaturalytelseListe(gjenopptakelseNaturalytelseListe);
        return this;
    }
    public InntektsmeldingBuilder medArbeidstakerFNR(String arbeidstakerFNR) {
        this.skjemainnholdBuilderKladd.medArbeidstakerFNR(arbeidstakerFNR);
        return this;
    }
    public InntektsmeldingBuilder medSykepengerIArbeidsgiverperioden(SykepengerIArbeidsgiverperioden sykepengerIArbeidsgiverperioden) {
        this.skjemainnholdBuilderKladd.medSykepengerIArbeidsgiverperioden(sykepengerIArbeidsgiverperioden);
        return this;
    }
    public InntektsmeldingBuilder medNaerRelasjon(Boolean naerRelasjon) {
        this.skjemainnholdBuilderKladd.medNaerRelasjon(naerRelasjon);
        return this;
    }
    public InntektsmeldingBuilder medAarsakTilInnsending(ÅrsakInnsendingKodeliste aarsakTilInnsending) {
        this.skjemainnholdBuilderKladd.medAarsakTilInnsending(aarsakTilInnsending);
        return this;
    }
    public InntektsmeldingBuilder medStartdatoForeldrepengerperiodenFOM(LocalDate startidspunktForeldrepenger) {
        this.skjemainnholdBuilderKladd.medStartdatoForeldrepengerperiodenFOM(startidspunktForeldrepenger);
        return this;
    }
    public InntektsmeldingBuilder medYtelse(YtelseKodeliste ytelse) {
        this.skjemainnholdBuilderKladd.medYtelse(ytelse);
        return this;
    }
    public InntektsmeldingBuilder medAvsendersystem(Avsendersystem avsendersystem) {
        this.skjemainnholdBuilderKladd.medAvsendersystem(avsendersystem);
        return this;
    }
    public InntektsmeldingBuilder medAvsendersystem(String avsenderSystem, String systemVersjon) {
        this.skjemainnholdBuilderKladd.medAvsendersystem(avsenderSystem, systemVersjon);
        return this;
    }
    public InntektsmeldingBuilder medArbeidsforhold(Arbeidsforhold arbeidsforhold) {
        this.skjemainnholdBuilderKladd.medArbeidsforhold(arbeidsforhold);
        return this;
    }
    public InntektsmeldingBuilder medFørsteFraværsdag(LocalDate førsteFraværsdag) {
        this.arbeidsforholdBuilderKladd.medFørsteFraværsdag(førsteFraværsdag);
        return this;
    }

    public InntektsmeldingBuilder medRefusjonsBelopPerMnd(BigDecimal refusjonsBelopPerMnd) {
        if(refusjonBuilderKladd == null) {
            refusjonBuilderKladd = new RefusjonBuilder();
        }
        this.refusjonBuilderKladd.medRefusjonsBelopPerMnd(refusjonsBelopPerMnd);
        return this;
    }

    public InntektsmeldingBuilder medRefusjonsBelopPerMnd(int prosentAvBeregnetInntekt) {
        if(refusjonBuilderKladd == null) {
            refusjonBuilderKladd = new RefusjonBuilder();
        }
        var multiplikand = prosentAvBeregnetInntekt / 100.0;
        var refusjonsBelopPerMnd = arbeidsforholdBuilderKladd.beregnetInntektBelop.multiply(BigDecimal.valueOf(multiplikand));
        this.refusjonBuilderKladd.medRefusjonsBelopPerMnd(refusjonsBelopPerMnd);
        return this;
    }

    public InntektsmeldingBuilder medEndringIRefusjonslist(List<EndringIRefusjon> endringIRefusjonList) {
        if(refusjonBuilderKladd == null) {
            refusjonBuilderKladd = new RefusjonBuilder();
        }
        this.refusjonBuilderKladd.medEndringIRefusjonslist(endringIRefusjonList);
        return this;
    }
    public InntektsmeldingBuilder medEndringIRefusjonslist(Map<LocalDate, BigDecimal> endringRefusjonMap) {
        if(refusjonBuilderKladd == null) {
            refusjonBuilderKladd = new RefusjonBuilder();
        }
        this.refusjonBuilderKladd.medEndringIRefusjonslist(endringRefusjonMap);
        return this;
    }
    public InntektsmeldingBuilder medRefusjonsOpphordato(LocalDate refusjonsOpphordato) {
        if(refusjonBuilderKladd == null) {
            refusjonBuilderKladd = new RefusjonBuilder();
        }
        this.refusjonBuilderKladd.medRefusjonsOpphordato(refusjonsOpphordato);
        return this;
    }

    public InntektsmeldingBuilder medBeregnetInntekt(BigDecimal beregnetInntektBelop) {
        this.arbeidsforholdBuilderKladd.medBeregnetInntekt(beregnetInntektBelop);
        return this;
    }

    public InntektsmeldingBuilder medBeregnetInntekt(int prosentAvvik) {
        var multiplikand = (100 + prosentAvvik) / 100.0;
        var beregnetInntektBelop = arbeidsforholdBuilderKladd.beregnetInntektBelop.multiply(BigDecimal.valueOf(multiplikand));
        this.arbeidsforholdBuilderKladd.medBeregnetInntekt(beregnetInntektBelop);
        return this;
    }

    public InntektsmeldingBuilder medÅrsakVedEndring(ÅrsakBeregnetInntektEndringKodeliste aarsakVedEndring){
        this.arbeidsforholdBuilderKladd.medÅrsakVedEndring(aarsakVedEndring);
        return this;
    }
    public InntektsmeldingBuilder medUtsettelse(String årsak, LocalDate periodeFom, LocalDate periodeTom){
        this.arbeidsforholdBuilderKladd.medUtsettelse(årsak, periodeFom, periodeTom);
        return this;
    }
    public InntektsmeldingBuilder medUtsettelse(ÅrsakUtsettelseKodeliste aarsakTilUtsettelse, LocalDate periodeFom, LocalDate periodeTom){
        arbeidsforholdBuilderKladd.medUtsettelse(aarsakTilUtsettelse, periodeFom, periodeTom);
        return this;
    }
    public InntektsmeldingBuilder medUtsettelse(List<UtsettelseAvForeldrepenger> utsettelseAvForeldrepengerList){
        this.arbeidsforholdBuilderKladd.medUtsettelse(utsettelseAvForeldrepengerList);
        return this;
    }
    public InntektsmeldingBuilder medGradering(BigDecimal arbeidsprosent, LocalDate fom, LocalDate tom) {
        arbeidsforholdBuilderKladd.medGradering(arbeidsprosent, fom, tom);
        return this;
    }
    public InntektsmeldingBuilder medGradering(List<GraderingIForeldrepenger> graderingIForeldrepengerList){
        this.arbeidsforholdBuilderKladd.medGradering(graderingIForeldrepengerList);
        return this;
    }
    public InntektsmeldingBuilder medAvtaltFerie(List<Periode> avtaltFerieListeList){
        this.arbeidsforholdBuilderKladd.medAvtaltFerie(avtaltFerieListeList);
        return this;
    }
    public InntektsmeldingBuilder medArbeidsforholdId(String arbeidsforholdId) {
        this.arbeidsforholdBuilderKladd.medArbeidsforholdId(arbeidsforholdId);
        return this;
    }


    public String createInntektesmeldingXML() {
        return createInntektsmeldingXML(this.build());
    }
    private String createInntektsmeldingXML(InntektsmeldingM inntektsmelding) {

        var sw = new StringWriter();
        try {
            var objectFactory = new ObjectFactory();
            var jaxbContext = JAXBContext.newInstance(InntektsmeldingM.class);
            var jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // Prettyprinter output
            jaxbMarshaller.marshal(
                    objectFactory.createMelding(inntektsmelding), sw
            );
            return sw.toString();
        } catch (JAXBException jaxbe) {

        }
        return null; //TODO: Håndtere på en bedre måte.

    }


    public InntektsmeldingM build() {
        this.skjemainnholdBuilderKladd.medArbeidsforhold(arbeidsforholdBuilderKladd.build());
        if (refusjonBuilderKladd != null) {
            this.skjemainnholdBuilderKladd.medRefusjon(refusjonBuilderKladd.build());
        }
        this.inntektsmeldingKladd.setSkjemainnhold(skjemainnholdBuilderKladd.build());
        return inntektsmeldingKladd;
    }
}
