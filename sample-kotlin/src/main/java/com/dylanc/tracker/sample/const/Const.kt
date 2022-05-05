package com.dylanc.tracker.sample.const

const val UM_APP_ID = "626ca3f630a4f67780c223e6"
const val UM_CHANNEL = "Umeng"

val referrerKeyMap by lazy {
  mapOf("page_name" to "from_page", "tab_name" to "from_tab_name", "channel_name" to "from_channel_name")
}