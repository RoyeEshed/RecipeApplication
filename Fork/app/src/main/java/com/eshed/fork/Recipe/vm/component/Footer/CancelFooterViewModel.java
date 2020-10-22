package com.eshed.fork.Recipe.vm.component.Footer;

public class CancelFooterViewModel extends FooterViewModel {
    public CancelFooterViewModel(boolean isEditable) {
        super(isEditable);
    }

    @Override
    public Type getType() {
        return Type.Footer_Cancel;
    }
}
