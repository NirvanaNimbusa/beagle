package com.pandulapeter.beagle.appDemo.feature.main.examples.authentication

import com.pandulapeter.beagle.common.configuration.toText
import com.pandulapeter.beagle.common.contracts.BeagleListItemContract

data class TestAccount(
    val email: String,
    val password: String
) : BeagleListItemContract {

    override val title = email.toText()
}