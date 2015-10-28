package sh.hashbangbin.cqrs.actors

import akka.actor.ActorRef
import com.rbmhtechnology.eventuate.EventsourcedActor

import scala.util.{Failure, Success}

case class CreateSupplier()
case class UpdateSupplier()
case class GetSuppliers()

case class SupplierUpdated()
case class SupplierCreated()
case class SupplierCreatedSuccess()


class SupplierActor(override val id: String, override val aggregateId: Option[String], val eventLog: ActorRef) extends EventsourcedActor {

  override def onEvent: Receive = {
    case SupplierCreated() => {

    }
  }

  override def onCommand: Receive = {
    case CreateSupplier => {
      persist(SupplierCreated){
        case Success(evt) => {
          println("Successfully persisted SupplierCreated() event")
          sender() ! SupplierCreatedSuccess()
        }
        case Failure(evt) => {
          println("FAILED to persist SupplierCreated() event")
        }
      }
    }
  }

}
