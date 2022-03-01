package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

@Deprecated
public enum Stønadskonto {

    FELLESPERIODE,
    MØDREKVOTE,
    FEDREKVOTE,
    FORELDREPENGER,
    FLERBARNSDAGER,
    FORELDREPENGER_FØR_FØDSEL,
    @JsonEnumDefaultValue
    INGEN_STØNADSKONTO,
    ;

}
