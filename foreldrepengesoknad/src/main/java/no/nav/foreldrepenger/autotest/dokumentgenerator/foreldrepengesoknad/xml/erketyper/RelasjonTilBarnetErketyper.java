package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.erketyper;

import java.time.LocalDate;

import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.OmsorgsovertakelseÅrsak;
import no.nav.vedtak.felles.xml.soeknad.felles.v3.Adopsjon;
import no.nav.vedtak.felles.xml.soeknad.felles.v3.Foedsel;
import no.nav.vedtak.felles.xml.soeknad.felles.v3.Omsorgsovertakelse;
import no.nav.vedtak.felles.xml.soeknad.felles.v3.Termin;
import no.nav.vedtak.felles.xml.soeknad.kodeverk.v3.Omsorgsovertakelseaarsaker;

public class RelasjonTilBarnetErketyper {

    private RelasjonTilBarnetErketyper() {
        // Skal ikke instansieres
    }

    public static Foedsel fødsel(LocalDate fødselsdato) {
        return fødsel(1, fødselsdato);
    }

    public static Foedsel fødsel(int antall, LocalDate fødselsdato) {
        var soekersRelasjonTilBarnet = new Foedsel();
        soekersRelasjonTilBarnet.setAntallBarn(antall);
        soekersRelasjonTilBarnet.setFoedselsdato((fødselsdato));

        return soekersRelasjonTilBarnet;
    }

    public static Termin termin(LocalDate termindato) {
        return termin(1, termindato);
    }

    public static Termin termin(int antall, LocalDate termindato) {
        var termin = new Termin();
        termin.setAntallBarn(antall);
        termin.setTermindato((termindato));
        termin.setUtstedtdato((termindato.minusMonths(1)));
        return termin;
    }

    public static Foedsel fødselMedTermin(int antall, LocalDate fødselsdato, LocalDate termindato) {
        var soekersRelasjonTilBarnet = new Foedsel();
        soekersRelasjonTilBarnet.setAntallBarn(antall);
        soekersRelasjonTilBarnet.setFoedselsdato((fødselsdato));
        soekersRelasjonTilBarnet.setTermindato(termindato);

        return soekersRelasjonTilBarnet;
    }

    public static Adopsjon adopsjon(boolean ektefellesBarn) {
        return adopsjon(LocalDate.now().plusMonths(1), ektefellesBarn);
    }

    public static Adopsjon adopsjon(LocalDate omsorgsovertakelsedatoe, Boolean ektefellesBarn) {
        var adopsjon = new Adopsjon();
        adopsjon.setAntallBarn(1);
        adopsjon.setAdopsjonAvEktefellesBarn(ektefellesBarn);
        adopsjon.getFoedselsdato().add((LocalDate.now().minusYears(10)));
        adopsjon.setAnkomstdato((omsorgsovertakelsedatoe));
        adopsjon.setOmsorgsovertakelsesdato((omsorgsovertakelsedatoe));

        return adopsjon;
    }

    // Mulige aarsaker ANDRE_FORELDER_DØD, OVERTATT_OMSORG, OVERTATT_OMSORG_F,
    // ADOPTERER_ALENE
    public static Omsorgsovertakelse omsorgsovertakelse(OmsorgsovertakelseÅrsak årsak) {
        var omsorgsovertakelse = new Omsorgsovertakelse();
        omsorgsovertakelse.setAntallBarn(1);
        var omsorgsovertakelseaarsaker = new Omsorgsovertakelseaarsaker();
        if (årsak == OmsorgsovertakelseÅrsak.ANDRE_FORELDER_DØD) {
            omsorgsovertakelseaarsaker.setKode("ANDRE_FORELDER_DØD");
        } else if (årsak == OmsorgsovertakelseÅrsak.OVERTATT_OMSORG) {
            omsorgsovertakelseaarsaker.setKode("OVERTATT_OMSORG");
        } else if (årsak == OmsorgsovertakelseÅrsak.OVERTATT_OMSORG_F) {
            omsorgsovertakelseaarsaker.setKode("OVERTATT_OMSORG_F");
        } else {
            omsorgsovertakelseaarsaker.setKode("ADOPTERER_ALENE");
        }
        omsorgsovertakelseaarsaker.setKodeverk("FAR_SOEKER_TYPE");
        omsorgsovertakelse.setOmsorgsovertakelseaarsak(omsorgsovertakelseaarsaker);
        omsorgsovertakelse.setOmsorgsovertakelsesdato((LocalDate.now().plusMonths(1)));
        omsorgsovertakelse.getFoedselsdato().add((LocalDate.now().minusMonths(6)));

        return omsorgsovertakelse;
    }

}
