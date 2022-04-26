package no.nav.foreldrepenger.autotest.dokumentgenerator.inntektsmelding.erketyper;

import java.math.BigDecimal;

import no.nav.foreldrepenger.autotest.dokumentgenerator.inntektsmelding.builders.InntektsmeldingBuilder;
import no.nav.foreldrepenger.common.domain.ArbeidsgiverIdentifikator;
import no.nav.foreldrepenger.common.domain.Fødselsnummer;
import no.nav.foreldrepenger.common.domain.Orgnummer;
import no.nav.inntektsmelding.xml.kodeliste._20180702.YtelseKodeliste;
import no.nav.inntektsmelding.xml.kodeliste._20180702.ÅrsakInnsendingKodeliste;

public class InntektsmeldingSvangerskapspengerErketyper {

    private InntektsmeldingSvangerskapspengerErketyper() {
    }

    public static InntektsmeldingBuilder lagSvangerskapspengerInntektsmelding(Fødselsnummer fnr, Integer beløp,
                                                                              ArbeidsgiverIdentifikator arbeidsgiverIdentifikator) {
        if (arbeidsgiverIdentifikator instanceof Orgnummer o) {
            return lagSvangerskapspengerInntektsmelding(fnr, beløp, o);
        } else {
            throw new IllegalStateException("Ikke støttet. For privat arbeidgiver bruk lagInntektsmeldingPrivateArbeidsgiver()");

        }
    }

    public static InntektsmeldingBuilder lagSvangerskapspengerInntektsmelding(Fødselsnummer fnr, Integer beløp,
                                                                              Orgnummer orgnummer) {
        return new InntektsmeldingBuilder()
                .medArbeidstakerFNR(fnr.value())
                .medBeregnetInntekt(BigDecimal.valueOf(beløp))
                .medYtelse(YtelseKodeliste.SVANGERSKAPSPENGER)
                .medAarsakTilInnsending(ÅrsakInnsendingKodeliste.NY)
                .medArbeidsgiver(orgnummer.value(), "41925090")
                .medAvsendersystem("FS32", "1.0");
    }

    public static InntektsmeldingBuilder lagInntektsmeldingPrivateArbeidsgiver(Fødselsnummer fnr, Integer beløp,
            Fødselsnummer fnrArbeidsgiver) {
        return new InntektsmeldingBuilder()
                .medArbeidstakerFNR(fnr.value())
                .medBeregnetInntekt(BigDecimal.valueOf(beløp))
                .medYtelse(YtelseKodeliste.SVANGERSKAPSPENGER)
                .medAarsakTilInnsending(ÅrsakInnsendingKodeliste.NY)
                .medAvsendersystem("FS32", "1.0")
                .medArbeidsgiverPrivat(fnrArbeidsgiver.value(), "41925090");
    }
}
