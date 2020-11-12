package com.charlye934.firstproyectreactive

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RX06Bus {
    private val bus = PublishSubject.create<Any>()
    var events: Observable<Any>
        get() = bus
        set(message) {
            bus.onNext(message)
        }

    companion object {
        var instance: RX06Bus? = null
            get() {
                if (field == null) {
                    field = RX06Bus()
                }
                return field
            }
            private set
    }
}
