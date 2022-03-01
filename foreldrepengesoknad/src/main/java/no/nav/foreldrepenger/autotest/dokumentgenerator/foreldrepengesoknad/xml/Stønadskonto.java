package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonValue;

@Deprecated
public enum Stønadskonto {

    FELLESPERIODE,
    MØDREKVOTE,
    FEDREKVOTE,
    FORELDREPENGER,
    FLERBARNSDAGER,
    FORELDREPENGER_FØR_FØDSEL,
    INGEN_STØNADSKONTO("-"),
    ;

    @JsonValue
    private final String kode;

    Stønadskonto() {
        this(null);
    }

    Stønadskonto(String kode) {
        this.kode = Optional.ofNullable(kode).orElse(name());
    }

    public String getKode() {
        return kode;
    }
}
