package com.example.rentmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.common.utils.Constant
import com.example.common.utils.ViewType
import com.example.components.IRouter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val router: IRouter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plot737.setOnClickListener {
            router.routeTo(this, ViewType.PLOT, Bundle().apply {
                putString(Constant.PLOT_TYPE, Constant.PLOT737)
            })
        }

        plot578.setOnClickListener {
            router.routeTo(this, ViewType.PLOT, Bundle().apply {
                putString(Constant.PLOT_TYPE, Constant.PLOT578)
            })
        }
    }
}
