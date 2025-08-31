package io.github.cpearl0.ctnhcore.api.Pattern;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.common.block.CoilBlock;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import io.github.cpearl0.ctnhcore.common.block.blockdata.IPBData;
import io.github.cpearl0.ctnhcore.common.block.blockdata.ISSFData;
import io.github.cpearl0.ctnhcore.registry.CTNHBlocks;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CTNHBlockMaps {
    public static final Map<IPBData, Supplier<io.github.cpearl0.ctnhcore.common.block.PhotovoltaicBlock>> PhotovoltaicBlock = new HashMap();
    public static final Map<ISSFData,Supplier<io.github.cpearl0.ctnhcore.common.block.SpaceStructuralFramework>> SpaceStructuralFramework =new HashMap();
    public static final Map<Integer, Supplier<Block>> ReactorCoreBlock = new HashMap();
    public static final Map<Integer, Supplier<? extends Block>> CasingBlock = new HashMap<>();
    public static final Map<Integer, Supplier<? extends Block>> PipeBlock = new HashMap<>();
    public static final Map<Integer, Supplier<? extends Block>> CoilBlock = new HashMap<>();
    public static final Map<Integer, Supplier<? extends Block>> MachineCasingBlock = new HashMap<>();
    public static void initBlocks() {
        CasingBlock.put(1, GTBlocks.CASING_BRONZE_BRICKS);
        CasingBlock.put(2, GTBlocks.CASING_STEEL_SOLID);
        CasingBlock.put(3, GTBlocks.CASING_ALUMINIUM_FROSTPROOF);
        CasingBlock.put(4, GTBlocks.CASING_STAINLESS_CLEAN);
        CasingBlock.put(5, GTBlocks.CASING_TITANIUM_STABLE);
        CasingBlock.put(6, GTBlocks.CASING_TUNGSTENSTEEL_ROBUST);
        CasingBlock.put(7, CTNHBlocks.CASING_NAQUADAH_BLOCK);

        PipeBlock.put(1, GTBlocks.CASING_BRONZE_PIPE);
        PipeBlock.put(2, GTBlocks.CASING_STEEL_PIPE);
        PipeBlock.put(3, GTBlocks.CASING_TITANIUM_PIPE);
        PipeBlock.put(4, GTBlocks.CASING_TUNGSTENSTEEL_PIPE);

        GTCEuAPI.HEATING_COILS.forEach((coil, block) -> {
            CoilBlock.put(coil.getTier(), block);
        });
    }
}
