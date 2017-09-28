package tracker

abstract class EventTrackers {
    companion object {
        fun firebase(): EventTracker = EventTrackerImpl.Firebase
    }
}
