package com.narxoz.rpg.decorator;

public class PoisonCoatingDecorator extends ActionDecorator {
    public PoisonCoatingDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return "Toxic " + super.getActionName();
    }

    @Override
    public int getDamage() {
        return super.getDamage() + 3;
    }

    @Override
    public String getEffectSummary() {
        return super.getEffectSummary() + " Applies a deadly poison.";
    }
}