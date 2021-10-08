package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml;

import java.util.Arrays;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@Deprecated
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Stønadskonto {

    FELLESPERIODE,
    MØDREKVOTE,
    FEDREKVOTE,
    FORELDREPENGER,
    FLERBARNSDAGER,
    FORELDREPENGER_FØR_FØDSEL,
    INGEN_STØNADSKONTO("-"),
    ;

    private final String kode;

    Stønadskonto() {
        this(null);
    }

    Stønadskonto(String kode) {
        this.kode = Optional.ofNullable(kode).orElse(name());
    }

    @JsonCreator
    public static Stønadskonto fraKode(String kode) {
        return Arrays.stream(Stønadskonto.values())
                .filter(value -> value.getKode().equalsIgnoreCase(kode))
                .findFirst()
                .orElse(Stønadskonto.INGEN_STØNADSKONTO);
    }

    public String getKode() {
        return kode;
    }
}
