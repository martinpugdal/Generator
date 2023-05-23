package dk.martinersej.generator.generator;

import dk.martinersej.generator.generator.block.GeneratorBlock;
import dk.martinersej.generator.generator.chest.GeneratorChest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class GeneratorUser {

    private final UUID uuid;
    private double multiplier;
    private double xp;

    private final Set<GeneratorBlock> generatorBlocks;
    private GeneratorChest generatorChest;

    public GeneratorUser(UUID uuid) {
        this.uuid = uuid;
        this.xp = 0;
        this.multiplier = 1;
        this.generatorBlocks = Collections.synchronizedSet(new HashSet<>());
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

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getXp() {
        return xp;
    }

    public void setXp(double xp) {
        this.xp = xp;
    }

    public UUID getUUID() {
        return uuid;
    }

    public Set<GeneratorBlock> getGenerators() {
        return generatorBlocks;
    }
}
