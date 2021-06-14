package com.doublesymetrymusic.data.mapper

import com.doublesymetrymusic.data.model.CurrentTrackData
import com.doublesymetrymusic.data.model.MusicSessionData
import com.doublesymetrymusic.data.model.MusicSessionsData
import com.doublesymetrymusic.data.model.SessionsData
import com.doublesymetrymusic.domain.model.CurrentTrack
import com.doublesymetrymusic.domain.model.MusicSession
import com.doublesymetrymusic.domain.model.MusicSessions
import com.doublesymetrymusic.domain.model.Sessions

/**
 * Map [MusicSessionsData] from retrofit to the model [MusicSessions]
 */
internal class MusicSessionsMapper : Mapper<MusicSessionsData, MusicSessions> {

  override fun map(from: MusicSessionsData): MusicSessions {
    return MusicSessions(
      data = mapSessions(from.data),
    )
  }

  private fun mapSessions(sessionsData: SessionsData?): Sessions? {
    return sessionsData?.run {
      Sessions(sessions = sessions?.mapNotNull { mapMusicSession(it) })
    }
  }

  private fun mapMusicSession(musicSessionData: MusicSessionData?): MusicSession? {
    return musicSessionData?.run {
      MusicSession(
        name = name,
        listenerCount = listener_count,
        genres = genres?.toList(),
        currentTrack = mapCurrentTrack(current_track)
      )
    }
  }

  private fun mapCurrentTrack(currentTrackData: CurrentTrackData?): CurrentTrack? {
    return currentTrackData?.run {
      CurrentTrack(
        title = title,
        artworkUrl = artwork_url
      )
    }
  }
}