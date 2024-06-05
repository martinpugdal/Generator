package dk.martinersej.generator.shop;

import dk.martinersej.generator.generator.block.GeneratorType;
import dk.martinersej.generator.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ShopGenerators implements Shop {

    private final ShopItem[] shopItems;

    ShopGenerators() {
        List<GeneratorType> generatorTypes = Arrays.asList(GeneratorType.values());
        generatorTypes.removeIf(generatorType -> !generatorType.isUpgradeAble());
        shopItems = new ShopItem[generatorTypes.size()];

        for (int i = 0; i < shopItems.length; i++) {
            GeneratorType generatorType = generatorTypes.get(i);
            shopItems[i] = new ShopItem() {
                @Override
                public String getName() {
                    return generatorType.getName();
                }

                @Override
                public double getBuyPrice() {
                    return generatorType.getUpgradePrice();
                }

                @Override
                public ItemStack getItemStack() {
                    return generatorType.getItemStack();
                }

                @Override
                public double getSellPrice() {
                    return generatorType.getUpgradePrice() * 0.25;
                }

                @Override
                public int getAmount() {
                    return 1;
                }
            };
        }
    }

    @Override
    public ItemStack getCategoryItem() {
        return new ItemBuilder(Material.STAINED_GLASS).setName("Generator").toItemStack();
    }

    @Override
    public ShopItem[] getShopItems() {
        return shopItems;
    }
}
