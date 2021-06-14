package com.doublesymetrymusic.data.mapper

internal interface Mapper<FROM, TO> {
  fun map(from: FROM): TO
}