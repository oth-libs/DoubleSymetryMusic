package com.doublesymetrymusic.domain.model

data class MusicSessions(
  val data: Sessions?
) {
  fun getMusicSessions() = data?.sessions
}

data class Sessions(
  val sessions: List<MusicSession>?
)

data class MusicSession(
  val name: String?,
  val listenerCount: Int?,
  val genres: List<String>?,
  val currentTrack: CurrentTrack?
) {
  fun getGenresString() = genres?.joinToString(", ") ?: String()

  fun getListenersCount() = "${listenerCount ?: 0}"

  fun getArtworkUrl() = currentTrack?.artworkUrl
}

data class CurrentTrack(
  val title: String?,
  val artworkUrl: String?
)