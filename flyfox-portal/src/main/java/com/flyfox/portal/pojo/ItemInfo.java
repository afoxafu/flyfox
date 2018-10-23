package com.flyfox.portal.pojo;

import com.flyfox.pojo.Item;

public class ItemInfo extends Item {

	public String[] getImages() {
		String image = getImage();
		if (image != null) {
			String[] images = image.split(",");
			return images;
		}
		return null;
	}
}
