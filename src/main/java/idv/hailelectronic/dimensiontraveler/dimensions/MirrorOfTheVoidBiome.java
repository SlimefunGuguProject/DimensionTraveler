package idv.hailelectronic.dimensiontraveler.dimensions;

import lombok.Getter;
import org.bukkit.block.Biome;

public enum MirrorOfTheVoidBiome {

    FOREST(Biome.FOREST),
    OCEAN(Biome.OCEAN),
    RIVER(Biome.RIVER),
    PLAINS(Biome.PLAINS),
    MOUNTAINS(Biome.MOUNTAINS),
    CRACK(Biome.THE_VOID);

    @Getter
    private final Biome biome;

    MirrorOfTheVoidBiome(Biome biome) {
        this.biome = biome;
    }

    //public static void biome(MirrorOfTheVoidBiome mirrorOfTheVoidBiome) {
    //}
}
