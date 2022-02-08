package idv.hailelectronic.dimensiontraveler;

import idv.hailelectronic.dimensiontraveler.items.DenyUse;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

public class DimensionTraveler extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {
        // Read something from your config.yml
        Config cfg = new Config(this);

        //if (cfg.getBoolean("options.auto-update")) {
            // You could start an Auto-Updater for example
        //}
        //Category
        ItemStack itemGroupItem = new CustomItemStack(Material.BASALT, "&b&k|||&r&b维度旅者&k|||", "");
        NamespacedKey itemGroupId = new NamespacedKey(this, "dimension_traveler");
        //Item
        //  Raw Cast Slate
        ItemGroup itemGroup = new ItemGroup(itemGroupId, itemGroupItem);
        SlimefunItemStack sfItemRawCastSlate = new SlimefunItemStack("RAW_CAST_SLATE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2ZlNDgwNmQxODVhYzA4MDNjMjMyNjE5NDFiM2Q0N2M5YzA2NWYyZDJhYmQyYzY2YjFkN2EwYTdkYTM2NjQzMCJ9fX0=", "&7未烧制的铸石", "&6Basic material","&8&ndimension_traveler:raw_cast_slate");
        ItemStack[] recipeRawCastSlate = { new ItemStack(Material.BASALT), new ItemStack(Material.BASALT), new ItemStack(Material.BASALT), SlimefunItems.IRON_DUST, new ItemStack(Material.DIAMOND), SlimefunItems.IRON_DUST, new ItemStack(Material.BASALT), new ItemStack(Material.BASALT), new ItemStack(Material.BASALT) };
        SlimefunItem itemRawCastSlate = new SlimefunItem(itemGroup, sfItemRawCastSlate, RecipeType.ENHANCED_CRAFTING_TABLE, recipeRawCastSlate,new SlimefunItemStack(sfItemRawCastSlate,8));
        itemRawCastSlate.register(this);
        //  Cast Slate
        SlimefunItemStack sfItemCastSlate = new SlimefunItemStack("CAST_SLATE", Material.DRIED_KELP, "&7铸石板", "&6Basic material","&8&ndimension_traveler:cast_slate");
        ItemStack[] recipeCastSlate = { getItem("RAW_CAST_SLATE")};
        DenyUse itemCastSlate = new DenyUse(itemGroup, sfItemCastSlate, RecipeType.SMELTERY, recipeCastSlate);
        itemCastSlate.register(this);
        //  Dimension Gem
        SlimefunItemStack sfItemDimensionGem = new SlimefunItemStack("DIMENSION_GEM", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2I5NTFmZWQ2YTdiMmNiYzIwMzY5MTZkZWM3YTQ2YzRhNTY0ODE1NjRkMTRmOTQ1YjZlYmMwMzM4Mjc2NmQzYiJ9fX0=", "&9维度宝石", "&6Basic material","&8&ndimension_traveler:dimension_gem");
        ItemStack[] recipeDimensionGem = { null,new ItemStack(Material.ENDER_PEARL),null,new ItemStack(Material.ENDER_PEARL),new ItemStack(Material.DIAMOND),new ItemStack(Material.ENDER_PEARL),null,new ItemStack(Material.ENDER_PEARL),null };
        SlimefunItem itemDimensionGem = new SlimefunItem(itemGroup, sfItemDimensionGem, RecipeType.ENHANCED_CRAFTING_TABLE, recipeDimensionGem);
        itemDimensionGem.register(this);
        //  Portal Controller
        SlimefunItemStack sfItemPortalController = new SlimefunItemStack("PORTAL_CONTROLLER", Material.LODESTONE, "&e传送门控制器", "&6Functional Block","&8&ndimension_traveler:portal_controller");
        ItemStack[] recipePortalController = { getItem("CAST_SLATE"),getItem("CAST_SLATE"),getItem("CAST_SLATE"),getItem("CAST_SLATE"),getItem("DIMENSION_GEM"),getItem("CAST_SLATE"),getItem("CAST_SLATE"),getItem("CAST_SLATE"),getItem("CAST_SLATE")};
        SlimefunItem itemPortalController = new SlimefunItem(itemGroup, sfItemPortalController, RecipeType.ENHANCED_CRAFTING_TABLE, recipePortalController);
        itemPortalController.register(this);
        //Research
        //  000
        NamespacedKey beforeEnter =  new NamespacedKey( this , " before_enter " );
        Research research000 =  new Research(beforeEnter, 730516000 , " Before enter " , 15 );
        research000.addItems(itemRawCastSlate,itemCastSlate,itemDimensionGem,itemPortalController);
        research000.register();
    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        // You can return a link to your Bug Tracker instead of null here
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        /*
         * You will need to return a reference to your Plugin here.
         * If you are using your main class for this, simply return "this".
         */
        return this;
    }
    /*Copy from ExoticGarden:
     *https://github.com/TheBusyBiscuit/ExoticGarden/blob/master/src/main/java/io/github/thebusybiscuit/exoticgarden/ExoticGarden.java
     * Line:332~336
     */
    private static ItemStack getItem(String id) {
        SlimefunItem item = SlimefunItem.getById(id);
        return item != null ? item.getItem() : null;
    }



}
