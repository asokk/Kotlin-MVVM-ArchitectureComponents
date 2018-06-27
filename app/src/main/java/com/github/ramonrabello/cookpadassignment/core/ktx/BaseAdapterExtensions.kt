package com.github.ramonrabello.cookpadassignment.core.ktx

import com.github.ramonrabello.cookpadassignment.view.BaseAdapter

/**
 * Extension functions for [BaseAdapter].
 */
inline fun <VH, K, T : BaseAdapter<K, VH>> T.addItem(model: K, afterInsert: () -> Unit) {
    val position = this.items.indexOf(model)
    synchronized(items) {
        this.items.add(model).takeIf { position == -1 }
                ?.apply {
                    notifyItemInserted(0)
                    afterInsert()
                }
    }
}

inline fun <VH, K, T : BaseAdapter<K, VH>> T.deleteItem(model: K, afterDelete: () -> Unit) {
    val position = this.items.indexOf(model)
    synchronized(items) {
        this.items.remove(model).takeIf { position != -1 }
                ?.apply {
                    notifyItemRemoved(position)
                    afterDelete()
                }
    }
}

inline fun <VH, K, T : BaseAdapter<K, VH>> T.updateItem(model: K, afterUpdate: () -> Unit) {
    val position = this.items.indexOf(model)
    synchronized(items) {
        this.items.set(position, model).takeIf { position != -1 }
                ?.apply {
                    notifyItemChanged(position)
                    afterUpdate()
                }
    }
}