package com.somename.mytranslate.general

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import android.view.View
import com.somename.mytranslate.R

import java.lang.ref.Reference
import java.lang.ref.WeakReference
import java.util.concurrent.atomic.AtomicBoolean

class LoadingDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, theme)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity!!)
                .setView(View.inflate(activity, R.layout.dialog_loading, null))
                .create()
    }

    private class LoadingDialogView internal constructor(private val mFm: FragmentManager) : LoadingView {

        private val mWaitForHide: AtomicBoolean

        init {
            val shown = mFm.findFragmentByTag(LoadingDialog::class.java.name) != null
            mWaitForHide = AtomicBoolean(shown)
        }

        override fun showLoading() {
            if (mWaitForHide.compareAndSet(false, true)) {
                if (mFm.findFragmentByTag(LoadingDialog::class.java.name) == null) {
                    val dialog = LoadingDialog()
                    dialog.show(mFm, LoadingDialog::class.java.name)
                }
            }
        }

        override fun hideLoading() {
            if (mWaitForHide.compareAndSet(true, false)) {
                HANDLER.post(HideTask(mFm))
            }
        }
    }

    private class HideTask(fm: FragmentManager) : Runnable {

        private val mFmRef: Reference<FragmentManager>

        init {
            mFmRef = WeakReference(fm)
        }

        override fun run() {
            HANDLER.removeCallbacks(this)
            val fm = mFmRef.get()
            if (fm != null) {
                val dialog = fm.findFragmentByTag(LoadingDialog::class.java.name) as LoadingDialog
                dialog.dismissAllowingStateLoss()
            }
        }
    }

    companion object {

        private val HANDLER = Handler(Looper.getMainLooper())

        fun view(fm: FragmentManager): LoadingView {
            return LoadingDialogView(fm)
        }

        fun view(fragment: Fragment): LoadingView {
            return view(fragment.fragmentManager!!)
        }
    }

}
