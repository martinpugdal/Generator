package dk.martinersej.generator.generator;

import dk.martinersej.generator.generator.block.GeneratorBlock;
import dk.martinersej.generator.generator.chest.GeneratorChest;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User {

    private final UUID uuid;
    private final Set<GeneratorBlock> generatorBlocks = Collections.synchronizedSet(new HashSet<>());
    private long generatorSlots = 25;
    private double multiplier = 1;
    private double xp = 0;
    private GeneratorChest generatorChest;

    public User(UUID uuid) {
        this.uuid = uuid;
    }

    public User(UUID uuid, double multiplier, double xp, long generatorSlots) {
        this(uuid);
        this.multiplier = multiplier;
        this.xp = xp;
        this.generatorSlots = generatorSlots;
    }

    public OfflinePlayer getPlayer() {
        return Bukkit.getOfflinePlayer(uuid);
    }

    public void addGeneratorBlock(GeneratorBlock generatorBlock) {
        this.generatorBlocks.add(generatorBlock);
    }

    public void removeGeneratorBlock(GeneratorBlock generatorBlock) {
        this.generatorBlocks.remove(generatorBlock);
    }

    public GeneratorChest getGeneratorChest() {
        return generatorChest;
    }

    public void setGeneratorChest(GeneratorChest generatorChest) {
        this.generatorChest = generatorChest;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(long multiplier) {
        this.multiplier = multiplier;
    }

    public void addMultiplier(long multiplier) {
        this.multiplier += multiplier;
    }

    public void removeMultiplier(long multiplier) {
        this.multiplier -= multiplier;
    }

    public double getXp() {
        return xp;
    }

    public void setXp(long xp) {
        this.xp = xp;
    }

    public void addXp(double xp) {
        this.xp += xp;
    }

    public void removeXp(long xp) {
        this.xp -= xp;
    }

    public UUID getUUID() {
        return uuid;
    }

    public Set<GeneratorBlock> getGenerators() {
        return generatorBlocks;
    }

    public long getGeneratorSlots() {
        return generatorSlots;
    }

    public void addGeneratorSlots(long generatorSlots) {
        this.generatorSlots += generatorSlots;
    }

    public void setGeneratorSlots(long generatorSlots) {
        this.generatorSlots = generatorSlots;
    }
}
