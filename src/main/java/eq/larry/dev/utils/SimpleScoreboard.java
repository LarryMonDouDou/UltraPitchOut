package eq.larry.dev.utils;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class SimpleScoreboard {
    private Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    private String title;
    private Map<String, Integer> scores;
    private List<Team> teams;

    public SimpleScoreboard(String var1) {
        this.title = var1;
        this.scores = Maps.newLinkedHashMap();
        this.teams = Lists.newArrayList();
    }

    public void blankLine() {
        this.add("§5§l►");
    }

    public void add(String var1) {
        this.add(var1, (Integer)null);
    }

    public void add(String var1, Integer var2) {
        Preconditions.checkArgument(var1.length() < 48, "text cannot be over 48 characters in length");
        var1 = this.fixDuplicates(var1);
        this.scores.put(var1, var2);
    }

    private String fixDuplicates(String var1) {
        while(this.scores.containsKey(var1)) {
            var1 = var1 + "§r";
        }

        if (var1.length() > 48) {
            var1 = var1.substring(0, 47);
        }

        return var1;
    }

    private Entry<Team, String> createTeam(String var1) {
        String var2 = "";
        if (var1.length() <= 16) {
            return new SimpleEntry((Object)null, var1);
        } else {
            Team var3 = this.scoreboard.registerNewTeam("text-" + this.scoreboard.getTeams().size());
            Iterator var4 = Splitter.fixedLength(16).split(var1).iterator();
            var3.setPrefix((String)var4.next());
            var2 = (String)var4.next();
            if (var1.length() > 32) {
                var3.setSuffix((String)var4.next());
            }

            this.teams.add(var3);
            return new SimpleEntry(var3, var2);
        }
    }

    public void build() {
        Objective var1 = this.scoreboard.registerNewObjective(this.title.length() > 16 ? this.title.substring(0, 15) : this.title, "dummy");
        var1.setDisplayName(this.title);
        var1.setDisplaySlot(DisplaySlot.SIDEBAR);
        int var2 = this.scores.size();

        for(Iterator var4 = this.scores.entrySet().iterator(); var4.hasNext(); --var2) {
            Entry var3 = (Entry)var4.next();
            Entry var5 = this.createTeam((String)var3.getKey());
            Integer var6 = var3.getValue() != null ? (Integer)var3.getValue() : var2;
            OfflinePlayer var7 = Bukkit.getOfflinePlayer((String)var5.getValue());
            if (var5.getKey() != null) {
                ((Team)var5.getKey()).addPlayer(var7);
            }

            var1.getScore(var7).setScore(var6);
        }

    }

    public void reset() {
        this.title = null;
        this.scores.clear();
        Iterator var2 = this.teams.iterator();

        while(var2.hasNext()) {
            Team var1 = (Team)var2.next();
            var1.unregister();
        }

        this.teams.clear();
    }

    public Scoreboard getScoreboard() {
        return this.scoreboard;
    }

    public void send(Player... var1) {
        Player[] var5 = var1;
        int var4 = var1.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            Player var2 = var5[var3];
            var2.setScoreboard(this.scoreboard);
        }

    }
}