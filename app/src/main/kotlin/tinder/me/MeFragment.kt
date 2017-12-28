package tinder.me

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout

internal class MeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?) =
            FrameLayout(context).also {
                it.background = ColorDrawable(Color.BLUE)
            }

    companion object {
        fun newInstance() = MeFragment()
    }
}
