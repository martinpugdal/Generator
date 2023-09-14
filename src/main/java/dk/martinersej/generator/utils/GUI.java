package dk.martinersej.generator.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.function.Supplier;

public abstract class GUI implements InventoryHolder {

    private final int rows;
    private final WeakHashMap<Integer, ItemStack> items = new WeakHashMap<>();
    private String title;
    private Inventory inventory;

    public GUI(String title, int rows) {
        this.title = title;
        this.rows = rows;
        this.inventory = Bukkit.createInventory(this, rows * 9, title);
    }

    public int getRows() {
        return rows;
    }

    public WeakHashMap<Integer, ItemStack> getItems() {
        return items;
    }

    public String getTitle() {
        return title;
    }

    public GUI setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public void setItem(int slot, ItemStack item) {
        items.put(slot, item);
    }

    public void setItem(int row, int col, ItemStack item) {
        setItem((row-1) * 9 + col, item);
    }

    public void updateItem(int slot, ItemStack item) {
        if (items.get(slot) == null) {
            items.put(slot, item);
        } else {
            items.replace(slot, item);
        }
        for (HumanEntity viewer : inventory.getViewers()) {
            viewer.getOpenInventory().setItem(slot, item);
        }
    }

    public void updateItem(int row, int col, ItemStack item) {
        updateItem((row-1) * 9 + col, item);
    }

    public void clearItems() {
        items.clear();
    }

    public void addUpdatingItem(int slot, Supplier<ItemStack> supplier) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (getInventory().getViewers().isEmpty()) {
                    cancel();
                }
                updateItem(slot, supplier.get());
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin(getClass().getPackage().getName().split("^\\w+")[0]), 0L, 1L);
    }

    public void rerender() {
        inventory.clear();
        items.forEach(inventory::setItem);
        for (HumanEntity viewer : inventory.getViewers()) {
            ((Player) viewer).updateInventory();
        }
    }

    public void rerender(boolean hardRerender) {
        if (hardRerender) {
            Inventory oldInventory = inventory;
            inventory = Bukkit.createInventory(this, rows * 9, title);
            new ArrayList<>(oldInventory.getViewers()).forEach(viewer -> viewer.openInventory(inventory));

        }
    }

    public void setRow(ItemStack item, int... row) {
        for (int i : row) {
            for (int j = 0; j < 9; j++) {
                int slot = i * 9 + j;
                setItem(slot, item);
            }
        }
    }

    public void setCol(ItemStack item, int... col) {
        for (int i : col) {
            for (int j = 0; j < rows; j++) {
                int slot = j * 9 + i;
                setItem(slot, item);
            }
        }
    }

    public abstract void onGUIClick(InventoryClickEvent event);

    public GUI build() {
        inventory.clear();
        items.forEach(inventory::setItem);
        return this;
    }

    public GUI build(boolean hardBuild) {
        if (hardBuild) {
            inventory = Bukkit.createInventory(this, rows * 9, title);
        }
        return build();
    }

    public void open(Player player) {
        player.openInventory(inventory);
    }
}