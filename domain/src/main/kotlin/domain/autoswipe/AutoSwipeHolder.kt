package domain.autoswipe

object AutoSwipeHolder {
    internal lateinit var autoSwipeIntentFactory: AutoSwipeIntentFactory

    fun autoSwipeIntentFactory(autoSwipeIntentFactory: AutoSwipeIntentFactory) {
        this.autoSwipeIntentFactory = autoSwipeIntentFactory
    }
}
