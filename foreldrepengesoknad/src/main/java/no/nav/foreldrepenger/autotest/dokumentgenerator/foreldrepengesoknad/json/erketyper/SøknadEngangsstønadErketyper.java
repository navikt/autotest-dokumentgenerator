package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper;

import java.time.LocalDate;

import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.builder.EngangsstønadBuilder;
import no.nav.foreldrepenger.common.domain.BrukerRolle;

public final class SøknadEngangsstønadErketyper {

    private SøknadEngangsstønadErketyper() {
    }

    private static EngangsstønadBuilder lagEngangsstønad(BrukerRolle brukerRolle) {
        return new EngangsstønadBuilder(brukerRolle)
                .medMedlemsskap(MedlemsskapErketyper.medlemsskapNorge());
    }

    public static EngangsstønadBuilder lagEngangstønadFødsel(BrukerRolle brukerRolle,
                                                             LocalDate familiehendelse) {
        return lagEngangsstønad(brukerRolle)
                .medRelasjonTilBarn(RelasjonTilBarnErketyper.fødsel(1, familiehendelse));
    }

    public static EngangsstønadBuilder lagEngangstønadTermin(BrukerRolle brukerRolle, LocalDate familiehendelse) {
        return lagEngangsstønad(brukerRolle)
                .medRelasjonTilBarn(RelasjonTilBarnErketyper.termin(1, familiehendelse));
    }

    public static EngangsstønadBuilder lagEngangstønadAdopsjon(BrukerRolle brukerRolle,
                                                               LocalDate omsorgsovertakelsedato, Boolean ektefellesBarn) {
        return lagEngangsstønad(brukerRolle)
                .medRelasjonTilBarn(RelasjonTilBarnErketyper.adopsjon(omsorgsovertakelsedato, ektefellesBarn));
    }

    public static EngangsstønadBuilder lagEngangstønadOmsorg(BrukerRolle brukerRolle,
                                                             LocalDate omsorgsovertakelsedato) {
        return lagEngangsstønad(brukerRolle)
                .medRelasjonTilBarn(RelasjonTilBarnErketyper.omsorgsovertakelse(omsorgsovertakelsedato));
    }
}
