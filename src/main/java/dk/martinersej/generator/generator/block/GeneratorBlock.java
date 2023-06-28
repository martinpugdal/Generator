package dk.martinersej.generator.generator.block;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.GeneratorElement;
import dk.martinersej.generator.generator.GeneratorItem;
import dk.martinersej.generator.generator.GeneratorType;
import dk.martinersej.generator.generator.chest.GeneratorChest;
import dk.martinersej.generator.utils.ParticleUtils;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GeneratorBlock extends GeneratorElement {
    private final Location offSetLocation;
    private GeneratorType generatorType;

    public GeneratorBlock(Location location, UUID owner, GeneratorType generatorType) {
        super(owner, location);
        this.offSetLocation = location.clone().add(0, 1, 0);
        this.generatorType = generatorType;
        Generator.getUserManager().getUser(owner).addGeneratorBlock(this);
    }

    public GeneratorType getGeneratorType() {
        return generatorType;
    }

    public void setGeneratorType(GeneratorType generatorType) {
        this.generatorType = generatorType;
    }

    public void drop() {
        GeneratorChest generatorChest = Generator.getUserManager().getUser(getOwner()).getGeneratorChest();
        if (generatorChest != null) {
            generatorChest.addDrop(generatorType);
            if (MinecraftServer.getServer().recentTps[0] > 19) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getLocation().distance(getLocation()) < 20) {
                        ParticleUtils.drawLine(getLocation(), generatorChest.getLocation(), player, 0.5, generatorType.getColor());
                    }
                }
            }
        } else {
            getLocation().getWorld().dropItemNaturally(offSetLocation, generatorType.getDrop());
        }
    }

    public boolean isUpgradeable() {
        return GeneratorType.getGeneratorType(generatorType.getTier() + 1) != null;
    }

    public GeneratorItem createItem() {
        return new GeneratorBlockItem(generatorType);
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(getOwner());
    }

    public void upgrade() {
        if (isUpgradeable()) {
            generatorType = GeneratorType.getGeneratorType(generatorType.getTier() + 1);
            Block block = getLocation().getBlock();
            block.setType(generatorType.getItemStack().getType());
            block.setData(generatorType.getItemStack().getData().getData());
            Generator.getGeneratorManager().updateElement(this);
        }
    }
}
