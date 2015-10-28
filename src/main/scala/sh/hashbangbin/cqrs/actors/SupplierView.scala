package sh.hashbangbin.cqrs.actors

import akka.actor.ActorRef
import com.rbmhtechnology.eventuate.EventsourcedView

class SupplierView(override val id: String, override val aggregateId: Option[String], override val eventLog: ActorRef) extends EventsourcedView{

  override def onEvent: Receive = {
    case SupplierCreated => {
      //update SQL database
      println("sending supplier to DB")
    }
    case SupplierUpdated => {
      //update SQL database
      println("update supplier in DB")
    }
  }

  override def onCommand: Receive = {
    case GetSuppliers => {
      //retrieve suppliers from SQL database
    }
  }

}
