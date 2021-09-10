package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.erketyper;

import java.math.BigInteger;
import java.time.LocalDate;

import no.nav.vedtak.felles.xml.soeknad.felles.v3.Periode;
import no.nav.vedtak.felles.xml.soeknad.foreldrepenger.v3.AnnenOpptjening;
import no.nav.vedtak.felles.xml.soeknad.foreldrepenger.v3.Frilans;
import no.nav.vedtak.felles.xml.soeknad.foreldrepenger.v3.Frilansoppdrag;
import no.nav.vedtak.felles.xml.soeknad.foreldrepenger.v3.NorskOrganisasjon;
import no.nav.vedtak.felles.xml.soeknad.foreldrepenger.v3.Opptjening;
import no.nav.vedtak.felles.xml.soeknad.foreldrepenger.v3.Regnskapsfoerer;
import no.nav.vedtak.felles.xml.soeknad.foreldrepenger.v3.UtenlandskArbeidsforhold;
import no.nav.vedtak.felles.xml.soeknad.kodeverk.v3.AnnenOpptjeningTyper;
import no.nav.vedtak.felles.xml.soeknad.kodeverk.v3.Land;
import no.nav.vedtak.felles.xml.soeknad.kodeverk.v3.Virksomhetstyper;

public class OpptjeningErketyper {

    private OpptjeningErketyper() {
    }

    public static Opptjening medFrilansOpptjening() {

        var periode = new Periode();
        periode.setFom((LocalDate.now().minusYears(2)));
        periode.setTom((LocalDate.now()));

        var frilansoppdrag = new Frilansoppdrag();
        frilansoppdrag.setOppdragsgiver("Tims BBQ og fotmassasje");
        frilansoppdrag.setPeriode(periode);

        var frilans = new Frilans();
        frilans.setHarInntektFraFosterhjem(false);
        frilans.setErNyoppstartet(false);
        frilans.setNaerRelasjon(false);
        frilans.getPeriode().add(periode);
        frilans.getFrilansoppdrag().add(frilansoppdrag);

        var opptjening = new Opptjening();
        opptjening.setFrilans(frilans);
        return opptjening;

    }

    public static Opptjening medFrilansOpptjening(LocalDate Fom, LocalDate Tom) {

        var periode = new Periode();
        periode.setFom(Fom);
        periode.setTom(Tom);

        var frilansoppdrag = new Frilansoppdrag();
        frilansoppdrag.setOppdragsgiver("Tims BBQ og fotmassasje");
        frilansoppdrag.setPeriode(periode);

        var frilans = new Frilans();
        frilans.setHarInntektFraFosterhjem(false);
        frilans.setErNyoppstartet(false);
        frilans.setNaerRelasjon(false);
        frilans.getPeriode().add(periode);
        frilans.getFrilansoppdrag().add(frilansoppdrag);

        var opptjening = new Opptjening();
        opptjening.setFrilans(frilans);
        return opptjening;

    }

    public static Opptjening medEgenNaeringOpptjening(boolean erNyIArbeidslivet, BigInteger næringsInntekt,
            boolean varigEndretNæring) {
        return medEgenNaeringOpptjening(LocalDate.now().minusYears(4), LocalDate.now(), erNyIArbeidslivet,
                næringsInntekt, varigEndretNæring);
    }

    public static Opptjening medEgenNaeringOpptjening(LocalDate Fom, LocalDate Tom, boolean erNyIArbeidslivet,
            BigInteger næringsInntekt, boolean varigEndretNæring) {

        var opptjening = new Opptjening();
        var naeringer = opptjening.getEgenNaering();
        var naering = new NorskOrganisasjon();

        var regnskapsfoerer = new Regnskapsfoerer();
        regnskapsfoerer.setNavn("Regnar Regnskap");
        regnskapsfoerer.setTelefon("99999999");
        naering.setRegnskapsfoerer(regnskapsfoerer);

        var periode = new Periode();
        periode.setFom(Fom);
        periode.setTom(Tom);
        naering.setPeriode(periode);

        var typer = naering.getVirksomhetstype();
        var type = new Virksomhetstyper();
        type.setKode("ANNEN");
        typer.add(type);

        naering.setOppstartsdato((LocalDate.now().minusYears(4)));
        naering.setOrganisasjonsnummer("910909088");
        naering.setNaerRelasjon(false);
        naering.setNavn("Navnet Organisasjon");
        naering.setErVarigEndring(varigEndretNæring);
        if (varigEndretNæring) {
            naering.setEndringsDato((LocalDate.now().minusWeeks(1)));
        }
        naering.setBeskrivelseAvEndring("Endringsbeskrivelse");
        naering.setNaeringsinntektBrutto(næringsInntekt);
        naering.setErNyoppstartet(false);
        naering.setErNyIArbeidslivet(erNyIArbeidslivet);

        naeringer.add(naering);
        return opptjening;

    }

    public static Opptjening medUtenlandskArbeidsforhold(String arbeidsgiverNavn, String landKode) {

        var opptjening = new Opptjening();

        var utenlandskArbeidsforholdList = opptjening.getUtenlandskArbeidsforhold();
        var utenlandskArbeidsforhold = new UtenlandskArbeidsforhold();

        var periode = new Periode();
        periode.setFom((LocalDate.now().minusYears(4)));
        periode.setTom((LocalDate.now()));
        utenlandskArbeidsforhold.setPeriode(periode);

        utenlandskArbeidsforhold.setArbeidsgiversnavn(arbeidsgiverNavn);
        var land = new Land();
        land.setKode(landKode);
        utenlandskArbeidsforhold.setArbeidsland(land);

        utenlandskArbeidsforholdList.add(utenlandskArbeidsforhold);
        return opptjening;

    }

    public static Opptjening medEgenNaeringOpptjening() {
        return medEgenNaeringOpptjening(false, BigInteger.valueOf(1_500_000), true);
    }

    public static Opptjening medEgenNaeringOgFrilansOpptjening() {

        var periode = new Periode();
        periode.setFom((LocalDate.now().minusYears(2)));
        periode.setTom((LocalDate.now()));

        var frilansoppdrag = new Frilansoppdrag();
        frilansoppdrag.setOppdragsgiver("Tims BBQ og fotmassasje");
        frilansoppdrag.setPeriode(periode);

        var frilans = new Frilans();
        frilans.setHarInntektFraFosterhjem(false);
        frilans.setErNyoppstartet(false);
        frilans.setNaerRelasjon(false);
        frilans.getPeriode().add(periode);
        frilans.getFrilansoppdrag().add(frilansoppdrag);

        var opptjening = new Opptjening();
        opptjening.setFrilans(frilans);

        var naeringer = opptjening.getEgenNaering();
        var naering = new NorskOrganisasjon();

        var regnskapsfoerer = new Regnskapsfoerer();
        regnskapsfoerer.setNavn("Regnar Regnskap");
        regnskapsfoerer.setTelefon("99999999");
        naering.setRegnskapsfoerer(regnskapsfoerer);

        var periodeSN = new Periode();
        periodeSN.setFom((LocalDate.now().minusYears(4)));
        periodeSN.setTom((LocalDate.now()));
        naering.setPeriode(periodeSN);

        var typer = naering.getVirksomhetstype();
        var type = new Virksomhetstyper();
        type.setKode("ANNEN");
        typer.add(type);

        naering.setOppstartsdato((LocalDate.now().minusYears(4)));
        naering.setOrganisasjonsnummer("910909088");
        naering.setNaerRelasjon(false);
        naering.setNavn("Navnet Organisasjon");
        naering.setErVarigEndring(true);
        naering.setBeskrivelseAvEndring("Endringsbeskrivelse");
        naering.setEndringsDato((LocalDate.now().minusWeeks(1)));
        naering.setNaeringsinntektBrutto(BigInteger.valueOf(1_500_000));
        naering.setErNyoppstartet(false);
        naering.setErNyIArbeidslivet(false);

        naeringer.add(naering);
        return opptjening;

    }

    public static Opptjening medVentelonnVartpengerOpptjening() {
        var opptjening = new Opptjening();
        var annenOpptjening = opptjening.getAnnenOpptjening();
        var ventelonn = new AnnenOpptjening();
        var type = new AnnenOpptjeningTyper();
        type.setKode("VENTELØNN_VARTPENGER");
        ventelonn.setType(type);
        var periode = new Periode();
        periode.setFom((LocalDate.now().minusYears(4)));
        periode.setTom((LocalDate.now()));
        ventelonn.setPeriode(periode);
        annenOpptjening.add(ventelonn);
        return opptjening;

    }

    public static Opptjening medMilitærOpptjening() {
        var opptjening = new Opptjening();
        var annenOpptjening = opptjening.getAnnenOpptjening();
        var militær = new AnnenOpptjening();
        var type = new AnnenOpptjeningTyper();
        type.setKode("MILITÆR_ELLER_SIVILTJENESTE");
        militær.setType(type);
        var periode = new Periode();
        periode.setFom((LocalDate.now().minusMonths(4)));
        periode.setTom((LocalDate.now().minusMonths(3)));
        militær.setPeriode(periode);
        annenOpptjening.add(militær);
        return opptjening;

    }

}
