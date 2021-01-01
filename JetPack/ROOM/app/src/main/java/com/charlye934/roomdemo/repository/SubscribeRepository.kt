package com.charlye934.roomdemo.repository

import com.charlye934.roomdemo.db.Subscriber
import com.charlye934.roomdemo.db.SubscriberDAO

class SubscribeRepository(private val dao: SubscriberDAO) {

    val subscribers = dao.getAllSubscribers()

    suspend fun insert(subscriber: Subscriber){
        dao.insertSubscriber(subscriber)
    }

    suspend fun update(subscriber: Subscriber){
        dao.updateSubscriber(subscriber)
    }

    suspend fun delete(subscriber: Subscriber){
        dao.deleteSubcriber(subscriber)
    }

    suspend fun deleteAll(){
        dao.deleteAll()
    }
}