package com.mastercard.exercise.cityconnections.specification;

import java.io.IOException;

public interface ConfigurationLoader<T, V> {
	T loadConfiguration(V v) throws IOException;
}
