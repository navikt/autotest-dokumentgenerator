package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.builders;

import java.time.LocalDate;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.SøkersRolle;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.util.JaxbHelper;
import no.nav.foreldrepenger.søknad.v3.SøknadConstants;
import no.nav.vedtak.felles.xml.soeknad.felles.v3.AnnenForelder;
import no.nav.vedtak.felles.xml.soeknad.felles.v3.AnnenForelderMedNorskIdent;
import no.nav.vedtak.felles.xml.soeknad.felles.v3.Bruker;
import no.nav.vedtak.felles.xml.soeknad.felles.v3.Vedlegg;
import no.nav.vedtak.felles.xml.soeknad.felles.v3.Ytelse;
import no.nav.vedtak.felles.xml.soeknad.kodeverk.v3.Brukerroller;
import no.nav.vedtak.felles.xml.soeknad.v3.ObjectFactory;
import no.nav.vedtak.felles.xml.soeknad.v3.OmYtelse;
import no.nav.vedtak.felles.xml.soeknad.v3.Soeknad;

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
    }

    public B medMottattDato(LocalDate mottattDato) {
        søknadKladd.setMottattDato(mottattDato);
        return self();
    }

    public B medBegrunnelseForSenSoeknad(String begrunnelseForSenSoeknad){
        søknadKladd.setBegrunnelseForSenSoeknad(begrunnelseForSenSoeknad);
        return self();
    }

    public B medTilleggsopplysninger(String tilleggsopplysninger){
        søknadKladd.setTilleggsopplysninger(tilleggsopplysninger);
        return self();
    }

    public B medAndreVedlegg(List<Vedlegg> andreVedlegg){
        if(andreVedlegg != null){
            andreVedlegg.forEach(av -> søknadKladd.getAndreVedlegg().add(av));
        }
        return self();
    }

    public B medPåkrevdeVedlegg(List<Vedlegg> påkrevdeVedlegg) {
        if(påkrevdeVedlegg != null) {
            påkrevdeVedlegg.forEach(pv -> søknadKladd.getPaakrevdeVedlegg().add(pv));
        }
        return self();
    }



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
        var omYtelseKladd = new OmYtelse();
        omYtelseKladd.getAny().add(omYtelseJAXBElement);
        return omYtelseKladd;
    }

    protected Bruker soekerAvType(String aktoerId, SøkersRolle søkersRolle) {
        var bruker = new Bruker();
        bruker.setAktoerId(aktoerId);
        var brukerroller = new Brukerroller();
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
        var forelder = new AnnenForelderMedNorskIdent();
        forelder.setAktoerId(aktørId);
        return forelder;
    }


    Soeknad build() {
        if(søknadKladd.getTilleggsopplysninger() == null){
            søknadKladd.setTilleggsopplysninger("");
        }
        if(søknadKladd.getMottattDato() == null){
            søknadKladd.setMottattDato(LocalDate.now());
        }
        return søknadKladd;
    }
}
