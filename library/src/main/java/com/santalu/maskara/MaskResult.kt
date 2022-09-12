package com.santalu.maskara

import android.text.Editable
import android.text.Selection

/**
 * Created by fatih.santalu on 7/7/2020.
 */

internal data class MaskResult(
    val selection: Int,
    val masked: String,
    val unMasked: String,
    val isDone: Boolean
)

/**
 * Applies mask result to given text without getting affected by any input filters
 */
internal fun MaskResult.apply(text: Editable) {
    // suspend filters to allow masking work for all input types
    val filters = text.filters
    text.filters = emptyArray()

    text.replace(0, text.length, masked)
    
    // The selection could be higher than the length of the text if the EditText contains a filter then it'll lead it to crash.
    val filteredSelection = kotlin.math.min(selection, text.length)
    Selection.setSelection(text, filteredSelection)

    // resume filters
    text.filters = filters
}
