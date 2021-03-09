package dev.ebnbin.ebdev

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

internal class CrashActivityViewModel : ViewModel() {
    val log: MutableLiveData<CharSequence?> = MutableLiveData(null)
}
