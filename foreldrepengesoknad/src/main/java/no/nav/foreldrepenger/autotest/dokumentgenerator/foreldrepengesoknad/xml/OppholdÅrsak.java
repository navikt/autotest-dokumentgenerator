package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml;

import com.fasterxml.jackson.annotation.JsonValue;

@Deprecated
public enum OppholdÅrsak {
    MØDREKVOTE_ANNEN_FORELDER("UTTAK_MØDREKVOTE_ANNEN_FORELDER"),
    FEDREKVOTE_ANNEN_FORELDER("UTTAK_FEDREKVOTE_ANNEN_FORELDER"),
    FELLESPERIODE_ANNEN_FORELDER("UTTAK_FELLESP_ANNEN_FORELDER"),
    FORELDREPENGER_ANNEN_FORELDER("UTTAK_FORELDREPENGER_ANNEN_FORELDER"),
    INGEN("INGEN"),
    UDEFINERT("-");

    @JsonValue
    private final String kode;

    OppholdÅrsak(String kode) {
        this.kode = kode;
    }

    public String getKode() {
        return kode;
    }
}
