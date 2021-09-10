package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.erketyper;

import java.time.LocalDate;
import java.util.List;

import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.SøkersRolle;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.builders.SvangerskapspengerBuilder;
import no.nav.vedtak.felles.xml.soeknad.svangerskapspenger.v1.Tilrettelegging;

public class SøknadSvangerskapspengerErketype {

    private SøknadSvangerskapspengerErketype() {
        // Skal ikke instansieres
    }

    public static SvangerskapspengerBuilder lagSvangerskapspengerSøknad(String søkerAktørId, SøkersRolle søkersRolle,
            LocalDate termin, List<Tilrettelegging> tilretteleggingListe) {
        return new SvangerskapspengerBuilder(søkerAktørId, søkersRolle, tilretteleggingListe)
                .medTermindato(termin)
                .medMedlemskap(MedlemskapErketyper.medlemskapNorge());
    }
}
