package com.santalu.maskara

/**
 * Created by fatih.santalu on 7/7/2020.
 *
 * Defines the mask visibility and behavior of the cursor
 *
 * [NORMAL]
 * Mask is never visible.
 * Cursor is not limited.
 *
 * [COMPLETABLE]
 * Mask becomes visible right after the user started typing until the user deleted everything.
 * Cursor is not limited.
 *
 * [PERSISTENT]
 * Mask becomes visible right after the user started typing and never becomes hidden.
 * Cursor is limited between mask characters.
 * Placeholders are not allowed to delete.
 */

enum class MaskStyle {

    NORMAL,
    COMPLETABLE,
    PERSISTENT;

    companion object {

        fun valueOf(ordinal: Int): MaskStyle = values().find { it.ordinal == ordinal } ?: NORMAL
    }
}
