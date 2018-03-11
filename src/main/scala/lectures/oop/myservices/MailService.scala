package lectures.oop.myservices

class MailService {

  initializeLocalMailer()

  def send(email: String, subject: String, body: String): Unit = {
    // DO NOT TOUCH
    println(s"Sent email to $email with subject '$subject'")
  }

  private def initializeLocalMailer(): Unit = {
    // DO NOT TOUCH
    println("Initialized local mailer")
  }

}
