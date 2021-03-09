package dev.ebnbin.ebdev

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import cat.ereza.customactivityoncrash.CustomActivityOnCrash
import dev.ebnbin.eb.appIcon
import dev.ebnbin.eb.appLabel
import dev.ebnbin.ebdev.databinding.EbdevCrashActivityBinding

internal class CrashActivity : AppCompatActivity() {
    private val viewModel: CrashActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        if (intent == null) {
            finish()
            return
        }
        val caocConfig = CustomActivityOnCrash.getConfigFromIntent(intent)
        if (caocConfig == null) {
            finish()
            return
        }
        val binding = DataBindingUtil.setContentView<EbdevCrashActivityBinding>(this, R.layout.ebdev_crash_activity)
        binding.lifecycleOwner = this
        binding.ebdevIcon.setImageDrawable(appIcon)
        binding.ebdevTitle.text = getString(R.string.ebdev_crash_title, appLabel)
        binding.ebdevClose.setOnClickListener {
            CustomActivityOnCrash.closeApplication(this, caocConfig)
        }
        binding.ebdevRestart.setOnClickListener {
            CustomActivityOnCrash.restartApplication(this, caocConfig)
        }
        binding.viewModel = viewModel
        if (viewModel.log.value == null) {
            viewModel.log.value = CustomActivityOnCrash.getStackTraceFromIntent(intent)
        }
    }

    override fun onBackPressed() {
//        super.onBackPressed()
    }
}
