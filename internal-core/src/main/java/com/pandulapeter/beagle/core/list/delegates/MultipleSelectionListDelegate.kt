package com.pandulapeter.beagle.core.list.delegates

import com.pandulapeter.beagle.common.contracts.BeagleListItemContract
import com.pandulapeter.beagle.common.contracts.module.Cell
import com.pandulapeter.beagle.core.list.cells.ExpandedItemCheckBoxCell
import com.pandulapeter.beagle.core.list.delegates.shared.ExpandableModuleDelegate
import com.pandulapeter.beagle.core.list.delegates.shared.ValueWrapperModuleDelegate
import com.pandulapeter.beagle.modules.MultipleSelectionListModule

internal class MultipleSelectionListDelegate<T : BeagleListItemContract> : ExpandableModuleDelegate<MultipleSelectionListModule<T>>,
    ValueWrapperModuleDelegate.StringSet<MultipleSelectionListModule<T>>() {

    override fun canExpand(module: MultipleSelectionListModule<T>) = module.items.isNotEmpty()

    override fun getTitle(module: MultipleSelectionListModule<T>) = super.getTitle(module).let { text ->
        if (module.shouldRequireConfirmation && hasPendingChanges(module)) text.withSuffix("*") else text
    }

    override fun MutableList<Cell<*>>.addItems(module: MultipleSelectionListModule<T>) {
        addAll(module.items.map { item ->
            ExpandedItemCheckBoxCell(
                id = "${module.id}_${item.id}",
                text = item.title,
                isChecked = getUiValue(module).contains(item.id),
                isEnabled = module.isEnabled,
                onValueChanged = { isChecked ->
                    if (isChecked) {
                        setUiValue(module, getUiValue(module) + item.id)
                    } else {
                        setUiValue(module, getUiValue(module) - item.id)
                    }
                }
            )
        })
    }

    override fun createCells(module: MultipleSelectionListModule<T>) = super.createCells(module).also {
        callListenerForTheFirstTimeIfNeeded(module, getCurrentValue(module))
    }
}