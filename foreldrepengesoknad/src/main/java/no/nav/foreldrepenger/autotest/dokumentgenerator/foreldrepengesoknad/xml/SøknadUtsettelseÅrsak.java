package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Deprecated
/**
 * Brukes i søknad og fakta om uttak
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
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

    private final String kode;

    SøknadUtsettelseÅrsak(String kode) {
        this.kode = kode;
    }

    @JsonCreator
    public static SøknadUtsettelseÅrsak fraKode(@JsonProperty(value = "kode") Object node) {
        if (node == null) {
            return null;
        }
        var kode = TempAvledeKode.getVerdi(SøknadUtsettelseÅrsak.class, node, "kode");
        return Arrays.stream(SøknadUtsettelseÅrsak.values())
                .filter(value -> value.getKode().equalsIgnoreCase(kode))
                .findFirst()
                .orElse(SøknadUtsettelseÅrsak.UDEFINERT);
    }

    public String getKode() {
        return kode;
    }
}
