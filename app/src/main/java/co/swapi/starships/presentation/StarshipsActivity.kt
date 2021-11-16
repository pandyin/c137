package co.swapi.starships.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.swapi.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class StarshipsActivity : AppCompatActivity() {

    private val viewModel: StarshipsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starships)

        viewModel.getAllStarships()
            .subscribe()
    }
}