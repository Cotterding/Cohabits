package ovh.cohabits.cohabit1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddColocationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddColocationFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_colocation, container, false)
        val cardView = view?.findViewById<CardView>(R.id.card_view_box_add_colocation)
        cardView?.setBackgroundResource(R.drawable.box)
        val button_add_colocation = view?.findViewById<Button>(R.id.button_add_colocation)
        button_add_colocation?.setBackgroundTintList(this.getResources().getColorStateList(R.color.purple_500));
        button_add_colocation?.setOnClickListener() {
        val textViewNomCodeColoc = view.findViewById<TextView>(R.id.textViewNomCodeColoc)
            textViewNomCodeColoc.visibility = View.VISIBLE
            val textCodeColoc = view.findViewById<TextView>(R.id.textCodeColoc)
            textCodeColoc.setText("A9HRM")
            textCodeColoc.visibility = View.VISIBLE
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddColocationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                AddColocationFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}