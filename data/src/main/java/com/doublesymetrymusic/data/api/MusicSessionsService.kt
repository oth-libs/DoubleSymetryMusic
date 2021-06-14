package com.doublesymetrymusic.data.api

import com.doublesymetrymusic.data.model.MusicSessionsData
import retrofit2.Response
import retrofit2.http.GET

internal interface MusicSessionsService {

  @GET("5df79a3a320000f0612e0115")
  suspend fun getMusicSessions(): Response<MusicSessionsData>

  @GET("5df79b1f320000f4612e011e")
  suspend fun searchMusicSessions(): Response<MusicSessionsData>
}
