package com.somename.mytranslate.screen.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.widget.Toast

import com.somename.mytranslate.AppDelegate
import com.somename.mytranslate.R
import com.somename.mytranslate.content.WordFromDBViewModel
import com.somename.mytranslate.general.LoadingDialog
import com.somename.mytranslate.general.LoadingView
import com.somename.mytranslate.screen.adapters.BaseAdapter
import com.somename.mytranslate.screen.adapters.RecyclerAdapter
import com.somename.mytranslate.screen.presenter.ScrollingPresenter
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.content_scrolling.*

import java.util.ArrayList

import javax.inject.Inject
import android.view.MenuItem
import android.view.View


class ScrollingActivity : AppCompatActivity(), BaseAdapter.OnItemClickListener, ScrollingPresenter.View, RecyclerAdapter.OnClickListener {

    private var mLoadingView: LoadingView? = null

    private var mAdapter: RecyclerAdapter? = null

    @Inject
    lateinit var mPresenter: ScrollingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)

        mLoadingView = LoadingDialog.view(supportFragmentManager)
        recyclerView.layoutManager = LinearLayoutManager(this)

        mAdapter = RecyclerAdapter(ArrayList())
        mAdapter!!.attachToRecyclerView(recyclerView)
        mAdapter!!.setOnItemClickListener(this)
        mAdapter!!.setOnRemoveClickListener(this)

        val app = application as AppDelegate
        app.mainComponent?.inject(this)

        mPresenter.view = this
        mPresenter.init()

        fab.setOnClickListener {
            startActivity(Intent(this,TranslateActivity::class.java))
        }
    }

    override fun showLoading() {
        mLoadingView!!.showLoading()
    }

    override fun hideLoading() {
        mLoadingView!!.hideLoading()
    }

    override fun showWords(wordsFromDBViewModel: List<WordFromDBViewModel>) {
        mAdapter?.changeDataSet(wordsFromDBViewModel)
    }

    override fun showError() {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show()
    }

    override fun onItemClick(position: Int) {

    }

    override fun onRemoveClick(position: Int) {
        AlertDialog.Builder(this)
                .setTitle(getString(R.string.attention))
                .setMessage(getString(R.string.remove_question))
                .setPositiveButton(getString(android.R.string.ok), { _: DialogInterface, _: Int ->
                    mAdapter?.getItem(position)?.let { mPresenter.removeWord(it) }
                })
                .setNegativeButton(getString(android.R.string.no),null)
                .create()
                .show()
    }

    public override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_searchview, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchView.clearFocus()
                mPresenter.searchWord(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {

            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                mPresenter.init()
                return true
            }

        })
        return true
    }
}
