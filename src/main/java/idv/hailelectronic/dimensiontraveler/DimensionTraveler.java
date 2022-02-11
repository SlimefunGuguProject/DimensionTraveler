package idv.hailelectronic.dimensiontraveler;

import idv.hailelectronic.dimensiontraveler.dimensions.MirrorOfTheVoid;
import idv.hailelectronic.dimensiontraveler.items.DenyUse;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.WorldCreator;
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
        ItemStack itemGroupItem = new CustomItemStack(Material.BASALT, "&b&k|||&r&b维度旅者&k|||");
        NamespacedKey itemGroupId = new NamespacedKey(this, "dimension_traveler");
        ItemGroup itemGroup = new ItemGroup(itemGroupId, itemGroupItem);
        //Item
        //  Basalt Fragments
        SlimefunItemStack sfItemBasaltFragments = new SlimefunItemStack("BASALT_FRAGMENTS", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2ZlNDgwNmQxODVhYzA4MDNjMjMyNjE5NDFiM2Q0N2M5YzA2NWYyZDJhYmQyYzY2YjFkN2EwYTdkYTM2NjQzMCJ9fX0=", "&7玄武岩碎块", "&6Basic material","&8&ndimension_traveler:basalt_fragments");
        ItemStack[] recipeBasaltFragments = { new ItemStack(Material.BASALT)};
        SlimefunItem itemBasaltFragments = new SlimefunItem(itemGroup, sfItemBasaltFragments, RecipeType.GRIND_STONE, recipeBasaltFragments,new SlimefunItemStack(sfItemBasaltFragments,8));
        itemBasaltFragments.register(this);
        //  Cast Slate
        SlimefunItemStack sfItemCastSlate = new SlimefunItemStack("CAST_SLATE", Material.DRIED_KELP, "&7铸石板", "&6Basic material","&8&ndimension_traveler:cast_slate");
        ItemStack[] recipeCastSlate = { getItem("BASALT_FRAGMENTS"),SlimefunItems.IRON_DUST};
        DenyUse itemCastSlate = new DenyUse(itemGroup, sfItemCastSlate, RecipeType.HEATED_PRESSURE_CHAMBER, recipeCastSlate);
        itemCastSlate.register(this);
        //  Dimension Fragments
        SlimefunItemStack sfItemDimensionFragments = new SlimefunItemStack("DIMENSION_FRAGMENTS", Material.PRISMARINE_SHARD, "&9维度碎片", "&6Basic material","&8&ndimension_traveler:dimension_fragments");
        ItemStack[] recipeDimensionFragments = { new ItemStack(Material.ENDER_PEARL)};
        SlimefunItem itemDimensionFragments = new SlimefunItem(itemGroup, sfItemDimensionFragments, RecipeType.GRIND_STONE, recipeDimensionFragments,new SlimefunItemStack(sfItemDimensionFragments,4));
        itemDimensionFragments.register(this);
        //  Dimension Gem
        SlimefunItemStack sfItemDimensionGem = new SlimefunItemStack("DIMENSION_GEM", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2I5NTFmZWQ2YTdiMmNiYzIwMzY5MTZkZWM3YTQ2YzRhNTY0ODE1NjRkMTRmOTQ1YjZlYmMwMzM4Mjc2NmQzYiJ9fX0=", "&9维度宝石", "&6Basic material","&8&ndimension_traveler:dimension_gem");
        ItemStack[] recipeDimensionGem = { SlimefunItems.MAGIC_LUMP_3,getItem("DIMENSION_FRAGMENTS"),SlimefunItems.ENDER_LUMP_3,getItem("DIMENSION_FRAGMENTS"),new ItemStack(Material.DIAMOND),getItem("DIMENSION_FRAGMENTS"),SlimefunItems.ENDER_LUMP_3,getItem("DIMENSION_FRAGMENTS"),SlimefunItems.MAGIC_LUMP_3 };
        SlimefunItem itemDimensionGem = new SlimefunItem(itemGroup, sfItemDimensionGem, RecipeType.MAGIC_WORKBENCH, recipeDimensionGem);
        itemDimensionGem.register(this);
        //  Portal Controller
        SlimefunItemStack sfItemPortalController = new SlimefunItemStack("PORTAL_CONTROLLER", Material.LODESTONE, "&e传送门控制器", "&6Functional Block","&8&ndimension_traveler:portal_controller");
        ItemStack[] recipePortalController = { getItem("CAST_SLATE"),getItem("CAST_SLATE"),getItem("CAST_SLATE"),getItem("CAST_SLATE"),getItem("DIMENSION_GEM"),getItem("CAST_SLATE"),getItem("CAST_SLATE"),getItem("CAST_SLATE"),getItem("CAST_SLATE")};
        SlimefunItem itemPortalController = new SlimefunItem(itemGroup, sfItemPortalController, RecipeType.ENHANCED_CRAFTING_TABLE, recipePortalController);
        itemPortalController.register(this);
        //  Thin Cloud
        SlimefunItemStack sfItemThinCloud = new SlimefunItemStack("THIN_CLOUD", Material.WHITE_STAINED_GLASS, "&f轻薄的云", "&6Basic material","&8&ndimension_traveler:thin_cloud");
        ItemStack[] recipeThinCloud = { getItem("CAST_SLATE"),getItem("CAST_SLATE"),getItem("CAST_SLATE"),getItem("CAST_SLATE"),getItem("DIMENSION_GEM"),getItem("CAST_SLATE"),getItem("CAST_SLATE"),getItem("CAST_SLATE"),getItem("CAST_SLATE")};
        SlimefunItem itemThinCloud = new SlimefunItem(itemGroup, sfItemThinCloud, RecipeType.ENHANCED_CRAFTING_TABLE, recipeThinCloud);
        itemThinCloud.register(this);
        //Research
        //  000
        NamespacedKey beforeEnter =  new NamespacedKey( this , "before_enter" );
        Research research000 =  new Research(beforeEnter, 730516000 , "启程前 Before enter" , 15 );
        research000.addItems(itemBasaltFragments,itemCastSlate,itemDimensionFragments,itemDimensionGem,itemPortalController);
        research000.register();
        //  001
        NamespacedKey firstEnteringForeignLand =  new NamespacedKey( this , "first_entering_foreign_land" );
        Research research001 =  new Research(firstEnteringForeignLand, 730516001 , "初入异境 First entering foreign land" , 20 );
        research001.addItems(itemThinCloud);
        research001.register();
        //Dimension
        /*Copy from DynaTech:
         *https://github.com/ProfElements/DynaTech/blob/master/src/main/java/me/profelements/dynatech/DynaTech.java
         * Line:48~51
         */
        WorldCreator worldCreator = new WorldCreator("mirrorOfTheVoid");
        worldCreator.generator(new MirrorOfTheVoid());
        World mirrorOfTheVoid = worldCreator.createWorld();
        new BlockStorage(mirrorOfTheVoid);
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
    static ItemStack getItem(String id) {
        SlimefunItem item = SlimefunItem.getById(id);
        return item != null ? item.getItem() : null;
    }



}
