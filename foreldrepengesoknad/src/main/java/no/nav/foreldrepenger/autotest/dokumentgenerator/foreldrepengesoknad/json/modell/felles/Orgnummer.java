package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public record Orgnummer(@JsonValue String orgnummer) {

    @Override
    public String orgnummer() {
        return orgnummer;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Orgnummer fraString(String orgnummer) {
        return new Orgnummer(orgnummer);
    }

    @Override
    public String toString() {
        return orgnummer();
    }
}
