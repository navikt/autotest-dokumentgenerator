package no.nav.foreldrepenger.autotest.dokumentgenerator.inntektsmelding.erketyper;

import java.math.BigDecimal;
import java.time.LocalDate;

import no.nav.foreldrepenger.autotest.dokumentgenerator.inntektsmelding.builders.InntektsmeldingBuilder;
import no.nav.foreldrepenger.common.domain.ArbeidsgiverIdentifikator;
import no.nav.foreldrepenger.common.domain.Fødselsnummer;
import no.nav.foreldrepenger.common.domain.Orgnummer;
import no.nav.inntektsmelding.xml.kodeliste._20180702.YtelseKodeliste;
import no.nav.inntektsmelding.xml.kodeliste._20180702.ÅrsakInnsendingKodeliste;

public class InntektsmeldingForeldrepengeErketyper {

    private InntektsmeldingForeldrepengeErketyper() {
    }

    public static InntektsmeldingBuilder lagInntektsmelding(Integer beløp, Fødselsnummer fnr, LocalDate fpStartdato, ArbeidsgiverIdentifikator arbeidsgiverIdentifikator) {
        if (arbeidsgiverIdentifikator instanceof Orgnummer o) {
            return new InntektsmeldingBuilder()
                    .medBeregnetInntekt(BigDecimal.valueOf(beløp))
                    .medArbeidstakerFNR(fnr.value())
                    .medYtelse(YtelseKodeliste.FORELDREPENGER)
                    .medAarsakTilInnsending(ÅrsakInnsendingKodeliste.NY)
                    .medStartdatoForeldrepengerperiodenFOM(fpStartdato)
                    .medAvsendersystem("FS22", "1.0")
                    .medArbeidsgiver(o.value(), "41925090");
        } else {
            throw new IllegalStateException("Bruk metode lagInntektsmeldingPrivateArbeidsgiver() siden det er privat arbeidsgiver!");
        }
    }

    public static InntektsmeldingBuilder lagInntektsmeldingPrivateArbeidsgiver(Integer beløp, Fødselsnummer fnr,
                                                                               LocalDate fpStartdato,
                                                                               Fødselsnummer fnrArbeidsgiver) {
        return new InntektsmeldingBuilder()
                .medBeregnetInntekt(BigDecimal.valueOf(beløp))
                .medArbeidstakerFNR(fnr.value())
                .medYtelse(YtelseKodeliste.FORELDREPENGER)
                .medAarsakTilInnsending(ÅrsakInnsendingKodeliste.NY)
                .medStartdatoForeldrepengerperiodenFOM(fpStartdato)
                .medAvsendersystem("FS22", "1.0")
                .medArbeidsgiverPrivat(fnrArbeidsgiver.value(), "41925090");
    }
}
