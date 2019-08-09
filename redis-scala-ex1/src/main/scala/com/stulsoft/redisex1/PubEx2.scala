/*
 * Copyright (c) 2019. Yuriy Stul
 */

package com.stulsoft.redisex1

import akka.actor.ActorSystem
import com.typesafe.scalalogging.LazyLogging
import redis.RedisClient

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContextExecutor}

/** Synchronously
 *
 * @author Yuriy Stul
 */
object PubEx2 extends App with LazyLogging {
  implicit val sys: ActorSystem = ActorSystem("PubEx2")
  implicit val ex: ExecutionContextExecutor = sys.dispatcher

  val client = RedisClient(AppConfig.host, AppConfig.port)

  try {
    val executionResult = client.publish("ystest", """{"a":"aaaa","b":123}""")
    val l = Await.result(executionResult, 5.seconds)
    logger.info(s"Result = $l") // l here is number of listeners
    // do here something after success
  } catch {
    case ex: Exception => logger.error(s"Failure: ${ex.getMessage}")
  }
  finally {
    sys.terminate()
  }
}
