package com.somename.mytranslate.screen.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.somename.mytranslate.AppDelegate
import com.somename.mytranslate.R
import com.somename.mytranslate.content.WordFromTranslatorViewModel
import com.somename.mytranslate.general.LoadingDialog
import com.somename.mytranslate.general.LoadingView
import com.somename.mytranslate.screen.presenter.TranslatePresenter
import kotlinx.android.synthetic.main.activity_translate.*
import javax.inject.Inject

class TranslateActivity : AppCompatActivity(), TranslatePresenter.View {


    private var mLoadingView: LoadingView? = null

    @Inject
    lateinit var mPresenter: TranslatePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translate)

        mLoadingView = LoadingDialog.view(supportFragmentManager)

        val app = application as AppDelegate
        app.mainComponent?.inject(this)

        mPresenter.view = this

        var lang = getString(R.string.english_lang)
        radioButtonDeLang.setOnCheckedChangeListener { _, p1 ->
            if (p1)
                lang = getString(R.string.german_lang)
        }
        radioButtonEnLang.setOnCheckedChangeListener { _, p1 ->
            if (p1)
                lang = getString(R.string.english_lang)
        }
        radioButtonRuLang.setOnCheckedChangeListener { _, p1 ->
            if (p1)
                lang = getString(R.string.russian_lang)
        }
        inputWordEditText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                mPresenter.getTranslate(s.toString(), lang)
            }
        })
    }

    override fun showTranslate(wordFromTranslatorViewModel: WordFromTranslatorViewModel, originalText: String) {
        translateTextView.text = wordFromTranslatorViewModel.text
        yandexTextView.visibility = View.VISIBLE
        translateTitleTextView.visibility = View.VISIBLE
        buttonOk.setOnClickListener {
            mPresenter.addWord(wordFromTranslatorViewModel, originalText)
            startActivity(Intent(this,ScrollingActivity::class.java))
            finish()
        }
        buttonInverse.setOnClickListener {
            val lang = wordFromTranslatorViewModel.langWord
            if (lang == getString(R.string.english_lang))
                radioButtonEnLang.isChecked = true
            if (lang == getString(R.string.russian_lang))
                radioButtonRuLang.isChecked = true
            if (lang == getString(R.string.german_lang))
                radioButtonDeLang.isChecked = true
            inputWordEditText.setText(wordFromTranslatorViewModel.text)
        }
    }

    override fun showLoading() {
        mLoadingView!!.showLoading()
    }

    override fun hideLoading() {
        mLoadingView!!.hideLoading()
    }

    override fun showError() {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show()
    }

    public override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }
}
