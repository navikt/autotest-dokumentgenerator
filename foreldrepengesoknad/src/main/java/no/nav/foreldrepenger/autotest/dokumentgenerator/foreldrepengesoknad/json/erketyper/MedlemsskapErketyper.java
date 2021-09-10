package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper;

import java.time.LocalDate;
import java.util.List;

import com.neovisionaries.i18n.CountryCode;

import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.LukketPeriode;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.medlemskap.ArbeidsInformasjon;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.medlemskap.Medlemsskap;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.medlemskap.Utenlandsopphold;

public final class MedlemsskapErketyper {

    private MedlemsskapErketyper() {
    }

    public static Medlemsskap medlemsskapNorge() {
        return Medlemsskap.builder()
                .arbeidSiste12(ArbeidsInformasjon.ARBEIDET_I_NORGE)
                .build();
    }

    public static Medlemsskap medlemskapUtlandetForrige12mnd() {
        return Medlemsskap.builder()
                .arbeidSiste12(ArbeidsInformasjon.ARBEIDET_I_NORGE)
                .utenlandsopphold(List.of(utenlandsopphold(LocalDate.now().minusYears(2), LocalDate.now())))
                .build();
    }

    private static Utenlandsopphold utenlandsopphold(LocalDate fom, LocalDate tom) {
        return new Utenlandsopphold(CountryCode.US, new LukketPeriode(fom, tom));
    }
}
