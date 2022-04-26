package no.nav.foreldrepenger.autotest.dokumentgenerator.inntektsmelding;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import no.nav.foreldrepenger.autotest.dokumentgenerator.inntektsmelding.builders.InntektsmeldingBuilder;
import no.nav.inntektsmelding.xml.kodeliste._20180702.YtelseKodeliste;
import no.nav.inntektsmelding.xml.kodeliste._20180702.ÅrsakInnsendingKodeliste;

class InntektsmeldingBuilderTest{


    @Test
    void IMtest(){
        var inntektsmeldingBuilder = new InntektsmeldingBuilder()
                .medBeregnetInntekt(BigDecimal.valueOf(3000))
                .medArbeidstakerFNR("111111")
                .medStartdatoForeldrepengerperiodenFOM(LocalDate.now().plusWeeks(2))
                .medArbeidsgiver("13213123", "3333333")
                .medArbeidstakerFNR("121313212313")
                .medYtelse(YtelseKodeliste.FORELDREPENGER)
                .medAarsakTilInnsending(ÅrsakInnsendingKodeliste.NY)
                .medFørsteFraværsdag(LocalDate.now().minusWeeks(3));
        var im = inntektsmeldingBuilder.build();
        assertThat(im).isNotNull();
        System.out.println("end");
    }
}
