package com.santalu.maskara

import android.text.Selection
import kotlin.math.max
import kotlin.math.min

/**
 * Created by fatih.santalu on 7/7/2020.
 */

@Suppress("SpellCheckingInspection")
internal class Maskara(private val mask: Mask) {

    operator fun invoke(text: CharSequence, action: Action): MaskResult {
        val source = text.iterator()
        val masked = StringBuilder()
        val unMasked = StringBuilder()

        var char = source.nextOrNull()
        loop@ for (maskChar in mask.value) {
            when {
                maskChar == char && maskChar != mask.character -> {
                    masked.append(maskChar)
                    char = source.nextOrNull()
                }
                maskChar == mask.character -> {
                    char = source.nextMaskChar(char)
                    if (char == null) {
                        if (mask.style == MaskStyle.NORMAL) break@loop
                        masked.append(maskChar)
                    } else {
                        masked.append(char)
                        unMasked.append(char)
                        char = source.nextOrNull()
                    }
                }
                else -> {
                    if (char == null && mask.style == MaskStyle.NORMAL && action == Action.DELETE) break@loop
                    masked.append(maskChar)
                }
            }
        }

        // Clear mask to let user defined hint shown
        var maskedText = masked.toString()
        var unMaskedText = unMasked.toString()
        if (mask.style != MaskStyle.PERSISTENT && mask.value.startsWith(maskedText)) {
            maskedText = masked.clear().toString()
            unMaskedText = unMasked.clear().toString()
        }

        val selection = mask.nextSelection(text, maskedText, action)
        return MaskResult(selection, maskedText, unMaskedText, mask.isDone(maskedText))
    }
}

private fun CharIterator.nextOrNull(): Char? = if (hasNext()) nextChar() else null

/**
 * Returns the next valid mask character from start
 */
private fun CharIterator.nextMaskChar(char: Char?): Char? {
    var nextMaskChar = char ?: return null
    while (hasNext()) {
        if (nextMaskChar.isLetterOrDigit()) break
        nextMaskChar = nextChar()
    }
    // In case we could not find any valid character
    return if (nextMaskChar.isLetterOrDigit()) nextMaskChar else null
}

/**
 * Returns index of first mask character after the current cursor position
 */
private fun Mask.nextSelection(before: CharSequence, after: CharSequence, action: Action): Int {
    if (after.isEmpty()) return 0

    val start = Selection.getSelectionStart(before)
    if (action == Action.DELETE) {
        if (style != MaskStyle.PERSISTENT) return start
        // Do not let the cursor exceed beyond first mask character
        val firstMaskIndex = value.indexOf(character, 0)
        return start.coerceAtLeast(firstMaskIndex)
    }

    val end = after.length
    var nextMaskIndex = value.indexOf(character, start)
    // Make sure the cursor is located to the end of the selection
    val previousChar = before.getOrNull(start)
    val nextChar = after.getOrNull(nextMaskIndex)
    if (previousChar != nextChar && nextChar != character) {
        ++nextMaskIndex
    }

    return nextMaskIndex.coerceIn(min(start, end), max(start, end))
}

/**
 * Returns validity of mask result
 */
private fun Mask.isDone(after: CharSequence): Boolean {
    return after.length == value.length && !after.contains(character)
}
