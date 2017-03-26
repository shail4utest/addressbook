package addressbook

import org.joda.time.DateTime

/**
  * Created by shailesh on 26/03/2017.
  */


case class Profile(name:String,gender:String,dateOfBirth:DateTime)

trait AddressBook{

  /**
    *
    * @param name File to be loaded from resources
    * @return List[String]
    */
  def readFile(name:String) : List[String]={
    val bufferedSource = io.Source.fromInputStream(getClass().getClassLoader().getResourceAsStream(name))
     bufferedSource.getLines.toList
  }

}

object AddressBook extends AddressBook