package addressbook

/**
  * Created by shailesh on 26/03/2017.
  */



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

object AdddresBook extends AddressBook