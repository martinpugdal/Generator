package dk.martinersej.generator.utils.gui;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public abstract class PaginatedGui extends BaseGui {

    private final List<ItemStack> pageItems = new ArrayList<>();
    private final Map<Integer, ItemStack> currentPage;

    private int pageSize;
    private int pageNum = 1;

    public PaginatedGui(final String title, final int rows, final int pageSize) {
        super(title, rows);
        this.pageSize = pageSize;
        int inventorySize = rows * 9;
        this.currentPage = new LinkedHashMap<>(inventorySize);
    }

    public PaginatedGui(final String title, final int rows) {
        this(title, rows, 0);
    }

    public int getPageSize() {
        return pageSize;
    }

    public PaginatedGui setPageSize(final int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public void addItem(final ItemStack itemStack) {
        pageItems.add(itemStack);
    }

    public void removeItem(final ItemStack itemStack) {
        pageItems.remove(itemStack);
    }

    public void removeItem(final int index) {
        pageItems.remove(index);
    }

    ItemStack getPageItem(final int slot) {
        return currentPage.get(slot);
    }

    public void addItem(final ItemStack itemStack, boolean clone) {
        if (clone) {
            addItem(itemStack.clone());
        } else {
            addItem(itemStack);
        }
    }

    public Map<Integer, ItemStack> getCurrentPageItems() {
        return Collections.unmodifiableMap(currentPage);
    }

    public List<ItemStack> getPageItems() {
        return pageItems;
    }

    private List<ItemStack> getPageItems(final int givenPage) {
        final int page = givenPage - 1;

        final List<ItemStack> guiItems = new ArrayList<>();

        int max = ((page * pageSize) + pageSize);
        if (max > pageItems.size()) max = pageItems.size();

        for (int i = page * pageSize; i < max; i++) {
            guiItems.add(pageItems.get(i));
        }

        return guiItems;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getTotalPagesNum() {
        return (int) Math.ceil((double) pageItems.size() / pageSize);
    }

    public int getNextPageNum() {
        if (pageNum + 1 > getTotalPagesNum()) return pageNum;
        return pageNum + 1;
    }

    public int getPreviousPageNum() {
        if (pageNum - 1 == 0) return pageNum;
        return pageNum - 1;
    }

    public boolean nextPage() {
        if (pageNum + 1 > getTotalPagesNum()) return false;
        pageNum++;
        updatePage();
        return true;
    }

    public boolean previousPage() {
        if (pageNum - 1 < 1) return false;
        pageNum--;
        updatePage();
        return true;
    }

    private void populatePage() {
        for (final ItemStack itemStack : getPageItems(pageNum)) {
            for (int slot = 0; slot < getRows() * 9; slot++) {
                if (getItem(slot) != null || getInventory().getItem(slot) != null) continue;
                currentPage.put(slot, itemStack);
                getInventory().setItem(slot, itemStack);
                break;
            }
        }
    }

    @Override
    public void rerender() {
        getInventory().clear();
        currentPage.clear();

        populateGui();

        if (pageSize == 0) pageSize = calculatePageSize();

        populatePage();
        updatePage();

        for (HumanEntity viewer : new ArrayList<>(getInventory().getViewers())) ((Player) viewer).updateInventory();
    }

    public abstract void onInventoryClick(InventoryClickEvent event);

    void updatePage() {
        clearPage();
        populatePage();
    }

    void clearPage() {
        for (Map.Entry<Integer, ItemStack> entry : currentPage.entrySet()) {
            getInventory().setItem(entry.getKey(), null);
        }
    }

    public void clearPageItems(final boolean update) {
        pageItems.clear();
        if (update) rerender();
    }

    public void clearPageItems() {
        clearPageItems(false);
    }

    void rerenderPage() {
        clearPage();
        populatePage();
    }

    @Override
    public void open(Player player) {
        super.rerender();
        rerender();
        player.openInventory(getInventory());
    }

    int calculatePageSize() {
        int counter = 0;
        for (int slot = 0; slot < getRows() * 9; slot++) {
            if (getInventory().getItem(slot) == null && getItem(slot) == null) counter++;
        }
        return counter;
    }
}
