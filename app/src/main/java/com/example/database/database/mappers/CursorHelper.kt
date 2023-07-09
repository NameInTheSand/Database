package com.example.database.database.mappers

import android.database.CursorWindow
import net.sqlcipher.AbstractWindowedCursor
import net.sqlcipher.CrossProcessCursorWrapper
import net.sqlcipher.Cursor

object CursorHelper {

    fun unwrapCursor(cursor: Cursor): CursorWindow {
        val wrappedCursor = (cursor as CrossProcessCursorWrapper)
        return (wrappedCursor as AbstractWindowedCursor).window
    }

}