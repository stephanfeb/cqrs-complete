package sh.hashbangbin.cqrs.actors

import java.util.UUID

import akka.actor.{Terminated, Props, ActorSystem}
import javax.inject._

import akka.pattern.ask
import akka.util.Timeout
import com.rbmhtechnology.eventuate.ReplicationEndpoint
import com.rbmhtechnology.eventuate.ReplicationEndpoint._
import com.rbmhtechnology.eventuate.log.leveldb.LeveldbEventLog

import scala.concurrent.duration._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Try, Success}

object EventSourceApp extends App {
  implicit val timeout: Timeout = 5 seconds

  val system = ActorSystem.create("hashbangbin")
  val endpoint = ReplicationEndpoint(id => LeveldbEventLog.props(id))(system)
  val eventLog = endpoint.logs(DefaultLogName)

  def hello(greeting: String) = {
      val id = UUID.randomUUID().toString
      val aggId = UUID.randomUUID().toString
      val viewAggId = UUID.randomUUID().toString
      val supplierActor = system.actorOf(Props(new SupplierActor(id, Some(aggId), eventLog)))
      val viewActor = system.actorOf(Props(new SupplierView(UUID.randomUUID().toString, Some(aggId), eventLog)))

      supplierActor ! CreateSupplier


  }

  hello("world")
//
//  system.terminate().onComplete {
//    case _ : Try[Terminated] => {
//      println("OK. Shutting down.")
//    }
//  }

}
