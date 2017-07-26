package domain.autoswipe

object AutoSwipeHolder {
    internal lateinit var autoSwipeIntentServiceStarterFactory: AutoSwipeIntentServiceStarterFactory

    fun autoSwipeIntentFactory(
            autoSwipeIntentServiceStarterFactory: AutoSwipeIntentServiceStarterFactory) {
        this.autoSwipeIntentServiceStarterFactory = autoSwipeIntentServiceStarterFactory
    }
}
