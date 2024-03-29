package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper;

import static java.util.Collections.emptyList;

import java.time.LocalDate;
import java.util.List;

import com.neovisionaries.i18n.CountryCode;

import no.nav.foreldrepenger.common.domain.felles.LukketPeriode;
import no.nav.foreldrepenger.common.domain.felles.medlemskap.Medlemsskap;
import no.nav.foreldrepenger.common.domain.felles.medlemskap.Utenlandsopphold;

public final class MedlemsskapErketyper {

    private MedlemsskapErketyper() {
    }

    public static Medlemsskap medlemsskapNorge() {
        return new Medlemsskap(emptyList(), emptyList());
    }

    public static Medlemsskap medlemskapUtlandetForrige12mnd() {
        return new Medlemsskap(List.of(utenlandsopphold(LocalDate.now().minusYears(2), LocalDate.now())), emptyList());
    }

    private static Utenlandsopphold utenlandsopphold(LocalDate fom, LocalDate tom) {
        return new Utenlandsopphold(CountryCode.US, new LukketPeriode(fom, tom));
    }
}
