package nl.Steffion.BlockHunt;

import me.libraryaddict.scoreboard.ScoreboardManager;
import nl.Steffion.BlockHunt.Arena.ArenaState;
import nl.Steffion.BlockHunt.Managers.MessageM;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class ScoreboardHandler {

    public static void updateScoreboard(Arena arena) {
        if ((Boolean) W.config.get(ConfigC.scoreboard_enabled) == true) {
            if (arena.gameState == ArenaState.INGAME) {
                for (Player pl : arena.playersInArena) {
                    ScoreboardManager.setDisplayName(pl, DisplaySlot.SIDEBAR,
                            BlockHunt.cutString(MessageM.replaceAll((String) W.config.get(ConfigC.scoreboard_title)), 32));
                    ScoreboardManager.makeScore(pl, DisplaySlot.SIDEBAR,
                            BlockHunt.cutString(MessageM.replaceAll((String) W.config.get(ConfigC.scoreboard_timeleft)), 32),
                            arena.timer);
                    ScoreboardManager.makeScore(pl, DisplaySlot.SIDEBAR,
                            BlockHunt.cutString(MessageM.replaceAll((String) W.config.get(ConfigC.scoreboard_seekers)), 32),
                            arena.seekers.size());
                    ScoreboardManager.makeScore(pl, DisplaySlot.SIDEBAR,
                            BlockHunt.cutString(MessageM.replaceAll((String) W.config.get(ConfigC.scoreboard_hiders)), 32),
                            arena.playersInArena.size() - arena.seekers.size());
                }
            } else {
                for (Player pl : arena.playersInArena) {
                    pl.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
                }
            }
        }
    }

    public static void removeScoreboard(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }
}
