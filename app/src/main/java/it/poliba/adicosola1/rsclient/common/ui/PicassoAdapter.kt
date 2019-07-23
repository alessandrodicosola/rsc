package it.poliba.adicosola1.rsclient.common.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import it.poliba.adicosola1.rsclient.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {

    Picasso.get().load(url).fit().centerInside().error(R.drawable.ic_error_black_24dp).into(view)
}

