package io.github.cpearl0.ctnhcore.common.block;

import com.gregtechceu.gtceu.api.block.AppearanceBlock;
import io.github.cpearl0.ctnhcore.common.block.blockdata.ISSFData;
import io.github.cpearl0.ctnhcore.common.block.blockdata.PlanetMinerData;
import lombok.Getter;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;

public class PlanetMinerBlock extends AppearanceBlock {
    public final PlanetMinerData data;

    public PlanetMinerBlock(BlockBehaviour.Properties properties,PlanetMinerData data) {
        super(properties);
        this.data = data;
    }
    public PlanetMinerData getData() {
        return this.data;
    }
    public static enum PlanetMinerFrameworkType implements StringRepresentable, PlanetMinerData {
        NQ_EXCITE_CARBON_CARBON_NANOFIBER_STRUCTURAL_BLOCK(1,false);
        @Getter
        private final int tier;
        private final boolean allowed;

        public boolean centrifugal_allow() {
            return this.allowed;
        }




        private  PlanetMinerFrameworkType(int tier,boolean allowed) {
            this.tier = tier;
            this.allowed=allowed;
        }



        public @NotNull String getPlanetMinerFrameworkName() {
            return this.name().toLowerCase();
        }

        public @NotNull String getSerializedName() {
            return this.getPlanetMinerFrameworkName();
        }
    }
}

