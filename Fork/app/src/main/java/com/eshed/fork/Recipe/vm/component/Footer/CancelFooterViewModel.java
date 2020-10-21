package com.eshed.fork.Recipe.vm.component.Footer;

public class CancelFooterViewModel extends FooterViewModel {
    public boolean isEditable;

    public CancelFooterViewModel(boolean isEditable) {
        this.isEditable = isEditable;
    }

    @Override
    public Type getType() {
        return Type.Footer_Cancel;
    }
}
