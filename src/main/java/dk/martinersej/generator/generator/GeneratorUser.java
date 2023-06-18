package dk.martinersej.generator.generator;

import dk.martinersej.generator.generator.block.GeneratorBlock;
import dk.martinersej.generator.generator.chest.GeneratorChest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class GeneratorUser {

    private final UUID uuid;
    private final Set<GeneratorBlock> generatorBlocks = Collections.synchronizedSet(new HashSet<>());
    private long generatorSlots = 25;
    private long multiplier = 1;
    private long xp = 0;
    private GeneratorChest generatorChest;

    public GeneratorUser(UUID uuid) {
        this.uuid = uuid;
    }

    public GeneratorUser(UUID uuid, long multiplier, long xp, long generatorSlots) {
        this(uuid);
        this.multiplier = multiplier;
        this.xp = xp;
        this.generatorSlots = generatorSlots;
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

    public long getMultiplier() {
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

    public long getXp() {
        return xp;
    }

    public void setXp(long xp) {
        this.xp = xp;
    }

    public void addXp(long xp) {
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

    public void setGeneratorSlots(long generatorSlots) {
        this.generatorSlots = generatorSlots;
    }
}
