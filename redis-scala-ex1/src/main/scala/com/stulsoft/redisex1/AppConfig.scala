/*
 * Copyright (c) 2019. Yuriy Stul
 */

package com.stulsoft.redisex1

import java.io.File

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging

/**
 * @author Yuriy Stul
 */
object AppConfig extends LazyLogging {
  private lazy val config = ConfigFactory.parseFile(new File("application.conf"))
    .withFallback(ConfigFactory.load())

  def host: String = config.getConfig("redis").getString("host")

  def port: Int = config.getConfig("redis").getInt("port")
}
