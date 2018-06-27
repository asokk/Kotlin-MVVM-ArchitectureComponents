package com.github.ramonrabello.cookpadassignment.view

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.MenuItem
import com.github.ramonrabello.cookpadassignment.R
import com.github.ramonrabello.cookpadassignment.view.NewCandidateActivity.Companion.EXTRA_IN_EDIT_MODE
import com.github.ramonrabello.cookpadassignment.core.data.model.Candidate
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.content_main.candidate_list
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking


class MainActivity : AppCompatActivity(), SwipeItemTouchHelper.Callback {

    private lateinit var viewModel: CandidateViewModel

    companion object {
        const val NEW_CANDIDATE_ACTIVITY_REQUEST_CODE = 1
    }

    private val candidateAdapter by lazy { CandidateAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        candidate_list.apply {
            adapter = candidateAdapter
            addItemDecoration(ItemSpacingDecoration(topOffset = 15))
            setLayoutManager(LinearLayoutManager(context))
        }

        // adding item touch helper
        // only ItemTouchHelper.LEFT added to detect Right to Left swipe
        // if you want both Right -> Left and Left -> Right
        // add pass ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT as param
        val itemTouchHelperCallback = SwipeItemTouchHelper(0, ItemTouchHelper.LEFT, this)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(candidate_list.recyclerView)

        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewCandidateActivity::class.java)
            startActivityForResult(intent, NEW_CANDIDATE_ACTIVITY_REQUEST_CODE)
        }

        viewModel = ViewModelProviders.of(this).get(CandidateViewModel::class.java)
        viewModel.allCandidates.observe(this, Observer {
            it?.let { candidateAdapter.updateCandidates(it) }
        })
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
        val candidate = candidateAdapter.items[position]
        launch {
            viewModel.delete(candidate)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_CANDIDATE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val candidate: Candidate? = data?.getParcelableExtra(NewCandidateActivity.EXTRA_CANDIDATE)
            candidate?.let {
                runBlocking {
                    if (data.getBooleanExtra(EXTRA_IN_EDIT_MODE, false)) {
                        viewModel.update(candidate)
                        candidateAdapter.notifyDataSetChanged()
                    } else {
                        viewModel.insert(candidate)
                    }
                }
            }
        } else {
            data?.let {
                Snackbar.make(fab, getString(R.string.empty_fields_message), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_delete_all -> {
                launch { viewModel.deleteAll() }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
