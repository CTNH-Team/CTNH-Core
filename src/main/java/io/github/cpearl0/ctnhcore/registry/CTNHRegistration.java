package io.github.cpearl0.ctnhcore.registry;

import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import io.github.cpearl0.ctnhcore.CTNHCore;
import net.minecraft.server.packs.FilePackResources;
import net.minecraft.server.packs.PackResources;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class CTNHRegistration {

    public static final CTNHRegistrate REGISTRATE = CTNHRegistrate.create();
    public static List<PackResources> getAllPackResources() {
        List<PackResources> packResources = new ArrayList<>();
        try (InputStream inputStream = CTNHRegistration.class.getResourceAsStream("/data/ctnhcore/ad_astra.zip")) {
            File tempFile = File.createTempFile("ctnh_resource_pack", ".tmp");
            FileUtils.copyInputStreamToFile(inputStream, tempFile);
            packResources.add(new FilePackResources(tempFile.getName(), tempFile, false));
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
        return packResources;
    }
}
