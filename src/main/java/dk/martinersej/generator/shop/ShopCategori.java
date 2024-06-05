package dk.martinersej.generator.shop;

public enum ShopCategori {

    BLOCKS(ShopBlocks.class),
    MISCELLANEOUS(ShopMiscellaneous.class),
    GENERATORS(ShopGenerators.class);

    private final Shop shopClass;

    ShopCategori(Class<?> shopClass) {
        Shop shopClass1;
        try {
            shopClass1 = (Shop) shopClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            shopClass1 = null;
        }
        this.shopClass = shopClass1;
    }

    public Shop getShop() {
        return shopClass;
    }
}