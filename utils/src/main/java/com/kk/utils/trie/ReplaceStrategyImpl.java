package com.kk.utils.trie;

public class ReplaceStrategyImpl implements ReplaceStrategy {

	@Override
	public String replaceWith(String keyword) {
		return "<" + keyword + ">";
	}

}
