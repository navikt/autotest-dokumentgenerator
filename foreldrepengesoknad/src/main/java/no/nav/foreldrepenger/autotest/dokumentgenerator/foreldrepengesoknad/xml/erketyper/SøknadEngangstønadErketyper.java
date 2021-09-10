package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.erketyper;

import java.time.LocalDate;

import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.OmsorgsovertakelseÅrsak;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.SøkersRolle;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.builders.EngangstønadBuilder;

public class SøknadEngangstønadErketyper {

    private SøknadEngangstønadErketyper() {
        // Skal ikke instansieres
    }

    private static EngangstønadBuilder lagEngangstønad(String aktørID, SøkersRolle søkersRolle) {
        return new EngangstønadBuilder(aktørID, søkersRolle)
                .medMedlemskap(MedlemskapErketyper.medlemskapNorge());
    }

    public static EngangstønadBuilder lagEngangstønadFødsel(String aktørID, SøkersRolle søkersRolle,
            LocalDate familiehendelse) {
        return lagEngangstønad(aktørID, søkersRolle)
                .medSoekersRelasjonTilBarnet(RelasjonTilBarnetErketyper.fødsel(1, familiehendelse));
    }

    public static EngangstønadBuilder lagEngangstønadTermin(String aktørID, SøkersRolle søkersRolle,
            LocalDate familiehendelse) {
        return lagEngangstønad(aktørID, søkersRolle)
                .medSoekersRelasjonTilBarnet(RelasjonTilBarnetErketyper.termin(1, familiehendelse));
    }

    public static EngangstønadBuilder lagEngangstønadAdopsjon(String aktørID, SøkersRolle søkersRolle,
            Boolean ektefellesBarn) {
        return lagEngangstønad(aktørID, søkersRolle)
                .medSoekersRelasjonTilBarnet(RelasjonTilBarnetErketyper.adopsjon(ektefellesBarn));
    }

    public static EngangstønadBuilder lagEngangstønadOmsorg(String aktørID, SøkersRolle søkersRolle,
            OmsorgsovertakelseÅrsak årsak) {
        return lagEngangstønad(aktørID, søkersRolle)
                .medSoekersRelasjonTilBarnet(RelasjonTilBarnetErketyper.omsorgsovertakelse(årsak));

    }
}
