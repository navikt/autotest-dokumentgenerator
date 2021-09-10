package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.foreldrepenger.fordeling;

public enum UtsettelsesÅrsak {
    ARBEID,
    LOVBESTEMT_FERIE,
    SYKDOM,
    INSTITUSJONSOPPHOLD_SØKER,
    INSTITUSJONSOPPHOLD_BARNET,
    HV_OVELSE("periode.utsettelse.hv"),
    NAV_TILTAK("periode.utsettelse.nav"),
    FRI;

    private final String key;

    UtsettelsesÅrsak() {
        this(null);
    }

    UtsettelsesÅrsak(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
