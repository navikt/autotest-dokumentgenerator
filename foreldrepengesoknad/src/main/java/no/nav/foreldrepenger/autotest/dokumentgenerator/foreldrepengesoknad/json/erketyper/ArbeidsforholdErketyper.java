package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper;

import no.nav.foreldrepenger.common.domain.Fødselsnummer;
import no.nav.foreldrepenger.common.domain.Orgnummer;
import no.nav.foreldrepenger.common.domain.svangerskapspenger.tilrettelegging.arbeidsforhold.PrivatArbeidsgiver;
import no.nav.foreldrepenger.common.domain.svangerskapspenger.tilrettelegging.arbeidsforhold.SelvstendigNæringsdrivende;
import no.nav.foreldrepenger.common.domain.svangerskapspenger.tilrettelegging.arbeidsforhold.Virksomhet;

public final class ArbeidsforholdErketyper {

    private ArbeidsforholdErketyper() {
    }

    public static Virksomhet virksomhet(Orgnummer orgnummer) {
        return new Virksomhet(orgnummer);
    }

    public static PrivatArbeidsgiver privatArbeidsgiver(String fnr) {
        return new PrivatArbeidsgiver(new Fødselsnummer(fnr));
    }

    public static SelvstendigNæringsdrivende selvstendigNæringsdrivende() {
        return new SelvstendigNæringsdrivende("", "");
    }

}
