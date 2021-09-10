package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper;

import java.time.LocalDate;
import java.util.List;

import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.relasjontilbarn.Adopsjon;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.relasjontilbarn.FremtidigFødsel;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.relasjontilbarn.Fødsel;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.relasjontilbarn.OmsorgsOvertakelsesÅrsak;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.relasjontilbarn.Omsorgsovertakelse;

public final class RelasjonTilBarnErketyper {

    private RelasjonTilBarnErketyper() {
    }

    public static Fødsel fødsel(int antallBarn, LocalDate fødselsdato) {
        return Fødsel.builder()
                .antallBarn(antallBarn)
                .fødselsdato(List.of(fødselsdato))
                .build();
    }

    public static FremtidigFødsel termin(int antallBarn, LocalDate termindato) {
        return FremtidigFødsel.builder()
                .antallBarn(antallBarn)
                .terminDato(termindato)
                .utstedtDato(termindato.minusMonths(1))
                .build();
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

    public static Omsorgsovertakelse omsorgsovertakelse(LocalDate omsorgsovertakelsedato, OmsorgsOvertakelsesÅrsak omsorgsOvertakelsesÅrsak) {
        return Omsorgsovertakelse.builder()
                .antallBarn(1)
                .fødselsdato(List.of(LocalDate.now().minusMonths(6)))
                .omsorgsovertakelsesdato(omsorgsovertakelsedato)
                .årsak(omsorgsOvertakelsesÅrsak)
                .build();
    }

}
