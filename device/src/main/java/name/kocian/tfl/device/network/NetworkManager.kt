package name.kocian.tfl.device.network

import io.reactivex.Observable

interface NetworkManager {

    fun isConnectionEstablished(): Boolean

    fun networkChangesObservable(): Observable<Boolean>
}
