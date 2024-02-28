package dk.martinersej.generator.generator.block;

import dk.martinersej.generator.generator.GeneratorItem;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class GeneratorBlockItem implements GeneratorItem<GeneratorBlock> {

    private final GeneratorType generatorType;

    public GeneratorBlockItem(GeneratorType generatorType) {
        this.generatorType = generatorType;
    }

    @Override
    public GeneratorBlock place(Location loc, UUID owner) {
        return new GeneratorBlock(loc, owner, generatorType);
    }

    @Override
    public ItemStack toItemStack() {
        return generatorType.getItemStack();
    }

    public GeneratorType getGeneratorType() {
        return this.generatorType;
    }

}
