package Cis.api.domain.enums;

public enum HorarioAgendamento {

    H0800, // 08h00
    H0900,
    H1000,
    H1100,
    H1200,
    H1300,
    H1400,
    H1500,
    H1600,
    H1700,
    H1800,
    H1900,
    H2000;

    public String getDisplayValue() {
        return this.name().replace("H", "") + "h00";
    }
}
