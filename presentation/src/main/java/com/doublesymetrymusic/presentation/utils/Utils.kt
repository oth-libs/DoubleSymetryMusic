package com.doublesymetrymusic.presentation.utils

/**
 * check for a class that is only in classpath when testing
 */
fun isUiTest(): Boolean {
  return try {
    Class.forName("com.doublesymetrymusic.presentation.homepage.HomePageViewModelTest")
    true
  } catch (e: ClassNotFoundException) {
    false
  }
}