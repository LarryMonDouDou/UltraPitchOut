package eq.larry.dev.Enums;

import eq.larry.dev.UltraCore;

public enum EnumsStatus {
    Waiting("Waiting", ""),
    Progress("Progress", ""),
    Finish("Finish", "");

    private String motd = " ";

    private EnumsStatus(String var3, String var4) {
        this.motd = UltraCore.getInstance().getConfig().getString("Motd." + var3).replaceAll("&([0-9a-fk-o,r])", "§$1");
    }

    public String getMotd() {
        return this.motd;
    }
}
