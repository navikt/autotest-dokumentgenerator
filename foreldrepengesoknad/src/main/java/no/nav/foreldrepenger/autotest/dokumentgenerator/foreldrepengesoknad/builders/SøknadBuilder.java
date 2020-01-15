package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.builders;

import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.SøkersRolle;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.util.JaxbHelper;
import no.nav.foreldrepenger.søknad.v3.SøknadConstants;
import no.nav.vedtak.felles.xml.soeknad.endringssoeknad.v3.Endringssoeknad;
import no.nav.vedtak.felles.xml.soeknad.engangsstoenad.v3.Engangsstønad;
import no.nav.vedtak.felles.xml.soeknad.felles.v3.*;
import no.nav.vedtak.felles.xml.soeknad.foreldrepenger.v3.Foreldrepenger;
import no.nav.vedtak.felles.xml.soeknad.kodeverk.v3.Brukerroller;
import no.nav.vedtak.felles.xml.soeknad.svangerskapspenger.v1.Svangerskapspenger;
import no.nav.vedtak.felles.xml.soeknad.v3.ObjectFactory;
import no.nav.vedtak.felles.xml.soeknad.v3.OmYtelse;
import no.nav.vedtak.felles.xml.soeknad.v3.Soeknad;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import java.time.LocalDate;
import java.util.List;

public abstract class SøknadBuilder<B extends SøknadBuilder<B>> {

    protected Soeknad søknadKladd;

    SøknadBuilder() {
        søknadKladd = new Soeknad();
    }
    protected abstract B self();
    protected abstract SøknadBuilder medYtelse(Ytelse ytelse);

    public B medSøker(String aktoerId, SøkersRolle søkerRolle) {
        søknadKladd.setSoeker(soekerAvType(aktoerId, søkerRolle));
        return self();
    };
    public B medMottattDato(LocalDate mottattDato) {
        søknadKladd.setMottattDato(mottattDato);
        return self();
    };
    public B medBegrunnelseForSenSoeknad(String begrunnelseForSenSoeknad){
        søknadKladd.setBegrunnelseForSenSoeknad(begrunnelseForSenSoeknad);
        return self();
    };
    public B medTilleggsopplysninger(String tilleggsopplysninger){
        søknadKladd.setTilleggsopplysninger(tilleggsopplysninger);
        return self();
    };
    public B medAndreVedlegg(List<Vedlegg> andreVedlegg){
        if(andreVedlegg != null){
            andreVedlegg.forEach(av -> søknadKladd.getAndreVedlegg().add(av));
        }
        return self();
    };
    public B medPåkrevdeVedlegg(List<Vedlegg> påkrevdeVedlegg) {
        if(påkrevdeVedlegg != null) {
            påkrevdeVedlegg.forEach(pv -> søknadKladd.getPaakrevdeVedlegg().add(pv));
        }
        return self();
    };



    /**
     * Konverterer {@link Soeknad} til XML, eller kaster en {@link RuntimeException} ved feil
     */
    public static String tilXML(Soeknad soeknad) {
        String xml = null;
        try {
            JAXBElement<Soeknad> soeknadsskjemaForeldrepengerJAXBElement = (new ObjectFactory().createSoeknad(soeknad));
            xml = JaxbHelper.marshalAndValidateJaxb(SøknadConstants.JAXB_CLASS,
                    soeknadsskjemaForeldrepengerJAXBElement,
                    SøknadConstants.XSD_LOCATION,
                    SøknadConstants.ADDITIONAL_XSD_LOCATION,
                    SøknadConstants.ADDITIONAL_CLASSES);
        } catch (JAXBException | SAXException e) {
            e.printStackTrace();
        }
        return xml;
    }

    protected OmYtelse setOmYtelseJAXBElement(JAXBElement<? extends Ytelse> omYtelseJAXBElement) {
        OmYtelse omYtelseKladd = new OmYtelse();
        omYtelseKladd.getAny().add(omYtelseJAXBElement);
        return omYtelseKladd;
    }

    protected Bruker soekerAvType(String aktoerId, SøkersRolle søkersRolle) {
        Bruker bruker = new Bruker();
        bruker.setAktoerId(aktoerId);
        Brukerroller brukerroller = new Brukerroller();
        if (søkersRolle ==  SøkersRolle.MOR) {
            brukerroller.setKode("MOR");
        }
        else if (søkersRolle ==  SøkersRolle.FAR) {
            brukerroller.setKode("FAR");
        }
        else if (søkersRolle == SøkersRolle.MEDMOR) {
            brukerroller.setKode("MEDMOR");
        }
        else {
            brukerroller.setKode("ANDRE");
        }
        brukerroller.setKodeverk("FORELDRE_TYPE");
        bruker.setSoeknadsrolle(brukerroller);
        return bruker;
    }
    protected AnnenForelder standardAnnenForelder(String aktørId) {
        AnnenForelderMedNorskIdent forelder = new AnnenForelderMedNorskIdent();
        forelder.setAktoerId(aktørId);
        return forelder;
    }


    Soeknad build() {
        if(søknadKladd.getBegrunnelseForSenSoeknad() == null){
            søknadKladd.setBegrunnelseForSenSoeknad((String) null);
        }
        if(søknadKladd.getTilleggsopplysninger() == null){
            søknadKladd.setTilleggsopplysninger("");
        }
        if(søknadKladd.getMottattDato() == null){
            søknadKladd.setMottattDato(LocalDate.now());
        }
        return søknadKladd;
    }
}
