package pl.coderslab.entity;

public enum InstitutionWhomHelpEnum {

    KIDS("dzieciom"),
    MOTHERS("samotnym matkom"),
    HOMELESS("bezdomnym"),
    INVALID("niepełnosprawnym"),
    OLD("osobom starszym");

    private String whomHelp;

    InstitutionWhomHelpEnum(String whom){
        this.whomHelp = whom;
    }

    public String getWhomHelp() {
        return whomHelp;
    }



}
