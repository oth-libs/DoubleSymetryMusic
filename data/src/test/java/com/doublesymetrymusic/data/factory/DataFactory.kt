package com.doublesymetrymusic.data.factory

import com.doublesymetrymusic.data.model.CurrentTrackData
import com.doublesymetrymusic.data.model.MusicSessionData
import com.doublesymetrymusic.data.model.MusicSessionsData
import com.doublesymetrymusic.data.model.SessionsData
import com.doublesymetrymusic.domain.model.CurrentTrack
import com.doublesymetrymusic.domain.model.MusicSession
import com.doublesymetrymusic.domain.model.MusicSessions
import com.doublesymetrymusic.domain.model.Sessions

internal object DataFactory {

  val musicSessionsData = MusicSessionsData(
    data = SessionsData(
      sessions = listOf(
        MusicSessionData(
          name = "name1",
          listener_count = 2,
          genres = listOf("genre1", "genre2"),
          current_track = CurrentTrackData(
            title = "title1",
            artwork_url = "___"
          )
        )
      )
    )
  )

  val musicSessions = MusicSessions(
    data = Sessions(
      sessions = listOf(
        MusicSession(
          name = "name1",
          listenerCount = 2,
          genres = listOf("genre1", "genre2"),
          currentTrack = CurrentTrack(
            title = "title1",
            artworkUrl = "___"
          )
        )
      )
    )
  )
}