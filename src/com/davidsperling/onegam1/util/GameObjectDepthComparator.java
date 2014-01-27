package com.davidsperling.onegam1.util;

import java.util.Comparator;

import com.davidsperling.onegam1.slickFramework.GameObject;

public class GameObjectDepthComparator implements Comparator<GameObject> {

	@Override
	public int compare(GameObject gameObject1, GameObject gameObject2) {
		return gameObject1.getDepth() > gameObject2.getDepth() ? -1
				: gameObject1.getDepth() < gameObject2.getDepth() ? 1
				: 0;
	}

}
