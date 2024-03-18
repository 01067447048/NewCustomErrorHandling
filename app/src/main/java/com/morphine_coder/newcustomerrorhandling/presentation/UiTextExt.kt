package com.morphine_coder.newcustomerrorhandling.presentation

import com.morphine_coder.newcustomerrorhandling.R
import com.morphine_coder.newcustomerrorhandling.domain.DataError
import com.morphine_coder.newcustomerrorhandling.domain.Result
import com.morphine_coder.newcustomerrorhandling.domain.UiText

fun DataError.asUiText(): UiText {
    return when(this) {
        DataError.Local.DISK_FULL -> UiText.StringResource(R.string.disk_full)
        DataError.Network.REQUEST_TIMEOUT -> UiText.StringResource(R.string.request_time_out)
        DataError.Network.TOO_MANY_REQUESTS -> UiText.StringResource(R.string.too_many_requests)
        DataError.Network.NO_INTERNET -> UiText.StringResource(R.string.no_internet)
        DataError.Network.PAYLOAD_TOO_LARGE -> UiText.StringResource(R.string.payload_too_large)
        DataError.Network.SERVER_ERROR -> UiText.StringResource(R.string.server_error)
        DataError.Network.SERIALIZATION -> UiText.StringResource(R.string.serfialization)
        DataError.Network.UNKNOWN -> UiText.StringResource(R.string.unknown)
    }
}

fun Result.Error<*, DataError>.asErrorUiText(): UiText {
    return this.error.asUiText()
}