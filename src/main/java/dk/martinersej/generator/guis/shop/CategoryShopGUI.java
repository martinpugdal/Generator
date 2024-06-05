package dk.martinersej.generator.guis.shop;

import dk.martinersej.generator.hooks.VaultHook;
import dk.martinersej.generator.shop.Shop;
import dk.martinersej.generator.shop.ShopCategori;
import dk.martinersej.generator.utils.ItemBuilder;
import dk.martinersej.generator.utils.gui.PaginatedGui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class CategoryShopGUI extends PaginatedGui {

    private final ShopCategori shopCategori;
    private final ItemStack borderItem;

    public CategoryShopGUI(ShopCategori shopCategori) {
        super("Shop", 5);
        this.shopCategori = shopCategori;

        // yellow stained glass
        borderItem = new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 4).setName(" ").toItemStack();
        setBorder(borderItem);

        //setup categories in the shop
        setupCategoryShop();

        setPageItems();

        build();
    }

    private void setPageItems() {
        ItemBuilder backToHomeItem = new ItemBuilder(Material.FLOWER_POT_ITEM).setName("§aTilbage til forsiden");
        ItemBuilder backItem = new ItemBuilder(Material.ARROW).setName("§aForrige side");
        ItemBuilder nextPageItem = new ItemBuilder(Material.ARROW).setName("§aNæste side");

        setItem(5, 5, backToHomeItem.toItemStack());

        if (getPageNum() != 1) {
            backItem.setAmount(getPageNum() - 1);
            setItem(5, 4, backItem.toItemStack());
        } else {
            setItem(5, 4, borderItem);
        }

        int maxPage = getTotalPagesNum();
        if (getPageNum() < maxPage) {
            nextPageItem.setAmount(getPageNum() + 1);
            setItem(5, 6, nextPageItem.toItemStack());
        } else {
            setItem(5, 6, borderItem);
        }
    }

    private void setupCategoryShop() {
        Shop.ShopItem[] shopItems = shopCategori.getShop().getShopItems();
        for (Shop.ShopItem shopItem : shopItems) {
            ItemStack item = shopItem.getDisplayItem();
            addItem(item);
        }
    }

    private Shop.ShopItem getShopItem(int slot) {
        //TODO: fix this, if u trying to buy from next rows, because border is counted here
        return shopCategori.getShop().getShopItems()[slot + (getPageNum() - 1) * getPageSize()];
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);
        if (event.getCurrentItem().getType().equals(Material.ARROW)) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aNæste side")) {
                if (nextPage()) {
                    rerender();
                    setPageItems();
                }
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aForrige side")) {
                if (previousPage()) {
                    rerender();
                    setPageItems();
                }
            }
        } else if (event.getCurrentItem().getType().equals(Material.FLOWER_POT_ITEM)) {
            new ShopGUI().open((Player) event.getWhoClicked());
        } else if (!event.getCurrentItem().getType().equals(Material.STAINED_GLASS_PANE)) {
            int startSlot = 10;
            Shop.ShopItem shopItem = getShopItem(event.getSlot() - startSlot);

            Player player = (Player) event.getWhoClicked();
            // buy
            if (event.isRightClick()) {
                if (player.getInventory().firstEmpty() == -1) {
                    player.sendMessage("Your inventory is full");
                } else if (VaultHook.getEconomy().getBalance(player) < shopItem.getBuyPrice()) {
                    player.sendMessage("You don't have enough money");
                } else {
                    VaultHook.getEconomy().withdrawPlayer(player, shopItem.getBuyPrice());
                    player.getInventory().addItem(shopItem.getSaleItem());
                    player.sendMessage("You bought " + shopItem.getName() + " for " + shopItem.getBuyPrice());
                }
            } else if (event.isLeftClick()) {
                // sell
                if (player.getInventory().containsAtLeast(shopItem.getItemStack(), shopItem.getAmount())) {
                    player.getInventory().removeItem(shopItem.getItemStack());
                    VaultHook.getEconomy().depositPlayer(player, shopItem.getSellPrice());
                    player.sendMessage("You sold " + shopItem.getName() + " for " + shopItem.getSellPrice());
                } else {
                    player.sendMessage("You don't have enough items to sell");
                }
            }
        }
    }
}
