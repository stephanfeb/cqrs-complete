package sh.hashbangbin.cqrs.test

import java.util.UUID

import akka.actor.{Props, ActorSystem}
import akka.testkit.{ImplicitSender, TestKit}
import com.rbmhtechnology.eventuate.ReplicationEndpoint
import com.rbmhtechnology.eventuate.ReplicationEndpoint.DefaultLogName
import com.rbmhtechnology.eventuate.log.leveldb.LeveldbEventLog
import org.scalatest.{WordSpecLike, Matchers, BeforeAndAfterAll}
import sh.hashbangbin.cqrs.actors._
import akka.pattern.ask

import akka.util.Timeout
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.util.Success

class SupplierActorSpec(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
        with WordSpecLike with Matchers with BeforeAndAfterAll{

  implicit val timeout = Timeout(5 seconds)

  def this() = this(ActorSystem("SupplierActorSpec"))
  val endpoint = ReplicationEndpoint(id => LeveldbEventLog.props(id))(_system)
  val eventLog = endpoint.logs(DefaultLogName)


  override def afterAll() = {
    TestKit.shutdownActorSystem(system)
  }

  "A SupplierActor" must {

    "handle the CreateSupplier() command" in {

      val id = UUID.randomUUID().toString
      val aggId = UUID.randomUUID().toString
      val viewAggId = UUID.randomUUID().toString
      val supplierActor = system.actorOf(Props(new SupplierActor(id, Some(aggId), eventLog)))

      supplierActor ! CreateSupplier
      expectMsgType[SupplierCreatedSuccess]


      val viewActor = system.actorOf(Props(new SupplierView(UUID.randomUUID().toString, Some(aggId), eventLog)))

    }
  }

}
