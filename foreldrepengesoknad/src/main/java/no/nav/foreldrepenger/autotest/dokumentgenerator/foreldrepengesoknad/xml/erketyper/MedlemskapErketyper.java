package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.erketyper;

import java.time.LocalDate;

import no.nav.vedtak.felles.xml.soeknad.felles.v3.Medlemskap;
import no.nav.vedtak.felles.xml.soeknad.felles.v3.OppholdNorge;
import no.nav.vedtak.felles.xml.soeknad.felles.v3.OppholdUtlandet;
import no.nav.vedtak.felles.xml.soeknad.felles.v3.Periode;
import no.nav.vedtak.felles.xml.soeknad.kodeverk.v3.Land;

public class MedlemskapErketyper {

    private MedlemskapErketyper() {
        // Skal ikke instansieres
    }

    public static Medlemskap medlemskapNorge() {
        var medlemskap = new Medlemskap();
        medlemskap.setBoddINorgeSiste12Mnd(true);
        medlemskap.setBorINorgeNeste12Mnd(true);
        medlemskap.setINorgeVedFoedselstidspunkt(true);
        medlemskap.getOppholdNorge().add(oppholdNorge(LocalDate.now().minusYears(2), LocalDate.now()));
        medlemskap.getOppholdNorge().add(oppholdNorge(LocalDate.now(), LocalDate.now().plusYears(2)));
        return medlemskap;
    }

    public static Medlemskap medlemskapUtlandetForrige12mnd() {
        var medlemskap = new Medlemskap();
        medlemskap.setINorgeVedFoedselstidspunkt(true);
        medlemskap.setBorINorgeNeste12Mnd(true);
        medlemskap.setBoddINorgeSiste12Mnd(false);
        medlemskap.getOppholdUtlandet().add(oppholdUtlandet(LocalDate.now().minusYears(2), LocalDate.now()));
        return medlemskap;
    }


    private static OppholdNorge oppholdNorge(LocalDate fom, LocalDate tom) {
        var oppholdNorge = new OppholdNorge();
        var periode = new Periode();
        periode.setFom(fom);
        periode.setTom(tom);

        oppholdNorge.setPeriode(periode);
        return oppholdNorge;
    }


    private static OppholdUtlandet oppholdUtlandet(LocalDate fom, LocalDate tom) {
        var oppholdUtlandet = new OppholdUtlandet();
        var land = new Land();
        land.setKode("USA");
        land.setKodeverk("LANDKODER");
        oppholdUtlandet.setLand(land);

        var periode = new Periode();
        periode.setFom(fom);
        periode.setTom(tom);
        oppholdUtlandet.setPeriode(periode);
        return oppholdUtlandet;
    }

}
