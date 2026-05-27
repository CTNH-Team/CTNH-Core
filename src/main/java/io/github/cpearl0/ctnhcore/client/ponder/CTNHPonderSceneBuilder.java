package io.github.cpearl0.ctnhcore.client.ponder;

import net.createmod.ponder.api.element.TextElementBuilder;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.Direction;

import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

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

    public TextElementBuilder showText(int duration) {
        return overlay().showText(duration).text("");
    }

    public TextElementBuilder showText(int duration, Lang lang) {
        textIndex++;
        validateTextKey(lang);
        return overlay().showText(duration).text("");
    }

    private void validateTextKey(Lang lang) {
        if (sceneId == null) {
            throw new IllegalStateException("Ponder scene title must be set before adding localized text");
        }
        String expectedKey = "ctnhcore.ponder." + sceneId + ".text_" + textIndex;
        if (!lang.key().equals(expectedKey)) {
            throw new IllegalArgumentException(
                    "Ponder text key mismatch, expected " + expectedKey + ", got " + lang.key());
        }
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
}
