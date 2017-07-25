package domain.alarm

abstract class AppAlarmManager<in T> {
    fun immediateOneShot(requestCode: Int, task: T) = delayOneShot(requestCode, 0, task)
    abstract fun delayOneShot(requestCode: Int, delayMillis: Long, task: T): Unit
}
