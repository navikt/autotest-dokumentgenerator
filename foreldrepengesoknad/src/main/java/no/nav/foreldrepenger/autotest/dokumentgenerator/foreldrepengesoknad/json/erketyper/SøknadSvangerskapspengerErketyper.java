package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper;

import java.time.LocalDate;
import java.util.List;

import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.builder.SvangerskapspengerBuilder;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.BrukerRolle;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.svangerskapspenger.tilrettelegging.Tilrettelegging;

public final class SøknadSvangerskapspengerErketyper {

    private SøknadSvangerskapspengerErketyper() {
    }

    public static SvangerskapspengerBuilder lagSvangerskapspengerSøknad(BrukerRolle brukerRolle,
                                                                        LocalDate termin,
                                                                        List<Tilrettelegging> tilretteleggingListe) {
        return new SvangerskapspengerBuilder(brukerRolle, tilretteleggingListe)
                .medTermindato(termin)
                .medMedlemsskap(MedlemsskapErketyper.medlemsskapNorge());
    }
}
