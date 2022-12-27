package com.arifwidayana.notebookstore.common.base

interface BaseContract {
    interface Activity {
        fun showContent(isVisible: Boolean)
        fun showLoading(isVisible: Boolean)
        fun showMessageToast(isEnabled: Boolean, message: String? = null)
        fun showMessageSnackBar(isEnabled: Boolean, message: String? = null)
    }

    interface DialogFragment {
        fun showMessageToast(isEnabled: Boolean, message: String? = null)
        fun showMessageSnackBar(isEnabled: Boolean, message: String? = null)
    }
}