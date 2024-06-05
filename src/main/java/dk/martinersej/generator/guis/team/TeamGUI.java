package dk.martinersej.generator.guis.team;

import dk.martinersej.generator.generator.Team;
import dk.martinersej.generator.utils.gui.BaseGui;
import org.bukkit.event.inventory.InventoryClickEvent;

public class TeamGUI extends BaseGui {

    public TeamGUI(Team team) {
        super("Team " + team.getName(), 5);

        // add items here.

        build();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        // handle clicks here.
    }
}
