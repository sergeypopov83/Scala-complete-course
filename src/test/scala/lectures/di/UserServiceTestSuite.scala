package lectures.di

object UserServiceTestSuite {

  val createTable =
    """ CREATE TABLE USERS(
    ID    NUMBER NOT NULL,
    NAME  TEXT  NOT NULL,
    PWD   CHAR(50)  NOT NULL);"""

  val dropTale = "drop table if exists users"

  val addUser = "insert into users(id, name, pwd) values (?, ?, ?)"

  val users: Seq[(Int, String, String)] = Seq(
    (23, "Daniil", "qwerty1"),
    (21, "Felix", "qwerty2"),
    (22, "Frosya", "qwerty3"),
    (45, "Jimmy", "qwerty4"),
    (42, "Gevorg", "qwerty5"),
    (467, "Vakhtang", "qwerty6"),
    (231, "Isolda", "qwerty7"),
    (467, "Segismund", "qwerty8"),
    (86754, "Avraam", "qwerty9"),
    (333, "Isaac", "qwerty10"),
    (1, "Don King III", "qwerty10")
  )
}
