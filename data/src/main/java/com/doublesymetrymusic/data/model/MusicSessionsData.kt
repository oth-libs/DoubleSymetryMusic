package com.doublesymetrymusic.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MusicSessionsData(
  @SerialName("data") val data: SessionsData?
)

@Serializable
internal data class SessionsData(
  @SerialName("sessions") val sessions: List<MusicSessionData?>?
)

@Serializable
internal data class MusicSessionData(
  @SerialName("name") val name: String?,
  @SerialName("listener_count") val listener_count: Int?,
  @SerialName("genres") val genres: List<String>?,
  @SerialName("current_track") val current_track: CurrentTrackData?
)

@Serializable
internal data class CurrentTrackData(
  @SerialName("title") val title: String?,
  @SerialName("artwork_url") val artwork_url: String?
)