package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper;

import static java.util.Collections.emptyList;

import java.time.LocalDate;
import java.util.List;

import com.neovisionaries.i18n.CountryCode;

import no.nav.foreldrepenger.common.domain.felles.LukketPeriode;
import no.nav.foreldrepenger.common.domain.felles.medlemskap.ArbeidsInformasjon;
import no.nav.foreldrepenger.common.domain.felles.medlemskap.FramtidigOppholdsInformasjon;
import no.nav.foreldrepenger.common.domain.felles.medlemskap.Medlemsskap;
import no.nav.foreldrepenger.common.domain.felles.medlemskap.TidligereOppholdsInformasjon;
import no.nav.foreldrepenger.common.domain.felles.medlemskap.Utenlandsopphold;

public final class MedlemsskapErketyper {

    private MedlemsskapErketyper() {
    }

    public static Medlemsskap medlemsskapNorge() {
        return new Medlemsskap(
                new TidligereOppholdsInformasjon(ArbeidsInformasjon.ARBEIDET_I_NORGE, null),
                new FramtidigOppholdsInformasjon(emptyList()));
    }

    public static Medlemsskap medlemskapUtlandetForrige12mnd() {
        return new Medlemsskap(
                new TidligereOppholdsInformasjon(
                        ArbeidsInformasjon.ARBEIDET_I_NORGE, List.of(utenlandsopphold(LocalDate.now().minusYears(2), LocalDate.now()))),
                new FramtidigOppholdsInformasjon(emptyList()));
    }

    private static Utenlandsopphold utenlandsopphold(LocalDate fom, LocalDate tom) {
        return new Utenlandsopphold(CountryCode.US, new LukketPeriode(fom, tom));
    }
}
