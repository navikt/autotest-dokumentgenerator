package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper;

import java.time.LocalDate;

import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.ProsentAndel;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.svangerskapspenger.tilrettelegging.DelvisTilrettelegging;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.svangerskapspenger.tilrettelegging.HelTilrettelegging;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.svangerskapspenger.tilrettelegging.IngenTilrettelegging;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.svangerskapspenger.tilrettelegging.arbeidsforhold.Arbeidsforhold;

public final class TilretteleggingsErketyper {

    private TilretteleggingsErketyper() {
    }

    public static HelTilrettelegging helTilrettelegging(LocalDate behovForTilretteleggingFom,
                                                        LocalDate tilrettelagtArbeidFom,
                                                        Arbeidsforhold arbeidsforhold) {
        return new HelTilrettelegging(arbeidsforhold, behovForTilretteleggingFom, tilrettelagtArbeidFom, null);
    }

    public static DelvisTilrettelegging delvisTilrettelegging(LocalDate behovForTilretteleggingFom,
                                                              LocalDate tilrettelagtArbeidFom,
                                                              Arbeidsforhold arbeidsforhold,
                                                              Number stillingsprosent) {
        return new DelvisTilrettelegging(
                arbeidsforhold,
                behovForTilretteleggingFom,
                tilrettelagtArbeidFom,
                new ProsentAndel(stillingsprosent.doubleValue()),
                null);

    }

    public static IngenTilrettelegging ingenTilrettelegging(LocalDate behovForTilretteleggingFom,
                                                            LocalDate slutteArbeidFom,
                                                            Arbeidsforhold arbeidsforhold) {
        return new IngenTilrettelegging(
                arbeidsforhold,
                behovForTilretteleggingFom,
                slutteArbeidFom,
                null);
    }

}
