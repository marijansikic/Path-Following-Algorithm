package com.sikic.pathfollowingalgorithm

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sikic.pathfollowingalgorithm.AsciiMapType.*
import kotlinx.android.synthetic.main.activity_main.*

private const val READ_REQUEST_CODE: Int = 10

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.liveData.observe(this, Observer<MainActivityState> {
            when (it) {
                is MainActivityState.Loaded -> showValidAlgorithm(
                    asciiMap = it.asciiMap,
                    path = it.path,
                    letters = it.letters
                )
                is MainActivityState.Invalid -> showInvalidAlgorithm(
                    asciiMap = it.asciiMap,
                    path = it.path,
                    letters = it.letters
                )
            }
        })

        btnSelectSource.setOnClickListener { showSourceDialog() }
    }

    private fun showValidAlgorithm(asciiMap: String, path: String, letters: String) {
        tvAsciiMap.text = asciiMap
        tvPath.text = getString(R.string.algorithm_path, path)
        tvLetters.text = getString(R.string.letters_path, letters)
    }

    private fun showInvalidAlgorithm(asciiMap: String, path: String, letters: String) {
        tvAsciiMap.text = asciiMap
        tvPath.text = getString(R.string.algorithm_path, path)
        tvLetters.text = getString(R.string.letters_path, letters)

        Toast.makeText(
            this,
            getString(R.string.invalid_format),
            Toast.LENGTH_LONG
        )
            .show()
    }

    private fun showSourceDialog() {
        val options = arrayOf(
            MAP1.value,
            MAP2.value,
            MAP3.value,
            MAP4.value,
            CUSTOM.value
        )

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setItems(options) { _, i ->
                if (i == options.lastIndex) {
                    browseFiles()
                } else {
                    viewModel.fetchAsciiMap(options[i])
                }

            }.show()
    }

    private fun browseFiles() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "text/plain"
        }
        startActivityForResult(intent, READ_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            resultData?.data?.also {
                if (it.scheme.equals("content")) {
                    val inputStream = contentResolver.openInputStream(it)
                    if (inputStream != null) {
                        viewModel.fetchAsciiMap(
                            String(inputStream.readBytes())
                        )
                    }
                }
            }
        }
    }
}
