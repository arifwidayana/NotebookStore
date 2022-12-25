package com.arifwidayana.notebookstore.common.base

interface BaseContract {
    fun showContent(isVisible: Boolean)
    fun showLoading(isVisible: Boolean)
    fun showMessageToast(isEnabled: Boolean, message: String? = null)
    fun showMessageSnackBar(isEnabled: Boolean, message: String? = null)
}