package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.erketyper;

import no.nav.vedtak.felles.xml.soeknad.svangerskapspenger.v1.Frilanser;
import no.nav.vedtak.felles.xml.soeknad.svangerskapspenger.v1.PrivatArbeidsgiver;
import no.nav.vedtak.felles.xml.soeknad.svangerskapspenger.v1.SelvstendigNæringsdrivende;
import no.nav.vedtak.felles.xml.soeknad.svangerskapspenger.v1.Virksomhet;

public class ArbeidsforholdErketyper {

    private ArbeidsforholdErketyper() {
        // Skal ikke instansieres
    }

    public static Virksomhet virksomhet(String identifikator) {
        var virksomhet = new Virksomhet();
        virksomhet.setIdentifikator(identifikator);
        return virksomhet;
    }

    public static PrivatArbeidsgiver privatArbeidsgiver() {
        return new PrivatArbeidsgiver();
    }

    public static SelvstendigNæringsdrivende selvstendigNæringsdrivende() {
        var selvstendigNæringsdrivende = new SelvstendigNæringsdrivende();
        selvstendigNæringsdrivende.setOpplysningerOmRisikofaktorer("");
        selvstendigNæringsdrivende.setOpplysningerOmTilretteleggingstiltak("");
        return selvstendigNæringsdrivende;
    }

    public static Frilanser frilanser() {
        return new Frilanser();
    }

}
