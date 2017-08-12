package lectures.di

trait UserService {
  def setUserPwd(id: String, pwd: String)
}

trait Configuration {
  def attribute(attrName: String): Option[String]

  def setAttribute(attrName: String, value: String): Unit
}