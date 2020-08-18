package com.google.codelabs.mdc.kotlin.shrine

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.codelabs.mdc.kotlin.shrine.network.ProductEntry
import com.google.codelabs.mdc.kotlin.shrine.staggeredgridlayout.StaggeredProductCardRecyclerViewAdapter
import kotlinx.android.synthetic.main.shr_product_grid_fragment.view.*

class ProductGridFragment : Fragment() {

    /**
     * Called to do initial creation of a fragment.
     *
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This fragment has options menu to show
        setHasOptionsMenu(true)
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return Returns the inflated View 'R.layout.shr_product_grid_fragment' for the fragment's UI.
     */
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.shr_product_grid_fragment, container, false)

        // Setup Toolbar as ActionBar
        (activity as AppCompatActivity).setSupportActionBar(rootView.app_bar)

        // Setup the RecyclerView
        rootView.recycler_view.apply {
            // Initialize a Horizontal GridLayoutManager with 2 columns
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false).apply {
                // Initialize the number of spans occupied by each item dynamically
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    /**
                     * Returns the number of span occupied by the item at `position`.
                     *
                     * @param position The adapter position of the item
                     * @return The number of spans occupied by the item at the provided position
                     */
                    override fun getSpanSize(position: Int): Int {
                        return if (position % 3 == 2) 2 else 1
                    }
                }
            }
            // Initialize the Adapter with the Product List entries
            adapter = StaggeredProductCardRecyclerViewAdapter(ProductEntry.initProductEntryList(resources))
            // All Items have fixed size
            setHasFixedSize(true)
            // Add Item Decoration for padding
            addItemDecoration(ProductGridItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.shr_product_grid_spacing),
                    resources.getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small)
            ))
        }

        return rootView
    }

    /**
     * Initialize the contents of the Fragment host's standard options menu.
     *
     * @param menu The options menu in which you place your items.
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Inflate the menu for this fragment
        inflater.inflate(R.menu.shr_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}
