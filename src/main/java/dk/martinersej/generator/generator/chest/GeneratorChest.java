package dk.martinersej.generator.generator.chest;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.GeneratorElement;
import dk.martinersej.generator.generator.GeneratorItem;
import dk.martinersej.generator.generator.GeneratorType;
import dk.martinersej.generator.hooks.VaultHook;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class GeneratorChest extends GeneratorElement {
    private final WeakHashMap<GeneratorType, Integer> drops = new WeakHashMap<>();
    private final GeneratorChestGUI gui;

    public GeneratorChest(Location location, UUID owner) {
        super(owner, location);
        Generator.getUserManager().getUser(owner).setGeneratorChest(this);
        gui = new GeneratorChestGUI("§aGenerator Chest" + " §7- §e" + Bukkit.getOfflinePlayer(owner).getName(), 5, this);
        gui.build();
    }

    public GeneratorChestGUI getGui() {
        return gui;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public GeneratorItem createItem() {
        return new GeneratorChestItem();
    }

    public void addDrop(GeneratorType generatorType) {
        drops.putIfAbsent(generatorType, 0);
        drops.computeIfPresent(generatorType, (k, v) -> v + generatorType.getDrop().getAmount());
    }

    public void addDrop(GeneratorType generatorType, long amount) {
        drops.putIfAbsent(generatorType, 0);
        drops.computeIfPresent(generatorType, (k, v) -> Math.toIntExact(v + amount));
    }

    public void removeDrop(GeneratorType generatorType) {
        drops.remove(generatorType);
    }

    public void openGUI(Player player) {
        gui.open(player);
    }

    private long sell(GeneratorType generatorType, long amount) {
        removeDrop(generatorType);
        double price = GeneratorType.DropPrice.valueOf(generatorType.name()).getPrice();
        return (long) (price * amount);
    }

    public void sell(UUID uuid, GeneratorType generatorType, long amount) {
        double total = sell(generatorType, amount);
        double multiplier = Generator.getUserManager().getUser(uuid).getMultiplier();
        total = total * multiplier;
        Player playerSold = Bukkit.getPlayer(uuid);
        VaultHook.addBalance(playerSold, total);
        Generator.getUserManager().getUser(uuid).addXp(GeneratorType.DropPrice.valueOf(generatorType.name()).getXp() * amount);
        Player ownerOfChest = Bukkit.getPlayer(getOwner());
        if (ownerOfChest != playerSold) {
            ownerOfChest.sendMessage("§e" + playerSold.getName() + " §asolgte for §e" + total + " §amed en multiplier på §e" + multiplier + " §a!");
        }
        playerSold.sendMessage("§aDu solgte for §e" + total + " §amed en multiplier på §e" + multiplier + " §a!");
    }

    public void sellAll(UUID uuid) {
        double sellTotal = 0;
        double xpTotal = 0;
        double multiplier = Generator.getUserManager().getUser(uuid).getMultiplier();
        Set<Map.Entry<GeneratorType, Integer>> drops = new HashSet<>(this.drops.entrySet());
        for (Map.Entry<GeneratorType, Integer> drop : drops) {
            sellTotal += sell(drop.getKey(), drop.getValue());
            xpTotal += GeneratorType.DropPrice.valueOf(drop.getKey().name()).getXp() * drop.getValue();
        }

        Player playerSold = Bukkit.getPlayer(uuid);
        Player ownerOfChest = Bukkit.getPlayer(getOwner());
        VaultHook.addBalance(playerSold, sellTotal * multiplier);
        Generator.getUserManager().getUser(uuid).addXp(xpTotal);
        if (ownerOfChest != playerSold && ownerOfChest != null) {
            ownerOfChest.sendMessage("§e" + playerSold.getName() + " §asolgte alle drops fra din generator chest!");
        }
        playerSold.sendMessage("§aDu solgte alle drops for §e" + sellTotal + " §amed en multiplier på §e" + multiplier + " §a!");
        gui.updateGui();
    }

    public WeakHashMap<GeneratorType, Integer> getDrops() {
        return drops;
    }
}
