package no.nav.foreldrepenger.autotest.dokumentgenerator.inntektsmelding.erketyper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import no.nav.foreldrepenger.autotest.dokumentgenerator.inntektsmelding.builders.InntektsmeldingBuilder;
import no.nav.foreldrepenger.common.domain.Fødselsnummer;
import no.nav.foreldrepenger.common.domain.Orgnummer;
import no.nav.foreldrepenger.vtp.kontrakter.TestscenarioDto;
import no.nav.foreldrepenger.vtp.testmodell.inntektytelse.arbeidsforhold.Arbeidsforhold;
import no.nav.foreldrepenger.vtp.testmodell.inntektytelse.inntektkomponent.Inntektsperiode;
import no.nav.inntektsmelding.xml.kodeliste._20180702.YtelseKodeliste;
import no.nav.inntektsmelding.xml.kodeliste._20180702.ÅrsakInnsendingKodeliste;

public class InntektsmeldingForeldrepengeErketyper {

    private InntektsmeldingForeldrepengeErketyper() {
    }

    @Deprecated(since = "2.0.0", forRemoval = true)
    public static List<InntektsmeldingBuilder> makeInntektsmeldingFromTestscenario(TestscenarioDto testscenario,
                                                                                   LocalDate startDatoForeldrepenger) {
        var søkerIdent = testscenario.personopplysninger().søkerIdent();
        return makeInntektsmeldingFromTestscenarioMedIdent(testscenario, søkerIdent, startDatoForeldrepenger, false);
    }

    @Deprecated(since = "2.0.0", forRemoval = true)
    public static List<InntektsmeldingBuilder> makeInntektsmeldingFromTestscenarioMedIdent(
            TestscenarioDto testscenario,
            String søkerIdent,
            LocalDate startDatoForeldrepenger,
            boolean erAnnenpart) {

        List<Inntektsperiode> inntektsperioder;
        List<Arbeidsforhold> arbeidsforholdEtterStartdatoFP;
        if (erAnnenpart) {
            inntektsperioder = testscenario.scenariodataAnnenpartDto().inntektskomponentModell()
                    .getInntektsperioderSplittMånedlig();
            arbeidsforholdEtterStartdatoFP = testscenario.scenariodataAnnenpartDto().arbeidsforholdModell()
                    .arbeidsforhold();
        } else {
            inntektsperioder = testscenario.scenariodataDto().inntektskomponentModell()
                    .getInntektsperioderSplittMånedlig();
            arbeidsforholdEtterStartdatoFP = testscenario.scenariodataDto().arbeidsforholdModell()
                    .arbeidsforhold();
        }

        List<InntektsmeldingBuilder> inntektsmeldinger = new ArrayList<>();
        for (var arbeidsforhold : arbeidsforholdEtterStartdatoFP) {
            String arbeidsgiverOrgnr = arbeidsforhold.arbeidsgiverOrgnr();
            var sisteInntektsperiode = inntektsperioder.stream()
                    .filter(inntektsperiode -> inntektsperiode.orgnr().equals(arbeidsgiverOrgnr))
                    .max(Comparator.comparing(Inntektsperiode::tom))
                    .orElseThrow(
                            () -> new IllegalStateException("Utvikler feil: Arbeidsforhold mangler inntektsperiode"));
            Integer beløp = sisteInntektsperiode.beløp();
            inntektsmeldinger.add(lagInntektsmelding(beløp, søkerIdent, startDatoForeldrepenger, arbeidsgiverOrgnr));
        }

        return inntektsmeldinger;
    }

    public static InntektsmeldingBuilder lagInntektsmelding(Integer beløp, Fødselsnummer fnr, LocalDate fpStartdato, Orgnummer orgNr) {
        return lagInntektsmelding(beløp, fnr.getFnr(), fpStartdato, orgNr.orgnr());
    }

    public static InntektsmeldingBuilder lagInntektsmelding(Integer beløp, String fnr, LocalDate fpStartdato, String orgNr) {
        return new InntektsmeldingBuilder()
                .medBeregnetInntekt(BigDecimal.valueOf(beløp))
                .medArbeidstakerFNR(fnr)
                .medYtelse(YtelseKodeliste.FORELDREPENGER)
                .medAarsakTilInnsending(ÅrsakInnsendingKodeliste.NY)
                .medStartdatoForeldrepengerperiodenFOM(fpStartdato)
                .medAvsendersystem("FS22", "1.0")
                .medArbeidsgiver(orgNr, "41925090");
    }

    public static InntektsmeldingBuilder lagInntektsmeldingPrivateArbeidsgiver(Integer beløp, String fnr,
                                                                               LocalDate fpStartdato,
                                                                               String fnrArbeidsgiver) {
        return new InntektsmeldingBuilder()
                .medBeregnetInntekt(BigDecimal.valueOf(beløp))
                .medArbeidstakerFNR(fnr)
                .medYtelse(YtelseKodeliste.FORELDREPENGER)
                .medAarsakTilInnsending(ÅrsakInnsendingKodeliste.NY)
                .medStartdatoForeldrepengerperiodenFOM(fpStartdato)
                .medAvsendersystem("FS22", "1.0")
                .medArbeidsgiverPrivat(fnrArbeidsgiver, "41925090");
    }
}
