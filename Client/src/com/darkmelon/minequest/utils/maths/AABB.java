package com.darkmelon.minequest.utils.maths;

public class AABB {

	public float minX, minY, minZ;
	public float maxX, maxY, maxZ;
	
	public AABB(AABB o) {
		
		set(o.minX, o.minY, o.minZ, o.maxX, o.maxY, o.maxZ);
	}
	
	public AABB(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
		
		set(minX, minY, minZ, maxX, maxY, maxZ);
	}
	
	public AABB() {
		
		set(0, 0, 0, 0, 0, 0);
	}
	
	public float clipZCoord(AABB aabb, float z) {
		
		AABB o = new AABB(aabb);
		o.move(0, 0, z);
		
		if(collide(o)) {
			
			if(z <= 0) {			
				if (o.minZ <= maxZ) {
					return 0;
				}
			}else {
				if(o.maxZ >= minZ) {
					return 0;
				}
			}
		}
		
		return z;
	}
	
	public float clipYCoord(AABB aabb, float y) {
		
		AABB o = new AABB(aabb);
		o.move(0, y, 0);
		
		if(collide(o)) {
		
			if(y <= 0) {			
				if (o.minY <= maxY) {
					return 0;
				}
			}
			else {
				if(o.maxY >= minY) {
					return 0;
				}
			}
		}

		return y;
	}
	
	public float clipXCoord(AABB aabb, float x) {
		 
		AABB o = new AABB(aabb);
		o.move(x, 0, 0);
		
		if(collide(o)) {
		
			if(x <= 0) {			
				if (o.minX <= maxX) {
					return 0;
				}
			}else {
				if(o.maxX >= minX) {
					return 0;
				}
			}
		}
		
		return x;
	}
	
	public void move(float x, float y, float z) {
		
		this.minX += x;
		this.minY += y;
		this.minZ += z;
		this.maxX += x;
		this.maxY += y;
		this.maxZ += z;
	}
	
	public boolean collide(AABB aabb) {
		
		if((aabb.maxX > minX && aabb.maxY > minY && aabb.maxZ > minZ) && (aabb.minX < maxX && aabb.minY < maxY && aabb.minZ < maxZ)) {
			return true;
		}
		
		return false;
	}
	
	public void set(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
		
		this.minX = minX;
		this.minY = minY;
		this.minZ = minZ;
		this.maxX = maxX;
		this.maxY = maxY;
		this.maxZ = maxZ;
	}
}
