package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper;

import com.neovisionaries.i18n.CountryCode;
import no.nav.foreldrepenger.common.domain.Orgnummer;
import no.nav.foreldrepenger.common.domain.felles.ProsentAndel;
import no.nav.foreldrepenger.common.domain.felles.opptjening.*;
import no.nav.foreldrepenger.common.domain.felles.ÅpenPeriode;

import java.time.LocalDate;
import java.util.List;

public final class OpptjeningErketyper {

    private OpptjeningErketyper() {
    }

    public static Opptjening frilansOpptjening() {
        return frilansOpptjening(LocalDate.now().minusYears(2), LocalDate.now());
    }

    public static Opptjening frilansOpptjening(LocalDate fom, LocalDate tom) {
        var frilans = lagFrilans(fom, tom);
        return new Opptjening(null, null, null, frilans);
    }

    public static Opptjening egenNaeringOpptjening(Boolean erNyIArbeidslivet, double næringsInntekt, Boolean varigEndretNæring) {
        return egenNaeringOpptjening(LocalDate.now().minusYears(4), LocalDate.now(), erNyIArbeidslivet,
                næringsInntekt, varigEndretNæring);
    }

    public static Opptjening egenNaeringOpptjening(LocalDate fom, LocalDate tom, Boolean erNyIArbeidslivet,
                                                   Number næringsInntekt, Boolean varigEndretNæring) {
        var norskOrganisasjon = lagNorskOrganisasjon(fom, tom, erNyIArbeidslivet, næringsInntekt, varigEndretNæring);
        return new Opptjening(null, List.of(norskOrganisasjon), null, null);
    }

    public static Opptjening egenNaeringOgFrilansOpptjening() {
        return new Opptjening(
                null,
                List.of(
                        lagNorskOrganisasjon(LocalDate.now().minusYears(4), LocalDate.now(), false, 1_500_000, true)
                ),
                null,
                lagFrilans(LocalDate.now().minusYears(2), LocalDate.now())
        );
    }

    public static Opptjening utenlandskArbeidsforhold(CountryCode landKode) {
        var utenlandskArbeidsforhold = lagUtenlandskArbeidsforhold(landKode);
        return new Opptjening(List.of(utenlandskArbeidsforhold), null, null, null);
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

    private static EgenNæring lagNorskOrganisasjon(LocalDate fom, LocalDate tom, Boolean erNyIArbeidslivet, Number næringsInntekt, Boolean varigEndretNæring) {
        return new EgenNæring(
                CountryCode.NO,
                new Orgnummer("910909088"),
                "Navnet Organisasjon",
                List.of(Virksomhetstype.ANNEN),
                new ÅpenPeriode(fom, tom),
                false,
                List.of(new Regnskapsfører("Regnar Regnskap", "99999999")),
                false,
                varigEndretNæring,
                erNyIArbeidslivet,
                næringsInntekt.longValue(),
                varigEndretNæring.equals(Boolean.TRUE) ? LocalDate.now().minusWeeks(1) : null,
                LocalDate.now().minusYears(4),
                "Endringsbeskrivelse",
                new ProsentAndel(100.0),
                List.of()
        );
    }

    private static UtenlandskArbeidsforhold lagUtenlandskArbeidsforhold(CountryCode landKode) {
        return new UtenlandskArbeidsforhold(
                "Utenlandsk selskap AS",
                new ÅpenPeriode(LocalDate.now().minusYears(4), LocalDate.now()),
                List.of(),
                landKode
        );
    }

}
