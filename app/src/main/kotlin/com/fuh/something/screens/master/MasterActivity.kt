package com.fuh.something.screens.master

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.widget.LinearLayoutManager
import com.fuh.something.R
import com.fuh.something.utils.BaseToolbarActivity
import com.fuh.something.utils.extensions.textValue
import kotlinx.android.synthetic.main.master_activity.*
import org.threeten.bp.Instant
import com.fuh.something.App
import com.fuh.something.models.Model
import com.fuh.something.models.Model_
import io.objectbox.Box
import io.objectbox.query.Query


/**
 * Created by nick on 14.09.17.
 */
class MasterActivity : BaseToolbarActivity() {

    private lateinit var modelsBox: Box<Model>
    private lateinit var modelsQuery: Query<Model>

    private lateinit var modelsAdapter: MasterAdapter

    override fun getLayoutId(): Int = R.layout.master_activity

    override fun ActionBar.init() {
        title = "MASTER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        modelsAdapter = MasterAdapter(R.layout.master_item)

        modelsBox = (application as App).boxStore.boxFor(Model::class.java)
        modelsQuery = modelsBox.query().order(Model_.text).build()
        rvMasterModels.adapter = modelsAdapter
        rvMasterModels.layoutManager = LinearLayoutManager(this)

        updateModels()

        ibtnMasterAddModel.setOnClickListener {
            val timestamp = Instant.now().toEpochMilli()
            val text = tilMasterModelText.textValue

            val model = Model(timestamp = timestamp, text = text)
            modelsBox.put(model)
            updateModels()

            tilMasterModelText.textValue = ""
        }
    }

    private fun updateModels() {
        val models = modelsQuery.find()
        modelsAdapter.replaceItems(models)
    }
}