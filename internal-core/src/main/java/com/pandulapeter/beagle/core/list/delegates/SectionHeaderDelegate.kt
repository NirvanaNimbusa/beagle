package com.pandulapeter.beagle.core.list.delegates

import com.pandulapeter.beagle.common.contracts.module.Cell
import com.pandulapeter.beagle.common.contracts.module.Module
import com.pandulapeter.beagle.core.list.cells.SectionHeaderCell
import com.pandulapeter.beagle.modules.SectionHeaderModule

@Deprecated("Remove together with SectionHeaderModule")
internal class SectionHeaderDelegate : Module.Delegate<SectionHeaderModule> {

    override fun createCells(module: SectionHeaderModule): List<Cell<*>> = listOf(
        SectionHeaderCell(
            id = module.id,
            text = module.title,
            isEnabled = true,
            icon = null,
            onItemSelected = null
        )
    )
}