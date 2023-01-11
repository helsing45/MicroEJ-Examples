/**
 *
 */
package com.logient.tools;

import ej.observable.Observable;

/**
 *
 */
public class ObservableField<T> extends Observable {
	private T _value;

	public ObservableField() {
		super();
	}

	public ObservableField(T value) {
		super();
		this._value = value;
	}

	public T get() {
		return this._value;
	}

	public synchronized void set(T value) {
		if (value != this._value) {
			this._value = value;
			setChanged();
			notifyObservers();
		}
	}
}
