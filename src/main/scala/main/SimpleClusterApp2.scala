package main

import akka.actor.ActorSystem
import akka.actor.Actor
import akka.actor.Props

object SimpleClusterApp2 {
  def main(args: Array[String]): Unit = {
 
    if (args.nonEmpty) System.setProperty("akka.remote.netty.tcp.port", args(0))
 
    val system = ActorSystem("ClusterSystem")
    val localActor = system.actorOf(Props[MyActor], name = "Ahya")
	localActor ! "say hi"
	
  }
}

class MyActor extends Actor {          
  def receive = {
        case "say hi" => {
          val actor = context.system.actorFor("akka.tcp://ClusterSystem@192.168.1.2:2551/user/actor")
          println("From PC-1 to PC-2")
          actor ! "hello"
          sender ! "hello"
        }
        case "hello" => {
//          println("got hello.. sending say hi back")
//          sender ! "say hi back"
          println("From PC-2 to PC-1")
          sender ! "say hi"
        }
        case "say hi back" => {
          println("received it back motherfucker")
        }
    }

}