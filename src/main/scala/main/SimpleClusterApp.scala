package main

import akka.actor.ActorSystem
import akka.actor.Props
 
object SimpleClusterApp {
  def main(args: Array[String]): Unit = {
 
    if (args.nonEmpty) System.setProperty("akka.remote.netty.tcp.port", args(0))
 
    val system = ActorSystem("ClusterSystem")
    val actor = system.actorOf(Props[MyActor], name = "actor")

  }
}
