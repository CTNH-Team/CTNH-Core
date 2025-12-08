//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.github.cpearl0.ctnhcore.common.gui.terminal;

import com.lowdragmc.lowdraglib.gui.widget.TextFieldWidget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import java.util.function.Consumer;
import java.util.function.Supplier;
import lombok.Generated;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.Mth;

public class TerminalInputWidget extends WidgetGroup {
    private Supplier<Integer> valueSupplier;
    private Integer min = this.defaultMin();
    private Integer max = this.defaultMax();
    private final Consumer<Integer> onChanged;
    private TextFieldWidget textField;

    protected String toText(Integer value) {
        return String.valueOf(value);
    }

    protected Integer fromText(String value) {
        return Integer.parseInt(value);
    }

    protected Integer clamp(Integer value, Integer min, Integer max) {
        return Mth.clamp(value, min, max);
    }

    protected Integer defaultMin() {
        return 0;
    }

    protected Integer defaultMax() {
        return Integer.MAX_VALUE;
    }

    protected void setTextFieldRange(TextFieldWidget textField, Integer min, Integer max) {
        textField.setNumbersOnly(min, max);
    }

    public TerminalInputWidget(int x, int y, int width, int height, Supplier<Integer> valueSupplier, Consumer<Integer> onChanged) {
        super(x, y, width, height);
        this.valueSupplier = valueSupplier;
        this.onChanged = onChanged;
        this.buildUI();
    }

    public void initWidget() {
        super.initWidget();
        this.textField.setCurrentString(this.toText((Integer)this.valueSupplier.get()));
    }

    public void writeInitialData(FriendlyByteBuf buffer) {
        super.writeInitialData(buffer);
        buffer.writeUtf(this.toText((Integer)this.valueSupplier.get()));
    }

    public void readInitialData(FriendlyByteBuf buffer) {
        super.readInitialData(buffer);
        this.textField.setCurrentString(buffer.readUtf());
    }

    private void buildUI() {
        this.textField = new TextFieldWidget(0, 0, this.getSizeWidth(), 12, () -> this.toText((Integer)this.valueSupplier.get()), (stringValue) -> this.setValue(this.clamp(this.fromText(stringValue), this.min, this.max))) {
            public boolean mouseWheelMove(double mouseX, double mouseY, double wheelDelta) {
                if (this.wheelDur > 0.0F && this.numberInstance != null && this.isMouseOverElement(mouseX, mouseY) && this.isFocus()) {
                    try {
                        this.onTextChanged(String.valueOf(Integer.parseInt(this.getCurrentString()) + (int)((float)(wheelDelta > (double)0.0F ? 1 : -1) * this.wheelDur)));
                    } catch (Exception var8) {
                    }

                    this.setFocus(true);
                    return true;
                } else {
                    return false;
                }
            }
        };
        this.addWidget(this.textField);
    }

    public TerminalInputWidget setValue(Integer value) {
        this.onChanged.accept(value);
        return this;
    }

    public TerminalInputWidget setMin(Integer min) {
        this.min = min;
        this.updateTextFieldRange();
        return this;
    }

    public TerminalInputWidget setMax(Integer max) {
        this.max = max;
        this.updateTextFieldRange();
        return this;
    }

    protected void updateTextFieldRange() {
        this.setTextFieldRange(this.textField, this.min, this.max);
        this.setValue(this.clamp((Integer)this.valueSupplier.get(), this.min, this.max));
    }

    @Generated
    public Supplier<Integer> getValueSupplier() {
        return this.valueSupplier;
    }

    @Generated
    public Integer getMin() {
        return this.min;
    }

    @Generated
    public Integer getMax() {
        return this.max;
    }
}
