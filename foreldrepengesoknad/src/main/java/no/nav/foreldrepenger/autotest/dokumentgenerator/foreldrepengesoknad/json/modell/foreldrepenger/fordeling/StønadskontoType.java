package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.foreldrepenger.fordeling;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum StønadskontoType {
    @JsonProperty("-")
    IKKE_SATT, FELLESPERIODE, MØDREKVOTE, FEDREKVOTE, FORELDREPENGER, FORELDREPENGER_FØR_FØDSEL
}
