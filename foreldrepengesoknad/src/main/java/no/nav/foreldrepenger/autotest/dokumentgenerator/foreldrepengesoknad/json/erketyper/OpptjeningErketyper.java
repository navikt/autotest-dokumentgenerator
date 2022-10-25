package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper;

import java.time.LocalDate;
import java.util.List;

import com.neovisionaries.i18n.CountryCode;

import no.nav.foreldrepenger.common.domain.Orgnummer;
import no.nav.foreldrepenger.common.domain.felles.ProsentAndel;
import no.nav.foreldrepenger.common.domain.felles.opptjening.Frilans;
import no.nav.foreldrepenger.common.domain.felles.opptjening.FrilansOppdrag;
import no.nav.foreldrepenger.common.domain.felles.opptjening.NorskOrganisasjon;
import no.nav.foreldrepenger.common.domain.felles.opptjening.Opptjening;
import no.nav.foreldrepenger.common.domain.felles.opptjening.Regnskapsfører;
import no.nav.foreldrepenger.common.domain.felles.opptjening.UtenlandskArbeidsforhold;
import no.nav.foreldrepenger.common.domain.felles.opptjening.Virksomhetstype;
import no.nav.foreldrepenger.common.domain.felles.ÅpenPeriode;

public final class OpptjeningErketyper {

    private OpptjeningErketyper() {
    }

    public static Opptjening frilansOpptjening() {
        return frilansOpptjening(LocalDate.now().minusYears(2), LocalDate.now());
    }

    public static Opptjening frilansOpptjening(LocalDate fom, LocalDate tom) {
        var frilans = lagFrilans(fom, tom);
        return Opptjening.builder()
                .frilans(frilans)
                .build();
    }

    public static Opptjening egenNaeringOpptjening(Boolean erNyIArbeidslivet, double næringsInntekt,
                                                   Boolean varigEndretNæring) {
        return egenNaeringOpptjening(LocalDate.now().minusYears(4), LocalDate.now(), erNyIArbeidslivet,
                næringsInntekt, varigEndretNæring);
    }

    public static Opptjening egenNaeringOpptjening(LocalDate fom, LocalDate tom, Boolean erNyIArbeidslivet,
                                                   Number næringsInntekt, Boolean varigEndretNæring) {
        var norskOrganisasjon = lagNorskOrganisasjon(fom, tom, erNyIArbeidslivet, næringsInntekt, varigEndretNæring);
        return Opptjening.builder()
                .egenNæring(List.of(norskOrganisasjon))
                .build();
    }

    public static Opptjening egenNaeringOgFrilansOpptjening() {
        return Opptjening.builder()
                .egenNæring(List.of(
                        lagNorskOrganisasjon(LocalDate.now().minusYears(4), LocalDate.now(),
                                false, 1_500_000, true)))
                .frilans(lagFrilans(LocalDate.now().minusYears(2), LocalDate.now()))
                .build();
    }

    public static Opptjening utenlandskArbeidsforhold(CountryCode landKode) {
        var utenlandskArbeidsforhold = lagUtenlandskArbeidsforhold(landKode);
        return Opptjening.builder()
                .utenlandskArbeidsforhold(List.of(utenlandskArbeidsforhold))
                .build();
    }


    /* PRIVATE HJELPEMETODER */
    private static Frilans lagFrilans(LocalDate fom, LocalDate tom) {
        var periode = new ÅpenPeriode(fom, tom);
        var frilansOppdrag = new FrilansOppdrag("Thai take away", periode);
        return new Frilans(periode,
                false,
                false,
                false,
                List.of(frilansOppdrag));
    }

    private static NorskOrganisasjon lagNorskOrganisasjon(LocalDate fom, LocalDate tom, Boolean erNyIArbeidslivet, Number næringsInntekt, Boolean varigEndretNæring) {
        return NorskOrganisasjon.builder()
                .virksomhetsTyper(List.of(Virksomhetstype.ANNEN))
                .periode(new ÅpenPeriode(fom, tom))
                .nærRelasjon(false)
                .regnskapsførere(List.of(new Regnskapsfører("Regnar Regnskap", "99999999")))
                .erNyOpprettet(false)
                .erVarigEndring(varigEndretNæring)
                .erNyIArbeidslivet(erNyIArbeidslivet)
                .næringsinntektBrutto(næringsInntekt.longValue())
                .endringsDato(varigEndretNæring.equals(Boolean.TRUE) ? LocalDate.now().minusWeeks(1) : null)
                .oppstartsDato(LocalDate.now().minusYears(4))
                .beskrivelseEndring("Endringsbeskrivelse")
                .stillingsprosent(new ProsentAndel(100.0))
                .orgName("Navnet Organisasjon")
                .orgNummer(new Orgnummer("910909088"))
                .build();
    }

    private static UtenlandskArbeidsforhold lagUtenlandskArbeidsforhold(CountryCode landKode) {
        return UtenlandskArbeidsforhold.builder()
                .periode(new ÅpenPeriode(LocalDate.now().minusYears(4), LocalDate.now()))
                .arbeidsgiverNavn("Utenlandsk selskap AS")
                .land(landKode)
                .build();
    }

}
