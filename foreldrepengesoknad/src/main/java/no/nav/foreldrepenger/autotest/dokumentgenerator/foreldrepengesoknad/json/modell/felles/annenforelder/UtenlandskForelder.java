package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.annenforelder;


import com.neovisionaries.i18n.CountryCode;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public final class UtenlandskForelder extends AnnenForelder {

    private final String id;
    private final CountryCode land;
    private final String navn;

    @Override
    public boolean hasId() {
        return id != null;
    }
}
