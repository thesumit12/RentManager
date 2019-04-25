package com.example.components.common

import androidx.databinding.ObservableField

/**
 * Extension class for ObservableField to allow only non-null values to be set.
 * @author Sumit Lakra
 * @date 04/25/19
 */
class NonNullObservableField<T : Any>(
    value: T
) : ObservableField<T>() {
    init {
        set(value)
    }

    override fun get(): T {
        @Suppress("UNCHECKED_CAST")
        return super.get() ?: "" as T
    }

    @Suppress("RedundantOverride") // Only allow non-null `value`.
    override fun set(value: T) = super.set(value)
}