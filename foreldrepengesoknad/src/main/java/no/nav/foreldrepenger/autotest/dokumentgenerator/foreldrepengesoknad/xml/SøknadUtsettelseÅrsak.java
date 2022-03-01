package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml;

import com.fasterxml.jackson.annotation.JsonValue;

@Deprecated
/**
 * Brukes i søknad og fakta om uttak
 */
public enum SøknadUtsettelseÅrsak {
    ARBEID("ARBEID"),
    FERIE("LOVBESTEMT_FERIE"),
    SYKDOM("SYKDOM"),
    INSTITUSJON_SØKER("INSTITUSJONSOPPHOLD_SØKER"),
    INSTITUSJON_BARN("INSTITUSJONSOPPHOLD_BARNET"),
    HV_OVELSE("HV_OVELSE"),
    NAV_TILTAK("NAV_TILTAK"),
    FRI("FRI"),
    UDEFINERT("-"),
    ;

    @JsonValue
    private final String kode;

    SøknadUtsettelseÅrsak(String kode) {
        this.kode = kode;
    }


    public String getKode() {
        return kode;
    }
}
