package no.nav.foreldrepenger.autotest.dokumentgenerator.inntektsmelding.builders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import no.nav.inntektsmelding.xml.kodeliste._20180702.NaturalytelseKodeliste;
import no.nav.inntektsmelding.xml.kodeliste._20180702.YtelseKodeliste;
import no.nav.inntektsmelding.xml.kodeliste._20180702.ÅrsakInnsendingKodeliste;
import no.seres.xsd.nav.inntektsmelding_m._20181211.Arbeidsforhold;
import no.seres.xsd.nav.inntektsmelding_m._20181211.Arbeidsgiver;
import no.seres.xsd.nav.inntektsmelding_m._20181211.ArbeidsgiverPrivat;
import no.seres.xsd.nav.inntektsmelding_m._20181211.Avsendersystem;
import no.seres.xsd.nav.inntektsmelding_m._20181211.GjenopptakelseNaturalytelseListe;
import no.seres.xsd.nav.inntektsmelding_m._20181211.Kontaktinformasjon;
import no.seres.xsd.nav.inntektsmelding_m._20181211.NaturalytelseDetaljer;
import no.seres.xsd.nav.inntektsmelding_m._20181211.ObjectFactory;
import no.seres.xsd.nav.inntektsmelding_m._20181211.Omsorgspenger;
import no.seres.xsd.nav.inntektsmelding_m._20181211.OpphoerAvNaturalytelseListe;
import no.seres.xsd.nav.inntektsmelding_m._20181211.PleiepengerPeriodeListe;
import no.seres.xsd.nav.inntektsmelding_m._20181211.Refusjon;
import no.seres.xsd.nav.inntektsmelding_m._20181211.Skjemainnhold;
import no.seres.xsd.nav.inntektsmelding_m._20181211.SykepengerIArbeidsgiverperioden;

public class SkjemainnholdBuilder {

    private ObjectFactory objectFactory;
    private ÅrsakInnsendingKodeliste aarsakTilInnsending;
    private YtelseKodeliste ytelse;
    private LocalDate startdatoForeldrepengeperiodenFOM;
    private Skjemainnhold skjemainnhold;
    private PleiepengerPeriodeListe pleiepengerPeriodeListe;
    private Omsorgspenger omsorgspenger;
    private ArbeidsgiverPrivat arbeidsgiverPrivat;
    private OpphoerAvNaturalytelseListe opphoerAvNaturalytelseListe;
    private GjenopptakelseNaturalytelseListe gjenopptakelseNaturalytelseListe;
    private Arbeidsforhold arbeidsforhold;
    private Arbeidsgiver arbeidsgiver;
    private String arbeidstakerFNR;
    private Refusjon refusjon;
    private SykepengerIArbeidsgiverperioden sykepengerIArbeidsgiverperioden;
    private Boolean naerRelasjon = null;
    private Avsendersystem avsendersystem;

    SkjemainnholdBuilder() {
        objectFactory = new ObjectFactory();
    }
    SkjemainnholdBuilder medPleiepengerPeriodeListe(PleiepengerPeriodeListe pleiepengerPeriodeListe) {
        this.pleiepengerPeriodeListe = pleiepengerPeriodeListe;
        return this;
    }
    SkjemainnholdBuilder medOmsorgspenger(Omsorgspenger omsorgspenger) {
        this.omsorgspenger = omsorgspenger;
        return this;
    }
    SkjemainnholdBuilder medArbeidsgiver(String virksomhetsnummer, String kontaktinformasjonTLF){
        this.arbeidsgiver = createArbeidsgiver(virksomhetsnummer, kontaktinformasjonTLF);
        return this;
    }
    SkjemainnholdBuilder medArbeidsgiver(Arbeidsgiver arbeidsgiver){
        this.arbeidsgiver = arbeidsgiver;
        return this;
    }
    SkjemainnholdBuilder medArbeidsgiverPrivat(String arbeidsgiverFnr, String kontaktinformasjonTLF) {
        medArbeidsgiverPrivat(createArbeidsgiverPrivat(arbeidsgiverFnr, kontaktinformasjonTLF));
        return this;
    }
    SkjemainnholdBuilder medArbeidsgiverPrivat(ArbeidsgiverPrivat arbeidsgiverPrivat) {
        this.arbeidsgiverPrivat = arbeidsgiverPrivat;
        return this;
    }
    SkjemainnholdBuilder medOpphoerAvNaturalytelseListe(BigDecimal belopPrMnd, LocalDate fom, NaturalytelseKodeliste kodelisteNaturalytelse) {
        if (this.opphoerAvNaturalytelseListe == null) {
            opphoerAvNaturalytelseListe = new OpphoerAvNaturalytelseListe();
        }
        this.opphoerAvNaturalytelseListe.getOpphoerAvNaturalytelse()
                .add(createNaturalytelseDetaljer(belopPrMnd, fom, kodelisteNaturalytelse));
        return this;
    }
    SkjemainnholdBuilder medOpphoerAvNaturalytelseListe(OpphoerAvNaturalytelseListe opphoerAvNaturalytelseListe) {
        this.opphoerAvNaturalytelseListe = opphoerAvNaturalytelseListe;
        return this;
    }
    SkjemainnholdBuilder medGjenopptakelseNaturalytelseListe(GjenopptakelseNaturalytelseListe gjenopptakelseNaturalytelseListe) {
        this.gjenopptakelseNaturalytelseListe = gjenopptakelseNaturalytelseListe;
        return this;
    }
    SkjemainnholdBuilder medArbeidstakerFNR(String arbeidstakerFNR) {
        this.arbeidstakerFNR = arbeidstakerFNR;
        return this;
    }
    SkjemainnholdBuilder medRefusjon(Refusjon refusjon) {
        this.refusjon = refusjon;
        return this;
    }
    SkjemainnholdBuilder medSykepengerIArbeidsgiverperioden(SykepengerIArbeidsgiverperioden sykepengerIArbeidsgiverperioden) {
        this.sykepengerIArbeidsgiverperioden = sykepengerIArbeidsgiverperioden;
        return this;
    }
    SkjemainnholdBuilder medNaerRelasjon(Boolean naerRelasjon) {
        this.naerRelasjon = naerRelasjon;
        return this;
    }
    SkjemainnholdBuilder medAarsakTilInnsending(ÅrsakInnsendingKodeliste aarsakTilInnsending) {
        this.aarsakTilInnsending = aarsakTilInnsending;
        return this;
    }
    SkjemainnholdBuilder medStartdatoForeldrepengerperiodenFOM(LocalDate startidspunktForeldrepenger) {
        this.startdatoForeldrepengeperiodenFOM = startidspunktForeldrepenger;
        objectFactory.createSkjemainnholdStartdatoForeldrepengeperiode(startidspunktForeldrepenger);
        return this;
    }
    SkjemainnholdBuilder medYtelse(YtelseKodeliste ytelse) {
        this.ytelse = ytelse;
        return this;

    }
    SkjemainnholdBuilder medAvsendersystem(Avsendersystem avsendersystem) {
        this.avsendersystem = avsendersystem;
        return this;

    }
    SkjemainnholdBuilder medAvsendersystem(String avsenderSystem, String systemVersjon) {
        this.avsendersystem = createAvsendersystem(avsenderSystem, systemVersjon);
        return this;

    }
    SkjemainnholdBuilder medArbeidsforhold(Arbeidsforhold arbeidsforhold) {
        this.arbeidsforhold = arbeidsforhold;
        return this;
    }

    private Avsendersystem createAvsendersystem(String avsenderSystem, String systemVersjon) {
        var as = new Avsendersystem();
        as.setSystemnavn(avsenderSystem);
        as.setSystemversjon(systemVersjon);
        return as;
    }
    private Arbeidsgiver createArbeidsgiver(String virksomhetsnummer, String kontaktinformasjonTLF) {
        var ag = new Arbeidsgiver();
        ag.setVirksomhetsnummer(virksomhetsnummer);
        var kontaktinformasjon = new Kontaktinformasjon();
        kontaktinformasjon.setTelefonnummer(kontaktinformasjonTLF);
        kontaktinformasjon.setKontaktinformasjonNavn("Corpolarsen");
        ag.setKontaktinformasjon(kontaktinformasjon);
        return ag;
    }
    private ArbeidsgiverPrivat createArbeidsgiverPrivat(String arbeidsgiverFnr, String kontaktinformasjonTLF) {
        var ag = new ArbeidsgiverPrivat();
        ag.setArbeidsgiverFnr(arbeidsgiverFnr);
        var kontaktinformasjon = new Kontaktinformasjon();
        kontaktinformasjon.setTelefonnummer(kontaktinformasjonTLF);
        kontaktinformasjon.setKontaktinformasjonNavn("Corpolarsen");
        ag.setKontaktinformasjon(kontaktinformasjon);
        return ag;
    }
    private NaturalytelseDetaljer createNaturalytelseDetaljer(BigDecimal belopPrMnd, LocalDate fom, NaturalytelseKodeliste kodelisteNaturalytelse) {
        var of = new ObjectFactory();
        var naturalytelseDetaljer = of.createNaturalytelseDetaljer();
        naturalytelseDetaljer.setBeloepPrMnd(of.createNaturalytelseDetaljerBeloepPrMnd(belopPrMnd));
        naturalytelseDetaljer.setFom(of.createNaturalytelseDetaljerFom(fom));
        naturalytelseDetaljer.setNaturalytelseType(of.createNaturalytelseDetaljerNaturalytelseType(kodelisteNaturalytelse.value()));

        return naturalytelseDetaljer;

    }

    public Skjemainnhold build() {
        if (avsendersystem == null) {
            medAvsendersystem(createAvsendersystem("FS22", "1.0"));
        }
        if (arbeidsgiver == null && arbeidsgiverPrivat == null) {
            throw new NullPointerException("Inntektsmelding må ha arbeidsgiver eller arbeidsgiverPrivat");
        }
        Objects.requireNonNull(ytelse, "Ytelse kan ikke være null");
        Objects.requireNonNull(aarsakTilInnsending, "Årsak til innsending kan ikke være null");
        Objects.requireNonNull(arbeidstakerFNR, "arbeidstakerFNR kan ikke være null");

        skjemainnhold = new Skjemainnhold();
        if (this.pleiepengerPeriodeListe != null) {
            skjemainnhold.setPleiepengerPerioder(objectFactory.createSkjemainnholdPleiepengerPerioder(pleiepengerPeriodeListe));
        }
        if (this.omsorgspenger != null) {
            skjemainnhold.setOmsorgspenger(objectFactory.createSkjemainnholdOmsorgspenger(omsorgspenger));
        }
        if (this.arbeidsgiverPrivat != null) {
            skjemainnhold.setArbeidsgiverPrivat(objectFactory.createSkjemainnholdArbeidsgiverPrivat(this.arbeidsgiverPrivat));
        }
        if (this.opphoerAvNaturalytelseListe != null) {
            skjemainnhold.setOpphoerAvNaturalytelseListe(objectFactory.createSkjemainnholdOpphoerAvNaturalytelseListe(this.opphoerAvNaturalytelseListe));
        }
        if (this.gjenopptakelseNaturalytelseListe != null) {
            skjemainnhold.setGjenopptakelseNaturalytelseListe(objectFactory.createSkjemainnholdGjenopptakelseNaturalytelseListe(this.gjenopptakelseNaturalytelseListe));
        }
        if (this.arbeidsforhold != null) {
            skjemainnhold.setArbeidsforhold(objectFactory.createSkjemainnholdArbeidsforhold(this.arbeidsforhold));
        }
        if (this.arbeidsgiver != null) {
            skjemainnhold.setArbeidsgiver(objectFactory.createSkjemainnholdArbeidsgiver(arbeidsgiver));
        }
        if (this.arbeidstakerFNR != null && this.arbeidstakerFNR.length() > 0) {
            skjemainnhold.setArbeidstakerFnr(arbeidstakerFNR);
        }
        if (this.refusjon != null) {
            skjemainnhold.setRefusjon(objectFactory.createSkjemainnholdRefusjon(refusjon));
        }
        if (this.sykepengerIArbeidsgiverperioden != null) {
            skjemainnhold.setSykepengerIArbeidsgiverperioden(
                    objectFactory.createSkjemainnholdSykepengerIArbeidsgiverperioden(
                            this.sykepengerIArbeidsgiverperioden));
        }
        if (this.naerRelasjon != null) {
            skjemainnhold.setNaerRelasjon(this.naerRelasjon);
        }
        if (this.aarsakTilInnsending != null) {
            skjemainnhold.setAarsakTilInnsending(this.aarsakTilInnsending.value());
        }
        if (this.startdatoForeldrepengeperiodenFOM != null) {
            skjemainnhold.setStartdatoForeldrepengeperiode(
                    objectFactory.createSkjemainnholdStartdatoForeldrepengeperiode(startdatoForeldrepengeperiodenFOM));
        }
        if (this.ytelse != null) {
            skjemainnhold.setYtelse(ytelse.value());
        }
        if (this.avsendersystem != null) {
            skjemainnhold.setAvsendersystem(avsendersystem);
        }
        else {
            medAvsendersystem(createAvsendersystem("FS22", "1.0"));
        }

        return skjemainnhold;

    }

}
