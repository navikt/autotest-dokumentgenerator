package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper;

import java.time.LocalDate;
import java.util.List;

import no.nav.foreldrepenger.common.domain.felles.relasjontilbarn.Adopsjon;
import no.nav.foreldrepenger.common.domain.felles.relasjontilbarn.FremtidigFødsel;
import no.nav.foreldrepenger.common.domain.felles.relasjontilbarn.Fødsel;
import no.nav.foreldrepenger.common.domain.felles.relasjontilbarn.Omsorgsovertakelse;

public final class RelasjonTilBarnErketyper {

    private RelasjonTilBarnErketyper() {
    }

    public static Fødsel fødsel(int antallBarn, LocalDate fødselsdato) {
        return new Fødsel(antallBarn, List.of(fødselsdato), fødselsdato, null);
    }

    public static Fødsel fødselMedTermin(int antallBarn, LocalDate fødselsdato, LocalDate termindato) {
        return new Fødsel(antallBarn, List.of(fødselsdato), termindato, null);
    }

    public static FremtidigFødsel termin(int antallBarn, LocalDate termindato) {
        return new FremtidigFødsel(antallBarn, termindato, termindato.minusMonths(1), null);
    }

    public static Adopsjon adopsjon(LocalDate omsorgsovertakelsesdato, Boolean ektefellesBarn) {
        return Adopsjon.builder()
                .antallBarn(1)
                .ektefellesBarn(ektefellesBarn)
                .fødselsdato(List.of(LocalDate.now().minusYears(10)))
                .ankomstDato(omsorgsovertakelsesdato)
                .omsorgsovertakelsesdato(omsorgsovertakelsesdato)
                .build();
    }

    public static Omsorgsovertakelse omsorgsovertakelse(LocalDate omsorgsovertakelsedato) {
        return Omsorgsovertakelse.builder()
                .antallBarn(1)
                .fødselsdato(List.of(LocalDate.now().minusMonths(6)))
                .omsorgsovertakelsesdato(omsorgsovertakelsedato)
                .build();
    }

}
