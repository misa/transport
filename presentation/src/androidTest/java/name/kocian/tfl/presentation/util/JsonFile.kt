package name.kocian.tfl.presentation.util

import android.support.test.InstrumentationRegistry

class JsonFile(private val name: String) {

    fun load() = InstrumentationRegistry.getContext().assets.open(name).use {
        it.reader().readText()
    }
}
