package com.pandulapeter.beagle.logOkHttp

import com.pandulapeter.beagle.commonBase.BeagleNetworkLoggerContract

object BeagleOkHttpLogger : BeagleNetworkLoggerContract by OkHttpLoggerImplementation()