package com.mastercard.exercise.cityconnections.datastructure.search.specification;

public interface Finder<T, U, V> {
	T find(U u, V v);
}
