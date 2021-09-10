package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.erketyper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import no.nav.vedtak.felles.xml.soeknad.felles.v3.Vedlegg;
import no.nav.vedtak.felles.xml.soeknad.kodeverk.v3.Innsendingstype;
import no.nav.vedtak.felles.xml.soeknad.svangerskapspenger.v1.Arbeidsforhold;
import no.nav.vedtak.felles.xml.soeknad.svangerskapspenger.v1.DelvisTilrettelegging;
import no.nav.vedtak.felles.xml.soeknad.svangerskapspenger.v1.HelTilrettelegging;
import no.nav.vedtak.felles.xml.soeknad.svangerskapspenger.v1.IngenTilrettelegging;
import no.nav.vedtak.felles.xml.soeknad.svangerskapspenger.v1.ObjectFactory;
import no.nav.vedtak.felles.xml.soeknad.svangerskapspenger.v1.Tilrettelegging;

public class TilretteleggingsErketyper {

    public static final String LASTET_OPP = "LASTET_OPP";
    public static final String INNSENDINGSVALG = "INNSENDINGSVALG";

    private TilretteleggingsErketyper() {
        // Skal ikke instansieres
    }

    public static Tilrettelegging helTilrettelegging(LocalDate behovForTilretteleggingFom,
                                                     LocalDate tilrettelagtArbeidFom,
                                                     Arbeidsforhold arbeidsforhold) {
        var tilrettelegging = new Tilrettelegging();
        tilrettelegging.setBehovForTilretteleggingFom(behovForTilretteleggingFom);
        tilrettelegging.setArbeidsforhold(arbeidsforhold);

        var of = new ObjectFactory();
        var innsendingstype = new Innsendingstype();
        innsendingstype.setKode(LASTET_OPP);
        innsendingstype.setKodeverk(INNSENDINGSVALG);
        var vedlegg = new Vedlegg();
        vedlegg.setId("A" + UUID.randomUUID().toString().substring(0, 6));
        vedlegg.setInnsendingstype(innsendingstype);

        var helTilrettelegging = new HelTilrettelegging();
        helTilrettelegging.setTilrettelagtArbeidFom(tilrettelagtArbeidFom);
        tilrettelegging.getHelTilrettelegging().add(helTilrettelegging);
        tilrettelegging.getVedlegg().add(of.createTilretteleggingVedlegg(vedlegg));
        return tilrettelegging;
    }

    public static Tilrettelegging delvisTilrettelegging(LocalDate behovForTilretteleggingFom,
                                                        LocalDate tilrettelagtArbeidFom,
                                                        Arbeidsforhold arbeidsforhold, BigDecimal stillingsprosent) {
        var tilrettelegging = new Tilrettelegging();
        tilrettelegging.setBehovForTilretteleggingFom(behovForTilretteleggingFom);
        tilrettelegging.setArbeidsforhold(arbeidsforhold);

        var of = new ObjectFactory();
        var innsendingstype = new Innsendingstype();
        innsendingstype.setKode(LASTET_OPP);
        innsendingstype.setKodeverk(INNSENDINGSVALG);
        var vedlegg = new Vedlegg();
        vedlegg.setId("B" + UUID.randomUUID().toString().substring(0, 6));
        vedlegg.setInnsendingstype(innsendingstype);

        var delvisTilrettelegging = new DelvisTilrettelegging();
        delvisTilrettelegging.setTilrettelagtArbeidFom(tilrettelagtArbeidFom);
        delvisTilrettelegging.setStillingsprosent(stillingsprosent);
        tilrettelegging.getDelvisTilrettelegging().add(delvisTilrettelegging);
        tilrettelegging.getVedlegg().add(of.createTilretteleggingVedlegg(vedlegg));
        return tilrettelegging;
    }

    public static Tilrettelegging ingenTilrettelegging(LocalDate behovForTilretteleggingFom,
                                                       LocalDate tilrettelagtArbeidFom,
                                                       Arbeidsforhold arbeidsforhold) {
        var tilrettelegging = new Tilrettelegging();
        tilrettelegging.setBehovForTilretteleggingFom(behovForTilretteleggingFom);
        tilrettelegging.setArbeidsforhold(arbeidsforhold);

        var of = new ObjectFactory();
        var innsendingstype = new Innsendingstype();
        innsendingstype.setKode(LASTET_OPP);
        innsendingstype.setKodeverk(INNSENDINGSVALG);
        var vedlegg = new Vedlegg();
        vedlegg.setId("C" + UUID.randomUUID().toString().substring(0, 6));
        vedlegg.setInnsendingstype(innsendingstype);

        var ingenTilrettelegging = new IngenTilrettelegging();
        ingenTilrettelegging.setSlutteArbeidFom(tilrettelagtArbeidFom);
        tilrettelegging.getIngenTilrettelegging().add(ingenTilrettelegging);
        tilrettelegging.getVedlegg().add(of.createTilretteleggingVedlegg(vedlegg));
        return tilrettelegging;
    }

}