/*
 * Copyright (c) 2019. Yuriy Stul
 */

package com.stulsoft.redisex1

import akka.actor.ActorSystem
import com.typesafe.scalalogging.LazyLogging
import redis.RedisClient

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

/** Asynchronously
 *
 * @author Yuriy Stul
 */
object PubEx1 extends App with LazyLogging {
  implicit val sys: ActorSystem = ActorSystem("PubEx1")
  implicit val ex: ExecutionContextExecutor = sys.dispatcher

  val client = RedisClient(AppConfig.host, AppConfig.port)

  client.publish("ystest", """{"a":"aaaa","b":123}""")
    .onComplete {
      case Success(l) =>
        logger.info(s"Result = $l")
        sys.terminate()
      case Failure(ex) =>
        logger.error(s"Failure: ${ex.getMessage}")
        sys.terminate()
    }
}
