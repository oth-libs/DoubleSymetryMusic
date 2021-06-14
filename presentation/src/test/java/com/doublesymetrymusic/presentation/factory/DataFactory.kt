package com.doublesymetrymusic.data.factory

import com.doublesymetrymusic.domain.model.CurrentTrack
import com.doublesymetrymusic.domain.model.MusicSession
import com.doublesymetrymusic.domain.model.MusicSessions
import com.doublesymetrymusic.domain.model.Sessions

internal object DataFactory {

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
        ),
        MusicSession(
          name = "name2",
          listenerCount = 2,
          genres = listOf("genre1", "genre2"),
          currentTrack = CurrentTrack(
            title = "title1",
            artworkUrl = "___"
          )
        ),
        MusicSession(
          name = "name3",
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