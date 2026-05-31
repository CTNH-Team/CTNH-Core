package io.github.cpearl0.ctnhcore.client.ponder;

import io.github.cpearl0.ctnhcore.CTNHCore;

import com.gregtechceu.gtceu.GTCEu;

import net.createmod.ponder.api.element.TextElementBuilder;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;

import com.simibubi.create.foundation.ponder.CreateSceneBuilder;

import static io.github.cpearl0.ctnhcore.registry.CTNHRegistration.REGISTRATE;

public class CTNHPonderSceneBuilder extends CreateSceneBuilder {

    private String sceneId;
    private int textIndex;

    public CTNHPonderSceneBuilder(SceneBuilder builder) {
        super(builder);
    }

    public void init5x5(SceneBuildingUtil util) {
        this.configureBasePlate(0, 0, 5);
        this.scaleSceneView(0.9f);
        this.world().showSection(util.select().layer(0), Direction.UP);
    }

    public void init7x7(SceneBuildingUtil util) {
        this.configureBasePlate(0, 0, 7);
        this.scaleSceneView(0.75f);
        this.world().showSection(util.select().layer(0), Direction.UP);
    }

    public void init9x9(SceneBuildingUtil util) {
        this.configureBasePlate(0, 0, 9);
        this.scaleSceneView(0.6f);
        this.world().showSection(util.select().layer(0), Direction.UP);
    }

    public void initAll(SceneBuildingUtil util) {
        this.configureBasePlate(0, 0, 32);
        this.scaleSceneView(0.3f);
        this.world().showSection(util.select().layer(0), Direction.UP);
    }

    public void rotateAround(int duration) {
        int time = duration / 4;
        this.rotateCameraY(90);
        this.idle(time);
        this.rotateCameraY(90);
        this.idle(time);
        this.rotateCameraY(90);
        this.idle(time);
        this.rotateCameraY(90);
        this.idle(time);
    }

    public CreateSceneBuilder getSceneBuilder() {
        return this;
    }

    public TextElementBuilder showText(int duration, String en, String cn) {
        String key = nextTextKey();
        registerLang(key, en, cn);
        return overlay().showText(duration).text("");
    }

    public void title(String sceneId) {
        this.sceneId = sceneId;
        this.textIndex = 0;
        title(sceneId, "");
    }

    public void title(String sceneId, String title) {
        this.sceneId = sceneId;
        this.textIndex = 0;
        super.title(sceneId, title);
    }

    public void title(String sceneId, String en, String cn) {
        title(sceneId, en);
        registerLang(sceneLangKey("title"), en, cn);
        registerLang(sceneLangKey("header"), en, cn);
    }

    public void title(String sceneId, String headerEn, String headerCn, String titleEn, String titleCn) {
        title(sceneId, headerEn);
        registerLang(sceneLangKey("title"), titleEn, titleCn);
        registerLang(sceneLangKey("header"), headerEn, headerCn);
    }

    public void title(String sceneId, Component component) {
        String text = component.getString();
        title(sceneId, text, text);
    }

    private String nextTextKey() {
        return sceneLangKey("text_" + ++textIndex);
    }

    private String sceneLangKey(String entry) {
        return CTNHCore.MODID + ".ponder." + sceneId + "." + entry;
    }

    private void registerLang(String key, String en, String cn) {
        if (GTCEu.isDataGen()) {
            REGISTRATE.genLang(key, en, cn);
        }
    }
}
