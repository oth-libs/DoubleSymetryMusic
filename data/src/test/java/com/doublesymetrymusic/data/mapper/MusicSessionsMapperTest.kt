package com.doublesymetrymusic.data.mapper

import com.doublesymetrymusic.data.factory.DataFactory
import com.doublesymetrymusic.data.model.MusicSessionsData
import com.doublesymetrymusic.domain.model.MusicSessions
import org.junit.Test
import kotlin.test.assertEquals

class MusicSessionsMapperTest {

  private val mapper: Mapper<MusicSessionsData, MusicSessions> = MusicSessionsMapper()

  @Test
  fun testMapper() {
    val expected = DataFactory.musicSessions

    // When
    val result = mapper.map(DataFactory.musicSessionsData)

    // Then
    assertEquals(expected, result)
  }

}