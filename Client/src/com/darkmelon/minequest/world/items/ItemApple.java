package com.darkmelon.minequest.world.items;

import com.darkmelon.minequest.world.ItemStack;
import com.darkmelon.minequest.world.entities.Entity;
import com.darkmelon.minequest.world.entities.EntityLiving;

public class ItemApple extends Item {
	
	@Override
	public int getTexture(int i)  {
		return 1;
	}
	
	@Override
	public void onUse(ItemStack stack, Entity user) {
		if(user instanceof EntityLiving) {
			
			if(((EntityLiving)user).getHealth() != ((EntityLiving)user).getMaxHealth()) {
				
				((EntityLiving)user).setHealth(((EntityLiving)user).getHealth() + 3);
				stack.setCount(stack.getCount() - 1);
			}
		}
	}
}
