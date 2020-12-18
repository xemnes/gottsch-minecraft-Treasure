/**
 * 
 */
package com.someguyssoftware.treasure2.item.charm;

@FunctionalInterface
public interface Tooltip {
    public void add(final ItemStack stack, final World world, final List<String> tooltip, final ITooltipFlag flag);
}
