package lectures.oop.myservices

trait EsbService{
  def sendMessageToIbmMq(message: String): String
}

class IbmMqService extends  EsbService {
  private val connectionId = connectToIbmMq()

  private def connectToIbmMq(): Int = {
    // DO NOT TOUCH
    println("Connected to IBM WebSphere super-duper MQ Manager")
    13 // chosen by fair dice roll
  }

  def sendMessageToIbmMq(message: String): String = {
    // DO NOT TOUCH
    println(s"Sent MQ message via $connectionId: $message")
    s"Message sending result for $message"
  }
}
