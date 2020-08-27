package com.google.codelabs.mdc.kotlin.shrine

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.Interpolator
import android.widget.ImageView

/**
 * [android.view.View.OnClickListener] used to translate the product grid sheet downward on
 * the Y-axis when the navigation icon in the toolbar is pressed.
 *
 * @property context [Context] to read [android.content.res.Resources] and [DisplayMetrics].
 * @property sheet [View] containing the front layer elements that needs to be
 * moved away (translated) in order to reveal the Backdrop menu.
 * @property interpolator [Interpolator] for defining the rate of change of animation for translation. Can be `null`.
 * @property openIcon [Drawable] resource for the Navigation icon to be shown when the Backdrop menu is hidden. Can be `null`.
 * @property closeIcon [Drawable] resource for the Navigation icon to be shown when the Backdrop menu is shown. Can be `null`.
 * @constructor Constructs the [View.OnClickListener], to show/hide Backdrop menu when
 * Navigation Icon is clicked.
 */
class NavigationIconClickListener @JvmOverloads internal constructor(
        private val context: Context,
        private val sheet: View,
        private val interpolator: Interpolator? = null,
        private val openIcon: Drawable? = null,
        private val closeIcon: Drawable? = null) : View.OnClickListener {

    // Container for animations to be played
    private val animatorSet = AnimatorSet()

    // Height of the screen
    private val height: Int

    // A Boolean state that keeps track of whether the Backdrop menu is shown/hidden
    private var backdropShown = false

    init {
        // Read and save the height of the screen from the current DisplayMetrics
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        height = displayMetrics.heightPixels
    }

    /**
     * Called when a view has been clicked.
     *
     * @param view The view that was clicked, which is the Navigation icon.
     */
    override fun onClick(view: View) {
        // Toggle the Backdrop menu state
        backdropShown = !backdropShown

        // Cancel all existing animations if any
        animatorSet.removeAllListeners()
        animatorSet.end()
        animatorSet.cancel()

        // Update the Navigation Icon based on the state of Backdrop menu
        updateIcon(view)

        // Compute and save the translation height required to move away the front layer to show the Backdrop menu
        val translateY = height - context.resources.getDimensionPixelSize(R.dimen.shr_product_grid_reveal_height)
        // Construct the Animator for "translationY" property
        val animator = ObjectAnimator.ofFloat(sheet, "translationY", (if (backdropShown) translateY else 0).toFloat())
        // Set the duration of translation animation
        animator.duration = 500
        // Set the Interpolator for translation animation if provided
        if (interpolator != null) {
            animator.interpolator = interpolator
        }
        // Build the animator to play this translation animation
        animatorSet.play(animator)
        // Start the translation animation
        animator.start()
    }

    /**
     * Changes the Navigation Icon to be shown based on the [backdropShown] state.
     *
     * @param view The view that was clicked, which is the Navigation icon.
     */
    private fun updateIcon(view: View) {
        // Modify only when icons for both states are provided
        if (openIcon != null && closeIcon != null) {
            // Ensure this method is called for the ImageView
            if (view !is ImageView) {
                throw IllegalArgumentException("updateIcon() must be called on an ImageView")
            }
            // Set the Navigation Icon based on the state of Backdrop menu
            if (backdropShown) {
                // If Backdrop menu is going to be shown, set the icon for close action
                view.setImageDrawable(closeIcon)
            } else {
                // If Backdrop menu is going to be hidden, set the icon for open action
                view.setImageDrawable(openIcon)
            }
        }
    }
}
